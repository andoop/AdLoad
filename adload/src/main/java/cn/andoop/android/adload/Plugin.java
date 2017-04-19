package cn.andoop.android.adload;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：插件对象
* * * * * * * * * * * * * * * * * * */

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.DownLoadListener;
import cn.andoop.android.adload.listener.PluginInitListener;
import cn.andoop.android.adload.manager.DbManager;
import cn.andoop.android.adload.manager.DlManager;
import cn.andoop.android.adload.manager.PluginRepertory;
import dalvik.system.DexClassLoader;

public class Plugin implements Serializable{

    protected String id;
    protected int version;
    protected  String url;
    protected String md5;
    protected String main;
    protected String filename;

    public Plugin(String id, int version, String url, String md5, String main,String filename) {
        this.id = id;
        this.version = version;
        this.url = url;
        this.md5 = md5;
        this.main = main;
        this.filename=filename;
    }

    /**
     * 生成插件对象
     * @param json
     * @return
     */
    public static Plugin parse(String json){

        Plugin plugin = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String id = jsonObject.optString("id");
            String url = jsonObject.optString("url");
            int version=jsonObject.optInt("version");
            String md5=jsonObject.optString("md5");
            String main=jsonObject.optString("main");
            String filename=jsonObject.optString("filename");
            plugin=new Plugin(id,version,url,md5,main,filename);

        } catch (Exception e) {
            Log.e("----->" + "Plugin", "parse:" + e.toString());
            return null;
        }
        return plugin;
    }

    /**
     * 插件初始化，初始化成功后就可以使用
     * @param pluginInitListener
     */
    public void init(Activity context, PluginInitListener pluginInitListener){
        int version = DbManager.newInstance(context).getVersion(this.id);
        if(this.version>version){
            //需要更新插件
            //通知需要更新插件
            if(pluginInitListener !=null){
                pluginInitListener.update(true);
            }
            //下载插件
            toDownload(context, pluginInitListener);
        }else {
            //获取插件路径
            String path = DbManager.newInstance(context).getPath(id);
            //判断参见是否存在
            if(new File(path).exists()){
                loadDynamic(path,context,pluginInitListener);
            }else {
                toDownload(context,pluginInitListener);
            }
        }

    }
    //去下载插件
    private void toDownload(final Activity context, final PluginInitListener pluginInitListener) {

        //如果旧文件存在，删除旧文件
        String path = DbManager.newInstance(context).getPath(id);
        if(!TextUtils.isEmpty(path)&&new File(path).exists()){
            new File(path).delete();
        }

        DlManager.newInstance(context).download(this,new DownLoadListener(){
            @Override
            public void dlStart(Plugin plugin) {
                if (pluginInitListener !=null){
                    pluginInitListener.dlStart(plugin);
                }
            }

            @Override
            public void dlIng(Plugin plugin, int percent) {
                if (pluginInitListener !=null){
                    pluginInitListener.dlIng(plugin,percent);
                }
            }

            @Override
            public void dlCompleted(Plugin plugin,String path) {
                if (pluginInitListener !=null){
                    pluginInitListener.dlCompleted(plugin,path);
                }

                loadDynamic(path, context, pluginInitListener);
            }
            @Override
            public void dlFail(Plugin plugin, String msg) {
                if (pluginInitListener !=null){
                    pluginInitListener.dlFail(plugin,msg);
                }
            }
        });
    }

    private void loadDynamic(String path, final Activity context, PluginInitListener pluginInitListener) {
        //生成对应的DexClassLoader
        DexClassLoader dexClassLoader = new DexClassLoader(path,DlManager.newInstance(context).getDexFilePath(), null, context.getClassLoader());
        try {
            Class<?> aClass = dexClassLoader.loadClass(main);
            HashMap<String,Object> instance= ( HashMap<String,Object>) aClass.newInstance();
            final AdPlugin adPlugin = new AdPlugin(this, instance,dexClassLoader);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //初始化真正的插件
                    adPlugin.excute(
                            new MsgEntity()
                                    .addCmd("type","init")
                                    .addCmd("context",context)
                    );
                }
            });

            //添加到仓库中
            PluginRepertory.newInstance().addAdPlugin(id,adPlugin);
            //更新数据库
            DbManager.newInstance(context).update(this,path);
            if (pluginInitListener !=null){
                pluginInitListener.initSuccess(adPlugin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //移除到仓库中
            PluginRepertory.newInstance().removeAdPlugin(id);
            Log.e("----->" + "Plugin", "dlCompleted:" + e.toString());
            if (pluginInitListener !=null){
                pluginInitListener.initFail(e.toString());
            }
        }
    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }

    public String getMd5() {
        return md5;
    }

    public String getMain() {
        return main;
    }

    public String getFilename() {
        return filename;
    }
}

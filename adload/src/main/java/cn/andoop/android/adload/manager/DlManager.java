package cn.andoop.android.adload.manager;

import android.content.Context;

import java.io.File;

import cn.andoop.android.adload.Plugin;
import cn.andoop.android.adload.download.AndoopDownloadExcutor;
import cn.andoop.android.adload.download.AndoopDownloadListener;
import cn.andoop.android.adload.download.AndoopDownloadThread;
import cn.andoop.android.adload.listener.DownLoadListener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：下载管理者
* * * * * * * * * * * * * * * * * * */
public class DlManager {
    private Context context;
    private static DlManager INSTANCE;
    private AndoopDownloadExcutor downloadExcutor;

    private DlManager(Context context) {
        this.context = context;
        downloadExcutor=new AndoopDownloadExcutor();
    }

    public static DlManager newInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (DlManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DlManager(context);
                }
            }

        }
        return INSTANCE;
    }
    //dex文件夹路径
    public String getDexFilePath(){
        File optimizedFile=  new File(context.getFilesDir().getAbsolutePath()+ File.separator+"mdex");
        if(!optimizedFile.exists()){
            optimizedFile.mkdirs();
        }
        return optimizedFile.getAbsolutePath();
    }

    /**
     * 下载插件
     * @param plugin
     * @param downLoadListener
     */
    public void download(final Plugin plugin, final DownLoadListener downLoadListener) {
        String suffix=".jar";
        if(plugin.getUrl().endsWith(".apk")){
            suffix=".apk";
        }
        AndoopDownloadThread andoopDownloadThread = new AndoopDownloadThread(context, plugin.getUrl(), plugin.getMd5(), suffix,plugin.getFilename(), getDexFilePath(), new AndoopDownloadListener() {
            @Override
            public void onStart(String url, String realUrl) {
                if(downLoadListener!=null)
                     downLoadListener.dlStart(plugin);
            }

            @Override
            public void onLoading(long current, long count) {
                if(downLoadListener!=null)
                    downLoadListener.dlIng(plugin, (int) ((current*100)/count));
            }

            @Override
            public void onSuccess(String url, String realUrl, String contentType, File f) {
                if(downLoadListener!=null)
                    downLoadListener.dlCompleted(plugin,f.getAbsolutePath());
            }

            @Override
            public void onFailure(String error) {
                if(downLoadListener!=null)
                    downLoadListener.dlFail(plugin,error);
            }
        });

        downloadExcutor.excuteDownLoad(andoopDownloadThread );
    }
}

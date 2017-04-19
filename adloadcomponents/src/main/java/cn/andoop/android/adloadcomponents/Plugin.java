package cn.andoop.android.adloadcomponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.andoop.android.adloadcomponents.activity.ActivityWrap;
import cn.andoop.android.adloadcomponents.service.ServiceWrap;


/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/4/19
* explain：插件接口基类
* * * * * * * * * * * * * * * * * * */
public abstract class Plugin extends HashMap<String,Object> implements Serializable{
    //插件activity包装类集合
    private Map<String,ActivityWrap> activityWrapMap;
    //插件service包装类集合
    private Map<String,ServiceWrap> serviceWrapMap;
    //初始化时进行赋值
    private Context mContext;
    private String mPlugId="001";

    public Plugin(){
        activityWrapMap=new ArrayMap<>();
    }
    @Override
    public void putAll(Map<? extends String, ?> m) {
        DataEntity dataEntity = new DataEntity(m);
        //判断是否子类进行拦截
        if(onReceivedCmds(dataEntity)){
            Log.e("----->" + "Plugin", "putAll:" + "cmds_interrupted");
            return;
        }

        String type=dataEntity.type();

        if("init".equals(type)){
            //初始化
            if(activityWrapMap==null){
                activityWrapMap=new ArrayMap<>();
            }
            if(serviceWrapMap==null){
                serviceWrapMap=new ArrayMap<>();
            }

            //注册插件中控件
            registComponents(activityWrapMap,serviceWrapMap);
            mContext= (Context) m.get("context");
            //调用init方法
            onInit(mContext,dataEntity);
        }else if("start_activity".equals(type)){
            Context context= (Context) m.get("context");
            if(!onStartActivity(context,dataEntity)){
                //开启activity
                String activity_id=m.get("activity_id").toString();
                ActivityWrap activityWrap = activityWrapMap.get(activity_id);
                if(activityWrap==null){
                    throw new NullPointerException("no activity match the id:"+activity_id);
                }
                Class clazz= (Class) m.get("proxy_activity_class");
                String id=m.get("plugin_id").toString();
                String activityid=m.get("activity_id").toString();
                startActivity(context, clazz, id, activityid,null);
            }else {
                Log.e("----->" + "Plugin", "putAll:" + "start_activity_interrupted");
            }
        }else if("activity_life_circle".equals(type)){
            activityWrapMap.get(m.get("activity_id").toString()).putAll(m);
        }else{

        }
    }


    /**
     * 注册控件，目前支持activity和service
     * @param activityWrapMap
     * @param serviceWrapMap
     */
    protected abstract void registComponents(Map<String,ActivityWrap> activityWrapMap,Map<String,ServiceWrap> serviceWrapMap);

    /**
     * 插件初始化时，会回调此方法
     * @param context
     */
    protected abstract void onInit(Context context,DataEntity dataEntity);

    /**
     * 当接收指令时进行回调，除本框架已经占用的指令，如 init
     * @param context
     * @param dataEntity
     */
    protected abstract void onComands(Context context,DataEntity dataEntity);
    /**
     * 当 开启activity时，将首先回调此方法,子类可以重新此方法进行拦截动作
     * @param context
     * @param dataEntity
     * @return  true 由子类处理并拦截不进行后续逻辑，false 不进行拦截，后续逻辑正常进行
     */
    protected boolean onStartActivity(Context context,DataEntity dataEntity){

        return false;
    }

    /**
     * 一旦接收到指令，就会首先调用次方法，子类可以重载此方法拦截指令然后自己做处理
     * @param dataEntity
     * @return true 拦截一切指令  false 不拦截指令
     */
    protected boolean onReceivedCmds(DataEntity dataEntity){
        return false;
    }

    /**
     * 开启activity
     * @param context
     * @param proxyActivityClazz 代理activity的class
     * @param plugId  插件id
     * @param activityId  要启动的插件activity的id
     * @param extra  要传入的数据
     */
    public void startActivity(Context context, Class proxyActivityClazz, String plugId, String activityId,Bundle extra) {
        Intent intent = new Intent(context, proxyActivityClazz);
        if(extra==null)
            extra=new Bundle();
        extra.putString("plugin_id", plugId);
        extra.putString("activity_id",activityId);
        intent.putExtra("data",extra);
        context.startActivity(intent);
    }

    /**
     * 获取插件id
     * @return
     */
    public String getId() {
        return mPlugId;
    }

    /**
     * 设置插件id
     * @param id
     */
    public void setId(String id) {
        this.mPlugId = id;
    }
}

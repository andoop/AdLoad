package cn.andoop.android.adloadcomponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import cn.andoop.android.adloadcomponents.activity.ActivityWrap;
import cn.andoop.android.adloadcomponents.service.ServiceWrap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Plugin
        extends HashMap<String, Object>
        implements Serializable
{
    private Map<String, ActivityWrap> activityWrapMap;
    private Map<String, ServiceWrap> serviceWrapMap;
    protected Context mContext;
    private String mPlugId = "001";

    public Plugin()
    {
        this.activityWrapMap = new ArrayMap();
    }

    public void putAll(Map m)
    {
        DataEntity dataEntity = new DataEntity(m);
        if (onReceivedCmds(dataEntity))
        {
            Log.e("----->Plugin", "putAll:cmds_interrupted");
            return;
        }
        String type = dataEntity.type();
        if ("init".equals(type))
        {
            if (this.activityWrapMap == null) {
                this.activityWrapMap = new ArrayMap();
            }
            if (this.serviceWrapMap == null) {
                this.serviceWrapMap = new ArrayMap();
            }
            registComponents(this.activityWrapMap, this.serviceWrapMap);
            this.mContext = ((Context)m.get("context"));

            onInit(this.mContext, dataEntity);
        }
        else if ("start_activity".equals(type))
        {
            Context context = (Context)m.get("context");
            if (!onStartActivity(context, dataEntity))
            {
                String activity_id = m.get("activity_id").toString();

                ActivityWrap activityWrap = (ActivityWrap)this.activityWrapMap.get(activity_id);
                if (activityWrap == null) {
                    throw new NullPointerException("no activity match the id:" + activity_id);
                }
                Class clazz = (Class)m.get("proxy_activity_class");
                String id = m.get("plugin_id").toString();
                String activityid = m.get("activity_id").toString();
                startActivity(context, clazz, id, activityid, null);
            }
            else
            {
                Log.e("----->Plugin", "putAll:start_activity_interrupted");
            }
        }
        else if ("start_service".equals(type))
        {
            Context context = (Context)m.get("context");
            if (!onStartService(context, dataEntity))
            {
                String service_id = m.get("service_id").toString();

                ServiceWrap serviceWrap = (ServiceWrap)this.serviceWrapMap.get(service_id);
                if (serviceWrap == null) {
                    throw new NullPointerException("no service match the id:" + service_id);
                }
                Class clazz = (Class)m.get("proxy_service_class");
                String id = m.get("plugin_id").toString();
                String serviceid = m.get("service_id").toString();
                startService(context, clazz, id, serviceid, null);
            }
            else
            {
                Log.e("----->Plugin", "putAll:start_service_interrupted");
            }
        }
        else if ("activity_life_circle".equals(type))
        {
            ((ActivityWrap)this.activityWrapMap.get(m.get("activity_id").toString())).putAll(m);
        }
        else if ("service_life_circle".equals(type))
        {
            ((ServiceWrap)this.serviceWrapMap.get(m.get("service_id").toString())).putAll(m);
        }
    }

    protected abstract void registComponents(Map<String, ActivityWrap> paramMap, Map<String, ServiceWrap> paramMap1);

    protected abstract void onInit(Context paramContext, DataEntity paramDataEntity);

    protected boolean onStartActivity(Context context, DataEntity dataEntity)
    {
        return false;
    }

    protected boolean onStartService(Context context, DataEntity dataEntity)
    {
        return false;
    }

    protected boolean onReceivedCmds(DataEntity dataEntity)
    {
        return false;
    }

    private void startActivity(Context context, Class proxyActivityClazz, String plugId, String activityId, Bundle extra)
    {
        Intent intent = new Intent(context, proxyActivityClazz);
        if (extra == null) {
            extra = new Bundle();
        }
        extra.putString("plugin_id", plugId);
        extra.putString("activity_id", activityId);
        intent.putExtras(extra);
        context.startActivity(intent);
    }

    private void startService(Context context, Class proxyServiceClazz, String plugId, String serviceid, Bundle extra)
    {
        Intent intent = new Intent(context, proxyServiceClazz);
        if (extra == null) {
            extra = new Bundle();
        }
        extra.putString("plugin_id", plugId);
        extra.putString("service_id", serviceid);
        intent.putExtras(extra);
        context.startService(intent);
    }

    public String getId()
    {
        return this.mPlugId;
    }

    public void setId(String id)
    {
        this.mPlugId = id;
    }
}

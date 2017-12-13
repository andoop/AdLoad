package cn.andoop.android.adloadcomponents.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import java.util.HashMap;
import java.util.Map;

public class ServiceWrap
{
    private IService iService;

    public ServiceWrap(IService iService)
    {
        this.iService = iService;
    }

    public void putAll(Map<String, Object> m)
    {
        String method = m.get("method").toString();
        Object value = m.get("parameters");
        if ("onCreate".equals(method))
        {
            Service proxy = (Service)m.get("proxy_service");
            this.iService.bindProxy(proxy);

            this.iService.onCreate();
        }
        else if ("onStart".equals(method))
        {
            HashMap<String, Object> map = (HashMap)value;
            Intent intent = (Intent)map.get("intent");
            int startId = ((Integer)map.get("startId")).intValue();
            this.iService.onStart(intent, startId);
        }
        else if ("onStartCommand".equals(method))
        {
            HashMap<String, Object> map = (HashMap)value;
            Intent intent = (Intent)map.get("intent");
            int flags = ((Integer)map.get("flags")).intValue();
            int startId = ((Integer)map.get("startId")).intValue();
            this.iService.onStartCommand(intent, flags, startId);
        }
        else if ("onDestroy".equals(method))
        {
            this.iService.onDestroy();
        }
        else if ("onConfigurationChanged".equals(method))
        {
            Configuration newConfig = (Configuration)value;
            this.iService.onConfigurationChanged(newConfig);
        }
        else if ("onLowMemory".equals(method))
        {
            this.iService.onLowMemory();
        }
        else if ("onTrimMemory".equals(method))
        {
            int level = ((Integer)value).intValue();
            this.iService.onTrimMemory(level);
        }
        else if ("onBind".equals(method))
        {
            Intent intent = (Intent)value;
            this.iService.onBind(intent);
        }
        else if ("onUnbind".equals(method))
        {
            Intent intent = (Intent)value;
            this.iService.onUnbind(intent);
        }
        else if ("onRebind".equals(method))
        {
            Intent intent = (Intent)value;
            this.iService.onRebind(intent);
        }
        else if ("onTaskRemoved".equals(method))
        {
            Intent rootIntent = (Intent)value;
            this.iService.onTaskRemoved(rootIntent);
        }
    }
}

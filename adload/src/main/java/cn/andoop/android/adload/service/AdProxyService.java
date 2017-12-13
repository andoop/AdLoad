package cn.andoop.android.adload.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import cn.andoop.android.adload.AdPlugin;
import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.PluginDataListener;
import cn.andoop.android.adload.manager.PluginRepertory;
import java.util.HashMap;
import java.util.Map;

public class AdProxyService
  extends Service
  implements PluginDataListener
{
  private Map<String, AdPlugin> pluginMap;
  
  public void onCreate()
  {
    super.onCreate();
    this.pluginMap = new HashMap();
  }
  
  @Nullable
  public IBinder onBind(Intent intent)
  {
    final IBinder[] iBinder = new IBinder[1];
    
    Bundle extras = intent.getExtras();
    if (extras != null)
    {
      AdPlugin plugin = PluginRepertory.newInstance().getAdPlugin(extras.getString("plugin_id"));
      String pluginServiceId = extras.getString("service_id");
      if (plugin == null)
      {
        Log.e("----->APService", "onCreate:err: mPlugin==null");
        return iBinder[0];
      }
      if (!this.pluginMap.containsKey(pluginServiceId)) {
        plugin.excute(new MsgEntity()
        
          .addCmd("type", "service_life_circle")
          .addCmd("proxy_service", this)
          .addCmd("service_id", pluginServiceId)
          .addCmd("method", "onCreate")
          .addPluginDataListener(this));
      }
      this.pluginMap.put(pluginServiceId, plugin);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", pluginServiceId)
        .addCmd("method", "onBind")
        .addCmd("parameters", intent)
        .addPluginDataListener(new PluginDataListener()
        {
          public void onCallBack(Map<? extends String, ?> m)
          {
            iBinder[0] = ((IBinder)m.get("binder"));
          }
        }));
    }
    else
    {
      Log.e("----->APService", "onCreate:err: extras==null");
      return iBinder[0];
    }
    return iBinder[0];
  }
  
  public int onStartCommand(Intent intent, int flags, int startId)
  {
    if (intent != null)
    {
      Bundle extras = intent.getExtras();
      if (extras != null)
      {
        AdPlugin plugin = PluginRepertory.newInstance().getAdPlugin(extras.getString("plugin_id"));
        String pluginServiceId = extras.getString("service_id");
        if (plugin == null)
        {
          Log.e("----->APService", "onCreate:err: mPlugin==null");
          return super.onStartCommand(intent, flags, startId);
        }
        if (!this.pluginMap.containsKey(pluginServiceId)) {
          plugin.excute(new MsgEntity()
          
            .addCmd("type", "service_life_circle")
            .addCmd("proxy_service", this)
            .addCmd("service_id", pluginServiceId)
            .addCmd("method", "onCreate")
            .addPluginDataListener(this));
        }
        this.pluginMap.put(pluginServiceId, plugin);
        Map<String, Object> parameters = new HashMap();
        parameters.put("intent", intent);
        parameters.put("flags", Integer.valueOf(flags));
        parameters.put("startId", Integer.valueOf(startId));
        plugin.excute(new MsgEntity()
        
          .addCmd("type", "service_life_circle")
          .addCmd("proxy_service", this)
          .addCmd("service_id", pluginServiceId)
          .addCmd("method", "onStartCommand")
          .addCmd("parameters", parameters)
          .addPluginDataListener(this));
      }
      else
      {
        Log.e("----->APService", "onCreate:err: extras==null");
        return super.onStartCommand(intent, flags, startId);
      }
    }
    else
    {
      Log.e("----->APService", "onStartCommand:intent is null");
    }
    return super.onStartCommand(intent, flags, startId);
  }
  
  public void onCallBack(Map<? extends String, ?> m)
  {
    Log.e("----->APService", "onCallBack:" + m);
  }
  
  public void onDestroy()
  {
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onDestroy")
        .addCmd("parameters", "")
        .addPluginDataListener(this));
    }
    super.onDestroy();
  }
  
  public void onConfigurationChanged(Configuration newConfig)
  {
    super.onConfigurationChanged(newConfig);
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onConfigurationChanged")
        .addCmd("parameters", newConfig)
        .addPluginDataListener(this));
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onLowMemory")
        
        .addPluginDataListener(this));
    }
  }
  
  public void onTrimMemory(int level)
  {
    super.onTrimMemory(level);
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onTrimMemory")
        .addCmd("parameters", Integer.valueOf(level))
        .addPluginDataListener(this));
    }
  }
  
  public boolean onUnbind(Intent intent)
  {
    final Boolean[] result = { Boolean.valueOf(super.onUnbind(intent)) };
    if ((intent != null) && (intent.getExtras() != null))
    {
      AdPlugin plugin = PluginRepertory.newInstance().getAdPlugin(intent.getExtras().getString("plugin_id"));
      String pluginServiceId = intent.getExtras().getString("service_id");
      if (plugin != null) {
        plugin.excute(new MsgEntity()
        
          .addCmd("type", "service_life_circle")
          .addCmd("proxy_service", this)
          .addCmd("service_id", pluginServiceId)
          .addCmd("method", "onUnbind")
          .addCmd("parameters", intent)
          .addPluginDataListener(new PluginDataListener()
          {
            public void onCallBack(Map<? extends String, ?> m)
            {
              result[0] = ((Boolean)m.get("data"));
            }
          }));
      }
    }
    return result[0].booleanValue();
  }
  
  public void onRebind(Intent intent)
  {
    super.onRebind(intent);
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onRebind")
        .addCmd("parameters", intent)
        .addPluginDataListener(this));
    }
  }
  
  public void onTaskRemoved(Intent rootIntent)
  {
    super.onTaskRemoved(rootIntent);
    for (String id : this.pluginMap.keySet())
    {
      AdPlugin plugin = (AdPlugin)this.pluginMap.get(id);
      plugin.excute(new MsgEntity()
      
        .addCmd("type", "service_life_circle")
        .addCmd("proxy_service", this)
        .addCmd("service_id", id)
        .addCmd("method", "onTaskRemoved")
        .addCmd("parameters", rootIntent)
        .addPluginDataListener(this));
    }
  }
}

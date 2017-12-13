package cn.andoop.android.adload;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import cn.andoop.android.adload.activity.AdProxyActivity;
import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.PluginDataListener;
import cn.andoop.android.adload.service.AdProxyService;
import dalvik.system.DexClassLoader;
import java.util.HashMap;

public class AdPlugin
        extends Plugin
{
    private HashMap<String, Object> instance;
    private DexClassLoader mDexClassLoader;

    public AdPlugin(Plugin plugin, HashMap<String, Object> instance, DexClassLoader dexClassLoader)
    {
        super(plugin.getId(), plugin.getVersion(), plugin.getUrl(), plugin.getMd5(), plugin.getMain(), plugin.getFilename());
        this.instance = instance;
    }

    public void excute(MsgEntity msgEntity)
    {
        this.instance.putAll(msgEntity);
    }

    public void put(String key, Object obj)
    {
        this.instance.put(key, obj);
    }

    public Object getInstance(String refrence)
    {
        if (this.mDexClassLoader == null) {
            return null;
        }
        try
        {
            Class<?> aClass = this.mDexClassLoader.loadClass(refrence);
            return aClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void startActivity(Context context, String plugin_id, String activity_id)
    {
        startActivity(context, plugin_id, activity_id, AdProxyActivity.class, null);
    }

    public void startActivity(Context context, String plugin_id, String activity_id, Class<? extends Activity> proxyActivityClass)
    {
        startActivity(context, plugin_id, activity_id, proxyActivityClass, null);
    }

    public void startActivity(Context context, String plugin_id, String activity_id, PluginDataListener pluginDataListener)
    {
        startActivity(context, plugin_id, activity_id, AdProxyActivity.class, pluginDataListener);
    }

    public void startActivity(Context context, String plugin_id, String activity_id, Class<? extends Activity> proxyActivityClass, PluginDataListener pluginDataListener)
    {
        excute(new MsgEntity()
                .addCmd("type", "start_activity")
                .addCmd("activity_id", activity_id)
                .addCmd("plugin_id", plugin_id)
                .addCmd("context", context)
                .addCmd("proxy_activity_class", proxyActivityClass)
                .addPluginDataListener(pluginDataListener));
    }

    public void startService(Context context, String plugin_id, String service_id)
    {
        startService(context, plugin_id, service_id, AdProxyService.class);
    }

    public void startService(Context context, String plugin_id, String service_id, Class<? extends Service> proxyServiceClass)
    {
        startService(context, plugin_id, service_id, proxyServiceClass, null);
    }

    public void startService(Context context, String plugin_id, String service_id, PluginDataListener pluginDataListener)
    {
        startService(context, plugin_id, service_id, AdProxyService.class, pluginDataListener);
    }

    public void startService(Context context, String plugin_id, String service_id, Class<? extends Service> proxyServiceClass, PluginDataListener pluginDataListener)
    {
        excute(new MsgEntity()
                .addCmd("type", "start_service")
                .addCmd("service_id", service_id)
                .addCmd("plugin_id", plugin_id)
                .addCmd("context", context)
                .addCmd("proxy_service_class", proxyServiceClass)
                .addPluginDataListener(pluginDataListener));
    }
}

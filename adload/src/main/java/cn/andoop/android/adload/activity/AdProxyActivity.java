package cn.andoop.android.adload.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

import cn.andoop.android.adload.AdPlugin;
import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.PluginDataListener;
import cn.andoop.android.adload.manager.PluginRepertory;

public class AdProxyActivity
        extends FragmentActivity
        implements PluginDataListener
{
    private AdPlugin mPlugin;
    private String currentPluginActivityId;

    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            this.mPlugin = PluginRepertory.newInstance().getAdPlugin(extras.getString("plugin_id"));
            this.currentPluginActivityId = extras.getString("activity_id");
            if (this.mPlugin == null) {
                Log.e("----->APActivity", "onCreate:err: mPlugin==null");
            }
        }
        else
        {
            Log.e("----->APActivity", "onCreate:err: extras==null");
            return;
        }
        this.mPlugin.excute(new MsgEntity()

                .addCmd("type", "activity_life_circle")
                .addCmd("proxy_activity", this)
                .addCmd("activity_id", this.currentPluginActivityId)
                .addCmd("method", "onCreate")
                .addCmd("parameters", savedInstanceState)
                .addPluginDataListener(this));
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void onCallBack(Map<? extends String, ?> m)
    {
        Log.e("----->APActivity", "onCallBack:");
    }

    protected void onStart()
    {
        super.onStart();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onStart"));
        }
    }

    protected void onRestart()
    {
        super.onRestart();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onRestart"));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.mPlugin != null)
        {
            Map<String, Object> paramters = new HashMap();
            paramters.put("requestCode", Integer.valueOf(requestCode));
            paramters.put("resultCode", Integer.valueOf(resultCode));
            paramters.put("data", data);
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onActivityResult")
                    .addCmd("parameters", paramters));
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onResume"));
        }
    }

    protected void onPause()
    {
        super.onPause();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onPause"));
        }
    }

    protected void onStop()
    {
        super.onStop();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onStop"));
        }
    }

    protected void onDestroy()
    {
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onDestroy"));
        }
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onSaveInstanceState")
                    .addCmd("parameters", outState));
        }
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onNewIntent")
                    .addCmd("parameters", intent));
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onRestoreInstanceState")
                    .addCmd("parameters", savedInstanceState));
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        final boolean[] result = { super.onTouchEvent(event) };
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onTouchEvent")
                    .addCmd("parameters", event)
                    .addPluginDataListener(new PluginDataListener()
                    {
                        public void onCallBack(Map<? extends String, ?> m)
                        {
                            Boolean data = (Boolean)m.get("data");
                            if (data.booleanValue()) {
                                result[0] = true;
                            }
                        }
                    }));
        }
        return result[0];
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        final boolean[] result = { super.onKeyUp(keyCode, event) };
        if (this.mPlugin != null)
        {
            Map<String, Object> paramters = new HashMap();
            paramters.put("keyCode", Integer.valueOf(keyCode));
            paramters.put("event", event);
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onKeyUp")
                    .addCmd("parameters", paramters)
                    .addPluginDataListener(new PluginDataListener()
                    {
                        public void onCallBack(Map<? extends String, ?> m)
                        {
                            Boolean data = (Boolean)m.get("data");
                            if (data.booleanValue()) {
                                result[0] = true;
                            }
                        }
                    }));
        }
        return result[0];
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        final boolean[] result = { super.onKeyUp(keyCode, event) };
        if (this.mPlugin != null)
        {
            Map<String, Object> paramters = new HashMap();
            paramters.put("keyCode", Integer.valueOf(keyCode));
            paramters.put("event", event);
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onKeyDown")
                    .addCmd("parameters", paramters)
                    .addPluginDataListener(new PluginDataListener()
                    {
                        public void onCallBack(Map<? extends String, ?> m)
                        {
                            Boolean data = (Boolean)m.get("data");
                            if (data.booleanValue()) {
                                result[0] = true;
                            }
                        }
                    }));
        }
        return result[0];
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams params)
    {
        super.onWindowAttributesChanged(params);
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onWindowAttributesChanged")
                    .addCmd("parameters", params));
        }
    }

    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onWindowFocusChanged")
                    .addCmd("parameters", Boolean.valueOf(hasFocus)));
        }
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onBackPressed"));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        final boolean[] result = { super.onCreateOptionsMenu(menu) };
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onCreateOptionsMenu")
                    .addCmd("parameters", menu)
                    .addPluginDataListener(new PluginDataListener()
                    {
                        public void onCallBack(Map<? extends String, ?> m)
                        {
                            Boolean data = (Boolean)m.get("data");
                            if (data.booleanValue()) {
                                result[0] = true;
                            }
                        }
                    }));
        }
        return result[0];
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        final boolean[] result = { super.onOptionsItemSelected(item) };
        if (this.mPlugin != null) {
            this.mPlugin.excute(new MsgEntity()

                    .addCmd("type", "activity_life_circle")
                    .addCmd("proxy_activity", this)
                    .addCmd("activity_id", this.currentPluginActivityId)
                    .addCmd("method", "onOptionsItemSelected")
                    .addCmd("parameters", item)
                    .addPluginDataListener(new PluginDataListener()
                    {
                        public void onCallBack(Map<? extends String, ?> m)
                        {
                            Boolean data = (Boolean)m.get("data");
                            if (data.booleanValue()) {
                                result[0] = true;
                            }
                        }
                    }));
        }
        return result[0];
    }
}

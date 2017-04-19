package cn.andoop.android.adload.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Map;

import cn.andoop.android.adload.AdPlugin;
import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.PluginDataListener;
import cn.andoop.android.adload.manager.PluginRepertory;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/10
* explain：
* * * * * * * * * * * * * * * * * * */

public class AdProxyActivity extends FragmentActivity implements PluginDataListener {
    //插件中activity
    private AdPlugin mPlugin;
    //当前正在代理的插件activity的id
    private String currentPluginActivityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取插件activity
        Bundle extras = getIntent().getBundleExtra("data");
        if (extras != null) {
            mPlugin = PluginRepertory.newInstance().getAdPlugin(extras.getString("plugin_id"));
            currentPluginActivityId = extras.getString("activity_id");
            Log.e("----->" + "AdProxyActivity", "onCreate:被代理的插件activity: " + currentPluginActivityId);
            if (mPlugin == null) {
                Log.e("----->" + "AdProxyActivity", "onCreate:" + "err: mPlugin==null");
                return;
            }
        } else {
            Log.e("----->" + "AdProxyActivity", "onCreate:" + "err: extras==null");
            return;
        }

        //执行代理方法，传入代理Activity对象
        mPlugin.excute(
                new MsgEntity()
                        .addCmd("type", "activity_life_circle")
                        .addCmd("proxy_activity", this)
                        .addCmd("activity_id", currentPluginActivityId)
                        .addCmd("method", "onCreate")
                        .addCmd("parameters", savedInstanceState)
                        .addPluginDataListener(this)
        );
    }
    //插件将回调此方法
    @Override
    public void onCallBack(Map<? extends String, ?> m) {
        Log.e("----->" + "AdProxyActivity", "onCallBack:");

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlugin != null) {
            mPlugin.excute(
                    new MsgEntity()
                            .addCmd("type", "activity_life_circle")
                            .addCmd("proxy_activity", this)
                            .addCmd("activity_id", currentPluginActivityId)
                            .addCmd("method", "onResume")
            );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlugin != null) {
            mPlugin.excute(
                    new MsgEntity()
                            .addCmd("type", "activity_life_circle")
                            .addCmd("proxy_activity", this)
                            .addCmd("activity_id", currentPluginActivityId)
                            .addCmd("method", "onPause")
            );
        }
    }

    @Override
    protected void onDestroy() {
        if (mPlugin != null) {
            mPlugin.excute(
                    new MsgEntity()
                            .addCmd("type", "activity_life_circle")
                            .addCmd("proxy_activity", this)
                            .addCmd("activity_id", currentPluginActivityId)
                            .addCmd("method", "onDestroy")
            );
        }
        super.onDestroy();
    }

   
}

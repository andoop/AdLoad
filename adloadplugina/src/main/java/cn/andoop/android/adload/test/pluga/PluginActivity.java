package cn.andoop.android.adload.test.pluga;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.andoop.android.adloadcomponents.activity.FakeActivity;


/**
 * Created by domob on 2017/4/17.
 */

public class PluginActivity extends FakeActivity {
    private FragmentActivity mProxy;
    private Plug mPlug;
    public PluginActivity(Plug plug) {
        this.mPlug=plug;
    }

    @Override
    public void bindProxy(Activity proxy) {
        super.bindProxy(proxy);
        mProxy= (FragmentActivity) proxy;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy.setContentView(initView());
    }

    private View initView() {
        TextView tv=new TextView(mProxy);
        tv.setTextColor(Color.parseColor("#ff0000"));
        tv.setText("sssssssssssssssssssssssssssssss");
        return tv;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("----->" + "PluginActivity", "onResume:");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("----->" + "PluginActivity", "onPause:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("----->" + "PluginActivity", "onDestroy:");
    }
}

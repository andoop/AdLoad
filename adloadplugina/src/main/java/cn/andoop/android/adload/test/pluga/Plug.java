package cn.andoop.android.adload.test.pluga;

import android.content.Context;
import android.util.Log;

import java.util.Map;

import cn.andoop.android.adloadcomponents.DataEntity;
import cn.andoop.android.adloadcomponents.Plugin;
import cn.andoop.android.adloadcomponents.activity.ActivityWrap;
import cn.andoop.android.adloadcomponents.service.ServiceWrap;


/**
 * Created by andoop on 2017/3/9.
 */

public class Plug extends Plugin{
    @Override
    protected void registComponents(Map<String, ActivityWrap> activityWrapMap, Map<String, ServiceWrap> serviceWrapMap) {
        Log.e("----->" + "Plug", "registComponents:");
        activityWrapMap.put("test1_activity",new ActivityWrap(new PluginActivity(this)));
        activityWrapMap.put("test2_activity",new ActivityWrap(new Plugin2Activity(this)));
    }
    @Override
    protected void onInit(Context context, DataEntity dataEntity) {
        Log.e("----->" + "Plug", "onInit:");
        setId("002");

    }
    @Override
    protected boolean onReceivedCmds(DataEntity dataEntity) {
        Log.e("----->" + "Plug", "onReceivedCmds:");
        return super.onReceivedCmds(dataEntity);
    }

    @Override
    protected boolean onStartActivity(Context context, DataEntity dataEntity) {
        Log.e("----->" + "Plug", "onStartActivity:");
        return super.onStartActivity(context, dataEntity);
    }
}

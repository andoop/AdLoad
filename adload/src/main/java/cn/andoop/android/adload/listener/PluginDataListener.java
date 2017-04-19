package cn.andoop.android.adload.listener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/9
* explain：插件数据回调
* * * * * * * * * * * * * * * * * * */

import java.util.Map;

public interface PluginDataListener {

    void onCallBack(Map<? extends String, ?> m);
}

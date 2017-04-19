package cn.andoop.android.adload.listener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：插件加载监听
* * * * * * * * * * * * * * * * * * */

import cn.andoop.android.adload.AdPlugin;

public interface PluginInitListener extends DownLoadListener{
    void update(boolean update);
    void initFail(String msg);
    void initSuccess(AdPlugin adPlugin);
}

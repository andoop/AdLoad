package cn.andoop.android.adload.listener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：下载监听
* * * * * * * * * * * * * * * * * * */

import cn.andoop.android.adload.Plugin;

public interface DownLoadListener {
    void dlStart(Plugin plugin);
    void dlIng(Plugin plugin,int percent);
    void dlCompleted(Plugin plugin,String path);
    void dlFail(Plugin plugin,String msg);

}

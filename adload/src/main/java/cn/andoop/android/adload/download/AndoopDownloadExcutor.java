package cn.andoop.android.adload.download;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/9
* explain：下载线程执行者
* * * * * * * * * * * * * * * * * * */

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndoopDownloadExcutor {


    private final ExecutorService executorService;

    public AndoopDownloadExcutor(){
        executorService = Executors.newCachedThreadPool();
    }

    public void excuteDownLoad(AndoopDownloadThread andoopDownloadThread){
        Log.e("----->" + "DownloadExcutor", "excuteDownLoad:");
        executorService.execute(andoopDownloadThread);
    }
}

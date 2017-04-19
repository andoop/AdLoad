package cn.andoop.android.adload.download;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/9
* explain：下载线程
* * * * * * * * * * * * * * * * * * */
public class AndoopDownloadThread extends Thread{

    private  String url;
    private  String md5;
    private  String filename;
    private  String savepath;
    private  AndoopDownloadListener andoopDownloadlistener;
    private  String suffix;
    private  Context context;
    private String realUrl;


    public AndoopDownloadThread(Context context,String url, String md5, String suffix, String filename, String savepath, AndoopDownloadListener andoopDownloadListener){
        this.context=context;
        this.url=url;
        this.realUrl=url;
        this.md5=md5;
        this.filename=AndoopDownloadUtils.getFileName(url,suffix,filename);
        this.suffix=suffix;
        this.savepath=AndoopDownloadUtils.getSavePath(context,savepath);
        this.andoopDownloadlistener=andoopDownloadListener;


    }
    @Override
    public void run() {
        //清除本地文件
        File oldfile = new File(savepath, filename);
        if(oldfile.exists()){
            oldfile.delete();
        }
        if(url.startsWith("http")||url.startsWith("https")){
            downloadFromNet();
        }else if(url.startsWith("file:///android_asset")){
            downloadFromAsset();
        }
    }

    private void downloadFromAsset() {
        InputStream inputStream=null;
        try {
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onStart(url,url);
            }

            inputStream = context.getAssets().open(filename);
            writeToFile(inputStream,inputStream.available());
        } catch (Exception e) {
            e.printStackTrace();
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onFailure(e.toString());
            }
        }finally {
            try {
                closeStream(inputStream,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadFromNet(){
        HttpURLConnection connection=null;
        InputStream inputStream=null;
        try {
            connection= (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();
           realUrl = connection.getURL().toString();
            if(!url.equals(realUrl.toString())){
                //302跳转处理
                this.filename=AndoopDownloadUtils.getFileName(realUrl.toString(),suffix,filename);
            }
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onStart(url,realUrl);
            }
            inputStream = connection.getInputStream();
            int contentLength = connection.getContentLength();
            writeToFile(inputStream,contentLength);
        } catch (Exception e) {
            e.printStackTrace();
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onFailure(e.toString());
            }
        }finally {
            try {
                closeStream(inputStream,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //将流写入文件
    private void writeToFile(InputStream inputStream,int count) throws Exception {
        byte[] bytes=new byte[1024];
        File dexfile=new File(savepath, filename);
        FileOutputStream fileOutputStream = new FileOutputStream(dexfile);
        int num=0;
        int current=0;
        while ((num=inputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,num);
            current+=num;
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onLoading(current,count);
            }
        }

        //md5检查
        if(!TextUtils.isEmpty(md5)){
            Log.e("----->" + "DownloadThread", "writeToFile:" + md5 + " : " + AndoopDownloadUtils.getFileMD5String(dexfile));
            if(md5.equals(AndoopDownloadUtils.getFileMD5String(dexfile))){
                if(andoopDownloadlistener!=null){
                    andoopDownloadlistener.onSuccess(url,realUrl,"",dexfile);
                }
            }else {
                if(andoopDownloadlistener!=null){
                    andoopDownloadlistener.onFailure("文件校验不通过！");
                }
            }
        }else {
            if(andoopDownloadlistener!=null){
                andoopDownloadlistener.onSuccess(url,realUrl,"",dexfile);
            }
        }

        closeStream(inputStream,fileOutputStream);
    }

    private void closeStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        if(inputStream!=null){
            inputStream.close();
            inputStream=null;
        }
        if(outputStream!=null){
            outputStream.close();
            outputStream=null;
        }
    }

}

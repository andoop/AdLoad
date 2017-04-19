package cn.andoop.android.adloadcomponents.activity;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/4/17
* explain：activity页面封装类
* * * * * * * * * * * * * * * * * * */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;


public class ActivityWrap extends HashMap<String,Object>{
    private IActivity mIActivity;
    public  ActivityWrap(IActivity iActivity){
        this.mIActivity=iActivity;
        Log.e("----->" + "ActivityWrap", "ActivityWrap:"+mIActivity);
    }
    //@Override
//    public Object put(String key, Object value) {
//        Log.e("----->" + "ActivityWrap", "put:" + key);
//        //解析命令参数
//        if("onCreate".equals(key)){
//            //onCreate方法
//            Bundle savedInstanceState= (Bundle) value;
//            mIActivity.onCreate(savedInstanceState);
//        }else if("onStart".equals(key)){
//            mIActivity.onStart();
//        }else if("onRestart".equals(key)){
//            mIActivity.onRestart();
//        }else if("onActivityResult".equals(key)){
//            HashMap<String,Object> map= (HashMap<String, Object>) value;
//            int requestCode= (int) map.get("requestCode");
//            int resultCode= (int) map.get("resultCode");
//            Intent data= (Intent) map.get("data");
//            mIActivity.onActivityResult(requestCode,resultCode,data);
//        }else if("onResume".equals(key)){
//            mIActivity.onResume();
//        }else if("onPause".equals(key)){
//            mIActivity.onPause();
//        }else if("onStop".equals(key)){
//            mIActivity.onStop();
//        }else if("onDestroy".equals(key)){
//            mIActivity.onDestroy();
//        }else if("onSaveInstanceState".equals(key)){
//            Bundle outState= (Bundle) value;
//            mIActivity.onSaveInstanceState(outState);
//        }else if("onNewIntent".equals(key)){
//            Intent intent= (Intent) value;
//            mIActivity.onNewIntent(intent);
//        }else if("onRestoreInstanceState".equals(key)){
//            Bundle savedInstanceState= (Bundle) value;
//            mIActivity.onRestoreInstanceState(savedInstanceState);
//        }else if("onTouchEvent".equals(key)){
//            MotionEvent event= (MotionEvent) value;
//            mIActivity.onTouchEvent(event);
//        }else if("onKeyUp".equals(key)){
//            HashMap<String,Object> map= (HashMap<String, Object>) value;
//            int keyCode= (int) map.get("keyCode");
//            KeyEvent event= (KeyEvent) map.get("event");
//            mIActivity.onKeyUp(keyCode,event);
//        }else if("onWindowAttributesChanged".equals(key)){
//            WindowManager.LayoutParams params= (WindowManager.LayoutParams) value;
//            mIActivity.onWindowAttributesChanged(params);
//        }else if("onWindowFocusChanged".equals(key)){
//            boolean hasFocus= (boolean) value;
//            mIActivity.onWindowFocusChanged(hasFocus);
//        }else if("onBackPressed".equals(key)){
//            mIActivity.onBackPressed();
//        }else if("onCreateOptionsMenu".equals(key)){
//            Menu menu= (Menu) value;
//            mIActivity.onCreateOptionsMenu(menu);
//        }else if("onOptionsItemSelected".equals(key)){
//            MenuItem item= (MenuItem) value;
//            mIActivity.onOptionsItemSelected(item);
//        }
//        return value;
//
//    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        Log.e("----->" + "ActivityWrap", "putAll:" );
        String method=m.get("method").toString();
        Object value=m.get("parameters");
        //解析命令参数
        if("onCreate".equals(method)){
            Activity proxy= (Activity) m.get("proxy_activity");
            mIActivity.bindProxy(proxy);
            //onCreate方法
            Bundle savedInstanceState= (Bundle) value;
            mIActivity.onCreate(savedInstanceState);
        }else if("onStart".equals(method)){
            mIActivity.onStart();
        }else if("onRestart".equals(method)){
            mIActivity.onRestart();
        }else if("onActivityResult".equals(method)){
            HashMap<String,Object> map= (HashMap<String, Object>) value;
            int requestCode= (int) map.get("requestCode");
            int resultCode= (int) map.get("resultCode");
            Intent data= (Intent) map.get("data");
            mIActivity.onActivityResult(requestCode,resultCode,data);
        }else if("onResume".equals(method)){
            mIActivity.onResume();
        }else if("onPause".equals(method)){
            mIActivity.onPause();
        }else if("onStop".equals(method)){
            mIActivity.onStop();
        }else if("onDestroy".equals(method)){
            mIActivity.onDestroy();
        }else if("onSaveInstanceState".equals(method)){
            Bundle outState= (Bundle) value;
            mIActivity.onSaveInstanceState(outState);
        }else if("onNewIntent".equals(method)){
            Intent intent= (Intent) value;
            mIActivity.onNewIntent(intent);
        }else if("onRestoreInstanceState".equals(method)){
            Bundle savedInstanceState= (Bundle) value;
            mIActivity.onRestoreInstanceState(savedInstanceState);
        }else if("onTouchEvent".equals(method)){
            MotionEvent event= (MotionEvent) value;
            mIActivity.onTouchEvent(event);
        }else if("onmethodUp".equals(method)){
            HashMap<String,Object> map= (HashMap<String, Object>) value;
            int keyCode= (int) map.get("keyCode");
            KeyEvent event= (KeyEvent) map.get("event");
            mIActivity.onKeyUp(keyCode,event);
        }else if("onWindowAttributesChanged".equals(method)){
            WindowManager.LayoutParams params= (WindowManager.LayoutParams) value;
            mIActivity.onWindowAttributesChanged(params);
        }else if("onWindowFocusChanged".equals(method)){
            boolean hasFocus= (boolean) value;
            mIActivity.onWindowFocusChanged(hasFocus);
        }else if("onBackPressed".equals(method)){
            mIActivity.onBackPressed();
        }else if("onCreateOptionsMenu".equals(method)){
            Menu menu= (Menu) value;
            mIActivity.onCreateOptionsMenu(menu);
        }else if("onOptionsItemSelected".equals(method)){
            MenuItem item= (MenuItem) value;
            mIActivity.onOptionsItemSelected(item);
        }
    }
}

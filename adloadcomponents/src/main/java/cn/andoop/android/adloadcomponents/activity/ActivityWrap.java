package cn.andoop.android.adloadcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

public class ActivityWrap
{
    private IActivity mIActivity;

    public ActivityWrap(IActivity iActivity)
    {
        this.mIActivity = iActivity;
    }

    public void putAll(Map<String, Object> m)
    {
        String method = m.get("method").toString();
        Object value = m.get("parameters");
        if ("onCreate".equals(method))
        {
            Activity proxy = (Activity)m.get("proxy_activity");
            this.mIActivity.bindProxy(proxy);

            Bundle savedInstanceState = (Bundle)value;
            this.mIActivity.onCreate(savedInstanceState);
        }
        else if ("onStart".equals(method))
        {
            this.mIActivity.onStart();
        }
        else if ("onRestart".equals(method))
        {
            this.mIActivity.onRestart();
        }
        else if ("onActivityResult".equals(method))
        {
            HashMap<String, Object> map = (HashMap)value;
            int requestCode = ((Integer)map.get("requestCode")).intValue();
            int resultCode = ((Integer)map.get("resultCode")).intValue();
            Intent data = (Intent)map.get("data");
            this.mIActivity.onActivityResult(requestCode, resultCode, data);
        }
        else if ("onResume".equals(method))
        {
            this.mIActivity.onResume();
        }
        else if ("onPause".equals(method))
        {
            this.mIActivity.onPause();
        }
        else if ("onStop".equals(method))
        {
            this.mIActivity.onStop();
        }
        else if ("onDestroy".equals(method))
        {
            this.mIActivity.onDestroy();
        }
        else if ("onSaveInstanceState".equals(method))
        {
            Bundle outState = (Bundle)value;
            this.mIActivity.onSaveInstanceState(outState);
        }
        else if ("onNewIntent".equals(method))
        {
            Intent intent = (Intent)value;
            this.mIActivity.onNewIntent(intent);
        }
        else if ("onRestoreInstanceState".equals(method))
        {
            Bundle savedInstanceState = (Bundle)value;
            this.mIActivity.onRestoreInstanceState(savedInstanceState);
        }
        else if ("onTouchEvent".equals(method))
        {
            MotionEvent event = (MotionEvent)value;
            boolean result = this.mIActivity.onTouchEvent(event);
            Map<String, Object> resultData = new HashMap();
            resultData.put("data", Boolean.valueOf(result));
            m.putAll(resultData);
        }
        else if ("onKeyUp".equals(method))
        {
            HashMap<String, Object> map = (HashMap)value;
            int keyCode = ((Integer)map.get("keyCode")).intValue();
            KeyEvent event = (KeyEvent)map.get("event");
            boolean result = this.mIActivity.onKeyUp(keyCode, event);
            Map<String, Object> resultData = new HashMap();
            resultData.put("data", Boolean.valueOf(result));
            m.putAll(resultData);
        }
        else if ("onKeyDown".equals(method))
        {
            HashMap<String, Object> map = (HashMap)value;
            int keyCode = ((Integer)map.get("keyCode")).intValue();
            KeyEvent event = (KeyEvent)map.get("event");
            boolean result = this.mIActivity.onKeyDown(keyCode, event);
            Map<String, Object> resultData = new HashMap();
            resultData.put("data", Boolean.valueOf(result));
            m.putAll(resultData);
        }
        else if ("onWindowAttributesChanged".equals(method))
        {
            WindowManager.LayoutParams params = (WindowManager.LayoutParams)value;
            this.mIActivity.onWindowAttributesChanged(params);
        }
        else if ("onWindowFocusChanged".equals(method))
        {
            boolean hasFocus = ((Boolean)value).booleanValue();
            this.mIActivity.onWindowFocusChanged(hasFocus);
        }
        else if ("onBackPressed".equals(method))
        {
            this.mIActivity.onBackPressed();
        }
        else if ("onCreateOptionsMenu".equals(method))
        {
            Menu menu = (Menu)value;
            boolean result = this.mIActivity.onCreateOptionsMenu(menu);
            Map<String, Object> resultData = new HashMap();
            resultData.put("data", Boolean.valueOf(result));
            m.putAll(resultData);
        }
        else if ("onOptionsItemSelected".equals(method))
        {
            MenuItem item = (MenuItem)value;
            boolean result = this.mIActivity.onOptionsItemSelected(item);
            Map<String, Object> resultData = new HashMap();
            resultData.put("data", Boolean.valueOf(result));
            m.putAll(resultData);
        }
    }
}

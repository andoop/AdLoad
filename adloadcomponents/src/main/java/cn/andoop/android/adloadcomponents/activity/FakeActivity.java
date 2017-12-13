package cn.andoop.android.adloadcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import java.io.Serializable;

public class FakeActivity
        extends FragmentActivity
        implements IActivity, Serializable
{
    protected Activity that;

    public void bindProxy(Activity proxy)
    {
        this.that = proxy;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        if (this.that == null)
        {
            super.onCreate(savedInstanceState);
            this.that = this;
        }
    }

    public void onStart()
    {
        if (this.that == this) {
            super.onStart();
        }
    }

    public void onRestart()
    {
        if (this.that == this) {
            super.onRestart();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (this.that == this) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onResume()
    {
        if (this.that == this) {
            super.onResume();
        }
    }

    public void onPause()
    {
        if (this.that == this) {
            super.onPause();
        }
    }

    public void onStop()
    {
        if (this.that == this) {
            super.onStop();
        }
    }

    public void onDestroy()
    {
        if (this.that == this) {
            super.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle outState)
    {
        if (this.that == this) {
            super.onSaveInstanceState(outState);
        }
    }

    public void onNewIntent(Intent intent)
    {
        if (this.that == this) {
            super.onNewIntent(intent);
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        if (this.that == this) {
            super.onRestoreInstanceState(savedInstanceState);
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if (this.that == this) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        if (this.that == this) {
            return super.onKeyUp(keyCode, event);
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (this.that == this) {
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams params)
    {
        if (this.that == this) {
            super.onWindowAttributesChanged(params);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus)
    {
        if (this.that == this) {
            super.onWindowFocusChanged(hasFocus);
        }
    }

    public void onBackPressed()
    {
        if (this.that == this) {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (this.that == this) {
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (this.that == this) {
            return super.onOptionsItemSelected(item);
        }
        return false;
    }

}

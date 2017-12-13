package cn.andoop.android.adloadcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

public abstract interface IActivity
{
    public abstract void onCreate(Bundle paramBundle);

    public abstract void onStart();

    public abstract void onRestart();

    public abstract void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();

    public abstract void onDestroy();

    public abstract void onSaveInstanceState(Bundle paramBundle);

    public abstract void onNewIntent(Intent paramIntent);

    public abstract void onRestoreInstanceState(Bundle paramBundle);

    public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);

    public abstract boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent);

    public abstract boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent);

    public abstract void onWindowAttributesChanged(WindowManager.LayoutParams paramLayoutParams);

    public abstract void onWindowFocusChanged(boolean paramBoolean);

    public abstract void onBackPressed();

    public abstract boolean onCreateOptionsMenu(Menu paramMenu);

    public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);

    public abstract void bindProxy(Activity paramActivity);
}

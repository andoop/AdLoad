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

import java.io.Serializable;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/4/17
* explain：假activity，模拟activity功能，实现了IActivity
* 插件中有activity功能的类，可以继承此方法，选择重载的方法即可
* * * * * * * * * * * * * * * * * * */
public class FakeActivity extends FragmentActivity implements IActivity,Serializable{

    @Override
    public void bindProxy(Activity proxy) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


}

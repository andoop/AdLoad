package cn.andoop.android.adloadcomponents.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

public abstract interface IService
{
  public abstract void bindProxy(Service paramService);
  
  public abstract void onCreate();
  
  public abstract void onStart(Intent paramIntent, int paramInt);
  
  public abstract int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2);
  
  public abstract void onDestroy();
  
  public abstract void onConfigurationChanged(Configuration paramConfiguration);
  
  public abstract void onLowMemory();
  
  public abstract void onTrimMemory(int paramInt);
  
  public abstract IBinder onBind(Intent paramIntent);
  
  public abstract boolean onUnbind(Intent paramIntent);
  
  public abstract void onRebind(Intent paramIntent);
  
  public abstract void onTaskRemoved(Intent paramIntent);
}

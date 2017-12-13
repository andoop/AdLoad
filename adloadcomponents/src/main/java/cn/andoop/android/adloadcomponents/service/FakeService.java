package cn.andoop.android.adloadcomponents.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.io.Serializable;

public class FakeService
  extends Service
  implements IService, Serializable
{
  protected Service that;
  
  public void bindProxy(Service proxy)
  {
    this.that = proxy;
  }
  
  public void onCreate()
  {
    if (this.that == null)
    {
      super.onCreate();
      this.that = this;
    }
  }
  
  @Nullable
  public IBinder onBind(Intent intent)
  {
    if (this.that == null) {
      return null;
    }
    return null;
  }
  
  public boolean onUnbind(Intent intent)
  {
    if (this.that == this) {
      return super.onUnbind(intent);
    }
    return true;
  }
  
  public void onRebind(Intent intent)
  {
    if (this.that == this) {
      super.onRebind(intent);
    }
  }
  
  public int onStartCommand(Intent intent, int flags, int startId)
  {
    if (this.that == this) {
      return super.onStartCommand(intent, flags, startId);
    }
    return 0;
  }
  
  public void onStart(Intent intent, int startId)
  {
    if (this.that == this) {
      super.onStart(intent, startId);
    }
  }
  
  public void onConfigurationChanged(Configuration newConfig)
  {
    if (this.that == this) {
      super.onConfigurationChanged(newConfig);
    }
  }
  
  public void onLowMemory()
  {
    if (this.that == this) {
      super.onLowMemory();
    }
  }
  
  public void onTrimMemory(int level)
  {
    if (this.that == this) {
      super.onTrimMemory(level);
    }
  }
  
  public void onTaskRemoved(Intent rootIntent)
  {
    if (this.that == this) {
      super.onTaskRemoved(rootIntent);
    }
  }
  
  public void onDestroy()
  {
    if (this.that == this) {
      super.onDestroy();
    }
  }
}

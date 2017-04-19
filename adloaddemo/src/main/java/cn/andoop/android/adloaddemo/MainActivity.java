package cn.andoop.android.adloaddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.andoop.android.adload.AdPlugin;
import cn.andoop.android.adload.Plugin;
import cn.andoop.android.adload.activity.AdProxyActivity;
import cn.andoop.android.adload.entity.MsgEntity;
import cn.andoop.android.adload.listener.PluginDataListener;
import cn.andoop.android.adload.listener.PluginInitListener;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.anim_btn1)
    AnimDownloadProgressButton animDownloadProgressButton_dvx;
    @InjectView(R.id.anim_btn2)
    AnimDownloadProgressButton animDownloadProgressButton_offerwall;
    @InjectView(R.id.ll_video)
    LinearLayout ll_video;

    private AdPlugin pluginVedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        animDownloadProgressButton_dvx.setCurrentText("启用视频广告插件");
        animDownloadProgressButton_offerwall.setCurrentText("启用积分墙广告插件");

        animDownloadProgressButton_dvx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDvxVedio();
            }
        });
        animDownloadProgressButton_offerwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initOfferWall();
            }
        });


    }

    private void initOfferWall() {

    }

    private void initDvxVedio() {

        final Plugin parse = new Plugin("002",30, "file:///android_asset/dex/test.jar", "", "cn.andoop.android.adload.test.pluga.Plug", "test.jar");
        if (parse == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            return;
        }
        parse.init(this, new PluginInitListener() {
            @Override
            public void update(boolean update) {
                Log.e("----->" + "MainActivity", "update:" + update);
            }

            @Override
            public void initFail(String msg) {
                Log.e("----->" + "MainActivity", "initFail:" + msg);
            }

            @Override
            public void initSuccess(final AdPlugin adPlugin) {
                pluginVedio = adPlugin;
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animDownloadProgressButton_dvx.setCurrentText("初始化完成");
                        ll_video.setVisibility(View.VISIBLE);
                    }
                });

            }

            @Override
            public void dlStart(Plugin plugin) {
                Log.e("----->" + "MainActivity", "dlStart:" + plugin.getUrl());
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animDownloadProgressButton_dvx.setState(AnimDownloadProgressButton.DOWNLOADING);
                    }
                });

            }

            @Override
            public void dlIng(Plugin plugin, final int percent) {
                Log.e("----->" + "MainActivity", "dlIng:" + percent);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animDownloadProgressButton_dvx.setProgress(percent);
                    }
                });
            }

            @Override
            public void dlCompleted(Plugin plugin, String path) {
                Log.e("----->" + "MainActivity", "dlCompleted:" + path);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animDownloadProgressButton_dvx.setState(AnimDownloadProgressButton.NORMAL);
                        animDownloadProgressButton_dvx.setCurrentText("下载完成");
                    }
                });
            }

            @Override
            public void dlFail(Plugin plugin, String msg) {
                Log.e("----->" + "MainActivity", "dlFail:" + msg);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animDownloadProgressButton_dvx.setCurrentText("初始化失败");
                        ll_video.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    public void openVideoPage01(View view) {
        pluginVedio.excute(new MsgEntity()
                .addCmd("type", "start_activity")
                .addCmd("activity_id", "test1_activity")
                .addCmd("plugin_id", pluginVedio.getId())
                .addCmd("context", MainActivity.this)
                .addCmd("proxy_activity_class", AdProxyActivity.class)
                .addPluginDataListener(new PluginDataListener() {
                    @Override
                    public void onCallBack(Map<? extends String, ?> m) {
                        Log.e("----->" + "MainActivity", "onCallBack:" + m.get("data"));
                    }
                })
        );
    }

    public void openVideoPage02(View view) {
        pluginVedio.excute(new MsgEntity()
                .addCmd("type", "start_activity")
                .addCmd("activity_id", "test2_activity")
                .addCmd("plugin_id", pluginVedio.getId())
                .addCmd("context", MainActivity.this)
                .addCmd("proxy_activity_class", AdProxyActivity.class)
                .addPluginDataListener(new PluginDataListener() {
                    @Override
                    public void onCallBack(Map<? extends String, ?> m) {
                        Log.e("----->" + "MainActivity", "onCallBack:" + m.get("data"));
                    }
                })
        );
    }
}

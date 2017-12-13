package cn.andoop.android.adload.test.pluga;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.andoop.android.adloadcomponents.activity.FakeActivity;


/**
 * Created by domob on 2017/4/17.
 */

public class Plugin2Activity extends FakeActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(initView());
    }

    private View initView() {
        LinearLayout linearLayout = new LinearLayout(that);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        TextView tv=new TextView(that);
        tv.setTextColor(Color.parseColor("#ff0000"));
        tv.setText("bbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        linearLayout.addView(tv);


        Button button = new Button(that);
        button.setText("跳转插件中页面01");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mPlug.startActivity(that,that.getClass(),mPlug.getId(),"test1_activity",null);
            }
        });
        linearLayout.addView(button);

        return linearLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("----->" + "PluginActivity", "onResume:");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("----->" + "PluginActivity", "onPause:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("----->" + "PluginActivity", "onDestroy:");
    }
}

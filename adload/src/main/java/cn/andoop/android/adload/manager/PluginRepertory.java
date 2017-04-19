package cn.andoop.android.adload.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.andoop.android.adload.AdPlugin;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/4/18
* explain：插件仓库
* * * * * * * * * * * * * * * * * * */

public class PluginRepertory {
    private static PluginRepertory INSTANCE;
    private Map<String,AdPlugin> adPlugins;

    private PluginRepertory() {
        this.adPlugins=new HashMap<>();
    }

    public static PluginRepertory newInstance() {

        if (INSTANCE == null) {
            synchronized (PluginRepertory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PluginRepertory();
                }
            }

        }
        return INSTANCE;
    }

    //添加插件
    public void addAdPlugin(String id,AdPlugin adPlugin){
        adPlugins.put(id,adPlugin);
    }
    public void removeAdPlugin(String id){
        adPlugins.remove(id);
    }
    public AdPlugin getAdPlugin(String id){
        return adPlugins.get(id);
    }
    public void clear(){
        adPlugins.clear();
    }

}

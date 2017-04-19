package cn.andoop.android.adload.entity;

import java.util.HashMap;
import java.util.Map;

import cn.andoop.android.adload.listener.PluginDataListener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/9
* explain：消息体
* * * * * * * * * * * * * * * * * * */
public class MsgEntity extends HashMap<String,Object>{

    private PluginDataListener pluginDataListener;

    public MsgEntity addCmd(String cmd_name, Object cmd){
        put(cmd_name,cmd);
        return this;
    }



    public String getCmd(String cmd_name){
        return (get(cmd_name)==null?"":get(cmd_name).toString());
    }

    public MsgEntity addPluginDataListener(PluginDataListener pluginDataListener){
        this.pluginDataListener=pluginDataListener;
        return this;
    }
    //数据回调

    @Override
    public void putAll(Map<? extends String, ?> m) {
       if(pluginDataListener!=null){
           pluginDataListener.onCallBack(m);
       }
    }

}

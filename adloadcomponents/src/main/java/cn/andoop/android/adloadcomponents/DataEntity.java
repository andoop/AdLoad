package cn.andoop.android.adloadcomponents;

import java.util.Map;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/4/19
* explain：消息体，传消息和进行消息回调
* * * * * * * * * * * * * * * * * * */
public class DataEntity {
    private Map<String,Object> mdata;
    public DataEntity(Map data){
        this.mdata=data;
    }
    public Object get(String key){

        return mdata.get(key);
    }

    public void sendData(Map<String,Object> data){
        this.mdata.putAll(data);
    }

    public String type() {
        return mdata.get("type").toString();
    }
}

package cn.andoop.android.adload;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：Plugin子类
* * * * * * * * * * * * * * * * * * */

import java.util.HashMap;

import cn.andoop.android.adload.entity.MsgEntity;
import dalvik.system.DexClassLoader;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class AdPlugin extends Plugin {
    private HashMap<String,Object> instance;
    //插件对应的classloader，用他来实例化插件中类的对象
    private DexClassLoader mDexClassLoader;
    public AdPlugin(Plugin plugin, HashMap<String, Object> instance, DexClassLoader dexClassLoader) {
        super(plugin.getId(),plugin.getVersion(),plugin.getUrl(),plugin.getMd5(),plugin.getMain(),plugin.getFilename());
        this.instance=instance;
    }
    /**
     * 执行
     * @param msgEntity
     */
    public void excute(MsgEntity msgEntity){
        instance.putAll(msgEntity);
    }
    public void put(String key,Object obj){
        instance.put(key,obj);
    }

    /**
     * 根据全类名，获取实例对象
     * @param refrence
     * @return
     */
    public Object getInstance(String refrence){
        if(mDexClassLoader==null){
            return null;
        }else {
            try {
                Class<?> aClass = mDexClassLoader.loadClass(refrence);
                Object obj=aClass.newInstance();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

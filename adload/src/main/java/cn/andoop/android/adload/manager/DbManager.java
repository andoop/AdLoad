package cn.andoop.android.adload.manager;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/3/8
* explain：数据库管理者
* * * * * * * * * * * * * * * * * * */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.andoop.android.adload.Plugin;
import cn.andoop.android.adload.db.AndoopDbHelper;
import cn.andoop.android.adload.db.OnUpdateCallback;


public class DbManager {
    private Context context;
    private static DbManager INSTANCE;
    private String dbname="dexdb";
    private String[] createsqls;
    private int version=1;
    private String DEX_TABLE_NAME="dextable";
    OnUpdateCallback onUpdateCallback=new OnUpdateCallback() {
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    };

    private final AndoopDbHelper andoopDbHelper;

    private DbManager(Context context) {
        this.context = context;

        String DEX_TABLE_SQL="CREATE TABLE IF NOT EXISTS ["+DEX_TABLE_NAME+"] (\n" +
                "  [id] VARCHAR,\n" +
                "  [version] INTEGER,\n" +
                "  [url] VARCHAR,\n" +
                "  [md5] VARCHAR,\n" +
                "  [main] VARCHAR,\n" +
                "  [filename] VARCHAR,\n" +
                "  [path] VARCHAR);\n";

        createsqls=new String[]{DEX_TABLE_SQL};

        andoopDbHelper = new AndoopDbHelper(context, dbname, createsqls, version, onUpdateCallback);
    }

    public static DbManager newInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (DbManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbManager(context);
                }
            }
        }
        return INSTANCE;
    }
    /**
     * 根据id获取一个插件的版本
     * @param id
     */
    public int getVersion(String id) {
        String sql="select version from "+DEX_TABLE_NAME+" where id=?";
        Cursor cursor = andoopDbHelper.rawQuery(sql, new String[]{id});
        if(cursor!=null&&cursor.moveToFirst()){
            int version = cursor.getInt(cursor.getColumnIndex("version"));
            return version;
        }else {
            andoopDbHelper.closeDb();
        }
        return -1;
    }

    /**
     * 根据id获取插件路径
     * @param id
     * @return
     */
    public String getPath(String id) {
        String sql="select path from "+DEX_TABLE_NAME+" where id=?";
        Cursor cursor = andoopDbHelper.rawQuery(sql, new String[]{id});
        if(cursor!=null&&cursor.moveToFirst()){
            String path = cursor.getString(cursor.getColumnIndex("path"));
            return path;
        }else {
            andoopDbHelper.closeDb();
        }
        return "";
    }

    /**
     * 根据id清除对应数据
     * @param id
     */
    public void clear(String id){
        andoopDbHelper.delete(DEX_TABLE_NAME, "id=?", new String[]{id});
        andoopDbHelper.closeDb();
    }

    /**
     * 更新或者插入数据
     * @param plugin
     */
    public void update(Plugin plugin,String path){
        andoopDbHelper.delete(DEX_TABLE_NAME,"id=?",new String[]{plugin.getId()});
        ContentValues values=new ContentValues();
        values.put("id",plugin.getId());
        values.put("version",plugin.getVersion());
        values.put("url",plugin.getUrl());
        values.put("md5",plugin.getMd5());
        values.put("main",plugin.getMain());
        values.put("path",path);
        values.put("filename",plugin.getFilename());
        andoopDbHelper.insert(DEX_TABLE_NAME,values);
        andoopDbHelper.closeDb();
    }
}

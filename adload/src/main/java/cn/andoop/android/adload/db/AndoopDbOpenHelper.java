package cn.andoop.android.adload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AndoopDbOpenHelper
        extends SQLiteOpenHelper
{
    private final String[] createsqls;
    private OnUpdateCallback onUpdateCallback;

    public AndoopDbOpenHelper(Context context, String dbname, String[] createsqls, int version, OnUpdateCallback onUpdateCallback)
    {
        super(context, dbname, null, version);
        this.createsqls = createsqls;
        this.onUpdateCallback = onUpdateCallback;
    }

    public void onCreate(SQLiteDatabase db)
    {
        if (this.createsqls == null) {
            return;
        }
        for (String sql : this.createsqls) {
            db.execSQL(sql);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (this.onUpdateCallback != null) {
            this.onUpdateCallback.onUpgrade(db, oldVersion, newVersion);
        }
    }
}

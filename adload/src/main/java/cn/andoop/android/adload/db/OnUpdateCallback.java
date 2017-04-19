package cn.andoop.android.adload.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by andoop on 2016/11/7.
 */

public interface OnUpdateCallback {
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}

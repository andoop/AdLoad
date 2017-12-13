package cn.andoop.android.adload.db;

import android.database.sqlite.SQLiteDatabase;

public abstract interface OnUpdateCallback
{
    public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2);
}

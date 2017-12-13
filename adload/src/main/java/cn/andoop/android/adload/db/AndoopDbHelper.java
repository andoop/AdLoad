package cn.andoop.android.adload.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class AndoopDbHelper
{
    private AndoopDbOpenHelper andoopDbOpenHelper;
    private SQLiteDatabase mDB;

    public AndoopDbHelper(Context activity, String dbname, String[] createsqls, int version, OnUpdateCallback onUpdateCallback)
    {
        this.andoopDbOpenHelper = new AndoopDbOpenHelper(activity, dbname, createsqls, version, onUpdateCallback);
    }

    public Cursor query(String table, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(table);
        this.mDB = this.andoopDbOpenHelper.getReadableDatabase();
        Cursor cursor = qb.query(this.mDB, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }

    public boolean insert(String table, ContentValues initialValues)
    {
        this.mDB = this.andoopDbOpenHelper.getWritableDatabase();
        long rowid = this.mDB.insert(table, null, initialValues);
        if (rowid <= 0L) {
            return false;
        }
        return true;
    }

    public int delete(String table, String where, String[] whereArgs)
    {
        this.mDB = this.andoopDbOpenHelper.getWritableDatabase();
        int delete = this.mDB.delete(table, where, whereArgs);
        return delete;
    }

    public int update(String table, ContentValues values, String where, String[] whereArgs)
    {
        this.mDB = this.andoopDbOpenHelper.getWritableDatabase();
        int update = this.mDB.update(table, values, where, whereArgs);
        return update;
    }

    public Cursor rawQuery(String sql, String[] selectionArgs)
    {
        this.mDB = this.andoopDbOpenHelper.getReadableDatabase();
        Cursor cursor = this.mDB.rawQuery(sql, selectionArgs);
        return cursor;
    }

    public void closeDb()
    {
        if (this.mDB != null) {
            try
            {
                this.mDB.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                this.mDB.close();
            }
        }
    }
}

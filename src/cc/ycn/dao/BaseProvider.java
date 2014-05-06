package cc.ycn.dao;

import android.database.sqlite.SQLiteDatabase;

public interface BaseProvider {
    public void createTable(SQLiteDatabase db);

    public void deleteTable(SQLiteDatabase db);

    public void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion);
}

package cc.ycn.view;

import android.database.sqlite.SQLiteDatabase;

public abstract class Base {
    public int _id;
    public long createTime;
    public long updateTime;

    public Base() {
        createTime = System.currentTimeMillis();
        updateTime = createTime;
    }
}

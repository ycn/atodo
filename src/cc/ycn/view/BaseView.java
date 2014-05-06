package cc.ycn.view;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseView {
    public int _id;
    public long createTime;
    public long updateTime;

    public BaseView() {
        createTime = System.currentTimeMillis();
        updateTime = createTime;
    }
}

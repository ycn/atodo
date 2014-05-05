package cc.ycn.view;

import android.database.sqlite.SQLiteDatabase;

public class Task extends Base {
    public String content = "N/A";
    public boolean done = false;

    public Task() {
        super();
    }

    public Task(String content) {
        super();
        this.content = content;
    }
}

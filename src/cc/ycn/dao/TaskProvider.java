package cc.ycn.dao;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import cc.ycn.util.DBHelper;

public class TaskProvider extends ContentProvider implements BaseProvider {

    public static final String TABLE_NAME = "task";

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ycn.task";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ycn.task";

    private static final int TASK_ALL = 1;
    private static final int TASK_ONE = 2;

    private static final String AUTHORITY = "cc.ycn.todo.TaskProvider";
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final Uri TASK_ALL_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    public static final Uri TASK_ONE_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME + "/#");


    private DBHelper helper;

    static {
        matcher.addURI(AUTHORITY, TABLE_NAME, TASK_ALL);
        matcher.addURI(AUTHORITY, TABLE_NAME + "/#", TASK_ONE);
    }

    @Override
    public boolean onCreate() {
        helper = DBHelper.getInstance(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int match = matcher.match(uri);
        switch (match) {
            case TASK_ALL:
                return CONTENT_TYPE;
            case TASK_ONE:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match = matcher.match(uri);
        switch (match) {
            case TASK_ALL:
                break;
            case TASK_ONE:
                long _id = ContentUris.parseId(uri);
                selection = "_id = ?";
                selectionArgs = new String[]{String.valueOf(_id)};
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = matcher.match(uri);
        if (match != TASK_ALL) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        if (values != null) {
            long rowId = db.insert(TABLE_NAME, null, values);
            if (rowId > 0) {
                notifyChanged();
                return ContentUris.withAppendedId(uri, rowId);
            }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = matcher.match(uri);
        if (match != TASK_ALL) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        if (count > 0) {
            notifyChanged();
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = matcher.match(uri);
        if (match != TASK_ALL) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        if (count > 0) {
            notifyChanged();
        }
        return count;
    }

    private void notifyChanged() {
        getContext().getContentResolver().notifyChange(TASK_ALL_URI, null);
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ",createTime INTEGER"
                + ",updateTime INTEGER"
                + ",content VARCHAR"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void deleteTable(SQLiteDatabase db) {
        String sql = "DROP TABLE IF EXISTS "
                + TABLE_NAME;
        db.execSQL(sql);
    }

    @Override
    public void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        //        deleteTable(db);
        //        createTable(db);
    }
}

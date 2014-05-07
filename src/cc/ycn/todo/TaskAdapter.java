package cc.ycn.todo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import cc.ycn.dao.TaskProvider;
import cc.ycn.view.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private static class ViewHolder {
        CheckBox taskCheck;
        TextView taskContent;
        TextView taskMeta;
    }

    private Activity context;
    private int resourceId;
    private List<Task> taskList;
    private ContentResolver resolver;


    public TaskAdapter(Activity context, int resourceId, List<Task> taskList, ContentResolver resolver) {
        this.context = context;
        this.resourceId = resourceId;
        this.taskList = taskList;
        if (this.taskList == null) {
            this.taskList = new LinkedList<Task>();
        }
        this.resolver = resolver;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.taskCheck = (CheckBox) convertView.findViewById(R.id.task_check);
            holder.taskContent = (TextView) convertView.findViewById(R.id.task_content);
            holder.taskMeta = (TextView) convertView.findViewById(R.id.task_meta);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Task task = taskList.get(position);
        holder.taskCheck.setChecked(task.done);
        holder.taskCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                done(task, isChecked);
            }
        });
        holder.taskContent.setText(task.content);
        holder.taskMeta.setText(getDate(task.createTime));

        return convertView;
    }

    public void add(Task task) {
        taskList.add(0, task);
        ContentValues values = new ContentValues();
        values.put("content", task.content);
        values.put("createTime", task.createTime);
        values.put("updateTime", task.updateTime);
        values.put("done", task.done);
        resolver.insert(TaskProvider.TASK_ALL_URI, values);
        notifyDataSetChanged();
    }

    public void done(Task task, boolean done) {
        task.done = done;
        ContentValues values = new ContentValues();
        values.put("done", task.done);
        values.put("updateTime", System.currentTimeMillis());
        resolver.update(TaskProvider.TASK_ALL_URI, values, "_id = ?", new String[]{task._id + ""});
        notifyDataSetChanged();
    }

    public void queryList() {
        Cursor cursor = resolver.query(TaskProvider.TASK_ALL_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            Task task = new Task(cursor.getString(cursor.getColumnIndex("content")));
            task._id = cursor.getInt(cursor.getColumnIndex("_id"));
            task.createTime = cursor.getLong(cursor.getColumnIndex("createTime"));
            task.updateTime = cursor.getLong(cursor.getColumnIndex("updateTime"));
            task.done = cursor.getInt(cursor.getColumnIndex("done")) == 1;
            taskList.add(0, task);
        }
    }

    private String getDate(long timeStamp) {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        } catch (Exception e) {
            return "N/A";
        }
    }
}

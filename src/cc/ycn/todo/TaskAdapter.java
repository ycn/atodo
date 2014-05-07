package cc.ycn.todo;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import cc.ycn.view.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private static class ViewHolder {
        Task task;
        CheckBox checkBox;
    }

    private Activity context;
    private int resourceId;
    private List<Task> taskList;

    public TaskAdapter(Activity context, int resourceId, List<Task> taskList) {
        this.context = context;
        this.resourceId = resourceId;
        this.taskList = taskList;
        if (this.taskList == null) {
            this.taskList = new LinkedList<Task>();
        }
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
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(resourceId, parent, false);

        CheckBox taskCheck = (CheckBox) row.findViewById(R.id.task_check);
        TextView taskContent = (TextView) row.findViewById(R.id.task_content);
        TextView taskMeta = (TextView) row.findViewById(R.id.task_meta);

        Task task = taskList.get(position);
        taskContent.setText(task.content);
        taskMeta.setText(getDate(task.createTime));

        return row;
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

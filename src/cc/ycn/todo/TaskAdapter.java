package cc.ycn.todo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.taskCheck = (CheckBox) convertView.findViewById(R.id.task_check);
            holder.taskCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    taskList.get(position).done = isChecked;
                }
            });
            holder.taskContent = (TextView) convertView.findViewById(R.id.task_content);
            holder.taskMeta = (TextView) convertView.findViewById(R.id.task_meta);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Task task = taskList.get(position);
        holder.taskCheck.setChecked(task.done);
        holder.taskContent.setText(task.content);
        holder.taskMeta.setText(getDate(task.createTime));

        return convertView;
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

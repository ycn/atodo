package cc.ycn.todo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cc.ycn.view.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {

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
//        View row;
//        row = View.inflate(resourceId, parent, false);
        return null;
    }
}

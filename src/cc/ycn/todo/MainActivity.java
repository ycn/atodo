package cc.ycn.todo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import cc.ycn.util.UserInputHelper;
import cc.ycn.view.Task;
import cc.ycn.view.ViewUtils;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "aTodo_main";

    private SharedPreferences settings;
    private EditText todoInput;
    private Button todoSubmit;
    private String inputText;
    private ListView todoTasks;
    private List<Task> taskList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        initStates();
        appSetup();
        findViews();
        setListeners();
        setDefaults();
    }

    private void initStates() {
        settings = getSharedPreferences(TAG, MODE_PRIVATE);
        Log.d(TAG, "SharedPreferences:" + settings.getAll().toString());
        taskList = new LinkedList<Task>();
    }


    private void appSetup() {
        setContentView(R.layout.main);
    }

    private void findViews() {
        todoInput = (EditText) findViewById(R.id.todo_input);
        todoSubmit = (Button) findViewById(R.id.todo_submit);
        todoTasks = (ListView) findViewById(R.id.todo_tasks);
        todoTasks.setAdapter(new TaskAdapter(this, R.layout.task, taskList));
        for (int i=0; i< 100 ; i++) {
            taskList.add(new Task("Go to lunch." + i));
        }

    }

    private void setListeners() {

    }

    private void setDefaults() {
        inputText = settings.getString("inputText", "");
        ViewUtils.setInputText(todoInput, inputText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        inputText = UserInputHelper.getString(todoInput.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        ViewUtils.setInputText(todoInput, inputText);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        SharedPreferences.Editor edit = settings.edit();
        edit.putString("inputText", inputText);
        edit.commit();
    }
}

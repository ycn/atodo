package cc.ycn.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import cc.ycn.util.UserInputHelper;

public class MainActivity extends Activity {
    private static final String TAG = "aTodo";
    private EditText todoInput;
    private Button todoSubmit;
    private String inputText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        initStates();
        appSetup();
        findViews();
        setListeners();
    }

    private void initStates() {
        inputText = "";
    }

    private void appSetup() {
        setContentView(R.layout.main);
    }

    private void findViews() {
        todoInput = (EditText) findViewById(R.id.todo_input);
        todoSubmit = (Button) findViewById(R.id.todo_submit);
    }

    private void setListeners() {

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

        inputText = UserInputHelper.getString(todoInput.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (inputText != "") {
            todoInput.setText(inputText);
        }
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
    }

}

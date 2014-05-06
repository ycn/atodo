package cc.ycn.view;

import android.widget.EditText;
import cc.ycn.util.UserInputHelper;

public class ViewUtils {

    public static void setInputText(EditText view, String text) {
        if (view == null) return;
        if (text == null) text = "";
        text = UserInputHelper.getString(text);
        view.setText(text);
        view.setSelection(text.length());
    }
}

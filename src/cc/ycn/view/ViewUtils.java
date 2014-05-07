package cc.ycn.view;

import android.widget.EditText;
import cc.ycn.util.UserInputHelper;

public class ViewUtils {

    public static void setInputText(EditText view, String text, int mode) {
        if (view == null) return;
        if (text == null) text = "";
        text = UserInputHelper.getString(text);
        view.setText(text);
        switch (mode) {
            case 0: // blur
                break;
            case 1: // place the cursor at last
                view.setSelection(text.length());
                break;
            case 2: // select the text
                view.setSelection(0, text.length());
                break;
        }

    }
}

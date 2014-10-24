package com.example.rjhy.myactionbar;

import android.content.Context;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Created by rjhy on 14-9-17.
 */
public class TestView extends LinearLayout implements Checkable {
    public TestView(Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}

package com.example.rjhy.myactionbar;

import android.content.Context;
import android.support.v7.view.CollapsibleActionView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by rjhy on 14-9-15.
 */
public class MyActionView extends LinearLayout {

    private EditText editText;
    private Button button;

    public MyActionView(Context context){
        this(context, null);
    }

    public MyActionView(Context context, AttributeSet attrs){
        super(context, attrs);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_action_view, this, true);

        editText = (EditText) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getVisibility() == View.GONE){
                    editText.setVisibility(View.VISIBLE);
                }else{
                    editText.setVisibility(View.GONE);
                }
            }
        });
    }
}

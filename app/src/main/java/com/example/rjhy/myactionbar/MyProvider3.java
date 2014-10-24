package com.example.rjhy.myactionbar;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ActionProvider;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by rjhy on 14-9-16.
 */
public class MyProvider3 extends ActionProvider {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ImageButton button;
    private Intent intent;

    private String mProviderHistoryFileName = "provider3.xml";

    public MyProvider3(Context context){
        super(context);
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public View onCreateActionView() {
        ActivityChooserModel activityChooserModel = ActivityChooserModel.get(mContext, mProviderHistoryFileName);
        ActivityChooserView activityChooserView = new ActivityChooserView(mContext);

        activityChooserView.setExpandActivityOverflowButtonDrawable(getContext().getResources().getDrawable(android.R.drawable.ic_menu_add));
        activityChooserView.setProvider(this);
        activityChooserView.setActivityChooserModel(activityChooserModel);
        return activityChooserView;
    }

    public void setProviderIntent(Intent intent){
        ActivityChooserModel dataModel = ActivityChooserModel.get(mContext,
                mProviderHistoryFileName);
        dataModel.setIntent(intent);
    }
}

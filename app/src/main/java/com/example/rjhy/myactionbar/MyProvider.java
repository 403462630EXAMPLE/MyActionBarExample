package com.example.rjhy.myactionbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ActionProvider;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserView;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by rjhy on 14-9-12.
 */
public class MyProvider extends ActionProvider {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ImageButton button;
    public MyProvider(Context context){
        super(context);
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public View onCreateActionView() {
        Log.i(MyActivity.TAG, "onCreateActionView");
        View view = layoutInflater.inflate(R.layout.action_provider, null);
        button = (ImageButton) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something...
                Log.i(MyActivity.TAG, "onClick");
                popMenu(v);
            }
        });
        //return null 将执行hasSubMenu和onPrepareSubMenu
        return view;

    }

    private void popView(View v){
        View view = layoutInflater.inflate(R.layout.popup_content, null);
        //测量view尺寸
        view.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);

        PopupWindow popupWindow = new PopupWindow(view);
        //设置PopupWindow尺寸
        popupWindow.setWidth(view.getMeasuredWidth());
        popupWindow.setHeight(view.getMeasuredHeight());

        popupWindow.setFocusable(true);
        //设置PopupWindow背景
        popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.abc_item_background_holo_dark));
        //设置PopupWindow点击屏幕其他地方消失，注意：PopupWindow一定要设置背景（也就是调用setBackgroundDrawable方法），这个方法才有效
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(v);
    }

    private void popListView(View v){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, new String[]{"添加", "删除", "修改"});
        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setAnchorView(v);

        //这里是设置ListPopupWindow尺寸为包裹AnchorView大小
//        listPopupWindow.setContentWidth(ListPopupWindow.WRAP_CONTENT);
//        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, ""+i, Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });

        //设置ListPopupWindow尺寸为包裹内容大小
        listPopupWindow.show();
        listPopupWindow.getListView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        listPopupWindow.setContentWidth(listPopupWindow.getListView().getMeasuredWidth());
        listPopupWindow.show();
    }

    @Override
    public boolean onPerformDefaultAction() {
        Log.i(MyActivity.TAG, "onPerformDefaultAction");
        return super.onPerformDefaultAction();
    }

    private void popMenu(View v){
        PopupMenu popupMenu = new PopupMenu(mContext, v);
        popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(mContext, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        Log.i(MyActivity.TAG, "onPrepareSubMenu");
//        super.onPrepareSubMenu(subMenu);
        subMenu.clear();
        subMenu.add(0, 1, 1, "subMenu1").setIcon(android.R.drawable.ic_dialog_email)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(mContext, "subMenu1", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        subMenu.add(0, 2, 3, "subMenu2").setIcon(android.R.drawable.ic_dialog_info)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(mContext, "subMenu2", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        subMenu.add(0, 3, 2, "subMenu3").setIcon(android.R.drawable.ic_dialog_alert)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(mContext, "subMenu3", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}

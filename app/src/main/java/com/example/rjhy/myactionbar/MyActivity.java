package com.example.rjhy.myactionbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public class MyActivity extends ActionBarActivity {
    private Button button;
    public final static String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        getSupportActionBar().hide();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick");
                Intent intent = new Intent(MyActivity.this, MyActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        MenuItem searchItem = menu.findItem(R.id.action_settings);
//        LinearLayout linearLayout = (LinearLayout) MenuItemCompat.getActionView(searchItem);
//        Button button1 = (Button) linearLayout.findViewById(R.id.action_button);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Log.i("MyActivity", "onMenuItemActionExpand");
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Log.i("MyActivity", "onMenuItemActionCollapse");
                return false;
            }
        });

        MenuItem menuItem = menu.findItem(R.id.share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getDefaultIntent());

        MenuItem menuItem2 = menu.findItem(R.id.custom2);
        MyProvider3 myProvider = (MyProvider3) MenuItemCompat.getActionProvider(menuItem2);
        myProvider.setProviderIntent(new Intent("com.example.provider"));

        MenuItem menuItem1 = menu.findItem(R.id.custom);
        MyProvider2 myProvider2 = (MyProvider2) MenuItemCompat.getActionProvider(menuItem1);
        myProvider2.setShareIntent(new Intent("com.example.provider"));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return getDefaultIntent();
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

}

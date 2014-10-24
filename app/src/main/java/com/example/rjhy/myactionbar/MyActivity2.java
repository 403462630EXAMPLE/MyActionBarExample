package com.example.rjhy.myactionbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity2 extends ActionBarActivity {

    private ViewPager viewPager;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);
        viewPager = (ViewPager) findViewById(R.id.pager);
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        adapter.add(new TestFragment1());
        adapter.add(new TestFragment2());
        viewPager.setAdapter(adapter);

//        initNavigationTabs();
        //第一步
        List<String> list = new ArrayList<String>();
        for (int i=0; i<5; i++) {
            list.add("position" + i);
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        //第二步
        ActionBar.OnNavigationListener onNavigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                Toast.makeText(MyActivity2.this, "position"+i, Toast.LENGTH_SHORT).show();
                return false;
            }
        };
        //第三步
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //第四步
        getSupportActionBar().setListNavigationCallbacks(spinnerAdapter, onNavigationListener);
    }



    private void initNavigationTabs() {
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                getSupportActionBar().setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



        ActionBar.Tab tab1 = getSupportActionBar().newTab()
                .setText("tab1")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTabListener(tabListener);
        ActionBar.Tab tab2 = getSupportActionBar().newTab()
                .setText("tab2")
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTabListener(tabListener);

//        ActionBar.Tab tab1 = getSupportActionBar().newTab()
//                .setText("tab1")
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .setTabListener(new MyTabListener(this, "tab1", TestFragment1.class));
//        ActionBar.Tab tab2 = getSupportActionBar().newTab()
//                .setText("tab2")
//                .setIcon(android.R.drawable.ic_dialog_email)
//                .setTabListener(new MyTabListener(this, "tab2", TestFragment2.class));

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().addTab(tab1);
        getSupportActionBar().addTab(tab2);
    }
    class MyPageAdapter extends FragmentStatePagerAdapter{
        private List<Fragment> list = new ArrayList<Fragment>();

        public MyPageAdapter(FragmentManager fm){
            super(fm);
        }

        public void add(Fragment fragment){
            list.add(fragment);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }


    class MyTabListener<T extends Fragment> implements ActionBar.TabListener{
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        public MyTabListener(Activity activity, String tag, Class<T> class0){
            this.mActivity = activity;
            this.mTag = tag;
            this.mClass = class0;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment == null) {
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }

    public static class TestFragment1 extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.test, null);
            ((TextView)(view.findViewById(R.id.text))).setText("TestFragment1");
            return view;
        }
    }
    public static class TestFragment2 extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.test, null);
            ((TextView)(view.findViewById(R.id.text))).setText("TestFragment2");
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.home) {
            Log.i(MyActivity.TAG, "onOptionsItemSelected:"+id);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public Intent getSupportParentActivityIntent() {
//        Log.i("my", "getSupportParentActivityIntent");
//        Intent intent = new Intent();
//        intent.setClass(this, MyActivity.class);
//
//        return intent;
//    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }
}

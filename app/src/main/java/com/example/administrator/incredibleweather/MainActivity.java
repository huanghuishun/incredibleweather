package com.example.administrator.incredibleweather;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import database.DataHandle;
import fragment.ForecastWeatherFragment;
import fragment.LivingIndexFragment;
import fragment.TodayWeatherFragment;
import util.HttpCallbackListener;
import util.HttpUtil;
import widget.CustomProgress;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers?&cityid=";    //API地址

    private List<Fragment> fragmentList;
    private LivingIndexFragment livingIndexFragment = null;
    private TodayWeatherFragment todayWeatherFragment = null;
    private ForecastWeatherFragment forecastWeatherFragment = null;
    private Toolbar toolbar = null;

    private String countyname = "default";     //设置全局的countyname

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;                             //3个对象用于广播接收

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.myposition);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.mytab);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.myviewpager);
        tabLayout.addTab(tabLayout.newTab().setText("最近天气"));
        tabLayout.addTab(tabLayout.newTab().setText("今天"));
        tabLayout.addTab(tabLayout.newTab().setText("生活指数"));
     //   tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        fragmentList = adapter.getFragmentList();
        if (fragmentList != null && fragmentList.size() > 0) {
            forecastWeatherFragment = (ForecastWeatherFragment) fragmentList.get(0);
            todayWeatherFragment = (TodayWeatherFragment) fragmentList.get(1);
            livingIndexFragment = (LivingIndexFragment) fragmentList.get(2);
        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                fragmentList = adapter.getFragmentList();
                if (fragmentList != null && fragmentList.size() > 0) {
                    forecastWeatherFragment = (ForecastWeatherFragment) fragmentList.get(0);
                    todayWeatherFragment = (TodayWeatherFragment) fragmentList.get(1);
                    livingIndexFragment = (LivingIndexFragment) fragmentList.get(2);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

        localBroadcastManager = LocalBroadcastManager.getInstance(MyApplication.getContext());
        intentFilter = new IntentFilter();
        intentFilter.addAction("出事啦！");
        intentFilter.addAction("加载成功！");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);     //注册广播监听器


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("出事啦！")) {
                Snackbar.make(findViewById(R.id.main), "错误！查询不到城市信息！", Snackbar.LENGTH_LONG).setAction("重新设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ChooseAreaActivity.class);
                        startActivityForResult(intent, 1);
                    }
                }).show();
                //  localBroadcastManager.unregisterReceiver(localReceiver);
            } else if (action.equals("加载成功！")){
                Snackbar.make(findViewById(R.id.main), "加载成功！", Snackbar.LENGTH_LONG).setAction("好", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.navv);
                        linearLayout.setBackgroundResource(R.drawable.drawback);

                    }
                }).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_refresh:
                    if (DataHandle.getCountycode().equals("")) {
                        Snackbar.make(findViewById(R.id.main), "请先设置城市！", Snackbar.LENGTH_LONG).setAction("好", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, ChooseAreaActivity.class);
                                startActivityForResult(intent, 1);

                            }
                        }).show();
                    } else {
                        queryWeatherCode(DataHandle.getCountycode());
                        //       CustomProgress dialog = new CustomProgress(MainActivity.this);
                        //        dialog.show(MainActivity.this, "加载中...", true, null);
                 /*       Snackbar.make((LinearLayout) findViewById(R.id.main), "刷新成功！", Snackbar.LENGTH_SHORT).setAction("好", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();*/


                    }
                    break;


            }
            return true;
        }
    };


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {             //Activity加载完毕监听器
        if (hasFocus) {
            todayWeatherFragment.setToday(0);
            forecastWeatherFragment.setToday();
            livingIndexFragment.setToday(0);
            if (getCountyname().equals("default")) {
                toolbar.setTitle(todayWeatherFragment.getCity());
            } else {
                toolbar.setTitle(getCountyname());
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String countyCode = data.getStringExtra("COUNTY_CODE");
                    String countyName = data.getStringExtra("COUNTY_NAME");
                    setCountyname(countyName);
                    DataHandle.setCountycode(countyCode);
                    toolbar.setTitle(getCountyname());
                    queryWeatherCode(countyCode);
                }
                break;
            default:
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_refresh || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myplace) {
            // 我的
            item.setChecked(false);
        } else if (id == R.id.nav_mylocation) {
            //定位
            item.setChecked(false);
        } else if (id == R.id.nav_search) {
            //查找
            Intent intent = new Intent(MainActivity.this, ChooseAreaActivity.class);
            startActivityForResult(intent, 1);
            item.setChecked(false);


        } else if (id == R.id.nav_edit) {
            /*   心情
            final TextView textView = (TextView) findViewById(R.id.moodtext);
            final TextView textView2 = (TextView) findViewById(R.id.moodtext2);
            final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.navv);
            LayoutInflater inflater = LayoutInflater.from(this);
            final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.edit, null);
            final AlertDialog mydialog = new AlertDialog.Builder(MainActivity.this).create();
            Button editok = (Button) layout.findViewById(R.id.editok);
            Button editcancel = (Button) layout.findViewById(R.id.editcancel);
            final EditText editText = (EditText) layout.findViewById(R.id.mood);
            final EditText editText2 = (EditText) layout.findViewById(R.id.mood2);
            mydialog.show();
            mydialog.getWindow().setContentView(layout);
            editText.setFocusable(true);
            editText.requestFocus();

            editok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().length() != 0) {
                        textView.setText(editText.getText().toString());
                    }
                    if (editText2.getText().length() != 0) {
                        textView2.setText(editText2.getText().toString());
                    }
                    Snackbar.make((LinearLayout) findViewById(R.id.main), "设置成功！", Snackbar.LENGTH_SHORT).setAction("好", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                    mydialog.dismiss();
                }
            });
            editcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make((LinearLayout) findViewById(R.id.main), "你取消了操作。", Snackbar.LENGTH_SHORT).setAction("好", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                    mydialog.dismiss();
                }
            });   */
            item.setChecked(false);

        } else if (id == R.id.nav_manage) {
            //设置

        } else if (id == R.id.nav_revert) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void queryWeatherCode(final String county_code) {
        String address = "http://www.weather.com.cn/data/list3/city" + county_code + ".xml";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

            @Override
            public void onFinish(String response) {
                String[] array = response.split("\\|");
                String weatherCode = array[1];
                String address = httpUrl + weatherCode;
                HttpUtil.requestbyAPI(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        DataHandle.saveData(MainActivity.this, response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                todayWeatherFragment.setToday(1);
                                forecastWeatherFragment.setToday();
                                livingIndexFragment.setToday(1);

                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }


    public String getCountyname() {    //延时首页countyname设定，解决1个bug
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }


}


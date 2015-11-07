package com.example.administrator.incredibleweather;





import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fragment.ForecastWeatherFragment;
import fragment.LivingIndexFragment;
import fragment.TodayWeatherFragment;

/**
 * Created by Administrator on 2015/10/29.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private LivingIndexFragment livingIndexFragment=null;
    private TodayWeatherFragment todayWeatherFragment=null;
    private ForecastWeatherFragment forecastWeatherFragment=null;

    private List<Fragment> fragmentList;


    int tabs_num;
    public MyPagerAdapter(FragmentManager fragmentManager,int tabs_num){
        super(fragmentManager);
        this.tabs_num = tabs_num;
        fragmentList=new ArrayList<>();
        livingIndexFragment = new LivingIndexFragment();
        todayWeatherFragment = new TodayWeatherFragment();
        forecastWeatherFragment = new ForecastWeatherFragment();
        fragmentList.add(0,forecastWeatherFragment);
        fragmentList.add(1,todayWeatherFragment);
        fragmentList.add(2,livingIndexFragment);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (fragmentList.get(0)==null){
                    forecastWeatherFragment = new ForecastWeatherFragment();
                    fragmentList.add(0,forecastWeatherFragment);
                }else {
                    forecastWeatherFragment= (ForecastWeatherFragment) fragmentList.get(0);
                }
                return forecastWeatherFragment;
            case 1:
                if (fragmentList.get(1)==null){
                    todayWeatherFragment = new TodayWeatherFragment();
                    fragmentList.add(1,todayWeatherFragment);
                }else {
                    todayWeatherFragment= (TodayWeatherFragment) fragmentList.get(1);
                }
                return todayWeatherFragment;
            case 2:
                if (fragmentList.get(2)==null){
                    livingIndexFragment = new LivingIndexFragment();
                    fragmentList.add(2,livingIndexFragment);
                }else {
                    livingIndexFragment = (LivingIndexFragment) fragmentList.get(2);
                }
                return livingIndexFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs_num;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }
}
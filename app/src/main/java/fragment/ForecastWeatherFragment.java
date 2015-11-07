package fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.incredibleweather.ChooseAreaActivity;
import com.example.administrator.incredibleweather.MainActivity;
import com.example.administrator.incredibleweather.MyApplication;
import com.example.administrator.incredibleweather.R;
import com.google.gson.Gson;

import database.DataHandle;
import gson.Status;
import widget.ChooseImages;

/**
 * Created by Administrator on 2015/10/29.
 */
public class ForecastWeatherFragment extends android.support.v4.app.Fragment {

    private View view;

    private TextView date1;
    private TextView date2;
    private TextView date3;
    private TextView date4;
    private TextView date5;
    private TextView date6;
    private TextView date7;
    private TextView[] date = new TextView[]{date1, date2, date3, date4, date5, date6, date7};
    private int[] dateid = new int[]{R.id.date1, R.id.date2, R.id.date3, R.id.date4, R.id.date5, R.id.date6, R.id.date7};

    private TextView week1;
    private TextView week2;
    private TextView week3;
    private TextView week4;
    private TextView week5;
    private TextView week6;
    private TextView week7;
    private TextView[] week = new TextView[]{week1, week2, week3, week4, week5, week6, week7};
    private int[] weekid = new int[]{R.id.week1, R.id.week2, R.id.week3, R.id.week4, R.id.week5, R.id.week6, R.id.week7};

    private TextView type1;
    private TextView type2;
    private TextView type3;
    private TextView type4;
    private TextView type5;
    private TextView type6;
    private TextView type7;
    private TextView[] type = new TextView[]{type1, type2, type3, type4, type5, type6, type7};
    private int[] typeid = new int[]{R.id.type1, R.id.type2, R.id.type3, R.id.type4, R.id.type5, R.id.type6, R.id.type7};

    private TextView temp1;
    private TextView temp2;
    private TextView temp3;
    private TextView temp4;
    private TextView temp5;
    private TextView temp6;
    private TextView temp7;
    private TextView[] temp = new TextView[]{temp1, temp2, temp3, temp4, temp5, temp6, temp7};
    private int[] tempid = new int[]{R.id.temp1, R.id.temp2, R.id.temp3, R.id.temp4, R.id.temp5, R.id.temp6, R.id.temp7};

    private TextView fengxiang1;
    private TextView fengxiang2;
    private TextView fengxiang3;
    private TextView fengxiang4;
    private TextView fengxiang5;
    private TextView fengxiang6;
    private TextView fengxiang7;
    private TextView[] fengxiang = new TextView[]{fengxiang1, fengxiang2, fengxiang3, fengxiang4, fengxiang5, fengxiang6, fengxiang7};
    private int[] fengxiangid = new int[]{R.id.fengxiang1, R.id.fengxiang2, R.id.fengxiang3, R.id.fengxiang4, R.id.fengxiang5, R.id.fengxiang6, R.id.fengxiang7};

    private TextView fengli1;
    private TextView fengli2;
    private TextView fengli3;
    private TextView fengli4;
    private TextView fengli5;
    private TextView fengli6;
    private TextView fengli7;
    private TextView[] fengli = new TextView[]{fengli1, fengli2, fengli3, fengli4, fengli5, fengli6, fengli7};
    private int[] fengliid = new int[]{R.id.fengli1, R.id.fengli2, R.id.fengli3, R.id.fengli4, R.id.fengli5, R.id.fengli6, R.id.fengli7};

    private ImageView forc1;
    private ImageView forc2;
    private ImageView forc3;
    private ImageView forc4;
    private ImageView forc5;
    private ImageView forc6;
    private ImageView forc7;
    private ImageView[] forc = new ImageView[]{forc1, forc2, forc3, forc4, forc5, forc6, forc7};
    private int[] forcid = new int[]{R.id.forc1, R.id.forc2, R.id.forc3, R.id.forc4, R.id.forc5, R.id.forc6, R.id.forc7};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forecast, container, false);

        for (int i = 0; i < 7; i++) {
            date[i] = (TextView) view.findViewById(dateid[i]);
            week[i] = (TextView) view.findViewById(weekid[i]);
            temp[i] = (TextView) view.findViewById(tempid[i]);
            type[i] = (TextView) view.findViewById(typeid[i]);
            fengxiang[i] = (TextView) view.findViewById(fengxiangid[i]);
            fengli[i] = (TextView) view.findViewById(fengliid[i]);
            forc[i] = (ImageView) view.findViewById(forcid[i]);
        }
        return view;
    }

    public void setToday() {
        Status status = DataHandle.getgsonData();
        if (status != null) {
            for (int i = 0; i < 3; i++) {
                if (!TextUtils.isEmpty(status.getRetData().getHistory().get(i + 4).getDate())) {
                    String[] day = status.getRetData().getHistory().get(i + 4).getDate().split("-");
                    if (day != null && day.length == 3) {
                        date[i].setText(day[1] + "/" + day[2]);
                    }
                }

                //    date[i].setText(status.getRetData().getHistory().get(i+4).getDate());  不用这个是因为这个太长，用split-去掉年份
                week[i].setText(status.getRetData().getHistory().get(i + 4).getWeek());
                type[i].setText(status.getRetData().getHistory().get(i + 4).getType());
                temp[i].setText(status.getRetData().getHistory().get(i + 4).getLowtemp() + "~" + status.getRetData().getHistory().get(i + 4).getHightemp());
                fengxiang[i].setText(status.getRetData().getHistory().get(i + 4).getFengxiang());
                fengli[i].setText(status.getRetData().getHistory().get(i + 4).getFengli());
                forc[i].setImageResource(ChooseImages.ChooseImagesDrawable(status.getRetData().getHistory().get(i + 4).getType()));
            }
            if (!TextUtils.isEmpty(status.getRetData().getToday().getDate())) {
                String[] dayy = status.getRetData().getToday().getDate().split("-");
                if (dayy != null && dayy.length == 3) {
                    date[3].setText(dayy[1] + "/" + dayy[2]);
                }
                // date[3].setText(status.getRetData().getToday().getDate()); 不用这个是因为这个太长，用split-去掉年份
                week[3].setText(status.getRetData().getToday().getWeek());
                type[3].setText(status.getRetData().getToday().getType());
                temp[3].setText(status.getRetData().getToday().getLowtemp() + "~" + status.getRetData().getToday().getHightemp());
                fengxiang[3].setText(status.getRetData().getToday().getFengxiang());
                fengli[3].setText(status.getRetData().getToday().getFengli());
                forc[3].setImageResource(ChooseImages.ChooseImagesDrawable(status.getRetData().getToday().getType()));
                for (int i = 4; i < 7; i++) {
                    if (!TextUtils.isEmpty(status.getRetData().getForecast().get(i - 4).getDate())) {
                        String[] day = status.getRetData().getForecast().get(i - 4).getDate().split("-");
                        if (day != null && day.length == 3) {
                            date[i].setText(day[1] + "/" + day[2]);
                        }
                    }
                    //    date[i].setText(status.getRetData().getForecast().get(i - 4).getDate());  不用这个是因为这个太长，用split-去掉年份
                    week[i].setText(status.getRetData().getForecast().get(i - 4).getWeek());
                    type[i].setText(status.getRetData().getForecast().get(i - 4).getType());
                    temp[i].setText(status.getRetData().getForecast().get(i - 4).getLowtemp() + "~" + status.getRetData().getForecast().get(i - 4).getHightemp());
                    fengxiang[i].setText(status.getRetData().getForecast().get(i - 4).getFengxiang());
                    fengli[i].setText(status.getRetData().getForecast().get(i - 4).getFengli());
                    forc[i].setImageResource(ChooseImages.ChooseImagesDrawable(status.getRetData().getForecast().get(i - 4).getType()));
                }
            }
        }
    }
}



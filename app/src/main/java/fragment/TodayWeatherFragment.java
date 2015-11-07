package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.incredibleweather.MainActivity;
import com.example.administrator.incredibleweather.MyApplication;
import com.example.administrator.incredibleweather.R;
import com.google.gson.Gson;

import database.DataHandle;
import gson.Status;
import widget.ChooseImages;


/**
 * Created by Administrator on 2015/10/26.
 */
public class TodayWeatherFragment extends android.support.v4.app.Fragment {
    private View view;

    private TextView TtodayTemp;
    private TextView TtodayType;
    private TextView TmyPlace;
    private TextView Ttodate;
    private TextView Ttoweek;
    private TextView TtodayTemp2;
    private TextView TtodayFengxiang;
    private TextView TtodayFengli;
    private TextView TomorrowTemp2;
    private TextView TomorrowFengxiang;
    private TextView TomorrowFengli;
    private ImageView TodayImg;

    private LocalBroadcastManager localBroadcastManager;


  //  private reFreshWeatherData OnRefreshWeatherDataListener=null;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        view = inflater.inflate(R.layout.todayweather,container,false);
        TtodayTemp = (TextView)view.findViewById(R.id.todayTemp);
        TtodayType = (TextView)view.findViewById(R.id.todayType);
        TmyPlace = (TextView)view.findViewById(R.id.myPlace);
        Ttodate = (TextView)view.findViewById(R.id.todate);
        Ttoweek = (TextView)view.findViewById(R.id.toweek);
        TtodayTemp2 = (TextView)view.findViewById(R.id.todayTemp2);
        TtodayFengxiang = (TextView)view.findViewById(R.id.todayFengxiang);
        TtodayFengli = (TextView)view.findViewById(R.id.todayFengli);
        TomorrowTemp2 = (TextView)view.findViewById(R.id.tomorrowTemp2);
        TomorrowFengxiang = (TextView)view.findViewById(R.id.tomorrowFengxiang);
        TomorrowFengli = (TextView) view.findViewById(R.id.tomorrowFengli);
        TodayImg = (ImageView)view.findViewById(R.id.todayimg);

        return view;


    }
    public void setToday(int id) {    //设置id是因为不想Mainactivity的onWindowFocusChanged方法与query方法冲突
        Status status = DataHandle.getgsonData();
            if (status != null ) {
                TtodayTemp.setText(status.getRetData().getToday().getCurTemp());
                TtodayType.setText(status.getRetData().getToday().getType());
                TmyPlace.setText(status.getRetData().getCity());
                Ttodate.setText(status.getRetData().getToday().getDate());
                Ttoweek.setText(status.getRetData().getToday().getWeek());
                TtodayTemp2.setText(status.getRetData().getToday().getLowtemp() + "~" + status.getRetData().getToday().getHightemp());
                TtodayFengxiang.setText(status.getRetData().getToday().getFengxiang());
                TtodayFengli.setText(status.getRetData().getToday().getFengli());
                TomorrowTemp2.setText(status.getRetData().getForecast().get(0).getLowtemp() + "~" + status.getRetData().getForecast().get(0).getHightemp());
                TomorrowFengxiang.setText(status.getRetData().getForecast().get(0).getFengxiang());
                TomorrowFengli.setText(status.getRetData().getForecast().get(0).getFengli());
                TodayImg.setImageResource(ChooseImages.ChooseImagesDrawable(status.getRetData().getToday().getType()));
         //       localBroadcastManager = LocalBroadcastManager.getInstance(MyApplication.getContext());
    //            Intent intent = new Intent("加载成功！");
      //          localBroadcastManager.sendBroadcast(intent);
            } else if (id == 1){
                localBroadcastManager = LocalBroadcastManager.getInstance(MyApplication.getContext());
                Intent intent = new Intent("出事啦！");
                localBroadcastManager.sendBroadcast(intent);//发送数据错误提示广播
            }

    }
    public String getCity(){
        String city = "";
        Status status = DataHandle.getgsonData();
        if (status != null) {
         //   System.out.println("response=" + response);
            city = status.getRetData().getCity();
        }
        return city;
    }
}

  /*  public void setOnRefreshWeatherDataListener(reFreshWeatherData onRefreshWeatherDataListener) {
        OnRefreshWeatherDataListener = onRefreshWeatherDataListener;
        if (OnRefreshWeatherDataListener!=null){
            OnRefreshWeatherDataListener.reFreshData(TtodayTemp,TtodayType,TmyPlace,Ttodate,TtodayTemp2,TtodayFengxiang,TtodayFengli);
        }
    }

    public interface reFreshWeatherData{
        void reFreshData(TextView TtodayTemp,TextView TtodayType, TextView Tmyplace,TextView Ttodate,TextView Ttodaytemp2,TextView TtodayFengxiang,TextView TtodayFengli);
    }
}  */

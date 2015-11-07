package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.incredibleweather.MainActivity;
import com.example.administrator.incredibleweather.MyApplication;
import com.example.administrator.incredibleweather.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import gson.Forecast;
import gson.History;
import gson.Status;

/**
 * Created by Administrator on 2015/11/2.
 */
public class RecentWeatherFragment extends ListFragment {
    private View view;
    private String[] date = new String[7];
    private String[] week = new String[7];
    private String[] type = new String[7];
    private String[] temp = new String[7];
    private String[] fengxiang = new String[7];
    private String[] fengli = new String[7];

    private SimpleAdapter simpleAdapter;
    private Context context = MyApplication.getContext();
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        view = inflater.inflate(R.layout.recentweather,container,false);
        listView = (ListView)view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String response = sharedPreferences.getString("allofdata", "");
        if (response.length() != 0) {
            System.out.println("response=" + response);
            Gson gson = new Gson();
            Status status = gson.fromJson(response, Status.class);
            List<History> historyList = status.getRetData().getHistory();
            List<Forecast> forecastList = status.getRetData().getForecast();
            for (int i = 0; i < 3; i++) {
                date[i] = historyList.get(i+4).getDate();
                week[i] = historyList.get(i+4).getWeek();
                type[i] = historyList.get(i+4).getType();
                temp[i] = historyList.get(i+4).getLowtemp() + "~" + historyList.get(i+4).getHightemp();
                fengxiang[i] = historyList.get(i+4).getFengxiang();
                fengli[i] = historyList.get(i+4).getFengli();
            }
            date[3] = status.getRetData().getToday().getDate();
            week[3] = status.getRetData().getToday().getWeek();
            type[3] = status.getRetData().getToday().getType();
            temp[3] = status.getRetData().getToday().getLowtemp() + "~" + status.getRetData().getToday().getHightemp();
            fengxiang[3] = status.getRetData().getToday().getFengxiang();
            fengli[3] = status.getRetData().getToday().getFengli();
            for (int i = 4; i < 7; i++) {
                date[i] = forecastList.get(i-4).getDate();
                week[i] = forecastList.get(i-4).getWeek();
                type[i] = forecastList.get(i-4).getType();
                temp[i] = forecastList.get(i-4).getLowtemp() + "~" + forecastList.get(i-4).getHightemp();
                fengxiang[i] = forecastList.get(i-4).getFengxiang();
                fengli[i] = forecastList.get(i-4).getFengli();
            }

        }
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 7; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("date", date[i]);
            listItem.put("week", week[i]);
            listItem.put("type", type[i]);
            listItem.put("temp", temp[i]);
            listItem.put("fengxiang", fengxiang[i]);
            listItem.put("fengli", fengli[i]);
        }

        setListAdapter(simpleAdapter);
        simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.recentwidget,
                new String[]{"date", "week", "type", "temp", "fengxiang", "fengli"},
                new int[]{R.id.rdate, R.id.rweek, R.id.rtype, R.id.rtemp, R.id.rfengxiang, R.id.rfengli});




    }



  /*      Context context = MyApplication.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String response = sharedPreferences.getString("allofdata", "");
        if (response.length() != 0) {
            System.out.println("response=" + response);
            Gson gson = new Gson();
            Status status = gson.fromJson(response, Status.class);
            List<History> historyList = status.getRetData().getHistory();
            List<Forecast> forecastList = status.getRetData().getForecast();
            for (int i = 0; i < 3; i++) {
                date[i] = historyList.get(i+4).getDate();
                week[i] = historyList.get(i+4).getWeek();
                type[i] = historyList.get(i+4).getType();
                temp[i] = historyList.get(i+4).getLowtemp() + "~" + historyList.get(i+4).getHightemp();
                fengxiang[i] = historyList.get(i+4).getFengxiang();
                fengli[i] = historyList.get(i+4).getFengli();
            }
            date[3] = status.getRetData().getToday().getDate();
            week[3] = status.getRetData().getToday().getWeek();
            type[3] = status.getRetData().getToday().getType();
            temp[3] = status.getRetData().getToday().getLowtemp() + "~" + status.getRetData().getToday().getHightemp();
            fengxiang[3] = status.getRetData().getToday().getFengxiang();
            fengli[3] = status.getRetData().getToday().getFengli();
            for (int i = 4; i < 7; i++) {
                date[i] = forecastList.get(i-4).getDate();
                week[i] = forecastList.get(i-4).getWeek();
                type[i] = forecastList.get(i-4).getType();
                temp[i] = forecastList.get(i-4).getLowtemp() + "~" + forecastList.get(i-4).getHightemp();
                fengxiang[i] = forecastList.get(i-4).getFengxiang();
                fengli[i] = forecastList.get(i-4).getFengli();
            }

        }

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 7; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("date", date[i]);
            listItem.put("week", week[i]);
            listItem.put("type", type[i]);
            listItem.put("temp", temp[i]);
            listItem.put("fengxiang", fengxiang[i]);
            listItem.put("fengli", fengli[i]);
        }
        simpleAdapter = new SimpleAdapter(context, listItems, R.layout.recentwidget,
                new String[]{"date", "week", "type", "temp", "fengxiang", "fengli"},
                new int[]{R.id.rdate, R.id.rweek, R.id.rtype, R.id.rtemp, R.id.rfengxiang, R.id.rfengli});

        listView.setAdapter(simpleAdapter);     */



   public void setToday(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String response = sharedPreferences.getString("allofdata", "");
        if (response.length() != 0) {
            System.out.println("response=" + response);
            Gson gson = new Gson();
            Status status = gson.fromJson(response, Status.class);
            List<History> historyList = status.getRetData().getHistory();
            List<Forecast> forecastList = status.getRetData().getForecast();
            for (int i = 0; i < 3; i++) {
                date[i] = historyList.get(i+4).getDate();
                week[i] = historyList.get(i+4).getWeek();
                type[i] = historyList.get(i+4).getType();
                temp[i] = historyList.get(i+4).getLowtemp() + "~" + historyList.get(i+4).getHightemp();
                fengxiang[i] = historyList.get(i+4).getFengxiang();
                fengli[i] = historyList.get(i+4).getFengli();
            }
            date[3] = status.getRetData().getToday().getDate();
            week[3] = status.getRetData().getToday().getWeek();
            type[3] = status.getRetData().getToday().getType();
            temp[3] = status.getRetData().getToday().getLowtemp() + "~" + status.getRetData().getToday().getHightemp();
            fengxiang[3] = status.getRetData().getToday().getFengxiang();
            fengli[3] = status.getRetData().getToday().getFengli();
            for (int i = 4; i < 7; i++) {
                date[i] = forecastList.get(i-4).getDate();
                week[i] = forecastList.get(i-4).getWeek();
                type[i] = forecastList.get(i-4).getType();
                temp[i] = forecastList.get(i-4).getLowtemp() + "~" + forecastList.get(i-4).getHightemp();
                fengxiang[i] = forecastList.get(i-4).getFengxiang();
                fengli[i] = forecastList.get(i-4).getFengli();
            }

        }
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 7; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("date", date[i]);
            listItem.put("week", week[i]);
            listItem.put("type", type[i]);
            listItem.put("temp", temp[i]);
            listItem.put("fengxiang", fengxiang[i]);
            listItem.put("fengli", fengli[i]);
        }
        simpleAdapter = new SimpleAdapter(context, listItems, R.layout.recentwidget,
                new String[]{"date", "week", "type", "temp", "fengxiang", "fengli"},
                new int[]{R.id.rdate, R.id.rweek, R.id.rtype, R.id.rtemp, R.id.rfengxiang, R.id.rfengli});

        listView.setAdapter(simpleAdapter);

    }

}

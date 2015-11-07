package database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.incredibleweather.MyApplication;
import com.google.gson.Gson;

import gson.Status;

/**
 * Created by Administrator on 2015/10/30.
 */
public class DataHandle {
    public static void saveData(Context context, String response) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("allofdata", response);
        editor.commit();


    }

    public static Status getgsonData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        String response = sharedPreferences.getString("allofdata", "");
        if (response.length() > 83) {  //长度小于83为错误数据
            Gson gson = new Gson();
            Status status = gson.fromJson(response, Status.class);
            return status;
        } else {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
            editor.putString("allofdata","");
            editor.commit(); //覆盖错误数据
            return null;
        }
    }
    public static void setCountycode(String countycode){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putString("countycode",countycode);
        editor.commit();
    }

    public static String getCountycode(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        String countycode = sharedPreferences.getString("countycode","");
        return countycode;
    }
}

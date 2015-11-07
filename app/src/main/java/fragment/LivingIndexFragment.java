package fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.incredibleweather.MainActivity;
import com.example.administrator.incredibleweather.MyApplication;
import com.example.administrator.incredibleweather.R;
import com.google.gson.Gson;

import database.DataHandle;
import gson.Status;


/**
 * Created by Administrator on 2015/10/29.
 */
public class LivingIndexFragment extends android.support.v4.app.Fragment {

    private View view;

    private TextView ganmao;
    private TextView fangshai;
    private TextView chuanyi;
    private TextView yundong;
    private TextView xiche;
    private TextView liangshai;
    private TextView ganmaodetail;
    private TextView fangshaidetail;
    private TextView chuanyidetail;
    private TextView yundongdetail;
    private TextView xichedetail;
    private TextView liangshaidetail;
    private TextView[] name = new TextView[]{ganmao, fangshai, chuanyi, yundong, xiche, liangshai};
    private TextView[] detail = new TextView[]{ganmaodetail, fangshaidetail, chuanyidetail, yundongdetail, xichedetail, liangshaidetail};
    private int[] nameid = new int[]{R.id.ganmao, R.id.fangshai, R.id.chuanyi, R.id.yundong, R.id.xiche, R.id.liangshai};
    private int[] detailid = new int[]{R.id.ganmaodetail, R.id.fangshaidetail, R.id.chuanyidetail, R.id.yundongdetail, R.id.xichedetail, R.id.liangshaidetail};

    private LocalBroadcastManager localBroadcastManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.livingindex, container, false);

        for (int i = 0; i < 6; i++) {
            name[i] = (TextView) view.findViewById(nameid[i]);
            detail[i] = (TextView) view.findViewById(detailid[i]);
        }

        return view;
    }

    public void setToday(int id) {  //设置id是因为不想Mainactivity的onWindowFocusChanged方法与query方法冲突
        Status status = DataHandle.getgsonData();
            if (status != null) {
                for (int i = 0; i < 6; i++) {
                    name[i].setText(status.getRetData().getToday().getIndex().get(i).getName() + "："
                            + status.getRetData().getToday().getIndex().get(i).getIndex());
                    detail[i].setText(status.getRetData().getToday().getIndex().get(i).getDetails());

                }
                if (id != 0) {
                    localBroadcastManager = LocalBroadcastManager.getInstance(MyApplication.getContext());
                    Intent intent = new Intent("加载成功！");
                    localBroadcastManager.sendBroadcast(intent);
                }
            }

    }
}

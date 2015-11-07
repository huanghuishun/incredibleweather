package widget;


import com.example.administrator.incredibleweather.R;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ChooseImages {

    private static int imagedrawable;

    public static int ChooseImagesDrawable(String weather){

        switch (weather){
            case "晴":
                imagedrawable = R.drawable.qing;
                return imagedrawable;
            case "多云":
                imagedrawable = R.drawable.duoyun;
                return imagedrawable;
            case "阴":
                imagedrawable = R.drawable.yin;
                return imagedrawable;
            case "阵雨":
                imagedrawable = R.drawable.zhenyu;
                return imagedrawable;
            case "雷阵雨":
                imagedrawable = R.drawable.leizhenyu;
                return imagedrawable;
            case "雷阵雨伴有冰雹":
                imagedrawable = R.drawable.leizhenyubanyoubingbao;
                return imagedrawable;
            case "雨夹雪":
                imagedrawable = R.drawable.yujiaxue;
                return imagedrawable;
            case "小雨":
                imagedrawable = R.drawable.xiaoyu;
                return imagedrawable;
            case "中雨":
                imagedrawable = R.drawable.zhongyu;
                return imagedrawable;
            case "大雨":
                imagedrawable = R.drawable.dayu;
                return imagedrawable;
            case "暴雨":
                imagedrawable = R.drawable.baoyu;
                return imagedrawable;
            case "大暴雨":
                imagedrawable = R.drawable.baoyu;
                return imagedrawable;
            case "特大暴雨":
                imagedrawable = R.drawable.baoyu;
                return imagedrawable;
            case "阵雪":
                imagedrawable = R.drawable.zhenxue;
                return imagedrawable;
            case "小雪":
                imagedrawable = R.drawable.xiaoxue;
                return imagedrawable;
            case "中雪":
                imagedrawable = R.drawable.zhongxue;
                return imagedrawable;
            case "大雪":
                imagedrawable = R.drawable.daxue;
                return imagedrawable;
            case "暴雪":
                imagedrawable = R.drawable.baoxue;
                return imagedrawable;
            case "雾":
                imagedrawable = R.drawable.wu;
                return imagedrawable;
            case "冻雨":
                imagedrawable = R.drawable.dongyu;
                return imagedrawable;
            case "沙尘暴":
                imagedrawable = R.drawable.shachenbao;
                return imagedrawable;
            case "浮尘":
                imagedrawable = R.drawable.fuchen;
                return imagedrawable;
            case "扬沙":
                imagedrawable = R.drawable.fuchen;
                return imagedrawable;
            case "强沙尘暴":
                imagedrawable = R.drawable.shachenbao;
                return imagedrawable;
            case "霾":
                imagedrawable = R.drawable.mai;
                return imagedrawable;
            default:
                imagedrawable = R.drawable.nothing;
                return imagedrawable;
        }
    }

}

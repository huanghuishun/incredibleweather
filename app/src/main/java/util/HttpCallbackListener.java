package util;

/**
 * Created by Administrator on 2015/10/20.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}

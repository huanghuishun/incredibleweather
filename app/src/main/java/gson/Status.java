package gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class Status {

    private String errNum;
    private String errMsg;
    private RetData retData;

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public RetData getRetData() {
        return retData;
    }

    public void setRetData(RetData retData) {
        this.retData = retData;
    }

    @Override
    public String toString(){
        return "Status [errNum=" + errNum +",errMsg=" + errMsg +",RetData=" + retData +"]";
    }
}

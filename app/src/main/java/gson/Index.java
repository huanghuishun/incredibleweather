package gson;

/**
 * Created by Administrator on 2015/10/28.
 */
public class Index {

    private String name;
    private String code;
    private String index;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString(){
        return "Index [name=" + name +",code=" + code +",index=" + index +",details=" +details +"]";
    }
}

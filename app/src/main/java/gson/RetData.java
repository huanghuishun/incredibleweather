package gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class RetData {

    private String city;
    private String cityid;
    private Today today;
    private List<Forecast> forecast;
    private List<History> history;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    @Override
    public String toString(){
        return "RetData [city=" + city +",cityid=" + cityid +",today=" + today +",forecast" + forecast +",history" + history + "]";
    }
}

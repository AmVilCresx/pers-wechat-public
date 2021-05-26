package pers.avc.wechat.core.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class WeatherInfoBo {

    @JSONField(deserialize = false)
    private String city;

    private String info;

    /**
     * 温度
     */
    private String temperature;

    /**
     * 湿度
     */
    private String humidity;

    /**
     * 风向
     */
    private String direct;

    /**
     * 风力
     */
    private String power;

    /**
     * 空气质量
     */
    private String aqi;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
}

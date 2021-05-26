package pers.avc.wechat.core.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.avc.wechat.common.constants.CommonConstants;
import pers.avc.wechat.common.constants.WeChatURLConstants;
import pers.avc.wechat.core.bo.WeatherInfoBo;

import java.util.Map;
import java.util.Objects;

/**
 * 第三方服务调用
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@Service
public class ThirdService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${juhe.weather.key}")
    private String weatherKey;

    /**
     * 随机返回一句古诗文
     * @return 古诗文
     */
    public String randomPoetry() {
        String respStr = restTemplate.getForObject(WeChatURLConstants.RANDOM_POETRY, String.class);
        log.info("请求随机一句古诗文返回结果:{}", respStr);
        JSONObject jsonObject = JSONObject.parseObject(respStr);
        if (!Objects.equals(jsonObject.getInteger(CommonConstants.RESPONSE_CODE_FIELD), HttpStatus.OK.value())) {
            log.warn("请求随机一句古诗文返回失败！");
            return null;
        }

        String jsonStr = jsonObject.getString("result");
        JSONObject resultObject = JSONObject.parseObject(jsonStr);
        String name = resultObject.getString("name");
        String from = resultObject.getString("from");
        return name + "  --" + from;
    }

    /**
     * 查詢城市的实时天气
     * @param cityName 城市名称
     * @return {@link WeatherInfoBo}
     */
    public String queryCityRealTimeWeather(String cityName) {
        String url = String.format(WeChatURLConstants.WEATHER_INFO, cityName, weatherKey);
        String weatherRespStr = restTemplate.getForObject(url, String.class);
        log.info("查询天气返回结果:{}", weatherRespStr);
        JSONObject jsonObject = JSONObject.parseObject(weatherRespStr);
        int errorCode = jsonObject.getIntValue("error_code");
        if (errorCode == 0) {
            log.info("查询天气成功！");
            JSONObject retObject = JSONObject.parseObject(jsonObject.getString("result"));
            String realTime = retObject.getString("realtime");
            log.info("实时天气信息cityName={}, info={}", cityName, realTime);
            WeatherInfoBo infoBo = JSONObject.parseObject(realTime, WeatherInfoBo.class);
            infoBo.setCity(cityName);
            return buildWeatherInfoStr(infoBo);
        }
        return null;
    }

    private String buildWeatherInfoStr(WeatherInfoBo infoBo) {
        StringBuilder strBuf = new StringBuilder();
        String newLine = CommonConstants.NEW_LINE;
        strBuf.append("城   市: ").append(infoBo.getCity()).append(newLine);
        strBuf.append("天   气: ").append(infoBo.getInfo()).append(newLine);
        strBuf.append("温   度: ").append(infoBo.getTemperature()).append(newLine);
        strBuf.append("湿   度: ").append(infoBo.getHumidity()).append(newLine);
        strBuf.append("风   向: ").append(infoBo.getDirect()).append(newLine);
        strBuf.append("风   力: ").append(infoBo.getPower()).append(newLine);
        strBuf.append("空气质量: ").append(infoBo.getAqi()).append(newLine);
        return strBuf.toString();
    }
}

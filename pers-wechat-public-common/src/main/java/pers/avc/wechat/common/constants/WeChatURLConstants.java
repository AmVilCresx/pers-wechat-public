package pers.avc.wechat.common.constants;

/**
 * 微信URL常量
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public interface WeChatURLConstants {

    /**
     * 获取accessToken
     */
    String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 创建菜单
     */
    String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    /**
     * 随机一句古诗
     */
    String RANDOM_POETRY =  "http://poetry.apiopen.top/sentences";

    /**
     * 聚合天气查询
     */
    String WEATHER_INFO = "http://apis.juhe.cn/simpleWeather/query?city=%s&key=%s";

    /**
     * 新增永久素材
     */
    String UPLOAD_FOREVER_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";

    /**
     * 删除素材
     */
    String DELTE_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";
}

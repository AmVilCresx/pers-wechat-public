package pers.avc.wechat.common.constants.button;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public enum ButtonKeyName {
    
    TODAY_MUSIC("TODAY_MUSIC", "今日歌曲"),

    RANDOM_POETRY("RANDOM_POETRY", "随机一句古诗文"),

    REAL_TIME_WEATHER("REAL_TIME_WEATHER", "天气查询")

    ;

    public final String name;

    public final String key;

    ButtonKeyName(String key, String name) {
        this.key = key;
        this.name = name;
    }
}

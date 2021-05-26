package pers.avc.wechat.core.event;

import org.apache.commons.lang3.StringUtils;

/**
 * 微信时间枚举
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public enum WeChatEventType {
    SUBSCRIBE, UNSUBSCRIBE, CLICK
    ;

    public static WeChatEventType matchByStr(String str) {
        WeChatEventType[] weChatEvents = WeChatEventType.values();
        for (WeChatEventType event : weChatEvents) {
            if (StringUtils.equalsIgnoreCase(event.toString(), str)) {
                return event;
            }
        }
        return null;
    }
}

package pers.avc.wechat.core.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理微信事件处理器
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class WeChatEventHandlerManager {

    private static final Map<WeChatEventType, WeChatEventHandler> HANDLERS = new ConcurrentHashMap<>();

    public static void registry(WeChatEventType chatEvent, WeChatEventHandler eventHandler) {
        HANDLERS.put(chatEvent, eventHandler);
    }

    public static WeChatEventHandler takeEventHandler(WeChatEventType chatEvent) {
        return HANDLERS.getOrDefault(chatEvent, null);
    }

}

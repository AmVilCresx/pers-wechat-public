package pers.avc.wechat.core.message;

import java.util.Objects;

/**
 * 信息消息类型
 *
 *  @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public enum WeChatMsgType {
    text, image, voice, video, shortvideo, location, link, event
    ;

    public static WeChatMsgType matchByStr(String resource) {
        WeChatMsgType[] msgTypes = WeChatMsgType.values();
        for (WeChatMsgType msgType : msgTypes) {
            if (Objects.equals(msgType.toString(), resource)){
                return msgType;
            }
        }
        return null;
    }
}

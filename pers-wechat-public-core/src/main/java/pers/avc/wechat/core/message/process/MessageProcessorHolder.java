package pers.avc.wechat.core.message.process;

import pers.avc.wechat.core.message.WeChatMsgType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消息处理器管理
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class MessageProcessorHolder {

    private static final List<MessageProcessor> PROCESSORS = new ArrayList<>();

    public static void addProcessor(MessageProcessor processor) {
        PROCESSORS.add(processor);
    }

    public static MessageProcessor getMsgProcess(WeChatMsgType msgType) {
        return PROCESSORS.stream()
                .filter(candidate -> candidate.support(msgType)).findFirst().orElse(null);
    }
}

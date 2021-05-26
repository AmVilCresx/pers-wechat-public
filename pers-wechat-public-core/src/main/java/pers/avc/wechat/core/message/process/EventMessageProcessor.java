package pers.avc.wechat.core.message.process;

import pers.avc.wechat.core.annotation.WeChatMessageProcessor;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;
import pers.avc.wechat.core.dto.req.MessageNecessaryReqDto;
import pers.avc.wechat.core.event.WeChatEventType;
import pers.avc.wechat.core.event.WeChatEventHandler;
import pers.avc.wechat.core.event.WeChatEventHandlerManager;
import pers.avc.wechat.core.message.MessageToBeanConvert;
import pers.avc.wechat.core.message.WeChatMsgType;

import java.util.Objects;

/**
 * 事件消息
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@WeChatMessageProcessor
public class EventMessageProcessor implements MessageProcessor{

    @Override
    public boolean support(WeChatMsgType msgType) {
        return Objects.equals(msgType, WeChatMsgType.event);
    }

    @Override
    public String process(MessageNecessaryReqDto msgDto) {
        EventMessageNecessaryReqDto necessaryReqDto = (EventMessageNecessaryReqDto) msgDto;
        String eventStr = necessaryReqDto.getEvent();
        WeChatEventHandler eventHandler = WeChatEventHandlerManager.takeEventHandler(WeChatEventType.matchByStr(eventStr));
        return eventHandler.handle(necessaryReqDto);
    }

    @Override
    public void registryConvert() {
        MessageToBeanConvert.addMapping(WeChatMsgType.event, EventMessageNecessaryReqDto.class);
    }
}

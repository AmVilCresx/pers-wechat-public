package pers.avc.wechat.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.avc.wechat.core.annotation.EventHandler;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;
import pers.avc.wechat.core.mapper.UserEventMapper;
import pers.avc.wechat.core.model.UserEvent;

import java.util.Date;

/**
 * 取消事件处理
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@EventHandler
public class UnSubscribeEventHandler implements WeChatEventHandler{

    @Autowired
    private UserEventMapper eventMapper;

    @Override
    public WeChatEventType getEventName() {
        return WeChatEventType.UNSUBSCRIBE;
    }

    @Override
    public String handle(EventMessageNecessaryReqDto necessaryReqDto) {
        log.info("{} 取消订阅了该公众号", necessaryReqDto.getFromUserName());
        UserEvent userEvent = new UserEvent();
        userEvent.setOpenId(necessaryReqDto.getFromUserName());
        userEvent.setEvent(WeChatEventType.UNSUBSCRIBE.toString());
        userEvent.setCreateTime(new Date());
        eventMapper.insertSelective(userEvent);
        return "";
    }
}

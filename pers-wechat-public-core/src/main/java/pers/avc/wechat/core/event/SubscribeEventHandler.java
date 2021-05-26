package pers.avc.wechat.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.avc.wechat.core.annotation.EventHandler;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;
import pers.avc.wechat.core.mapper.UserEventMapper;
import pers.avc.wechat.core.message.MessageHelper;
import pers.avc.wechat.core.model.UserEvent;
import pers.xml2bean.springboot.core.XmlUtils;

import java.util.Date;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@EventHandler
@Slf4j
public class SubscribeEventHandler implements WeChatEventHandler{

    @Autowired
    private UserEventMapper eventMapper;

    @Override
    public WeChatEventType getEventName() {
        return WeChatEventType.SUBSCRIBE;
    }

    @Override
    public String handle(EventMessageNecessaryReqDto necessaryReqDto) {
        log.info("{} 订阅了该公众号", necessaryReqDto.getFromUserName());
        UserEvent userEvent = new UserEvent();
        userEvent.setOpenId(necessaryReqDto.getFromUserName());
        userEvent.setEvent(WeChatEventType.SUBSCRIBE.toString());
        userEvent.setCreateTime(new Date());
        eventMapper.insertSelective(userEvent);
        return XmlUtils.bean2Xml(MessageHelper.defaultRespMsg(necessaryReqDto.getToUserName(), necessaryReqDto.getFromUserName()));
    }
}

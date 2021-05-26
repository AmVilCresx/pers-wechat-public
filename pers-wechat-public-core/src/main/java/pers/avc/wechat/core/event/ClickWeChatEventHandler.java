package pers.avc.wechat.core.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pers.avc.wechat.common.constants.button.ButtonKeyName;
import pers.avc.wechat.core.annotation.EventHandler;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;
import pers.avc.wechat.core.dto.resp.TextRespMessageDto;
import pers.avc.wechat.core.message.MessageHelper;
import pers.avc.wechat.core.message.process.TextMessageProcessor;
import pers.avc.wechat.core.service.ThirdService;
import pers.xml2bean.springboot.core.XmlUtils;

import java.util.Objects;

/**
 * 点击菜单按钮时间
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@EventHandler
public class ClickWeChatEventHandler implements WeChatEventHandler{

    @Autowired
    private ThirdService thirdService;

    @Override
    public WeChatEventType getEventName() {
        return WeChatEventType.CLICK;
    }

    @Override
    public String handle(EventMessageNecessaryReqDto reqDto) {
        String fromUser = reqDto.getFromUserName();
        String toUser = reqDto.getToUserName();
        String eventKey = reqDto.getEventKey();
        log.info("菜单点击事件: eventKey={}", eventKey);
        if (StringUtils.isBlank(eventKey)) {
            return XmlUtils.bean2Xml(MessageHelper.defaultRespMsg(toUser, fromUser));
        }
        if (Objects.equals(eventKey, ButtonKeyName.RANDOM_POETRY.key)) {
            String poetryStr = thirdService.randomPoetry();
            TextRespMessageDto respMessageDto = TextMessageProcessor.abstractMessageTemplate(toUser, fromUser);
            respMessageDto.setContent(poetryStr);
            return XmlUtils.bean2Xml(respMessageDto);
        } else if (Objects.equals(eventKey, ButtonKeyName.REAL_TIME_WEATHER.key)) {
            TextRespMessageDto respMessageDto = TextMessageProcessor.abstractMessageTemplate(toUser, fromUser);
            respMessageDto.setContent("请按照：【天气+城市】的格式输入...");
            return XmlUtils.bean2Xml(respMessageDto);
        }
        return XmlUtils.bean2Xml(MessageHelper.defaultRespMsg(toUser, fromUser));
    }
}

package pers.avc.wechat.core.message.process;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pers.avc.wechat.common.constants.CommonConstants;
import pers.avc.wechat.core.annotation.WeChatMessageProcessor;
import pers.avc.wechat.core.dto.req.MessageNecessaryReqDto;
import pers.avc.wechat.core.dto.req.TextMessageNecessaryReqDto;
import pers.avc.wechat.core.dto.resp.TextRespMessageDto;
import pers.avc.wechat.core.constants.MessageConstants;
import pers.avc.wechat.core.message.MessageHelper;
import pers.avc.wechat.core.message.MessageToBeanConvert;
import pers.avc.wechat.core.message.WeChatMsgType;
import pers.avc.wechat.core.service.ThirdService;
import pers.xml2bean.springboot.core.XmlUtils;

import java.util.Objects;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@WeChatMessageProcessor
public class TextMessageProcessor implements MessageProcessor {

    @Autowired
    private ThirdService thirdService;

    @Override
    public boolean support(WeChatMsgType msgType) {
        return Objects.equals(WeChatMsgType.text, msgType);
    }

    @Override
    public String process(MessageNecessaryReqDto msgDto) {
        TextMessageNecessaryReqDto messageDto = (TextMessageNecessaryReqDto)msgDto;
        String takeMsg = messageDto.getContent().trim();
        return responseMessage(takeMsg, messageDto);
    }

    @Override
    public void registryConvert() {
        MessageToBeanConvert.addMapping(WeChatMsgType.text, TextMessageNecessaryReqDto.class);
    }

    /**
     * 返回一个内容不全的抽象模板，提供外部使用
     * @return TextRespMessageDto
     */
    public static TextRespMessageDto abstractMessageTemplate(String fromUser, String toUser) {
        TextRespMessageDto respMessageDto = new TextRespMessageDto();
        respMessageDto.setFromUserName(fromUser);
        respMessageDto.setToUserName(toUser);
        respMessageDto.setCreateTime(System.currentTimeMillis());
        respMessageDto.setMsgType(WeChatMsgType.text.toString());
        return respMessageDto;
    }

    private String responseMessage(String takeMsg, TextMessageNecessaryReqDto reqMsgDto) {
        TextRespMessageDto defRespDto = MessageHelper.defaultRespMsg(reqMsgDto.getToUserName(), reqMsgDto.getFromUserName());
        if (ArrayUtils.contains(MessageConstants.HELP, takeMsg)) {
            return XmlUtils.bean2Xml(defRespDto);
        }

        String reqFromUser = reqMsgDto.getFromUserName();
        String reqToUser = reqMsgDto.getToUserName();
        if (Objects.equals(takeMsg, MessageConstants.ONE_STR)) {
            TextRespMessageDto messageDto = abstractMessageTemplate(reqToUser, reqFromUser);
            messageDto.setContent("君子之交淡如水,潘嘎之交掺了水!");
            return XmlUtils.bean2Xml(messageDto);
        }

        if (Objects.equals(takeMsg, MessageConstants.TWO_STR)) {
            TextRespMessageDto messageDto = abstractMessageTemplate(reqToUser, reqFromUser);
            messageDto.setContent("乌龟掉进王八窝----一路货色");
            return XmlUtils.bean2Xml(messageDto);
        }

        if (Objects.equals(takeMsg, MessageConstants.THREE_STR)) {
            TextRespMessageDto messageDto = abstractMessageTemplate(reqToUser, reqFromUser);
            messageDto.setContent("小偷翻窗,剌了裤裆----贼鸡儿损哪");
            return XmlUtils.bean2Xml(messageDto);
        }

        if (StringUtils.startsWithIgnoreCase(takeMsg, CommonConstants.WEATHER_QUERY_PREFIX)) {
            TextRespMessageDto messageDto = abstractMessageTemplate(reqToUser, reqFromUser);
            String[] msgArr = StringUtils.split(takeMsg, CommonConstants.PLUS);
            String content;
            if (msgArr.length != 2) {
                content = "查询天气输入的格式错误!";
            } else {
                String cityName = msgArr[1];
                content = thirdService.queryCityRealTimeWeather(cityName);
                if (StringUtils.isBlank(content)) {
                    content = "查询天气失败, 请稍后再试...";
                }
            }
            messageDto.setContent(content);
            return XmlUtils.bean2Xml(messageDto);
        }

        return XmlUtils.bean2Xml(defRespDto);
    }
}

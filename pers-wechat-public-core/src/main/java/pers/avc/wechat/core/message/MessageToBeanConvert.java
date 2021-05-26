package pers.avc.wechat.core.message;

import org.springframework.beans.BeanUtils;
import pers.avc.wechat.core.constants.MessageConstants;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;
import pers.avc.wechat.core.dto.req.MessageNecessaryReqDto;
import pers.avc.wechat.core.dto.req.TextMessageNecessaryReqDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class MessageToBeanConvert {

    public final static Map<WeChatMsgType, Class<? extends MessageNecessaryReqDto>> MAPPINGS = new HashMap<>();

    public static void addMapping(WeChatMsgType msgType, Class<? extends MessageNecessaryReqDto> clazz) {
        MAPPINGS.put(msgType, clazz);
    }

    public static MessageNecessaryReqDto convert(Map<String, Object> dataMap, WeChatMsgType weChatMsgType) {
        MessageNecessaryReqDto reqDto = convert(dataMap);
        Class<?> clazz = MAPPINGS.get(weChatMsgType);
        if (TextMessageNecessaryReqDto.class.isAssignableFrom(clazz)) {
            TextMessageNecessaryReqDto textReqDto = new TextMessageNecessaryReqDto();
            BeanUtils.copyProperties(reqDto, textReqDto);
            textReqDto.setContent(dataMap.get(MessageConstants.CONTENT_PROP).toString());
            return textReqDto;
        }

        if (EventMessageNecessaryReqDto.class.isAssignableFrom(clazz)) {
            EventMessageNecessaryReqDto necessaryReqDto = new EventMessageNecessaryReqDto();
            BeanUtils.copyProperties(reqDto, necessaryReqDto);
            necessaryReqDto.setEvent(dataMap.get(MessageConstants.EVENT_PROP).toString());
            if (dataMap.containsKey(MessageConstants.EVENT_KEY_PROP)) {
                necessaryReqDto.setEventKey(dataMap.get(MessageConstants.EVENT_KEY_PROP).toString());
            }
            return necessaryReqDto;
        }
        return reqDto;
    }

     public static MessageNecessaryReqDto convert(Map<String, Object> dataMap) {
         MessageNecessaryReqDto messageNecessaryReqDto = new MessageNecessaryReqDto();
         messageNecessaryReqDto.setFromUserName(dataMap.get(MessageConstants.FROM_USER_PROP).toString());
         messageNecessaryReqDto.setToUserName(dataMap.get(MessageConstants.TO_USER_PROP).toString());
         return messageNecessaryReqDto;
     }
}

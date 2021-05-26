package pers.avc.wechat.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.avc.wechat.common.util.SHA1Util;
import pers.avc.wechat.core.dto.req.MessageNecessaryReqDto;
import pers.avc.wechat.core.constants.MessageConstants;
import pers.avc.wechat.core.message.MessageHelper;
import pers.avc.wechat.core.message.MessageToBeanConvert;
import pers.avc.wechat.core.message.WeChatMsgType;
import pers.avc.wechat.core.message.process.MessageProcessor;
import pers.avc.wechat.core.message.process.MessageProcessorHolder;
import pers.xml2bean.springboot.core.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * 微信消息处理服务层
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Service
@Slf4j
public class WeChatCoreService {

    public String checkSignature(String messageToken, String signature, String timestamp, String nonce, String echoStr) {
        String[] str= {messageToken,timestamp,nonce};
        Arrays.sort(str);
        String line = str[0] + str[1] + str[2];
        String afterCheck = SHA1Util.encrypt(line);
        return afterCheck.equalsIgnoreCase(signature) ? echoStr : "";
    }

    /**
     * 处理微信服务器推送的消息
     * @param request HttpServletRequest
     * @return xml字符串消息
     */
    public String processRequest(HttpServletRequest request) {
        Map<String, Object> parseMap = MessageHelper.parseXmlToMap(request);
        String msgType = (String) parseMap.get(MessageConstants.MSG_TYPE_PROP);
        WeChatMsgType type = WeChatMsgType.matchByStr(msgType);
        if (Objects.isNull(type)) {
            log.warn("未找到对应的消息处理器: msgType={}", msgType);
            String reqFromUser = (String)parseMap.get(MessageConstants.FROM_USER_PROP);
            String reqToUser = (String)parseMap.get(MessageConstants.TO_USER_PROP);
            return XmlUtils.bean2Xml(MessageHelper.defaultRespMsg(reqToUser, reqFromUser));
        }
        log.info("微信消息类型: {}", type);
        MessageNecessaryReqDto necessaryReqDto = MessageToBeanConvert.convert(parseMap, type);
        MessageProcessor processor = MessageProcessorHolder.getMsgProcess(type);
        String respStr = processor.process(necessaryReqDto);
        log.info(respStr);
        return respStr;
    }
}

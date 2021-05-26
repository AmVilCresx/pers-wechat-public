package pers.avc.wechat.core.message.process;

import org.springframework.beans.factory.InitializingBean;
import pers.avc.wechat.core.dto.req.MessageNecessaryReqDto;
import pers.avc.wechat.core.message.WeChatMsgType;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public interface MessageProcessor extends InitializingBean {

    /**
     * 是否支持处理某种类型的消息
     * @param msgType {@link WeChatMsgType}
     * @return 断言结果
     */
    boolean support(WeChatMsgType msgType);

    /**
     * 消息处理
     * @return 处理结果
     */
    String process(MessageNecessaryReqDto msgDto);

    default void registryConvert() {

    }

    @Override
    default void afterPropertiesSet() throws Exception {
        registryConvert();
        MessageProcessorHolder.addProcessor(this);
    }
}

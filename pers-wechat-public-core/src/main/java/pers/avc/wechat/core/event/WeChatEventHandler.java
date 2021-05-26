package pers.avc.wechat.core.event;

import org.springframework.beans.factory.InitializingBean;
import pers.avc.wechat.core.dto.req.EventMessageNecessaryReqDto;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public interface WeChatEventHandler extends InitializingBean {

    /**
     * 钩子方法，由子类实现
     * @return 事件类型
     */
    WeChatEventType getEventName();

    /**
     * 具体的处理逻辑， 由具体子类实现
     * @return 处理结果
     */
    String handle(EventMessageNecessaryReqDto eventMessageNecessaryReqDto);

    /**
     * 将事件处理器放入到 Manager 统一管理
     * @throws Exception Exception
     */
    @Override
    default void afterPropertiesSet() throws Exception {
        WeChatEventHandlerManager.registry(getEventName(), this);
    }
}

package pers.avc.wechat.core.dto.req;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import pers.xml2bean.springboot.core.CDATAConvert;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class TextMessageNecessaryReqDto extends MessageNecessaryReqDto{
    @XStreamAlias("Content")
    @XStreamConverter(CDATAConvert.class)
    private String content;

    @XStreamAlias("MsgId")
    private Long msgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}

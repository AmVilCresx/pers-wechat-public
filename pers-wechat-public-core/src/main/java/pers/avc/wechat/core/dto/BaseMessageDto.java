package pers.avc.wechat.core.dto;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import pers.xml2bean.springboot.core.CDATAConvert;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class BaseMessageDto {

    @XStreamAlias("ToUserName")
    @XStreamConverter(CDATAConvert.class)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(CDATAConvert.class)
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(CDATAConvert.class)
    private String msgType;

    public BaseMessageDto() {
    }

    public BaseMessageDto(String toUser, String fromUser, Long createTime, String msgType, Long msgId) {
        this.toUserName = toUser;
        this.fromUserName = fromUser;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}

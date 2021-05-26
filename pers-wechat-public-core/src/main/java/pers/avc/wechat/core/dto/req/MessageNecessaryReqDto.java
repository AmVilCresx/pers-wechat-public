package pers.avc.wechat.core.dto.req;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class MessageNecessaryReqDto {

    private String fromUserName;

    private String toUserName;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}

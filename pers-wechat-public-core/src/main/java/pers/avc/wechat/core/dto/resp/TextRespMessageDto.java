package pers.avc.wechat.core.dto.resp;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import pers.avc.wechat.core.dto.BaseMessageDto;
import pers.xml2bean.springboot.core.CDATAConvert;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@XStreamAlias("xml")
public class TextRespMessageDto extends BaseMessageDto {
    @XStreamAlias("Content")
    @XStreamConverter(CDATAConvert.class)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package pers.avc.wechat.core.message;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pers.avc.wechat.core.dto.resp.TextRespMessageDto;
import pers.xml2bean.springboot.core.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
public class MessageHelper {

    /**
     * 解析 微信的 xml 消息体
     * @param request HttpServletRequest
     * @return 解析结果
     */
    public static Map<String, Object> parseXmlToMap(HttpServletRequest request) {
        try (InputStream input = request.getInputStream()){
            SAXReader reader = new SAXReader();
            Document doc = reader.read(input);
            Map<String, Object> retMap = new HashMap<>();
            Element root = doc.getRootElement();
            XmlUtils.xml2Map(root, retMap);
            return retMap;
        }catch (Exception e) {
            log.error("解析xml消息发生异常:", e);
        }
        return null;
    }

    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("大佬，请按照菜单提示操作：\n\n");
        sb.append("1.社交小技巧\n");
        sb.append("2.一丘之貉？\n");
        sb.append("3.夺笋\n\n");
        sb.append("回复?调出此菜单。");
        return sb.toString();
    }

    public static TextRespMessageDto defaultRespMsg(String fromUser, String toUser) {
        TextRespMessageDto msg = new TextRespMessageDto();
        msg.setFromUserName(fromUser);
        msg.setToUserName(toUser);
        msg.setCreateTime(System.currentTimeMillis());
        msg.setMsgType(WeChatMsgType.text.toString());
        msg.setContent(menuText());
        return msg;
    }
}

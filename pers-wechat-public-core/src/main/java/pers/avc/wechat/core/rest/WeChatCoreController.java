package pers.avc.wechat.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pers.avc.wechat.core.service.WeChatCoreService;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信服务器访问接口
 *
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@RestController
@RequestMapping("/wechat")
public class WeChatCoreController {

    @Value("${wechat.message.token:}")
    private String messageToken;

    @Autowired
    private WeChatCoreService weChatCoreService;

    /**
     * 用于开发者和微信服务器验签
     */
    @GetMapping
    public String callbackForSign(String signature, String timestamp, String nonce, @RequestParam(value = "echostr") String echoStr) {
        return weChatCoreService.checkSignature(messageToken, signature, timestamp, nonce, echoStr);
    }

    /**
     * 接收微信消息
     */
    @PostMapping
    public String processMsg(HttpServletRequest request) {
        return weChatCoreService.processRequest(request);
    }
}

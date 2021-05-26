package pers.avc.wechat.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.avc.wechat.admin.service.AccessTokenService;
import pers.avc.wechat.admin.service.MenuService;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@RestController
@RequestMapping("/we")
public class WeChatAdminController {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/access_token")
    public String token(){
        return accessTokenService.getAccessToken();
    }

    @PostMapping("/create_menu")
    public String creatMenu() {
        return menuService.createMenu();
    }
}

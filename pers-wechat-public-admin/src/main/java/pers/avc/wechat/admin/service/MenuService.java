package pers.avc.wechat.admin.service;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.avc.wechat.common.constants.WeChatURLConstants;
import pers.avc.wechat.common.constants.button.ButtonKeyName;
import pers.avc.wechat.common.constants.button.ButtonType;
import pers.avc.wechat.common.menu.MenuEntity;
import pers.avc.wechat.common.menu.button.ComplexButton;
import pers.avc.wechat.common.menu.button.SimpleButton;

import java.util.Collections;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@Service
public class MenuService {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RestTemplate restTemplate;

    @SneakyThrows
    public String createMenu() {
        MenuEntity entity = new MenuEntity();

        SimpleButton button = new SimpleButton();
        button.setType(ButtonType.CLICK.lowerCaseValue());
        button.setKey(ButtonKeyName.REAL_TIME_WEATHER.key);
        button.setName(ButtonKeyName.REAL_TIME_WEATHER.name);

        // 复合菜单
        ComplexButton complexButton = new ComplexButton();
        complexButton.setName("日常");
        SimpleButton randPox = SimpleButton.createBtn(ButtonType.CLICK, ButtonKeyName.RANDOM_POETRY);
        complexButton.setSubButton(Collections.singletonList(randPox));

        entity.addButton(button).addButton(complexButton);

        String accessToken = accessTokenService.getAccessToken();
        String requestData = JSONObject.toJSONString(entity);

        String url = String.format(WeChatURLConstants.CREATE_MENU, accessToken);
        log.info("url="+url);
        log.info("请求数据：{}", requestData);
        return restTemplate.postForObject(url, requestData, String.class);
    }
}

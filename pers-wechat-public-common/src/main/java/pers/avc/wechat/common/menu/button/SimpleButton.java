package pers.avc.wechat.common.menu.button;

import pers.avc.wechat.common.constants.button.ButtonKeyName;
import pers.avc.wechat.common.constants.button.ButtonType;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class SimpleButton extends BaseMenuButton {

    private String type;

    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SimpleButton() {
    }

    public static SimpleButton createBtn(ButtonType type, ButtonKeyName keyName) {
        SimpleButton button = new SimpleButton();
        button.setName(keyName.name);
        button.setKey(keyName.key);
        button.setType(type.lowerCaseValue());
        return button;
    }
}

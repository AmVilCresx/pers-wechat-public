package pers.avc.wechat.common.menu;

import org.springframework.util.CollectionUtils;
import pers.avc.wechat.common.menu.button.BaseMenuButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class MenuEntity {

    private List<BaseMenuButton> button;

    public List<BaseMenuButton> getButton() {
        return button;
    }

    public void setButton(List<BaseMenuButton> button) {
        this.button = button;
    }

    public MenuEntity addButton(BaseMenuButton menuButton) {
        if (CollectionUtils.isEmpty(button)) {
            button = new ArrayList<>();
        }
        button.add(menuButton);
        return this;
    }
}

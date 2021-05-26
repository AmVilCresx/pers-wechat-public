package pers.avc.wechat.common.menu.button;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class ComplexButton extends BaseMenuButton{

    @JSONField(name = "sub_button")
    private List<BaseMenuButton> subButton;

    public List<BaseMenuButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<BaseMenuButton> subButton) {
        this.subButton = subButton;
    }
}

package pers.avc.wechat.common.constants.button;

/**
 * 按鈕类型
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 *
 * 参考说明： <a href="https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html"></a>
 */
public enum ButtonType {
    CLICK,
    VIEW,
    SCANCODE_PUSH,
    SCANCODE_WAITMSG,
    PIC_SYSPHOTO,
    PIC_PHOTO_OR_ALBUM,
    PIC_WEIXIN,
    LOCATION_SELECT,
    MEDIA_ID,
    VIEW_LIMITED
    ;

    public String lowerCaseValue() {
        return this.toString().toLowerCase();
    }
}

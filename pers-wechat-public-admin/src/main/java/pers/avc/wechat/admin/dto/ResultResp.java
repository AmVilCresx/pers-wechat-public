package pers.avc.wechat.admin.dto;

import pers.avc.wechat.admin.contants.WeChatAdminErrorCode;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class ResultResp<T> {

    private T data;

    private String msg;

    private int code;

    public ResultResp(int code, String msg, T data) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public ResultResp() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static <C> ResultResp<C> ok(C c) {
        return new ResultResp<>(WeChatAdminErrorCode.SUCCESS, "success", c);
    }

    public static ResultResp<?> error(String msg) {
        return new ResultResp<>(WeChatAdminErrorCode.FAILED, msg, null);
    }
}

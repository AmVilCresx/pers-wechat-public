package pers.avc.wechat.admin.model;

import pers.avc.wechat.admin.contants.MediaType;

import java.util.Date;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public class MediaInfo {

    /**
     * 主键Id
     * */
    private Integer id;

    /**
     * 媒体类型
     * 转换器 {@link MediaType}
     * */
    private MediaType type;

    /**
     * 微信服务器返回的media_id
     * */
    private String mediaId;

    /**
     * 如果上传的媒体类型是图片，则返回的json中有该字段
     * */
    private String url;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
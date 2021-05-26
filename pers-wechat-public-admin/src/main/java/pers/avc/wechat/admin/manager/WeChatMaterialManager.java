package pers.avc.wechat.admin.manager;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pers.avc.wechat.admin.contants.WeChatAdminErrorCode;
import pers.avc.wechat.admin.contants.MediaType;
import pers.avc.wechat.admin.dto.ResultResp;
import pers.avc.wechat.admin.mapper.MediaInfoMapper;
import pers.avc.wechat.admin.model.MediaInfo;
import pers.avc.wechat.admin.service.AccessTokenService;
import pers.avc.wechat.common.constants.CommonConstants;
import pers.avc.wechat.common.constants.WeChatURLConstants;
import pers.avc.wechat.common.util.MultipartFileToFileUtil;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@Component
public class WeChatMaterialManager {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MediaInfoMapper mediaInfoMapper;

    public ResultResp<MediaInfo> uploadMedia(MultipartFile multipartFile, String title, String type, String introduction) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

        if (Objects.equals(type, MediaType.VIDEO.getName())) {
            if (!StringUtils.hasText(title) || !StringUtils.hasText(introduction)) {
                return new ResultResp<>(WeChatAdminErrorCode.PARAM_ERROR, "视频类型的title和introduction不可为空", null);
            }
            JSONObject object = new JSONObject();
            object.put("title", title);
            object.put("introduction", introduction);
            data.add("description", object.toJSONString());
        }

        File targetFile = MultipartFileToFileUtil.multipartFileToFile(multipartFile);
        MediaInfo mediaInfo;
        try{
            WritableResource resource = new FileSystemResource(targetFile);
            data.add("media", resource);
            String url = String.format(WeChatURLConstants.UPLOAD_FOREVER_MATERIAL, accessTokenService.getAccessToken(), type);
            String respStr = restTemplate.postForObject(url, data, String.class);
            log.info("上传素材返回的结果:{}", respStr);
            mediaInfo = insertRecordWhenSuccess(type, respStr);
        }finally {
            if (Objects.nonNull(targetFile) && targetFile.exists()) {
                MultipartFileToFileUtil.deleteTempFile(targetFile);
            }
        }

        return ResultResp.ok(mediaInfo);
    }

    private MediaInfo insertRecordWhenSuccess(String mediaType, String respStr) {
        JSONObject respObject = JSONObject.parseObject(respStr);
        Integer errCode = respObject.getInteger("errcode");
        // 上传成功， 不会返回错误码字段
        if (Objects.isNull(errCode)) {
            MediaInfo mediaInfo = new MediaInfo();
            String mediaId = respObject.getString("media_id");
            mediaInfo.setMediaId(mediaId);
            mediaInfo.setType(MediaType.getTypeByName(mediaType));
            mediaInfo.setCreateTime(new Date());
            String url = respObject.getString("url");
            if (StringUtils.hasText(url)) {
                mediaInfo.setUrl(url);
            }
            mediaInfoMapper.insertSelective(mediaInfo);
            return mediaInfo;
        }
        return null;
    }

    public ResultResp<?> deleteByMediaId(String mediaId) {
        MediaInfo mediaInfo = mediaInfoMapper.selectByMediaId(mediaId);
        if (Objects.isNull(mediaInfo)) {
            return ResultResp.error("该素材不存在");
        }

        String delUrl = String.format(WeChatURLConstants.DELTE_MATERIAL, accessTokenService.getAccessToken());
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("media_id", mediaId);
        String delRespStr = restTemplate.postForObject(delUrl, jsonBody, String.class);
        log.info("删除素材返回结果:{}", delRespStr);

        JSONObject respObj = JSONObject.parseObject(delRespStr);
        Integer errCode = respObj.getInteger(CommonConstants.WECHAT_RESP_ERRCODE);
        if (Objects.nonNull(errCode) && errCode == 0) {
            log.info("微信返回删除结果:成功!");
            mediaInfoMapper.delteByMediaId(mediaId);
        }
        return ResultResp.ok("ok");
    }
}

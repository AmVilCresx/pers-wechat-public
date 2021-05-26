package pers.avc.wechat.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.avc.wechat.admin.dto.ResultResp;
import pers.avc.wechat.admin.manager.WeChatMaterialManager;
import pers.avc.wechat.admin.validation.WeChatMediaTypeValidate;

import javax.validation.constraints.NotBlank;


/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@RestController
@RequestMapping("/material")
@Validated
public class WeChatMaterialController {

    @Autowired
    private WeChatMaterialManager weChatMaterialManager;

    @PostMapping
    public ResultResp<?> uploadForever(@RequestParam("file") MultipartFile multipartFile,
                                       @Validated @NotBlank(message = "title不可为空") String title,
                                       @Validated @WeChatMediaTypeValidate String mediaType,
                                       String introduction) {
        return weChatMaterialManager.uploadMedia(multipartFile, title, mediaType, introduction);
    }

    /**
     * 根据媒体id删除
     * @param mediaId 媒体Id
     * @return ResultResp
     */
    @DeleteMapping("/delete/{media_id}")
    public ResultResp<?> delMaterial(@PathVariable("media_id") String mediaId) {
        return weChatMaterialManager.deleteByMediaId(mediaId);
    }
}

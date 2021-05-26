package pers.avc.wechat.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.avc.wechat.admin.contants.MediaType;
import pers.avc.wechat.admin.model.MediaInfo;

import java.util.List;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Repository
public interface MediaInfoMapper {

    List<MediaInfo> selectByType(@Param("type") MediaType type);

    int insertSelective(MediaInfo record);

    int delteByMediaId(@Param("mediaId")String mediaId);
}

package pers.avc.wechat.core.mapper;

import org.springframework.stereotype.Repository;
import pers.avc.wechat.core.model.UserEvent;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Repository
public interface UserEventMapper {
    int insertSelective(UserEvent record);
}

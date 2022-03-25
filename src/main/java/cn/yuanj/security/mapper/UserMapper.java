package cn.yuanj.security.mapper;

import cn.yuanj.security.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YuanJ
 * @date 2022/3/19 11:41
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * sda
     *
     * @param userId
     * @return
     */
    List<String> getPrimaryPrincipal(@Param("userId") Long userId);
}

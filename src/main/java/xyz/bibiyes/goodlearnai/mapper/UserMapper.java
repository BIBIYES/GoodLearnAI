package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.bibiyes.goodlearnai.entity.User;

/**
 * @author Mouse Sakura
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

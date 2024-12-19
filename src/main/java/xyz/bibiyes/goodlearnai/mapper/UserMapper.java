package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.bibiyes.goodlearnai.entity.User;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.StudentWrongQuestionVO;

import java.util.List;

/**
 * @author Mouse Sakura
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> getStudentAll(String name);
    List<StudentWrongQuestionVO> getStudentWrongQuestionView(Long userId);
}

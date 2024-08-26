package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

/**
 * @author Mouse Sakura
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    // 模糊查询问题
    @Select("SELECT * FROM questions WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<Question> findByTitle(@Param("title") String title);
}

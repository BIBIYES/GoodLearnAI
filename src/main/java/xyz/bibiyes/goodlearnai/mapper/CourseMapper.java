package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.Course;
import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据用户id查询课程
     * @param userId 用户id
     * @return 课程列表
     */
    @Select("SELECT * FROM course WHERE user_id = #{userId}")
    List<Course> selectCoursesByUserId(@Param("userId") Long userId);


}

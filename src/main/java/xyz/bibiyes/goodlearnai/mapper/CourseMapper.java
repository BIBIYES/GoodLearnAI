package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.Course;
import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    // 自定义 SQL 查询，通过教师 ID 获取课程
    @Select("SELECT * FROM course WHERE teacher_id = #{teacherId}")
    List<Course> getCoursesByTeacherId(@Param("teacherId") Long teacherId);


}

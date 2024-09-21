package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;

import java.util.List;

public interface ExamPaperMapper extends BaseMapper<ExamPaper> {

    @Select("SELECT * FROM exam_paper WHERE teacher_id = #{teacherId}")
    List<ExamPaper> getExamPapersByTeacherId(@Param("teacherId") Long teacherId);
}

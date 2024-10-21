package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;

import java.util.List;

public interface ExamPaperMapper extends BaseMapper<ExamPaper> {

    @Select("SELECT * FROM exam_paper WHERE user_id = #{user_id}")
    List<ExamPaper> selectExamPaperByUserId(@Param("user_id") Long user_id);
}

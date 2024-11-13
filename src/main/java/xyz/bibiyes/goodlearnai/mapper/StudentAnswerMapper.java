package xyz.bibiyes.goodlearnai.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.bibiyes.goodlearnai.entity.StudentAnswer;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.ExamPaperStudentAnswer;

import java.util.List;

@Mapper
public interface StudentAnswerMapper extends BaseMapper<StudentAnswer> {

    List<ExamPaperStudentAnswer> getExamPaperStudentAnswer(@Param("examPaperId") Long examPaperId, @Param("userId") Long userId);
}

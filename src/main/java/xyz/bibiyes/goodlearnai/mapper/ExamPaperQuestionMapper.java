package xyz.bibiyes.goodlearnai.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.bibiyes.goodlearnai.entity.ExamPaperQuestion;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

@Mapper
public interface ExamPaperQuestionMapper {

    // 获取试卷中的题目
    List<Question> getQuestionsByExamPaperId(@Param("examPaperId") Integer examPaperId);

    // 获取课程中的题目（不在当前试卷中的）
    List<Question> getAvailableQuestions(@Param("courseId") Integer courseId, @Param("examPaperId") Integer examPaperId);

    // 添加题目到试卷
    int insert(ExamPaperQuestion examPaperQuestion);

    // 删除试卷中的题目
    @Delete("DELETE FROM exam_paper_question WHERE exam_paper_id = #{examPaperId} AND question_id = #{questionId}")
    int deleteByExamPaperAndQuestion(@Param("examPaperId") Integer examPaperId, @Param("questionId") Integer questionId);
}

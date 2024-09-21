package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.entity.ExamPaperQuestion;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

@Mapper
public interface ExamPaperQuestionMapper {

    /**
     * 根据试卷ID获取关联的题目列表
     */
    @Select("SELECT q.* FROM exam_paper_question epq " +
            "JOIN question q ON epq.question_id = q.question_id " +
            "WHERE epq.exam_paper_id = #{examPaperId}")
    List<Question> selectQuestionsByPaperId(Long examPaperId);

    /**
     * 删除试卷ID的所有关联
     */
    void deleteByPaperId(Long paperId);

    /**
     * 删除试卷中某个题目的关联
     */
    boolean deleteByPaperIdAndQuestionId(@Param("paperId") Long paperId, @Param("questionId") Long questionId);

    /**
     * 插入新的试卷-题目关联
     */
    void insert(@Param("paperId") Long paperId, @Param("questionId") Long questionId);
}

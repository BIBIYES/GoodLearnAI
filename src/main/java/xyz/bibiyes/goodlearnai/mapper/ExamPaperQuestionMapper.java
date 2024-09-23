package xyz.bibiyes.goodlearnai.mapper;


import org.apache.ibatis.annotations.*;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;
import java.util.Map;

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
     * 删除试卷中某个题目的关联
     */
    boolean deleteByPaperIdAndQuestionId(@Param("paperId") Long paperId, @Param("questionId") Long questionId);

    /**
     * 插入新的试卷-题目关联
     */
    @Insert("INSERT INTO exam_paper_question (exam_paper_id, question_id) VALUES (#{paperId}, #{questionId})")
    void insert(@Param("paperId") Long paperId, @Param("questionId") Long questionId);

    /**
     * 删除试卷ID的所有关联
     */
    @Delete("DELETE FROM exam_paper_question WHERE exam_paper_id = #{paperId}")
    int deleteByPaperId(Long paperId) ;


}

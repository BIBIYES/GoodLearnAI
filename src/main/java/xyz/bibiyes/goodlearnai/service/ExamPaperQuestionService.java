package xyz.bibiyes.goodlearnai.service;


import xyz.bibiyes.goodlearnai.entity.Question;


import java.util.List;
import java.util.Map;

public interface ExamPaperQuestionService {

    /**
     * 根据试卷ID获取关联的题目列表
     * @param paperId 试卷ID
     * @return 关联的题目列表
     */
    List<Question> getQuestionsByPaperId(Long paperId);

    

    /**
     * 更新试卷的题目关联
     * @param paperId 试卷ID
     * @param questionIds 题目ID列表
     * @return 更新是否成功
     */
    boolean updateExamPaperQuestions(Long paperId, List<Long> questionIds);

    /**
     * 从试卷中删除某个题目的关联
     * @param paperId 试卷ID
     * @param questionId 题目ID
     * @return 删除是否成功
     */
    boolean removeQuestionFromPaper(Long paperId, Long questionId);
}

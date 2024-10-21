package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.mapper.ExamPaperQuestionMapper;
import xyz.bibiyes.goodlearnai.service.ExamPaperQuestionService;

import java.util.List;


@Service
public class ExamPaperQuestionServiceImpl implements ExamPaperQuestionService {

    @Autowired
    private ExamPaperQuestionMapper examPaperQuestionMapper;

    /**
     * 根据试卷ID获取关联的题目列表
     */
    @Override
    public List<Question> getQuestionsByPaperId(Long paperId) {
        return examPaperQuestionMapper.selectQuestionsByPaperId(paperId);
    }

    /**
     * 更新试卷的题目关联
     */
    @Override
    public boolean updateExamPaperQuestions(Long paperId, List<Long> questionIds) {
        // 先删除原有的关联
        int rowsDeleted = examPaperQuestionMapper.deleteByPaperId(paperId);
        // 插入新的关联
        int flag = 0;
        if (!questionIds.isEmpty()) {
            for (Long questionId : questionIds) {
                examPaperQuestionMapper.insert(paperId, questionId);
                flag += 1;
            }
        }
        return rowsDeleted > 0 || flag > 0;

    }

    /**
     * 从试卷中删除某个题目的关联
     */
    @Override
    public boolean removeQuestionFromPaper(Long paperId, Long questionId) {
        return examPaperQuestionMapper.deleteByPaperIdAndQuestionId(paperId, questionId);
    }
}

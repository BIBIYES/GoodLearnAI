package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

public interface QuestionService {
    Result getQuestionsByCourseId(Long courseId, int pageNum, int pageSize);
    boolean deleteQuestionById(Long questionId);
    boolean saveQuestion(Question question);  // 添加保存题目的方法
    boolean updateQuestion(Question question); // 添加更新题目的方法
    List<Question> getAllQuestions();

    Question getQuestionById(Long questionId);

    Result saveQuestions(List<Question> questions, Integer courseId);

    Result searchQuestions(Long courseId, String keyword);
}

package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsByCourseId(Long courseId);
    boolean deleteQuestionById(Long questionId);
    boolean saveQuestion(Question question);  // 添加保存题目的方法
    boolean updateQuestion(Question question); // 添加更新题目的方法
    List<Question> getAllQuestions();

    Question getQuestionById(Long questionId);
}

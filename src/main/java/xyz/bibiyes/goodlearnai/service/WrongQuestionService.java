package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;
import java.util.List;

public interface WrongQuestionService {
    List<WrongQuestionVO> getWrongQuestions(Long userId, String examPaperName, String questionTitle);
} 
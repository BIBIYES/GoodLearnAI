package xyz.bibiyes.goodlearnai.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WrongQuestionVO {
    private Long wrongQuestionsId;
    private String examPaperName;    // 试卷名称
    private String questionTitle;     // 题目标题
    private String questionContent;   // 题目内容
    private String wrongAnswer;       // 错误答案
    private String aiAnswer;         // AI答案
    private Long questionId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 创建时间
} 
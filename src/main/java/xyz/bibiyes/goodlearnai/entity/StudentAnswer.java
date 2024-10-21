package xyz.bibiyes.goodlearnai.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("student_answer")
public class StudentAnswer {
    private Long answerId;
    private Long userId;
    private Long examPaperId;
    private Long examPaperQuestionId;
    private String answerContent;
    private String answerTime;
    private String aiAnswer;
}

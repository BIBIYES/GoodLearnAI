package xyz.bibiyes.goodlearnai.vo;

import lombok.Data;

@Data
public class StudentWrongQuestionVO {
    private Long userId;
    private String username;
    private Long courseId;
    private String courseName;
    private Long examPaperId;
    private String examPaperName;
    private Integer wrongQuestionsCount;
    private String wrongQuestionTitles;


}
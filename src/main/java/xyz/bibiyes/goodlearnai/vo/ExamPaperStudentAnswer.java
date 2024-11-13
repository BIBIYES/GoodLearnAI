package xyz.bibiyes.goodlearnai.vo;

import lombok.Data;

@Data
public class ExamPaperStudentAnswer {
    private String username;
    private String examPaperName;
    private String questionTitle;
    private String questionContent;
    private String answerContent;
    private String aiAnswer;
}

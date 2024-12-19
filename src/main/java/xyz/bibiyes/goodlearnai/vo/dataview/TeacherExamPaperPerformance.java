package xyz.bibiyes.goodlearnai.vo.dataview;

import lombok.Data;

@Data
public class TeacherExamPaperPerformance {
    private Long examPaperId;
    private String examPaperName;
    private String examPaperCreatedDate;
    private String courseName;
    private Integer totalWrongQuestions;
    private Integer studentsWithWrongAnswers;
    private Double wrongQuestionRate;
}

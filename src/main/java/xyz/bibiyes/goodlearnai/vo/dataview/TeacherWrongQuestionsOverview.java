package xyz.bibiyes.goodlearnai.vo.dataview;


import lombok.Data;

@Data
public class TeacherWrongQuestionsOverview {
    private Long userId;
    private String username;
    private Integer totalWrongQuestions;
    private Integer coursesWithWrongQuestions;
    private String latestWrongQuestionTime;
}

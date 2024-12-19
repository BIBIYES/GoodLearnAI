package xyz.bibiyes.goodlearnai.vo.dataview;


import lombok.Data;

@Data
public class TeacherCourseDifficultyAnalysis {
    private Long courseId;
    private String courseName;
    private Integer totalWrongQuestions;
    private Integer studentsWithWrongAnswers;
    private Double avgWrongQuestionsPerStudent;
}

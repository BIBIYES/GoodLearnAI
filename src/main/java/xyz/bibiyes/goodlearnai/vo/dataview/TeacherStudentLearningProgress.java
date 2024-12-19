package xyz.bibiyes.goodlearnai.vo.dataview;
import lombok.Data;
@Data
public class TeacherStudentLearningProgress {
    private Long userId;
    private String username;
    private Integer totalExamsAttempted;
    private Integer totalWrongQuestions;
    private Integer recentWrongQuestions;
    private Double avgWrongQuestionsPerExam;
}

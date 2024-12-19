package xyz.bibiyes.goodlearnai.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherCourseDifficultyAnalysis;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherExamPaperPerformance;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherStudentLearningProgress;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherWrongQuestionsOverview;
import java.util.List;

/**
 * 老师端的数据面板Mapper接口
 */
@Mapper
public interface TeacherViewMapper {
    @Select("SELECT * FROM teacher_wrong_questions_overview")
    List<TeacherWrongQuestionsOverview> getTeacherWrongQuestionsOverview();

    @Select("SELECT * FROM teacher_course_difficulty_analysis")
    List<TeacherCourseDifficultyAnalysis> getTeacherCourseDifficultyAnalysis();

    @Select("SELECT * FROM teacher_exam_paper_performance")
    List<TeacherExamPaperPerformance> getTeacherExamPaperPerformance();

    @Select("SELECT * FROM teacher_student_learning_progress")
    List<TeacherStudentLearningProgress> getTeacherStudentLearningProgress();
}

package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.service.ITeacherViewService;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherCourseDifficultyAnalysis;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherExamPaperPerformance;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherStudentLearningProgress;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherWrongQuestionsOverview;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherViewController {

    @Autowired
    private ITeacherViewService iTeacherViewService;

    @GetMapping("/wrong-questions-overview")
    public List<TeacherWrongQuestionsOverview> getTeacherWrongQuestionsOverview() {
        return iTeacherViewService.getTeacherWrongQuestionsOverview();
    }
    @GetMapping("/course-difficulty-analysis")
    public List<TeacherCourseDifficultyAnalysis> getTeacherCourseDifficultyAnalysis() {
        return iTeacherViewService.getTeacherCourseDifficultyAnalysis();
    }

    @GetMapping("/exam-paper-performance")
    public List<TeacherExamPaperPerformance> getTeacherExamPaperPerformance() {
        return iTeacherViewService.getTeacherExamPaperPerformance();
    }

    @GetMapping("/student-learning-progress")
    public List<TeacherStudentLearningProgress> getTeacherStudentLearningProgress() {
        return iTeacherViewService.getTeacherStudentLearningProgress();
    }
}

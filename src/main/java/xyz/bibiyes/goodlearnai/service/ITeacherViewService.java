package xyz.bibiyes.goodlearnai.service;

/**
 * 老师端数据面板接口
 */


import xyz.bibiyes.goodlearnai.vo.dataview.TeacherCourseDifficultyAnalysis;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherExamPaperPerformance;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherStudentLearningProgress;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherWrongQuestionsOverview;

import java.util.List;

public interface ITeacherViewService {
    List<TeacherWrongQuestionsOverview> getTeacherWrongQuestionsOverview();
    List<TeacherCourseDifficultyAnalysis> getTeacherCourseDifficultyAnalysis();
    List<TeacherExamPaperPerformance> getTeacherExamPaperPerformance();
    List<TeacherStudentLearningProgress> getTeacherStudentLearningProgress();
}

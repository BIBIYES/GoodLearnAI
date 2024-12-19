package xyz.bibiyes.goodlearnai.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.mapper.TeacherViewMapper;
import xyz.bibiyes.goodlearnai.service.ITeacherViewService;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherCourseDifficultyAnalysis;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherExamPaperPerformance;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherStudentLearningProgress;
import xyz.bibiyes.goodlearnai.vo.dataview.TeacherWrongQuestionsOverview;

import java.util.List;

/**
 * 老师数据面板服务层
 */
@Service
public class TeacherViewServiceImpl implements ITeacherViewService {

    @Autowired
    private TeacherViewMapper teacherViewMapper;

    @Override
    public List<TeacherWrongQuestionsOverview> getTeacherWrongQuestionsOverview() {
        return teacherViewMapper.getTeacherWrongQuestionsOverview();
    }

    @Override
    public List<TeacherCourseDifficultyAnalysis> getTeacherCourseDifficultyAnalysis() {
        return teacherViewMapper.getTeacherCourseDifficultyAnalysis();
    }

    @Override
    public List<TeacherExamPaperPerformance> getTeacherExamPaperPerformance() {
        return teacherViewMapper.getTeacherExamPaperPerformance();
    }

    @Override
    public List<TeacherStudentLearningProgress> getTeacherStudentLearningProgress() {
        return teacherViewMapper.getTeacherStudentLearningProgress();
    }
}

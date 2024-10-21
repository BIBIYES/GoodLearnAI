package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.mapper.ExamPaperMapper;
import xyz.bibiyes.goodlearnai.mapper.ExamPaperQuestionMapper;
import xyz.bibiyes.goodlearnai.service.ExamPaperService;

import java.util.List;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private ExamPaperQuestionMapper examPaperQuestionMapper;


    @Override
    public boolean insertExamPaper(ExamPaper examPaper) {
        return examPaperMapper.insert(examPaper) > 0;
    }

    @Override
    public List<ExamPaper> selectExamPaperByUserId(Long teacherId) {
        return examPaperMapper.selectExamPaperByUserId(teacherId);
    }

    @Override
    public boolean deleteExamPaperById(Long examPaperId) {
        return examPaperMapper.deleteById(examPaperId) > 0;
    }
    @Override
    public List<ExamPaper> getAllExamPapers() {
        return examPaperMapper.selectList(null); // MyBatis-Plus 提供的 selectList() 方法，无条件查询所有
    }
    @Override
    public ExamPaper selectExamPaperById(Long examPaperId){
        return examPaperMapper.selectById(examPaperId);
    }

    // 更新试卷 updateExamPaper
    @Override
    public boolean updateExamPaper(ExamPaper examPaper) {
        return examPaperMapper.updateById(examPaper) > 0;
    }
}

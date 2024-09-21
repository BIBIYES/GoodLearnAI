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
    public boolean createExamPaper(ExamPaper examPaper) {
        return examPaperMapper.insert(examPaper) > 0;
    }

    @Override
    public List<ExamPaper> getExamPapersByTeacherId(Long teacherId) {
        return examPaperMapper.getExamPapersByTeacherId(teacherId);
    }

    @Override
    public boolean deleteExamPaperById(Long examPaperId) {
        return examPaperMapper.deleteById(examPaperId) > 0;
    }
    @Override
    public List<ExamPaper> getAllExamPapers() {
        return examPaperMapper.selectList(null); // MyBatis-Plus 提供的 selectList() 方法，无条件查询所有
    }

}

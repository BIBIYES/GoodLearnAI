package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.mapper.StudentExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;

import java.util.List;
import java.util.Map;

@Service
public class StudentExamPaperServiceImpl implements StudentExamPaperService {

    @Autowired
    private StudentExamPaperMapper studentExamPaperMapper;

    @Override
    public boolean joinExamPaper(StudentExamPaper studentExamPaper) {
        try {
            return studentExamPaperMapper.insert(studentExamPaper) > 0;
        } catch (DuplicateKeyException e) {
            return false; // 捕获重复插入异常
        }
    }


    @Override
    public List<Map<String, Object>> getJoinedExamPapersByStudentId(Long studentId) {
        // 直接调用 mapper 进行联表查询
        return studentExamPaperMapper.getJoinedExamPapersByStudentId(studentId);
    }

    // 如果你打算依赖数据库处理重复插入的逻辑，这个方法可以移除
    @Override
    public boolean isStudentJoined(Long studentId, Long examPaperId) {
        return studentExamPaperMapper.isStudentJoinedExam(studentId, examPaperId) > 0;
    }
}

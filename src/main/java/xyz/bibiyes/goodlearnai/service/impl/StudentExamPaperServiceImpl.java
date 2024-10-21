package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.dto.StudentJoinedExamPaperDTO;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.exception.CustomException;
import xyz.bibiyes.goodlearnai.mapper.StudentExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

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
    public Result getJoinedExamPapersByStudentId(Long studentId) {
        try {
            List<StudentJoinedExamPaperDTO> examPapers = studentExamPaperMapper.getJoinedExamPapersByStudentId(studentId);
            if (examPapers.isEmpty()) {
                return Result.success("暂无加入的试卷", examPapers);
            } else {
                return Result.success("成功获取加入的试卷", examPapers);
            }
        } catch (Exception e) {
            throw new CustomException("试卷id不存在");
        }
    }

}

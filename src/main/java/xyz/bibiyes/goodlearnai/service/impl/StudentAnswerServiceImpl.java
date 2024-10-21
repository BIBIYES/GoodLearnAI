package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bibiyes.goodlearnai.entity.StudentAnswer;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.mapper.StudentAnswerMapper;
import xyz.bibiyes.goodlearnai.mapper.StudentExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.StudentAnswerService;

import java.util.List;

@Service
public class StudentAnswerServiceImpl extends ServiceImpl<StudentAnswerMapper, StudentAnswer> implements StudentAnswerService {
    @Autowired
    private StudentExamPaperMapper studentExamPaperMapper;

    @Override
    @Transactional
    public boolean insertStudentAnswerList(Long userId, Long examPaperId, List<StudentAnswer> studentAnswerList) {
        studentAnswerList.forEach(studentAnswer -> {
            studentAnswer.setUserId(userId);
            studentAnswer.setExamPaperId(examPaperId);
        });
        if (saveBatch(studentAnswerList)) {
            // 将试卷状态切换为已经完成
            int flag = studentExamPaperMapper.updateStatus(userId,examPaperId);
            return flag > 0;
        }
        return false;

    }
}
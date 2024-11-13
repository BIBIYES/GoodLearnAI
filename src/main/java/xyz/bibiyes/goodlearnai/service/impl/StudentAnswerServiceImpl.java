package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.StudentAnswer;
import xyz.bibiyes.goodlearnai.exception.CustomException;
import xyz.bibiyes.goodlearnai.mapper.StudentAnswerMapper;
import xyz.bibiyes.goodlearnai.mapper.StudentExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.StudentAnswerService;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.ExamPaperStudentAnswer;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentAnswerServiceImpl extends ServiceImpl<StudentAnswerMapper, StudentAnswer> implements StudentAnswerService {
    @Autowired
    private StudentExamPaperMapper studentExamPaperMapper;
    @Resource
    private  StudentAnswerMapper studentAnswerMapper;

    @Override
    public boolean insertStudentAnswerList(Long userId, Long examPaperId, List<StudentAnswer> studentAnswerList) {
        try {
            // 设置 userId 和 examPaperId
            studentAnswerList.forEach(studentAnswer -> {
                studentAnswer.setUserId(userId);
                studentAnswer.setExamPaperId(examPaperId);
            });

            // 保存学生答案批次
            boolean saveSuccess = saveBatch(studentAnswerList);
            if (!saveSuccess) {
                throw new CustomException("答案提交失败，保存批次失败！");
            }

            // 更新试卷状态
            int updateCount = studentExamPaperMapper.updateStatus(userId, examPaperId);
            if (updateCount <= 0) {
                throw new CustomException("无法更新试卷状态，可能已经提交过！");
            }

            return true;
        } catch (CustomException e) {
            throw e; // 自定义异常抛出
        } catch (Exception e) {
            // 捕获其他异常，包装成自定义异常抛出
            throw new CustomException("提交失败，请稍后重试！");
        }
    }

    @Override
    public Result getExamPaperStudentAnswer(Long examPaperId, Long userId) {
        List<ExamPaperStudentAnswer> examPaperStudentAnswer = studentAnswerMapper.getExamPaperStudentAnswer(examPaperId, userId);
        return Result.success("ok",examPaperStudentAnswer);
    }
}

package xyz.bibiyes.goodlearnai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.bibiyes.goodlearnai.entity.StudentAnswer;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

public interface StudentAnswerService extends IService<StudentAnswer> {
    boolean insertStudentAnswerList(Long userId, Long examPaperId, List<StudentAnswer> studentAnswerList);

    Result getExamPaperStudentAnswer(Long examPaperId, Long userId);
}

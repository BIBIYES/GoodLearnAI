package xyz.bibiyes.goodlearnai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;

import java.util.List;

public interface ExamPaperService extends IService<ExamPaper> {
    List<ExamPaper> getExamPapersByTeacherId(Long teacherId);
    boolean deleteExamPaperById(Long examPaperId);
}

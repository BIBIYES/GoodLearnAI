package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

public interface ExamPaperService {
    boolean createExamPaper(ExamPaper examPaper);
    List<ExamPaper> getExamPapersByTeacherId(Long teacherId);
    boolean deleteExamPaperById(Long examPaperId);

    List<ExamPaper> getAllExamPapers();

}

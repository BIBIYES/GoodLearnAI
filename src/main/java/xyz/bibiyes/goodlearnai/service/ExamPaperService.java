package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.ExamPaper;

import java.util.List;

public interface ExamPaperService {
    boolean insertExamPaper(ExamPaper examPaper);
    List<ExamPaper> selectExamPaperByUserId(Long teacherId);
    boolean deleteExamPaperById(Long examPaperId);

    List<ExamPaper> getAllExamPapers();

    ExamPaper selectExamPaperById(Long examPaperId);

    boolean updateExamPaper(ExamPaper examPaper);
}

package xyz.bibiyes.goodlearnai.service;


import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.utils.Result;

public interface StudentExamPaperService {
    boolean joinExamPaper(StudentExamPaper studentExamPaper);
    Result getJoinedExamPapersByStudentId(Long userId);

    Result getStudentExamPaperByExamPaperId(Long examPaperId);

    Result getStudentExamPapers();
}

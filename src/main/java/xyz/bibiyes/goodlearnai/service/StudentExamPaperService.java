package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.dto.StudentJoinedExamPaperDTO;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;
import java.util.Map;

public interface StudentExamPaperService {
    boolean joinExamPaper(StudentExamPaper studentExamPaper);
    Result getJoinedExamPapersByStudentId(Long userId);

    Result getStudentExamPaperByExamPaperId(Long examPaperId);
}

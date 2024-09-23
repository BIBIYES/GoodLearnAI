package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;

import java.util.List;
import java.util.Map;

public interface StudentExamPaperService {
    boolean joinExamPaper(StudentExamPaper studentExamPaper);
    List<Map<String, Object>> getJoinedExamPapersByStudentId(Long studentId);
    boolean isStudentJoined(Long studentId, Long examPaperId);
}

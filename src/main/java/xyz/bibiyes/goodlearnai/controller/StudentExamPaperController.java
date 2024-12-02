package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

@RestController
@RequestMapping("/studentExamPapers")
@CrossOrigin
public class StudentExamPaperController {

    @Autowired
    private StudentExamPaperService studentExamPaperService;

    // 通过试卷id来查询该试卷下学生完成情况
    @GetMapping("/{examPaperId}")
    public Result getStudentExamPaperByExamPaperId(@PathVariable Long examPaperId) {
        return studentExamPaperService.getStudentExamPaperByExamPaperId(examPaperId);
    }
    // 获取所有试卷的完成情况
    @GetMapping("/get-all-papers")
    public Result getAllStudentExamPapers() {
        return studentExamPaperService.getStudentExamPapers();
    }

}

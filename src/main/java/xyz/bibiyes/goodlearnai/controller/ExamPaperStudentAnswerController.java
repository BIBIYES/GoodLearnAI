package xyz.bibiyes.goodlearnai.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.bibiyes.goodlearnai.service.StudentAnswerService;
import xyz.bibiyes.goodlearnai.utils.Result;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student-answer")
public class ExamPaperStudentAnswerController {
    @Resource
    private StudentAnswerService studentAnswerService;
    @GetMapping("/exam-paper-student-answer")
    public Result getExamPaperStudentAnswer(@RequestParam Long examPaperId, @RequestParam("userId") Long userId){
        return studentAnswerService.getExamPaperStudentAnswer(examPaperId,userId);
    }
}

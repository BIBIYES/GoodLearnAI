package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.service.ExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/exam-papers")
@CrossOrigin
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    /**
     * 返回所有试卷
     * @return examPapers
     */
    @GetMapping
    public Result getAllExamPapers() {
        List<ExamPaper> examPapers = examPaperService.getAllExamPapers();
        if (!examPapers.isEmpty()) {
            return Result.success("Exam papers retrieved successfully", examPapers);
        } else {
            return Result.error("No exam papers found");
        }
    }

    /**
     * 创建一个试卷
     * @param examPaper 一个试卷对象
     * @return 返回创建成功的提示
     */
    @PostMapping
    public Result createExamPaper(@RequestBody ExamPaper examPaper) {
        boolean saved = examPaperService.createExamPaper(examPaper);
        if (saved) {
            return Result.success("Exam paper created successfully", examPaper);
        } else {
            return Result.error("Failed to create exam paper");
        }
    }

    /**
     * 通过老师id 来获取老师自己的试卷
     * @param teacherId 老师自己的id
     * @return 返回老师自己的所有试卷
     */
    @GetMapping("/teachers/{teacherId}")
    public Result getExamPapersByTeacherId(@PathVariable Long teacherId) {
        List<ExamPaper> examPapers = examPaperService.getExamPapersByTeacherId(teacherId);
        if (!examPapers.isEmpty()) {
            return Result.success("Exam papers retrieved successfully", examPapers);
        } else {
            return Result.error("No exam papers found for the teacher");
        }
    }

    /**
     * 通过试卷 examPaperId 来删除 试卷
     * @param examPaperId 试卷id
     * @return 返回删除成功或者删除失败
     */
    @DeleteMapping("/{examPaperId}")
    public Result deleteExamPaper(@PathVariable Long examPaperId) {
        boolean removed = examPaperService.deleteExamPaperById(examPaperId);
        if (removed) {
            return Result.success("Exam paper deleted successfully");
        } else {
            return Result.error("Failed to delete exam paper");
        }
    }


}

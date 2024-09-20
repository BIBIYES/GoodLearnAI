package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.service.ExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/exampaper")
@CrossOrigin
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @PostMapping("/create")
    public Result createExamPaper(@RequestBody ExamPaper examPaper) {
        boolean saved = examPaperService.save(examPaper);
        if (saved) {
            return Result.success("exampaper", "ExamPaper created successfully", examPaper);
        } else {
            return Result.error("exampaper", "Failed to create examPaper");
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public Result getExamPapersByTeacherId(@PathVariable Long teacherId) {
        List<ExamPaper> examPapers = examPaperService.getExamPapersByTeacherId(teacherId);
        if (examPapers != null && !examPapers.isEmpty()) {
            return Result.success("exampaper", "ExamPapers retrieved successfully", examPapers);
        } else {
            return Result.error("exampaper", "No exam papers found for the teacher");
        }
    }

    @DeleteMapping("/delete/{examPaperId}")
    public Result deleteExamPaper(@PathVariable Long examPaperId) {
        boolean removed = examPaperService.deleteExamPaperById(examPaperId);
        if (removed) {
            return Result.success("exampaper", "ExamPaper deleted successfully");
        } else {
            return Result.error("exampaper", "Failed to delete examPaper");
        }
    }
}

package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studentExamPapers")
@CrossOrigin
public class StudentExamPaperController {

    @Autowired
    private StudentExamPaperService studentExamPaperService;

    /**
     * 学生加入试卷
     */
    @PostMapping
    public Result joinExamPaper(@RequestBody StudentExamPaper studentExamPaper) {
        boolean success = studentExamPaperService.joinExamPaper(studentExamPaper);
        if (success) {
            return Result.success("加入试卷成功");
        } else {
            return Result.error("学生已加入该试卷或加入失败");
        }
    }

    /**
     * 根据学生ID获取已加入的试卷
     */
    @GetMapping("/students/{studentId}/examPapers")
    public Result getExamPapersByStudentId(@PathVariable Long studentId) {
        // 使用联表查询的结果
        List<Map<String, Object>> joinedPapers = studentExamPaperService.getJoinedExamPapersByStudentId(studentId);
        return Result.success("已加入的试卷列表", joinedPapers);
    }
}

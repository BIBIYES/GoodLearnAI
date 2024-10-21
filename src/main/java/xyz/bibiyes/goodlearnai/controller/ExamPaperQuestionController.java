package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.service.ExamPaperQuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/examPaperQuestions")
@CrossOrigin
public class ExamPaperQuestionController {

    @Autowired
    private ExamPaperQuestionService examPaperQuestionService;

    /**
     * 根据试卷ID获取关联的题目列表
     */
    @GetMapping("/{paperId}/questions")
    public Result getQuestionsByPaperId(@PathVariable Long paperId) {
        List<Question> questions = examPaperQuestionService.getQuestionsByPaperId(paperId);
        return Result.success("questions", questions);
    }

    /**
     * 更新试卷的题目关联
     */
    @PutMapping("/{paperId}")
    public Result updateExamPaperQuestions(@PathVariable Long paperId, @RequestBody List<Long> questionIds) {
        boolean updated = examPaperQuestionService.updateExamPaperQuestions(paperId, questionIds);
        if (updated) {
            return Result.success("试卷题目关联更新成功");
        } else {
            return Result.error("试卷题目关联更新失败");
        }
    }

    /**
     * 删除试卷中某个题目的关联
     */
    @DeleteMapping("/{paperId}/question/{questionId}")
    public Result deleteQuestionFromPaper(@PathVariable Long paperId, @PathVariable Long questionId) {
        boolean removed = examPaperQuestionService.removeQuestionFromPaper(paperId, questionId);
        if (removed) {
            return Result.success("题目从试卷中删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}

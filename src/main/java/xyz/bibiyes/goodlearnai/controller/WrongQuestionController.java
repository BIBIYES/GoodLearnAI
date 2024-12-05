package xyz.bibiyes.goodlearnai.controller;

import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.WrongQuestion;
import xyz.bibiyes.goodlearnai.service.IWrongQuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/wrong-question")
public class WrongQuestionController {
    
    @Resource
    private IWrongQuestionService wrongQuestionService;
    @PostMapping("/add")
    public Result addWrongQuestion(@RequestBody WrongQuestion wrongQuestion) {
        return wrongQuestionService.addWrongQuestion(wrongQuestion);
    }

    /**
     * 获取用户的错题记录未标记正确的列表
     * @param userId 用户ID
     * @param examPaperName 试卷名称（可选）
     * @param questionTitle 题目标题（可选）
     */
    @GetMapping("/user/{userId}")
    public Result getWrongQuestions(
            @PathVariable Long userId,
            @RequestParam(required = false) String examPaperName,
            @RequestParam(required = false) String questionTitle) {
        List<WrongQuestionVO> wrongQuestions = wrongQuestionService.getWrongQuestions(userId, examPaperName, questionTitle);
        return Result.success("获取错题记录成功", wrongQuestions);
    }

      @PutMapping("/delete/{wrongQuestionsId}/{userId}")
    public Result deleteWrongQuestion(@PathVariable Long wrongQuestionsId ,@PathVariable Long userId) {
        return wrongQuestionService.deleteByWrongQuestionsId(wrongQuestionsId,userId);
    }
/**
 * 获取用户该试卷所有的错误题目
 */
@GetMapping("/user/{userId}/exam-paper/{examPaperId}")
    public Result getWrongQuestionsByExamPaperId(@PathVariable Long userId, @PathVariable Long examPaperId) {
        List<WrongQuestion> wrongQuestions = wrongQuestionService.lambdaQuery()
                .eq(WrongQuestion::getUserId, userId)
                .eq(WrongQuestion::getExamPaperId, examPaperId)
                .list();
        return Result.success("获取错题记录成功", wrongQuestions);
    }



}

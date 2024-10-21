package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.service.QuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取所有题目。
     * @return 返回封装好的所有题目的列表，如果没有题目则返回错误信息。
     */
    @GetMapping
    public Result getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        if (questions != null && !questions.isEmpty()) {
            return Result.success("Questions retrieved successfully", questions);
        } else {
            return Result.error("No questions found");
        }
    }

    /**
     * 根据题目 ID 获取题目详情。
     * @param questionId 题目的唯一标识。
     * @return 返回指定题目的详细信息，如果题目不存在则返回错误信息。
     */
    @GetMapping("/{questionId}")
    public Result getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        if (question != null) {
            return Result.success("Question retrieved successfully", question);
        } else {
            return Result.error("No question found for the provided ID");
        }
    }



    /**
     * 更新指定题目。
     * @param questionId 需要更新的题目的唯一标识。
     * @param question   题目的新信息。
     * @return 如果更新成功，返回更新成功的信息，否则返回错误信息。
     */
    @PutMapping("/{questionId}")
    public Result updateQuestionById(@PathVariable Long questionId, @RequestBody Question question) {
        question.setQuestionId(questionId);  // 设置ID，确保更新操作
        boolean updated = questionService.updateQuestion(question);
        if (updated) {
            return Result.success("Question updated successfully", question);
        } else {
            return Result.error("Failed to update question");
        }
    }

    /**
     * 根据题目 ID 删除指定的题目。
     *
     * @param questionId 题目的唯一标识。
     * @return 如果删除成功，返回删除成功的信息，否则返回错误信息。
     */
    @DeleteMapping("/{questionId}")
    public Result deleteQuestion(@PathVariable Long questionId) {
        boolean removed = questionService.deleteQuestionById(questionId);
        if (removed) {
            return Result.success("Question deleted successfully");
        } else {
            return Result.error("Failed to delete question");
        }
    }
}

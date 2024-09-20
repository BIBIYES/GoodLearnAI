package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.service.QuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // 获取所有题目
    @GetMapping("/questions")
    public Result getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        if (questions != null && !questions.isEmpty()) {
            return Result.success("questions", "Questions retrieved successfully", questions);
        } else {
            return Result.error("questions", "No questions found");
        }
    }
    // 根据 ID 获取题目详情
    @GetMapping("/{questionId}")
    public Result getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        if (question != null) {
            return Result.success("question", "Question retrieved successfully", question);
        } else {
            return Result.error("question", "No question found for the provided ID");
        }
    }
    // 创建或更新题目
    @PostMapping("/create")
    public Result createOrUpdateQuestion(@RequestBody Question question) {
        if (question.getQuestionId() != null) {
            // 更新操作，调用自定义的 updateQuestion 方法
            boolean updated = questionService.updateQuestion(question);
            if (updated) {
                return Result.success("question", "Question updated successfully", question);
            } else {
                return Result.error("question", "Failed to update question");
            }
        } else {
            // 插入操作，调用自定义的 saveQuestion 方法
            boolean saved = questionService.saveQuestion(question);
            if (saved) {
                return Result.success("question", "Question created successfully", question);
            } else {
                return Result.error("question", "Failed to create question");
            }
        }
    }

    // 根据课程 ID 获取题目
    @GetMapping("/course/{courseId}")
    public Result getQuestionsByCourseId(@PathVariable Long courseId) {
        List<Question> questions = questionService.getQuestionsByCourseId(courseId);
        if (questions != null && !questions.isEmpty()) {
            return Result.success("question", "Questions retrieved successfully", questions);
        } else {
            return Result.error("question", "No questions found for the course");
        }
    }

    // 删除题目
    @DeleteMapping("/delete/{questionId}")
    public Result deleteQuestion(@PathVariable Long questionId) {
        boolean removed = questionService.deleteQuestionById(questionId);
        if (removed) {
            return Result.success("question", "Question deleted successfully");
        } else {
            return Result.error("question", "Failed to delete question");
        }
    }
}

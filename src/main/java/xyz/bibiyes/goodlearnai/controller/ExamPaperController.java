package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
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

    /**
     * 通过试卷 id 来获取试卷信息
     * @param examPaperId 试卷 id
     * @return 试卷信息
     */
    @GetMapping("/{examPaperId}")
    public Result getExamPaperById(@PathVariable Long examPaperId) {
        ExamPaper examPaper = examPaperService.selectExamPaperById(examPaperId);

        // 判断 examPaper 对象是否为 null
        if (examPaper != null) {
            return Result.success("Exam papers retrieved successfully", examPaper);
        } else {
            return Result.error("No exam papers found for the given ID");
        }
    }

    //更新试卷
    @PutMapping("/{examPaperId}")
    public Result updateExamPaper(@PathVariable Long examPaperId, @RequestBody ExamPaper examPaper) {
        examPaper.setExamPaperId(examPaperId);
        boolean updated = examPaperService.updateExamPaper(examPaper);
        if (updated) {
            return Result.success("Exam paper updated successfully");
        } else {
            return Result.error("Failed to update exam paper");
        }
    }

}

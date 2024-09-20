package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("exam_paper_question")
public class ExamPaperQuestion {
    @TableId
    private Integer examPaperQuestionId;
    private Integer examPaperId;
    private Integer questionId;
}

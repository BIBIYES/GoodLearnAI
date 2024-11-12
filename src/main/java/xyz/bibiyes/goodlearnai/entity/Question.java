package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("question")
public class Question {
    @TableId(type = IdType.AUTO)
    private Long questionId;
    private String questionTitle;
    private String questionContent;
    private String answer;
    private String questionCreatedDate;
    private String questionUpdatedDate;
    private Integer courseId;
    private Integer userId;
}

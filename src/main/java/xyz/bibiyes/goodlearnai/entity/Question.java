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
    private LocalDateTime creationDate;
    private Integer courseId;
    private Integer teacherId;
}

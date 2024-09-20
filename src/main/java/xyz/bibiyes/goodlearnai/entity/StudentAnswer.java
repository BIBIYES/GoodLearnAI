package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_answer")
public class StudentAnswer {
    @TableId(type = IdType.AUTO)
    private Long answerId;  // 将 Integer 改为 Long 以匹配数据库中的数据类型
    private Integer studentId;
    private Integer examPaperQuestionId;
    private String studentAnswer;
}

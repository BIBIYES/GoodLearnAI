package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")

public class Course {
    @TableId(type = IdType.AUTO)
    private Long courseId;
    private String courseName;
    private String courseCreatedDate;
    private String courseUpdatedDate;
    private Long userId;
}

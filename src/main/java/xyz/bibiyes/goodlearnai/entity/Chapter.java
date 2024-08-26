package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chapter")
public class Chapter {
    @TableId
    private Long id;
    private Long subjectId;
    private String name;
}
package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("subject")
public class Subject {
    @TableId
    private Long id;
    private String name;
}
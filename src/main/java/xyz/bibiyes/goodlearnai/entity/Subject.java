package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("subject")
public class Subject {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
}
package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * @author Mouse Sakura
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String prompt;
    private String uploadDate;
    private Long userId;

}

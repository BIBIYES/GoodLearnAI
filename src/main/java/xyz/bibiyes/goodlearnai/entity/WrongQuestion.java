package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luozihao
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wrong_question")
public class WrongQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wrong_questions_id", type = IdType.AUTO)
    private Long wrongQuestionsId;

    private Long userId;

    private Long examPaperId;

    private Long questionId;

    private String wrongAnswer;

    private String aiAnswer;
    private Date createTime;

}

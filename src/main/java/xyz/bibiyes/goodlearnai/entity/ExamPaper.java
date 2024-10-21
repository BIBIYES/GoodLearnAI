package xyz.bibiyes.goodlearnai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("exam_paper")
public class ExamPaper {
    @TableId(type = IdType.AUTO)
        private Long examPaperId;
        private String examPaperName;
        private String examPaperCreatedDate;
        private String examPaperUpdatedDate;
        private Long userId;
}

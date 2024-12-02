package xyz.bibiyes.goodlearnai.vo;

import lombok.Data;

import java.util.Date;

/**
 * 该实体类 返回所有已经完成的试卷以及他的用户名字用于前端渲染排行榜
 */
@Data
public class AllExamPaperUserStatusVO {
    private Long studentExamPaperId;
    private Long userId;
    private Long examPaperId;
    private String joinDate;
    private String status;
    private String username;

}

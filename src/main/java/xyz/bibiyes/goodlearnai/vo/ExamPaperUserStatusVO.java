package xyz.bibiyes.goodlearnai.vo;

import lombok.Data;

/**
 * 返回试卷中用户状态的视图对象
 */
@Data
public class ExamPaperUserStatusVO {

    private Long userId;
    private String username;
    private Long examPaperId;
    private String joinDate;
    private String status;


}

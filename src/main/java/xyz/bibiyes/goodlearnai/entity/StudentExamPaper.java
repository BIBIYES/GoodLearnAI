package xyz.bibiyes.goodlearnai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.Timestamp;
// 生成get set方法
@Data
@ToString
// 生成无参构造方法
@NoArgsConstructor
// 生成全参构造方法
@AllArgsConstructor
public class StudentExamPaper {
    private Long studentExamPaperId;
    private Long userId;
    private Long examPaperId;
    private String joinDate;
    private String status;

    
}

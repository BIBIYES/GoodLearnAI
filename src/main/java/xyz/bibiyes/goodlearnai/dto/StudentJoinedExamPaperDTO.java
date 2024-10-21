package xyz.bibiyes.goodlearnai.dto;

import lombok.Data;

@Data
public class StudentJoinedExamPaperDTO {
    private Long examPaperId;
    private String joinDate;
    private String username;
    private String examPaperCreatedDate;
    private String examPaperName;
    private String status;

}

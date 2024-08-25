package xyz.bibiyes.goodlearnai.entity;

import lombok.*;

/**
 * @author Mouse Sakura
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    private int id;
    private String title;
    private String content;
    private String prompt;
    private String uploadDate;

}

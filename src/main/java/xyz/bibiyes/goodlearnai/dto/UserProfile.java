package xyz.bibiyes.goodlearnai.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserProfile {
    private Long userId;
    private String username;
    private Long cqipcId;
    private Date birthday;
    private String address;
    private  String avatar;
}

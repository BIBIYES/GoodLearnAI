package xyz.bibiyes.goodlearnai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
     * @author Mouse Sakura
     */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFrom {
    private String username;
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String confirmPassword;
    private String authenticator;
    @Getter
    private String verificationCode;

    public String getEmail() {
        return userEmail;
    };

}

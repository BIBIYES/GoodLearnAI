package xyz.bibiyes.goodlearnai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
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
    private String verificationCode;

    public String getEmail() {
        return userEmail;
    };

    public String getVerificationCode() {
        return verificationCode;
    }
}

package xyz.bibiyes.goodlearnai.from;

import lombok.Data;

    /**
     * @author Mouse Sakura
     */
@Data
public class RegisterFrom {
    private String username;
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String confirmPassword;
    private String authenticator;

}

package xyz.bibiyes.goodlearnai.from;

import lombok.Data;

    /**
     * @author Mouse Sakura
     */
@Data
public class RegisterFrom {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
    private String authenticator;

}

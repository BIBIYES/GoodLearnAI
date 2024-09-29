package xyz.bibiyes.goodlearnai.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mouse Sakura
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String userCreatedDate;
    private String userUpdatedDate;
}

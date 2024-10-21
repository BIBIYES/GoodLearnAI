package xyz.bibiyes.goodlearnai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.User;
import xyz.bibiyes.goodlearnai.dto.LoginFrom;
import xyz.bibiyes.goodlearnai.dto.RegisterFrom;
import xyz.bibiyes.goodlearnai.mapper.UserMapper;
import xyz.bibiyes.goodlearnai.utils.Jjwt;
import xyz.bibiyes.goodlearnai.utils.Md5;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mouse Sakura
 */
@Service
@Slf4j
public class UserService {
    // jwt令牌生成工具类
    @Resource
    private Jjwt genjwt;
    // md5密码加密工具类
    @Resource
    private Md5 md5;
    @Resource
    private UserMapper usersMapper;


    /**
     * 用户注册
     * @param registerFrom 封装的用户注册实体类
     * @return 返回成功或失败
     * @throws NoSuchAlgorithmException  md5加密异常
     */
    public Result register(RegisterFrom registerFrom) throws NoSuchAlgorithmException {
        log.info("+++++++++++++++++{}",registerFrom.getUsername());
        log.info("+++++++++++++++++{}",registerFrom.getUserEmail());
        log.info("+++++++++++++++++{}",registerFrom.getUserPassword());
        log.info("+++++++++++++++++{}",registerFrom.getConfirmPassword());
        log.info("+++++++++++++++++{}",registerFrom.getUserRole());
        log.info("+++++++++++++++++{}",registerFrom.getAuthenticator());
        if (registerFrom.getUserPassword().equals(registerFrom.getConfirmPassword())) {
            // 1. 验证邮箱是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_email", registerFrom.getUserEmail());
            User user = usersMapper.selectOne(queryWrapper);
            if (user != null) {
                return Result.error( "邮箱已注册");
            }

            // 2. 验证角色和鉴权码
            if (isRoleValid(registerFrom) || "student".equals(registerFrom.getUserRole())) {
                // 3. 密码加密
                String password = md5.hashPassword(registerFrom.getUserPassword());

                // 4. 保存用户信息
                User newUser = createUser(registerFrom, password);
                usersMapper.insert(newUser);

                return Result.success( "注册成功");
            } else {
                return Result.error("鉴权码认证失败");
            }
        }
        return Result.error( "两次密码不一致");

    }

    // 验证角色和鉴权码
    private boolean isRoleValid(RegisterFrom registerFrom) {
        if ("teacher".equals(registerFrom.getUserRole())) {
            return "Abcd1234".equals(registerFrom.getAuthenticator());
        }
        return "students".equals(registerFrom.getUserRole());
    }

    // 创建用户对象
    private User createUser(RegisterFrom registerFrom, String password) {
        User user = new User();
        user.setUsername(registerFrom.getUsername());
        user.setUserEmail(registerFrom.getUserEmail());
        user.setUserPassword(password);
        user.setUserRole(registerFrom.getUserRole());
        return user;
    }
    public Result login(LoginFrom loginFrom) throws NoSuchAlgorithmException {
        // 1. 验证邮箱是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", loginFrom.getUserEmail());
        User user = usersMapper.selectOne(queryWrapper);

        if (user == null) {
            return Result.error("用户不存在");
        }

        // 2. 验证密码
        String inputPassword = loginFrom.getUserPassword();
        String storedPassword = user.getUserPassword();

        // 使用 MD5 对用户输入的密码进行加密
        String encryptedInputPassword = md5.hashPassword(inputPassword);

        if (!encryptedInputPassword.equals(storedPassword)) {
            return Result.error("密码错误");
        }
        // 登录成功，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getUserId());
        claims.put("name",user.getUsername());
        claims.put("user_email",user.getUserEmail());
        claims.put("role",user.getUserRole());
        String jwt = genjwt.genJwt(claims);
        Map<String, Object> data = new HashMap<>();
        data.put("id",user.getUserId());
        data.put("user_email",user.getUserEmail());
        data.put("name",user.getUsername());
        data.put("role",user.getUserRole());
        data.put("token",jwt);
        return Result.success("登陆成功",data);
    }
}

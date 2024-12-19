package xyz.bibiyes.goodlearnai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.dto.UserProfile;
import xyz.bibiyes.goodlearnai.entity.User;
import xyz.bibiyes.goodlearnai.dto.LoginFrom;
import xyz.bibiyes.goodlearnai.dto.RegisterFrom;
import xyz.bibiyes.goodlearnai.mapper.UserMapper;
import xyz.bibiyes.goodlearnai.utils.Jjwt;
import xyz.bibiyes.goodlearnai.utils.Md5;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.service.VerificationCodeService;
import xyz.bibiyes.goodlearnai.service.IUserService;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private Jjwt genjwt;
    @Resource
    private Md5 md5;
    @Resource
    private UserMapper usersMapper;
    @Resource
    private VerificationCodeService verificationCodeService;

    /**
     * 用户注册
     *
     * @param registerFrom 封装的用户注册实体类
     * @return 返回成功或失败
     * @throws NoSuchAlgorithmException md5 加密异常
     */
    @Override
    public Result register(RegisterFrom registerFrom) throws NoSuchAlgorithmException {
        log.info("+++++++++++++++++{}", registerFrom.getUsername());
        log.info("+++++++++++++++++{}", registerFrom.getUserEmail());
        log.info("+++++++++++++++++{}", registerFrom.getUserPassword());
        log.info("+++++++++++++++++{}", registerFrom.getConfirmPassword());
        log.info("+++++++++++++++++{}", registerFrom.getUserRole());
        log.info("+++++++++++++++++{}", registerFrom.getAuthenticator());

        log.info("开始验证邮箱是否存在");
        // 1. 验证邮箱是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", registerFrom.getUserEmail());
        User user = usersMapper.selectOne(queryWrapper);
        if (user != null) {
            return Result.error("邮箱已注册");
        }

        // 2. 验证角色和鉴权码
        log.info("开始验证角色和鉴权码");
        if (isRoleValid(registerFrom) || "student".equals(registerFrom.getUserRole())) {
            // 3. 密码加密
            log.info("开始密码加密");
            String password = md5.hashPassword(registerFrom.getUserPassword());

            // 4. 保存用户信息
            log.info("开始保存用户信息");
            User newUser = createUser(registerFrom, password);
            usersMapper.insert(newUser);

            return Result.success("注册成功");
        } else {
            return Result.error("鉴权码认证失败");
        }
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

    @Override
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
        // 登录成功，生成 jwt 令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserId());
        claims.put("name", user.getUsername());
        claims.put("user_email", user.getUserEmail());
        claims.put("role", user.getUserRole());
        String jwt = genjwt.genJwt(claims);
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getUserId());
        data.put("user_email", user.getUserEmail());
        data.put("name", user.getUsername());
        data.put("avatar", user.getAvatar());
        data.put("cqipcId", user.getCqipcId());
        data.put("birthday", user.getBirthday());
        data.put("address", user.getAddress());
        data.put("role", user.getUserRole());
        data.put("token", jwt);
        return Result.success("登陆成功", data);
    }

    /**
     * 修改用户信息
     *
     * @param userProfile 要更改的用户信息
     * @return 返回成功或者失败
     */
    @Override
    public Result changeProfile(UserProfile userProfile) {
        if (userProfile == null) {
            return Result.error("用户信息不能为空");
        }
        User user = new User();
        BeanUtil.copyProperties(userProfile, user);
        if (updateById(user)) {
            return Result.success("用户信息更新成功");
        } else {
            return Result.error("无法添加请检查输入的信息是否有误");
        }

    }
    // 获取所有学生可通过名字模糊查询

    @Override
    public Result getAllStudent(String name) {
        List<User> users = usersMapper.getStudentAll(name);
        if(users.isEmpty()){
            return Result.error("没有找到该学生");
        }else {
            return Result.success("查询成功",users);
        }
    }

    // 获取学生用户的错题计数
    @Override
    public Result getStudentWrongQuestionView(Long userId) {
        return Result.success("查询成功",usersMapper.getStudentWrongQuestionView(userId));
    }
}

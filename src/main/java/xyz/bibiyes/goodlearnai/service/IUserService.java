package xyz.bibiyes.goodlearnai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.bibiyes.goodlearnai.dto.LoginFrom;
import xyz.bibiyes.goodlearnai.dto.RegisterFrom;
import xyz.bibiyes.goodlearnai.dto.UserProfile;
import xyz.bibiyes.goodlearnai.entity.User;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.security.NoSuchAlgorithmException;

public interface IUserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param registerFrom 封装的用户注册实体类
     * @return 返回成功或失败
     * @throws NoSuchAlgorithmException md5 加密异常
     */
    Result register(RegisterFrom registerFrom) throws NoSuchAlgorithmException;

    /**
     * 用户登录
     *
     * @param loginFrom 登录表单数据
     * @return 返回登录结果
     * @throws NoSuchAlgorithmException md5 加密异常
     */
    Result login(LoginFrom loginFrom) throws NoSuchAlgorithmException;

    /**
     * 修改用户信息
     *
     * @param userProfile 要更改的用户信息
     * @return 返回成功或者失败
     */
    Result changeProfile(UserProfile userProfile);

    Result getAllStudent(String name);
} 
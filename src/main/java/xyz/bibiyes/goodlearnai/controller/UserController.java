package xyz.bibiyes.goodlearnai.controller;

import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.from.LoginFrom;
import xyz.bibiyes.goodlearnai.from.RegisterFrom;
import xyz.bibiyes.goodlearnai.service.UserService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mouse Sakura
 */
@CrossOrigin
@RestController
@RequestMapping("/goodlearnai")
public class UserController {

    @Resource
    private UserService usersService;

    /**
     * 用户注册 学生 or 老师
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterFrom registerForm) throws NoSuchAlgorithmException {
        return usersService.register(registerForm);

    }

    /**
     * 用户登录 学生 or 老师
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFrom loginFrom) throws NoSuchAlgorithmException {
        return usersService.login(loginFrom);

    }

}

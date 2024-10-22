package xyz.bibiyes.goodlearnai.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.dto.EmailFrom;
import xyz.bibiyes.goodlearnai.dto.StudentJoinedExamPaperDTO;
import xyz.bibiyes.goodlearnai.entity.Course;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.dto.LoginFrom;
import xyz.bibiyes.goodlearnai.dto.RegisterFrom;
import xyz.bibiyes.goodlearnai.entity.User;
import xyz.bibiyes.goodlearnai.mapper.UserMapper;
import xyz.bibiyes.goodlearnai.service.*;
import xyz.bibiyes.goodlearnai.utils.EmailUtils;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.utils.VerificationCodeGenerator;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static xyz.bibiyes.goodlearnai.service.VerificationCodeService.verificationCodeCache;

/**
 * @author Mouse Sakura
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService usersService;
    @Resource
    private CourseService courseService;

    @Resource
    private ExamPaperService examPaperService;
    @Resource
    private StudentExamPaperService studentExamPaperService;
    @Resource
    private VerificationCodeService verificationCodeService;
    @Resource
    private UserMapper usersMapper;

    /**
     * 用户注册 学生 or 老师
     */

    @PostMapping("/send-verification-code")
    public Result sendVerificationCode(@RequestBody EmailFrom emailFrom) {
        /**
            * Author: Chen Qinfeng
            * Date: 2024-10-17
         */
        String email = emailFrom.getEmail();
        System.out.println(email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", email);
        User user = usersMapper.selectOne(queryWrapper);
        if (user != null) {
            return Result.error("邮箱已注册");
        }
        verificationCodeService.sendVerificationCode(email);
        System.out.println(verificationCodeCache.get(email));
        if (verificationCodeCache.get(email) != null) {
            return Result.error("请勿重复发送验证码");
        }
        return Result.success("验证码已发送至您的邮箱，请查收。");
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterFrom registerForm) throws NoSuchAlgorithmException {
        /**
         * Author: Chen Qinfeng
         * Date: 2024-10-17
         */
        // 验证验证码
        if (VerificationCodeService.verifyVerificationCode(registerForm.getUserEmail(), registerForm.getVerificationCode())) {
            return usersService.register(registerForm);
        } else {
            return Result.error("验证码错误");
        }

    }

    /**
     * 用户登录 学生 or 老师
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFrom loginFrom) throws NoSuchAlgorithmException {
        return usersService.login(loginFrom);

    }

    /**
     * 获取用户下所有的课程
     *
     * @param userId 用户ID
     * @return List<Course>
     */
    @GetMapping("/{userId}/courses")
    public Result getAllCourses(@PathVariable Long userId) {
        try {
            return Result.success("Courses retrieved successfully", courseService.selectCoursesByUserId(userId));
        } catch (Exception e) {
            return Result.error("Failed to retrieve courses");
        }

    }

    /**
     * 在当前用户下创建一个课程
     *
     * @param course 传入一个课程的实体
     * @return 返回创建成功或者创建失败
     */
    @PostMapping("/{userId}/course")
    public Result createCourse(@PathVariable Long userId, @RequestBody Course course) {
        course.setUserId(userId);
        boolean saved = courseService.insertCourse(course);
        if (saved) {
            return Result.success("课程创建成功");
        } else {
            return Result.error("课程创建失败");
        }
    }

    /**
     * 创建用户自己一个试卷
     *
     * @param examPaper 试卷对象
     * @return 返回创建成功或者创建失败
     */
    @PostMapping("/{userId}/exam-paper")
    public Result insertExamPaper(@PathVariable Long userId, @RequestBody ExamPaper examPaper) {
        examPaper.setUserId(userId);
        System.out.println(examPaper);
        boolean saved = examPaperService.insertExamPaper(examPaper);
        if (saved) {
            return Result.success("Exam paper created successfully", examPaper);
        } else {
            return Result.error("Failed to create exam paper");
        }
    }


    /**
     * 获取用户自己的试卷
     *
     * @param userId 用户ID
     * @return List<ExamPaper>
     */
    @GetMapping("/{userId}/exam-papers")
    public Result selectExamPaperByUserId(@PathVariable Long userId) {
        List<ExamPaper> examPapers = examPaperService.selectExamPaperByUserId(userId);
        if (!examPapers.isEmpty()) {
            return Result.success("Exam papers retrieved successfully", examPapers);
        } else {
            return Result.error("No exam papers found for the teacher");
        }
    }


    /**
     * 加入一个试卷
     *
     * @param userId      用户ID
     * @param examPaperId 试卷ID
     * @return 返回成功或者失败
     */
    @PostMapping("/{userId}/exam-papers/{examPaperId}")
    public Result joinExamPaper(@PathVariable Long userId, @PathVariable Long examPaperId) {
        System.out.println(userId);
        System.out.println(examPaperId);
        StudentExamPaper studentExamPaper = new StudentExamPaper();
        studentExamPaper.setExamPaperId(examPaperId);
        studentExamPaper.setUserId(userId);
        studentExamPaper.setStatus("未完成");
        return studentExamPaperService.joinExamPaper(studentExamPaper) ? Result.success("添加试卷成功") : Result.error("添加失败你已经存在该试卷中");

    }

    /**
     * 获取用户加入的试卷
     *
     * @param userId 用户ID
     * @return List<Map < String, Object>>
     */
    @GetMapping("/{userId}/student-exam-papers")
    public Result getExamPapersByStudentId(@PathVariable Long userId) {
        return studentExamPaperService.getJoinedExamPapersByStudentId(userId);
    }
}

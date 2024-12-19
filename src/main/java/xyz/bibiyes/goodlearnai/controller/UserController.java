package xyz.bibiyes.goodlearnai.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.dto.EmailFrom;
import xyz.bibiyes.goodlearnai.dto.LoginFrom;
import xyz.bibiyes.goodlearnai.dto.RegisterFrom;
import xyz.bibiyes.goodlearnai.dto.UserProfile;
import xyz.bibiyes.goodlearnai.entity.*;
import xyz.bibiyes.goodlearnai.mapper.UserMapper;
import xyz.bibiyes.goodlearnai.service.*;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static xyz.bibiyes.goodlearnai.service.VerificationCodeService.verificationCodeCache;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private IUserService iuserService;
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
    @Resource
    private StudentAnswerService studentAnswerService;

    /**
     * 发送验证码
     */
    @PostMapping("/send-verification-code")
    public Result sendVerificationCode(@RequestBody EmailFrom emailFrom) {
        String email = emailFrom.getEmail();
        System.out.println(email);
        if (email.isEmpty()) {
            return Result.error("邮箱不能为空");
        }
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

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterFrom registerForm) throws NoSuchAlgorithmException {
        if (VerificationCodeService.verifyVerificationCode(registerForm.getUserEmail(), registerForm.getVerificationCode())) {
            return iuserService.register(registerForm);
        } else {
            return Result.error("验证码错误");
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFrom loginFrom) throws NoSuchAlgorithmException {
        return iuserService.login(loginFrom);
    }

    /**
     * 更新个人信息
     */
    @PutMapping("/profile")
    public Result profile(@RequestBody UserProfile userProfile) {
        return iuserService.changeProfile(userProfile);
    }

    /**
     * 获取用户下所有的课程
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
     * 创建课程
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
     * 创建试卷
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
     * 获取用户的试卷
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
     * 加入试卷
     */
    @PostMapping("/{userId}/exam-papers/{examPaperId}")
    public Result joinExamPaper(@PathVariable Long userId, @PathVariable Long examPaperId) {
        System.out.println(userId);
        System.out.println(examPaperId);
        StudentExamPaper studentExamPaper = new StudentExamPaper();
        studentExamPaper.setExamPaperId(examPaperId);
        studentExamPaper.setUserId(userId);
        studentExamPaper.setStatus("未完成");
        return studentExamPaperService.joinExamPaper(studentExamPaper) ? 
            Result.success("添加试卷成功") : 
            Result.error("添加失败你已经存在该试卷中");
    }

    /**
     * 获取学生加入的试卷
     */
    @GetMapping("/{userId}/student-exam-papers")
    public Result getExamPapersByStudentId(@PathVariable Long userId) {
        return studentExamPaperService.getJoinedExamPapersByStudentId(userId);
    }

    /**
     * 提交试卷答案
     */
    @PostMapping("/{userId}/exam-paper/{examPaperId}/student-answer")
    public Result insertStudentAnswer(
            @PathVariable Long userId, 
            @PathVariable Long examPaperId, 
            @RequestBody List<StudentAnswer> studentAnswerList) {
        System.out.println(userId);
        System.out.println(examPaperId);
        System.out.println(studentAnswerList);

        boolean flag = studentAnswerService.insertStudentAnswerList(userId, examPaperId, studentAnswerList);
        System.out.println(flag);
        if (flag) {
            return Result.success("所有答案已经全部提交");
        } else {
            return Result.error("答案提交失败");
        }
    }
    // 获取所有的学生模糊查询版本
    @GetMapping("/get-all-student")
    public Result getAllStudent(@RequestParam(value = "name" ) String name) {
        return iuserService.getAllStudent(name);

    }
    // 获取学生的错题数量信息
    @GetMapping("/{userId}/student_wrong_question_view")
    //获取学生的错题数量信息
    public Result getStudentWrongQuestionView(@PathVariable Long userId) {
        return iuserService.getStudentWrongQuestionView(userId);
    }
} 
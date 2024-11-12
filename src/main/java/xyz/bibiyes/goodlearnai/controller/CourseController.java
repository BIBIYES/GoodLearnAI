package xyz.bibiyes.goodlearnai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Course;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.service.CourseService;
import xyz.bibiyes.goodlearnai.service.QuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;


    /**
     * 获取所有课程
     *
     * @return 返回所有课程
     */
    @GetMapping
    public Result getAllCourses() {
        return Result.success("List of courses", courseService.selectCoursesAll());
    }

    /**
     * 编辑课程信息
     *
     * @param course 课程对象
     */
    @PutMapping("/{courseId}")
    public Result updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        course.setCourseId(courseId);
        boolean updated = courseService.updateCourse(course);
        if (updated) {
            return Result.success("Course updated successfully");
        } else {
            return Result.error("Failed to update course");
        }
    }

    /**
     * 删除课程
     *
     * @param courseId 课程 id
     * @return 返回删除成功或者删除失败
     */
    @DeleteMapping("/{courseId}")
    public Result deleteCourse(@PathVariable Long courseId) {
        boolean removed = courseService.deleteCourseById(courseId);
        if (removed) {
            return Result.success("course", "Course deleted successfully");
        } else {
            return Result.error("Failed to delete course");
        }
    }

    /**
     * 获取课程详情
     *
     * @param courseId 课程 id
     * @return 返回课程对象
     */
    @GetMapping("/{courseId}")
    public Result selectCourseById(@PathVariable Long courseId) {
        // 获取课程
        Course course = courseService.selectCourseById(courseId);
        // 如果课程id对象为空
        if (course == null) {
            return Result.error("Course not found");
        } else {
            // 返回课程对象
            return Result.success("Course retrieved successfully", course);
        }

    }

    /**
     * 创建新的题目。
     *
     * @param question 新题目的详细信息。
     * @return 如果创建成功，返回创建成功的信息，否则返回错误信息。
     */
    @PostMapping("/{courseId}/question")
    public Result createQuestion(@RequestBody Question question) {
        boolean saved = questionService.saveQuestion(question);
        if (saved) {
            return Result.success("Question created successfully", question);
        } else {
            return Result.error("Failed to create question");
        }
    }

    /**
     * 批量添加题目
     *
     * @param questions 待添加的题目列表。
     */
    @PostMapping("/{courseId}/questions")
    public Result createQuestions(@RequestBody List<Question> questions, @PathVariable Integer courseId) {
        questions.forEach(System.out::println);
        System.out.println(courseId);
        return questionService.saveQuestions(questions, courseId);

    }

    /**
     * 获取该课程下的所有题目
     *
     * @param courseId 课程的唯一标识。
     * @return 返回指定课程下的题目列表，如果课程没有题目则返回错误信息。
     */
    @GetMapping("{courseId}/questions")
    public Result getQuestionsByCourseId(@PathVariable Long courseId,
                                         @RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "10") int pageSize) {
        return questionService.getQuestionsByCourseId(courseId, pageNum, pageSize);
    }

    /**
     * 获取该课程下所有的题目支持模糊匹配
     */
    @GetMapping("/{courseId}/questions/search")
    public Result searchQuestions(@PathVariable Long courseId, @RequestParam(defaultValue = "") String keyWord) {
        return questionService.searchQuestions(courseId, keyWord);
    }


}


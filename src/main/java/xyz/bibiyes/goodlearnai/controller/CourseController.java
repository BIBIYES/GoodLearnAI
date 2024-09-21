package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Course;
import xyz.bibiyes.goodlearnai.service.CourseService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;
@RestController
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 创建一个课程
     * @param course 传入一个课程的实体
     * @return 返回创建成功或者创建失败
     */
    @PostMapping
    public Result createCourse(@RequestBody Course course) {
        boolean saved = courseService.createCourse(course);
        if (saved) {
            return Result.success( "Course created successfully", course);
        } else {
            return Result.error("Failed to create course");
        }
    }

    /**
     * 获取所有课程
     * @return 返回所有课程
     */
    @GetMapping
    public Result getAllCourses() {
        return Result.success("List of courses", courseService.getAllCourses());
    }

    /**
     * 通过 老师 id 来获取他的自己课程
     * @param teacherId 老师id
     * @return 返回老师他自己的课程
     */
    @GetMapping("/teachers/{teacherId}")
    public Result getCoursesByTeacherId(@PathVariable Long teacherId) {
        List<Course> courses = courseService.getCoursesByTeacherId(teacherId);
        if (courses != null && !courses.isEmpty()) {
            return Result.success("Courses retrieved successfully", courses);
        } else {
            return Result.error("No courses found for the teacher");
        }
    }

    /**
     * 通过课程 id 来获取课程
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
}


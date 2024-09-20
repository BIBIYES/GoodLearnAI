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
    // 创建课程
    @PostMapping
    public Result createCourse(@RequestBody Course course) {
        boolean saved = courseService.createCourse(course);
        if (saved) {
            return Result.success("course", "Course created successfully", course);
        } else {
            return Result.error("course", "Failed to create course");
        }
    }

    // 获取所有课程
    @GetMapping
    public Result getAllCourses() {
        return Result.success("course", "List of courses", courseService.getAllCourses());
    }

    // 通过教师 ID 获取该教师的课程
    @GetMapping("/teachers/{teacherId}")
    public Result getCoursesByTeacherId(@PathVariable Long teacherId) {
        List<Course> courses = courseService.getCoursesByTeacherId(teacherId);
        if (courses != null && !courses.isEmpty()) {
            return Result.success("course", "Courses retrieved successfully", courses);
        } else {
            return Result.error("course", "No courses found for the teacher");
        }
    }

    // 删除课程
    @DeleteMapping("/{courseId}")
    public Result deleteCourse(@PathVariable Long courseId) {
        boolean removed = courseService.deleteCourseById(courseId);
        if (removed) {
            return Result.success("course", "Course deleted successfully");
        } else {
            return Result.error("course", "Failed to delete course");
        }
    }
}


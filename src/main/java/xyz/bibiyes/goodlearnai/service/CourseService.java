package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.Course;
import java.util.List;

public interface CourseService {
    boolean createCourse(Course course); // 新增课程
    List<Course> getAllCourses(); // 获取所有课程
    List<Course> getCoursesByTeacherId(Long teacherId); // 根据教师ID获取课程
    boolean deleteCourseById(Long courseId); // 根据课程ID删除课程
}

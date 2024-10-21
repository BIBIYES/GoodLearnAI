package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.Course;
import java.util.List;

public interface CourseService {
    boolean insertCourse(Course course); // 新增课程
    List<Course> selectCoursesAll(); // 获取所有课程

    boolean deleteCourseById(Long courseId); // 根据课程ID删除课程

    List<Course> selectCoursesByUserId(Long userId);

    Course selectCourseById(Long courseId);

    boolean updateCourse(Course course);
}

package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.Course;
import xyz.bibiyes.goodlearnai.mapper.CourseMapper;
import xyz.bibiyes.goodlearnai.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public boolean createCourse(Course course) {
        // MyBatis-Plus 提供的 insert 方法
        return courseMapper.insert(course) > 0; // 返回影响的行数
    }

    @Override
    public List<Course> getAllCourses() {
        // 使用 MyBatis-Plus 提供的 selectList 方法
        return courseMapper.selectList(null);
    }

    @Override
    public List<Course> getCoursesByTeacherId(Long teacherId) {
        // 调用自定义的 Mapper 方法
        return courseMapper.getCoursesByTeacherId(teacherId);
    }

    @Override
    public boolean deleteCourseById(Long courseId) {
        // 调用自定义的 Mapper 方法
        return courseMapper.deleteById(courseId) > 0;
    }
}

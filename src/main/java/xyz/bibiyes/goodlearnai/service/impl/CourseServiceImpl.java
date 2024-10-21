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

    /**
     * 插入课程
     * @param course 课程对象
     * @return boolean
     */
    @Override
    public boolean insertCourse(Course course) {
        return courseMapper.insert(course) > 0; // 返回影响的行数
    }

    /**
     * 查询所有课程
     * @return List<Course>
     */
    @Override
    public List<Course> selectCoursesAll() {
        // 使用 MyBatis-Plus 提供的 selectList 方法
        return courseMapper.selectList(null);
    }

    /**
     * 通过课程id善除课程
     * @param courseId 课程id
     * @return boolean
     */
    @Override
    public boolean deleteCourseById(Long courseId) {
        // 调用自定义的 Mapper 方法
        return courseMapper.deleteById(courseId) > 0;
    }

    /**
     * 通过用户id查询课程
     * @param userId 用户id
     * @return List<Course>
     */
    @Override
    public List<Course> selectCoursesByUserId(Long userId) {
        return courseMapper.selectCoursesByUserId(userId);
    }

    @Override
    public Course selectCourseById(Long courseId) {
        return  courseMapper.selectById(courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
    	return courseMapper.updateById(course) > 0;
    }
}

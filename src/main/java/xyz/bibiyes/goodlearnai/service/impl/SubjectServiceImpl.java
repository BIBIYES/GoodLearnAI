package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.Subject;
import xyz.bibiyes.goodlearnai.mapper.SubjectMapper;
import xyz.bibiyes.goodlearnai.service.SubjectService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Resource SubjectMapper subjectMapper;

    @Override
    public List<Subject> list() {
        return baseMapper.selectList(null);  // Using MyBatis-Plus's built-in selectList method
    }

    @Override
    public boolean save(String name) {
        return subjectMapper.insertName(name) > 0;
    }


    @Override
    public boolean updateById(Subject subject) {
        return baseMapper.updateById(subject) > 0;  // Using MyBatis-Plus's built-in updateById method
    }

    @Override
    public boolean removeById(Long id) {
        return baseMapper.deleteById(id) > 0;  // Using MyBatis-Plus's built-in deleteById method
    }
}

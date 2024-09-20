package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.StudentAnswer;
import xyz.bibiyes.goodlearnai.mapper.StudentAnswerMapper;
import xyz.bibiyes.goodlearnai.service.StudentAnswerService;

@Service
public class StudentAnswerServiceImpl extends ServiceImpl<StudentAnswerMapper, StudentAnswer> implements StudentAnswerService {
}

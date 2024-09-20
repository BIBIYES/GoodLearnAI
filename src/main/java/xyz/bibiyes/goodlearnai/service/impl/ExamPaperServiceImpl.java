package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.ExamPaper;
import xyz.bibiyes.goodlearnai.mapper.ExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.ExamPaperService;

import java.util.List;

@Service
public class ExamPaperServiceImpl extends ServiceImpl<ExamPaperMapper, ExamPaper> implements ExamPaperService {

    @Override
    public List<ExamPaper> getExamPapersByTeacherId(Long teacherId) {
        QueryWrapper<ExamPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean deleteExamPaperById(Long examPaperId) {
        return this.removeById(examPaperId);
    }
}

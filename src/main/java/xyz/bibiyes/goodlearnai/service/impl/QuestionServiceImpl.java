package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bibiyes.goodlearnai.entity.Question;
import xyz.bibiyes.goodlearnai.mapper.QuestionMapper;
import xyz.bibiyes.goodlearnai.service.QuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getQuestionsByCourseId(Long courseId) {
        // 使用 QueryWrapper 来构造查询条件
        return questionMapper.selectList(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Question>().eq("course_id", courseId));
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionMapper.selectById(questionId); // 使用 MyBatis Plus 的 selectById 方法
    }

    @Override
    public boolean deleteQuestionById(Long questionId) {
        // 调用 Mapper 层的删除方法
        return questionMapper.deleteById(questionId) > 0;
    }

    @Override
    public boolean saveQuestion(Question question) {
        // 保存新题目
        return questionMapper.insert(question) > 0;
    }

    @Override
    @Transactional
    public Result saveQuestions(List<Question> questions, Integer courseId) {
        questions.forEach(question -> question.setCourseId(courseId));
        try {
            return saveBatch(questions) ? Result.success("批量插入成功"): Result.error("批量插入失败");
        } catch (Exception e) {
            return Result.error("批量插入失败");
        }

    }

    @Override
    public boolean updateQuestion(Question question) {
        // 更新题目
        return questionMapper.updateById(question) > 0;
    }

    @Override
    public List<Question> getAllQuestions() {
        // 查询所有题目
        return questionMapper.selectList(null);
    }
}

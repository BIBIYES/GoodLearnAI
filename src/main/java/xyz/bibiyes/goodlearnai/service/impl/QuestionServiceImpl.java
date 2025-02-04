package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Question getQuestionById(Long questionId) {
        return questionMapper.selectById(questionId); // 使用 MyBatis Plus 的 selectById 方法
    }


    @Override
    public Result getQuestionsByCourseId(Long courseId, int pageNum, int pageSize) {
        // 使用 PageHelper 开启分页
        PageHelper.startPage(pageNum, pageSize);  // 假设这里使用的是默认的分页参数，前端可以传递 pageNum 和 pageSize
        // 构造查询条件
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("course_id", courseId);
        List<Question> list = list(questionQueryWrapper);
        PageInfo<Question> pageInfo = new PageInfo<>(list);
        if(list.isEmpty()){
            return Result.error("空的课程");
        }
        return Result.success("查询成功", pageInfo);
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
            return saveBatch(questions) ? Result.success("批量插入成功") : Result.error("批量插入失败");
        } catch (Exception e) {
            return Result.error("批量插入失败");
        }

    }

    /**
     * 通过课程id来获取题目并且支持模糊查询
     *
     * @param courseId 课程id
     * @param keyword  关键词
     * @return 返回题目List
     */
    @Override
    public Result searchQuestions(Long courseId, String keyword) {
        LambdaQueryWrapper<Question> questionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        questionLambdaQueryWrapper.eq(Question::getCourseId, courseId).like(Question::getQuestionTitle, keyword);
        List<Question> list = list(questionLambdaQueryWrapper);
        // 判断是否为空
        return list.isEmpty() ? Result.error("没有找到相关题目") : Result.success("查询成功", list);
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

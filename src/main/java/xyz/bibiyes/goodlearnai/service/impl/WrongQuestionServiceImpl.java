package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.WrongQuestion;
import xyz.bibiyes.goodlearnai.mapper.WrongQuestionMapper;
import xyz.bibiyes.goodlearnai.service.IWrongQuestionService;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;

import java.util.List;

@Service
public class WrongQuestionServiceImpl extends ServiceImpl<WrongQuestionMapper, WrongQuestion> implements IWrongQuestionService {
    
    @Override
    public Result addWrongQuestion(WrongQuestion wrongQuestion) {
        wrongQuestion.setIsDeleted(false);
        boolean save = save(wrongQuestion);
        if(save){
            return Result.success("添加错题成功");
        }
        return Result.error("添加失败");
    }

    @Override
    public List<WrongQuestionVO> getWrongQuestions(Long userId, String examPaperName, String questionTitle) {
        return baseMapper.selectWrongQuestions(userId, examPaperName, questionTitle);
    }

    @Override
    public Result deleteByWrongQuestionsId(Long wrongQuestionsId, Long userId) {
        // 检查参数是否为空
        if (wrongQuestionsId == null || userId == null) {
            return Result.error("问题ID或用户ID不能为空");
        }

        // 构建更新条件并执行伪删除
        boolean update = update(new LambdaUpdateWrapper<WrongQuestion>()
                .eq(WrongQuestion::getQuestionId, wrongQuestionsId)  // 条件：匹配问题ID
                .eq(WrongQuestion::getUserId, userId)                // 条件：匹配用户ID
                .set(WrongQuestion::getIsDeleted, 1));               // 设置伪删除标记为1

        if (update) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

}

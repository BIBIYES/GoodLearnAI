package xyz.bibiyes.goodlearnai.service.impl;

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
}

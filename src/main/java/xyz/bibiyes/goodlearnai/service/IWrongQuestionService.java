package xyz.bibiyes.goodlearnai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.bibiyes.goodlearnai.entity.WrongQuestion;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luozihao
 * @since 2024-12-03
 */
public interface IWrongQuestionService extends IService<WrongQuestion> {

    Result addWrongQuestion(WrongQuestion wrongQuestion);
    
    // 添加新的查询方法
    List<WrongQuestionVO> getWrongQuestions(Long userId, String examPaperName, String questionTitle);
}

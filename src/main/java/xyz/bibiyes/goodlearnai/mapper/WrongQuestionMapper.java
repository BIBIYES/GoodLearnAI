package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.bibiyes.goodlearnai.entity.WrongQuestion;
import xyz.bibiyes.goodlearnai.vo.WrongQuestionVO;

import java.util.List;

@Mapper
public interface WrongQuestionMapper extends BaseMapper<WrongQuestion> {
    List<WrongQuestionVO> selectWrongQuestions(
        @Param("userId") Long userId,
        @Param("examPaperName") String examPaperName,
        @Param("questionTitle") String questionTitle
    );
}

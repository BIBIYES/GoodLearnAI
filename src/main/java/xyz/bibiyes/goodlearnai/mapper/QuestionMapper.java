package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.bibiyes.goodlearnai.entity.Question;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}

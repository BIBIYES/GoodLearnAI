package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import xyz.bibiyes.goodlearnai.entity.ChatMessage;
import xyz.bibiyes.goodlearnai.mapper.ChatMessageMapper;
import xyz.bibiyes.goodlearnai.service.IChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luozihao
 * @since 2024-12-05
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements IChatMessageService {

    @Override
    public Result getMessageHistory(Long sessionId) {
        List<ChatMessage> list = list(new LambdaQueryWrapper<ChatMessage>().eq(ChatMessage::getSessionId, sessionId));
        if(list.isEmpty()){
            return Result.success("未有历史数据");
        }
        return Result.success("查询成功", list);
    }

    @Override
    public Result addMessage(ChatMessage chatMessage) {
        boolean save = save(chatMessage);
        if(save){
            return Result.success("添加成功");
        }
        return Result.error("添加失败");

    }
}

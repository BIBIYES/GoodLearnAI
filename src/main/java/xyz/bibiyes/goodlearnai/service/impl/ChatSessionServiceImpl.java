package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import xyz.bibiyes.goodlearnai.entity.ChatSession;
import xyz.bibiyes.goodlearnai.mapper.ChatSessionMapper;
import xyz.bibiyes.goodlearnai.service.IChatSessionService;
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
public class ChatSessionServiceImpl extends ServiceImpl<ChatSessionMapper, ChatSession> implements IChatSessionService {

    @Override
    public Result getUserChatSession(Long userId) {
        List<ChatSession> chatSessions = list(new LambdaQueryWrapper<ChatSession>().eq(ChatSession::getUserId, userId));
        if(chatSessions.isEmpty()){
            return Result.success(  "暂无数据");
        }else {
            return Result.success("查询成功", chatSessions);
        }
    }

    @Override
    public Result createSession(ChatSession chatSession) {
        boolean save = save(chatSession); // MyBatis-Plus 的 save 方法会回填 ID
        if (save) {
            return Result.success("创建成功", chatSession); // 返回插入成功的数据
        } else {
            return Result.error("创建失败");
        }
    }

}

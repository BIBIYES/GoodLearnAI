package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.ChatSession;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.bibiyes.goodlearnai.utils.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luozihao
 * @since 2024-12-05
 */
public interface IChatSessionService extends IService<ChatSession> {

    Result getUserChatSession(Long userId);

    Result createSession(ChatSession chatSession);
}

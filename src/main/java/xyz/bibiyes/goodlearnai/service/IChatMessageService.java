package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.ChatMessage;
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
public interface IChatMessageService extends IService<ChatMessage> {

    Result getMessageHistory(Long sessionId);

    Result addMessage(ChatMessage chatMessage);
}

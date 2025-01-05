package xyz.bibiyes.goodlearnai.controller;


import org.springframework.web.bind.annotation.*;

import xyz.bibiyes.goodlearnai.entity.ChatMessage;
import xyz.bibiyes.goodlearnai.service.IChatMessageService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luozihao
 * @since 2024-12-05
 */
@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {
    @Resource
    private IChatMessageService iChatMessageService;
    // 获取消息历史
    @GetMapping("/getMessageHistory/{sessionId}")
    public Result getMessageHistory(@PathVariable Long sessionId) {
      return iChatMessageService.getMessageHistory(sessionId);
    }
    // 添加消息
    @PostMapping("/addMessage")
    public Result addMessage(@RequestBody ChatMessage chatMessage) {
        return iChatMessageService.addMessage(chatMessage);
    }
}

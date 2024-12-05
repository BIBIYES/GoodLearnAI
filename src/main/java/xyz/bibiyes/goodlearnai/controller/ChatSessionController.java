package xyz.bibiyes.goodlearnai.controller;


import org.springframework.web.bind.annotation.*;

import xyz.bibiyes.goodlearnai.entity.ChatSession;
import xyz.bibiyes.goodlearnai.service.IChatSessionService;
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
@RequestMapping("/chat-session")
public class ChatSessionController {
    @Resource
    private IChatSessionService iChatSessionService;
    // 获取用户所有的会话
    @GetMapping("/get-user-chat-session")
    public Result getUserChatSession(@RequestParam Long userId) {
        return iChatSessionService.getUserChatSession(userId);
    }

    @PostMapping
    public Result createSession(@RequestBody ChatSession chatSession) {
        return iChatSessionService.createSession(chatSession);
    }
}

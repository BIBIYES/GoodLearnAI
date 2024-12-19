package xyz.bibiyes.goodlearnai.controller;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import com.plexpt.chatgpt.entity.chat.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
    

import java.net.Proxy;
import java.util.Arrays;

@RestController
@CrossOrigin
public class ChatController {

    @GetMapping("/chat/sse")

    public SseEmitter sseEmitter(String prompt) {

        // 初始化 ChatStream 对象

        ChatGPTStream chatGPTStream = ChatGPTStream.builder().timeout(600).apiKey("fastgpt-xCyK8iQF4gSmmiZeeAcHarJC58XikqPQizjOilK3k5eumw9PilcXISSc")
//                .proxy(proxy)
                .apiHost("http://10.181.146.107/api/") // 替换为你的实际 API 主机地址
                .build().init();

        // 创建 SseEmitter 对象，用于推送事件到客户端
        SseEmitter sseEmitter = new SseEmitter(-1L); // -1L 表示不超时

        // 创建 SseStreamListener，用于处理流式响应并通过 SseEmitter 发送
        SseStreamListener listener = new SseStreamListener(sseEmitter);
        Message message = Message.of(prompt);
        // 设置完成回调
        listener.setOnComplete(msg -> {
            // 回答完成后可以做一些处理
            System.out.println("Response completed: " + msg);

        });

        // 开始流式处理并将结果通过 SseEmitter 发送到客户端
        chatGPTStream.streamChatCompletion(Arrays.asList(message), listener);

        // 返回 SseEmitter 对象，开始向客户端推送事件
        return sseEmitter;
    }


}



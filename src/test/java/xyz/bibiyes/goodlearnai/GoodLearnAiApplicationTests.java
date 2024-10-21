package xyz.bibiyes.goodlearnai;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.Proxy;
import java.util.Arrays;

@SpringBootTest
class GoodLearnAiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void gptTest() {
        // 设置代理，以便访问 OpenAI API
        Proxy proxy = Proxys.http("127.0.0.1", 10808);

        // 初始化 ChatGPTStream 对象
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .apiKey("fastgpt-ejnDcudPOSfENpkEZxqMw2VcgHFapjSu4rmwRoiER6iqGd0NjXIfkt00n")
                .proxy(proxy)
                .apiHost("https://api.fastgpt.in/api/") // 使用你特定的 API 主机地址
                .build()
                .init();

        // 设置监听器，用于处理流式响应并打印到控制台
        ConsoleStreamListener listener = new ConsoleStreamListener();

        // 创建一个消息发送到 ChatGPT API
        Message message = Message.of("写一段七言绝句诗，题目是：火锅！");

        // 创建 ChatCompletion 对象以构建请求
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(Arrays.asList(message))
                .build();

        // 发送请求并将响应流式传输到控制台
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
    }
}

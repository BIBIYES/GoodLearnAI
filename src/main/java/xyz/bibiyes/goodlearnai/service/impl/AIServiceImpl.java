package xyz.bibiyes.goodlearnai.service.impl;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.service.AIService;

import java.net.Proxy;

@Service
public class AIServiceImpl  implements AIService {
    @Override
    public String getAI(String str){
        //国内需要代理
        Proxy proxy = Proxys.http("127.0.0.1", 7897);


        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("fastgpt-ejnDcudPOSfENpkEZxqMw2VcgHFapjSu4rmwRoiER6iqGd0NjXIfkt00n")
                .proxy(proxy)
                .apiHost("https://api.fastgpt.in/api/") //反向代理地址
                .build()
                .init();

        return chatGPT.chat(str);
    }

}

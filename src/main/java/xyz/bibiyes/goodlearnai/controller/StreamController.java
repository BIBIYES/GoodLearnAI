package xyz.bibiyes.goodlearnai.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class StreamController {

    @GetMapping("/stream")
    public SseEmitter stream() {
        // 设置无限超时，避免流式请求超时
        SseEmitter emitter = new SseEmitter(0L);

        CompletableFuture.runAsync(() -> {
            try {
                OkHttpClient client = new OkHttpClient.Builder().build();

                // 构造请求JSON字符串，根据实际需求修改
                String requestBodyStr = "{\n" +
                        "    \"chatId\": \"my_chatId\",\n" +
                        "    \"stream\": true,\n" +
                        "    \"detail\": false,\n" +
                        "    \"responseChatItemId\": \"my_responseChatItemId\",\n" +
                        "    \"variables\": {\n" +
                        "        \"uid\": \"asdfadsfasfd2323\",\n" +
                        "        \"name\": \"张三\"\n" +
                        "    },\n" +
                        "    \"messages\": [\n" +
                        "        {\n" +
                        "            \"role\": \"user\",\n" +
                        "            \"content\": \"导演是谁\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";

                Request request = new Request.Builder()
                        .url("https://cloud.fastgpt.cn/api/v1/chat/completions") // 请根据实际地址修改
                        .addHeader("Authorization", "Bearer fastgpt-ygf1hf0C7jdXTucxaArlqnG3VozOdyhU5qdppASyLR9ID5tPtGV4sHZZRhz") // 实际的Token
                        .post(RequestBody.create(
                                MediaType.parse("application/json"),
                                requestBodyStr
                        ))
                        .build();

                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Fastgpt request failed: " + response);
                }

                // 获取响应流并逐行读取
                InputStream is = response.body().byteStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    // fastgpt stream 返回的格式中，每行以 "data: " 开头表示一个块
                    if (line.startsWith("data: ")) {
                        String jsonStr = line.substring("data: ".length()).trim();
                        if ("[DONE]".equals(jsonStr)) {
                            // 完成信号
                            emitter.send(SseEmitter.event().name("done").data("[DONE]"));
                            break;
                        } else {
                            // 解析JSON，提取content
                            try {
                                JSONObject json = new JSONObject(jsonStr);
                                JSONArray choices = json.optJSONArray("choices");
                                if (choices != null && choices.length() > 0) {
                                    JSONObject delta = choices.getJSONObject(0).optJSONObject("delta");
                                    if (delta != null) {
                                        String content = delta.optString("content", "");
                                        // 将content内容通过SSE推送给前端
                                        emitter.send(SseEmitter.event().name("message").data(content));
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                reader.close();
                is.close();
                response.close();

                // SSE完成
                emitter.complete();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    emitter.send(SseEmitter.event().name("error").data("Server Error"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}

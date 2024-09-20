package xyz.bibiyes.goodlearnai.controller;

import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.service.AIService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodlearnai")
@CrossOrigin
public class AIController {

    @Resource
    AIService aiService;
    @GetMapping("/ai")
    public Result getAI(@RequestParam String str) {
        aiService.getAI(str);
        return Result.success("AI", "AI已经响应完成",aiService.getAI(str));
    }
    @GetMapping("/aii")
    public String getMessage(){
        return "热重载感觉有点慢";
    }
}

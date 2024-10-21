package xyz.bibiyes.goodlearnai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/static{path:[^.]*}")
    public String forward() {
        // 将所有未匹配到的路径转发到index.html
        return "forward:/index.html";
    }
}

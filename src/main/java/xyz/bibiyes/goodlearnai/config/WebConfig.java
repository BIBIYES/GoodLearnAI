package xyz.bibiyes.goodlearnai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


 /**
  * @author Sakura
  */
 @Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置跨域请求的映射规则
     * 此方法用于解决前端跨域请求的问题，通过指定允许的源、方法、头等信息来实现跨域访问的控制
     *
     * @param registry 跨域请求的注册对象，用于配置跨域的相关规则
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        // 本地开发环境
                        "http://localhost",
                        "http://localhost:5173",
                        // 部署的前端地址（使用域名）
                        "http://bibiyes.xyz/",
                        // 服务器的前端地址（使用IP）
                        "http://8.137.114.76"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许所有请求头
                .allowedHeaders("*")
                // 允许发送凭证（如 Cookies）
                .allowCredentials(true);
    }

}

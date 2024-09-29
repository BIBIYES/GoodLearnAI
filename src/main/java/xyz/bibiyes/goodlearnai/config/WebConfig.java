package xyz.bibiyes.goodlearnai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        // 配置全局跨域请求处理，允许所有来源，支持所有HTTP方法，并允许发送凭证（如Cookies）
        registry.addMapping("/**") // 对所有请求路径生效
                .allowedOrigins("*") // 允许所有来源
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowCredentials(true) // 允许发送凭证
                .maxAge(3600) // 预检请求的结果缓存时间，单位为秒
                .allowedHeaders("*"); // 允许所有请求头
    }

}

package xyz.bibiyes.goodlearnai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mouse Sakura
 */
@SpringBootApplication
@MapperScan("xyz.bibiyes.goodlearnai.mapper")
public class GoodLearnAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodLearnAiApplication.class, args);
    }

}

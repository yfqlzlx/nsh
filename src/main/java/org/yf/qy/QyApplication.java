package org.yf.qy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("org.yf.qy.mapper")
@ComponentScan(basePackages = "org.yf")
public class QyApplication {
    public static void main(String[] args) {
        SpringApplication.run(QyApplication.class, args);
    }
}

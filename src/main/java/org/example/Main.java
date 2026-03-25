package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 이 어노테이션이 스프링 부트의 핵심 엔진입니다!
public class Main {
    public static void main(String[] args) {
        // 이 한 줄이 톰캣 서버를 올리고 백엔드를 구동시킵니다.
        SpringApplication.run(Main.class, args);
    }
}
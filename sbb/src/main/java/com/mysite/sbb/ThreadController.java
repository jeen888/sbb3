package com.mysite.sbb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JDK21+ 가상스레드 동작 테스트
 */
@RestController
public class ThreadController {
    @GetMapping("/thread-test")
    public String getThreadInfo() {
        Thread currentThread=Thread.currentThread();

        // 가상스레드 여부와 이름을 반환
        return String.format("Is Virtual Thread: %b | Thread Name: %s",
            currentThread.isVirtual(),
            currentThread.toString()
        );
    }
}

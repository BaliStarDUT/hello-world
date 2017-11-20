package com.james.springboot.service.mq.reveiver;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageReceiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    public void reveiveMessage(String message){
        System.out.println("Received <"+message+">");
        latch.countDown();
    }
}

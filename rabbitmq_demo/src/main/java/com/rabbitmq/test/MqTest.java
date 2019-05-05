package com.rabbitmq.test;

import com.rabbitmq.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void testSend1(){
        rabbitTemplate.convertAndSend("itcast","直接模式");
    }

    /**
     * 分裂模式
     */
    @Test
    public void testSend2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式");
    }
    /**
     * 主题模式
     */
    @Test
    public void testSend3(){
        rabbitTemplate.convertAndSend("topic84","good.log","主题模式");
    }
}

package com.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/10 16:12
 * @package com.test
 */
@Component
public class RabbitmqConsume {
    @RabbitListener(queues = "item_queue")
    public void sendMQ(String message){
        System.out.println("消费者接收到的消息为：" + message);
    }
}

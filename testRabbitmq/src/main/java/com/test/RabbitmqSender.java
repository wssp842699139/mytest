package com.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/10 16:06
 * @package com.test
 */
@Controller
public class RabbitmqSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendMQ")
    public void sendMQ(){
        Message message = new Message("hello".getBytes(),new MessageProperties());
        rabbitTemplate.convertAndSend("item_topic_exchange","item.test",message);

    }
}

package com.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.test.RabbitmqConfig.BUSINESS_EXCHANGE_NAME;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/10 16:06
 * @package com.test
 */
@RestController
public class RabbitmqSender {
    @Autowired
    RabbitTemplate rabbitTemplate;


    @RequestMapping(value = "/sendMQ",method = RequestMethod.GET)
    public String sendMQ(){
        Message message = new Message("hello".getBytes(),new MessageProperties());
        rabbitTemplate.convertAndSend("item_topic_exchange","item.test",message);
        return "success";
    }

    @RequestMapping(value = "/sendDelayMQ", method = RequestMethod.GET)
    public String sendDelayMQ(String msg){
        msg = "发送时间："+new Date() + msg;
        System.out.println(msg);
        rabbitTemplate.convertSendAndReceive(BUSINESS_EXCHANGE_NAME, "", msg,message -> {
            message.getMessageProperties().setExpiration(1000 * 10 + "");
            return message;
        });
        return "success";
    }

}

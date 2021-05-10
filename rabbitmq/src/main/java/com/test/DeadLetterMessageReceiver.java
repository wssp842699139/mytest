package com.test;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static com.test.RabbitmqConfig.DEAD_LETTER_QUEUEA_NAME;
import static com.test.RabbitmqConfig.DEAD_LETTER_QUEUEB_NAME;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/21 22:27
 * @package com.test
 */
@Component
public class DeadLetterMessageReceiver {
    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息B：" + new String(message.getBody()));
        System.out.println("收到死信消息B时间为：" + new Date());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}

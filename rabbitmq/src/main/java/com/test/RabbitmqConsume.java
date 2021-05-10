package com.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/10 16:12
 * @package com.test
 */
@Component
public class RabbitmqConsume {
    @RabbitListener(queues = "item_queue")
    //@QueueBinding注解的三个属性：
    //value: @Queue 注解，用于声明队列，value 为 queueName, durable 表示队列是否持久化, autoDelete 表示没有消费者之后队列是否自动删除
    //exchange: @Exchange 注解，用于声明 exchange， type 指定消息投递策略，我们这里用的 topic 方式
    //key: 在 topic 方式下，这个就是我们熟知的 routingKey
    //默认 ack 方式(noack, auto, manual)
    public void sendMQ(String message){
        System.out.println(message.toString());
        //System.out.println(message.getMessageProperties().getReceivedDelay());
        System.out.println("-----------收到消息:"+message+",当前时间："+new Date());

    }



    //默认 ack 方式(noack, auto, manual)
    //deliveryTag: 相当于消息的唯一标识，用于 mq 辨别是哪个消息被 ack/nak 了
    //channel: mq 和 consumer 之间的管道，通过它来 ack/nak
    /*@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "topic.n3", durable = "false", autoDelete = "true"),
            exchange = @Exchange(value = "topic.e", type = ExchangeTypes.TOPIC), key = "r"), ackMode = "MANUAL")
    public void consumerDoAck(String data, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel)
            throws IOException {
        System.out.println("consumerDoAck: " + data);

        if (data.contains("success")) {
            // RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
            channel.basicAck(deliveryTag, false);
        } else {
            // 第三个参数true，表示这个消息会重新进入队列
            channel.basicNack(deliveryTag, false, true);
        }
    }*/

}

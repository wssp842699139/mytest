package com.test;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/7 16:36
 * @package com.test
 */

@Configuration
public class RabbitmqConfig {
    /***
     * 声明交换机
     */
    @Bean(name = "itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange("item_topic_exchange").durable(true).build();
    }

    /***
     * 声明队列
     */
    @Bean(name = "itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable("item_queue").build();
    }

    /***
     * 队列绑定到交换机上
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemQueue")Queue queue,
                                     @Qualifier("itemTopicExchange")Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }

}

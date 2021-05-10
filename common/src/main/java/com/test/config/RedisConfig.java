package com.test.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    //@Value("${spring.redis.host}")
    private String host = "localhost";

   // @Value("${spring.redis.port}")
    private Integer port = 6379;

    //@Value("${spring.redis.password}")
    private String password = null;

   // @Value("${spring.redis.database}")
    private Integer database = 0;

    //@Value("${spring.redis.timeout:6000}")
    private Integer timeout = 2000;

    //@Value("${spring.redis.jedis.pool.max-active}")
    private int redisPoolMaxActive = 8;

    //@Value("${spring.redis.jedis.pool.max-wait}")
    private int redisPoolMaxWait = 10;

    //@Value("${spring.redis.jedis.pool.max-idle}")
    private int redisPoolMaxIdle = 30;

    //@Value("${spring.redis.jedis.pool.min-idle}")
    private int redisPoolMinIdle = 10;

    //@Value("${redis.user.host}")
    private String userHost = "localhost";
    //@Value("${redis.user.port}")
    private Integer userPort = 6379;
    //@Value("${redis.user.password}")
    private String userPassword = null;


    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = setPoolConfig(redisPoolMaxIdle, redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true);

        if (StringUtils.isBlank(this.password)){
            return new JedisPool(jedisPoolConfig,this.host, this.port,this.timeout,null,this.database);
        }
        return new JedisPool(jedisPoolConfig,this.host, this.port,this.timeout,this.password,this.database);
    }

//    @Bean
    public RedisConnectionFactory redisConnectionFactory(int db) {
        return createJedisConnectionFactory(db, host, port, password, timeout);
    }
    /*@Bean(name = "StockConnectionFactory")
    @Primary
    public RedisConnectionFactory ConnectionFactory() {
        Integer dbindex = this.database;
        return createJedisConnectionFactory(dbindex, host, port, password, timeout);
    }

    @Bean(name = "ExpireKeyConnectionFactory")
    public RedisConnectionFactory ExpireKeyConnectionFactory() {
        return createJedisConnectionFactory(8, host, port, password, timeout);
    }*/
/*
    @Bean(name = "redisDb2")
    public RedisTemplate getRedisDb2() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(createJedisConnectionFactory(2, userHost, userPort, userPassword, timeout));

        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 设置value的序列化规则和 key的序列化规则
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }*/

    /*@Bean(name = "redisDb5")
    public RedisTemplate getRedisDb5() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory(5));
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }*/
    //商品使用
    /*@Bean(name = "redisDb4")
    public RedisTemplate getRedisDb4() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory(4));
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }*/

    /*@Bean(name = "redisDb10")
    public RedisTemplate getRedisDb10() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory(10));
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }*/
    //过期监听 事件专用库
    /*@Bean(name = "expireDb")
    public RedisTemplate getRedisDb1() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory(8));
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }
*/
    /*@Bean(name = "sessionRedisTemplate")
    public RedisTemplate getSessionRedisTemplate() {
        int dbIndex=10;
        RedisTemplate template = new RedisTemplate();
        JedisConnectionFactory jedisConnectionFactory = this.createJedisConnectionFactory(dbIndex, this.userHost, this.userPort, this.userPassword, timeout);
        template.setConnectionFactory(jedisConnectionFactory);
        setSerializer(template);
        return template;
    }*/

    public void setSerializer(RedisTemplate redisTemplate) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //设置键（key）的序列化方式

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());


        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
       /* FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();*/

    }


    /**
     * @return 自定义redisTemplate，自带的bean没有序列化器
     */
    /*@Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("StockConnectionFactory")RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        //将数据信息转化为json格式  redis服务器中可以查看数据信息
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }*/

    public JedisConnectionFactory createJedisConnectionFactory(int dbIndex, String host, int port, String password, int timeout) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(dbIndex);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        if (StringUtils.isNotBlank(password)){
            jedisConnectionFactory.setPassword(password);
        }
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setPoolConfig(setPoolConfig(redisPoolMaxIdle, redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true));
        return jedisConnectionFactory;
    }

    public JedisPoolConfig setPoolConfig(int maxIdle, int minIdle, int maxActive, int maxWait, boolean testOnBorrow) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }

}

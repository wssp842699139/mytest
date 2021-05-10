package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/13 17:13
 * @package com.es
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("8.129.185.160", 9200, "http")
                )
        );
        return client;
    }
}

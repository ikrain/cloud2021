package cn.krain.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author CC
 * @data 2021/2/1 - 23:35
 */
@Configuration
public class ApplicationContextConfig {
    /*  注解@Bean
    *   创建Bean并加入到容器中
    *   等同于application.xml文件中的<bean id="" class="">
    * */
    @Bean
    @LoadBalanced       // 该注解用于赋予RestTemplate负载均衡的能力，同时eureka已经集成了ribbon，无需再次在pom文件中引入
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

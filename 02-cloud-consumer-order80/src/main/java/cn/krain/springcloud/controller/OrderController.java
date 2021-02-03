package cn.krain.springcloud.controller;

import cn.krain.springcloud.entities.CommentResult;
import cn.krain.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author CC
 * @data 2021/2/1 - 23:38
 */
@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;          // 与RedisTemplate性质相同

    @GetMapping(value = "/consumer/payment/create")
    private CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommentResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    private CommentResult<Payment> getPaymentById(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommentResult.class);
    }
}

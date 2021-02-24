package cn.krain.springcloud.controller;

import cn.krain.springcloud.entities.CommentResult;
import cn.krain.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommentResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommentResult<Payment> getPaymentById(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommentResult.class);
    }

    /*
    *   使用RestTemplate的getForEntity方法，获取entity对象（该对象包含响应头、响应状态码、响应体等信息）
    *   getForObject方法只包含相应数据（json）
    * */
    @GetMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommentResult<Payment> getPaymentById2(@PathVariable("id") long id){
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id, CommentResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getHeaders()+"\t"+entity.getStatusCode());
            return entity.getBody();
        }else {
            return new CommentResult(444,"操作失败");
        }
    }
}

package cn.krain.springcloud.controller;

import cn.krain.springcloud.entities.CommentResult;
import cn.krain.springcloud.entities.Payment;
import cn.krain.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CC
 * @data 2021/2/1 - 22:15
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPost;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    private CommentResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入数据结果："+result);
        if (result>0){
            return new CommentResult(200,"数据插入成功,端口号："+serverPost,result);
        }
        return new CommentResult(400,"数据插入失败",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    private CommentResult getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询数据结果："+payment);
        if (payment!=null){
            return new CommentResult(200,"数据查询成功,端口号："+serverPost,payment);
        }
        return new CommentResult(400,"数据查询失败",null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services){
            log.info("****element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}

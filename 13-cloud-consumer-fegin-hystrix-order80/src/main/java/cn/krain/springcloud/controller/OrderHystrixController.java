package cn.krain.springcloud.controller;

import cn.krain.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CC
 * @data 2021/2/25 - 19:50
 *
 * 全局Fallback的配置：
 *      1. @DefaultProperties(defaultFallback = "")：设置全局fallback方法
 *      2. 编写fallback方法
 *      3. 在服务方法上方添加 @HystrixCommand 注解
 * 如果在 @HystrixCommand 注解中包含fallback，则为自定义fallback
 *
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        //int a = 10/0;       // 用于测试全局fallback
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("paymentInfo_OK_result:"+result);
        return result;
    }

    /**
     * 在消费端进行服务降级，如果服务提供方或消费方任一出现请求超时或异常，系统都会进行服务降级，跳转到对应的兜底服务：paymentTimeoutFallbackMethod
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("paymentInfo_Timeout_result:"+result);
        return result;
    }

    public String paymentTimeoutFallbackMethod(Integer id){
        return "我是消费者80，对方支付系统出错，请10秒后再试";
    }

    public String globalFallback(){
        return "Global异常处理信息，请稍后再试......";
    }
}

package cn.krain.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author CC
 * @data 2021/2/25 - 22:29、
 *
 * 使用PaymentHystrixServiceImpl实现PaymentHystrixService接口，使该类作为PaymentHystrixService接口的服务降级处理类，包含所有服务的fallback
 * 当服务端宕机时，客户端也会收到信息，而不会挂起耗死服务器
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "--------------PaymentHystrixService fall back paymentInfo_OK-------------";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "--------------PaymentHystrixService fall back paymentInfo_Timeout-------------";
    }
}

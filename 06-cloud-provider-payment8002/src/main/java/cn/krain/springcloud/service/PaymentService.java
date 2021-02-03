package cn.krain.springcloud.service;

import cn.krain.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author CC
 * @data 2021/2/1 - 22:12
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}

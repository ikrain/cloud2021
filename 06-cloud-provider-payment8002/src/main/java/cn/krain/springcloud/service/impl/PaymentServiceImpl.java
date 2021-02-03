package cn.krain.springcloud.service.impl;

import cn.krain.springcloud.dao.PaymentDao;
import cn.krain.springcloud.entities.Payment;
import cn.krain.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CC
 * @data 2021/2/1 - 22:13
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

package com.mazn.springcloud.controller;

import com.mazn.springcloud.entities.CommonResult;
import com.mazn.springcloud.entities.Payment;
import com.mazn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //没有@RequestBody,url上的参数无法映射到实体类中
        int result = paymentService.create(payment);
        if (result>0){
            return new CommonResult(200,"插入数据成功",result);
        }else {
            return new CommonResult(500,"插入数据失败",null);
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            log.info("查询数据成功，{}",payment);
            return new CommonResult(200,"查询数据成功", payment);
        }else {
            log.error("查询数据失败,查询id为：{}",id);
            return new CommonResult(500,"查询数据失败,查询id为："+id, null);
        }
    }
}

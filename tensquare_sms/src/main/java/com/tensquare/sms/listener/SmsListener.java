package com.tensquare.sms.listener;

import com.aliyuncs.CommonResponse;
import com.tensquare.sms.util.SmsUtil;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void executeSms(Map<String,String> map){
        System.out.println("手机号"+map.get("mobile"));
        System.out.println("验证码"+map.get("checkcode"));
        CommonResponse response = smsUtil.sendSms(map.get("mobile"), "{\"code\":\"" + map.get("checkcode") + "\"}");
        System.out.println(response.getData());
    }

}

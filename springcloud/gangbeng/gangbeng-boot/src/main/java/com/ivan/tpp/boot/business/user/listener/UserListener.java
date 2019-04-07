package com.ivan.tpp.boot.business.user.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
@RabbitListener(queues="exchangeOne.queue")
public class UserListener {
	
	private static final Logger logger = LoggerFactory.getLogger(UserListener.class);
	
	
	
	@RabbitHandler
    public void process(@Header(name="comein",required=false) Integer head,@Header(value="comein",required=false) List<Integer> list, byte[] bytes) {
		logger.info("{}========Message========Receiver  : {} ===value  {}", head , new String(bytes),JSON.toJSONString(list));
    }
	
//	@RabbitListener(queues="exchangeOne.queue")
//    public void processOrder(@Header(name="comein", value="1") Message message) {
//        System.out.println("================Receiver  : " + new String(message.getBody()));
//    }
//	@RabbitHandler
//    public void process(@Header(name="comein", value="1") Message message) {
//        System.out.println("================Receiver  : " + new String(message.getBody()));
//    }
}

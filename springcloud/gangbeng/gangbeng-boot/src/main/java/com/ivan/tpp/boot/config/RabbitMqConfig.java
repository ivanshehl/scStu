package com.ivan.tpp.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;

@Configuration
public class RabbitMqConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);
	
	@Bean
	public MessageConverter jackson2JsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public MessageConverter simpleMessageConverter(){
		return new SimpleMessageConverter();
	}
	
	@Bean
	public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory,RabbitProperties properties,MessageConverter jackson2JsonMessageConverter) {
		return baseRabbitTemplateCreate(connectionFactory, properties, jackson2JsonMessageConverter);
	}
	
	@Bean
	public RabbitTemplate simpleRabbitTemplate(ConnectionFactory connectionFactory,RabbitProperties properties,MessageConverter simpleMessageConverter) {
		return baseRabbitTemplateCreate(connectionFactory, properties, simpleMessageConverter);
	}

	private boolean determineMandatoryFlag(RabbitProperties properties) {
		Boolean mandatory = properties.getTemplate().getMandatory();
		return (mandatory != null) ? mandatory : properties
				.isPublisherReturns();
	}
	
	private boolean determineMandatory2Flag(RabbitProperties properties) {
		Boolean mandatory = properties.getTemplate().getMandatory();
		return (mandatory != null) ? mandatory : false;
	}
	
	private RabbitTemplate baseRabbitTemplateCreate(ConnectionFactory connectionFactory,RabbitProperties properties,MessageConverter messageConverter){
		try {
			connectionFactory.createConnection().createChannel(false).waitForConfirms();
		} catch (AmqpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		rabbitTemplate.setMandatory(determineMandatory2Flag(properties));
		rabbitTemplate.setConfirmCallback(new ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack,
					String cause) {
				if(ack){
					logger.info("ack======================success");
				}else{
					logger.info("ack======================error=========={}",cause);
				}
			}
		});
//		rabbitTemplate.setReturnCallback(new ReturnCallback() {
//			@Override
//			public void returnedMessage(Message message, int replyCode,
//					String replyText, String exchange, String routingKey) {
//				// TODO Auto-generated method stub
//				logger.info("message={}=======replyCode={}=========replyText={}======exchange={}=======routingKey={}",new Object[]{JSON.toJSONString(message),replyCode,replyText,exchange,routingKey});
//			}
//		});
		return rabbitTemplate;
	}
}

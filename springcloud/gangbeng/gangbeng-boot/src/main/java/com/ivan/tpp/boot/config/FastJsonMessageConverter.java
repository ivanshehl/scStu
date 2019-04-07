package com.ivan.tpp.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.MessageConversionException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FastJsonMessageConverter extends AbstractJsonMessageConverter {
	
	private static Logger log = LoggerFactory.getLogger(FastJsonMessageConverter.class);

	private ObjectMapper jsonObjectMapper;

	private Jackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
	
	@Override
	protected Message createMessage(Object object,
			MessageProperties messageProperties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object fromMessage(Message message)
			throws MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.xjx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBeanByName(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
	
	public static Object getBeanByClass(Class clazz){
		return applicationContext.getBean(clazz);
	}

}

package com.beanfactory;

import java.lang.reflect.InvocationTargetException;

public interface BeanRegistry {
	public void addNewBean(BeanDefinition beanDefinition) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}

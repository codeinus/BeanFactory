package com.beanfactory;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory extends BeanRegistry {
	<T> T getInstance(Class<T> type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
	Object getInstance(String beanName);
}

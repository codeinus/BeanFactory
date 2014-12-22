package com;

import com.beanfactory.SimpleBeanFactory;
import com.beans.Sample1;
import com.beans.Sample2;

import java.lang.reflect.InvocationTargetException;


public class Main {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory("/com/bean-definitions.properties");

		Sample1 instance = null;

		instance = simpleBeanFactory.getInstance(Sample1.class);

		System.out.println(instance != null);

		Object instance2 = null;

		instance2 = simpleBeanFactory.getInstance("sample2");

		System.out.println(instance2 != null);
		System.out.println(Sample2.class.isAssignableFrom(instance2.getClass()));
		System.out.println(instance2 instanceof Sample2);
	}
}

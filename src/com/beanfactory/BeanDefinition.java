package com.beanfactory;

import com.ResourceUtil;

public class BeanDefinition {
	private String beanName;
	private String classFullName;
	private Scope scope = Scope.SINGLETON;

	public BeanDefinition(String beanname, String classfullname){

		this.beanName = beanname;
		this.classFullName = classfullname;
	}

	public BeanDefinition(String classFullName){
		this.classFullName = classFullName;

		String[] parsed = classFullName.split(".");
		this.beanName = parsed[parsed.length-1];
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}
}

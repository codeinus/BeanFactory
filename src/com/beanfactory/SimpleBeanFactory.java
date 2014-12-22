package com.beanfactory;

import com.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coupang on 2014. 12. 18..
 */
public class SimpleBeanFactory implements BeanFactory {

    private BeanFactory beanFactory;
    private String propertyPath;
    private Map<String ,Object> map;
    private Map<String, String > name;


    public SimpleBeanFactory(String propertyPath){
        map = new HashMap<String, Object>();
        name = new HashMap<String, String>();

        this.propertyPath = propertyPath;
        try {
            loadBeans();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public <T> T getInstance(Class<T> type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // TODO : 코드를 채워주세요

        if(map.get(type.getName()) == null){
            addNewBean(new BeanDefinition(type.getName()));
        }

        T bean = (T) map.get(type.getName());
        return bean;
    }

    public Object getInstance(String beanName){//동일한 빈네임이 들어오면 같은 오브젝트 리턴
        // TODO : 코드를 채워주세요

        String fullName = name.get(beanName);
        if(fullName == null)
            return null;

        return map.get(fullName);
    }

    public void loadBeans() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        InputStream inputStream = ResourceUtil.resourceAsInputStream(this.propertyPath);
        String[] lines = ResourceUtil.readFully(inputStream);
        BeanDefinition beanDefinition;

        if(lines != null){
            for(String line : lines){
                String[] parsed = line.split("=");

                beanDefinition = new BeanDefinition(parsed[0],parsed[1]);
                addNewBean(beanDefinition);

                //map.put(,parsed[1]);
                System.out.println("key "+parsed[0]+" value "+parsed[1]);
            }
        }
    }

    @Override
    public void addNewBean(BeanDefinition beanDefinition) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor constructor = null;
        constructor = Class.forName(beanDefinition.getClassFullName()).getDeclaredConstructor();
        constructor.setAccessible(true);
        map.put(beanDefinition.getClassFullName(),constructor.newInstance());
        name.put(beanDefinition.getBeanName(),beanDefinition.getClassFullName());
    }
}

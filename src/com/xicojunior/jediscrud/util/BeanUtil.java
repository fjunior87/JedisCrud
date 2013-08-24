package com.xicojunior.jediscrud.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

public class BeanUtil {

	public static Map<String, String> toMap(Object object){
		Map<String, String> properties =  new HashMap<String, String>();
		try {
			properties = BeanUtilsBean.getInstance().describe(object);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	
	public static <T extends Object>T populate(Map properties, T object){
		try {
			BeanUtilsBean.getInstance().populate(object, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
}

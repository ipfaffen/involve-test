package com.ipfaffen.involve.util;

import java.lang.reflect.Constructor;

/**
 * @author Isaias Pfaffenseller
 */
public class Reflection {

	/**
	 * Create a new instance calling empty constructor method.
	 * 
	 * @param clazz
	 * @return
	 */
	public static Object newInstance(Class<?> clazz) {
		return newInstance(clazz, new Class[]{}, new Object[]{});
	}

	/**
	 * Create a new instance.
	 * 
	 * @param clazz
	 * @param paramType - constructor method parameter type.
	 * @param param - constructor method parameter.
	 * @return
	 */
	public static Object newInstance(Class<?> clazz, Class<?> paramType, Object param) {
		return newInstance(clazz, new Class[]{paramType}, new Object[]{param});
	}

	/**
	 * Create a new instance.
	 * 
	 * @param clazz
	 * @param paramsTypes - constructor method parameters type.
	 * @param params - constructor method parameters.
	 * @return
	 */
	public static Object newInstance(Class<?> clazz, Class<?>[] paramsTypes, Object[] params) {
		try {
			Constructor<?> constructor = clazz.getConstructor(paramsTypes);
			return constructor.newInstance(params);
		}
		catch(Exception e) {
			throw new RuntimeException("Invalid new instance.", e);
		}
	}

	/**
	 * Create a new instance calling empty constructor method.
	 * 
	 * @param clazzName - class name with package.
	 * @return
	 */
	public static Object newInstance(String clazzName) {
		return newInstance(clazzName, new Class[]{}, new Object[]{});
	}

	/**
	 * Create a new instance.
	 * 
	 * @param clazzName - class name with package.
	 * @param paramType - constructor method parameter type.
	 * @param param - constructor method parameter type.
	 * @return
	 */
	public static Object newInstance(String clazzName, Class<?> paramType, Object param) {
		return newInstance(clazzName, new Class[]{paramType}, new Object[]{param});
	}

	/**
	 * Create a new instance.
	 * 
	 * @param clazzName - class name with package.
	 * @param paramsTypes - constructor method parameter type.
	 * @param params - constructor method parameter type.
	 * @return
	 */
	public static Object newInstance(String clazzName, Class<?>[] paramsTypes, Object[] params) {
		try {
			return newInstance(Class.forName(clazzName), paramsTypes, params);
		}
		catch(Exception e) {
			throw new RuntimeException("Invalid new instance.", e);
		}
	}
}
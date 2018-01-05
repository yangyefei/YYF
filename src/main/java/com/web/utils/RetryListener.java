package com.web.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
/**
 * 监听方式的 失败重跑方法
 * @author yyf
 *
 */
public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {
		 IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
         
        	 annotation.setRetryAnalyzer(Retry.class);
        }
    }

}

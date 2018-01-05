package com.web.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/****
 * 失败重跑方法，这里允许最大重跑次数为2次
 * @author yyf
 *
 */
public class Retry implements IRetryAnalyzer {
	private int retryCount         = 0;
    private int maxRetryCount     = 2;   // retry a failed test 2 additional times
	public boolean retry(ITestResult arg0) {
		
		 if (retryCount <maxRetryCount) {
	            retryCount++;
	            return true;
	        }
		return false;
	}

}

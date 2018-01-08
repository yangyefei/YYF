package service;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public interface InitialService {
	/**
	 * 启动ctrip应用
	 * @param driver
	 * @param apkPath
	 * @return
	 * @throws MalformedURLException
	 */
	public AndroidDriver appiumAndroidCtripSetUp(AndroidDriver driver) throws MalformedURLException;
	

	public WebDriver browserOfInternetSetUp(WebDriver driver) throws Exception;
	
	/**
	 * 启动chrome浏览器
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebDriver browserOfChromeSetUp(WebDriver driver) throws Exception;
	
	/**
	 * 启动Firefox浏览器
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebDriver browserOfFirefoxSetUp(WebDriver driver) throws Exception;

	

}

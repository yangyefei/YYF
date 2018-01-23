package service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import service.InitialService;


public class InitialServiceImpl implements InitialService {

	private String AppPackage="ctrip.english";

	private String AppActivity="com.ctrip.ibu.myctrip.main.module.home.IBUHomeActivity";

	private String platformVersion;

	private String appRunMachineIp="127.0.0.1";

	private String webRunMachineIp="127.0.0.1";

	private String appiumPort="4723";

	
	@Override
	public AndroidDriver appiumAndroidCtripSetUp(AndroidDriver driver) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		// capabilities.setCapability(Mobi, "7.1");
		// capabilities.setCapability("deviceName", "emulator-5554");
		// capabilities.setCapability("deviceName","device");
		// capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("newCommandTimeout", "10");
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("autoAcceptAlerts", "True");	
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("platformVersion", platformVersion);
		// capabilities.setCapability("udid",
		// "emulator-5554");//如果要远程调用模拟器，这个参数必须要有
		// capabilities.setCapability("app", app.getAbsolutePath());
//		capabilities.setCapability("app", apkPath);
		capabilities.setCapability("appPackage", AppPackage);
		capabilities.setCapability("appActivity", AppActivity);
		capabilities.setCapability("noSign", "True");
		driver = new AndroidDriver(new URL("http://" + appRunMachineIp + ":" + appiumPort + "/wd/hub"), capabilities);

		return driver;
	}

	@Override
	public WebDriver browserOfInternetSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub

		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"),
				DesiredCapabilities.internetExplorer());

		return driver;
	}

	@Override
	public WebDriver browserOfChromeSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"),
				DesiredCapabilities.chrome());

		return driver;
	}

	@Override
	public WebDriver browserOfFirefoxSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"),
				DesiredCapabilities.firefox());
		return driver;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public void setAppActivity(String appActivity) {
		AppActivity = appActivity;
	}

	public void setAppPackage(String appPackage) {
		AppPackage = appPackage;
	}

	public String getAppiumPort() {
		return appiumPort;
	}

	public void setAppiumPort(String appiumPort) {
		this.appiumPort = appiumPort;
	}

	public String getAppRunMachineIp() {
		return appRunMachineIp;
	}

	public void setAppRunMachineIp(String appRunMachineIp) {
		this.appRunMachineIp = appRunMachineIp;
	}

	public String getWebRunMachineIp() {
		return webRunMachineIp;
	}

	public void setWebRunMachineIp(String webRunMachineIp) {
		this.webRunMachineIp = webRunMachineIp;
	}

}

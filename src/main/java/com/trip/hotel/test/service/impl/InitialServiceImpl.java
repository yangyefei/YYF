package com.trip.hotel.test.service.impl;

import com.trip.hotel.test.service.InitialService;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class InitialServiceImpl implements InitialService {

    private static final String ANDROID_PACKAGE_RELEASE = "";
    private static final String ANDROID_PACKAGE_DEBUG = "english.debug";
    private String AppPackage = "english";

    private String AppActivity = "main.module.home.HomeActivity";

    private String platformVersion;

    private String appRunMachineIp = "127.0.0.1";

    private String webRunMachineIp = "127.0.0.1";

    private String appiumPort = "4723";

    @Override
    public AndroidDriver<WebElement> createAndroidReleaseDriver()
            throws MalformedURLException {
        return appiumAndroidCtripSetUp(ANDROID_PACKAGE_RELEASE);
    }

    @Override
    public AndroidDriver<WebElement> createAndroidDebugDriver()
            throws MalformedURLException {
        return appiumAndroidCtripSetUp(ANDROID_PACKAGE_DEBUG);
    }

    private AndroidDriver<WebElement> appiumAndroidCtripSetUp(String appPackage)
            throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        // capabilities.setCapability(Mobi, "7.1");
        // capabilities.setCapability("deviceName", "emulator-5554");
        // capabilities.setCapability("deviceName","device");
        // capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "127.0.0.1:62025");
        capabilities.setCapability("newCommandTimeout", "10");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("autoAcceptAlerts", "True");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("platformVersion", platformVersion);
        // capabilities.setCapability("udid",
        // "emulator-5554");//如果要远程调用模拟器，这个参数必须要有
        // capabilities.setCapability("app", app.getAbsolutePath());
        // capabilities.setCapability("app", apkPath);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", AppActivity);
        capabilities.setCapability("noSign", "True");

        return new AndroidDriver<>(new URL("http://" + appRunMachineIp + ":" + appiumPort + "/wd/hub"), capabilities);
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


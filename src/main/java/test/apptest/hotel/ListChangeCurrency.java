package test.apptest.hotel;

import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class ListChangeCurrency extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService  appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    int timeOutInSeconds = 60;
    
    @BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
    
    @Test(description = "by sxm: C1309722	切换韩币酒店筛选", groups = { "Base" })
    public void listChangeKRW() throws Exception{
    	logger.info("---搜索上海---");
  	    appCommonService.homeSearchHotel(driver, "上海");
  	    doChangeCurrency("KRW");
    	WebElement price= new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_currency_name")));
    	try {
			logger.info("---开始验证C1309722	切换韩币酒店筛选---");
			logger.info("---当前页面币种："+price.getText());
			assertTrue(price.getText().contains("KRW"));
			logger.info("---验证C1309722	切换韩币酒店筛选Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309722	切换韩币酒店筛选Fail---");
		}
    }
    
    @Test(description = "by sxm: C1309723	切换小币种筛选酒店", groups = { "Base" })
    public void listChangePHP() throws Exception{
    	logger.info("---搜索上海---");
  	    appCommonService.homeSearchHotel(driver, "上海");
  	    doChangeCurrency("PHP");
    	WebElement price= new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_currency_name")));
    	try {
			logger.info("---开始验证C1309723	切换小币种筛选酒店---");
			logger.info("---当前页面币种："+price.getText());
			assertTrue(price.getText().contains("PHP"));
			logger.info("---验证C1309723	切换小币种筛选酒店Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309723	切换小币种筛选酒店Fail---");
		}
    }
    
    public void doChangeCurrency(String curreny){
    	WebElement curr= new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("ll_currency")));
    	String currency_name = curr.findElement(By.id("tv_currency_name")).getText();
    	if (currency_name.equals(curreny)) {
			logger.info("---当前币种和要切换的币种一致---");
		}
    	else {
			curr.click();
			List<WebElement> currenys = driver.findElements(By.id("tv_title"));
			for (WebElement cur : currenys) {
				if (cur.getText().contains(curreny)) {
					logger.info("---选择币种"+cur.getText()+"---");
					cur.click();
					break;
				}
			}

		}
    }
    
    @AfterMethod
    public void afterMethod(){
    	logger.info("---返回到酒店首页---");
    	new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
    	logger.info("---返回到首页---");
    	new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.widget.ImageButton"))).click();
    }
    
    @AfterClass
    public void afterClass() {
  	  driver.quit();
    }
    
}

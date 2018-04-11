package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;
public class KrSearchJp extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	
	@BeforeClass
    public void beforeclass() throws MalformedURLException{
    	driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
    	//logger.info("初始化成功，准备登陆");
		//appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
    }
	
	@Test
	public void krSearchJp() throws Exception{
		logger.info("----开始测试C1309626韩文站点输入日语酒店名称搜索酒店----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_home_bottom_account_icon"))).click();
		logger.info("----点击设定----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("ll_settings"))).click();
		List<WebElement> vaule = driver.findElements(By.id("value"));
		if(vaule.get(0).getText().equals("한국어")){
			logger.info("----已经是韩国站点----");
			logger.info("----点击返回到公共首页----");
			driver.findElement(By.className("android.widget.ImageButton")).click();
			logger.info("----点击返回到主页----");
			driver.findElement(By.id("rl_home")).click();
		}
		else{
			List<WebElement> sites = driver.findElements(By.id("text"));
		    logger.info("----点击语言----");
		    sites.get(0).click();
		    List<WebElement> languages = driver.findElements(By.id("ibu_baseview_language_item_name"));
		    for (WebElement language : languages) {
		    	if(language.getText().equals("한국어")){
		    		logger.info("----是韩语站点就点击----");
		    		language.click();
		    		break;
		    	}
			}
		    List<WebElement> distinct = driver.findElements(By.className("android.widget.LinearLayout"));
		    distinct.get(4).click();
		    logger.info("----已经选择了南韩----");
		    if(driver.findElement(By.className("android.widget.ImageButton")).isDisplayed()){
				logger.info("----点击返回到公共首页----");
				driver.findElement(By.className("android.widget.ImageButton")).click();
				logger.info("----点击返回到主页----");
				new AppCommonServiceImpl();
				driver = new InitialServiceImpl().appiumAndroidCtripSetUp(driver,"ctrip.english");
				new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_home"))).click();
				driver.findElement(By.id("rl_home")).click();
			}
		}
		logger.info("----点击酒店----");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_stay_in"))).click();
        WebElement e = new WebDriverWait(driver, timeOutInSeconds)
		.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
        e.clear();
        String searchKeyWord = "イエンリン ブルーム ホット スプリング ホテル(Yanling Bloom Hot Spring Hotel)";
        logger.info("----输入日语酒店名称----"+searchKeyWord);
        e.sendKeys(searchKeyWord);
		WebElement tvTitle = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		try{
			String hotelname = tvTitle.getText();
			assertTrue(searchKeyWord.contains(hotelname));
			logger.info("----C1309626韩文站点输入日语酒店名称搜索酒店Pass----");
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}

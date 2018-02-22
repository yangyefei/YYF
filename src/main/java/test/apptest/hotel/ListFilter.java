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

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ListFilter extends BaseTest{
	
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
  @Test(description = "by ylf: C1309670 热门地标或更多地标筛选", groups = { "Base" })
  public void filterMarkland() {
		logger.info("by ylf: C1309670 热门地标或更多地标筛选");
		
	    logger.info("搜上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("上海"); 
	    ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	    destinationlist.get(0).click();

	    logger.info("进入上海列表页");
	    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	    
	    doFilterMarkland();
        
	    if(LocationChecked())	  
	    {
	    	Assert.assertTrue(true);
	    	logger.info("C1309670 热门地标或更多地标筛选成功");
	    }
	    else
	    	logger.info("C1309670 热门地标或更多地标筛选失败");	
  }
  
  @Test(description = "by ylf: C1309676 其他筛选", groups = { "Base" })
  public void filterOthers() {
		logger.info("by ylf: C1309676 其他筛选");
		
	    logger.info("搜上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("上海"); 
	    ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	    destinationlist.get(0).click();

	    logger.info("进入上海列表页");
	    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	    
	    doFilterOthers();
        
	    if(FilterChecked())	  
	    {
	    	Assert.assertTrue(true);
	    	logger.info("C1309676 其他筛选成功");
	    }
	    else
	    	logger.info("C1309676 其他筛选失败");	
  }
  
  private boolean LocationChecked() {
	 WebElement filterreddot= driver.findElement(By.id("top_bar_location_red_dot"));
	 return filterreddot.isDisplayed();
 }
  
  private boolean FilterChecked() {
	 WebElement filterreddot= driver.findElement(By.id("top_bar_filter_red_dot"));
	 return filterreddot.isDisplayed();
 }
  
private void doFilterMarkland() {
	    logger.info("--点击位置---");
		driver.findElement(By.id("tv_location")).click();
		
		List<WebElement> filterNames = driver.findElements(By.id("tv_filter_content"));
      for (WebElement filterName : filterNames) 
      {
	       	 if(filterName.getText().equals("熱門地標"))
	       	 {
				logger.info("---点击熱門地標---");
				filterName.click();
				
				WebElement submenu = driver.findElement(By.id("list_sub_menus"));

				List<WebElement>  Items = submenu.findElements(By.className("android.widget.LinearLayout"));
				Items.get(0).click();
				logger.info("---选中"+Items.get(0).getText()+"---");
				break;
	       	 }
		}
      logger.info("---显示筛选结果---");
      driver.findElement(By.id("tv_show_result")).click();	
}

private void doFilterOthers() {
    logger.info("--点击筛选---");
	driver.findElement(By.id("tv_filter")).click();
	
	logger.info("--点击其它類型住宿---");
	driver.findElement(By.id("iv_filter_select")).click();
	
	List<WebElement> Items = driver.findElement(By.id("rv_content")).findElements(By.className("android.widget.LinearLayout"));
	Items.get(0).findElement(By.id("hotel_filter_checkbox_view_checkbox")).click();
	logger.info("---选中"+Items.get(0).findElement(By.id("hotel_filter_checkbox_view_title")).getText()+"---");
	
	Items.get(1).findElement(By.id("hotel_filter_checkbox_view_checkbox")).click();
	logger.info("---选中"+Items.get(1).findElement(By.id("hotel_filter_checkbox_view_title")).getText()+"---");


  logger.info("---显示筛选结果---");
  driver.findElement(By.id("tv_show_result")).click();	
}
@AfterMethod
  public void afterMethod() {
	     logger.info("---返回搜索首页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
	     //返回Trip首页	     
	     logger.info("---返回Trip首页---");
	     driver.findElementByClassName("android.widget.ImageButton").click();
  }

  @BeforeClass
  public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功");
	}

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

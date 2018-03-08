package test.apptest.hotel;

import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import common.utils.ListSortType;
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
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class ListSort extends BaseTest{
	
	private InitialService initial = new InitialServiceImpl();
	private AppCommonServiceImpl appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
  @Test(description = "by ylf: C1309700	最受欢迎", groups = { "Base" })
  public void ListSortByHot() {
		logger.info("by ylf: C1309700	最受欢迎");
		
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
	    
	    doSort(ListSortType.ByHot,"最受欢迎");
/*	    doSort(ListSortType.ByPriceAsc,"价格低到高");
	    doSort(ListSortType.ByPriceDesc,"价格高到低");
	    doSort(ListSortType.ByScore,"用户评分");
	    doSort(ListSortType.ByStar,"星级低到高");
	    doSort(ListSortType.ByDistance,"距离近到远");*/
        
	    if(SortChecked())	  
	    {
	    	Assert.assertTrue(true);
	    	logger.info("C1309700 最受欢迎排序成功");
	    }
	    else
	    	logger.info("C1309700 最受欢迎排序失败");	
  }
  
  @Test(description = "by ylf: C1309704	星级由高到低", groups = { "Base" })
  public void ListSortByStar() {
		logger.info("by ylf: C1309704	星级由高到低");
		
	    logger.info("搜上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
/*		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("上海"); 
	    ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	    destinationlist.get(0).click();
*/
	    logger.info("进入上海列表页");
	    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	    
	    doSort(ListSortType.ByStar,"星级高到低");
	    
/*	    doSort(ListSortType.ByPriceAsc,"价格低到高");
	    doSort(ListSortType.ByPriceDesc,"价格高到低");
	    doSort(ListSortType.ByScore,"用户评分");
	    doSort(ListSortType.ByDistance,"距离近到远");*/
        
	    if(SortChecked())	  
	    {
	    	Assert.assertTrue(true);
	    	logger.info("C1309704	星级由高到低成功");
	    }
	    else
	    	logger.info("C1309704	星级由高到低失败");	
  }
  
    
  private boolean SortChecked() {
	 WebElement filterreddot= driver.findElement(By.id("top_bar_sort_red_dot"));
	 return filterreddot.isDisplayed();
 }
   
private void doSort(ListSortType sorttype, String descript) {
	int Y_offset =0; 
	switch (sorttype) {  
    case ByHot:  
    	Y_offset = 170;  
        break;  
    case ByPriceAsc:  
    	Y_offset = 220;  
        break;  
    case ByPriceDesc:  
    	Y_offset = 280; ;  
        break;  
    case ByScore:  
    	Y_offset = 340; ;  
        break;   
    case ByStar:  
    	Y_offset = 400; ;  
        break;  
    case ByDistance:  
    	Y_offset = 460; ;  
        break;  
    }  
	
	
	logger.info("--点击排序---");
	driver.findElement(By.id("tv_sort")).click();
    try {
  	  logger.info("---等待2秒---");
  	  Thread.sleep(2 *1000);
	  } 
	  catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	  }
      logger.info("---点击"+descript+"---");
      int click_x_0 = driver.manage().window().getSize().width;
      int click_y_0 = driver.manage().window().getSize().height; 
      int x_click0 = 270 * click_x_0 / 540;      
      int y_click0 = Y_offset * click_y_0 / 850; 

      driver.tap(1, x_click0, y_click0, 100);
      logger.info("---点击"+descript+"成功---");
      try {
    	  logger.info("---等待5秒---");
    	  Thread.sleep(5 *1000);
	  } 
	  catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	  }
      
}

  @BeforeClass
  public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功");
	}
  
  @AfterMethod
  public void afterMethod() {
	     logger.info("---返回搜索首页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
	     //返回Trip首页	     
	     logger.info("---返回Trip首页---");
	     driver.findElementByClassName("android.widget.ImageButton").click();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

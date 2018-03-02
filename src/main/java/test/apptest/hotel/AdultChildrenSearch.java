package test.apptest.hotel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.testng.AssertJUnit.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import service.impl.HotelHomePageInitialImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import common.frame.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

public class AdultChildrenSearch extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private HotelHomePageInitialImpl hotelHomePageInitialImpl = new HotelHomePageInitialImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	private Logger logger = Logger.getLogger("AdultChildrenSearch.class");
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}

	@Test(description = "by sxm: C1309644 2成人1儿童（1岁），搜索酒店+C1309638 页面默认成人儿童数量搜索", groups = { "Base" })
	public void twoadultOneChildrenSearch() throws Exception {
		logger.info("by sxm: C1309644 2成人1儿童（1岁）");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("Singapore"); 
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     destinationlist.get(0).click(); 
	     
	     //清除首页的星级筛选
	     hotelHomePageInitialImpl.HotelPageStarsFilter(driver);
	     
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_adult"))).click();
	     //获取刚进入首页时，显示的成人数
    	 List<WebElement> number=driver.findElements(By.id("plus_minus_number_view_number"));
    	 int adult = Integer.parseInt(number.get(0).getText());
    	 int child = Integer.parseInt(number.get(1).getText());
    	 
    	 List<WebElement> numberPlus = driver.findElements(By.id("plus_minus_number_view_plus"));
    	 List<WebElement> numberMinus = driver.findElements(By.id("plus_minus_number_view_minus"));
    	 
    	 if (adult == 2 ) 
    	 {
			logger.info("---成人数是2---");
			}
    	 else if (adult < 2) 
    	 {
			for( ; adult < 2; adult++)
			{
				numberPlus.get(0).click();
				logger.info("--点击增加成人数目--");
				Thread.sleep(1000);
				}
			}
    	 else 
    	 {
 				for( ; adult>2 ;adult--)
 				{
 					numberMinus.get(0).click();
 					logger.info("--点击减少成人数目--");
 					Thread.sleep(1000);
 				}
		}
    	 
    	 if(child == 1 )
    	 {
    		 logger.info("---儿童数量是1---");

    	 }
    	 else if (child < 1) 
    	 {
    		 for( ; child<1 ; child++)
    		 {
    			 logger.info("---增加儿童数目---");
    			 numberPlus.get(1).click();
    			 Thread.sleep(1000);
    		 }
    	 }
    	 else 
    	 {
    		 if(child > 1)
    		 {
    			 for( ; child>1 ; child--)
    			 {
    				 logger.info("---减少儿童数目---");
    				 numberMinus.get(1).click();
    				 Thread.sleep(1000);
    			 }
    		 }
    	 }
    	 
    	 new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_child_item"))).click();
         List<WebElement> childAges = driver.findElements(By.id("view_select_age_item"));
         for (WebElement childage : childAges) 
         {
        	 String childAge = childage.findElement(By.id("tv_age_index")).getText();
        	 if(childAge.equals("1 歲"))
        	 {
        		Actions actions = new Actions(driver);
 				actions.moveToElement(childage);
 				logger.info("---选择儿童年龄"+childAge+"---");
 				childage.click();
 				break;
        	 }
		}
        logger.info("---点击入住人数确认按钮---");
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
        logger.info("---点击搜索按钮---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
        List<WebElement> hotelNames = driver.findElements(By.id("tv_hotel_name"));
        logger.info("---点击第一家酒店---");
        hotelNames.get(0).click();
        logger.info("---点击酒店详情页成人儿童---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotel_view_hotel_rooms_date_people_container"))).click();
        try{
             List<WebElement> numbers = driver.findElements(By.id("plus_minus_number_view_number"));
             String adultNum = number.get(0).getText();
             Assert.assertEquals("2",adultNum);
             String childNum = number.get(1).getText();
             Assert.assertEquals("1",childNum);
             if(driver.findElement(By.id("view_child_item")).isDisplayed())
             {
             	String childagerange = driver.findElement(By.id("tv_age_range")).getText();
             	Assert.assertEquals("1 歲",childagerange);
             }
             logger.info("---C1309644 2成人1儿童（1岁），搜索酒店验证通过---");
        }
        catch(Exception exception){
        	exception.printStackTrace();
        	logger.info("---C1309644 2成人1儿童（1岁），搜索酒店验证失败---");
        }
        try{
        	logger.info("---开始验证C1309638页面默认成人儿童数量搜索--- ");
			logger.info("---返回到酒店详情页---");
			driver.findElement(By.id("tv_apply")).click();
			logger.info("---返回搜索列表页---");
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("ivBack"))).click();
		    logger.info("---返回搜索首页---");
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
			String children = driver.findElement(By.id("tv_children")).getText();
			String adultnum = driver.findElement(By.id("tv_adult")).getText();
			logger.info("---判断酒店首页成人儿童条件是否存在---");
			Assert.assertTrue(adultnum.contains("2"));
			Assert.assertTrue(children.contains("1"));
			logger.info("---酒店首页成人儿童条件存在---");
			logger.info("---点击搜索按钮---");
	        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
	        List<WebElement> hotelName = driver.findElements(By.id("tv_hotel_name"));
	        logger.info("---点击第一家酒店---");
	        hotelName.get(0).click(); 
	        logger.info("---验证酒店详情页成人儿童数量---");
	        String childdetail = driver.findElement(By.id("tv_child_number")).getText();
	        String adultdetail = driver.findElement(By.id("tv_adult_number")).getText();
	        Assert.assertTrue(adultdetail.contains("2"));
			Assert.assertTrue(childdetail.contains("1"));
			logger.info("---验证C1309638页面默认成人儿童数量搜索通过--- ");
			//返回搜索列表页
		     logger.info("---返回搜索列表页---");
		     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("ivBack"))).click();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证C1309638页面默认成人儿童数量搜索失败--- ");
		}
	}
	
	@Test(description = "by ylf: C1309640 2成人2儿童（一成年，一未成年）搜索酒店", groups = { "Base" })
	public void twoadultOneAdultChildrenSearch() throws Exception {
		logger.info("by ylf: C1309640 2成人2儿童（一成年13岁，一未成年）");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("Singapore"); 
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     destinationlist.get(0).click(); 
	     
	     //清除首页的星级筛选
	     hotelHomePageInitialImpl.HotelPageStarsFilter(driver);
	     
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_adult"))).click();
	     //获取刚进入首页时，显示的成人数
    	 List<WebElement> number=driver.findElements(By.id("plus_minus_number_view_number"));
    	 int adult = Integer.parseInt(number.get(0).getText());
    	 int child = Integer.parseInt(number.get(1).getText());
    	 
    	 List<WebElement> numberPlus = driver.findElements(By.id("plus_minus_number_view_plus"));
    	 List<WebElement> numberMinus = driver.findElements(By.id("plus_minus_number_view_minus"));
    	 
    	 if (adult == 2 ) 
    	 {
			logger.info("---成人数是2---");
			}
    	 else if (adult < 2) 
    	 {
			for( ; adult < 2; adult++)
			{
				numberPlus.get(0).click();
				logger.info("--点击增加成人数目--");
				Thread.sleep(1000);
				}
			}
    	 else 
    	 {
 				for( ; adult>2 ;adult--)
 				{
 					numberMinus.get(0).click();
 					logger.info("--点击减少成人数目--");
 					Thread.sleep(1000);
 				}
		}
    	 
    	 if(child == 2 )
    	 {
    		 logger.info("---儿童数量是2---");

    	 }
    	 else if (child < 2) 
    	 {
    		 for( ; child<2 ; child++)
    		 {
    			 logger.info("---增加儿童数目---");
    			 numberPlus.get(1).click();
    			 Thread.sleep(1000);
    		 }
    	 }
    	 else 
    	 {
    		 if(child > 2)
    		 {
    			 for( ; child>2 ; child--)
    			 {
    				 logger.info("---减少儿童数目---");
    				 numberMinus.get(1).click();
    				 Thread.sleep(1000);
    			 }
    		 }
    	 }
    	 
    	 new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_child_item"))).click();
    	 AppCommonServiceImpl acs = new AppCommonServiceImpl();
    	 acs.swipeToDown(driver);
    	 acs.swipeToDown(driver);    	 
    	 List<WebElement> childAges = driver.findElements(By.id("view_select_age_item"));
         for (WebElement childage : childAges) 
         {
        	 String childAge = childage.findElement(By.id("tv_age_index")).getText();
        	 //logger.info(childAge);
        	 if(childAge.equals("13 歲"))
        	 {
        		Actions actions = new Actions(driver);
 				actions.moveToElement(childage);
 				logger.info("---选择儿童年龄"+childAge+"---");
 				childage.click();
 				break;
        	 }
		}
        logger.info("---点击入住人数确认按钮---");
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
        logger.info("---点击搜索按钮---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
        List<WebElement> hotelNames = driver.findElements(By.id("tv_hotel_name"));
        logger.info("---点击第一家酒店---");
        hotelNames.get(0).click();
        logger.info("---点击酒店详情页成人儿童---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("iv_change_people_arrow"))).click();
        try{
             List<WebElement> numbers = driver.findElements(By.id("plus_minus_number_view_number"));
             String adultNum = number.get(0).getText();
             Assert.assertEquals("2",adultNum);
             String childNum = number.get(1).getText();
             Assert.assertEquals("2",childNum);
             if(driver.findElement(By.id("view_child_item")).isDisplayed())
             {
             	String childagerange = driver.findElement(By.id("tv_age_range")).getText();
             	Assert.assertEquals("13 歲",childagerange);
             }
             logger.info("---C1309640 2成人2儿童（一成年，一未成年）搜索酒店验证通过---");
             
        }
        catch(Exception exception){
        	exception.printStackTrace();
        	logger.info("---C1309640 2成人2儿童（一成年，一未成年）搜索酒店验证失败---");
        }
        //返回详情页
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
        //返回酒店列表页
        driver.findElementById("ivBack").click();

	}
	
	@Test(description = "by lnn: 8成人3儿童（均成年）搜索C1309639", groups = { "Base" })
	public void adultChildrenSearch() throws Exception {
		logger.info("by lnn: 8成人3儿童（均成年）");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys("新加坡"); 
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     destinationlist.get(0).click(); 
	     
	     //清除首页的星级筛选
	     hotelHomePageInitialImpl.HotelPageStarsFilter(driver);
	     
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_adult"))).click();
	     //获取刚进入首页时，显示的成人数
    	 WebElement adult=driver.findElement(By.id("view_plus_guests_adult"));
    	 WebElement adultplus=adult.findElement(By.xpath("//android.widget.TextView"));
    	 int adultnums=Integer.parseInt(adultplus.getText());
    	 logger.info("成人数"+adultnums);
    	//获取刚进入首页时，显示的儿童数
    	 WebElement child=driver.findElement(By.id("view_plus_guests_child"));
    	 WebElement childplus=child.findElement(By.xpath("//android.widget.TextView"));
    	 int childnums=Integer.parseInt(childplus.getText());
    	 logger.info("儿童数"+childnums);
	     for(int i=0;i<8-adultnums;i++)
	     {
	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_adult"));
	    	 adults.findElement(By.id("plus_minus_number_view_plus")).click();	 
	     }
	     for(int j=0;j<3-childnums;j++)
	     {
	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_child"));
	    	 adults.findElement(By.id("plus_minus_number_view_plus")).click();
	     }   
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click(); 
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_check_in"))).click(); 
	     try {
	    	 WebElement adulttotalnums= driver.findElement(By.id("tv_adult"));
	    	 Assert.assertEquals("8 成人",adulttotalnums.getText());
	    	 logger.info("C1309639:验证8成人成功");
	    	 WebElement tv_childrentotalnums= driver.findElement(By.id("tv_children"));
	    	 Assert.assertEquals("3 小童",tv_childrentotalnums.getText());
	    	 logger.info("C1309639:验证3儿童成功");  	 
	     } catch (Exception e) 
	    {
	    	logger.info("C1309639:验证8成人3儿童失败");
	    }
	}
	

	@AfterMethod
	public void afterTest() {
	     logger.info("---返回搜索首页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
	     //返回Trip首页	     
	     logger.info("---返回Trip首页---");
	     driver.findElementByClassName("android.widget.ImageButton").click();
	}	
	
 	@AfterClass(alwaysRun=true)
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}

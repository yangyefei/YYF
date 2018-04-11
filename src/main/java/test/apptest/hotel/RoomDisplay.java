package test.apptest.hotel;

import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.android.po.PoBase;
import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomDisplay extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService  appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    int timeOutInSeconds = 60;
    
    @BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
    
    @Test(description = "by yulf: C1309736	基础房型的起价和列表页起价保持一致", groups = { "Base" })
    public void checkStartingPrice() throws Exception{
    	logger.info("by yulf: C1309736	基础房型的起价和列表页起价保持一致");
		
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
	    
	    logger.info("获取第一个酒店起价");
	    WebElement listPrice = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("price")));
	    logger.info(listPrice.getText());
	    
	    listPrice.click();
    	WebElement hotel= new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_hotels_item_container")));
    	hotel.click();
	    WebElement detailPrice= new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_sub_room_price")));
	    logger.info("详情页酒店起价："+ detailPrice.getText());
	    
    	try {
			logger.info("---开始验证C1309736	基础房型的起价和列表页起价保持一致---");
			assertTrue(detailPrice.getText().equals(listPrice.getText()));
			logger.info("---验证C1309736	基础房型的起价和列表页起价保持一致Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309736	基础房型的起价和列表页起价保持一致Fail---");
		}
    }
    
    @Test(description = "by sxm: C1309740	详情页酒店政策下方外露优质点评", groups = { "Base" })
    public void reviews() throws Exception{
    	logger.info("---搜索上海思南公館酒店---");
    	appCommonService.homeSearchHotel(driver, "上海思南公館酒店");
    	appCommonService.listToDetail(driver);
    	appCommonService.swipeToDown(driver, 1000, 2);
    	try {
			logger.info("---开始验证C1309740	详情页酒店政策下方外露优质点评---");
			WebElement comment = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_detail_foot_comment")));
			String content = comment.findElement(By.id("view_hotel_detail_foot_comment_content")).getText();
			assertFalse(content.isEmpty());
			logger.info("---验证C1309740	详情页酒店政策下方外露优质点评Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309740	详情页酒店政策下方外露优质点评Fail---");
		}
    }
    
    @Test(description = "By sxm: C1309739	【房型弹层】功能", groups = { "Base" })
    public void RoomShell() throws Exception{
    	logger.info("---搜索上海思南公館酒店---");
    	appCommonService.homeSearchHotel(driver, "上海思南公館酒店");
    	appCommonService.listToDetail(driver);
    	logger.info("---点击房型弹层---");
    	new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("hotel_rooms_list_sub_room_right")))
		.click();
    	try {
    		logger.info("---开始验证C1309739	【房型弹层】功能---");
    		WebElement book = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("htl_room_detail_bottom_bar_btn")));
    		Assert.assertTrue(book.isDisplayed());
    		logger.info("---验证C1309739	【房型弹层】功能Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309739	【房型弹层】功能Fail---");
		}
    	driver.findElement(By.id("tvClose")).click();
    }
    
    @Test(description = "By yulf: C1309746	酒店描述的展示", groups = { "Base" })
    public void VerifyDescription() throws Exception{
		logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		
		logger.info("搜上海");
		PoBase.findElement(driver, HtlHomePage.hotel_main_search).click();
		PoBase.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword).clear();
		PoBase.findElement(driver, HtlHomePage.SearchEnginePage.hotel_destination_search_keyword).sendKeys("上海");
		PoBase.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle).get(0).click();
		
		logger.info("进入酒店列表");
		PoBase.findElement(driver, HtlHomePage.search_button).click();

		logger.info("进入酒店详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("翻屏幕");
		appCommonService.swipeToDown(driver, 1000, 2);
		appCommonService.swipeToDown(driver, 1000, 2);
		appCommonService.swipeToDown(driver, 1000, 2);
		
		String description = HtlDetailPage.getHotelDescription(driver);
		
		logger.info("酒店描述："+ description);
		try {
    		logger.info("---开始验证C1309746	酒店描述的展示---");
    		
    		Assert.assertTrue(description!="");
    		logger.info("---验证C1309746	酒店描述的展示Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309746	酒店描述的展示Fail---");
		}
    	
    }
    
    @AfterMethod
	public void afterTest() {
    	logger.info("---返回列表页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("ivBack"))).click();
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

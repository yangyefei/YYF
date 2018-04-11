package test.apptest.hotel;

import com.trip.hotel.test.common.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import java.net.MalformedURLException;
import java.util.List;

public class MyHotelOrder extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	
	
	//2018/4/8   修改驱动丢失导致的配置失败  by yyf
	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
	}
	
	@Test(description = "By sxm : C1309650 酒店订单/历史订单与订单详情页的跳转联动（ctrip用户）", groups={"Base"})
	public void myHotelOrderTrip() throws Exception{
		logger.info("---进入酒店首页---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		logger.info("---进入我的订单页面---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_my_order"))).click();
		Thread.sleep(5000);
		WebElement List = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.support.v7.widget.RecyclerView")));
		//WebElement List = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
		WebElement hotel = List.findElements(By.className("android.widget.LinearLayout")).get(0);
		String state = hotel.findElement(By.id("tv_order_state")).getText();
		String price = hotel.findElement(By.id("view_hotel_price")).getText();
		String name = hotel.findElement(By.id("tv_hotel_name")).getText();
		String room = hotel.findElement(By.id("tv_hotel_info")).getText();
		String night = hotel.findElement(By.id("tv_check_info")).getText();
		logger.info("---进入订单详情页---");
		hotel.click();
		try {
			logger.info("---开始验证酒店订单与订单详情页的跳转联动（ctrip账户）---");
			String hotelname = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_hotel_name"))).getText();
			String hotelprice = driver.findElement(By.id("tv_price_pay")).getText();
			String hotelstate = driver.findElement(By.id("tv_order_status")).getText();
			String roomname = driver.findElement(By.id("tv_room_name")).getText();
			String roomnight = driver.findElement(By.id("tv_date_night_room")).getText();
			logger.info("---断言订单状态: "+state+"---"+hotelstate);
			Assert.assertEquals(state,hotelstate);
			logger.info("---断言订单价格: "+price+"---"+hotelprice);
			Assert.assertEquals(price,hotelprice);
			logger.info("---断言酒店名称: "+name+"---"+hotelname);
			Assert.assertEquals(name,hotelname);
			logger.info("---断言房型名称: "+room+"---"+roomname);
			Assert.assertEquals(room,roomname);
			//logger.info("---断言间夜数目: "+night+"---"+roomnight);
			//Assert.assertTrue(night.contains(roomnight));
			logger.info("---验证酒店订单与订单详情页的跳转联动（ctrip账户）成功---");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证酒店订单与订单详情页的跳转联动（ctrip账户）失败---");
		}
		
		logger.info("---返回到酒店订单列表页---");
		driver.findElement(By.className("android.widget.ImageButton")).click();
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("toolbar"))).
		findElement(By.className("android.support.v7.widget.LinearLayoutCompat")).click();
		logger.info("---进入订单历史记录列表---");
		WebElement hisList = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.support.v7.widget.RecyclerView")));
		//WebElement List = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
		WebElement hishotel = hisList.findElements(By.className("android.widget.LinearLayout")).get(0);
		String hisstate = hishotel.findElement(By.id("tv_order_state")).getText();
		String hisprice = hishotel.findElement(By.id("view_hotel_price")).getText();
		String hisname = hishotel.findElement(By.id("tv_hotel_name")).getText();
		String hisroom = hishotel.findElement(By.id("tv_hotel_info")).getText();
		String hisnight = hishotel.findElement(By.id("tv_check_info")).getText();
		logger.info("---进入订单详情页---");
		hishotel.click();
		try {
			logger.info("---开始验证酒店历史订单详情页的跳转联动（ctrip账户）---");
			String hotelname = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_hotel_name"))).getText();
			String hotelprice = driver.findElement(By.id("tv_price_pay")).getText();
			String hotelstate = driver.findElement(By.id("tv_order_status")).getText();
			String roomname = driver.findElement(By.id("tv_room_name")).getText();
			String roomnight = driver.findElement(By.id("tv_date_night_room")).getText();
			logger.info("---断言订单状态: "+hisstate+"---"+hotelstate);
			Assert.assertEquals(hisstate,hotelstate);
			logger.info("---断言订单价格: "+hisprice+"---"+hotelprice);
			Assert.assertEquals(hisprice,hotelprice);
			logger.info("---断言酒店名称: "+hisname+"---"+hotelname);
			Assert.assertEquals(hishotel,hotelname);
			logger.info("---断言房型名称: "+hisroom+"---"+roomname);
			Assert.assertEquals(hisroom,roomname);
			logger.info("---验证酒店历史订单与订单详情页的跳转联动（ctrip账户）成功---");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证酒店历史订单与订单详情页的跳转联动（ctrip账户）失败---");
		}
	}
	@Test(description = "By sxm : C1309650 酒店订单/历史订单与订单详情页的跳转联动（快速预订用户）", groups={"Base"})
	public void  MyHotelOrderGuest() throws Exception{
		// TODO Auto-generated constructor stub
        appCommonService.logoutForApp(driver);
		logger.info("---进入首页---");
		appCommonService.homeSearchHotel(driver, "新加坡");
	    Thread.sleep(5000);
	    List<WebElement> hotelNames = driver.findElements(By.id("tv_hotel_name"));
	    hotelNames.get(0).click();
		logger.info("---酒店详情页选择房型---");
		WebElement baseRoom = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("hotel_rooms_list_main_room_root_container")));
		baseRoom.click();
		List<WebElement> subRooms = driver.findElements(By.id("hotel_rooms_list_sub_room_container"));
		subRooms.get(0).findElement(By.id("hotel_rooms_list_sub_room_btn_book")).click();
		logger.info("---快速预定---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("guest_button"))).click();
		WebElement contact = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_book_contact_info")));
		List<WebElement> text = contact.findElements(By.id("view_edit_text"));
		text.get(0).clear();
		text.get(0).sendKeys("Li");
		text.get(1).clear();
		text.get(1).sendKeys("Sisi");
		text.get(2).clear();
		text.get(2).sendKeys("Test@test.com");
		WebElement phone = contact.findElement(By.id("hotel_book_contact_phone_input"));
		phone.clear();
		phone.sendKeys("12345678");
		driver.findElement(By.id("tv_bottom_select")).click();
		logger.info("---返回到酒店填写页---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tvLeft"))).click();
		
		if(driver.findElement(By.id("pay_back_exit")).isDisplayed())
		{
			driver.findElement(By.id("pay_back_exit")).click();
		}
		logger.info("---返回到酒店房型页---");
		driver.findElement(By.className("android.widget.ImageButton")).click();
		if(driver.findElement(By.id("btn_negative")).isDisplayed())
		{
			driver.findElement(By.id("btn_negative")).click();
		}
		
		logger.info("---返回到酒店列表页---");
		driver.findElement(By.id("ivBack")).click();
		logger.info("---返回到酒店首页---");
		driver.findElement(By.id("iv_back")).click();
		
		logger.info("---进入我的订单页面---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_my_order"))).click();
		Thread.sleep(5000);
		WebElement List = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.support.v7.widget.RecyclerView")));
		//WebElement List = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
		WebElement hotel = List.findElements(By.className("android.widget.LinearLayout")).get(0);
		String state = hotel.findElement(By.id("tv_order_state")).getText();
		String price = hotel.findElement(By.id("view_hotel_price")).getText();
		String name = hotel.findElement(By.id("tv_hotel_name")).getText();
		String room = hotel.findElement(By.id("tv_hotel_info")).getText();
		String night = hotel.findElement(By.id("tv_check_info")).getText();
		logger.info("---进入订单详情页---");
		hotel.click();
		try {
			logger.info("---开始验证酒店订单与订单详情页的跳转联动（Guest账户）---");
			String hotelname = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_hotel_name"))).getText();
			String hotelprice = driver.findElement(By.id("tv_price_pay")).getText();
			String hotelstate = driver.findElement(By.id("tv_order_status")).getText();
			String roomname = driver.findElement(By.id("tv_room_name")).getText();
			String roomnight = driver.findElement(By.id("tv_date_night_room")).getText();
			logger.info("---断言订单状态: "+state+"---"+hotelstate);
			Assert.assertEquals(state,hotelstate);
			logger.info("---断言订单价格: "+price+"---"+hotelprice);
			Assert.assertEquals(price,hotelprice);
			logger.info("---断言酒店名称: "+name+"---"+hotelname);
			Assert.assertEquals(name,hotelname);
			logger.info("---断言房型名称: "+room+"---"+roomname);
			Assert.assertEquals(room,roomname);
			logger.info("---验证酒店订单与订单详情页的跳转联动（Guest账户）成功---");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---验证酒店订单与订单详情页的跳转联动（Guest账户）失败---");
		}
	}
	
	@Test(description = "by lnn: 无任何酒店订单/历史订单记录，卡片展示（快速预订用户和ctrip用户）C1309653", groups = { "Base" })
	public void noBookingsFound() throws Exception {	
		logger.info("--用特定的无任何订单和历史记录的账号登录--");
		Userloginin("ojubanes-7458@yopmail.com", "lmr07155511909");
    	logger.info("--进入酒店首页--");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		logger.info("--进入我的订单列表--");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_my_order_title"))).click();
		try {
	    	 WebElement nohotelbookings= driver.findElement(By.id("tv_error_title"));
	    	 Assert.assertEquals("無相符結果",nohotelbookings.getText());
	    	 logger.info("C1309653:验证无任何酒店订单记录，卡片展示（ctrip用户）成功");	 
	     } catch (Exception e) 
	    {
	    	logger.info("C1309653:验证无任何酒店订单记录，卡片展示（ctrip用户）失败");
	    }
		logger.info("--进入历史订单列表--");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.xpath
				("//android.widget.TextView[contains(@text,'歷史記錄')]"))).click();
		try {
	    	 WebElement nohistorybookings= driver.findElement(By.id("tv_error_title"));
	    	 Assert.assertEquals("無相符結果",nohistorybookings.getText());
	    	 logger.info("C1309653:验证无任何历史订单记录，卡片展示（ctrip用户）成功");	 
	     } catch (Exception e) 
	    {
	    	logger.info("C1309653:验证无任何历史订单记录，卡片展示（ctrip用户）失败");
	    }

	}
	
	public void Userloginin(String userName, String userPassWord) throws InterruptedException {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("rl_account"))).click();
			//用户先登出
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("tv_email"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_sign_out"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("btn_positive"))).click();
			//用户后登录
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tvSignIn"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("login_btn"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("account_input")))
					.clear();
			driver.findElement(By.id("account_input")).sendKeys(userName);
			driver.findElement(By.id("password_input")).sendKeys(userPassWord);
			driver.findElement(By.id("login_btn")).click();
		    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("rl_home"))).click();
	}
	
	//2018/4/8   修改驱动丢失导致的配置失败  by yyf
	@AfterMethod
	public void afterMethod() {
//		logger.info("---返回到酒店订单列表页---");
//		driver.findElement(By.className("android.widget.ImageButton")).click();
//		logger.info("---返回到酒店首页---");
//		driver.findElement(By.className("android.widget.ImageButton")).click();
//		logger.info("---返回到首页---");
//		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.quit();
	}	
	
 	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}

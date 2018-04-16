package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

public class OrderPrePay extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	@SuppressWarnings("rawtypes")
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	String total_amount_detail;
	String total_amount_pay;
	String hote_name_detail;
	String room_name_detail;
	String mycardnumber;
	int i;//
	WebElement hotelName;

	/**
	 * C1320608 CNY支付全流程 C1320610 HKD支付全流程 C1320612 KRW支付全流程 C1320613 USD支付全流程
	 * C1320614 JPY支付全流程 C1320616 EUR支付全流程 C1320617 SGD支付全流程 C1320843 TWD支付全流程
	 * 
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 * @throws Exception
	 */
	@Test(description = "yefei.yang", dataProvider = "testData", groups = { "orderpay" })
	public void prePay(Map<String, String> datadriven) throws InterruptedException, MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp("ctrip.english.debug");
		logger.info("---Version:" + datadriven.get("version") + "---" + datadriven.get("money") + "Start Test---");
		try {
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.elementToBeClickable(By.id("upgrade_dialog_fragment_close_icon")))
					.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			appCommonService.logoutForApp(driver);
		} catch (Exception e) {
			logger.info("app not register");
		}

		// 进入酒店列表页面
		logger.info("进入酒店列表页");
		fromHomeToList();

		logger.info("切换币种");
		i = Integer.parseInt(datadriven.get("currency"));
		exchange(i);

		logger.info("进入酒店详情页");
		roomDetail();

		logger.info("进入订单页面");
		orderDetail(datadriven.get("name"));

		logger.info("进入支付页面");
		payPage();
		logger.info("---Version:" + datadriven.get("version") + "---" + datadriven.get("money") + "==>Test Pass---");

	}

	@AfterMethod(alwaysRun = true)
	public void after() {
		driver.quit();
	}

	// 从首页进入列表页
	public void fromHomeToList() {
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_home")))
				.click();
		try {
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.elementToBeClickable(By.id("upgrade_dialog_fragment_close_icon")))
					.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();

		WebElement search = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in")));

		if (search.getText().equals("上海")) {
			new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		} else {
			search.click();
			WebElement elem = new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
			elem.clear();
			elem.sendKeys("上海");
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")))
					.click();
		}

	}

	// 切换币种，选择上海嘉瑞酒店进入酒店详情页
	/**
	 * @param i
	 *            0：CNY 1：USD 2: HKD 3: TWD 4: EUR 9: JPY 10:KRW 13: SGD
	 */
	@SuppressWarnings("unchecked")
	public void exchange(int i) {
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("ll_currency")))
				.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchAction action = new TouchAction(driver);
		action.press(379, 333).waitAction(Duration.ofMillis(1000)).moveTo(379, 139).release().perform();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tv_title")));
		ArrayList<AndroidElement> currency = (ArrayList<AndroidElement>) driver.findElementsById("tv_title");
		currency.get(i).click();
		// new WebDriverWait(driver, timeOutInSeconds)
		// .until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();
		// WebElement keyword = new WebDriverWait(driver, timeOutInSeconds)
		// .until(ExpectedConditions.elementToBeClickable(By.id("etKeyword")));
		// keyword.clear();
		// keyword.sendKeys("上海嘉瑞酒店");
		// new WebDriverWait(driver,
		// timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		// ArrayList<WebElement> elements = (ArrayList<WebElement>)
		// driver.findElementsById("tvTitle");
		// elements.get(1).click();

		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotels_item_container")));
		ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElementsById("view_hotels_item_container");

		// 选择第一家酒店
		elements.get(0).click();
	}

	// 进入酒店详情页,
	public void roomDetail() throws InterruptedException {

		// ArrayList<WebElement> arrayList=(ArrayList<WebElement>)
		// driver.findElementsByClassName("android.widget.CheckedTextView");
		// arrayList.get(arrayList.size()-1).click();

		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//android.widget.CheckedTextView[contains(@text,'網上預付')]")))
				.click();

		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_rooms_list_sub_room_btn_book"))).click();

	}

	// 创单页面,酒店填写页
	/**
	 * @param name
	 *            顾客 姓
	 */
	@SuppressWarnings("unchecked")
	public void orderDetail(String name) {

		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("guest_button"))).click();

		} catch (Exception e) {
			logger.info("c");
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("more_account"))).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("guest_button"))).click();
		}

		logger.info("guset用户登录完成");

		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("hotel_book_summery_hotel_name")));
		} catch (Exception e) {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_positive")))
					.click();
		} finally {

			hotelName = new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("hotel_book_summery_hotel_name")));

			hote_name_detail = hotelName.getText();
			logger.info("---------hote_name_detail--------" + hote_name_detail);
			// hotel_book_summery_room_name
			WebElement roomName = new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("hotel_book_summery_room_name")));

			room_name_detail = roomName.getText();
			logger.info("---------room_name_detail---------" + room_name_detail);

			new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.elementToBeClickable(By.id("view_text_input_layout")));

			ArrayList<AndroidElement> aElements = (ArrayList<AndroidElement>) driver.findElementsById("view_edit_text");
			aElements.get(0).clear();
			aElements.get(0).sendKeys(name);
			aElements.get(1).clear();
			aElements.get(1).sendKeys("testergiven");
			aElements.get(2).clear();
			aElements.get(2).sendKeys("yefeiyang@ctrip.com");

			new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.elementToBeClickable(By.id("hotel_book_contact_phone_input")))
					.sendKeys("89898989");

			WebElement element = new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.elementToBeClickable(
							By.id("hotel_book_bottom_bar_normal_price_view_payment_currency_and_amount")));
			total_amount_detail = element.getText();
			logger.info(deleteZero(total_amount_detail));
			total_amount_detail = element.getText();
			driver.findElementById("tv_bottom_select").click();

			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("btn_positive")))
						.click();
				logger.info("已提交过相同订单");
			} catch (Exception e) {
				logger.info("第一次下单，非同城订单");
			}

		}

	}

	// 支付页面
	@SuppressWarnings("unchecked")
	public void payPage() throws InterruptedException {
		int j = 0;// 支付while循环体初始值
		Boolean flag;
		// SoftAssert assertion = new SoftAssert();
		WebElement totalMonkey = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("tvTotal")));
		String total_amount_pay = totalMonkey.getText();
		// 判断支付价格是否和详情页一致
		String formatMoney = deleteZero(total_amount_detail);
		assertEquals(formatMoney, total_amount_pay);
		// ArrayList<WebElement> hotelinfo = (ArrayList<WebElement>) driver
		// .findElementsByClassName("android.widget.TextView");
		// String hotel_name_pay = hotelinfo.get(3).getText();

		// 判断酒店名字是否一致
		// assertEquals(hote_name_detail, hotel_name_pay);
		// String hotelinfo_pay = hotelinfo.get(4).getText();
		// String[] strings = hotelinfo_pay.split(",");
		// String roomName = strings[0];
		// logger.info(roomName);
		// // 判断房间名字是否一致
		// assertion.assertEquals(room_name_detail, roomName, "房型名称不一致");

		try {
			ArrayList<AndroidElement> ivarrow = (ArrayList<AndroidElement>) driver.findElementsById("iv_arrow");
			if (i == 1) {
				ivarrow.get(0).click();
			} else if (i == 10) {
				ivarrow.get(1).click();
			}

		} catch (Exception e) {

		}
		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("cardbinCardNum")));
		AndroidElement cardnum = (AndroidElement) driver.findElement(By.id("cardbinCardNum"));
		cardnum.click();
		cardnum.sendKeys("5100");
		cardnum.sendKeys("0000");
		cardnum.sendKeys("0000");
		cardnum.sendKeys("0008");
		// 输入银行卡号
		// do {
		// cardnum.clear();
		// driver.pressKeyCode(12);// 5
		// driver.pressKeyCode(8);// 1
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// Thread.sleep(1000);
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// Thread.sleep(1000);
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);//
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// Thread.sleep(1000);
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(7);// 0
		// driver.pressKeyCode(15);// 8
		// j++;
		// mycardnumber = cardnum.getText();
		// logger.info("--------------=" + mycardnumber);
		// flag = mycardnumber.equals("5100 0000 0000 0008");
		// logger.info("----------flag----" + flag);
		// logger.info("---------------=" + j);
		// if (j == 5) {
		// break;
		// }
		// } while (!flag);

		logger.info("账号输入完成");
		// 输入持卡人姓名
		WebElement ceiname = driver.findElementByXPath("//android.widget.EditText[@text='持卡人姓名']");
		ceiname.click();
		ceiname.sendKeys("yyf");

		// 邮箱
		try {
			WebElement ceibEmail = driver.findElementByXPath("//android.widget.EditText[@text='電郵地址']");
			ceibEmail.click();
			ceibEmail.sendKeys("yefeiyang@ctrip.com");
		} catch (Exception e) {
			logger.info("无需输入邮箱");
		}

		// 输入有效期
		WebElement ceibDate = driver.findElementByXPath("//android.widget.EditText[@text='有效期']");
		ceibDate.click();
		do {
			ceibDate.clear();
			driver.pressKeyCode(8);
			Thread.sleep(1000);
			driver.pressKeyCode(8);
			driver.pressKeyCode(9);
			driver.pressKeyCode(9);
			String ceibDatetext = ceibDate.getText();
			logger.info(ceibDatetext + "------------------");
			flag = ceibDatetext.equals("11/22");
			j++;
			if (j == 10) {
				break;
			}
		} while (!flag);

		driver.pressKeyCode(8);
		driver.pressKeyCode(8);
		Thread.sleep(1000);
		driver.pressKeyCode(9);
		driver.pressKeyCode(9);

		// 安全吗
		WebElement ceibCvv = driver.findElementById("ctrip.english.debug:id/ceibCvv");
		ceibCvv.click();
		// ceibCvv.sendKeys("123");
		driver.pressKeyCode(8);
		driver.pressKeyCode(8);
		driver.pressKeyCode(9);
		// 电话号码
		try {
			driver.findElementById("vPhoneGetVerify").sendKeys("13000000001");
		} catch (Exception e) {

		}

		TouchAction action = new TouchAction(driver);
		action.press(379, 333).waitAction(Duration.ofMillis(1000)).moveTo(379, 139).release().perform();
		// ctvSubmit
		driver.findElementById("ctvSubmit").click();
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("ll_edc")));
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("ib_pay"))).click();
		} catch (Exception e) {

		}
		new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("tv_order_number")));
		// assertion.assertAll();

	}

	public String deleteZero(String string) {

		String lastString = string.substring(string.length() - 1, string.length());
		if (string.contains(".") && lastString.equals("0")) {
			String string2 = string.substring(0, (string.length() - 1));
			return string2;
		} else {
			return string;
		}
	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}

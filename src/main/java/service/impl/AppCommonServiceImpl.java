package service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import service.AppCommonService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

public class AppCommonServiceImpl implements AppCommonService {

	@Override
	public AppiumDriver loginForApp(AppiumDriver driver, String userName, String userPassWord) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("rl_account"))).click();
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("tv_email")))
					.isDisplayed();

		} catch (Exception e) {

			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tvSignIn"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("login_btn"))).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("account_input")))
					.clear();
			driver.findElement(By.id("account_input")).sendKeys(userName);
			driver.findElement(By.id("password_input")).sendKeys(userPassWord);
			driver.findElement(By.id("login_btn")).click();
			driver.findElement(By.id("tv_ok")).click();

		} finally {

			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("rl_home"))).click();
		
			return driver;
		}
	}

	@Override
	public AppiumDriver logoutForApp(AppiumDriver driver) {
		// TODO Auto-generated method stub
		// 点击我的
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		// 点击设置按钮

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("mine_setting_img"))).click();
		// 退出登录 edit by yyf
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("mine_setting_img"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("退出登录"))).click();
		// driver.findElement(By.name("退出登录")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("button1"))).click();
		// 退出登录 add by yyf
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.name("请点击登录/注册")));

		return driver;
	}

	@Override
	public AppiumDriver commentSubmit(AppiumDriver driver, String comment) {
		// TODO Auto-generated method stub

		// 星级评定
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("star_1"))).click();
		driver.findElement(By.id("star_2")).click();
		driver.findElement(By.id("star_3")).click();
		// 评论内容
		driver.findElement(By.id("zong_ti_ping_jia")).sendKeys(comment);
		driver.findElement(By.id("submit")).click();

		return driver;
	}

	@Override
	public AppiumDriver swipeToDown(AppiumDriver driver) {
		// TODO Auto-generated method stub

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 1000);// 向下滑动，间隔1s

		return driver;
	}

	@Override
	public AppiumDriver scrollAndFindName(AppiumDriver driver, String searchName, String nameId, String totalNum) {
		// TODO Auto-generated method stub

		// //获取项目总数
		// String totalNum = new
		// WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id(totalNumId))).getText();
		// //去掉数字两边的括号并且将其转换为int
		// int realTotalNum =
		// Integer.valueOf(totalNum.substring(1,totalNum.length()-1))+3;
		// 总数
		int realTotalNum = Integer.valueOf(totalNum) + 6;

		Boolean isfound = false;
		int allNum = 0;

		do {

			int currentNum;
			List<WebElement> elements = driver.findElementsById(nameId);
			currentNum = elements.size();
			System.out.println("当前页面元素总数currentNum=" + currentNum);

			// 页面中如果没有任何元素，直接返回
			if (0 == currentNum) {

				System.out.println("页面中没有任何所要查找的内容！");

				return driver;

			}

			// 查找当前页是否有匹配的内容
			for (WebElement webElement : elements) {

				if (searchName.equals(webElement.getText())) {

					System.out.println("内容已经被找到！");

					isfound = true;

					return driver;
				}
			}

			allNum = allNum + currentNum;

			// 滑动屏幕
			int width = driver.manage().window().getSize().width;
			int height = driver.manage().window().getSize().height;
			driver.swipe(width / 2, height * 7 / 8, width / 2, height * 1 / 8, 1000);

		} while (!isfound && allNum < realTotalNum);// 如果没有找到内容并且查找的项目数已经超过项目总数，跳出循环

		System.out.println("内容没有被找到！");

		return driver;
	}

}

package service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import service.HotelHomePageInitial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

public class HotelHomePageInitialImpl implements HotelHomePageInitial {
	
	@Override
	public AppiumDriver HotelPageStarsFilter(AppiumDriver driver) {
		try{
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return driver;	
		}
		
	}
	
	@Override
	public AppiumDriver HotelPageAdultsChildFilter(AppiumDriver driver) {
		try{
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_adult"))).click();
		     //获取刚进入首页时，显示的成人数
	    	 WebElement adult=driver.findElement(By.id("view_plus_guests_adult"));
	    	 WebElement adultplus=adult.findElement(By.xpath("//android.widget.TextView"));
	    	 int adultnums=Integer.parseInt(adultplus.getText());
	     	//获取刚进入首页时，显示的儿童数
	    	 WebElement child=driver.findElement(By.id("view_plus_guests_child"));
	    	 WebElement childplus=child.findElement(By.xpath("//android.widget.TextView"));
	    	 int childnums=Integer.parseInt(childplus.getText());
    	     for(int i=0;i<adultnums-1;i++)
    	     {
    	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_adult"));
	    	     adults.findElement(By.xpath("//android.widget.ImageView")).click();	
    	     }
    	     for(int j=0;j<childnums;j++)
    	     {
    	    	 WebElement adults=driver.findElement(By.id("view_plus_guests_child"));
    	    	 adults.findElement(By.xpath("//android.widget.ImageView")).click();
    	     }  
    	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_apply"))).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			return driver;
			}
		
	}
	

}

package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class ListKeywordsSearch extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;
    int timeOutInSeconds = 60;

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
    }

    @Test(description = "By ylf : C1309692	地铁站更多搜索", groups = {"Base"})
    public void listMetroStationMoreSearch() throws Exception {
        logger.info("---酒店首页搜索上海---");
        appCommonService.homeSearchHotel(driver, "上海");
        logger.info("---酒店列表页点击搜索框---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();

        logger.info("---向下翻页---");
        appCommonService.swipeToDown(driver);
        appCommonService.swipeToDown(driver);

        logger.info("---点击地铁站更多按钮---");
        List<WebElement> groups = driver.findElements(By.id("rl_top"));
        for (WebElement group : groups) {
            String groupName = group.findElement(By.id("view_hotel_top_destination_group_title")).getText();
            //logger.info(childAge);
            if (groupName.equals("地鐵站")) {
                Actions actions = new Actions(driver);
                actions.moveToElement(group);
                group.click();
                break;
            }
        }

        logger.info("---获取地铁线---");
        WebElement menu = driver.findElement(By.id("rv_menu"));
        groups = menu.findElements(By.className("android.widget.LinearLayout"));
        for (WebElement group : groups) {
            String groupName = group.findElement(By.id("tv_filter_content")).getText();
            logger.info(groupName);
            if (groupName.equals("1號線")) {
                Actions actions = new Actions(driver);
                actions.moveToElement(group);
                logger.info("---点击1号线---");
                group.click();
                break;
            }
        }

        logger.info("---点击莘庄---");
        menu = driver.findElement(By.id("rv_content"));
        groups = menu.findElements(By.className("android.widget.LinearLayout"));
        for (WebElement group : groups) {
            String groupName = group.findElement(By.id("tv_filter_content")).getText();
            //logger.info(childAge);
            if (groupName.equals("莘莊")) {
                Actions actions = new Actions(driver);
                actions.moveToElement(group);
                group.click();
                break;
            }
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).getText();
        if (FilterChecked()) {
            Assert.assertTrue(true);
            logger.info("C1309692 地铁站更多搜索成功");
        } else
            logger.info("C1309692 地铁站更多搜索失败");

    }

    @Test(description = "By ylf : C1309691	地铁站搜索", groups = {"Base"})
    public void listMetroStationSearch() throws Exception {
        logger.info("---酒店首页搜索上海---");
        appCommonService.homeSearchHotel(driver, "上海");
        logger.info("---酒店列表页点击搜索框---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();

        logger.info("---向下翻页---");
        appCommonService.swipeToDown(driver);
        appCommonService.swipeToDown(driver);

        logger.info("---获取筛选项---");
        WebElement root = driver.findElement(By.id("ll_container"));
        List<WebElement> groups = root.findElements(By.id("hotel_top_destination_line_item_0"));


        logger.info(groups.size());

        for (WebElement group : groups) {
            //String groupName = group.findElement(By.id("view_hotel_top_destination_group_title")).getText();
            logger.info(group.getText());
            if (group.getText().equals("靜安寺")) {
                logger.info("---点击地铁站中第一个按钮'靜安寺'---");
                group.click();
                break;
            }
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).getText();
        if (FilterChecked()) {
            Assert.assertTrue(true);
            logger.info("C1309691 地铁站搜索成功");
        } else
            logger.info("C1309691 地铁站搜索失败");

    }

    @Test(description = "By ylf : C1309697	海外火车站搜索", groups = {"Base"})
    public void listOverseaRailwayStationSearch() throws Exception {
        logger.info("---酒店首页搜索东京---");
        appCommonService.homeSearchHotel(driver, "東京");
        logger.info("---酒店列表页点击搜索框---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("etKeyword"))).sendKeys("JR站");


        List<WebElement> titles = driver.findElements(By.id("tvTitle"));


        logger.info(titles.size());

        for (WebElement title : titles) {
            //String groupName = group.findElement(By.id("view_hotel_top_destination_group_title")).getText();
            logger.info(title.getText());
            if (title.getText().equals("JR東京站")) {
                logger.info("---点击火车站中第一个按钮'JR東京站'---");
                title.click();
                break;
            }
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).getText();
        if (FilterChecked()) {
            Assert.assertTrue(true);
            logger.info("C1309697	海外火车站搜索成功");
        } else
            logger.info("C1309697	海外火车站搜索失败");

    }

    @Test(description = "By ylf : C1309696	海外机场车站搜索", groups = {"Base"})
    public void listOverseaAirportSearch() throws Exception {
        logger.info("---酒店首页搜索东京---");
        appCommonService.homeSearchHotel(driver, "東京");
        logger.info("---酒店列表页点击搜索框---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("etKeyword"))).sendKeys("羽田國際機場");


        List<WebElement> titles = driver.findElements(By.id("tvTitle"));


        logger.info(titles.size());

        for (WebElement title : titles) {
            //String groupName = group.findElement(By.id("view_hotel_top_destination_group_title")).getText();
            logger.info(title.getText());
            if (title.getText().equals("東京羽田國際機場")) {
                logger.info("---点击東京羽田國際機場---");
                title.click();
                break;
            }
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).getText();
        if (FilterChecked()) {
            Assert.assertTrue(true);
            logger.info("C1309696	海外机场车站搜索成功");
        } else
            logger.info("C1309696	海外机场车站搜索失败");

    }

    @Test(description = "By sxm : C1309690	海外省和景点下的品牌搜索", groups = {"Base"})
    public void listProvinceSearchBrand() throws Exception {
        logger.info("---酒店首页搜索紐約州---");
        appCommonService.homeSearchHotel(driver, "紐約州");
        logger.info("---酒店列表页搜索品牌---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();

        logger.info("---查看是否存在搜索历史---");
        if (driver.findElement(By.id("view_hotel_top_destination_group_title")).getText().equals("最近搜尋")) {
            logger.info("---清空历史搜索记录---");
            driver.findElement(By.id("tv_hotel_keyword_clear")).click();
        }
        Thread.sleep(1000);
        logger.info("---查找元素brinds---");
        WebElement brinds = driver.findElement(By.id("view_hotel_top_destination_group_container"));
        logger.info("---查找元素brind---");
        WebElement brand = brinds.findElements(By.className("android.widget.FrameLayout")).get(0).
                findElement(By.id("hotel_keywords_search_line_left")).
                findElement(By.id("hotel_top_destination_line_item_0"));
        logger.info("---点击品牌搜索---");
        String searchbrand = brand.getText();
        brand.click();
        try {
            logger.info("---C1309690	海外省和景点下的品牌搜索---");
            WebElement filterreddot = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("top_bar_filter_red_dot")));

            Assert.assertTrue(filterreddot.isDisplayed());
            logger.info("C1309690	海外省和景点下的品牌搜索Pass");
        } catch (Exception e) {
            logger.info("C1309690	海外省和景点下的品牌搜索Fail");
        }
    }

    @Test(description = "By sxm : C1309695	景点搜索", groups = {"Base"})
    public void listSearchScenic() throws Exception {
        logger.info("---首页搜索上海---");
        appCommonService.homeSearchHotel(driver, "上海");
        logger.info("---酒店列表页搜索景点---");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();
        WebElement etKeyWord = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("etKeyword")));
        etKeyWord.sendKeys("外滩");
        List<WebElement> list = driver.findElements(By.id("tvTitle"));
        logger.info("---点击第一个搜索结果---");
        list.get(1).click();
        if (SearchChecked()) {
            logger.info("---C1309695	景点搜索Pass---");
        } else
            logger.info("---C1309695	景点搜索Fail---");
    }

    private boolean SearchChecked() {
        WebElement filterreddot = driver.findElement(By.id("top_bar_location_red_dot"));
        return filterreddot.isDisplayed();
    }

    private boolean FilterChecked() {
        WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
        return filterreddot.isDisplayed();
    }

    //2018/4/8   修改驱动丢失导致的配置失败  by yyf
    @AfterMethod
    public void afterMethod() {
//		     logger.info("---返回搜索首页---");
//		     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
//		     //返回Trip首页	     
//		     logger.info("---返回Trip首页---");
//		     driver.findElementByClassName("android.widget.ImageButton").click();
        driver.quit();
    }

    @AfterClass
    public void afterClass() {
        logger.info("I here afterclass");
        driver.quit();
    }
}

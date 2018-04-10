package test.apptest.hotel;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class HomeKeySearch extends BaseTest {
    int timeOutInSeconds = 60;
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    @SuppressWarnings("rawtypes")
    private AndroidDriver driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        // TestLinster.webDriver = driver; // androiddriver 传递给testlinster


    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

    /**
     * @author yefeiyang
     * <p>
     * C1309602 目的地搜索国内城市名
     * C1309608 根据国内火车站搜索酒店
     * C1309615 当前城市搜索酒店
     */
    // 测试用例 执行 ，数据提供testData
    @Test(dataProvider = "testData", description = "yefei.yang", groups = {"yyf"})
    public void hotelSearch(Map<String, String> datadriven) throws Exception {
        driver = initial.appiumAndroidCtripSetUp(driver, "ctrip.english");
        // TestLinster.webDriver = driver; // androiddriver 传递给testlinster
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        logger.info("初始化成功，准备登陆");
        appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
        logger.info("---" + datadriven.get("id") + "---==>StartTest");
        // 进入酒店首页
        driver.findElement(By.id("myctrip_hotel_icon")).click();
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_stay_in")))
                .click();
        WebElement e = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
        e.clear();
        e.sendKeys(datadriven.get("searchKeyWord"));
        WebElement tvTitle = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
        assertEquals(tvTitle.getText(), datadriven.get("result"));
        driver.pressKeyCode(4);
        Thread.sleep(1000);
        driver.pressKeyCode(4);
        logger.info("---" + datadriven.get("id") + "==>PASS---");
        
    }


    /**
     * @throws MalformedURLException
     * @author yefeiyang
     * C1309622 最近搜索酒店再次点击搜索功能
     */
    @Test(description = "yefei.yang", groups = {"yyf"})
    public void recentSearches() throws MalformedURLException {
        driver = initial.appiumAndroidCtripSetUp(driver, "ctrip.english");
        logger.info("---C1309622  最近搜索酒店再次点击搜索功能---==>StartTest");
        driver.findElement(By.id("myctrip_hotel_icon")).click(); // 进入酒店首页
        WebElement inputBox = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("rl_stay_in")));

        inputBox.click();


        WebElement aomen = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("hotel_top_destination_line_item_1")));

        aomen.click();

        WebElement inputText = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in")));
        String expected = inputText.getText();

        inputText.click();
        WebElement recentName = new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_history_record")));
        String actual = recentName.getText();
        boolean result = actual.contains(expected);

        Assert.assertTrue(result);
        logger.info("---C1309622  最近搜索酒店再次点击搜索功能---==>PASS");
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

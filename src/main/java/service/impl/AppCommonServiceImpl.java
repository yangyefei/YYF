package service.impl;

import com.trip.hotel.test.android.DriverUtils;
import com.trip.hotel.test.android.Page;
import com.trip.hotel.test.android.TouchUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import service.AppCommonService;

import java.util.ArrayList;
import java.util.List;

public class AppCommonServiceImpl implements AppCommonService {
    protected final Log logger = LogFactory.getLog(this.getClass());

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

        // 点击账户
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("rl_account"))).click();

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("tvSignIn")))
                    .isDisplayed();
        } catch (Exception e) {
            // TODO: handle exception
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("tv_email"))).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_sign_out"))).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("btn_positive"))).click();

        }

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
        TouchUtils.swipe(driver, width / 2, height * 3 / 4, width / 2, height / 4, 1000);// 向下滑动，间隔1s

        return driver;
    }

    /**
     * 下拉
     *
     * @param driver
     * @param during
     * @param num
     */
    @Override
    public AppiumDriver swipeToDown(AppiumDriver driver, int during, int num) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        for (int i = 0; i < num; i++) {
            TouchUtils.swipe(driver, width / 2, height * 3 / 4, width / 2, height / 4, during);
        }
        return driver;
    }

    /**
     * 向左滑动
     *
     * @param driver
     * @param during
     * @param num
     */
    public AppiumDriver swipeToLeft(AppiumDriver driver, int during, int num) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        for (int i = 0; i < num; i++) {
            TouchUtils.swipe(driver, width * 3 / 4, height / 2, width / 4, height / 2, during);
        }
        return driver;
    }

    /**
     * 向右滑动
     *
     * @param driver
     * @param during
     * @param num
     */
    public AppiumDriver swipeToRight(AppiumDriver driver, int during, int num) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        for (int i = 0; i < num; i++) {
            TouchUtils.swipe(driver, width / 4, height / 2, width * 3 / 4, height / 2, during);
        }
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
            TouchUtils.swipe(driver, width / 2, height * 7 / 8, width / 2, height * 1 / 8, 1000);

        } while (!isfound && allNum < realTotalNum);// 如果没有找到内容并且查找的项目数已经超过项目总数，跳出循环

        System.out.println("内容没有被找到！");

        return driver;
    }


    @Override
    public AppiumDriver homeSearchHotel(AppiumDriver driver, String keyword) throws InterruptedException {
        //进入首页
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("rl_home"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
        String keywords = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
                .getText();
        if (keywords.equals(keyword)) {
            driver.findElement(By.id("hotel_destination_search_cancel")).click();
        } else {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
                    .sendKeys(keyword);
            Thread.sleep(5000);
            ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
            destinationlist.get(0).click();
        }
        //点击搜索按钮
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
        return driver;
    }


    @Override
    public AppiumDriver listToDetail(AppiumDriver driver) throws InterruptedException {
        //列表页点击首个酒店进入到详情页
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("view_hotels_item_container"))).click();
        return driver;
    }

    @Override
    public void changeLanguageTo(AndroidDriver<WebElement> driver, String targetLanguage) {
        logger.info("开始设置" + targetLanguage);
        logger.info("点击\"Account\"");
        DriverUtils.waitClick(driver, Page.AppHome.ACCOUNT);
        logger.info("点击\"Setting\"");
        DriverUtils.waitClick(driver, Page.Account.SETTING);

        // 切换语言
        WebElement values = DriverUtils.waitFind(driver, new By.ById("value"));
        String currentLanguage = values.getText();
        logger.info("当前语言是：" + currentLanguage);
        if (currentLanguage.equals(targetLanguage)) {
            logger.info("已经是" + targetLanguage);
        } else {
            List<WebElement> sites = driver.findElements(By.id("text"));
            logger.info("点击语言");
            sites.get(0).click();
            // 只是为了等待
            DriverUtils.waitFind(driver, Page.Account.Setting.LANGUAGE_LIST_LANGUAGE_NAME);
            List<WebElement> languages = driver.findElements(Page.Account.Setting.LANGUAGE_LIST_LANGUAGE_NAME);
            boolean isFound = false;
            for (WebElement language : languages) {
                String checkingLanguage = language.getText();
                logger.info("checkingLanguage = " + checkingLanguage);
                if (checkingLanguage.equals(targetLanguage)) {
                    logger.info("是" + targetLanguage + "语言就点击");
                    language.click();
                    isFound = true;
                    break;
                }
            }
            Assert.assertTrue(isFound);
        }

        // 返回到首页
        logger.info("返回到Account首页");
        driver.navigate().back();
        logger.info("返回到Home");
        driver.navigate().back();
    }
}

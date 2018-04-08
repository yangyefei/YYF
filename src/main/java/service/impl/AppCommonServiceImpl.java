package service.impl;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.AppCommonService;

import java.util.ArrayList;
import java.util.List;

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
        driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 1000);// 向下滑动，间隔1s

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
            driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
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
            driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
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
            driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
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
            driver.swipe(width / 2, height * 7 / 8, width / 2, height * 1 / 8, 1000);

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

}

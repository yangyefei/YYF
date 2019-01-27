package com.trip.hotel.test.android.qa.self;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

public class test extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;
    int timeOutInSeconds = 60;
    SoftAssert assertion = new SoftAssert();

    @BeforeSuite
    public void beforeSuites() {
        logger.info("-------------beforesuite-------------");
    }

    @Test
    public void calendar() throws MalformedURLException {
    }

    //硬断言
    @Test
    public void hardAssert() {
        try {
            Assert.assertEquals(1, 1, "yyf");
        } catch (Exception e) {
            logger.info("测试失败");
        }
        logger.info("外面失败");
    }

    //软断言
    @Test
    public void softAssert() {
        assertion.assertEquals(1, 0, "2个不相等");
        logger.info("woowowoowo测试失败");
        assertion.assertAll();  //词句后面语句不执行
        logger.info("woowowoowo测试ok-----------------------------------------------");
    }


    @Test(dataProvider = "testData")
    public void testaa() {

        //10转其他进制
        int i=5;
        String str = Integer.toString(456); //获取数字的十进制表示
        String str2 = Integer.toBinaryString(456); //获取数字的二进制表示
        String str3 = Integer.toHexString(456); //获取数字的十六进制表示
        String str4 = Integer.toOctalString(456); //获取数字的八进制表示
        System.out.println("'456'的十进制表示为：" + str+i);
        System.out.println("'456'的二进制表示为：" + str2);
        System.out.println("'456'的十六进制表示为：" + str3);
        System.out.println("'456'的八进制表示为：" + str4);

    }

    public void testbb(int i,String str){
        System.out.println(i+str);

    }



    //test
    @DataProvider(name = "testData")
    public Iterator<Object[]> data1test() throws IOException {
        return ExcelProviderByEnv(this, "testData");
    }

}

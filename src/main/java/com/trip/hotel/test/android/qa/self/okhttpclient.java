package com.trip.hotel.test.android.qa.self;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Currency;

public class okhttpclient {
    @Test
    public void testokhttp() {
        SoftAssert softAssert=new SoftAssert();
        String url = "http://www.weather.com.cn/data/sk/101010100.html";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String string=response.body().string();
            System.out.println(string);
            JSONObject jsonObject=com.alibaba.fastjson.JSONObject.parseObject(string);
            String city=jsonObject.getJSONObject("weatherinfo").getString("city");
            String cityid=jsonObject.getJSONObject("weatherinfo").getString("cityid");
            String wd=jsonObject.getJSONObject("weatherinfo").getString("WD");
            String ws=jsonObject.getJSONObject("weatherinfo").getString("WS");

            softAssert.assertEquals(city,"北京","beijing");
            softAssert.assertEquals(cityid,"10101010100","城市ID不想等");
            softAssert.assertEquals(wd,"南风","不是南风");
            softAssert.assertEquals(ws,"小于4级","不低于3级");
            softAssert.assertAll();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
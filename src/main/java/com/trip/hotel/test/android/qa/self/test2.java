package com.trip.hotel.test.android.qa.self;

import com.trip.hotel.test.common.BaseTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static java.lang.System.*;

public class test2 extends BaseTest {

    @Test(priority = 2, description = "携程测试hotel", groups = {"base"})
    public void testb() {
        int count = 0;
        String str = "i Am a ApplE";
        StringBuffer yyf = new StringBuffer(str).reverse();

        out.println(yyf);
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                count++;

            }

        }
        out.printf("大写字母有 %s个", count);

    }

    @Test(priority = 1, description = "携程测试hotel", groups = {"base"})
    public void readTest() throws IOException {
        out.println("ReadTest, Please Enter Data:");
        InputStreamReader is = new InputStreamReader(in); //new构造InputStreamReader对象
        BufferedReader br = new BufferedReader(is); //拿构造的方法传到BufferedReader中
        try { //该方法中有个IOExcepiton需要捕获
            String name = br.readLine();
            out.println("ReadTest Output:" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void read() throws IOException {

        String[][] str = new String[6][6];
        str[0][1] = "*";
        str[0][3] = "*";
        str[2][0] = "*";
        str[2][4] = "*";
        str[3][1] = "*";
        str[3][2] = "*";
        str[3][3] = "*";
        int heigh = str.length;
        int length = str[0].length;
        out.println(heigh + "-------" + length);
        for (int i = 0; i < heigh; i++) {
            for (int j = 0; j < length; j++) {
                if (j % 6 == 0) {
                    out.println("O ");
                }
                out.print(str[i][j]);
                out.print(1 / 1);
            }
        }
    }

    @Test
    public void testb1() {
        String string = "5100 0000 0000 0008";
        String string2;
        do {
            out.println("----------=" + string);
            string2 = string;
        } while (string2 != "5100 0000 0000 0008");
    }

    @Test
    public String deleteZero(String string) {

        String lastString = string.substring(string.length() - 1, string.length());
        if (string.contains(".") && lastString.equals("0")) {
            String string2 = string.substring(0, (string.length() - 1));
            return string2;
        } else {
            return string;
        }

    }

    public void MyPrintln(String str) {
        System.out.println(str);
    }

    public void MyPrintln(Number num) {
        System.out.println(num);
    }

    @Test
    public void testDate() {
        double num = 43.55;

        out.println(num);
        int num1 = (int) Math.floor(num);
        int num2 = (int) Math.ceil(num);
        MyPrintln(num1);
        MyPrintln(num2);

    }

    public static String test() {
//        vars.put("jmeter","1111");
        return "success";
    }
}



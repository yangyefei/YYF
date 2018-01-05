package com.web.test;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.Reporter;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;

/**
 * 懒汉模式
 * 
 * @author yyf
 *
 */
@Test
public class Single extends SpringBase {

	public Single() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}
	// private static Single single;
	// private Single() {
	// // TODO Auto-generated constructor stub
	// }

	SoftAssert sa = new SoftAssert();//软断言

	public void yyf() {
		System.out.println("test");
		sa.assertEquals(2, 2);
		sa.assertEquals(2,3,"匹配失败");
		sa.assertAll(); //软收尾
		System.out.println("testover");
		
	}
	// public static Single getInstance(){
	// if(single== null)
	// single=new Single();
	// return single;
	// }
	//

	public static void sumn(int[] array, int n) {
		if (array == null || array.length == 0) {
			System.out.println("数组不能为空");
		} else {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[i] + array[j] == n) {
						System.out.println(i + "   " + j);

					}
				}
			}
		}

	}

	public static void max(int[] array) {
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > temp) {
				temp = array[i];
			}

		}
		System.out.println(temp);
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 10, 5, 6, 7, 8 };
		max(array);
	}
}
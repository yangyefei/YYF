package com.web.test;

import java.net.*;
import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.remote.http.HttpClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import net.sf.json.JSONObject;

import java.io.*;

public class httpDemo {
	public static void main(String[] args) throws IOException {

		OkHttpClient client = new OkHttpClient();
		/**
		 * post
		 */
		// MediaType mediaType = MediaType.parse("application/json");
		// RequestBody body = RequestBody.create(mediaType,
		// "{\r\n\"userId\":\"41bbf7037464d6379a198ecb39a8066b\",\r\n\"userAnswer\":\"1B;2C;3C;4B;5A;6A;7E;8E;9A,9B,9E,9F;\",\r\n\"areaId\":100\r\n}");
		// Request request = new Request.Builder()
		// .url("http://172.16.11.133:8097/yrb/fund/chooseAnswer")
		// .post(body)
		// .addHeader("content-type", "application/json")
		// .addHeader("cache-control", "no-cache")
		// .addHeader("postman-token", "42e1bdb2-4644-cbac-d861-9344eaf16b01")
		// .build();

		/**
		 * get & post方法
		 */
		Request request = new Request.Builder().url("http://172.16.11.133:8097/yrb/sysTag/isExistFwTag/1").build();
		Response response = client.newCall(request).execute();
//		Headers responseHeaders = response.headers();
//		for (int i = 0; i < responseHeaders.size(); i++) {
//			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//		}
		String Str = response.body().string();
		// String[] str2=Str.split(",");
		// List<String> list=Arrays.asList(str2);
		
       /*
        * 创建jason字符串，String;list;object转json
        */
		JSONObject jsonObject = JSONObject.fromObject(Str);

		// JSONArray jsonArray = JSONArray.fromObject(list);
		// JSONArray jsonArray = JSONArray.fromObject(object);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("name", "yyf");

		System.out.println(jsonObject.toString());
		System.out.println(jsonObject2.optString("name"));
		// String id = (String) jsonObject.get("code");
		// System.out.println(id);
	}

}
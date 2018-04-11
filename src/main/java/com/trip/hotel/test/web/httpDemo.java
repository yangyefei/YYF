package com.trip.hotel.test.web;


import okhttp3.OkHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class httpDemo {
	public static void main(String[] args) throws IOException {

		OkHttpClient client = new OkHttpClient();
		String url="http://baidu.com";
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
//		Request request = new Request.Builder().url("http://www.trip.com").build();
//		Response response = client.newCall(request).execute();
		Document doc = (Document) Jsoup.connect(url).get(); 
		//		Headers responseHeaders = response.headers();
//		for (int i = 0; i < responseHeaders.size(); i++) {
//			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//		}
//		String Str = response.body().string();
		System.out.println(doc);
		// String[] str2=Str.split(",");
		// List<String> list=Arrays.asList(str2);
       /*
        * 创建jason字符串，String;list;object转json
        */
//		JSONObject jsonObject = JSONObject.fromObject(Str);

		// JSONArray jsonArray = JSONArray.fromObject(list);
		// JSONArray jsonArray = JSONArray.fromObject(object);
//		System.out.println(jsonObject.toString());
	
		// String id = (String) jsonObject.get("code");
		// System.out.println(id);
	}

}
package com.web.test;

import java.net.*;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;

public class URLDemo {
	public static void main(String[] args) throws IOException {
		int c;

		URL url = new URL("http://www.sohu.com");
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream = urlConnection.getInputStream();
		while ((c = inputStream.read()) != -1) {
			System.out.println((char) c);

		}

	}
}
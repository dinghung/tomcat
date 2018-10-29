package com.dh.tomcat.t01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class T1x2 {

	public static void main(String args[]) throws  Exception {
		Socket socket = new Socket("119.75.213.61",80);
		
		//check is Connected
		System.out.println(socket.isConnected());
		
		OutputStream os = socket.getOutputStream();
		boolean autoflush = true;
		PrintWriter out = new PrintWriter(socket.getOutputStream(),autoflush);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//send an HTTP request to the web server
		out.print("GET /index.jsp HTTP/1.1");
		out.print("Host: 119.75.213.61:80");
		out.print("Connection: Close");
		out.println();
		
		//read the response
		boolean loop = true;
		StringBuffer sb = new StringBuffer(8096);
		while(loop) {
			if(in.ready()) {
				int i = 0;
				while(i != -1) {
					i = in.read();
					sb.append((char) i);
				}
				loop = false;
			}
			Thread.currentThread().sleep(50);
		}
		
		//display the response to the out console
		System.out.print(sb.toString());
		socket.close();
		
	}
}

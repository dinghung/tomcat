package com.dh.tomcat.t01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 接收浏览器请求，访问静态资源
 * 如果资源存在将资源返回浏览器。如果不存在，提示错误信息。
 * @author dh
 *
 */
public class HttpServer {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
		
	}

	private void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//loop waiting for a request
		while(!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				//create request object and parse
				Request request = new Request(input);
				request.parse();
				
				//create Response object
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				
				//close the socket
				socket.close();
				
				//check if the previous URI is a shutdown command
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
				
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
		}
	}

}

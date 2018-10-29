package com.dh.tomcat.t02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
  *  每次調用會輸出方法名稱，service方法會輸出特定字符串到客戶端
 * @author dh
 *
 */
public class PrimitiveServlet implements Servlet{

	public void init(ServletConfig config) throws ServletException {
		System.out.print("init");
		
	}

	public ServletConfig getServletConfig() {
		
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.print("from service");
		PrintWriter out = res.getWriter();
		out.println("Hello, Roses are red.");
		out.print("Violets are blue.");
	}

	public String getServletInfo() {
		
		return null;
	}

	public void destroy() {
		System.out.print("destroy");
		
	}


}

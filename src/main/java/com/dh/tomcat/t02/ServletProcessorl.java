package com.dh.tomcat.t02;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

public class ServletProcessorl {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		
		try {
			//create a URLClassLoader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(WEB_ROOT);
			
			//the froming of repository is taken from the
			//createClassLoader method in
			//org.apache.catalina.startup.ClassLoaderFactory
			String repository= (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			
			//the code for forming the URL is taken from
			//the addRepository method in
			//org.apache.catalina.loader.StandardClassLoader
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		Servlet servlet = null;
		
		try {
			servlet = (Servlet)myClass.newInstance();
			servlet.service(request, response);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

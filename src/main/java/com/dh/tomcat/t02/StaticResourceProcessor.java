package com.dh.tomcat.t02;

public class StaticResourceProcessor {

	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

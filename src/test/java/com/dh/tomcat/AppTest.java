package com.dh.tomcat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   public static void main(String[] args) {
	   Lock lock = new ReentrantLock();
	   lock.lock();
   }
}

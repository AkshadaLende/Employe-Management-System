package com.employment.management.config;

public class Singleton {
   private static Singleton instance;
	private Singleton() {
		
		// TODO Auto-generated constructor stub
	}
	
	final public static Singleton getInstance() {
		if (instance==null) {
			return new Singleton();
		}
		return instance;
	}
 
}

package com.app.util;

import java.io.InputStream;
import java.net.URL;

public final class ResourceLoader {
	
	public static InputStream load(String path){
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if(input == null)
			input =  ResourceLoader.class.getResourceAsStream("/" + path);
		return input;
		
	}
	
	public static URL source(String path){
		URL input = ResourceLoader.class.getResource(path);
		if(input == null)
			input =  ResourceLoader.class.getResource("/" + path);
		return input;
		
	}
	
	public static String dir(){
		return System.getProperty("user.dir");
	}
}

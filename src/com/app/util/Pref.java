package com.app.util;
import java.util.prefs.Preferences;

public class Pref {
	
	public static void addPref(String name,String value){
			Preferences pref = Preferences.userRoot();
			pref.put(name, value);
	}
	
	public static String getPref(String name){
		Preferences pref = Preferences.userRoot();
		return pref.get(name, "root");
	}

}

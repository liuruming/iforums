package net.iforums.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages extends HashMap<String,String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3616659141540365726L;

	public Messages(){
		ResourceBundle res = ResourceBundle.getBundle("i18n/"+getClass().getSimpleName().toLowerCase(),Locale.getDefault());
		
		Enumeration<String> keys = res.getKeys();
		
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			put(key, res.getString(key));
		}
	}
	
	public String getMessage(String key){
		return get(key);
	}
}

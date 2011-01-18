package net.iforums.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages extends HashMap<String,String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3616659141540365726L;
	private String terms="";

	public Messages(){
		ResourceBundle res = ResourceBundle.getBundle("i18n/"+getClass().getSimpleName().toLowerCase(),Locale.getDefault());
		
		Enumeration<String> keys = res.getKeys();
		
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			put(key, res.getString(key));
		}
		String fileName = "/i18n/terms/terms_"+Locale.getDefault()+".txt";
		System.out.println(fileName);
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(Messages.class.getResourceAsStream(fileName),"utf-8"));
			String line = null;;
			line = bufferedReader.readLine();
		    while(line!=null){
		    	terms += line+"\n";
			    line = bufferedReader.readLine();
		    }
		} catch (IOException e) {
		}
	}
	public static void main(String[] args) {
		System.out.println(new Messages().getTerms());
	}
	public String getMessage(String key){
		return get(key);
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
}

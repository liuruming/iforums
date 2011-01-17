package net.iforums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ForumConstants {

	public static final String ERROR_VIEW_NAME = "error.html";
	
	public static void main(String[] args) {
		ResourceBundle res = ResourceBundle.getBundle("i18n/messages",Locale.getDefault());

		Map<String,String> res1 = new HashMap<String,String>();
//		res1.putAll(res.);
        System.out.println(res.getString("ForumIndex.moderators"));
	}
}

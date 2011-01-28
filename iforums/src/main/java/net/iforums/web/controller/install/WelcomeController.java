package net.iforums.web.controller.install;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.iforums.web.controller.AbstractController;

@Controller
@RequestMapping(value="install/welcome.do")
public class WelcomeController extends AbstractController {

	@Override
	protected ModelAndView handleGetPostRequestInternal(HttpServletRequest request, HttpServletResponse response,Map<String, Object> model) throws Exception {
		model.put("language", ServletRequestUtils.getStringParameter(request,"language"));
		model.put("database", ServletRequestUtils.getStringParameter(request,"database"));
		model.put("dbhost", ServletRequestUtils.getStringParameter(request,"dbHost"));
		model.put("dbuser", ServletRequestUtils.getStringParameter(request,"dbUser"));
		model.put("dbname", ServletRequestUtils.getStringParameter(request,"dbName"));
		model.put("dbport", ServletRequestUtils.getStringParameter(request,"dbPort"));
		model.put("dbpasswd", ServletRequestUtils.getStringParameter(request,"dbPassword"));
		model.put("dbencoding", ServletRequestUtils.getStringParameter(request,"dbEncoding"));
		model.put("use_pool", ServletRequestUtils.getStringParameter(request,"usePool"));
		model.put("forumLink", ServletRequestUtils.getStringParameter(request,"forumLink"));
		model.put("siteLink", ServletRequestUtils.getStringParameter(request,"siteLink"));
		model.put("dbdatasource", ServletRequestUtils.getStringParameter(request,"dbdatasource"));
		
		return new ModelAndView(getViewName(),model);
	}

}

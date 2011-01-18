package net.iforums.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.utils.ParamUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="login.do")
public class LoginController extends AbstractController{

	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		ParamUtil paramUtil = new ParamUtil(request);
		
		String username = paramUtil.getString("username", "");
		String password = paramUtil.getString("password", "");
		return new ModelAndView(getViewName(),model);
	}

}

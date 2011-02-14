package net.iforums.web.controller.sso;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.beans.User;
import net.iforums.service.SSOService;
import net.iforums.web.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value="sso.do")
public class SingleSignOnController extends AbstractController{
	
	@Resource
	private SSOService sSOService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		String username = ServletRequestUtils.getStringParameter(request, "username", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
		
		String path = ServletRequestUtils.getStringParameter(request, "path", "");

		User user = sSOService.validateLogin(username, password);
		
		logger.info("user:"+user);
		if(user!=null){
			request.getSession().setAttribute("loginUser", user);
		}else{
			return new ModelAndView(new RedirectView("login.html?error=1"));
		}
		return new ModelAndView(new RedirectView(path));
	}

}

package net.iforums.web.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.iforums.web.controller.AbstractController;

@Controller
@RequestMapping(value="/user/")
public class ProfileController extends AbstractController {

	@RequestMapping(value="profile.do")
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		return new ModelAndView(getViewName(),model);
	}

}

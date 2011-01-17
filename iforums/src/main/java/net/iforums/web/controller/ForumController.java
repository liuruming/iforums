package net.iforums.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.service.ForumService;
import net.iforums.utils.JsonUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="forum.do")
public class ForumController extends AbstractController{
	
	@Resource
	private ForumService forumService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,Map<String,Object> model)
			throws Exception {
		model.put("result", System.currentTimeMillis());
		
		model.put("categoryList", forumService.selectCategoryList(0, Integer.MAX_VALUE,true));
		System.out.println(JsonUtil.toString(model));
		return new ModelAndView(getViewName(),model);
	}
}

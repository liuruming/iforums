package net.iforums.web.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.Command;
import net.iforums.ControllerUtils;
import net.iforums.ForumStartup;
import net.iforums.JForumExecutionContext;
import net.iforums.SessionFacade;
import net.iforums.beans.Banlist;
import net.iforums.context.JForumContext;
import net.iforums.context.RequestContext;
import net.iforums.context.ResponseContext;
import net.iforums.context.web.WebRequestContext;
import net.iforums.context.web.WebResponseContext;
import net.iforums.repository.BanlistRepository;
import net.iforums.repository.ModulesRepository;
import net.iforums.repository.SecurityRepository;
import net.iforums.service.ForumService;
import net.iforums.utils.I18n;
import net.iforums.utils.JsonUtil;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;

@Controller
@RequestMapping(value="forum.do")
public class ForumController extends AbstractController{
	
	@Resource
	private ForumService forumService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("result", System.currentTimeMillis());
		
		model.put("categoryList", forumService.selectCategoryList(0, Integer.MAX_VALUE,true));
		System.out.println(JsonUtil.toString(model));
		return new ModelAndView(getViewName(),model);
	}
}

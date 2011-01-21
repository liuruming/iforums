package net.iforums.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.beans.Topic;
import net.iforums.service.ForumService;
import net.iforums.service.TopicService;
import net.iforums.utils.ParamUtil;
import net.iforums.web.ParamConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="topic.do")
public class TopicController extends AbstractController {
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private ForumService forumService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		ParamUtil paramUtil = new ParamUtil(request);
		
		long topicId = paramUtil.getInt(ParamConstants.ID, -1);
		
		Topic topic = topicService.getTopicById(topicId);
		model.put("topic", topic);
		model.put("forum", forumService.getForumById(topic.getForumId()));
		return new ModelAndView(getViewName(),model);
	}

}

package net.iforums.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.beans.Topic;
import net.iforums.service.ForumService;
import net.iforums.service.PostService;
import net.iforums.service.TopicService;
import net.iforums.utils.ParamUtil;
import net.iforums.web.ParamConstants;

import org.apache.velocity.tools.view.servlet.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="topic.do")
public class TopicController extends AbstractController {
	
	@Resource
	private PostService postService;
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private ForumService forumService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		ParamUtil paramUtil = new ParamUtil(request);
		
		int page = ServletRequestUtils.getIntParameter(request, ParamConstants.PAGE, 0);
		int size = ServletRequestUtils.getIntParameter(request, ParamConstants.SIZE, 10);
		int topicId = paramUtil.getInt(ParamConstants.ID, -1);
		
		Topic topic = topicService.getTopicById(topicId);
		model.put("posts",postService.selectPostByTopicId(topicId, page, size));
		model.put("topic", topic);
		model.put("forum", forumService.getForumById(topic.getForumId()));
		return new ModelAndView(getViewName(),model);
	}

}

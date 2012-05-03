package com.googlecode.iforums.web.module.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.web.controller.AbstractController;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class ForumTopicListModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
//        ModelAndView modelAndView = context.getModelAndView();
        int forumId = getForumId(request);
        
        int page = paramInt(request, "page", 0);
        int size = AbstractController.TOPIC_SIZE;
        
        Forum forum = get(model, "forum");
        
        //显示话题列表
        page = getPage(forum.getTopics(), page, size);
        
        List<Topic> topicList = topicService.select(forumId, page, size);
        model.addAttribute("topicList", topicList);
        return null;
    }

}

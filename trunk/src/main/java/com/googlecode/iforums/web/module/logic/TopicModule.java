package com.googlecode.iforums.web.module.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.googlecode.iforums.bean.Post;
import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.util.PaginationUtils;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class TopicModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
//        ModelAndView modelAndView = context.getModelAndView();
        int topicId = paramInt(request, "topicId");
        int page = paramInt(request, "page", 0);
        
        Topic topic = topicService.getObjectById(topicId);
        if(topic == null){
            return new ModelAndView(new RedirectView("error.html"));
        }
        
        model.put("topic", topic);
        topicService.incViews(topicId);
        
        model.put("forum", topic.getForum());
        
        getForum(model, topic.getForumId());
        
        //加入分页信息
        model.addAllAttributes(PaginationUtils.pagination(topic.getReplies(), page, SIZE));
        
        //回复分页
        page = getPage(topic.getReplies(), page, SIZE);
        List<Post> postList = postService.selectByTopicId(topicId, page, SIZE);
        model.put("postList", postList);
        return null;
    }

}

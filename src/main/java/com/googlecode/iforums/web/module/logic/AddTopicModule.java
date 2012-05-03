package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.Post;
import com.googlecode.iforums.bean.PostText;
import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class AddTopicModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
        ModelAndView modelAndView = context.getModelAndView();
        User loginUser = getLoginUser(request);
        
        //插入话题
        Topic topic = new Topic();
        populate(topic, request);
        topic.setUserId(loginUser.getId());
        topicService.insert(topic);
        
        //插入帖子表
        Post post = new Post();
        populate(post, request);
        post.setPosterIp(request.getRemoteHost());
        post.setUserId(loginUser.getId());
        post.setTopicId(topic.getId());
        postService.insert(post);
        
        //插入帖子文本表格
        PostText postText = new PostText();
        populate(postText, request);
        postText.setId(post.getId());
        postTextService.insert(postText);
        
        //更新主贴最新和最后id
        topicService.setPostId(topic.getId(), post.getId());
        
        //更新论坛最后帖子id和主题总数
        forumService.setLastTopicId(getForumId(request), topic.getId());
        
        model.addAttribute("info", "提示信息");
        model.addAttribute("topic", topic);
        modelAndView.setViewName("addTopicComplete");
        return modelAndView;
    }

}

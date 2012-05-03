package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.bean.Post;
import com.googlecode.iforums.bean.PostText;
import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class ReplyModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
//        ModelAndView modelAndView = context.getModelAndView();
        int forumId = getForumId(request);
        
        logger.info("forumId:" + forumId);
        
        int topicId = getTopicId(request);
        
        int page = paramInt(request, "page");
        
        User loginUser = getLoginUser(request);
        //插入帖子表
        Post post = new Post();
        populate(post, request);
        post.setPosterIp(request.getRemoteHost());
        post.setUserId(loginUser.getId());
        post.setTopicId(topicId);
        postService.insert(post);
        
        //插入帖子文本表格
        PostText postText = new PostText();
        populate(postText, request);
        postText.setId(post.getId());
        postTextService.insert(postText);
        
        //更新主贴最新和最后id
        topicService.setLastPostId(topicId, post.getId());
        
        Topic topic = get(model, "topic");
        Forum forum = get(model, "forum");
        topic.setForum(forum);
        return new ModelAndView(new RedirectView(path.url(topic, page)));
    }

}

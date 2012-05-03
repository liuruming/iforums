package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.util.PaginationUtils;
import com.googlecode.iforums.web.controller.AbstractController;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class ForumModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        Forum forum = null;
        int forumId = getForumId(request);
        if(Integer.MIN_VALUE == forumId){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        
        forum = forumService.getObjectById(forumId);
        model.addAttribute("forum", forum);
        if(forum!=null){
            int page = paramInt(request, "page", 0);
            int size = AbstractController.TOPIC_SIZE;
            
            model.addAllAttributes(PaginationUtils.pagination(forum.getTopics(), page, size));
            
            model.addAttribute("category", categoryService.getObjectById(forum.getCategoryId()));
            modelAndView.setViewName("forum");
        }else{
            return new ModelAndView(new RedirectView(request.getContextPath() + "/"));
        }
        return null;
    }

}

package com.googlecode.iforums.web.module.logic;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class ForumInfoModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        model.put("latestUser", userService.getLatestUser());
        model.put("totalUsers", userService.getTotal());
        model.put("totalPosts", postService.getTotal());
        return null;
    }

}

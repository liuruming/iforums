package com.googlecode.iforums.web.module.view;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.web.module.AbstractViewModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class DefaultViewModule extends AbstractViewModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
//        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        modelAndView.setViewName(context.getMethod().getName());
        return null;
    }

}

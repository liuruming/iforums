package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.util.PathUtils;
import com.googlecode.iforums.web.module.Module;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class ResourceModule implements Module {

    private static PathUtils path = new PathUtils();
    
    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
//        ModelAndView modelAndView = context.getModelAndView();
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("p", path);
        return null;
    }

}

package com.googlecode.iforums.web.module;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface Module {

    public static final String SINGLE_MODLE = "$";
    
    public ModelAndView handler(WebModuleContext context, ModelMap model);
    
}

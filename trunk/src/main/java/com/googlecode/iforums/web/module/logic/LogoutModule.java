package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class LogoutModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
//        ModelAndView modelAndView = context.getModelAndView();
        String refer = header(request, "Referer");
        model.addAttribute("refer", refer);
        
        setLoginUser(request, null);
        return new ModelAndView(new RedirectView(StringUtils.isBlank(refer)?request.getContextPath()+"/":refer));

    }

}

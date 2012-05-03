package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class UserModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        
        int userId = paramInt(request, "userId");
        if(userId == Integer.MIN_VALUE){
            model.addAttribute("error", "用户不存在");
            return modelAndView;
        }
        User user = userService.getObjectById(userId);
        
        if(user == null){
            model.addAttribute("error", "用户不存在");
            return modelAndView;
        }
        model.addAttribute("user", user);
        return null;
    }

}

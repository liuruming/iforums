package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class RegisterModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        boolean agree = ServletRequestUtils.getBooleanParameter(request, "agree", false);
        if(agree){
            String username = ServletRequestUtils.getStringParameter(request, "username", "");
            String email = ServletRequestUtils.getStringParameter(request, "email", "");
            String password = ServletRequestUtils.getStringParameter(request, "password", "");
            
            if(StringUtils.isBlank(username)||StringUtils.isBlank(email)){
                modelAndView.setViewName("register");
                modelAndView.getModelMap().addAttribute("error", "关键信息为空");
                return modelAndView;
            }
            User user = new User();
            
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            
            boolean isExist = userService.isExist(username, email);
            
            if(isExist){
                model.addAttribute("error", "用户已经存在");
                modelAndView.setViewName("register");
            }else{
                userService.insert(user);
                modelAndView.setViewName("registerComplete");
            }
        }else{
            modelAndView.setViewName("agreement");
        }
        
        return null;
    }

}

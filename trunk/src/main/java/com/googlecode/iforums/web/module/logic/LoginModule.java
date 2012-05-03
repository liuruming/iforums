package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class LoginModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        String username = paramString(request, "username");
        String password = paramString(request, "password");
        
        String refer = request.getHeader("Referer");
        model.addAttribute("refer", refer);
        
        modelAndView.setViewName("login");
        if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
            User user = userService.getUserByUserName(username);
            if(user!=null){
                if(user.getPassword().equals(password)){
                    request.getSession().setAttribute("loginUser", user);
                    return new ModelAndView(new RedirectView(StringUtils.isBlank(refer)||refer.contains("login.html")?request.getContextPath()+"/":refer));
                }else{
                    model.addAttribute("error", "密码错误");
                }
            }else{
                model.addAttribute("error", "用户名错误");
            }
        }
        return modelAndView;
    }

}

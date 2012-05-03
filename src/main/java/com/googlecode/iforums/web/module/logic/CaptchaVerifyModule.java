package com.googlecode.iforums.web.module.logic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class CaptchaVerifyModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        String vcode = ServletRequestUtils.getStringParameter(request, "vcode", "");
        String vsesscode = remove(request, "vcode");
        
        logger.error("vcode:" + vcode);
        if(StringUtils.isNotBlank(vsesscode)&&vsesscode.equalsIgnoreCase(vcode)){
            return null;
        }
        
        logger.error("vcode error:" + vcode);
        modelAndView.setViewName("error");
        model.put("error", "验证码不正确");
        return modelAndView;
    }

}

package com.googlecode.iforums.web.module.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.util.RandomImageUtils;
import com.googlecode.iforums.util.RandomImageUtils.VerifyImage;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class CaptchaModule extends AbstractLogicModule {
    private static RandomImageUtils utils = new RandomImageUtils();
    
    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        
        VerifyImage verifyImage = utils.creatImage();
        String srand = verifyImage.getSRand();
        
        set(request, "vcode", srand);
        
        BufferedImage image = verifyImage.getImage();
        response.setHeader("Expires", String.valueOf(System.currentTimeMillis()));
        response.setContentType("image/jpeg");
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("captcha.do", e);
        }
        
        return null;
    }

}

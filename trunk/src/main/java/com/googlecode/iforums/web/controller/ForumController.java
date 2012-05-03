package com.googlecode.iforums.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.annotation.Modules;
import com.googlecode.iforums.web.module.logic.AddTopicModule;
import com.googlecode.iforums.web.module.logic.CaptchaModule;
import com.googlecode.iforums.web.module.logic.CaptchaVerifyModule;
import com.googlecode.iforums.web.module.logic.CategoryModule;
import com.googlecode.iforums.web.module.logic.ForumInfoModule;
import com.googlecode.iforums.web.module.logic.ForumModule;
import com.googlecode.iforums.web.module.logic.ForumTopicListModule;
import com.googlecode.iforums.web.module.logic.HotestTopicModule;
import com.googlecode.iforums.web.module.logic.LoginModule;
import com.googlecode.iforums.web.module.logic.LoginUserModule;
import com.googlecode.iforums.web.module.logic.LogoutModule;
import com.googlecode.iforums.web.module.logic.RecentTopicModule;
import com.googlecode.iforums.web.module.logic.RegisterModule;
import com.googlecode.iforums.web.module.logic.ReplyModule;
import com.googlecode.iforums.web.module.logic.TopicModule;
import com.googlecode.iforums.web.module.logic.UserModule;
import com.googlecode.iforums.web.module.view.DefaultViewModule;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class ForumController extends AbstractController {

    @RequestMapping("/login.do")
    @Modules(value = { LoginModule.class }, view = DefaultViewModule.class)
    public ModelAndView login(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/logout.do")
    @Modules({LogoutModule.class})
    public ModelAndView logout(HttpServletRequest r, HttpServletResponse p, ModelAndView m,  @RequestHeader(required=false, value="Referer") String refer) {
        return $(r, p, m);
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping("/categoryList.do")
    @Modules({CategoryModule.class, ForumInfoModule.class})
    public ModelAndView categoryList(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping("/category.do")
    @Modules({CategoryModule.class, ForumInfoModule.class})
    public ModelAndView category(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/forum.do")
    @Modules({CategoryModule.class,ForumModule.class, ForumTopicListModule.class})
    public ModelAndView forum(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/recentTopics.do")
    @Modules({RecentTopicModule.class})
    public ModelAndView recentTopics(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/hotTopics.do")
    @Modules({HotestTopicModule.class})
    public ModelAndView hotTopics(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/userInfo.do")
    @Modules({UserModule.class})
    public ModelAndView userInfo(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/editInfo.do")
    @Modules({LoginUserModule.class, UserModule.class})
    public ModelAndView editInfo(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/reply.do")
    @Modules({LoginUserModule.class, ForumModule.class, TopicModule.class, ReplyModule.class})
    public ModelAndView reply(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/captcha.do")  
    @Modules({CaptchaModule.class})
    public ModelAndView captcha(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/topic.do")
    @Modules({TopicModule.class})
    public ModelAndView topic(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/addTopic.do")
    @Modules({LoginUserModule.class, CaptchaVerifyModule.class, ForumModule.class, AddTopicModule.class})
    public ModelAndView addTopic(HttpServletRequest r, HttpServletResponse p, ModelAndView m) {
        return $(r, p, m);
    }
    
    @RequestMapping("/newTopic.do")
    @Modules({LoginUserModule.class, ForumModule.class})
    public ModelAndView newTopic(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        return $(request, response, modelAndView);
    }
    
    @RequestMapping("/pm/inbox.do")
    @Modules({ LoginUserModule.class })
    public ModelAndView pmInbox(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        return $(request, response, modelAndView);
    }
    
    @RequestMapping("/pm/outbox.do")
    @Modules({LoginUserModule.class})
    public ModelAndView pmOutbox(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        return $(request, response, modelAndView);
    }
    
    @RequestMapping("/register.do")
    @Modules({CaptchaVerifyModule.class, RegisterModule.class})
    public ModelAndView register(HttpServletRequest r, HttpServletResponse p, ModelAndView m, @RequestHeader(required=false, value="Referer") String refer) {
        return $(r, p, m);
    }

}
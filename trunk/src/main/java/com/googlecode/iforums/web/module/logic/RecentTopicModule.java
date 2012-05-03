package com.googlecode.iforums.web.module.logic;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class RecentTopicModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        List<Topic> topicList = topicService.getRecentTopicList(0, 20);
        model.addAttribute("info", "最新主题");
        model.addAttribute("topicList", topicList);
        return null;
    }

}

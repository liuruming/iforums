package com.googlecode.iforums.web.module;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;

import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.service.CategoryService;
import com.googlecode.iforums.service.ForumService;
import com.googlecode.iforums.service.PostService;
import com.googlecode.iforums.service.PostTextService;
import com.googlecode.iforums.service.TopicService;
import com.googlecode.iforums.service.UserService;
import com.googlecode.iforums.util.PathUtils;

@Component
public abstract class AbstractLogicModule extends AbstractModule implements LogicModule {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Resource
    protected UserService userService;
    
    @Resource
    protected ForumService forumService;
    
    @Resource
    protected CategoryService categoryService;
    
    @Resource
    protected TopicService topicService;
    
    @Resource
    protected PostService postService;
    
    @Resource
    protected PostTextService postTextService;
    
    public static final String LOGIN_USER = "loginUser";
    
    public static final int SIZE = 10;
    public static final int TOPIC_SIZE = 30;
    
    protected static PathUtils path = new PathUtils();
    
    public void set(HttpServletRequest request, String key, String value){
        request.getSession().setAttribute(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(HttpServletRequest request, String key){
        return (T)request.getSession().getAttribute(key);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(ModelMap model, String topic){
        return (T)model.get(topic);
    }
    @SuppressWarnings("unchecked")
    public <T> T remove(HttpServletRequest request, String key){
        T value = (T)request.getSession().getAttribute(key);
        request.getSession().removeAttribute(key);
        return value;
    }
    
    public User getLoginUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(LOGIN_USER);
        
        return user;
    }
    public void setLoginUser(HttpServletRequest request, User user){
        if(user == null){
            request.getSession().removeAttribute(LOGIN_USER);
        }
        request.getSession().setAttribute(LOGIN_USER, user);
    }
    
    public int getCategoryId(HttpServletRequest request){
        return paramInt(request, "categoryId");
    }
    
    public int getForumId(HttpServletRequest request){
        return paramInt(request, "forumId");
    }
    public int getTopicId(HttpServletRequest request){
        return paramInt(request, "topicId");
    }
    public int getPage(int total,int current, int size){
        if(size == 0){
            return 1;
        }
        int pageNumber = (total+size)/size;
        if(current == 0){
            current = pageNumber;
        }else if(current<0){
            current = 1;
        }
        
        if(current>pageNumber){
            current = pageNumber;
        }
        return current;
    }
    public int getSize(HttpServletRequest request){
        return SIZE;
    }
    
    public int paramInt(HttpServletRequest request, String key, int defaultValue){
        return ServletRequestUtils.getIntParameter(request, key, defaultValue);
    }
    
    public int paramInt(HttpServletRequest request, String key){
        return paramInt(request, key, Integer.MIN_VALUE);
    }
    
    public String paramString(HttpServletRequest request, String key){
        return ServletRequestUtils.getStringParameter(request, key, StringUtils.EMPTY);
    }
    
    public String header(HttpServletRequest request, String name){
        return request.getHeader(name);
    }
    
    public <T> T populate(T obj, HttpServletRequest request){
        try {
            BeanUtils.populate(obj, request.getParameterMap());
        } catch (Exception e) {
            logger.warn("参数接受错误", e);
        }
        
        return obj;
    }
    
    public Forum getForum(ModelMap model, int forumId){
        Forum forum = get(model, "forum");
        if(forum != null){
            getCategory(model, forum.getCategoryId());
            return forum;
        }
        
        if(Integer.MIN_VALUE != forumId){
            forum = forumService.getObjectById(forumId);
            model.addAttribute("forum", forum);
            
            if(forum != null){
                getCategory(model, forum.getCategoryId());
            }
        }
        
        return forum;
    }

    
    public Category getCategory(ModelMap model, int categoryId){
        Category category = get(model, "category");
        if(category == null){
            category = categoryService.getObjectById(categoryId);
            model.addAttribute("category", category);
        }
        return category;
    }
}

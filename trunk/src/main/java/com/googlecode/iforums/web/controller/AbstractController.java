package com.googlecode.iforums.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.annotation.Modules;
import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.service.CategoryService;
import com.googlecode.iforums.service.ForumService;
import com.googlecode.iforums.service.PostService;
import com.googlecode.iforums.service.PostTextService;
import com.googlecode.iforums.service.TopicService;
import com.googlecode.iforums.service.UserService;
import com.googlecode.iforums.web.module.Module;
import com.googlecode.iforums.web.module.WebModuleContext;
import com.googlecode.iforums.web.module.logic.ResourceModule;
import com.googlecode.iforums.web.module.view.NullModule;

@Component
public class AbstractController implements ApplicationContextAware {
    
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
    
    private ApplicationContext applicationContext;
    
    private static Map<Class<? extends Module>, Module> handlerMap = new ConcurrentHashMap<Class<? extends Module>, Module>();
    
    private static Map<String, Method> methodMap = new ConcurrentHashMap<String, Method>();
    
    private static List<Class<? extends Module>> defaultHandlerList = new ArrayList<Class<? extends Module>>();
    static {
        defaultHandlerList.add(ResourceModule.class);
        
        List<Class<? extends AbstractController>> controllerList = new ArrayList<Class<? extends AbstractController>>();
        controllerList.add(ForumController.class);
        
        for(Class<? extends AbstractController> klass:controllerList){
            Method[] methods = klass.getMethods();
            
            for(Method method:methods){
                methodMap.put(method.getName(), method);
            }
        }
    };
    
    private Module getHandler(Class<? extends Module> clazz){
        Module handler = handlerMap.get(clazz);
        
        if( handler == null){
            handler = applicationContext.getBean(clazz);
            
            if(handler!=null)
                handlerMap.put(clazz, handler);
        }
        
        return handler;
    }
    protected ModelAndView $(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView){
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        
        Method method = methodMap.get(methodName);

        Modules handlers = AnnotationUtils.findAnnotation(method, Modules.class);
        if(handlers == null){
            logger.warn("Request Url without Module config:" + request.getRequestURI());
            return modelAndView;
        }
        Class<? extends Module>[] clazzes = handlers.value();
        
        List<Class<? extends Module>> handlerList = new ArrayList<Class<? extends Module>>();
        
        handlerList.addAll(defaultHandlerList);
        handlerList.addAll(Arrays.asList(clazzes));
        
        ModelAndView result = null;
        Module currentModule = null;
        for(Class<? extends Module> clazz: handlerList){
            currentModule = getHandler(clazz);
            if(currentModule == null){
                System.out.println("Class Handler:" + clazz.getCanonicalName() + " is null");
                continue;
            }
            WebModuleContext context = new WebModuleContext(method, request, response, modelAndView);
            result = currentModule.handler(context, modelAndView.getModelMap());
            
            if( result != null ){
                return result;
            }
        }
        
        if(currentModule != null&&currentModule instanceof NullModule){
            return null;
        }
        return modelAndView;
    }
    public void set(HttpServletRequest request, String key, String value){
        request.getSession().setAttribute(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(HttpServletRequest request, String key){
        return (T)request.getSession().getAttribute(key);
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
    public int getPage(HttpServletRequest request){
        int page = paramInt(request, "page");
        return page<=0?1:page;
    }
    public int getSize(HttpServletRequest request){
        return SIZE;
    }
    
    public int paramInt(HttpServletRequest request, String key){
        return ServletRequestUtils.getIntParameter(request, key, Integer.MIN_VALUE);
    }
    
    public <T> T populate(T obj, HttpServletRequest request){
        try {
            BeanUtils.populate(obj, request.getParameterMap());
        } catch (Exception e) {
            logger.warn("参数接受错误", e);
        }
        
        return obj;
    }
    
    public Forum getForum(HttpServletRequest request){
        return getForum(request, getForumId(request));
    }
    public Forum getForum(HttpServletRequest request, int forumId){
        Forum forum = null;
        if(Integer.MIN_VALUE != forumId){
            forum = forumService.getObjectById(forumId);
            request.setAttribute("forum", forum);
            if(forum!=null){
                request.setAttribute("category", categoryService.getObjectById(forum.getCategoryId()));
            }
        }
        return forum; 
    }
    
    public Category getCategory(HttpServletRequest request, int categoryId){
        Category category = categoryService.getObjectById(categoryId);
        request.setAttribute("category", category);
        return category;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
package com.googlecode.iforums.util;

import org.apache.commons.lang.StringUtils;

import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.bean.User;

public class PathUtils {

    public static String USER_INFO = "userInfo-%d.html";
    public static String TOPIC = "topic-%d-%d-%d.html";
    public static String CATEGORY = "forum-%d.html";
    public static String FORUM = "forum-%d-%d-%d.html";
    public static String FORUM_NEW_TOPIC = "newTopic-%d-%d.html";
    
    public static String MESSAGE_INBOX = "pm/inbox.html";
    public static String MESSAGE_OUTBOX = "pm/outbox.html";
    
    public static String loginUrl(){
        return "login.html";
    }
    public static String userInfo(int userId){
        return String.format(USER_INFO, userId);
    }
    public static String url(User user){
        if(user==null)
            return StringUtils.EMPTY;
        
        return userInfo(user.getId());
    }
    
    public String url(Category category){
        if(category==null)
            return StringUtils.EMPTY;
        return String.format(CATEGORY, category.getId());
    }
    
    public String newTopic(Forum forum){
        if(forum==null)
            return StringUtils.EMPTY;
        
        return String.format(FORUM_NEW_TOPIC, forum.getId(), forum.getCategoryId()); 
    }
    public String url(Forum forum, int page){
        if(forum==null)
            return StringUtils.EMPTY;
        
        return String.format(FORUM, forum.getId(), page, forum.getCategoryId());
    }
    public String url(Forum forum){
        return url(forum, 1);
    }
    
    public String url(Topic topic){
        return url(topic, 1);
    }
    public String url(Topic topic, int page){
        if(topic == null){
            return StringUtils.EMPTY;
        }
        
        return String.format(TOPIC, new Object[]{topic.getId(), page, topic.getForumId()});
    }
    
    public String topicUrl(int topicId, int page, int forumId){
        return String.format(TOPIC, new Object[]{topicId, page,forumId});
    }
    
    public String msgInbox(){
        return MESSAGE_INBOX;
    }
    public String msgOutbox(){
        return MESSAGE_OUTBOX;
    }
    public static void main(String[] args) {
        System.out.println(userInfo(11));
    }
}

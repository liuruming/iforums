package com.googlecode.iforums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.Topic;
import com.googlecode.iforums.dao.TopicMapper;

@Service
public class TopicService extends BaseService<Topic> {

    @Autowired
    private TopicMapper topicMapper;
    
    @Override
    public void setMapperHandlewired() {
        super.setMapper(topicMapper);
    }

    public int setPostId(int topicId, int postId){
        return topicMapper.setPostId(topicId, postId);
    }
    
    public int setLastPostId(int topicId, int postId){
        return topicMapper.setLastPostId(topicId, postId);
    }
    
    public int incViews(int topicId){
        return incViews(topicId, 1);
    }
    
    public int incViews(int topicId, int inc){
        return topicMapper.incViews(topicId, inc);
    }
    
    public List<Topic> select(int forumId, int page,int size){
        page = page<=0?1:page;
        int offset = (page-1)*size;

        return topicMapper.select(forumId, offset, size);
    }
    
    public List<Topic> getRecentTopicList(int page, int size){
        page = page<=0?1:page;
        int offset = (page-1)*size;
        
        return topicMapper.getRecentTopicList(offset, size);
    }
    
    public List<Topic> getHotestTopicList(int page, int size){
        page = page<=0?1:page;
        int offset = (page-1)*size;
        
        return topicMapper.getHotestTopicList(offset, size);
    }
}

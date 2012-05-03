package com.googlecode.iforums.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.Post;
import com.googlecode.iforums.dao.PostMapper;

@Service
public class PostService extends BaseService<Post> {

    @Resource
    private PostMapper postMapper;
    
    @Override
    public void setMapperHandlewired() {
        super.setMapper(postMapper);
    }

    public List<Post> selectByTopicId(int topicId, int page,int size){
        page = page<=0?1:page;
        int offset = (page-1)*size+1;

        return postMapper.selectByTopicId(topicId, offset, size);
    }
    
    public int getTotal(){
        return postMapper.getTotal();
    }
}

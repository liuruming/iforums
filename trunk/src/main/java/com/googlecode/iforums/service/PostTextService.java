package com.googlecode.iforums.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.PostText;
import com.googlecode.iforums.dao.PostTextMapper;

@Service
public class PostTextService extends BaseService<PostText> {

    @Resource
    private PostTextMapper postTextMapper;
    
    @Override
    public void setMapperHandlewired() {
        super.setMapper(postTextMapper);
    }

}

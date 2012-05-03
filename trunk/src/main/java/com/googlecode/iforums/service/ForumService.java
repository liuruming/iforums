package com.googlecode.iforums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.bean.Forum;
import com.googlecode.iforums.dao.ForumMapper;

/**
 * 
 * @author zhurx
 * 
 */
@Service
public class ForumService extends BaseService<Forum> {

	@Autowired
	private ForumMapper forumMapper;

	@Override
	public void setMapperHandlewired() {
		super.setMapper(forumMapper);
	}
	
	public List<Forum> getForumListByCategoryId(int categoryId){
		return forumMapper.getForumListByCategoryId(categoryId);
	}
	
	public Category getSectionForumList(Category category){
		category.setForumList(forumMapper.getForumListByCategoryId(category.getId()));
		return category;
	}
	
	public List<Category> getSectionForumList(List<Category> categoryList){
		for(Category section:categoryList){
			section.setForumList(forumMapper.getForumListByCategoryId(section.getId()));
		}
		
		return categoryList;
	}
	
	public Forum getForumById(int categoryId, int forumId){
	    return forumMapper.getForumById(categoryId, forumId);
	}

    public int setLastTopicId(int forumId, int id) {
        return forumMapper.setLastTopicId(forumId, id);
    }
    
    public int decTopics(int forumId, int inc){
        return forumMapper.decTopics(forumId, inc);
    }
    
    public int decTopics(int forumId){
        return decTopics(forumId, 1);
    }
}

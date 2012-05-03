package com.googlecode.iforums.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.googlecode.iforums.bean.Forum;

public interface ForumMapper extends Mapper<Forum> {

	public List<Forum> getForumListByCategoryId(@Param("categoryId") int categoryId);

	public Forum getForumById(@Param("categoryId") int categoryId, @Param("forumId") int forumId);

    public int setLastTopicId(@Param("id") int forumId, @Param("topicId") int topicId);
    
    /**
     * 
     * @param forumId
     * @param inc
     * @return
     */
    public int decTopics(@Param("id") int forumId, @Param("inc") int inc);
}

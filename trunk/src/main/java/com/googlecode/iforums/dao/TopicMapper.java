package com.googlecode.iforums.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.googlecode.iforums.bean.Topic;

public interface TopicMapper extends Mapper<Topic> {

    /**
     * 
     * @param topicId
     * @param postId
     * @return
     */
    public int setPostId(@Param("id") int topicId,@Param("postId")  int postId);
    
    /**
     * 
     * @param topic
     * @return
     */
    public int setLastPostId(@Param("id") int topicId,@Param("postId")  int postId);

    public int incViews(@Param("id") int topicId, @Param("inc") int inc);

    public List<Topic> select(@Param("forumId") int forumId, @Param("offset") int offset, @Param("size") int size);

    public List<Topic> getRecentTopicList(@Param("offset") int offset, @Param("size") int size);
    
    public List<Topic> getHotestTopicList(@Param("offset") int offset, @Param("size") int size);
}

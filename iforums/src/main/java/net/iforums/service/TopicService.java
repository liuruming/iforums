package net.iforums.service;

import java.util.List;

import net.iforums.beans.Topic;

public interface TopicService {
	
	/**
	 * 
	 * @param forumId
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Topic> selectTopicByForumId(int forumId,int page,int size);
	
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	public Topic getTopicById(long topicId);
}

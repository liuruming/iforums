package net.iforums.service;

import java.util.List;

import net.iforums.beans.Topic;

public interface TopicService {
	public List<Topic> selectTopicByForumId(int forumId,int page,int size);
}

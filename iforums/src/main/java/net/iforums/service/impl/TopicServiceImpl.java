package net.iforums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.iforums.beans.Topic;
import net.iforums.dao.TopicDao;
import net.iforums.dao.UserDao;
import net.iforums.service.TopicService;
import net.iforums.utils.JsonUtil;

@Service
public class TopicServiceImpl implements TopicService {
	@Resource
	private TopicDao topicDao;
	@Resource
	private UserDao userDao;
	
	public List<Topic> selectTopicByForumId(int forumId,int page,int size){
		List<Topic> topicList = topicDao.selectByForumId(forumId, page, size);
		for(Topic topic:topicList){
			topic.setPostedBy(userDao.getObjectById(topic.getUserId()));
			System.out.println((topic));
			System.out.println(JsonUtil.toString(topic.getPostedBy()));
		}
		return topicList;
	}
}

package net.iforums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.iforums.beans.Topic;
import net.iforums.dao.TopicDao;
import net.iforums.dao.UserDao;
import net.iforums.service.TopicService;
import net.iforums.utils.JsonUtil;

import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends BaseServiveImpl implements TopicService {
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
	
	public Topic getTopicById(long topicId){
		Topic topic = topicDao.getObjectById(topicId);
		if(topic!=null){
			topic.setPostedBy(userDao.getObjectById(topic.getUserId()));
		}
		return topicDao.getObjectById(topicId);
	}
}

package net.iforums.dao.impl;

import java.util.List;

import net.iforums.beans.*;
import net.iforums.dao.*;

public class ForumDaoImpl extends BaseORMDao<Forum> implements ForumDAO{

	@Override
	public List checkUnreadTopics(int forumId, long lastVisit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decrementTotalTopics(int forumId, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int discoverForumId(String listEmail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ForumStats getBoardStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LastPostInfo getLastPostInfo(int forumId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxPostId(int forumId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getModeratorList(int forumId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalMessages() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalTopics(int forumId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementTotalTopics(int forumId, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserSubscribed(int forumId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveTopics(String[] topics, int fromForumId, int toForumId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List notifyUsers(Forum forum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSubscription(int forumId, int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSubscriptionByForum(int forumId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastPost(int forumId, int postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModerated(int categoryId, boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Forum setOrderDown(Forum forum, Forum related) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forum setOrderUp(Forum forum, Forum related) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void subscribeUser(int forumId, int userId) {
		// TODO Auto-generated method stub
		
	}

}

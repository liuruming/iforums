package net.iforums.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.iforums.JForumExecutionContext;
import net.iforums.beans.Forum;
import net.iforums.beans.ForumStats;
import net.iforums.beans.LastPostInfo;
import net.iforums.beans.ModeratorInfo;
import net.iforums.beans.Topic;
import net.iforums.beans.User;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.ForumDao;
import net.iforums.dao.GroupSecurityDao;
import net.iforums.dao.TopicDao;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;

import org.springframework.stereotype.Repository;

@Repository
public class ForumDaoImpl extends BaseORMDao<Forum> implements ForumDao{
	@Resource
	private GroupSecurityDao groupSecurityDao;
	@Resource
	private TopicDao topicDao;

	public ForumDaoImpl(){
		setNamespace("Forum");
	}
	public List<Forum> selectForumByCatId(long catId,boolean hasLastPost){
        Map<String,Object> params = new HashMap<String,Object>(21);
        params.put("catId",catId);
        List<Forum> forumList = queryForEntryList("selectForumByCatId",params);
        
        for(Forum forum:forumList){
        	
        }
        return forumList;
	}
	/**
	 * @see net.iforums.dao.ForumDao#setOrderUp(Forum, Forum)
	 */
	public Forum setOrderUp(Forum forum, Forum related)
	{
		return this.changeForumOrder(forum, related);
	}

	/**
	 * @see net.iforums.dao.ForumDao#setOrderDown(Forum, Forum)
	 */
	public Forum setOrderDown(Forum forum, Forum related)
	{
		return this.changeForumOrder(forum, related);
	}

	private Forum changeForumOrder(Forum forum, Forum related)
	{
		int tmpOrder = related.getOrder();
		related.setOrder(forum.getOrder());
		forum.setOrder(tmpOrder);

		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("ForumModel.setOrderById"));
			p.setInt(1, forum.getOrder());
			p.setInt(2, forum.getId());
			p.executeUpdate();
			p.close();
			p = null;

			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("ForumModel.setOrderById"));
			p.setInt(1, related.getOrder());
			p.setInt(2, related.getId());
			p.executeUpdate();

			return this.getObjectById(forum.getId());
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}



	/**
	 * @see net.iforums.dao.ForumDao#setLastPost(int, int)
	 */
	public void setLastPost(int forumId, int postId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.updateLastPost"));

			p.setInt(1, postId);
			p.setInt(2, forumId);

			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#setTotalTopics(int)
	 */
	public void incrementTotalTopics(int forumId, int count)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.incrementTotalTopics"));
			p.setInt(1, count);
			p.setInt(2, forumId);
			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#setTotalTopics(int)
	 */
	public void decrementTotalTopics(int forumId, int count)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.decrementTotalTopics"));
			p.setInt(1, count);
			p.setInt(2, forumId);
			p.executeUpdate();

			// If there are no more topics, then clean the
			// last post id information
			int totalTopics = this.getTotalTopics(forumId);
			if (totalTopics < 1) {
				this.setLastPost(forumId, 0);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	private LastPostInfo getLastPostInfo(int forumId, boolean tryFix)
	{
		LastPostInfo lpi = new LastPostInfo();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("ForumModel.lastPostInfo"));
			p.setInt(1, forumId);

			rs = p.executeQuery();

			if (rs.next()) {
				lpi.setUsername(rs.getString("username"));
				lpi.setUserId(rs.getInt("user_id"));

				SimpleDateFormat df = new SimpleDateFormat(SystemGlobals.getValue(ConfigKeys.DATE_TIME_FORMAT));
				lpi.setPostDate(df.format(rs.getTimestamp("post_time")));
				lpi.setPostId(rs.getInt("post_id"));
				lpi.setTopicId(rs.getInt("topic_id"));
				lpi.setPostTimeMillis(rs.getTimestamp("post_time").getTime());
				lpi.setTopicReplies(rs.getInt("topic_replies"));

				lpi.setHasInfo(true);

				// Check if the topic is consistent
				Topic t = topicDao.getObjectById(lpi.getTopicId());

				if (t.getId() == 0) {
					// Hm, that's not good. Try to fix it
					topicDao.fixFirstLastPostId(lpi.getTopicId());
				}

				tryFix = false;
			}
			else if (tryFix) {
				rs.close();
				rs = null;
				p.close();
				p = null;

				int postId = this.getMaxPostId(forumId);

				p = JForumExecutionContext.getConnection().prepareStatement(
						SystemGlobals.getSql("ForumModel.latestTopicIdForfix"));
				p.setInt(1, forumId);
				rs = p.executeQuery();

				if (rs.next()) {
					int topicId;
					topicId = rs.getInt("topic_id");

					rs.close();
					rs = null;
					p.close();
					p = null;

					// Topic
					p = JForumExecutionContext.getConnection().prepareStatement(
							SystemGlobals.getSql("ForumModel.fixLatestPostData"));
					p.setInt(1, postId);
					p.setInt(2, topicId);
					p.executeUpdate();
					p.close();
					p = null;

					// Forum
					p = JForumExecutionContext.getConnection().prepareStatement(
							SystemGlobals.getSql("ForumModel.fixForumLatestPostData"));
					p.setInt(1, postId);
					p.setInt(2, forumId);
					p.executeUpdate();
				}
			}

			return (tryFix ? this.getLastPostInfo(forumId, false) : lpi);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#getLastPostInfo(int)
	 */
	public LastPostInfo getLastPostInfo(int forumId)
	{
		return this.getLastPostInfo(forumId, true);
	}

	/**
	 * @see net.iforums.dao.ForumDao#getModeratorList(int)
	 */
	public List getModeratorList(int forumId)
	{
		List l = new ArrayList();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.getModeratorList"));
			p.setInt(1, forumId);

			rs = p.executeQuery();

			while (rs.next()) {
				ModeratorInfo mi = new ModeratorInfo();

				mi.setId(rs.getInt("id"));
				mi.setName(rs.getString("name"));

				l.add(mi);
			}

			return l;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#getTotalMessages()
	 */
	public int getTotalMessages()
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.totalMessages"));
			rs = p.executeQuery();

			if (rs.next()) {
				return rs.getInt("total_messages");
			}

			return 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#getTotalTopics(int)
	 */
	public int getTotalTopics(int forumId)
	{
		int total = 0;
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.getTotalTopics"));
			p.setInt(1, forumId);
			rs = p.executeQuery();

			if (rs.next()) {
				total = rs.getInt(1);
			}

			return total;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#getMaxPostId(int)
	 */
	public int getMaxPostId(int forumId)
	{
		int id = -1;

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("ForumModel.getMaxPostId"));
			p.setInt(1, forumId);

			rs = p.executeQuery();
			if (rs.next()) {
				id = rs.getInt("post_id");
			}

			return id;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#moveTopics(java.lang.String[], int, int)
	 */
	public void moveTopics(String[] topics, int fromForumId, int toForumId)
	{
		PreparedStatement p = null;
		PreparedStatement t = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("ForumModel.moveTopics"));
			t = JForumExecutionContext.getConnection().prepareStatement(
				SystemGlobals.getSql("PostModel.setForumByTopic"));

			p.setInt(1, toForumId);
			p.setInt(2, fromForumId);
			
			t.setInt(1, toForumId);

			Forum f = this.getObjectById(toForumId);

			for (int i = 0; i < topics.length; i++) {
				int topicId = Integer.parseInt(topics[i]);
				p.setInt(3, topicId);
				t.setInt(2, topicId);

				p.executeUpdate();
				t.executeUpdate();

				topicDao.setModerationStatusByTopic(topicId, f.isModerated());
			}

			this.decrementTotalTopics(fromForumId, topics.length);
			this.incrementTotalTopics(toForumId, topics.length);

			this.setLastPost(fromForumId, this.getMaxPostId(fromForumId));
			this.setLastPost(toForumId, this.getMaxPostId(toForumId));
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
			DbUtils.close(t);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#hasUnreadTopics(int, long)
	 */
	public List checkUnreadTopics(int forumId, long lastVisit)
	{
		List l = new ArrayList();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.checkUnreadTopics"));
			p.setInt(1, forumId);
			p.setTimestamp(2, new Timestamp(lastVisit));

			rs = p.executeQuery();
			while (rs.next()) {
				Topic t = new Topic();
				t.setId(rs.getInt("topic_id"));
				t.setTime(new Date(rs.getTimestamp(1).getTime()));

				l.add(t);
			}

			return l;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#setModerated(int, boolean)
	 */
	public void setModerated(long categoryId, boolean status)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("ForumModel.setModerated"));
			p.setInt(1, status ? 1 : 0);
			p.setLong(2, categoryId);
			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.ForumDao#getBoardStatus()
	 */
	public ForumStats getBoardStatus()
	{
		ForumStats fs = new ForumStats();
		fs.setPosts(this.getTotalMessages());

		Connection c = JForumExecutionContext.getConnection();

		// Total Users
		Statement s = null;
		ResultSet rs = null;

		try {
			s = c.createStatement();
			rs = s.executeQuery(SystemGlobals.getSql("UserModel.totalUsers"));
			rs.next();
			fs.setUsers(rs.getInt(1));
			rs.close();
			rs = null;
			s.close();
			s = null;

			// Total Topics
			s = c.createStatement();
			rs = s.executeQuery(SystemGlobals.getSql("TopicModel.totalTopics"));
			rs.next();
			fs.setTopics(rs.getInt(1));
			rs.close();
			rs = null;
			s.close();
			s = null;

			// Posts per day
			double postPerDay = 0;
			
			// Topics per day
			double topicPerDay = 0;
			
			// user per day
			double userPerDay = 0;

			s = c.createStatement();
			rs = s.executeQuery(SystemGlobals.getSql("ForumModel.statsFirstPostTime"));
			if (rs.next()) {

				Timestamp firstTime = rs.getTimestamp(1);
				if (rs.wasNull()) {
					firstTime = null;
				}
				rs.close();
				rs = null;
				s.close();
				s = null;

				Date today = new Date();

				postPerDay = firstTime != null ? fs.getPosts() / this.daysUntilToday(today, firstTime) : 0;

				if (fs.getPosts() > 0 && postPerDay < 1) {
					postPerDay = 1;
				}

				topicPerDay = firstTime != null ? fs.getTopics() / this.daysUntilToday(today, firstTime) : 0;

				// Users per day
				s = c.createStatement();
				rs = s.executeQuery(SystemGlobals.getSql("ForumModel.statsFirstRegisteredUserTime"));
				if (rs.next()) {
					firstTime = rs.getTimestamp(1);
					if (rs.wasNull()) {
						firstTime = null;
					}
				}
				rs.close();
				rs = null;
				s.close();
				s = null;

				userPerDay = firstTime != null ? fs.getUsers() / this.daysUntilToday(today, firstTime) : 0;
			}

			fs.setPostsPerDay(postPerDay);
			fs.setTopicsPerDay(topicPerDay);
			fs.setUsersPerDay(userPerDay);

			return fs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, s);
		}
	}

	private int daysUntilToday(Date today, Date from)
	{
		int days = (int) ((today.getTime() - from.getTime()) / (24 * 60 * 60 * 1000));
		return days == 0 ? 1 : days;
	}

	/**
	 * This code is writen by looking at GenericTopicDao.java
	 * 
	 * @see
	 */
	public List notifyUsers(Forum forum)
	{
		int posterId = 0;//SessionFacade.getUserSession().getUserId();
		int anonUser = SystemGlobals.getIntValue(ConfigKeys.ANONYMOUS_USER_ID);

		PreparedStatement p = null;
		ResultSet rs = null;
		
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.notifyUsers"));

			p.setInt(1, forum.getId());
			p.setInt(2, posterId); // don't notify the poster
			p.setInt(3, anonUser); // don't notify the anonimous user

			rs = p.executeQuery();
			List users = new ArrayList();
			
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("user_email"));
				user.setUsername(rs.getString("username"));
				user.setLang(rs.getString("user_lang"));
				user.setNotifyAlways(rs.getInt("user_notify_always") == 1);
				user.setNotifyText(rs.getInt("user_notify_text") == 1);

				users.add(user);
			}
			
			return users;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}

	}

	public void subscribeUser(int forumId, int userId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.subscribeUser"));

			p.setInt(1, forumId);
			p.setInt(2, userId);

			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}

	}

	public boolean isUserSubscribed(int forumId, int userId)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.isUserSubscribed"));

			stmt.setInt(1, forumId);
			stmt.setInt(2, userId);

			rs = stmt.executeQuery();

			return rs.next();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, stmt);
		}
	}

	public void removeSubscription(int forumId, int userId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.removeSubscription"));
			p.setInt(1, forumId);
			p.setInt(2, userId);

			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}

	}

	/**
	 * Remove all subscriptions on a forum, such as when a forum is locked. It is not used now.
	 * 
	 * @param forumId int
	 */
	public void removeSubscriptionByForum(int forumId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ForumModel.removeSubscriptionByForum"));
			p.setInt(1, forumId);

			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}

	}
	
	/**
	 * @see net.iforums.dao.ForumDao#discoverForumId(java.lang.String)
	 */
	public int discoverForumId(String listEmail)
	{
		int forumId = 0;
		
		PreparedStatement p = null;
		ResultSet rs = null;
		
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
				SystemGlobals.getSql("ForumModel.discoverForumId"));
			p.setString(1, listEmail);
			rs = p.executeQuery();
			
			if (rs.next()) {
				forumId = rs.getInt(1);
			}
		}
		catch (SQLException e) {
			
		}
		finally {
			DbUtils.close(rs, p);
		}
		
		return forumId;
	}
}

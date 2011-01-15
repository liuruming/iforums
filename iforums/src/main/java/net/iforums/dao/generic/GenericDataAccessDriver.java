/*
 * Copyright (c) JForum Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following  disclaimer.
 * 2)  Redistributions in binary form must reproduce the 
 * above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 * 3) Neither the name of "Rafael Steil" nor 
 * the names of its contributors may be used to endorse 
 * or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT 
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 * 
 * This file creation date: Mar 3, 2003 / 2:19:47 PM
 * The JForum Project
 * http://www.jforum.net
 */
package net.jforum.dao.generic;

import net.iforums.dao.ApiDao;
import net.iforums.dao.AttachmentDao;
import net.iforums.dao.BanlistDao;
import net.iforums.dao.BannerDao;
import net.iforums.dao.BookmarkDao;
import net.iforums.dao.CategoryDao;
import net.iforums.dao.ConfigDao;
import net.iforums.dao.DataAccessDriver;
import net.iforums.dao.ForumDao;
import net.iforums.dao.GroupDao;
import net.iforums.dao.GroupSecurityDao;
import net.iforums.dao.KarmaDao;
import net.iforums.dao.LuceneDao;
import net.iforums.dao.MailIntegrationDao;
import net.iforums.dao.ModerationDao;
import net.iforums.dao.ModerationLogDao;
import net.iforums.dao.PollDao;
import net.iforums.dao.PostDao;
import net.iforums.dao.PrivateMessageDao;
import net.iforums.dao.RankingDao;
import net.iforums.dao.SmilieDao;
import net.iforums.dao.SummaryDao;
import net.iforums.dao.TopicDao;
import net.iforums.dao.TreeGroupDao;
import net.iforums.dao.UserDao;
import net.iforums.dao.UserSessionDao;

/**
 * @author Rafael Steil
 * @version $Id: GenericDataAccessDriver.java,v 1.16 2007/07/28 20:07:18 rafaelsteil Exp $
 */
public class GenericDataAccessDriver extends DataAccessDriver 
{
	private static GroupDao groupDao;
	private static PostDao postDao;
	private static PollDao pollDao;
	private static RankingDao rankingDao;
	private static TopicDao topicDao;
	private static UserDao userDao;
	private static TreeGroupDao treeGroupDao;
	private static SmilieDao smilieDao;
	private static GroupSecurityDao groupSecurityDao;
	private static PrivateMessageDao privateMessageDao;
	private static UserSessionDao userSessionDao;
	private static KarmaDao karmaDao ;
	private static BookmarkDao bookmarkDao;
	private static AttachmentDao attachmentDao;
	private static ModerationDao moderationDao;
	private static ForumDao forumDao;
	private static CategoryDao categoryDao ;
	private static ConfigDao configDao ;
	private static BannerDao bannerDao;
    private static SummaryDao summaryDao;
    private static MailIntegrationDao mailIntegrationDao;
    private static ApiDao apiDao;
    private static BanlistDao banlistDao;
    private static ModerationLogDao moderationLogDao;
    private static LuceneDao luceneDao;
    
	/**
	 * @see net.jforum.dao.DataAccessDriver#getForumModel()
	 */
	public ForumDao newForumDao() 
	{
		return forumDao;	
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getGroupModel()
	 */
	public GroupDao newGroupDao() 
	{
		return groupDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getPostModel()
	 */
	public PostDao newPostDao() 
	{
		return postDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getPollModel()
	 */
	public PollDao newPollDao() 
	{
		return pollDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getRankingModel()
	 */
	public RankingDao newRankingDao() 
	{	
		return rankingDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getTopicModel()
	 */
	public TopicDao newTopicDao() 
	{
		return topicDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#getUserModel()
	 */
	public UserDao newUserDao() 
	{
		return userDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#newCategoryDao()
	 */
	public CategoryDao newCategoryDao() 
	{
		return categoryDao;
	}

	/**
	 * @see net.jforum.dao.DataAccessDriver#newTreeGroupDao()
	 */
	public TreeGroupDao newTreeGroupDao() 
	{
		return treeGroupDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newSmilieDao()
	 */
	public SmilieDao newSmilieDao() 
	{
		return smilieDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newGroupSecurityDao()
	 */
	public GroupSecurityDao newGroupSecurityDao() 
	{
		return groupSecurityDao;
	}

	/** 
	 * @see net.jforum.dao.DataAccessDriver#newPrivateMessageDao()
	 */
	public PrivateMessageDao newPrivateMessageDao() 
	{
		return privateMessageDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newUserSessionDao()
	 */
	public UserSessionDao newUserSessionDao()
	{
		return userSessionDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newConfigDao()
	 */
	public ConfigDao newConfigDao()
	{
		return configDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newKarmaDao()
	 */
	public KarmaDao newKarmaDao()
	{
		return karmaDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newBookmarkDao()
	 */
	public BookmarkDao newBookmarkDao()
	{
		return bookmarkDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newAttachmentDao()
	 */
	public AttachmentDao newAttachmentDao()
	{
		return attachmentDao;
	}
	
	/** 
	 * @see net.jforum.dao.DataAccessDriver#newModerationDao()
	 */
	public ModerationDao newModerationDao()
	{
		return moderationDao;
	}

    /**
     * @see net.jforum.dao.DataAccessDriver#newBannerDao()
     */
	public BannerDao newBannerDao()
	{
		return bannerDao;
	}
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newSummaryDao()
     */
    public SummaryDao newSummaryDao()
    {
        return summaryDao;
    }
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newMailIntegrationDao()
     */
    public MailIntegrationDao newMailIntegrationDao()
    {
    	return mailIntegrationDao;
    }
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newApiDao()
     */
    public ApiDao newApiDao()
    {
    	return apiDao;
    }
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newBanlistDao()
     */
    public BanlistDao newBanlistDao()
    {
    	return banlistDao;
    }
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newModerationLogDao()
     */
    public ModerationLogDao newModerationLogDao()
    {
    	return moderationLogDao;
    }
    
    /**
     * @see net.jforum.dao.DataAccessDriver#newLuceneDao()
     */
    public LuceneDao newLuceneDao()
    {
    	return luceneDao;
    }
}

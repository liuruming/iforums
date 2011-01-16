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
 * This file creation date: 24/05/2004 / 12:01 PM
 * The JForum Project
 * http://www.jforum.net
 */
package net.iforums.dao.oracle;

import javax.annotation.Resource;

import net.iforums.dao.LuceneDao;
import net.iforums.dao.ModerationDao;
import net.iforums.dao.ModerationLogDao;
import net.iforums.dao.PostDao;
import net.iforums.dao.PrivateMessageDao;
import net.iforums.dao.TopicDao;
import net.iforums.dao.UserDao;
import net.iforums.dao.generic.GenericDataAccessDriver;

/**
 * @author Dmitriy Kiriy
 * @version $Id: OracleDataAccessDriver.java,v 1.11 2007/09/10 22:34:21 rafaelsteil Exp $
 */
public class OracleDataAccessDriver extends GenericDataAccessDriver
{
	@Resource
	private static PostDao postDao ;
	@Resource(name="")
	private static TopicDao topicDao;
	@Resource(name="")
	private static UserDao userDao;
	@Resource(name="")
	private static PrivateMessageDao pmDao ;
	@Resource(name="")
	private static ModerationDao moderationDao ;
	@Resource(name="")
	private static ModerationLogDao moderationLogDao ;
	@Resource(name="oracleLuceneDao")
	private static LuceneDao luceneDao;
	
	/**
	 * @see GenericDataAccessDriver#newModerationLogDao()
	 */
	public ModerationLogDao newModerationLogDao() 
	{
		return moderationLogDao;
	}
	
	/**
	 * @see net.iforums.dao.DataAccessDriver#newModerationDao()
	 */
	public ModerationDao newModerationDao()
	{
		return moderationDao;
	}
	
	/**
	 * @see net.iforums.dao.DataAccessDriver#newPostDao()
	 */
	public PostDao newPostDao()
	{
		return postDao;
	}

	/** 
	 * @see net.iforums.dao.DataAccessDriver#newTopicDao()
	 */
	public TopicDao newTopicDao()
	{
		return topicDao;
	}
	
	/** 
	 * @see net.iforums.dao.DataAccessDriver#newUserDao()
	 */
	public UserDao newUserDao()
	{
		return userDao;
	}
	
	/**
	 * @see net.iforums.dao.DataAccessDriver#newPrivateMessageDao()
	 */
	public PrivateMessageDao newPrivateMessageDao()
	{
		return pmDao;
	}
	
	/**
	 * @see GenericDataAccessDriver#newLuceneDao()
	 */
	public LuceneDao newLuceneDao() 
	{
		return luceneDao;
	}
}

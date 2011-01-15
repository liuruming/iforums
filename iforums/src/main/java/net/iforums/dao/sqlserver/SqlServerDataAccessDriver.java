/*
 * Copyright (c) JForum Team
 * All rights reserved.

 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:

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
 * Created on 29/05/2004 00:12:37
 * The JForum Project
 * http://www.jforum.net
 */
package net.jforum.dao.sqlserver;

import net.iforums.dao.DataAccessDriver;
import net.iforums.dao.KarmaDao;
import net.iforums.dao.PostDao;
import net.iforums.dao.PrivateMessageDao;
import net.iforums.dao.TopicDao;
import net.iforums.dao.UserDao;

/**
 * @author Andre de Andrade da Silva - andre.de.andrade@gmail.com
 * @author Dirk Rasmussen - d.rasmussen@bevis.de (2007/02/19, modifs for MS SqlServer 2005)
 * @see WEB-INF\config\database\sqlserver\sqlserver.sql (2007/02/19, MS SqlServer 2005 specific version!)
 * @version $Id: SqlServerDataAccessDriver.java,v 1.9 2007/07/28 20:07:18 rafaelsteil Exp $
 */
public class SqlServerDataAccessDriver extends net.jforum.dao.generic.GenericDataAccessDriver
{
	private static SqlServerPostDao postDao = new SqlServerPostDao();
	private static SqlServerTopicDao topicDao = new SqlServerTopicDao();
	private static SqlServerUserDao userDao = new SqlServerUserDao();
	private static SqlServerPrivateMessageDao pmDao = new SqlServerPrivateMessageDao();
	private static SqlServerKarmaDao karmaDao = new SqlServerKarmaDao();

	/** 
	 * @see net.jforum.dao.DataAccessDriver#newPostDao()
	 */
	public PostDao newPostDao()
	{
		return postDao;
	}

	/** 
	 * @see DataAccessDriver#newTopicDao()
	 */
	public TopicDao newTopicDao()
	{
		return topicDao;
	}
	
	/** 
	 * @see DataAccessDriver#newUserDao()
	 */
	public UserDao newUserDao()
	{
		return userDao;
	}
	
	/** 
	 * @see DataAccessDriver#newPrivateMessageDao()
	 */
	public PrivateMessageDao newPrivateMessageDao()
	{
		return pmDao;
	}
	
	/** 
	 * @see DataAccessDriver#newKarmaDao()
	 */
	public KarmaDao newKarmaDao()
	{
		return karmaDao;
	}
}

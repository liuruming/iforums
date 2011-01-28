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
 * Created on 24/05/2004 12:25:35
 * The JForum Project
 * http://www.jforum.net
 */
package net.iforums.dao.sqlserver;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import net.iforums.dao.impl.TopicDaoImpl;
import net.iforums.repository.ForumRepository;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.SystemGlobals;

import org.apache.log4j.Logger;

/**
 * @author Andre de Andrade da Silva - andre.de.andrade@gmail.com
 * @author Dirk Rasmussen - d.rasmussen@bevis.de (2007/02/19, modifs for MS SqlServer 2005)
 * @see WEB-INF\config\database\sqlserver\sqlserver.sql (2007/02/19, MS SqlServer 2005 specific version!)
 * @version $Id: SqlServerTopicDao.java,v 1.14 2007/08/01 22:09:02 rafaelsteil Exp $
 */
public class SqlServerTopicDaoImpl extends TopicDaoImpl
{
	private static final Logger logger = Logger.getLogger(SqlServerTopicDaoImpl.class);

	/**
	 * @see net.iforums.dao.TopicDao#selectAllByForumByLimit(int, int, int)
	 */
	public List selectAllByForumByLimit(int forumId, int startFrom, int count)
	{
		PreparedStatement p = null;
		String sqlStmnt = SystemGlobals.getSql("TopicModel.selectAllByForumByLimit");
		if (logger.isDebugEnabled())
		{
			logger.debug("selectAllByForumByLimit("+forumId+","+startFrom+","+count+")..., sqlStmnt="+sqlStmnt);
		}

		try {

			p.setInt(1, forumId);
			p.setInt(2, forumId);
			p.setInt(3, startFrom);
			p.setInt(4, startFrom+count);

			List list = super.fillTopicsData(p);
			p = null;
			return list;
		}
		catch (SQLException e) {
			logger.error(sqlStmnt, e);
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.TopicDao#selectRecentTopics(int)
	 */
	public List selectRecentTopics(int limit)
	{
		PreparedStatement p = null;
		String sqlStmnt = SystemGlobals.getSql("TopicModel.selectRecentTopicsByLimit");
		if (logger.isDebugEnabled())
		{
			logger.debug("selectRecentTopics("+limit+")..., sqlStmnt="+sqlStmnt);
		}

		try {

			p.setInt(1, limit );

			List list = this.fillTopicsData(p);
			p = null;
			return list;
		}
		catch (SQLException e) {
			logger.error(sqlStmnt, e);
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.TopicDao#selectByUserByLimit(int, int, int)
	 */
	public List selectByUserByLimit(int userId, int startFrom, int count)
	{
		PreparedStatement p = null;
		String sqlStmnt = SystemGlobals.getSql("TopicModel.selectByUserByLimit");
		sqlStmnt = sqlStmnt.replaceAll(":fids:", ForumRepository.getListAllowedForums());
		if (logger.isDebugEnabled())
		{
			logger.debug("selectByUserByLimit("+userId+","+startFrom+","+count+")..., sqlStmnt="+sqlStmnt);
		}

		try {

			p.setInt(1, userId);
			p.setInt(2, startFrom);
			p.setInt(3, count);

			List list = this.fillTopicsData(p);
			p = null;
			return list;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

}

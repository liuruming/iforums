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
 * This file creation date: Mar 23, 2003 / 7:52:13 PM
 * The JForum Project
 * http://www.jforum.net
 */
package net.iforums.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.iforums.JForumExecutionContext;
import net.iforums.beans.Ranking;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.RankingDao;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.SystemGlobals;

/**
 * @author Rafael Steil
 * @version $Id: GenericRankingDao.java,v 1.9 2006/12/02 03:19:44 rafaelsteil Exp $
 */
@Repository
public class RankingDaoImpl extends BaseORMDao<Ranking> implements RankingDao
{
	/**
	 * @see net.iforums.dao.RankingDao#selectById(int)
	 */
	public Ranking selectById(int rankingId)
	{
		Ranking ranking = new Ranking();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection()
					.prepareStatement(SystemGlobals.getSql("RankingModel.selectById"));
			p.setInt(1, rankingId);

			rs = p.executeQuery();
			
			if (rs.next()) {
				ranking = this.buildRanking(rs);
			}

			return ranking;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.RankingDao#selectAll()
	 */
	public List selectAll()
	{
		List l = new ArrayList();
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("RankingModel.selectAll"));
			rs = p.executeQuery();

			while (rs.next()) {
				Ranking ranking = buildRanking(rs);
				l.add(ranking);
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
	 * @see net.iforums.dao.RankingDao#delete(int)
	 */
	public void delete(int rankingId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("RankingModel.delete"));
			p.setInt(1, rankingId);

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
	 * @see net.iforums.dao.RankingDao#update(net.jforum.entities.Ranking)
	 */
	public void update(Ranking ranking)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("RankingModel.update"));

			p.setString(1, ranking.getTitle());
			p.setString(2, ranking.getImage());
			p.setInt(3, ranking.isSpecial() ? 1 : 0);
			p.setInt(4, ranking.getMin());
			p.setInt(5, ranking.getId());

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
	 * @see net.iforums.dao.RankingDao#addNew(net.jforum.entities.Ranking)
	 */
	public void addNew(Ranking ranking)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("RankingModel.addNew"));

			p.setString(1, ranking.getTitle());
			p.setInt(2, ranking.getMin());
			p.setInt(3, ranking.isSpecial() ? 1 : 0);

			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}
	
	public List selectSpecials()
	{
		List l = new ArrayList();
		
		PreparedStatement p = null;
		ResultSet rs = null;
		
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("RankingModel.selectSpecials"));
			rs = p.executeQuery();

			while (rs.next()) {
				Ranking ranking = this.buildRanking(rs);
				l.add(ranking);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
		
		return l;
	}

	private Ranking buildRanking(ResultSet rs) throws SQLException
	{
		Ranking ranking = new Ranking();

		ranking.setId(rs.getInt("rank_id"));
		ranking.setTitle(rs.getString("rank_title"));
		ranking.setImage(rs.getString("rank_image"));
		ranking.setMin(rs.getInt("rank_min"));
		ranking.setSpecial(rs.getInt("rank_special") == 1);
		
		return ranking;
	}
}

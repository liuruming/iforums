package net.iforums.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.iforums.JForumExecutionContext;
import net.iforums.beans.Config;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.ConfigDao;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.SystemGlobals;
@Repository
public class ConfigDaoImpl extends BaseORMDao<Config> implements ConfigDao{
//	/**
//	 * @see net.iforums.dao.ConfigDao#insert(net.jforum.entities.Config)
//	 */
//	public void insert(Config config)
//	{
//		PreparedStatement p = null;
//		try {
//			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("ConfigModel.insert"));
//			p.setString(1, config.getName());
//			p.setString(2, config.getValue());
//			p.executeUpdate();
//		}
//		catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		finally {
//			DbUtils.close(p);
//		}
//	}

	/**
	 * @see net.iforums.dao.ConfigDao#update(net.jforum.entities.Config)
	 */
	public void update(Config config)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("ConfigModel.update"));
			p.setString(1, config.getValue());
			p.setString(2, config.getName());
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
	 * @see net.iforums.dao.ConfigDao#delete(net.jforum.entities.Config)
	 */
	public void delete(Config config)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("ConfigModel.delete"));
			p.setInt(1, config.getId());
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
	 * @see net.iforums.dao.ConfigDao#selectAll()
	 */
	public List selectAll()
	{
		List l = new ArrayList();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("ConfigModel.selectAll"));
			rs = p.executeQuery();
			while (rs.next()) {
				l.add(this.makeConfig(rs));
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
	 * @see net.iforums.dao.ConfigDao#selectByName(java.lang.String)
	 */
	public Config selectByName(String name)
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("ConfigModel.selectByName"));
			p.setString(1, name);
			rs = p.executeQuery();
			Config c = null;

			if (rs.next()) {
				c = this.makeConfig(rs);
			}

			return c;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	protected Config makeConfig(ResultSet rs) throws SQLException
	{
		Config c = new Config();
		c.setId(rs.getInt("config_id"));
		c.setName(rs.getString("config_name"));
		c.setValue(rs.getString("config_value"));

		return c;
	}
}

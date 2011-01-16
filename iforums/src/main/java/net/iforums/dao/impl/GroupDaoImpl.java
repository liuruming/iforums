package net.iforums.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.iforums.JForumExecutionContext;
import net.iforums.beans.Group;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.GroupDao;
import net.iforums.dao.GroupSecurityDao;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.SystemGlobals;

import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl extends BaseORMDao<Group> implements GroupDao{
	@Resource
	private GroupSecurityDao groupSecurityDao;
	/**
	 * @see net.iforums.dao.GroupDao#selectById(int)
	 */
	public Group selectById(int groupId)
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.selectById"));
			p.setInt(1, groupId);

			rs = p.executeQuery();

			Group g = new Group();

			if (rs.next()) {
				g = this.getGroup(rs);
			}

			return g;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.GroupDao#canDelete(int)
	 */
	public boolean canDelete(int groupId)
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.canDelete"));
			p.setInt(1, groupId);

			boolean status = false;

			rs = p.executeQuery();
			if (!rs.next() || rs.getInt("total") < 1) {
				status = true;
			}

			return status;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	/**
	 * @see net.iforums.dao.GroupDao#delete(int)
	 */
	public void delete(int groupId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.delete"));
			p.setInt(1, groupId);

			p.executeUpdate();
			
			groupSecurityDao.deleteAllRoles(groupId);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	/**
	 * @see net.iforums.dao.GroupDao#update(net.jforum.entities.Group)
	 */
	public void update(Group group)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.update"));
			p.setString(1, group.getName());
			p.setInt(2, group.getParentId());
			p.setString(3, group.getDescription());
			p.setInt(4, group.getId());

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
	 * @see net.iforums.dao.GroupDao#addNew(net.jforum.entities.Group)
	 */
	public void addNew(Group group)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.addNew"));
			p.setString(1, group.getName());
			p.setString(2, group.getDescription());
			p.setInt(3, group.getParentId());

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
	 * @see net.iforums.dao.GroupDao#selectUsersIds(int)
	 */
	public List selectUsersIds(int groupId)
	{
		ArrayList l = new ArrayList();

		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("GroupModel.selectUsersIds"));
			p.setInt(1, groupId);

			rs = p.executeQuery();
			while (rs.next()) {
				l.add(new Integer(rs.getInt("user_id")));
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

	protected List fillGroups(ResultSet rs) throws SQLException
	{
		List l = new ArrayList();

		while (rs.next()) {
			l.add(this.getGroup(rs));
		}

		return l;
	}

	protected Group getGroup(ResultSet rs) throws SQLException
	{
		Group g = new Group();

		g.setId(rs.getInt("group_id"));
		g.setDescription(rs.getString("group_description"));
		g.setName(rs.getString("group_name"));
		g.setParentId(rs.getInt("parent_id"));

		return g;
	}

	/**
	 * @see net.iforums.dao.GroupDao#selectAll()
	 */
	public List selectAll()
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("GroupModel.selectAll"));
			rs = p.executeQuery();

			return this.fillGroups(rs);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}
}

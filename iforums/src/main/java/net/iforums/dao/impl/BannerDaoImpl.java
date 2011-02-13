package net.iforums.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.iforums.JForumExecutionContext;
import net.iforums.beans.Banner;
import net.iforums.dao.BannerDao;
import net.iforums.dao.BaseORMDao;
import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.SystemGlobals;

import org.springframework.stereotype.Repository;
@Repository
public class BannerDaoImpl extends BaseORMDao<Banner> implements BannerDao{

	public Banner selectById(int bannerId)
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		Banner b = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("BannerDao.selectById"));
			p.setInt(1, bannerId);

			rs = p.executeQuery();

			b = new Banner();
			if (rs.next()) {
				b = this.getBanner(rs);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}

		return b;
	}

	public List selectAll()
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("BannerDao.selectAll"));
			List l = new ArrayList();

			rs = p.executeQuery();
			while (rs.next()) {
				l.add(this.getBanner(rs));
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

	protected Banner getBanner(ResultSet rs) throws SQLException
	{
		Banner b = new Banner();

		b.setId(rs.getInt("banner_id"));
		b.setName(rs.getString("banner_name"));
		b.setPlacement(rs.getInt("banner_placement"));
		b.setDescription(rs.getString("banner_description"));
		b.setClicks(rs.getInt("banner_clicks"));
		b.setViews(rs.getInt("banner_views"));
		b.setUrl(rs.getString("banner_url"));
		b.setWeight(rs.getInt("banner_weight"));
		b.setActive(rs.getInt("banner_active") == 1);
		b.setComment(rs.getString("banner_comment"));
		b.setType(rs.getInt("banner_type"));
		b.setWidth(rs.getInt("banner_width"));
		b.setHeight(rs.getInt("banner_height"));

		return b;
	}

	public boolean canDelete(int bannerId)
	{
		boolean result = true;
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("BannerDao.canDelete"));
			p.setInt(1, bannerId);

			rs = p.executeQuery();
			if (!rs.next() || rs.getInt("total") < 1) {
				result = false;
			}

			return result;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(rs, p);
		}
	}

	public void delete(int bannerId)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("BannerDao.delete"));
			p.setInt(1, bannerId);
			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	public void update(Banner banner)
	{
		PreparedStatement p = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(SystemGlobals.getSql("BannerDao.update"));
			setBannerParam(p, banner);
			p.setInt(13, banner.getId());
			p.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.close(p);
		}
	}

	protected void setBannerParam(PreparedStatement p, Banner b) throws SQLException
	{
		p.setString(1, b.getName());
		p.setInt(2, b.getPlacement());
		p.setString(3, b.getDescription());
		p.setInt(4, b.getClicks());
		p.setInt(5, b.getViews());
		p.setString(6, b.getUrl());
		p.setInt(7, b.getWeight());
		p.setInt(8, b.isActive() ? 1 : 0);
		p.setString(9, b.getComment());
		p.setInt(10, b.getType());
		p.setInt(11, b.getWidth());
		p.setInt(12, b.getHeight());
	}

	public List selectActiveBannerByPlacement(int placement)
	{
		PreparedStatement p = null;
		ResultSet rs = null;
		try {
			p = JForumExecutionContext.getConnection().prepareStatement(
					SystemGlobals.getSql("BannerDao.selectActiveBannerByPlacement"));
			p.setInt(1, placement);

			List l = new ArrayList();

			rs = p.executeQuery();
			while (rs.next()) {
				l.add(this.getBanner(rs));
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
}

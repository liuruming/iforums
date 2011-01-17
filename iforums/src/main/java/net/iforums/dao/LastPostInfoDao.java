package net.iforums.dao;

import net.iforums.beans.LastPostInfo;


public interface LastPostInfoDao {
	/**
	 * 
	 * @param forumId
	 * @return
	 */
	public LastPostInfo getLastPostInfo(int forumId);
}

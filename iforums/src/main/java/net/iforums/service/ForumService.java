package net.iforums.service;

import java.util.List;

import net.iforums.beans.Category;

public interface ForumService {
	
	/**
	 * 获取所有的论坛分区
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Category> selectCategoryList(int page,int size,boolean hasForum);
}

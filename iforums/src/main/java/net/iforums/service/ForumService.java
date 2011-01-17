package net.iforums.service;

import java.util.List;

import net.iforums.beans.Category;
import net.iforums.beans.Forum;

public interface ForumService {
	
	/**
	 * 获取所有的论坛分区
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Category> selectCategoryList(int page,int size,boolean hasForum);
	
	/**
	 * 获取单个论坛分区
	 * @param id
	 * @return
	 */
	public Category getCategoryById(long id,boolean hasForum);
	
	/**
	 * 获取论坛分区
	 * @return
	 */
	public Forum getForumById(int id);
}

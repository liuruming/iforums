package net.iforums.dao.impl;

import net.iforums.beans.Category;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.CategoryDao;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends BaseORMDao<Category> implements CategoryDao{
	
	public CategoryDaoImpl(){
		setNamespace("Category");
	}

	/**
	 * @see net.iforums.dao.CategoryDao#setOrderUp(Category, Category)
	 */
	public void setOrderUp(Category category, Category relatedCategory)
	{
		this.setOrder(category, relatedCategory);
	}

	/**
	 * @see net.iforums.dao.CategoryDao#setOrderDown(Category, Category)
	 */
	public void setOrderDown(Category category, Category relatedCategory)
	{
		this.setOrder(category, relatedCategory);
	}

	/**
	 * @param category Category
	 * @param otherCategory Category
	 */
	private void setOrder(Category category, Category otherCategory)
	{

	}
}

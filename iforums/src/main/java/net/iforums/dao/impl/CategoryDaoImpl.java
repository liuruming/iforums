package net.iforums.dao.impl;

import net.iforums.beans.*;
import net.iforums.dao.*;

public class CategoryDaoImpl extends BaseORMDao<Category> implements CategoryDAO{

	@Override
	public boolean canDelete(int categoryId) {
		return false;
	}

	@Override
	public void setOrderDown(Category category, Category otherCategory) {
		
	}

	@Override
	public void setOrderUp(Category category, Category otherCategory) {
		
	}

}

package net.iforums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.iforums.beans.Category;
import net.iforums.dao.CategoryDao;
import net.iforums.dao.ForumDao;
import net.iforums.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService {

	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ForumDao forumDao;
	
	@Override
	public List<Category> selectCategoryList(int page, int size,boolean hasForum) {
		List<Category> categoryList = categoryDao.select(page, size);
		
		if(hasForum){
			for(Category category:categoryList){
			}
		}
		return categoryList;
	}

}

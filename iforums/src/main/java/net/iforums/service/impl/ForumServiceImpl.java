package net.iforums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.iforums.beans.Category;
import net.iforums.beans.Forum;
import net.iforums.dao.CategoryDao;
import net.iforums.dao.ForumDao;
import net.iforums.service.ForumService;

import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl extends BaseServiveImpl implements ForumService {

	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ForumDao forumDao;
	@Override
	public List<Category> selectCategoryList(int page, int size,boolean hasForum) {
		List<Category> categoryList = categoryDao.select(page, size);
		
		if(hasForum){
			for(Category category:categoryList){
				category.setForumList(forumDao.selectForumByCatId(category.getId(),true));
			}
		}
		return categoryList;
	}

	public Category getCategoryById(long id,boolean hasForum){
		Category category = categoryDao.getObjectById(id);
		
		if(category!=null&&hasForum){
			category.setForumList(forumDao.selectForumByCatId(category.getId(),true));
		}
		
		return category;
	}

	@Override
	public Forum getForumById(int id) {
		return forumDao.getObjectById(id);
	}
}

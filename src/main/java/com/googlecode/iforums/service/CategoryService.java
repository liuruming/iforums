package com.googlecode.iforums.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.dao.CategoryMapper;

/**
 * 
 * @author zhurx
 * 
 */
@Service
public class CategoryService extends BaseService<Category> {
	
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public void setMapperHandlewired() {
		super.setMapper(categoryMapper);
	}
}

package net.iforums.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.beans.Category;
import net.iforums.service.ForumService;
import net.iforums.utils.JsonUtil;
import net.iforums.utils.ParamUtil;
import net.iforums.web.ParamConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="index.do")
public class IndexController extends AbstractController{
	
	@Resource
	private ForumService forumService;
	
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,Map<String,Object> model)
			throws Exception {
		long catId = ServletRequestUtils.getIntParameter(request, ParamConstants.ID, -1);//paramUtil.getLong(, -1l);
		
		List<Category> categoryList = null;
		if(catId!=-1){
			Category category = forumService.getCategoryById(catId, true);
			if(category!=null){
				categoryList = new ArrayList<Category>();
				categoryList.add(category);
			}
		}
		categoryList = categoryList==null?forumService.selectCategoryList(0, Integer.MAX_VALUE,true):categoryList;
		model.put("categoryList", categoryList);
		
		System.out.println(JsonUtil.toString(model));
		return new ModelAndView(getViewName(),model);
	}
}

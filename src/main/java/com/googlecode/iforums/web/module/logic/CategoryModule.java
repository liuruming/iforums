package com.googlecode.iforums.web.module.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.iforums.bean.Category;
import com.googlecode.iforums.web.module.AbstractLogicModule;
import com.googlecode.iforums.web.module.WebModuleContext;

@Component
public class CategoryModule extends AbstractLogicModule {

    @Override
    public ModelAndView handler(WebModuleContext context, ModelMap model) {
        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();
        ModelAndView modelAndView = context.getModelAndView();
        List<Category> categoryList = null;
        int categoryId = ServletRequestUtils.getIntParameter(request, "categoryId", Integer.MIN_VALUE);

        List<Category> allCategoryList = categoryService.selectAll();; 
        if (Integer.MIN_VALUE != categoryId) {
            categoryList = new ArrayList<Category>();
            categoryList.add(categoryService.getObjectById(categoryId));
        }else{
            categoryList = allCategoryList;
        }

        model.put("categoryList", categoryList);
        model.put("allCategoryList", allCategoryList);
        modelAndView.setViewName("categoryList");
        return null;
    }

}

package net.iforums.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.ForumConstants;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 抽象的controller,会捕获所有的异常,返回指定的错误页面
 * @author Rafael Steil
 * @version $Id: $
 */
public abstract class AbstractController extends ParameterizableViewController{

	private String errorViewName = ForumConstants.ERROR_VIEW_NAME;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        try{
            return handleGetPostRequestInternal(request,response);
        }catch(Exception e){
            logger.error("页面逻辑处理出现错误",e);
        }
        return new ModelAndView(new RedirectView(getErrorViewName())); 
	}
    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected abstract ModelAndView handleGetPostRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
    public String getErrorViewName() {
		return errorViewName;
	}
	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}
}

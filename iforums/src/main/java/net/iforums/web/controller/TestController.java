package net.iforums.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/test/a.do")
public class TestController extends AbstractController{
//
//	@RequestMapping(value="new")
//	public @ResponseBody String newPath(){
//		System.out.println("----------------");
//		return "test";
//	}
//	@RequestMapping(value="new1")
//	public ModelAndView newPath(HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("result", "oooo1231111");
//		
//		return new ModelAndView("/templates/abc",params);
//	}
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest arg0,
//			HttpServletResponse arg1) throws Exception {
//		// TODO Auto-generated method stub
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("result", "oooo");
//		
//		return new ModelAndView("/templates/abc",params);
//	}
	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response,Map<String,Object> model)
			throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("result", "oooo4562222");
		
		return new ModelAndView("/templates/abc",params);
	}
}

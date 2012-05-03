package com.googlecode.iforums.web.module;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class WebModuleContext {

    public static final int ERROR_VCODE = 1;
    
    private int error = 0;
    private Method method;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ModelAndView modelAndView;
    public WebModuleContext(Method method, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView){
        this.method = method;
        this.request = request;
        this.response = response;
        
        this.modelAndView = modelAndView;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public void setModelAndView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }
    
    public boolean hasError(){
        return error!=0;
    }
}

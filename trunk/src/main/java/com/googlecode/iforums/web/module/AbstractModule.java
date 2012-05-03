package com.googlecode.iforums.web.module;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public abstract class AbstractModule implements Module {

    public PrintWriter getWriter(HttpServletResponse response){
        try {
            return response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

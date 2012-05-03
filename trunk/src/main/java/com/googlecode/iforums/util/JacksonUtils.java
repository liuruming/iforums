package com.googlecode.iforums.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.googlecode.iforums.bean.Category;
public class JacksonUtils {
    public static String toString(Object object) {
        ObjectMapper mapper = getJacksonObjectMapper();
        return toString(object, mapper);
    }
  
    private static String toString(Object object, ObjectMapper mapper) {
        String json = "";
        try {
            json = mapper.writeValueAsString(object);
        } catch (IOException e) {
        }
        return json;
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        if (StringUtils.isNotBlank(json)) {
            ObjectMapper mapper = getJacksonObjectMapper();
            try {
                return mapper.readValue(json, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static ObjectMapper mapper = null;
    private static ObjectMapper getJacksonObjectMapper() {
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        return mapper;
    }
    
    public static void main(String[] args) {
        System.out.println(toString(new Category()));
    }
}

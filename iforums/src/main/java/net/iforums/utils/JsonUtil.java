package net.iforums.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static final DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
    public static String toString(Object object) {
        ObjectMapper mapper = getJacksonObjectMapper();
        return toString(object, mapper);
    }
 
    public static String toString(Object object, ObjectMapper mapper) {
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

    private static ObjectMapper getJacksonNumAsStringObjectMapper() {
        ObjectMapper mapper = getJacksonObjectMapper();
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        return mapper;
    }


    private static ObjectMapper getJacksonObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().setDateFormat(df);
        mapper.getDeserializationConfig().setDateFormat(df);
        return mapper;
    }
}

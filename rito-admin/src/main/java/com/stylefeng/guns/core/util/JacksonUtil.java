package com.stylefeng.guns.core.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: json转换集合类型
 *
 * @author suntf
 * @date 2019/2/23 13:30
 */
public class JacksonUtil {

    private static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 空属性序列化返回空字符串封装
     */
    private static final ObjectMapper mapper = new JacksonObjectMapper();

    /**
     * 最原始序列化
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static ObjectMapper getInstance() {
        return mapper;
    }


    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass,
                                              Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass,
                elementClasses);
    }

    /**
     * 转换集合类型
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> convertList(String jsonString, Class<T> clazz) {
        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            List<T> list = null;
            try {
                mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
                list = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        return null;
    }

    /**
     * 转换map类型
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> convertMap(String jsonString) {

        if (jsonString != null && !"".equals(jsonString)) {
            JavaType javaType = getCollectionType(HashMap.class, String.class, Object.class);
            try {
                return mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 对象序列化
     *
     * @param obj 需要序列化的对象
     * @return 返回json字符串
     */
    public static String toJson(Object obj) {
        try {
            mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
            return mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将JSON转换成object
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {

        try {
            mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            mapper.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
            T t = mapper.readValue(json, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

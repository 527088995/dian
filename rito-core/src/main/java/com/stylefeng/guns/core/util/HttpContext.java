package com.stylefeng.guns.core.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/28 13:56
 */
public class HttpContext {
    public HttpContext() {
    }

    public static String getIp() {
        HttpServletRequest request = getRequest();
        return request == null ? "127.0.0.1" : request.getRemoteHost();
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getResponse();
    }

    public static Map<String, String> getRequestParameters() {
        HashMap<String, String> values = new HashMap();
        HttpServletRequest request = getRequest();
        if (request == null) {
            return values;
        } else {
            Enumeration enums = request.getParameterNames();

            while (enums.hasMoreElements()) {
                String paramName = (String) enums.nextElement();
                String paramValue = request.getParameter(paramName);
                values.put(paramName, paramValue);
            }

            return values;
        }
    }
}

package com.stylefeng.guns.config.rito;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.stereotype.Component;

@Component
public class HttpInvokerRequestInit {

    //@Autowired
    //private MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager;

    @SuppressWarnings("deprecation")
    @Bean
    public HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor() {
        HttpComponentsHttpInvokerRequestExecutor bean = new HttpComponentsHttpInvokerRequestExecutor();
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
        cm.setMaxTotal(100);
        HttpClient httpclient = new DefaultHttpClient(cm);
        //httpClient.setConnectionTimeout(1000);
        //httpClient.setTimeout(2000);
        bean.setHttpClient(httpclient);
        return bean;
    }

}


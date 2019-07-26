package com.stylefeng.guns.config.rito;

import com.longtop.efmp.peg.srv.bs.IAppSrvBS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class RemotingServiceInit {


    @Autowired
    private HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor;

    @Bean
    public HttpInvokerProxyFactoryBean iAppSrvBS() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl("http://20.9.1.5:7021/efmp-cor-war/remoting/CorPetService");
        bean.setServiceInterface(IAppSrvBS.class);
        bean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
        return bean;
    }
}


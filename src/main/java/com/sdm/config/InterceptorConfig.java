package com.sdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    //component注解默认未加载到spring管理中，会导致AuthInterceptor类中引用其他服务的@Reference注解和@Resource注解无效
    //bean注解使之加入spring容器管理
}

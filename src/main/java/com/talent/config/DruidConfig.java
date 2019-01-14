package com.talent.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guobing
 * @Title: DruidConfig
 * @ProjectName spring-boot-06-jdbc
 * @Description: 添加自定义数据源
 * @date 2019/1/14下午1:48
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     *      1. 首先需要配置一个管理后台的Servlet
     *      2. 配置一个监控的filter
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        // StatViewServlet来进入管理后台的servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        // 登录后台的用户名和密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        // 允许哪些登录
        initParams.put("allow", "");
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        // 不进行拦截
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        registrationBean.setInitParameters(initParams);
        // 拦截所有请求
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;

    }
}

package com.smartcity.springbootoauth2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
/**
 * @author lipoGiser
 * @date 2020/1/13 17:46
 * @Version 0.1
 * @des 配置资源服务器
 */
@Configuration
@EnableResourceServer //注解来开启资源服务器
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint ;

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    } ;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.info("=================server config=======================");
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/hello/")
                .permitAll()
                .antMatchers("/secure/**").authenticated();

    }

}

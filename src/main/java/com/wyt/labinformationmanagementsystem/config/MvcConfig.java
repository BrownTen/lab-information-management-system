package com.wyt.labinformationmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     *  自定义WebMvcConfiguration
     *  将于自动配置的WebMvcConfiguration一起作用
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            /**
             * 修改默认访问首页
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("index.html").setViewName("login");
                registry.addViewController("stu_main.html").setViewName("student/dashboard");
                registry.addViewController("admin_main.html").setViewName("admin/dashboard");
                registry.addViewController("teacher_main.html").setViewName("teacher/dashboard");
            }
        };
        return configurer;
    }

}

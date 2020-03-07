package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc     不要接管SpringMVC
@Configuration
//public class MyMvcConfig extends WebMvcConfigurerAdapter {
//public class MyMvcConfig extends WebMvcConfigurationSupport {     弹幕说解决没有css的问题是改成实现WebMvcConfigurer接口  评论里面有 还要方向 也就是下面的addResourceHandlers
public class MyMvcConfig implements WebMvcConfigurer {

//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer
    //https:www.cnblogs.com/zsg88/p/11278089.html
    //编码方式修改配置 不用配置文件
    /*@Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryWebServerFactoryCustomizer(){
        return new  WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8081);
            }
        };
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //浏览器发送/atguigu 请求，来到success页面
        //registry.addViewController("/atguigu").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源： *.css   ,   *.js
        //SpringBoot已经做好了静态资源映射     并没有。。。
        /*registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login",
                "/asserts/**","/webjars/**");*/
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","classpath:/mybatis/");
    }
}

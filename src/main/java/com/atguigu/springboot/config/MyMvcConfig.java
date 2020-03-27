package com.atguigu.springboot.config;

import com.atguigu.springboot.component.ManagerLoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocaleResolver;
import com.atguigu.springboot.component.UserLoginHandlerInterceptor;
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
        registry.addViewController("/pdfview.html").setViewName("pdfview");
        registry.addViewController("/html5player.html").setViewName("html5player");
        //registry.addViewController("/home/personalHistory.html").setViewName("home/personalHistory");
        //registry.addViewController("/home/login.html").setViewName("home/login");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源： *.css   ,   *.js
        //SpringBoot已经做好了静态资源映射     并没有。。。
        //后台拦截器
        registry.addInterceptor(new ManagerLoginHandlerInterceptor()).addPathPatterns("/back/**").excludePathPatterns("/index.html","/","/manager/login",
                "/asserts/**","/webjars/**","/home/verifyCode/getVerifyCode");


        //前台拦截器，只要拦截评论功能吧
        registry.addInterceptor(new UserLoginHandlerInterceptor()).addPathPatterns(
                "/home/comments/like/{commentId}",
                "/home/comments",
                "/home/showHistory",
                "/home/showPersonalRecommended");
        /*excludePathPatterns("/home/login","/","/user/login",
                "/asserts/**","/webjars/**","/blog/**","/image/**","/media/**","/music/**","/pdfjs/**",
                "/home/verifyCode/getVerifyCode");*/
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","classpath:/mybatis/","classpath:/media/");
    }
}

package com.lzw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

/**
 * Created by LZW on 2019/3/31.
 */
@Configuration
public class FilterConfig {

    @Value("${glOrigin}")
    private String glOrigin;




    /**
     * 此种过滤器，是直接修改springmvc内置对象CorsFilter。全局有效
     * cors跨域资源共享。
     * 解决了ajax跨域问题，也防止了csrf伪造站点请求。不需要单独csrfFilter去验证 请求的源了，cors也是验证请求的源，并且还支持ajax跨域
     * @return
     */
//    @Bean
//    public CorsFilter corsFilter(){
//        //1.添加CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //放行哪些原始域
//        if(glOrigin != null){
//            String[] gloArry = glOrigin.split(",");
//            if(gloArry.length > 0){
//                for(String glo:gloArry){
//                    config.addAllowedOrigin(glo);
//                }
//            }
//        }
//        //是否发送Cookie信息
//        config.setAllowCredentials(true);
//        //放行哪些原始域(请求方式)
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        //放行哪些原始域(头部信息)
//        config.addAllowedHeader("*");
//        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//        //config.addExposedHeader("*");
//        //2.添加映射路径
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//
//        //设置filter的级别
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
//        bean.setOrder(0);
//
//        //3.返回新的CorsFilter.
//        return new CorsFilter(configSource);
//    }

    /**
     * 这种过滤器的方式，和web.xml中直接管理一个filter一样，关联一个filter类，直接filter在web容器中的
     * 由于这种方式的过滤器最终在web容器中，所以在web容器中初始化的XssFilter类，不能直接通过注解获取spring里面的对象。。。
     * 在加载这个过滤器时，spring里面的对象都已经初始化完毕。所以是可以拿到的
     * 只不过需要拿到spring容器，从容器中再拿bean。
     * 等会可以直接测试！！！！！！！！！！！！！！！！！！！！！
     *！！！！！！！！！！！！
     * 防止xss脚本注入攻击，引入xssFilter对url和参数进行特殊字符验证
     * @return
     */
//    @Bean
//    public FilterRegistrationBean xssFilter(){
//        FilterRegistrationBean bean = new FilterRegistrationBean(new XssFilter());
//        bean.setOrder(1);
//        bean.addUrlPatterns("/**");
//        return bean;
//    }

    /**
     * 这种过滤器，看名字 他就是代理过滤器
     * 这货是在web容器中一个代理过滤器，代理到spring容器中的过滤器对象
     * @return
     */
//    @Bean
//    public DelegatingFilterProxyRegistrationBean xssFilter(){
//        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("xssFilter");
//        bean.addUrlPatterns("/**");
//        return bean;
//    }
}

package com.atguigu.scw.webui.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class AppWebMvcConfig implements WebMvcConfigurer {

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		
//	}

//	<mvc:interceptors>		
//		<mvc:interceptor>
//			<mvc:mapping path=""/>
//			<mvc:exclude-mapping path=""/>
//			<bean id="" class=""></bean>
//		</mvc:interceptor>
//	</mvc:interceptors>

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor())//添加拦截器
//        .addPathPatterns("/**") //拦截所有请求
//        .excludePathPatterns("/UserCon/**", "/Doctor/**", "/SMS/**");//对应的不拦截的请求
	}
	
// 	@RequestMapper("/abc")
// 	public String abc(){
// 		return "abc";
// 	}
// 	<mvc:view-controller path="/abc" view-name="abc"/>
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/login.html").setViewName("login");
	}
	
}

package com.lzw.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LZW
 * @version 2016年6月21日 上午9:50:58
 *
 * 访问路径 localhost:8080/swagger-ui.html
 *
 *  - @Api()用于类；
    表示标识这个类是swagger的资源
    - @ApiOperation()用于方法；
    表示一个http请求的操作
    - @ApiParam()用于方法，参数，字段说明；
    表示对参数的添加元数据（说明或是否必填等）
    - @ApiModel()用于类
    表示对类进行说明，用于参数用实体类接收
    - @ApiModelProperty()用于方法，字段
    表示对model属性的说明或者数据操作更改
    - @ApiIgnore()用于类，方法，方法参数
    表示这个方法或者类被忽略
    - @ApiImplicitParam() 用于方法
    表示单独的请求参数
    - @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam

    具体使用举例说明：
     @Api()
     用于类；表示标识这个类是swagger的资源
     tags–表示说明
     value–也是说明，可以使用tags替代
     但是tags如果有多个值，会生成多个list

 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket platformApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-platform").apiInfo(apiInfo())
				.forCodeGeneration(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("lzw-API").description("©2019 Copyright. Powered By lzw.")
				// .termsOfServiceUrl("")
				.contact(new Contact("lzw", "", "lzw@163.com")).license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}

}
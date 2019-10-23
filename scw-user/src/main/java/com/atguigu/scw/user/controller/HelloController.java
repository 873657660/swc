package com.atguigu.scw.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/** javadoc 只能生成api文档，不能模拟测试。
 * 测试Swagger的helloworld
 * @author Administrator
 *
 */
@Api(tags = "测试Swagger的helloworld")
@RestController
public class HelloController {

	@ApiImplicitParams(value = {
			@ApiImplicitParam(value="姓名",name = "name",required = true)
	})
	@ApiOperation("hello方法测试")
	@GetMapping("/hello")
	public String hello(String name) {
		return "OK:" + name;
	}
	
	@ApiImplicitParams(value = {
			@ApiImplicitParam(value="姓名",name = "name",required = true)
	})
	@ApiOperation("hello方法测试2")
	@GetMapping("/hello2")
	public User hello2(String name) {
		User user = new User();
		user.setId(1);
		user.setName("TOM");
		return user;
	}

}

package com.atguigu.scw.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class TMemberController {
	
	@Autowired
	TMemberService memberService ;

	@GetMapping("/getMemberById/{id}")
	public AppResponse<TMember> getMemberById(@PathVariable("id") Integer id){
		
		TMember member = memberService.getTMemberById(id);
		
		return AppResponse.ok(member);
	}
	
	
}

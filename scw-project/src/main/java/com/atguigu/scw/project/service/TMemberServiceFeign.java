package com.atguigu.scw.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.scw.project.service.exp.handler.TMemberServiceFeignExceptionHandler;
import com.atguigu.scw.project.vo.resp.TMember;
import com.atguigu.scw.vo.resp.AppResponse;

@FeignClient(value="SCW-USER",fallback = TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {
	@GetMapping("/user/getMemberById/{id}")
	public AppResponse<TMember> getMemberById(@PathVariable("id") Integer id);
}

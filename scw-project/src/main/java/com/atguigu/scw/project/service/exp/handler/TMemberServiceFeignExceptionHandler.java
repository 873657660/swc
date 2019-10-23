package com.atguigu.scw.project.service.exp.handler;

import org.springframework.stereotype.Component;

import com.atguigu.scw.project.service.TMemberServiceFeign;
import com.atguigu.scw.project.vo.resp.TMember;
import com.atguigu.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {@Override
	public AppResponse<TMember> getMemberById(Integer id) {
		return AppResponse.fail(null);
	}

}

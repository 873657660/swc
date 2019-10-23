package com.atguigu.scw.webui.service.exp.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TMemberServiceFeign;
import com.atguigu.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {

	@Override
	public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct,@RequestParam("password") String password) {
		
		log.debug("远程调用【SCW-USER】微服务的用户登录接口-出现异常.");
		
		AppResponse<UserRespVo> resp = AppResponse.fail(null);
		resp.setMsg("远程调用【SCW-USER】微服务的用户登录接口-出现异常.");
		
		return resp;
	}

}

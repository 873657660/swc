package com.atguigu.scw.order.service.exp.handler;

import org.springframework.stereotype.Component;

import com.atguigu.scw.order.service.TProjectServiceFeign;
import com.atguigu.scw.order.vo.resp.TReturn;
import com.atguigu.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<TReturn> returnInfo(Integer returnId) {
		log.debug("远程调用【SCW-PROJECT】微服务的根据id查询回报接口-出现异常.");
		
		AppResponse<TReturn> resp = AppResponse.fail(null);
		resp.setMsg("远程调用【SCW-PROJECT】微服务的根据id查询回报接口-出现异常.");
		
		return resp;
	}

}

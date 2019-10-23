package com.atguigu.scw.webui.service.exp.handler;

import org.springframework.stereotype.Component;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TOrderServiceFeign;
import com.atguigu.scw.webui.vo.req.OrderInfoSubmitVo;
import com.atguigu.scw.webui.vo.resp.TOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TOrderServiceFeignExceptionHandler implements TOrderServiceFeign {

	@Override
	public AppResponse<TOrder> saveOrder(OrderInfoSubmitVo vo) {
		AppResponse<TOrder> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【SCW-ORDER】的下订单接口失败.");
		log.debug("调用远程服务【SCW-ORDER】的下订单接口失败.");
		return resp;
	}

}

package com.atguigu.scw.webui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.exp.handler.TOrderServiceFeignExceptionHandler;
import com.atguigu.scw.webui.vo.req.OrderInfoSubmitVo;
import com.atguigu.scw.webui.vo.resp.TOrder;

@FeignClient(value="SCW-ORDER",fallback = TOrderServiceFeignExceptionHandler.class)
public interface TOrderServiceFeign {

	
	// 简单参数     @RequestParam   @PathVariable
	// 复杂参数     @RequestBody
	@PostMapping("/order/saveOrder")
	public AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo vo);
	
}

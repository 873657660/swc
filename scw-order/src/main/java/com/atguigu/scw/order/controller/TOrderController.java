package com.atguigu.scw.order.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.enums.OrderStatusEnumes;
import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.service.TOrderService;
import com.atguigu.scw.order.service.TProjectServiceFeign;
import com.atguigu.scw.order.vo.req.OrderInfoSubmitVo;
import com.atguigu.scw.order.vo.resp.TReturn;
import com.atguigu.scw.util.AppDateUtils;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api("订单")
@RequestMapping("/order")
public class TOrderController {

	@Autowired
	TOrderService orderService;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@PostMapping("/saveOrder")
	public AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo vo){
		System.out.println("saveOrder....");
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		TOrder order = new TOrder();
		
		String memberId = stringRedisTemplate.opsForValue().get(vo.getAccessToken());
		
		order.setMemberid(Integer.parseInt(memberId));
		order.setProjectid(vo.getProjectId());
		order.setReturnid(vo.getReturnId());
		
		String ordernum = UUID.randomUUID().toString().replaceAll("-", "");
		
		order.setOrdernum(ordernum);
		
		order.setCreatedate(AppDateUtils.getFormatTime());
		
		AppResponse<TReturn> resp = projectServiceFeign.returnInfo(vo.getReturnId());
		TReturn rtn = resp.getData();
		
		//下订单的总价格，不要从客户端提交过来。不 安全。
		//价格可能变动，重新计算总价格。
		Integer money = vo.getRtncount() * rtn.getSupportmoney() + rtn.getFreight();
		
		order.setMoney(money);//重新计算总金额
		order.setRtncount(vo.getRtncount());
		order.setStatus(OrderStatusEnumes.UNPAY.getCode()+"");
		order.setAddress(vo.getAddress());
		order.setInvoice(vo.getInvoice().toString());
		order.setInvoictitle(vo.getInvoictitle());
		order.setRemark(vo.getRemark());
		
		
		orderService.saveOrder(order);
		
		log.debug("后台服务保存订单成功：order={}",order);
		
		return AppResponse.ok(order);
	}
	
}

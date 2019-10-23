package com.atguigu.scw.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.mapper.TOrderMapper;
import com.atguigu.scw.order.service.TOrderService;

@Service
@Transactional
public class TOrderServiceImpl implements TOrderService {

	@Autowired
	TOrderMapper orderMapper ;

	@Override
	public void saveOrder(TOrder order) {
		orderMapper.insertSelective(order);
	}
	
}

package com.atguigu.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.exp.handler.TProjectServiceFeignExceptionHandler;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;

@FeignClient(value="SCW-PROJECT",fallback = TProjectServiceFeignExceptionHandler.class)
public interface TProjectServiceFeign {
	@GetMapping("/project/all")
	public AppResponse<List<ProjectVo>> all();
	
	@GetMapping("/project/details/info/{projectId}")
	public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId) ;

	@GetMapping("/project/support/info/{projectId}/{returnId}")
	public AppResponse<ReturnPayConfirmVo> support(
					@PathVariable("projectId") Integer projectId,
					@PathVariable("returnId") Integer returnId);
}

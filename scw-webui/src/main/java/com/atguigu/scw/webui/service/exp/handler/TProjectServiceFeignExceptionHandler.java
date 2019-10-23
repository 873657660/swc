package com.atguigu.scw.webui.service.exp.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<List<ProjectVo>> all() {
		AppResponse<List<ProjectVo>> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【SCW-PROJECT】的查询热点项目接口失败.");
		log.debug("调用远程服务【SCW-PROJECT】的查询热点项目接口失败.");
		return resp;
	}

	@Override
	public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
		AppResponse<ProjectDetailVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【SCW-PROJECT】的查询项目详情数据接口失败.");
		log.debug("调用远程服务【SCW-PROJECT】的查询项目详情数据接口失败.");
		return resp;
	}

	@Override
	public AppResponse<ReturnPayConfirmVo> support(Integer projectId, Integer returnId) {
		AppResponse<ReturnPayConfirmVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【SCW-PROJECT】的回报确认信息接口失败.");
		log.debug("调用远程服务【SCW-PROJECT】的回报确认信息接口失败.");
		return resp;
	}

}

package com.atguigu.scw.webui.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TProjectController {

	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	//去结算，订单确认 
	@RequestMapping("/project/confirmOrder")
	public String confirmOrder(Integer num,Model model,HttpSession session) {
		log.debug("num={}",num);
		
		UserRespVo userRespVo = (UserRespVo)session.getAttribute("userRespVo");
		if(userRespVo == null) {
			
			session.setAttribute("preURL", "/project/confirmOrder?num="+num);
			session.setAttribute("message", "请先登录,再进行结算");
			
			return "redirect:/login";
		}
		
		ReturnPayConfirmVo returnPayConfirmVo = (ReturnPayConfirmVo)session.getAttribute("returnPayConfirmVoSession");
		if(returnPayConfirmVo==null) {		
			return "redirect:/index";
		}
		returnPayConfirmVo.setNum(num);		
		returnPayConfirmVo.setTotalPrice(new BigDecimal(num * returnPayConfirmVo.getPrice()+returnPayConfirmVo.getFreight()));
		
		//session一致性问题。session数据是要同步到redis中的。所以,session数据变化必须重复存放到session,这样才会同步。
		session.setAttribute("returnPayConfirmVoSession", returnPayConfirmVo);
		
		return "project/pay-step-2";
	}
	
	//支持
	@RequestMapping("/project/support/{projectId}/{returnId}")
	public String support(
						@PathVariable("projectId") Integer projectId,
						@PathVariable("returnId") Integer returnId,
						Model model,HttpSession session) {
		
		AppResponse<ReturnPayConfirmVo> resp = projectServiceFeign.support(projectId, returnId);
		
		ReturnPayConfirmVo returnPayConfirmVo = resp.getData();
		model.addAttribute("returnPayConfirmVo", returnPayConfirmVo);
		session.setAttribute("returnPayConfirmVoSession", returnPayConfirmVo);
		
		return "project/pay-step-1";
	}
	
	@RequestMapping("/project/detail")
	public String detail(Integer id,Model model) {
		log.debug("projectId={}",id);
		
		AppResponse<ProjectDetailVo> resp = projectServiceFeign.detailsInfo(id);
		ProjectDetailVo projectDetailVo = resp.getData();
		
		model.addAttribute("projectDetailVo", projectDetailVo);
		
		log.debug("projectDetailVo={}",projectDetailVo);
		
		return "project/project";
	}
	
}

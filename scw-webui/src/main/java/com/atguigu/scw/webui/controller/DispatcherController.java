package com.atguigu.scw.webui.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TMemberServiceFeign;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DispatcherController {

	@Autowired	
	TMemberServiceFeign memberServiceFeign;	
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@RequestMapping("/index")
	public String index(Model model) {
		
		//model.addAttribute("hello", "atguigu");
		//model.addAttribute("strList", Arrays.asList("zhangsan","lisi","wangwu"));
		
		AppResponse<List<ProjectVo>> resp = projectServiceFeign.all();
		
		List<ProjectVo> data = resp.getData();
		
		model.addAttribute("projectVoList", data);
		
		log.debug("projectVoList={}",data);
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {		
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {		
		if(session!=null) {
			session.removeAttribute("userRespVo");
			session.invalidate();
		}
		return "redirect:/index";
	}
	
	
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd,HttpSession session,Model model) {
		
		log.debug("loginacct={}",loginacct);
		log.debug("userpswd={}",userpswd);
		
		AppResponse<UserRespVo> resp = memberServiceFeign.login(loginacct, userpswd);
		UserRespVo userRespVo = resp.getData();
		log.debug("userRespVo={}",userRespVo);
		if(userRespVo==null) {
			log.debug("登录失败");
			
			model.addAttribute("message", resp.getMsg());
			return "login";
		}else {
			session.setAttribute("userRespVo", userRespVo);
			log.debug("登录成功");
			
			String preURL = (String)session.getAttribute("preURL");
			if(StringUtils.isEmpty(preURL)) {
				return "redirect:/index";
			}else {
				return "redirect:"+preURL;
			}
			
		}
	}
}

package com.atguigu.scw.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.component.SmsTemplate;
import com.atguigu.scw.user.constant.AppUserConst;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
public class UserLoginRegistController {
	
	@Autowired
	SmsTemplate smsTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
	TMemberService memberService;
	
	
	
	
	@ApiOperation(value="发送短信验证码") 
	@PostMapping("/sendsms")
	public AppResponse<Object> sendsms(String loginacct){
		
		log.debug("loginacct={}",loginacct);
		
		String code = UUID.randomUUID().toString().substring(0, 4);
		
		stringRedisTemplate.opsForValue().set("code", code,AppUserConst.TIME_OUT_CODE_RIGIST,TimeUnit.MINUTES);

		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", loginacct);
		querys.put("param", "code:"+code);
		querys.put("tpl_id", "TP1711063");
		
		AppResponse<String> result = smsTemplate.sendSms(querys);
		String data = result.getData();
		if("ok".equals(data)) {
			return AppResponse.ok("ok");
		}else {
			return AppResponse.fail("fail");
		}
	} 
	
	@ApiOperation(value="用户注册") 
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo vo){
		try {
			log.debug("UserRegistVo={}",vo);
			
			//1.验证验证码
			String code = stringRedisTemplate.opsForValue().get("code");
			if(StringUtils.isEmpty(code)) {
				return AppResponse.fail("验证失效");
			}
			
			if(!code.equals(vo.getCode())) {
				return AppResponse.fail("验证码不一致");
			}
			
			
			//2.对拷
			TMember member = new TMember();		
			BeanUtils.copyProperties(vo, member); //属性名称和类型必须一致，才能对拷。
			
			//3.保存
			memberService.saveMember(member);
			
			//4.清理缓存
			stringRedisTemplate.delete("code");
			
			//5.返回结果
			return AppResponse.ok("ok");
		} catch (Exception e) {
			e.printStackTrace();
			return AppResponse.fail("注册失败");
		}
	} 
	
	
	@ApiOperation(value="用户登陆") 
	@ApiImplicitParams(value={
			@ApiImplicitParam(value="登陆账号",name="loginacct"),
			@ApiImplicitParam(value="用户密码",name="password")
	})
	@PostMapping("/login")
	public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct,@RequestParam("password") String password){
		log.debug("loginacct={}",loginacct);
		log.debug("password={}",password);
		
		try {
			TMember member = memberService.getMemberByLogin(loginacct,password);
			
			UserRespVo vo = new UserRespVo();
			
			BeanUtils.copyProperties(member, vo);
			
			String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
			vo.setAccessToken(accessToken);
			
			//将临时token与用户绑定，保存到redis中。便于以后识别用户是谁
			stringRedisTemplate.opsForValue().set(accessToken, member.getId()+"");
			
			return AppResponse.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return AppResponse.fail(null);
		}
	} 
	

	

	
	@ApiOperation(value="验证短信验证码") 
	@PostMapping("/valide")
	public AppResponse<Object> valide(){
		return AppResponse.ok("ok");
	} 
	
	@ApiOperation(value="重置密码") 
	@PostMapping("/reset")
	public AppResponse<Object> reset(){
		return AppResponse.ok("ok");
	} 
	
	

}

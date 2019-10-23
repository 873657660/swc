package com.atguigu.scw.project.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.atguigu.scw.enums.ProjectStatusEnume;
import com.atguigu.scw.project.bean.TReturn;
import com.atguigu.scw.project.component.OssTemplate;
import com.atguigu.scw.project.constant.ProjectConstant;
import com.atguigu.scw.project.service.TProjectService;
import com.atguigu.scw.project.vo.req.BaseVo;
import com.atguigu.scw.project.vo.req.ProjectBaseInfoVo;
import com.atguigu.scw.project.vo.req.ProjectRedisStorageVo;
import com.atguigu.scw.project.vo.req.ProjectReturnVo;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

	@Autowired
	OssTemplate ossTemplate ;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TProjectService projectService;
	
	/**
	 * 文件上传表单要求：
	 * 	1.<input type="file" name="uploadfile" multiple="multiple">
	 *  2.<form enctype="multipart/form-data">
	 *  3.method=post
	 *  
	 *  SpringMVC框架集成Commons-fileupload和Commons-io文件上传组件，完成文件上传过程。
	 *  文件上传解析器。CommonsMultipartResolver  默认限制上传大小：1M
	 *  表示文件对象：MultipartFile 
	 */
	//上传图片
	@ApiOperation(value = "上传图片")
	@PostMapping("/upload")
	public AppResponse<Object> upload(@RequestParam("uploadfile") MultipartFile[] uploadfile) {
		
		try {
			List<String> pathList = new ArrayList<String>();
			
			for (MultipartFile multipartFile : uploadfile) {
				InputStream inputStream = multipartFile.getInputStream(); //原始文件输入流
				String filename = multipartFile.getOriginalFilename(); //原始文件名称
				filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + filename ;
				String path = ossTemplate.upload(inputStream, filename);
				pathList.add(path);
			}
			
			log.debug("pathList={}",pathList);
			
			return AppResponse.ok(pathList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		return AppResponse.fail(null);
	}
	
	//1.项目初始化
	@ApiOperation(value = "第一步：项目初始创建")
	@PostMapping("/init")
	public AppResponse<Object> init(BaseVo vo) {
		
		//1.验证用户是否登录
		String accessToken = vo.getAccessToken();
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		if(StringUtils.isEmpty(memberId)) {
			AppResponse<Object> resp = AppResponse.fail(null);
			resp.setMsg("请先登录再访问");
			return resp;
		}
		
		//2.创建大VO,将小VO数据对拷大VO中
		ProjectRedisStorageVo bigVO = new ProjectRedisStorageVo();
		bigVO.setAccessToken(vo.getAccessToken());
		bigVO.setMemberid(Integer.parseInt(memberId));
		
		//3.给大VO设置临时projectToken(此时项目id主键还没生成出来。用临时token标识项目对象)
		String projectToken = UUID.randomUUID().toString().replaceAll("-", "");
		bigVO.setProjectToken(projectToken);
		
		//4.将大VO转换为JSON串
		String bigStr = JSON.toJSONString(bigVO);
		
		//5.存储到redis缓存区中
		stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+bigVO.getProjectToken(), bigStr);
		
		return AppResponse.ok(bigVO);
	}
	
	//2.填写项目基本信息和发起人信息
	@ApiOperation(value = "第二步：填写项目基本信息和发起人信息")
	@PostMapping("/projectinfo")
	public AppResponse<Object> projectinfo(ProjectBaseInfoVo vo) {
		
		//1.验证是否登录
		String accessToken = vo.getAccessToken();
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		if(StringUtils.isEmpty(memberId)) {
			AppResponse<Object> resp = AppResponse.fail(null);
			resp.setMsg("请先登录再访问");
			return resp;
		}
		
		//2.从Redis缓存区中获取bigStr转换为bigVO
		String bigStr = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX+vo.getProjectToken());
		ProjectRedisStorageVo bigVO = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);
		
		//3.对拷
		BeanUtils.copyProperties(vo, bigVO);
		
		//4.将bigVo保持到redis
		bigStr = JSON.toJSONString(bigVO);
		stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+vo.getProjectToken(), bigStr);

		return AppResponse.ok(bigVO);
	}
	
	//  json = [{name:'a',age:22},{name:'ba',age:23}]  原样提交，
	//通过@RequestBody可以直接接受(必须是集合List<User> users，不能是数组 User[] users)。
	//SpringMVC框架会将多个json对象封装为POJO对象。
	
	//  如果将json转换为 url?name=a&age=22&name=b&age=23  参数格式。那么，可以 String[] name  , Integer[] age
	//3.添加项目回报档位
	@ApiOperation(value = "第三步: 添加项目回报档位")
	@PostMapping("/return")
	public AppResponse<Object> returnDetail(@RequestBody List<ProjectReturnVo> voList) {
		log.debug("voList={}",voList);
		
		
		//1.验证是否登录
		String accessToken = voList.get(0).getAccessToken();
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		if(StringUtils.isEmpty(memberId)) {
			AppResponse<Object> resp = AppResponse.fail(null);
			resp.setMsg("请先登录再访问");
			return resp;
		}
		
		//2.从Redis缓存区中获取bigStr转换为bigVO
		String bigStr = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX+voList.get(0).getProjectToken());
		ProjectRedisStorageVo bigVO = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);
		
		//3.对拷
		List<TReturn> projectReturns = new ArrayList<TReturn>();
		for (ProjectReturnVo projectReturnVo : voList) {
			TReturn treturn = new TReturn();
			BeanUtils.copyProperties(projectReturnVo, treturn);
			projectReturns.add(treturn);
		}
		bigVO.setProjectReturns(projectReturns);
		
		//4.将bigVo保持到redis
		bigStr = JSON.toJSONString(bigVO);
		stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+voList.get(0).getProjectToken(), bigStr);
		
		return AppResponse.ok(bigVO);
	}
	
	//4.第四步：项目提交审核申请或保存草稿
	@ApiOperation(value = "第四步：项目提交审核申请或保存草稿")
	@PostMapping("/submit")
	public AppResponse<Object> submit(String ops,String accessToken,String projectToken) {
		log.debug("ops={}",ops);
		log.debug("accessToken={}",accessToken);
		log.debug("projectToken={}",projectToken);
		
		
		//1.验证是否登录
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		if(StringUtils.isEmpty(memberId)) {
			AppResponse<Object> resp = AppResponse.fail(null);
			resp.setMsg("请先登录再访问");
			return resp;
		}
		
		if("0".equals(ops)) { //保存草稿
			projectService.saveProject(projectToken,ProjectStatusEnume.DRAFT.getCode()+"");
			return AppResponse.ok("ok");
		}else if("1".equals(ops)) { //提交
			projectService.saveProject(projectToken,ProjectStatusEnume.SUBMIT_AUTH.getCode()+"");
			return AppResponse.ok("ok");
		}else { //操作错误			
			return AppResponse.fail(null);
		}

	}
	

	

	

	


	
	
//	@ApiOperation(value = "确认项目法人信息")
//	@PostMapping("/confirm/legal")
//	public AppResponse<Object> legal() {
//		return AppResponse.ok("ok");
//	}
	
//	@ApiOperation(value = "项目草稿保存")
//	@PostMapping("/tempsave")
//	public AppResponse<Object> tempsave() {
//		return AppResponse.ok("ok");
//	}
	
//	@ApiOperation(value = "删除项目回报档位")
//	@DeleteMapping("/return")
//	public AppResponse<Object> deleteReturnDetail() {
//		return AppResponse.ok("ok");
//	}
}

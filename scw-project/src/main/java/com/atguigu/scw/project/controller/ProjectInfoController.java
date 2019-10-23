package com.atguigu.scw.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.project.bean.TProject;
import com.atguigu.scw.project.bean.TProjectImages;
import com.atguigu.scw.project.bean.TReturn;
import com.atguigu.scw.project.bean.TTag;
import com.atguigu.scw.project.bean.TType;
import com.atguigu.scw.project.component.OssTemplate;
import com.atguigu.scw.project.service.ProjectInfoService;
import com.atguigu.scw.project.service.TMemberServiceFeign;
import com.atguigu.scw.project.vo.resp.ProjectDetailVo;
import com.atguigu.scw.project.vo.resp.ProjectVo;
import com.atguigu.scw.project.vo.resp.ReturnPayConfirmVo;
import com.atguigu.scw.project.vo.resp.TMember;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "项目基本功能模块（文件上传、项目信息获取等）")
@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectInfoController {

	@Autowired
	OssTemplate ossTemplate;

	@Autowired
	ProjectInfoService projectInfoService;
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	

	@ApiOperation("[+]支持项目，获取支持项目回报信息(项目信息+发起人信息+回报信息)")
	@GetMapping("/support/info/{projectId}/{returnId}")
	public AppResponse<ReturnPayConfirmVo> support(
			@PathVariable("projectId") Integer projectId,
			@PathVariable("returnId") Integer returnId) {
		ReturnPayConfirmVo vo = new ReturnPayConfirmVo();
		
		//1.封装项目信息
		TProject project = projectInfoService.getProjectInfo(projectId);
		vo.setProjectId(project.getId());
		vo.setProjectName(project.getName());
		vo.setProjectRemark(project.getRemark());		
		
		//2.封装发起人信息
		//远程调用用户微服务，获取会员用户(发起人)信息
		Integer memberid = project.getMemberid();
		AppResponse<TMember> resp = memberServiceFeign.getMemberById(memberid);
		TMember member = resp.getData();
		vo.setMemberId(member.getId());
		vo.setMemberName(member.getLoginacct());
		
		//3.封装回报信息
		TReturn treturn = projectInfoService.getProjectReturnById(returnId);
		vo.setReturnId(treturn.getId());
		vo.setReturnContent(treturn.getContent());
		vo.setNum(treturn.getCount());
		vo.setPrice(treturn.getSupportmoney());
		vo.setFreight(treturn.getFreight());
		vo.setSignalpurchase(treturn.getSignalpurchase());
		
		BigDecimal totalPrice = new BigDecimal(vo.getPrice()*vo.getNum()+vo.getFreight());
		
		vo.setTotalPrice(totalPrice);
		
		return AppResponse.ok(vo);
	}
	
	
	@ApiOperation("[+]获取项目信息详情")
	@GetMapping("/details/info/{projectId}")
	public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId) {
		TProject p = projectInfoService.getProjectInfo(projectId);
		ProjectDetailVo projectVo = new ProjectDetailVo();

		// 1、查出这个项目的所有图片
		List<TProjectImages> projectImages = projectInfoService.getProjectImages(p.getId());
		for (TProjectImages tProjectImages : projectImages) {
			if (tProjectImages.getImgtype() == 0) {
				projectVo.setHeaderImage(tProjectImages.getImgurl());
			} else {
				List<String> detailsImage = projectVo.getDetailsImage();
				detailsImage.add(tProjectImages.getImgurl());
			}
		}

		// 2、项目的所有支持档位；
		List<TReturn> returns = projectInfoService.getProjectReturns(p.getId());
		projectVo.setProjectReturns(returns);

		BeanUtils.copyProperties(p, projectVo);
		projectVo.setProjectId(projectId);
		
		return AppResponse.ok(projectVo);
	}

	@ApiOperation("[+]获取项目回报列表")
	@GetMapping("/details/returns/{projectId}")
	public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId) {

		List<TReturn> returns = projectInfoService.getProjectReturns(projectId);
		return AppResponse.ok(returns);
	}

	@ApiOperation("[+]获取项目某个回报档位信息")
	@GetMapping("/details/returns/info/{returnId}")
	public AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId) {
		TReturn tReturn = projectInfoService.getProjectReturnById(returnId);
		return AppResponse.ok(tReturn);
	}

	@ApiOperation("[+]获取系统所有的项目分类")
	@GetMapping("/types")
	public AppResponse<List<TType>> types() {

		List<TType> types = projectInfoService.getProjectTypes();
		return AppResponse.ok(types);
	}

	@ApiOperation("[+]获取系统所有的项目标签")
	@GetMapping("/tags")
	public AppResponse<List<TTag>> tags() {
		List<TTag> tags = projectInfoService.getAllProjectTags();
		return AppResponse.ok(tags);
	}

	@ApiOperation("[+]获取系统所有的项目")
	@GetMapping("/all")
	public AppResponse<List<ProjectVo>> all() {

		// 1、分步查询，先查出所有项目
		// 2、再查询这些项目图片
		List<ProjectVo> prosVo = new ArrayList<>();

		// 1、连接查询，所有的项目left join 图片表，查出所有的图片
		// left join：笛卡尔积 A*B 1000万*6 = 6000万
		// 大表禁止连接查询；
		List<TProject> pros = projectInfoService.getAllProjects();

		for (TProject tProject : pros) {
			Integer id = tProject.getId();
			List<TProjectImages> images = projectInfoService.getProjectImages(id);
			ProjectVo projectVo = new ProjectVo();
			
			BeanUtils.copyProperties(tProject, projectVo);
			
			projectVo.setProjectId(id);
			for (TProjectImages tProjectImages : images) {
				if (tProjectImages.getImgtype() == 0) {
					projectVo.setHeaderImage(tProjectImages.getImgurl());
				} 
			}
			prosVo.add(projectVo);

		}

		return AppResponse.ok(prosVo);
	}


	// 查热门推荐、分类推荐

}

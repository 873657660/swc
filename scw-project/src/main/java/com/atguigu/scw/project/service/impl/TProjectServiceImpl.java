package com.atguigu.scw.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.atguigu.scw.project.bean.TProject;
import com.atguigu.scw.project.bean.TProjectImages;
import com.atguigu.scw.project.bean.TProjectTag;
import com.atguigu.scw.project.bean.TProjectType;
import com.atguigu.scw.project.bean.TReturn;
import com.atguigu.scw.project.constant.ProjectConstant;
import com.atguigu.scw.project.mapper.TProjectImagesMapper;
import com.atguigu.scw.project.mapper.TProjectMapper;
import com.atguigu.scw.project.mapper.TProjectTagMapper;
import com.atguigu.scw.project.mapper.TProjectTypeMapper;
import com.atguigu.scw.project.mapper.TReturnMapper;
import com.atguigu.scw.project.service.TProjectService;
import com.atguigu.scw.project.vo.req.ProjectRedisStorageVo;
import com.atguigu.scw.util.AppDateUtils;

@Service
@Transactional
public class TProjectServiceImpl implements TProjectService {

	@Autowired
	TProjectMapper projectMapper;

	@Autowired
	TProjectImagesMapper projectImagesMapper;
	
	@Autowired
	TReturnMapper returnMapper;
	
	@Autowired
	TProjectTypeMapper projectTypeMapper;
	
	@Autowired
	TProjectTagMapper projectTagMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Override
	public void saveProject(String projectToken, String projectStatus) {
		String bigStr = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + projectToken);
		ProjectRedisStorageVo bigVo = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);

		// 1.保存项目数据
		TProject project = new TProject();
		project.setName(bigVo.getName());
		project.setRemark(bigVo.getRemark());
		project.setMoney(bigVo.getMoney().longValue());
		project.setDay(bigVo.getDay());

		project.setStatus(projectStatus);
		// project.setSupportmoney(0L);
		project.setMemberid(bigVo.getMemberid());
		project.setCreatedate(AppDateUtils.getFormatTime());

		projectMapper.insertSelective(project);

		// 2.保存图片
		// 2.1 保存头图
		Integer projectId = project.getId(); // 主键回填
		String headerImage = bigVo.getHeaderImage();
		TProjectImages projectImage = new TProjectImages();
		projectImage.setImgtype((byte) 0);
		projectImage.setImgurl(headerImage);
		projectImage.setProjectid(projectId);
		projectImagesMapper.insertSelective(projectImage);

		// 2.2 保存详情图
		List<String> detailsImage = bigVo.getDetailsImage();

		for (String imgurl : detailsImage) {
			TProjectImages pi = new TProjectImages();
			pi.setImgtype((byte) 1);
			pi.setImgurl(imgurl);
			pi.setProjectid(projectId);

			projectImagesMapper.insertSelective(pi);
		}

		// 3.保存回报
		List<TReturn> returns = bigVo.getProjectReturns();
		for (TReturn tReturn : returns) {
			// 设置项目的id
			tReturn.setProjectid(projectId);
			returnMapper.insertSelective(tReturn);
		}

		// 4.保存项目和分类关系
		List<Integer> typeids = bigVo.getTypeids();
		for (Integer tid : typeids) {
			TProjectType tProjectType = new TProjectType(null, projectId, tid);
			projectTypeMapper.insertSelective(tProjectType);
		}

		// 5.保存项目和标签关系
		List<Integer> tagids = bigVo.getTagids();
		for (Integer tgid : tagids) {
			TProjectTag tProjectTag = new TProjectTag(null, projectId, tgid);
			projectTagMapper.insertSelective(tProjectTag);
		}
		
		//6.删除redis中bigStr数据
		stringRedisTemplate.delete(ProjectConstant.TEMP_PROJECT_PREFIX + projectToken);
	}
}

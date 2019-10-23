package com.atguigu.scw.project.vo.req;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.scw.project.bean.TReturn;

import lombok.Data;

@Data
public class ProjectRedisStorageVo extends BaseVo {

	private String projectToken;// 项目的临时token
	
	private Integer memberid;// 会员id

	private List<Integer> typeids = new ArrayList<Integer>(); // 项目的分类id
	private List<Integer> tagids = new ArrayList<Integer>(); // 项目的标签id

	private String name;// 项目名称
	private String remark;// 项目简介
	private Integer money;// 筹资金额
	private Integer day;// 筹资天数

	private String headerImage;// 项目头部图片
	private List<String> detailsImage;// 项目详情图片

	// 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

	private List<TReturn> projectReturns = new ArrayList<TReturn>();// 项目回报

	//  易付宝企业账号,法人身份证号
}

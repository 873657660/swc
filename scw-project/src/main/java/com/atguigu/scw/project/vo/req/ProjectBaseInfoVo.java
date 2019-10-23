package com.atguigu.scw.project.vo.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProjectBaseInfoVo extends BaseVo{
	private String projectToken;// 项目的临时token

	private List<Integer> typeids = new ArrayList<Integer>(); // 项目的分类id
	private List<Integer> tagids = new ArrayList<Integer>(); // 项目的标签id

	private String name;// 项目名称
	private String remark;// 项目简介
	private Integer money;// 筹资金额
	private Integer day;// 筹资天数

	private String headerImage;// 项目头部图片
	private List<String> detailsImage;// 项目详情图片
	
}

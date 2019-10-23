package com.atguigu.scw.webui.vo.resp;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProjectVo implements Serializable{

	private Integer projectId ;
	
	private String name;// 项目名称
	private String remark;// 项目简介

	private String headerImage;// 项目头部图片

}

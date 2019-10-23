package com.atguigu.scw.project.vo.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.scw.project.bean.TReturn;

import lombok.Data;

@Data
public class ProjectDetailVo implements Serializable{

	private Integer projectId ;
	
	private String name;// 项目名称
	private String remark;// 项目简介
	private Long money;// 筹资金额
	private Integer day;// 筹资天数
	private String status;
	
    private String deploydate;

    private Long supportmoney = 0L;

    private Integer supporter = 0;

    private Integer completion = 0;

    private Integer memberid ;

    private String createdate;

    private Integer follower = 1000;
	
	private String headerImage;// 项目头部图片
	private List<String> detailsImage = new ArrayList<String>();// 项目详情图片
	// 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
	
	//第三步：收集回报信息
	private List<TReturn> projectReturns = new ArrayList<TReturn>();;// 项目回报
}

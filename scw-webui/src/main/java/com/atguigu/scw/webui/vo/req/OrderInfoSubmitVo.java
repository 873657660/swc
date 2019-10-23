package com.atguigu.scw.webui.vo.req;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


//服务接口调用传递参数
@ToString
@Data
public class OrderInfoSubmitVo implements Serializable{
	
	private String accessToken; 
	//private Integer memberId; 
	private Integer projectId; 
	private Integer returnId; 
	private Integer rtncount; 
	
	private String address;//收货地址id
	private Byte invoice;//0代表不要  1-代表要
	private String invoictitle;//发票抬头
	private String remark;//订单的备注

}

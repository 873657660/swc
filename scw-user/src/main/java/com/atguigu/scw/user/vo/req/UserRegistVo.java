package com.atguigu.scw.user.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@ApiModel
public class UserRegistVo {
	
	@ApiModelProperty("手机号")
	private String loginacct;
	
	@ApiModelProperty("密码")
	private String userpswd;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	@ApiModelProperty("验证码")
	private String code;
	
	@ApiModelProperty("会员类型")
	private String usertype;

}

package com.atguigu.scw.project.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProjectReturnVo extends BaseVo{
	
	@ApiModelProperty("项目临时Token")
    private String projectToken;

	@ApiModelProperty("回报类型：1实物回报，0虚拟回报")
    private String type;

	@ApiModelProperty("支持金额")
    private Integer supportmoney;

	@ApiModelProperty("回报内容")
    private String content;

	@ApiModelProperty("回报数量")
    private Integer count;

	@ApiModelProperty("单笔限购")
    private Integer signalpurchase;

	@ApiModelProperty("总限购数量")
    private Integer purchase;

	@ApiModelProperty("运费")
    private Integer freight;

	@ApiModelProperty("发票类型：0不开发票，1开发票")
    private String invoice;

	@ApiModelProperty("回报天数")
    private Integer rtndate;
}

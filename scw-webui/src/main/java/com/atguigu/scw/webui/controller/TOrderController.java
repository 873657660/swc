package com.atguigu.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.config.AlipayConfig;
import com.atguigu.scw.webui.service.TOrderServiceFeign;
import com.atguigu.scw.webui.vo.req.OrderFormInfoSubmitVo;
import com.atguigu.scw.webui.vo.req.OrderInfoSubmitVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.resp.TOrder;
import com.atguigu.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TOrderController {

	@Autowired
	TOrderServiceFeign orderServiceFeign;
	
	@ResponseBody
	@RequestMapping("/order/saveOrder")
	public String saveOrder(OrderFormInfoSubmitVo vo,HttpSession session) {
		log.debug("OrderFormInfoSubmitVo={}",vo);
		
		//1.下单
		OrderInfoSubmitVo orderInfoSubmitVo = new OrderInfoSubmitVo();
		BeanUtils.copyProperties(vo, orderInfoSubmitVo);
		
		UserRespVo userRespVo = (UserRespVo)session.getAttribute("userRespVo");
		String accessToken = userRespVo.getAccessToken();		
		orderInfoSubmitVo.setAccessToken(accessToken);
		
		ReturnPayConfirmVo returnPayConfirmVo =(ReturnPayConfirmVo)session.getAttribute("returnPayConfirmVoSession");
		orderInfoSubmitVo.setProjectId(returnPayConfirmVo.getProjectId());
		orderInfoSubmitVo.setReturnId(returnPayConfirmVo.getReturnId());
		
		orderInfoSubmitVo.setRtncount(returnPayConfirmVo.getNum());
		
		AppResponse<TOrder> resp = orderServiceFeign.saveOrder(orderInfoSubmitVo);
		TOrder order = resp.getData();
		log.debug("下单成功：{}",order);
		
		//2.支付
		String result = payOrder(order.getOrdernum(), order.getMoney() + "","xxx","yyy"); 

		//return "member/minecrowdfunding";
		return result ; // 表单返回给浏览器，立马提交表单，出现扫码页面
	}
	

	
	private String payOrder(String ordernum, String amount, String subject, String body) {
		//请求
		String result;
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url); //同步通知 返回给前台系统，告知用户支付完成等
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url); //异步通知 返回给后台系统，用于订单状态修改等。

			alipayRequest.setBizContent("{\"out_trade_no\":\""+ ordernum +"\"," 
					+ "\"total_amount\":\""+ amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

			result = alipayClient.pageExecute(alipayRequest).getBody();
			System.out.println(result);			
			return result ;
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return null ;
		}
		
		/* 
		<form name="punchout_form" 
		method="post" action="
		https://openapi.alipaydev.com/gateway.do?

		charset=utf-8&
		method=alipay.trade.page.pay&

		sign=WsY8ssscgh5szJUCVszGkmMC2nNiu%2FDOkQBoSpPUs4bI13aT1jQ27CK2mBkpmL87NITMNzw76mpxefoovV6XC5%2BjplErnWILnL42LlTSNe27NMHyhUsvBDr7Ryixz4Jo5YwGidwMQlMHIc5w23neF%2BKy7x8hbz6f4ywG4ZtZIvisT9AkngjwAK%2FI7v5zU9CInFqhdcYUX9NmBDswT20ZZpqK2Zzy2fbT27LqOEc9jmXo%2FuKcUy7696ZifEFGE218CK7ofFCQEkGRKyzv3EvUlryrQqw4VQA7iQKWgibjO1qwxFVwvDxmcObeN9YEzysAQ3tJf1IgQcWJIaz9nI2kZg%3D%3D&

		return_url=http%3A%2F%2Fscw001.free.idcfengye.com%2Falipay.trade.page.pay-JAVA-UTF-8%2Freturn_url.jsp&
		notify_url=http%3A%2F%2Fscw001.free.idcfengye.com%2Falipay.trade.page.pay-JAVA-UTF-8%2Fnotify_url.jsp&
		version=1.0&
		app_id=2016092300574219&
		sign_type=RSA2&
		timestamp=2019-09-03+10%3A19%3A31&
		alipay_sdk=alipay-sdk-java-dynamicVersionNo&
		format=json"
		>
		<input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;201993101911134&quot;,&quot;total_amount&quot;:&quot;299&quot;,&quot;subject&quot;:&quot;烤鸭&quot;,&quot;body&quot;:&quot;全聚德&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
		<input type="submit" value="立即支付" style="display:none" >
		</form>
		<script>document.forms[0].submit();</script>
		 */
		
		
	}














	@ResponseBody
	@RequestMapping("/order/updateOrder")
	public String updateOrder() {
		//通知时间间隔：1s,10s,1min,15min,1h,2h,6h,15h
		//异步通知
		log.debug("*****************异步通知**********************");
		
		//一般去修改订单状态等业务。
		
		//返回给支付宝消息。"success" 表示业务完成了
		return "success";
	}
	
}

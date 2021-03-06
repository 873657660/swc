package com.atguigu.scw;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.atguigu.scw.user.util.HttpUtils;

public class SmsTest {
	public static void main(String[] args) {
		String host = "http://dingxin.market.alicloudapi.com";
		String path = "/dx/sendSms";
		String method = "POST";
		String appcode = "97c8ccac3fd5436a8e7ab5bbcc7c7498";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", "13834421901");
		querys.put("param", "code:9999");
		querys.put("tpl_id", "TP1711063");
		
		Map<String, String> bodys = new HashMap<String, String>();

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

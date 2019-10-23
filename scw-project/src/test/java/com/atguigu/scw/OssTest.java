package com.atguigu.scw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class OssTest {

	public static void main(String[] args) throws FileNotFoundException {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI4FrnpkUBLJtuVjsyUVBp";
		String accessKeySecret = "AdImstqpuzUwgirOOBt9Qfu5wM7AfQ";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 上传文件流。
		InputStream inputStream = new FileInputStream("D:/temp/mm.jpg");
		ossClient.putObject("scw190831", "mm.jpg", inputStream);

		// 关闭OSSClient。
		ossClient.shutdown();
	}

}

package com.atguigu.scw.project.component;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import lombok.Data;

@Data
//@Component
public class OssTemplate {

	// @Value("${oss.endpoint}")
	String endpoint;
	String accessKeyId;
	String accessKeySecret;
	String bucketName;

	public String upload(InputStream inputStream, String filename) throws FileNotFoundException {
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		ossClient.putObject(bucketName, "pic/"+filename, inputStream);
		ossClient.shutdown();
		//https://scw190831.oss-cn-beijing.aliyuncs.com/mm.jpg
		return "https://" + bucketName + "." + endpoint + "/pic/" + filename;
	}
}

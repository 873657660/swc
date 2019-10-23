package com.atguigu.scw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwProjectApplicationTests {

	@Autowired
	DataSource dataSource;

	// 用于操作Redis的客户端模板类
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	StringRedisTemplate stringTemplate; // k,v都是字符串

	// Jedis(客户端) -> Redis
	@Test
	public void testRedisSet() throws SQLException {
		stringTemplate.opsForValue().set("hello22", "zhangsan");
	}

	@Test
	public void testRedisGet() throws SQLException {
		String str = stringTemplate.opsForValue().get("hello22");
		System.out.println(str);
	}

	@Test
	public void testDataSource() throws SQLException {
		System.out.println(dataSource.getClass());

		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		// com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@7f4a1cac
		connection.close(); // 调用连接对象的代理对象。不是销毁连接对象，而是归还到连接池
	}

}

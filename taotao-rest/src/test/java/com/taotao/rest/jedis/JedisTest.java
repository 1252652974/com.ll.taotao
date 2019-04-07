package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSinfle() {
		//创建一个jedis对象
		Jedis jedis = new Jedis("192.168.70.128", 6379);
		//调用jedis对象的方法，方法名称与redis的命令一致
		jedis.set("key", "value");
		String string = jedis.get("name");
		System.out.println(string);
		//关闭jedis
		jedis.close();
	}
	/**
	 * 调用连接池
	 */
	@Test
	public void testJedis() {
		//创建jedis连接池
		JedisPool pool = new JedisPool("192.168.70.128", 6379);
		//从连接池中获得连接池对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("name");
		System.out.println(string);
		//关闭jedis
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	 * <p>Title: testJedisCluster</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testJedisCluster() {
		//LOGGER.debug("调用redisCluster开始");
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.153", 7001));
		nodes.add(new HostAndPort("192.168.25.153", 7002));
		nodes.add(new HostAndPort("192.168.25.153", 7003));
		nodes.add(new HostAndPort("192.168.25.153", 7004));
		nodes.add(new HostAndPort("192.168.25.153", 7005));
		nodes.add(new HostAndPort("192.168.25.153", 7006));
		//LOGGER.info("创建一个JedisCluster对象");
		JedisCluster cluster = new JedisCluster(nodes);
		//LOGGER.debug("设置key1的值为1000");
		cluster.set("key1", "1000");
		
		//LOGGER.debug("从Redis中取key1的值");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
		try {
			int a = 1/0;
		} catch (Exception e) {
			e.printStackTrace();
			//LOGGER.error("系统发送异常", e);
		}
	}
	
	/**
	 * 单机版测试
	 * <p>Title: testSpringJedisSingle</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	@Test
	public void testSpringJedisCluster() {
//		jedisCluster
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}
}

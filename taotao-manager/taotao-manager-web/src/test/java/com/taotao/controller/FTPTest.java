package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {
	@Test
	public void testFtpClient() throws Exception {
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接，默认端口是21端口
		ftpClient.connect("192.168.70.128",21);
		//登录ftp服务器使用用户名密码登录
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ll\\Pictures\\4881dbef26504d79a8ad3639c593a263.jpg"));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/ww/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("hello.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
	
	@Test
	public void testFtpClient01() throws SocketException, IOException {
		//1、连接ftp服务器
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.70.128",21);
		//2、登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//3、读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ll\\Pictures\\4881dbef26504d79a8ad3639c593a263.jpg"));
		//4、上传文件
		//1）指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/ww/images");
		//2）指定文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		//第一个参数：文件在远程服务器的名称
		//第二个参数：文件流
		ftpClient.storeFile("hello.jpg", inputStream);
		//5、退出登录
		ftpClient.logout();
	}
	
	@Test
	public void testFtpUtils() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(new File(""));
		//2019/2/14表示对上传的图片根据日期进行分类（放在images下面的2019下面的2下面的14文件夹下（没有时自动新建S））
		FtpUtil.uploadFile("192.168.70.128", 21, "ftpuser", "ftpuser", "/home/ftpuser/ww/images", "/2019/02/14", "world.jpg", inputStream);
	}
}

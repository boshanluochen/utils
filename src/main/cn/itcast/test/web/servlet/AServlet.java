package cn.itcast.test.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

/**
 * 一般的Servlet都是只有一个请求处理方法
 * 登录：一个LoginServlet
 * 注册：一个RegistServlet
 * 修改密码...
 * 
 * 我们需要一个Servlet有多个请求处理方法
 * login()
 * regist()
 * updatePassword()
 * ...
 * 
 * ----------------
 * 
 * 让你的Servlet继承一个itcast的BaseServlet
 * 
 * 		BaseServlet:
 * 			1.可以有多个请求处理方法
 * 			2.简化了转发和重定向的代码
 * 
 * 		请求处理方法格式：
 * 			public String regist(HttpServletRequest request, HttpServletResponse response)
 * 				throws ServletException, IOException{
 * 			}
 * 		请求BaseServlet中的某个方法：
 * 		http://localhost:8080/tools/AServlet?method=regist
 * 
 */
//@WebServlet("/AServlet")(与web.xml中效果一样)
public class AServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String regist(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("regist()...");
		return "/index.jsp";//表示转发到index.jsp
//		return "f:/index.jsp";//f前缀表示forward,即转发(地址栏不会改变)
//		return "r:/index.jsp";//r前缀表示redirect,即重定向(地址栏会变过去)
//		return null; //不转发，也不重定向
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("login()...");
		return null;
	}
	

}

package cn.itcast.test.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

/**
 * һ���Servlet����ֻ��һ����������
 * ��¼��һ��LoginServlet
 * ע�᣺һ��RegistServlet
 * �޸�����...
 * 
 * ������Ҫһ��Servlet�ж����������
 * login()
 * regist()
 * updatePassword()
 * ...
 * 
 * ----------------
 * 
 * �����Servlet�̳�һ��itcast��BaseServlet
 * 
 * 		BaseServlet:
 * 			1.�����ж����������
 * 			2.����ת�����ض���Ĵ���
 * 
 * 		����������ʽ��
 * 			public String regist(HttpServletRequest request, HttpServletResponse response)
 * 				throws ServletException, IOException{
 * 			}
 * 		����BaseServlet�е�ĳ��������
 * 		http://localhost:8080/tools/AServlet?method=regist
 * 
 */
//@WebServlet("/AServlet")(��web.xml��Ч��һ��)
public class AServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String regist(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("regist()...");
		return "/index.jsp";//��ʾת����index.jsp
//		return "f:/index.jsp";//fǰ׺��ʾforward,��ת��(��ַ������ı�)
//		return "r:/index.jsp";//rǰ׺��ʾredirect,���ض���(��ַ������ȥ)
//		return null; //��ת����Ҳ���ض���
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("login()...");
		return null;
	}
	

}

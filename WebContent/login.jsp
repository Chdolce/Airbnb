<%@page import="df.zafu.AccountLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String account_email = request.getParameter("account_email");
	String account_password = request.getParameter("account_password");

	AccountLogin acc = new AccountLogin();

	int rs = acc.login(account_email, account_password);
	out.println(rs);

%>
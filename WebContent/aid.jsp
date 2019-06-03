<%@page import="df.zafu.ReserveAid"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String account_name = request.getParameter("account_name");

	ReserveAid aid = new ReserveAid();

	int rs = aid.reserveaid(account_name);
	out.println(rs);

%>
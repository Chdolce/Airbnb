<%@page import="df.zafu.AccountRegister"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String account_id = request.getParameter("account_id");
String account_name = request.getParameter("account_name");
String account_email = request.getParameter("account_email");
String account_phone = request.getParameter("account_phone");
String account_password = request.getParameter("account_password"); 
String account_image = request.getParameter("account_image");
AccountRegister reg = new AccountRegister();

int rs = reg.register(account_id, account_name,account_phone,account_email,account_password,account_image);
out.println(rs);

%>
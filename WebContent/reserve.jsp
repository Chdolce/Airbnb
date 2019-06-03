<%@page import="df.zafu.ReserveBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String guest_id = request.getParameter("guest_id");
String house_name = request.getParameter("house_name");
String start_time = request.getParameter("start_time");
String finish_time = request.getParameter("finish_time");
ReserveBean bean = new ReserveBean();

int rs = bean.reserve(guest_id, house_name, start_time, finish_time);
out.println(rs);

%>
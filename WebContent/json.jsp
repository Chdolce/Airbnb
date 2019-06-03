<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="df.zafu.RoomBean" %>
<%@page import="df.zafu.RoomInfo" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>


<%
RoomBean bean = new RoomBean();
List<RoomInfo> data =  bean.getAll();

//封装成json
out.println("[");
Iterator< RoomInfo > iter = data.iterator();
while( iter.hasNext() ){
	RoomInfo info = iter.next();
	out.print("{");
 	out.println("id:" + info.getAccount_id() + ", ");
	 out.print("name:" + info.getAccount_name() + ", ");
	 out.print("email:" + info.getAccount_email() + ", ");
	 out.print("phone:" + info.getAccount_phone()+ ", ");
	 out.print("password:" + info.getAccount_password() + " ");
	 out.println("}");
	 if( iter.hasNext() ){
		 out.print(",");
	 }
	
}
out.println("]");

%>


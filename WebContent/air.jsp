<%@page import="java.util.List"%>
<%@page import="df.zafu.RoomInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="df.zafu.RoomBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
RoomBean bean = new RoomBean();
List< RoomInfo> data =  bean.getAll();


//封装成json
out.println("[");
Iterator< RoomInfo > iter = data.iterator();
int i = 0;
while( iter.hasNext() ){
	RoomInfo info = iter.next();
	
	String yinHao = "\"";
 out.println("{");

  out.println("\"account_id\":\"" + info.getAccount_id() + "\",");
 
 out.println("\"account_name\":\"" + info.getAccount_name() + "\",");

 out.println("\"account_email\":\"" + info.getAccount_email() + "\",");

 out.println("\"account_phone\":\"" + info.getAccount_phone() + "\",");

 out.println("\"account_password\":\"" + info.getAccount_password()+ "\"," );
 
 out.println("\"account_image\":\"" + info.getAccount_image()+ "\"" );


 out.println("}");
 
 ++i;
 if( i < data.size() ){
	 out.println(",");
 }

	System.out.printf( "%s\n", info.toString()  );
	
}
out.println("]");

%>

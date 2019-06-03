<%@page import="java.util.Iterator"%>
<%@page import="df.zafu.ApartmentsInfo"%>
<%@page import="java.util.List"%>
<%@page import="df.zafu.ApartmentsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	
	ApartmentsBean bean = new ApartmentsBean();
	List<ApartmentsInfo> data = bean.getAll();
	
	//封装成json
	out.println("[");
	Iterator<ApartmentsInfo> iter = data.iterator();
	int i = 0;
	while(iter.hasNext()){
		ApartmentsInfo aInfo = iter.next();
		String yinHao = "\"";
		 out.println("{");
		 
		 out.println("\"hid\":\"" + aInfo.getHouse_id() + "\",");
		 out.println("\"title\":\"" + aInfo.getHouse_title() + "\",");
		 out.println("\"country\":\"" + aInfo.getCountry() + "\",");
		 out.println("\"province\":\"" + aInfo.getProvince()+ "\"," );
		 out.println("\"city\":\"" + aInfo.getCity()+ "\"," );
		 out.println("\"latitude\":\"" + aInfo.getLatitude()+ "\"," );
		 out.println("\"longtitude\":\"" + aInfo.getLongtitude()+ "\"," );
		 out.println("\"type\":\"" + aInfo.getType()+ "\"," );
		 out.println("\"guests_count\":\"" + aInfo.getGuests_count()+ "\"," );
		 out.println("\"info\":\"" + aInfo.getInfo()+ "\"," );
		 out.println("\"refer_price\":\"" + aInfo.getRefer_price()+ "\"," );
		 out.println("\"picture\":\""+aInfo.getPicture()+"\"");
		 out.println("}");
		 
		 ++i;
		 if( i < data.size() ){
			 out.println(",");
		 }

			System.out.printf( "%s\n", aInfo.toString()  );
			
		}
		out.println("]");
%>
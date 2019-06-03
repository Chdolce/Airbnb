<%@page import = "java.util.Iterator" %>
<%@page import="df.zafu.StoryInfo"%>
<%@page import="java.util.List"%>
<%@page import="df.zafu.StoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	StoryBean bean = new StoryBean();
	List<StoryInfo> data = bean.getAll();
	
	//封装成json
	out.println("[");
	Iterator<StoryInfo> iter = data.iterator();
	int i = 0;
	while(iter.hasNext()){
		StoryInfo aInfo = iter.next();
		String yinHao = "\"";
		 out.println("{");

		 out.println("\"storyid\":\"" + aInfo.getStoryid() + "\",");
		 out.println("\"storyplace\":\"" + aInfo.getStoryplace() + "\",");
		 out.println("\"storytitle\":\"" + aInfo.getStorytitle() + "\",");
		 out.println("\"storycontent\":\"" + aInfo.getStorycontent() + "\",");
		 out.println("\"storyimg\":\"" + aInfo.getStoryimg()+ "\"," );
		 out.println("\"authorid\":\"" + aInfo.getAuthorid()+ "\"," );
		 out.println("\"authorimg\":\"" + aInfo.getAuthorimg()+ "\"," );
		 out.println("\"authorname\":\"" + aInfo.getAuthorname()+ "\"" );
		 out.println("}");
		 
		 ++i;
		 if( i < data.size() ){
			 out.println(",");
		 }

			System.out.printf( "%s\n", aInfo.toString()  );
			
		}
		out.println("]");
%>
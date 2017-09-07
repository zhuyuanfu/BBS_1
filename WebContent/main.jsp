<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.Topic" %>
<%@ page import="db.TopicDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>论坛主页</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
</head>
<body>
<%
	out.println(session.getAttribute("userName"));
	out.println(session.getAttribute("password"));
	//out.println(session.getAttribute("car"));
	
	
%>

<div id = "head">head
欢迎<% out.println(session.getAttribute("userName")); %>，您的密码是<% out.println(session.getAttribute("password")); %>
<jsp:include page="header.jsp"></jsp:include>
</div>
<div id = "container">
	<div id = "content">this is content
		<table>
			<tr>
				<th>
					标题
				</th>
				<th>
					作者
				</th>
				<th>
					发布时间
				</th>
			</tr>
			
			
		</table>
	</div>
	<div id = "side">this is side
		
	</div>
</div>
<div id = "foot">foot
	<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>
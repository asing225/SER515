<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Quiz</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>
<div id="header"></div>
	<div>
		<a class="btn btn-primary" href="teacherLandingPage.jsp">Go Back</a>
	</div>
	<% 
	List<String> questions = (ArrayList<String>) request.getAttribute("Questions");
	List<String> answers = (ArrayList<String>) request.getAttribute("Answers");	
	%>
	
	<table class="table table=bordered table-hover col-md-4">
	<%
	for(int i=0; i<questions.size(); i++){
	%>
	<tr>
		<th>
		<%
			out.print(questions.get(i));
		%>
		</th>
		<td>
		<%
		out.print(answers.get(i));
		%>
		</td>
	</tr>
	<%
	}
	%>
	</table>
</body>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
</html>
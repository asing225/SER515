<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Quiz</title>
</head>
<body>
	<div>
		<a href="teacherLandingPage.jsp"><button>Go Back</button></a>
	</div>
	<% 
	List<String> questions = (ArrayList<String>) request.getAttribute("Questions");
	List<String> answers = (ArrayList<String>) request.getAttribute("Answers");	
	%>
	
	<table class="table table=bordered table-hover">
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
</html>
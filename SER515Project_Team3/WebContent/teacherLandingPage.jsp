<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Teacher Home</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>
	<div id="header"></div>
	<h1>Welcome <%= (String) session.getAttribute("firstname") %></h1>
	<div>
		<a class="btn btn-primary" href="teacher.html">Create Quiz</a>
	</div>
	<%
		List<String> quizNames = (ArrayList<String>) request.getSession().getAttribute("quizNames");
		List<Integer> quizIds = (ArrayList<Integer>) request.getSession().getAttribute("quizIds");
	%>
	<div class="container">
		<h2>Quiz</h2>
		<form method="GET">
			<table class="table table-bordered" id="quizTable">
			<% System.out.println(quizNames.size()); 
				int size = quizNames.size();
			%>
				<%
					if (quizNames.size() == 0) {
				%>
				<p align="center">No quizzes found!!</p>
				<%
					} else {
				%>

				<p align="center">You have following quizzes for the course!!</p>
				<%
					for (int i = 0; i < size; i++) {
				%>
				<tr>
					<td><a href="quiz?id=<%=quizIds.get(i)%>"><%=quizNames.get(i)%></a></td>
				</tr>

				<%
					}
					}
				%>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
</html>


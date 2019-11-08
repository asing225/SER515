<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Teacher Home</title>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
</head>
<body>
	<div id="header"></div>
	<h1>Welcome Teacher</h1>
	<div>
		<a class="btn" href="teacher.html">Create Quiz</a>
	</div>
	<%
		List<String> quizNames = (ArrayList<String>) request.getAttribute("quizNames");
		List<Integer> quizIds = (ArrayList<Integer>) request.getAttribute("quizIds");
	%>
	<div class="container">
		<h2>Quiz</h2>
		<form method="GET">
			<table id="quizTable" class="table table-bordered">
				<%
					if (quizNames.size() == 0) {
				%>
				<p align="center">No quizzes found!!</p>
				<%
					} else {
				%>

				<p align="center">You have following quizzes for the course!!</p>
				<%
					for (int i = 0; i < quizNames.size(); i++) {
				%>
				<tr>
					<td><a id="quizURLRow<%=i%>"
						href="quiz/?id=<%=quizIds.get(i)%>"><%=quizNames.get(i)%></a></td>
				</tr>

				<%
					}
					}
				%>
			</table>
		</form>
	</div>
</body>
</html>


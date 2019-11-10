<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.asu.ser515.model.Quiz"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Student Home</title>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
</head>
<body>
	<div id="header"></div>

	<%
		List<Quiz> listofQuiz = (ArrayList<Quiz>) request.getSession().getAttribute("ListQuiz");
		List<String> quizNames = new ArrayList();
		List<Integer> quizIds = new ArrayList();
		//List<Integer> quizIds = (ArrayList<Integer>) request.getSession().getAttribute("quizIds");
		for (int i = 0; i < listofQuiz.size(); i++) {
			Quiz quiz = listofQuiz.get(i);
			quizNames.add(quiz.getQuizname());
			quizIds.add(quiz.getQuiz_id());
		}
	%>
	<div class="container">
		<h2>Quiz</h2>
		<form method="GET">
			<table id="quizTable" class="table table-bordered">
				<%
					session.setAttribute("action", "load");
					if (quizNames.size() == 0) {
				%>
				<p align="center">
				<h3>No quizzes found!!</h3>
				</p>
				<%
					} else {
				%>

				<p align="center">
				<h3>
					You have following quizzes for the course!! <br>
				</h3>
				</p>
				<%
					for (int i = 0; i < quizNames.size(); i++) {
				%>
				<tr>
					<td><a id="quizURLRow<%=i%>"
						href="student?id=<%=quizIds.get(i)%>"><%=quizNames.get(i)%></a></td>
				</tr>

				<%
					}
					}
				%>
			</table>
		</form>
		<style>
a {
	position: relative;
	display: inline-block;
	margin-top: 20px;
}

a[title]:hover:after {
	content: attr(title);
	color: #ff0509;
	position: absolute;
	top: -100%;
	left: 0;
}
</style>
	</div>
</body>
</html>
<script type="text/javascript">
	// checking if the quiz has been answered or not.
	$(document)
			.ready(
					function() {
						var rowCount = $('#quizTable tr').length;
						for (var i = 0; i < rowCount; i++) {
							if (document.getElementById("quizStatus" + i).innerHTML == "Answered") {
								document.getElementById("quizURLRow" + i).href = "javascript: void(0)";
								document.getElementById("quizURLRow" + i).title = "Quiz already answered";
							}
						}
					});
</script>


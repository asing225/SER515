<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Admin Home</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>
	<div id="header"></div>
	<h1>
		Welcome
		<%=(String) session.getAttribute("firstname")%></h1>

	<%
		List<String> userDetail = (ArrayList<String>) request.getSession().getAttribute("userDetails");
	%>
	<div class="container">
		<h2>Quiz</h2>
		<form method="GET">
			<table class="table table-bordered" id="quizTable">
				<%
					int size = userDetail.size();
					if (size == 0) {
				%>
				<p align="center">No Users found!!</p>
				<%
					} else {
				%>

				<p align="center">You have following Users !</p>
				<%
					for (int i = 0; i < size; i++) {
				%>
				<tr>
					<td><a ><%=userDetail.get(i)%></a></td>
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


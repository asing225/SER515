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
	List<String> userId = (ArrayList<String>) request.getSession().getAttribute("userId");
	List<String> firstName = (ArrayList<String>) request.getSession().getAttribute("firstNameList");
	List<String> lastName = (ArrayList<String>) request.getSession().getAttribute("lastNameList");
	List<String> userType = (ArrayList<String>) request.getSession().getAttribute("userTypeList");
	List<String> UserNameList = (ArrayList<String>) request.getSession().getAttribute("userNameList");
	List<String> passwordList = (ArrayList<String>) request.getSession().getAttribute("passwordList");
	List<String> status = (ArrayList<String>) request.getSession().getAttribute("status");
	%>
	<div class="container">
		<table class="table table-bordered" id="quizTable">
			<%
				int size = firstName.size();
				if (size == 0) {
			%>
			<p align="center">No Users found!!</p>
			<%
				} else {
			%>

			<p align="center">You have following Users !</p>
			<tr>
				<th>User ID</th>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>UserType</th>
				<th>UserName</th>
				<th>Password</th>
				<th>Status</th>
			</tr>
			<%
				for (int i = 0; i < size; i++) {
			%>
			<tr>
				<td><a><%=userId.get(i)%></a></td>
				<td><a><%=firstName.get(i)%></a></td>
				<td><a><%=lastName.get(i)%></a></td>
				<td><a><%=userType.get(i)%></a></td>
				<td><a><%=UserNameList.get(i)%></a></td>
				<td><a><%=passwordList.get(i)%></a></td>
				<td>
					<form method="POST" action="admin">
						<% if("N".equals(status.get(i))){ %>
						<input name="status" value="<%=userId.get(i)%>" hidden /> <input
							type="submit" value="Activate" class="btn btn-success" />
						<%}
					else if("Y".equals(status.get(i))){%>
						<input name="status" value="<%=userId.get(i)%>" hidden /> <input
							type="submit" value="De-Activate" class="btn btn-danger" />
						<%}%>
					</form>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>
</body>
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/admin.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
</html>


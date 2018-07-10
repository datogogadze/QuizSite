<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="User.User"%>
<%@ page import="DAO.MessageDao"%>
<%@ page import="Message.TextMessage"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="DAO.UserDao"%>
<%@ page import="Achievement.Achievement"%>
<%@ page import="DAO.DoneQuizDao"%>
<%@ page import="Quiz.DoneQuiz"%>
<%@ page import="DAO.QuizDao"%>
<%@ page import="DAO.QuestionDao"%>
<%@ page import="DAO.AnswerDao"%>
<%@ page import="Quiz.Quiz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage users</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs" id="myTab">
			<li><a href="mainPage.jsp">Home</a></li>
			<li><a href="createQuiz.jsp">Create a quiz</a></li>
			<li><a href="takeQuiz.jsp">Take a quiz</a></li>
			<li><a href="manageQuizes.jsp">Manage quizes</a></li>
			<li><a href="manageUsers.jsp">Manage Users</a></li>
			<form action="SearchServlet" method="post">
				<div class="input-group">
					<input type="text" class="form-control" name = "username" placeholder="Search User">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
		</ul>


		<%
			UserDao us = new UserDao();
		%>

		<div class="container">
			<h2>All users</h2>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Username</th>
							<th>Type</th>
							<th>Change status</th>
							<th>Delete user</th>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<User> allUsers = us.getAllUsers();
						%>
						<%
							for (User u : allUsers) {
								String changeStatusLink = "UserModifyServlet?changeStatus=" + u.getId();
								String deleteLink = "UserModifyServlet?delete=" + u.getId();
								String userPageLink = "SearchServlet?username=" + u.getUserName();
						%>
						<tr>
							<td><%=u.getId()%></td>
							<td><a href="<%=userPageLink%>"> <%=u.getUserName()%></a></td>
							<td><a href="#"> <%
							 	if (u.getType() == 1)
 									%> Admin <%
 								else
 									%> User
							</a></td>
							<td><a href="<%=changeStatusLink%>">Change Status</a></td>
							<td><a href="<%=deleteLink%>">Delete User</a></td>
						</tr>
						<%
							}
						%>
					
				</table>
			</div>
		</div>
		<%
			us.closeCon();
		%>
	
</body>
</html>
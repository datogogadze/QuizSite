<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="User.User"%>
<%@ page import="DAO.MessageDao"%>
<%@ page import="Message.TextMessage"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
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
<title>Choose a quiz.</title>
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
			<%
				User user = (User) session.getAttribute("user");
				int type = user.getType();
			%>
			<li><a href="mainPage.jsp">Home</a></li>
			<li><a href="createQuiz.jsp">Create a quiz</a></li>
			<li><a href="takeQuiz.jsp">Take a quiz</a></li>
						<li><a href="topQuiz.jsp">Top quizzes</a></li>
			
			<%
				if (type == 1) {
			%>
			<li><a href="manageQuizes.jsp">Manage quizes</a></li>
			<li><a href="manageUsers.jsp">Manage Users</a></li>
			<%
				}
			%>

			<form action="SearchServlet" method="post">
				<div class="input-group">
					<input type="text" class="form-control" name="username"
						placeholder="Search User">
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
			AnswerDao an = new AnswerDao();
			QuestionDao qp = new QuestionDao(an);
			QuizDao complete = new QuizDao(qp, us);
			DoneQuizDao ddd = new DoneQuizDao();
			HashMap<Integer, Integer>  bla = ddd.getTop10();
			
		%>


		<div class="container">
			<h2>Top quizes</h2>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Quiz Name</th>
							<th>Author Username</th>
							<th>Best Score</th>
							<th> number taken </th>
							<th>Challenge</th>
							
						</tr>
					</thead>
					<tbody>
						<%
							for (int i : bla.keySet()) {
								Quiz cur = complete.getQuiz(i);
								String userPageLink = "SearchServlet?username=" + us.getUsernameByID(cur.getQuizAuthor());
								String quizPage = "quizDescription.jsp?quizid=" + i;
						%>
						<tr>
							<td><%=i%></td>
							<td><a href="<%=quizPage%>"><%=complete.getQuiz(i).getQuizName()%></a></td>
							<td><a href="<%=userPageLink%>"> <%=us.getUsernameByID(cur.getQuizAuthor())%></a></td>
							<td><%=complete.getMaxScore(i)%></td>
							<td><%=bla.get(i) %></td>
							<td>
							<%String cm = "ChallengeServlet?quizid="+i; %>
							<form method="post" action="<%=cm%>">
							<div class="form-group">
									<select class="form-control" id="select" name = "select">
										<%
											for (int j = 0; j < user.getFriends().size(); j++) {
										%>
										<option value="<%=user.getFriends().get(j)%>"><%=user.getFriends().get(j)%></option>
										<%
											}
										%>
									</select>
								</div> <input class="next-btn " type="submit" value="Challenge Friend">
								</form>
							</td>
							

							<%
								}
							%>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<%
			us.closeCon();
			an.closeCon();
			qp.closeCon();
			complete.closeCon();
			ddd.closeCon();
		%>
	
</body>
</html>
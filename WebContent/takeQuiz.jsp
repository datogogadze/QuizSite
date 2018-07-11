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
<title>Take a quiz</title>
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
				session.setAttribute("score", 0);
			%>
			<li class="active"><a href="mainPage.jsp">Home</a></li>
			<li><a href="createQuiz.jsp">Create a quiz</a></li>
			<li><a href="takeQuiz.jsp">Take a quiz</a></li>
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
			ArrayList<Quiz> bla = complete.getAllQuizes();
		%>


		<div class="container">
			<h2>All Quizes</h2>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Quiz Name</th>
							<th>Author Username</th>
							<th>Best Score</th>
							<th>Challenge</th>
							<th>Button</th>
						</tr>
					</thead>
					<tbody>

						<tr>
							<%
								ArrayList<Quiz> all = complete.getAllQuizes();
								for (int i = 0; i < all.size(); i++) {
							%>
						
						<tr>
							<td><%=all.get(i).getQuizId()%></td>
							<td><a
								href="<%="quizDescription.jsp?quizId=" + all.get(i).getQuizId()%>"><%=all.get(i).getQuizName()%></a></td>
							<%
								String link = "SearchServlet?username=" + us.getUserByID(all.get(i).getQuizAuthor());
							%>
							<td><a href="<%=link%>"><%=user.getName()%></a>
							<td><%=complete.getMaxScore(all.get(i).getQuizId())%></td>
							<td>
							<%String cm = "ChallengeServlet?quizid="+all.get(i).getQuizId(); %>
								<form method="post" action="<%=cm%>">
									<div class="form-group">
										<select class="form-control" name="select" id="select">
											<%
												for (int j = 0; j < user.getFriends().size(); j++) {
											%>
											<option value="<%=user.getFriends().get(j)%>"><%=user.getFriends().get(j)%></option>
											<%
												}
											%>
										</select>
									</div>

									<input class="next-btn " type="submit" value="Challenge Friend">
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
</body>
</html>
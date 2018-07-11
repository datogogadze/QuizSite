<%@page import="Questions.PictureResponse"%>
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
<%@ page import="Questions.Question"%>
<%@ page import="java.util.Date"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			<h2>All Quizzes</h2>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Quiz Name</th>
							<th>Author Username</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<%
								int x = Integer.parseInt(request.getParameter("quizId"));
								int q = Integer.parseInt(request.getParameter("question"));
								if (q == 0)
									session.setAttribute("startdate" + x, new Date());
								Quiz quiz = (Quiz) session.getAttribute("quiz");
							%>
							<td><%=complete.getQuiz(x).getQuizName()%></td>
							<td><%=us.getUsernameByID(complete.getQuiz(x).getQuizAuthor())%></td>
							<td><%=complete.getQuiz(x).getQuizDescription()%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<%
			ArrayList<Question> qus = qp.getAllQuestions(x);
			Question quest = qus.get(q);
			String k = quest.getQuestion();
			String l = "answerQuestionServlet?quizId=" + complete.getQuiz(x).getQuizId() + "&question=" + (q + 1);
		%>
		<form method="post" action="<%=l%>">
			<div class="panel panel-default">
				<div class="panel-heading">
					Question number
					<%=q + 1%>
				</div>
				<div class="panel-body"><%=k%>
				</div>
				<br>
				<%
					int quetype = quest.getQuestionType();
					if (quetype == 3) {
				%>
				<div class="funkyradio">
					<div class="funkyradio-default">
						<input type="radio" name="response" id="radio1"
							value="<%=quest.getAnswers().get(0)%>" /> <label for="radio1"><%=quest.getAnswers().get(0)%></label>
					</div>
					<div class="funkyradio-primary">
						<input type="radio" name="response" id="radio2"
							value="<%=quest.getAnswers().get(1)%>" /> <label for="radio2"><%=quest.getAnswers().get(1)%></label>
					</div>
					<div class="funkyradio-success">
						<input type="radio" name="response" id="radio3"
							value="<%=quest.getAnswers().get(2)%>" /> <label for="radio3"><%=quest.getAnswers().get(2)%></label>
					</div>
					<div class="funkyradio-danger">
						<input type="radio" name="response" id="radio4"
							value="<%=quest.getAnswers().get(3)%>" /> <label for="radio4"><%=quest.getAnswers().get(3)%></label>
					</div>
					<%
						}
					%>
					<%
						if (quetype == 1) {
					%><div class="form-group">
						<label for="response">Answer</label> <input type="text"
							class="form-control" name="response">
					</div>
					<%
						}
					%>
				</div>
				<%
					if (quetype == 2) {
				%>
				<div class="form-group">
					<label for="fill">Answer</label> <input type="text"
						class="form-control" name="response">
				</div>
				<%
					}
				%>
				<%
					if (quetype == 4) {
					}
				%>

				<%
					if (quest.getQuestionN() == qus.size()) {
				%>
				<input class="next-btn " type="submit" value="complete">
				<%
					} else {
				%>
				<input class="next-btn " type="submit" value="next">
				<%
					}
				%>
			</div>

		</form>
</body>
</html>
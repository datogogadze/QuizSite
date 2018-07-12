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
<%
	User user = (User) session.getAttribute("foundUser");
	String username = user.getUserName();
%>
<title><%=user.getUserName()%>'s page</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>

<body>

	<div class="container">
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="mainPage.jsp">Home</a></li>
			<li><a href="createQuiz.jsp">Create a quiz</a></li>
			<li><a href="takeQuiz.jsp">Take a quiz</a></li>
						<li><a href="topQuiz.jsp">Top quizzes</a></li>
			
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
	</div>

	<hr>
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<h1><%=user.getUserName()%></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<!--left col-->

				<ul class="list-group">
					<li class="list-group-item text-muted">User Info</li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>First
								name</strong></span> <%=user.getName()%></li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Last
								name</strong></span><%=user.getLastName()%></li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>E-mail</strong></span><%=user.getMail()%></li>
				</ul>
			</div>
			<!--/col-3-->




			<div class="col-sm-4">
				<form method="post" action="addServlet">
					<button type="submit">Add Friend</button>
				</form>
				<div class="form-group">
					<label for="comment">Send Note:</label>
					<form action="MessageServlet" method="post">
						<textarea name="message" class="form-control" rows="5"
							id="comment"></textarea>
						<button type="submit" class="btn btn-primary active">Send</button>
					</form>

				</div>
			</div>

			<!--/tab-content-->


			<div class="col-sm-5">

				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#home" data-toggle="tab">User's
							quizes</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="home">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>#</th>
										<th>Quiz name</th>
									</tr>
								</thead>
								<%
									UserDao us = new UserDao();
									AnswerDao an = new AnswerDao();
									QuestionDao qp = new QuestionDao(an);
									QuizDao complete = new QuizDao(qp, us);
									ArrayList<Quiz> bla = complete.getAllQuizes(user.getId());
									for (int i = 0; i < bla.size(); i++) {
										String quizName = bla.get(i).getQuizName();
								%>
								<tr>
									<td><%=i + 1%></td>
									<td><%=quizName%></td>

								</tr>
								<%
									}
								%>

							</table>
							<hr>
							<div class="row">
								<div class="col-md-4 col-md-offset-4 text-center">
									<ul class="pagination" id="myPager"></ul>
								</div>
							</div>
						</div>
						<!--/table-resp-->
						<hr>

					</div>




				</div>
			</div>
		</div>
		<!--/col-3-->

		<div class="col-sm-6">

			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#home" data-toggle="tab">Achievements</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Achievement Name</th>
								</tr>
							</thead>
							<tbody id="items">
								<%
									ArrayList<Achievement> achiev = user.getAchievements();

									for (int i = 0; i < achiev.size(); i++) {
								%>
								<tr>
									<td><%=i + 1%></td>
									<td><%=achiev.get(i).getAchievementName()%></td>

								</tr>
								<%
									}
								%>

							</tbody>
						</table>
						<hr>
						<div class="row">
							<div class="col-md-4 col-md-offset-4 text-center">
								<ul class="pagination" id="myPager"></ul>
							</div>
						</div>
					</div>
					<!--/table-resp-->
					<hr>
				</div>
			</div>
		</div>




		<div class="col-sm-6">

			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#home" data-toggle="tab">History</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Quiz name</th>
									<th>Score</th>
								</tr>
							</thead>
							<tbody id="items">

								<%
									DoneQuizDao complete1 = new DoneQuizDao();
									ArrayList<DoneQuiz> bla1 = complete1.getLast(user.getId());
									for (int i = 0; i < bla1.size(); i++) {
										int id = bla1.get(i).getQuizId();
										String quizName = complete1.getQuizName(id);
										int score = bla1.get(i).getScore();
								%>
								<tr>
									<td><%=i + 1%></td>
									<td><%=quizName%></td>
									<td><%=score%></td>

								</tr>
								<%
									}
								%>
							</tbody>

						</table>
						<hr>
						<div class="row">
							<div class="col-md-4 col-md-offset-4 text-center">
								<ul class="pagination" id="myPager"></ul>
							</div>
						</div>
					</div>
					<!--/table-resp-->
					<hr>
				</div>

			</div>
		</div>
	</div>
	<%
		us.closeCon();
		an.closeCon();
		qp.closeCon();
		complete.closeCon();
		complete1.closeCon();
	%>
	<!--/col-9-->
</body>
</html>
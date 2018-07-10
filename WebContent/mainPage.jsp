<%@page import="DAO.AnswerDao"%>
<%@page import="DAO.QuestionDao"%>
<%@page import="DAO.ChallengeDao"%>
<%@page import="Message.Challenge"%>
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
<%@ page import="DAO.FriendDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) session.getAttribute("user");
	String username = user.getUserName();
%>

<%
	String notFound = (String) session.getAttribute("NotFound");
	if (notFound.equals("NotFound")) {
		out.print("<script> alert('User not found.'); </script>");
		session.setAttribute("NotFound", "Found");
	}
%>
<title><%="Welcome " + username%></title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
			<%
				int type = user.getType();
			%>
			<li class="active"><a href="mainPage.jsp">Home</a></li>
			<li><a href="createQuiz.jsp">Create a quiz</a></li>
			<li><a href="takeQuiz.jsp">Take a quiz</a></li>
			<%
				if (type == 1) {
			%>
			<li><a href="manageQuizes.jsp">Manage quizzes</a></li>
			<li><a href="manageUsers.jsp">Manage Users</a></li>
			<%
				}
			%>
		</ul>

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

	</div>
	<hr>

	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<h1><%=username%></h1>
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
					<li class="list-group-item text-right"><span class="pull-left"><strong>E-mail</strong></span>
						<%=user.getMail()%></li>



				</ul>

			</div>
			<!--/col-3-->
			<div class="col-sm-4">

				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#home" data-toggle="tab">Friends</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="home">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>#</th>
									</tr>
								</thead>
								<tbody id="items">
									<%
										for (int i = 0; i < user.getFriends().size(); i++) {
									%>
									<tr>
										<td><%=i + 1%></td>
										<td><%=user.getFriends().get(i)%></td>

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
					<!--/tab-pane-->
					<div class="tab-pane" id="messages"></div>
					<!--/tab-pane-->
					<div class="tab-pane" id="settings">


						<hr>

					</div>

				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-content-->

			<div class="col-sm-4">

				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#home" data-toggle="tab">Notes</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="home">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Username</th>
										<th>Note</th>
									</tr>
								</thead>
								<tbody id="items">
									<%
										MessageDao m = new MessageDao();
										UserDao pr = new UserDao();
										AnswerDao an = new AnswerDao();
										QuestionDao qu = new QuestionDao(an);
										QuizDao qd = new QuizDao(qu, pr);
										ChallengeDao cd = new ChallengeDao();
										ArrayList<TextMessage> messages = m.getNotesByReciever(user.getId());
										FriendDao j = new FriendDao();
										ArrayList<Integer> arrr = j.getRequests(pr.getUserId(user.getUserName()));
										ArrayList<Challenge> challenges = cd.getChallenges(user.getId());
									%>

									<%
										for (int i = messages.size() - 1; i >= 0; i--) {
									%>
									<tr>
										<td><%=pr.getUserByID(messages.get(i).getSenderID()).getUserName()%></td>
										<td><%=messages.get(i).getText()%></td>

									</tr>

									<%
										}
										for (int i = arrr.size() - 1; i >= 0; i--) {
											String link = "AcceptServlet?senderId=" + arrr.get(i);
									%>
									<tr>
										<td><%=pr.getUserByID(arrr.get(i))%></td>
										<td><a href="<%=link%>">ACCEPT FRIEND REQUEST</a></td>

									</tr>
									<%
										}
										for (int i = 0; i < challenges.size(); i++) {
											String quizLink = "AcceptChallengeServlet?quizid=" + challenges.get(i).getQuizId() + "&senderId=" + challenges.get(i).getSenderID();
									%>
									<tr>
										<td><%=pr.getUsernameByID(challenges.get(i).getSenderID())%></td>
										<td><a href="<%=quizLink%>">Challenge in <%=qd.getQuiz(challenges.get(i).getQuizId()).getQuizName()%></a></td>
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
					<!--/tab-pane-->
					<div class="tab-pane" id="messages"></div>
					<!--/tab-pane-->
					<div class="tab-pane" id="settings">


						<hr>

					</div>

				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-content-->

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
					<!--/tab-pane-->
					<div class="tab-pane" id="messages"></div>
					<!--/tab-pane-->
					<div class="tab-pane" id="settings">


						<hr>

					</div>

				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-content-->
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
										
										ArrayList<DoneQuiz> bla = qd.getCompleteQuizes(user.getId());

										for (int i = bla.size()-1; i >= 0; i--) {
											int id = bla.get(i).getQuizId();
											String quizName = qd.getQuiz(id).getQuizName();
											String quizPage = "quizDescription.jsp?id=" + bla.get(i).getQuizId();
									%>
									<tr>
										<td><%=bla.size()-i%></td>
										<td><a href="<%=quizPage%>"><%=quizName%></a></td>
										<td><%=bla.get(i).getScore()%></td>

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
					<!--/tab-pane-->
					<div class="tab-pane" id="messages"></div>
					<!--/tab-pane-->
					<div class="tab-pane" id="settings">


						<hr>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		qu.closeCon();
		an.closeCon();
		j.closeCon();
		m.closeCon();
		pr.closeCon();
		qd.closeCon();
		cd.closeCon();
	%>
	<!--/row-->
</body>

</html>
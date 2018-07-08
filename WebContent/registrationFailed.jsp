<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="User.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/grid.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Registration failed</title>
</head>
<body>

	<div class="row background">
		<div class="col span-1-of-4 right-side">
			<h1 class="registration-heading">Try again.</h1>
		</div>
		
		<div class="registration-form-holder">
			<form action="RegistrationServlet" method="post" class="registration-form">
				<div class="row">									
					<h2>Fill the form</h2>
				</div>

				<div class="row">
					<div class="col span-1-of-3">
						<h3>First name</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="text" name="first_name" placeholder="first name"
							required>
					</div>
				</div>

				<div class="row">
					<div class="col span-1-of-3">
						<h3>Last name</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="text" name="last_name" placeholder="last name"
							required>
					</div>
				</div>

				<div class="row">
					<div class="col span-1-of-3">
						<h3>User name</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="text" name="username" placeholder="username" required>
					</div>
				</div>

				<div class="row">
					<div class="col span-1-of-3">
						<h3>Email</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="text" name="email" placeholder="email" required>
					</div>
				</div>

				<div class="row">
					<div class="col span-1-of-3 ">
						<h3>Password</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="password" name="password" placeholder="password"
							required>
					</div>
				</div>

				<div class="row">
					<div class="col span-1-of-3">
						<h3>Confirm password</h3>
					</div>
					<div class="col span-2-of-3 field-holder">
						<input type="password" name="comfirm_password"
							placeholder="comfirm password" required>
					</div>
				</div>

				<div class="row">
					<input class="registration-btn " type="submit" value="submit">
				</div>
				<div class="row">
					<a href="index.jsp" class="form-registration">Back to main</a>
				</div>
			</form>

		</div>
	</div>
	<script src="query1.js"></script>
</body>
</html>
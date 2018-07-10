<%@ page import="User.User"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create a quiz.</title>
<link rel="stylesheet" type="text/css" href="vendors/css/grid.css">
<link rel="stylesheet" type="text/css"
	href="vendors/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="vendors/css/style.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic'
	rel='stylesheet' type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>

		<%
			User user = (User)request.getSession().getAttribute("user");
		%>
		<script>
			let quiz = {
				"title" : "",
				"description" : "",
				"questions" : []
			}
		</script>
		<div class="row quiz-container">
			<div class="row quiz-background-part-one">
				<h1 class="quiz-type-header-property">Create Quiz</h1>
			</div>

			<div class="row quiz-background-part-two">

				<div class="alert-error">
					<h1 class="done-error">number of questions must be at least 10
						and less than 30!</h1>
					<button class="normal done-error-btn">continue</button>
				</div>

				<div class="question-container">
					<div class="question-add-or-done">
						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header">Title:</h1>
							</div>
							<div class="col span-2-of-3 input-holder">
								<input class="quiz-property-title-input"
									placeholder="enter title here">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-description-header">Description:</h1>
							</div>
							<div class="col span-2-of-3 textarea-holder">
								<textarea class="quiz-property-description-textarea"
									placeholder="enter text here"></textarea>
							</div>
						</div>

						<div class="row filler-questions">
							<div class="col span-1-of-4 left-icon">
								<i class="ion-ios-arrow-left"></i>
							</div>


							<div class="col span-2-of-4 questions-display">

								<div class="row attension">
									<h3 class="must-do">
										<b>note: </b> every quiz must contain at least 10 questions!
									</h3>
								</div>

								<div class="QR-diplay">
									<div class="row ">
										<div class="col span-1-of-6">
											<h1 class="display-text">question:</h1>
										</div>
										<div class="col span-5-of-6 text-border-box display-row">
											<h1 class="QR-display-question"></h1>
										</div>
									</div>



									<div class="row">
										<div class="col span-1-of-6">
											<h1 class="display-text">answers:</h1>
										</div>

										<div class="col span-5-of-6">
											<div class="row">
												<div class="col span-1-of-2 text-border-box display-row">
													<h1 class="QR-display-answer1"></h1>
												</div>
												<div
													class="col span-1-of-2 text-border-box display-row answer2">
													<h1 class="QR-display-answer2"></h1>
												</div>
											</div>




										</div>

									</div>

									<div class="row">
										<div class="question-count">
											<span class="question-count-text">question</span>
										</div>
										<button class="edit-QR">edit</button>
									</div>
								</div>

								<div class="MC-diplay">
									<div class="row ">
										<div class="col span-1-of-6">
											<h1 class="display-text">question:</h1>
										</div>
										<div class="col span-5-of-6 text-border-box display-row">
											<h1 class="MC-display-question"></h1>
										</div>
									</div>



									<div class="row">
										<div class="col span-1-of-6">
											<h1 class="display-text">right:</h1>
										</div>

										<div class="col span-5-of-6">
											<div class="row">
												<div class="col span-1-of-4"></div>
												<div class="col span-1-of-2 text-border-box display-row">
													<h1 class="MC-display-answer1"></h1>
												</div>
											</div>
										</div>
									</div>



									<div class="row">
										<div class="col span-1-of-6">
											<h1 class="display-text gama">wrong:</h1>
										</div>

										<div class="col span-5-of-6">
											<div class="row">
												<div class="col span-1-of-2 text-border-box display-row">
													<h1 class="MC-display-answer2"></h1>
												</div>
												<div
													class="col span-1-of-2 text-border-box display-row answer3">
													<h1 class="MC-display-answer3"></h1>
												</div>
											</div>

											<div class="row">
												<div class="col span-1-of-4"></div>
												<div class="col span-1-of-2 text-border-box display-row">
													<h1 class="MC-display-answer4"></h1>
												</div>
											</div>



										</div>
										<!--                -->
									</div>


									<div class="row">
										<div class="question-count">
											<span class="question-count-text">question</span>
										</div>
										<button class="edit-MC">edit</button>
									</div>
								</div>

								<!--                            ------------------------------------>

								<div class="FG-diplay">
									<div class="row ">
										<div class="col span-1-of-6">
											<h1 class="display-text">before blank:</h1>
										</div>
										<div class="col span-5-of-6 display-row">
											<h1 class="FG-display-BB"></h1>
										</div>
									</div>



									<div class="row">
										<div class="col span-1-of-6">
											<h1 class="display-text">blank:</h1>
										</div>
										<div class="col span-5-of-6 display-row  dddb">
											<h1 class="FG-display-B"></h1>
										</div>
									</div>

									<div class="row">
										<div class="col span-1-of-6">
											<h1 class="display-text">after blank:</h1>
										</div>
										<div class="col span-5-of-6  display-row">
											<h1 class="FG-display-AB"></h1>
										</div>
									</div>


									<div class="row">
										<div class="question-count">
											<span class="question-count-text">question</span>
										</div>
										<button class="edit-FG">edit</button>
									</div>
								</div>





								<div class="PR-diplay">
									<div class="row ">
										<div class="col span-1-of-2 PR-image">
											<img src="" alt="img not found" width="80%" class="PR-image">
										</div>
										<div class="col span-1-of-2">

											<div class="row">
												<h1 class="display-text">answer:</h1>
											</div>

											<div class="row display-row">
												<h1 class="PR-display-answer"></h1>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="question-count">
											<span class="question-count-text">question</span>
										</div>
										<button class="edit-PR">edit</button>
									</div>
								</div>


							</div>




							<div class="col span-1-of-4 right-icon">
								<i class="ion-ios-arrow-right right-icon-imena"></i>
							</div>
						</div>
						<!--                    add question doe quiz buttons    -->
						<div class="row">
							<div class="col span-1-of-2">
								<button class="normal add-question">Add Question</button>
							</div>

							<div class="col span-1-of-2">
								<button class="normal done">Done</button>
							</div>
						</div>

					</div>


					<div class="row question-type-container">
						<button class="normal question-type QR">Question-Response</button>
						<button class="normal question-type FG">Fill in the Blank</button>
						<button class="normal question-type MC">Multiple Choice</button>
						<button class="normal question-type PR">Picture-Response
							Questions</button>
					</div>



					<!--         question                response-->
					<div class="Question-Response-container">
						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-question-title">Your
									Question:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input QR-question-placeholder"
									placeholder="enter question here">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-answer-title">Right
									Answers:</h1>
							</div>
							<div class="col span-2-of-3">
								<input
									class="quiz-property-title-input QR-answer-placeholder first-answer"
									placeholder="first answer"> <input
									class="quiz-property-title-input QR-answer-placeholder second-answer"
									placeholder="second answer">
							</div>
						</div>
						<div class="row">
							<button class="normal QR-done">Done</button>
							<button class="normal QR-done-edit">edit</button>
						</div>



					</div>






					<!--         picture                response-->
					<div class="Picture-Response-container">
						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-question-title">image
									source:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input PR-question-placeholder"
									placeholder="enter url here">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-answer-title">Right
									Answer:</h1>
							</div>
							<div class="col span-2-of-3">
								<input
									class="quiz-property-title-input QR-answer-placeholder PR-answer"
									placeholder="answer">
							</div>
						</div>
						<div class="row">
							<button class="normal PR-done">Done</button>
							<button class="normal PR-done-edit">edit</button>
						</div>



					</div>


					<!--         multiple choise-->
					<div class="multiple-choice-container">
						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-question-title">Your
									Question:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input MC-question-placeholder"
									placeholder="enter question here">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-answer-title">Right
									Answer:</h1>
							</div>
							<div class="col span-2-of-3">
								<input
									class="quiz-property-title-input QR-answer-placeholder  mc-first-answer"
									placeholder="first answer">
							</div>
						</div>

						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header QR-answer-title">Wrong
									Answers:</h1>
							</div>
							<div class="col span-2-of-3">

								<input
									class="quiz-property-title-input QR-answer-placeholder  mc-second-answer"
									placeholder="second answer"> <input
									class="quiz-property-title-input QR-answer-placeholder  mc-third-answer"
									placeholder="third answer"> <input
									class="quiz-property-title-input QR-answer-placeholder mc-fourth-answer"
									placeholder="fourth answer">

							</div>
						</div>

						<div class="row">
							<button class="normal MC-done">Done</button>
							<button class="normal MC-done-edit">edit</button>
						</div>
					</div>









					<!--         fill in the blank-->
					<div class="fill-in-the-blank-container">
						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header FG-question-title">before
									blank:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input FG-BB-placeholder"
									placeholder="before blank">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header FG-answer-title">Blank:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input FG-B-placeholder"
									placeholder="blank">
							</div>
						</div>


						<div class="row">
							<div class="col span-1-of-3">
								<h1 class="quiz-property-title-header FG-question-title">After
									Blank:</h1>
							</div>
							<div class="col span-2-of-3">
								<input class="quiz-property-title-input FG-AB-placeholder"
									placeholder="after blank">
							</div>
						</div>



						<div class="row">
							<button class="normal FG-done">Done</button>
							<button class="normal FG-done-edit">edit</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script>
			let currentQuestion = 0;
			let emptyFields = 0;

			$(".left-icon")
					.click(
							function() {
								if (quiz.questions.length > 0) {
									if (currentQuestion === 0) {
										currentQuestion = quiz.questions.length;
									}
									currentQuestion--;
									console.log(currentQuestion);
									$(".QR-diplay").hide();
									$(".MC-diplay").hide();
									$(".PR-diplay").hide();
									$(".FG-diplay").hide();

									bla();
									bla2();
									bla3();

									if (quiz.questions[currentQuestion].type === 2) {
										if (quiz.questions[currentQuestion].beforeBlank === "") {
											$(".FG-display-BB")
													.text(
															"before blank field is empty");
										} else {
											$(".FG-display-BB")
													.text(
															quiz.questions[currentQuestion].beforeBlank);
										}

										if (quiz.questions[currentQuestion].blank === "") {
											$(".FG-display-B").text(
													"blank field is empty");
										} else {
											$(".FG-display-B")
													.text(
															quiz.questions[currentQuestion].blank);
										}

										if (quiz.questions[currentQuestion].afterBlank === "") {
											$(".FG-display-AB")
													.text(
															"after blank field is empty");
										} else {
											$(".FG-display-AB")
													.text(
															quiz.questions[currentQuestion].afterBlank);
										}

										$(".question-count-text")
												.text(
														"question "
																+ (currentQuestion + 1));

										$(".FG-diplay").show();
									}

								}
							});

			$(".right-icon")
					.click(
							function() {
								if (quiz.questions.length > 0) {
									if (currentQuestion === (quiz.questions.length - 1)) {
										currentQuestion = -1;
									}
									currentQuestion++;
									console.log(currentQuestion);
									$(".QR-diplay").hide();
									$(".MC-diplay").hide();
									$(".FG-diplay").hide();
									$(".PR-diplay").hide();

									bla();
									bla2();
									bla3();
									if (quiz.questions[currentQuestion].type === 2) {
										if (quiz.questions[currentQuestion].beforeBlank === "") {
											$(".FG-display-BB")
													.text(
															"before blank field is empty");
										} else {
											$(".FG-display-BB")
													.text(
															quiz.questions[currentQuestion].beforeBlank);
										}

										if (quiz.questions[currentQuestion].blank === "") {
											$(".FG-display-B").text(
													"blank field is empty");
										} else {
											$(".FG-display-B")
													.text(
															quiz.questions[currentQuestion].blank);
										}

										if (quiz.questions[currentQuestion].afterBlank === "") {
											$(".FG-display-AB")
													.text(
															"after blank field is empty");
										} else {
											$(".FG-display-AB")
													.text(
															quiz.questions[currentQuestion].afterBlank);
										}

										$(".question-count-text")
												.text(
														"question "
																+ (currentQuestion + 1));

										$(".FG-diplay").show();
									}
								}
							});

			$(".add-question").click(function() {
				$(".attension").css("display", "none");
				$(".question-add-or-done").css("display", "none");

				$(".quiz-type-header-property").text("choose question type");
				$(".question-type-container").show();
			});

			$(".QR").click(function() {
				quiz.questions.push({
					"type" : 1,
					"question" : "",
					"answer1" : "",
					"answer2" : ""
				});

				$(".quiz-type-header-property").text("Question Response");
				$(".Question-Response-container").show();
				$(".question-type-container").css("display", "none");
			});

			$(".PR").click(function() {
				quiz.questions.push({
					"type" : 4,
					"url" : "",
					"answer" : ""
				});

				$(".quiz-type-header-property").text("Picture Response");
				$(".Picture-Response-container").show();
				$(".question-type-container").css("display", "none");
			});

			$(".MC").click(function() {
				quiz.questions.push({
					"type" : 3,
					"question" : "",
					"answer1" : "",
					"answer2" : "",
					"answer3" : "",
					"answer4" : ""
				});

				$(".quiz-type-header-property").text("Multiple Choice");
				$(".multiple-choice-container").show();
				$(".question-type-container").css("display", "none");
			});

			$(".QR-done")
					.click(
							function() {
								quiz.questions[quiz.questions.length - 1].question = $(
										".QR-question-placeholder").val();
								quiz.questions[quiz.questions.length - 1].answer1 = $(
										".first-answer").val();
								quiz.questions[quiz.questions.length - 1].answer2 = $(
										".second-answer").val();
								$(".QR-question-placeholder").val("");
								$(".first-answer").val("");
								$(".second-answer").val("");

								$(".Question-Response-container").css(
										"display", "none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								$(".FG-diplay").hide();
								$(".PR-diplay").hide();
								$(".MC-diplay").hide();
								$(".QR-diplay").hide();

								if (quiz.questions[quiz.questions.length - 1].type === 1) {
									currentQuestion = quiz.questions.length - 1;

									if (quiz.questions[currentQuestion].question === "") {
										$(".QR-display-question").text(
												"question field is empty");
									} else {
										$(".QR-display-question")
												.text(
														quiz.questions[currentQuestion].question);
									}

									if (quiz.questions[currentQuestion].answer1 === "") {
										$(".QR-display-answer1").text(
												"answer field is empty");
									} else {
										$(".QR-display-answer1")
												.text(
														quiz.questions[currentQuestion].answer1);
									}

									if (quiz.questions[currentQuestion].answer2 === "") {
										$(".QR-display-answer2").text(
												"answer field is empty");
									} else {
										$(".QR-display-answer2")
												.text(
														quiz.questions[currentQuestion].answer2);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".QR-diplay").show();
								}

							});

			$(".MC-done")
					.click(
							function() {
								quiz.questions[quiz.questions.length - 1].question = $(
										".MC-question-placeholder").val();
								quiz.questions[quiz.questions.length - 1].answer1 = $(
										".mc-first-answer").val();
								quiz.questions[quiz.questions.length - 1].answer2 = $(
										".mc-second-answer").val();
								quiz.questions[quiz.questions.length - 1].answer3 = $(
										".mc-third-answer").val();
								quiz.questions[quiz.questions.length - 1].answer4 = $(
										".mc-fourth-answer").val();
								$(".MC-question-placeholder").val("");
								$(".mc-first-answer").val("");
								$(".mc-second-answer").val("");
								$(".mc-third-answer").val("");
								$(".mc-fourth-answer").val("");

								$(".multiple-choice-container").css("display",
										"none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								$(".FG-diplay").hide();
								$(".MC-diplay").hide();
								$(".QR-diplay").hide();
								$(".PR-diplay").hide();

								if (quiz.questions[quiz.questions.length - 1].type === 3) {
									currentQuestion = quiz.questions.length - 1;

									if (quiz.questions[currentQuestion].question === "") {
										$(".MC-display-question").text(
												"question field is empty");
									} else {
										$(".MC-display-question")
												.text(
														quiz.questions[currentQuestion].question);
									}

									if (quiz.questions[currentQuestion].answer1 === "") {
										$(".MC-display-answer1").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer1")
												.text(
														quiz.questions[currentQuestion].answer1);
									}

									if (quiz.questions[currentQuestion].answer2 === "") {
										$(".MC-display-answer2").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer2")
												.text(
														quiz.questions[currentQuestion].answer2);
									}

									if (quiz.questions[currentQuestion].answer3 === "") {
										$(".MC-display-answer3").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer3")
												.text(
														quiz.questions[currentQuestion].answer3);
									}

									if (quiz.questions[currentQuestion].answer4 === "") {
										$(".MC-display-answer4").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer4")
												.text(
														quiz.questions[currentQuestion].answer4);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".MC-diplay").show();
								}

							});

			$(".PR-done")
					.click(
							function() {

								quiz.questions[quiz.questions.length - 1].url = $(
										".PR-question-placeholder").val();
								quiz.questions[quiz.questions.length - 1].answer = $(
										".PR-answer").val();
								$(".PR-question-placeholder").val("");
								$(".PR-answer").val("");

								$(".Picture-Response-container").css("display",
										"none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								$(".FG-diplay").hide();
								$(".MC-diplay").hide();
								$(".QR-diplay").hide();
								$(".PR-diplay").hide();

								if (quiz.questions[quiz.questions.length - 1].type === 4) {
									currentQuestion = quiz.questions.length - 1;

									if (quiz.questions[currentQuestion].answer === "") {
										$(".PR-display-answer").text(
												"answer field is empty");
									} else {
										$(".PR-display-answer")
												.text(
														quiz.questions[currentQuestion].answer);
									}

									$(".PR-image")
											.attr(
													"src",
													quiz.questions[currentQuestion].url);

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".PR-diplay").show();
								}
							});

			$(".QR-done-edit")
					.click(
							function() {
								quiz.questions[currentQuestion].question = $(
										".QR-question-placeholder").val();
								quiz.questions[currentQuestion].answer1 = $(
										".first-answer").val();
								quiz.questions[currentQuestion].answer2 = $(
										".second-answer").val();
								$(".QR-question-placeholder").val("");
								$(".first-answer").val("");
								$(".second-answer").val("");

								$(".Question-Response-container").css(
										"display", "none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								if (quiz.questions[currentQuestion].type === 1) {

									if (quiz.questions[currentQuestion].question === "") {
										$(".QR-display-question").text(
												"question field is empty");
									} else {
										$(".QR-display-question")
												.text(
														quiz.questions[currentQuestion].question);
									}

									if (quiz.questions[currentQuestion].answer1 === "") {
										$(".QR-display-answer1").text(
												"answer field is empty");
									} else {
										$(".QR-display-answer1")
												.text(
														quiz.questions[currentQuestion].answer1);
									}

									if (quiz.questions[currentQuestion].answer2 === "") {
										$(".QR-display-answer2").text(
												"answer field is empty");
									} else {
										$(".QR-display-answer2")
												.text(
														quiz.questions[currentQuestion].answer2);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".QR-diplay").show();
								}
								$(".QR-done-edit").hide();
							});

			$(".MC-done-edit")
					.click(
							function() {
								quiz.questions[currentQuestion].question = $(
										".MC-question-placeholder").val();
								quiz.questions[currentQuestion].answer1 = $(
										".mc-first-answer").val();
								quiz.questions[currentQuestion].answer2 = $(
										".mc-second-answer").val();
								quiz.questions[currentQuestion].answer3 = $(
										".mc-third-answer").val();
								quiz.questions[currentQuestion].answer4 = $(
										".mc-fourth-answer").val();
								$(".MC-question-placeholder").val("");
								$(".mc-first-answer").val("");
								$(".mc-second-answer").val("");
								$(".mc-third-answer").val("");
								$(".mc-fourth-answer").val("");

								$(".multiple-choice-container").css("display",
										"none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								if (quiz.questions[currentQuestion].type === 3) {

									if (quiz.questions[currentQuestion].question === "") {
										$(".MC-display-question").text(
												"question field is empty");
									} else {
										$(".MC-display-question")
												.text(
														quiz.questions[currentQuestion].question);
									}

									if (quiz.questions[currentQuestion].answer1 === "") {
										$(".MC-display-answer1").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer1")
												.text(
														quiz.questions[currentQuestion].answer1);
									}

									if (quiz.questions[currentQuestion].answer2 === "") {
										$(".MC-display-answer2").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer2")
												.text(
														quiz.questions[currentQuestion].answer2);
									}

									if (quiz.questions[currentQuestion].answer3 === "") {
										$(".MC-display-answer3").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer3")
												.text(
														quiz.questions[currentQuestion].answer3);
									}

									if (quiz.questions[currentQuestion].answer4 === "") {
										$(".MC-display-answer4").text(
												"answer field is empty");
									} else {
										$(".MC-display-answer4")
												.text(
														quiz.questions[currentQuestion].answer4);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".MC-diplay").show();
								}
								$(".MC-done-edit").hide();
							});

			$(".PR-done-edit")
					.click(
							function() {
								quiz.questions[currentQuestion].url = $(
										".PR-question-placeholder").val();
								quiz.questions[currentQuestion].answer = $(
										".PR-answer").val();
								$(".PR-question-placeholder").val("");
								$(".PR-answer").val("");

								$(".Picture-Response-container").css("display",
										"none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								if (quiz.questions[currentQuestion].type === 4) {

									if (quiz.questions[currentQuestion].answer === "") {
										$(".PR-display-answer").text(
												"answer field is empty");
									} else {
										$(".PR-display-answer")
												.text(
														quiz.questions[currentQuestion].answer);
									}

									$(".PR-image")
											.attr(
													"src",
													quiz.questions[currentQuestion].url);

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".PR-diplay").show();
								}
								$(".PR-done-edit").hide();
							});

			$(".FG-done-edit")
					.click(
							function() {
								quiz.questions[currentQuestion].beforeBlank = $(
										".FG-BB-placeholder").val();
								quiz.questions[currentQuestion].blank = $(
										".FG-B-placeholder").val();
								quiz.questions[currentQuestion].afterBlank = $(
										".FG-AB-placeholder").val();
								$(".FG-BB-placeholder").val("");
								$(".FG-B-placeholder").val("");
								$(".FG-AB-placeholder").val("");

								$(".fill-in-the-blank-container").css(
										"display", "none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();

								if (quiz.questions[currentQuestion].type === 2) {

									if (quiz.questions[currentQuestion].beforeBlank === "") {
										$(".FG-display-BB").text(
												"before blank field is empty");
									} else {
										$(".FG-display-BB")
												.text(
														quiz.questions[currentQuestion].beforeBlank);
									}

									if (quiz.questions[currentQuestion].blank === "") {
										$(".FG-display-B").text(
												"blank field is empty");
									} else {
										$(".FG-display-B")
												.text(
														quiz.questions[currentQuestion].blank);
									}

									if (quiz.questions[currentQuestion].afterBlank === "") {
										$(".FG-display-AB").text(
												"after blank field is empty");
									} else {
										$(".FG-display-AB")
												.text(
														quiz.questions[currentQuestion].afterBlank);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".FG-diplay").show();
								}
								$(".FG-done-edit").hide();
							});

			$(".edit-QR").click(
					function() {
						$(".quiz-type-header-property").text(
								"Question Response");
						$(".Question-Response-container").show();
						$(".QR-question-placeholder").val(
								quiz.questions[currentQuestion].question);
						$(".first-answer").val(
								quiz.questions[currentQuestion].answer1);
						$(".second-answer").val(
								quiz.questions[currentQuestion].answer2);

						$(".question-add-or-done").css("display", "none");
						$(".QR-done-edit").show();
					});

			$(".edit-MC")
					.click(
							function() {
								$(".quiz-type-header-property").text(
										"Multiple Choice");
								$(".multiple-choice-container").show();

								$(".MC-question-placeholder")
										.val(
												quiz.questions[currentQuestion].question);
								$(".mc-first-answer")
										.val(
												quiz.questions[currentQuestion].answer1);
								$(".mc-second-answer")
										.val(
												quiz.questions[currentQuestion].answer2);
								$(".mc-third-answer")
										.val(
												quiz.questions[currentQuestion].answer3);
								$(".mc-fourth-answer")
										.val(
												quiz.questions[currentQuestion].answer4);

								$(".question-add-or-done").css("display",
										"none");
								$(".MC-done-edit").show();
							});

			$(".edit-FG").click(
					function() {
						$(".quiz-type-header-property").text(
								"Fill in the Blank");
						$(".fill-in-the-blank-container").show();
						$(".FG-BB-placeholder").val(
								quiz.questions[currentQuestion].beforeBlank);
						$(".FG-B-placeholder").val(
								quiz.questions[currentQuestion].blank);
						$(".FG-AB-placeholder").val(
								quiz.questions[currentQuestion].afterBlank);

						$(".question-add-or-done").css("display", "none");
						$(".FG-done-edit").show();
					});

			$(".edit-PR").click(
					function() {
						$(".quiz-type-header-property")
								.text("Picture Response");
						$(".Picture-Response-container").show();
						$(".PR-question-placeholder").val(
								quiz.questions[currentQuestion].url);
						$(".PR-answer").val(
								quiz.questions[currentQuestion].answer);

						$(".question-add-or-done").css("display", "none");
						$(".PR-done-edit").show();
					});

			$(".FG").click(function() {
				quiz.questions.push({
					"type" : 2,
					"beforeBlank" : "",
					"blank" : "",
					"afterBlank" : ""
				});

				$(".quiz-type-header-property").text("Fill in the Blank");
				$(".fill-in-the-blank-container").show();

				$(".question-type-container").css("display", "none");
			});

			$(".FG-done")
					.click(
							function() {
								quiz.questions[quiz.questions.length - 1].beforeBlank = $(
										".FG-BB-placeholder").val();
								quiz.questions[quiz.questions.length - 1].blank = $(
										".FG-B-placeholder").val();
								quiz.questions[quiz.questions.length - 1].afterBlank = $(
										".FG-AB-placeholder").val();
								$(".FG-BB-placeholder").val("");
								$(".FG-B-placeholder").val("");
								$(".FG-AB-placeholder").val("");

								$(".fill-in-the-blank-container").css(
										"display", "none");
								$(".quiz-type-header-property").text(
										"Create Quiz");
								$(".question-add-or-done").show();
								$(".FG-diplay").hide();
								$(".MC-diplay").hide();
								$(".PR-diplay").hide();
								$(".QR-diplay").hide();
								if (quiz.questions[quiz.questions.length - 1].type === 2) {
									currentQuestion = quiz.questions.length - 1;

									if (quiz.questions[currentQuestion].beforeBlank === "") {
										$(".FG-display-BB").text(
												"before blank field is empty");
									} else {
										$(".FG-display-BB")
												.text(
														quiz.questions[currentQuestion].beforeBlank);
									}

									if (quiz.questions[currentQuestion].blank === "") {
										$(".FG-display-B").text(
												"blank field is empty");
									} else {
										$(".FG-display-B")
												.text(
														quiz.questions[currentQuestion].blank);
									}

									if (quiz.questions[currentQuestion].afterBlank === "") {
										$(".FG-display-AB").text(
												"after blank field is empty");
									} else {
										$(".FG-display-AB")
												.text(
														quiz.questions[currentQuestion].afterBlank);
									}

									$(".question-count-text")
											.text(
													"question "
															+ (currentQuestion + 1));

									$(".FG-diplay").show();
								}
							});

			$(".done-error-btn").click(function() {
				$(".alert-error").hide();
				$(".quiz-type-header-property").show();
				$(".question-container").show();
			});

			$(".done")
					.click(
							function() {

								let mongo = quiz.questions.length;
								if (mongo > 30 || mongo < 1) {
									$(".question-container").hide();
									$(".quiz-type-header-property").hide();
									$(".done-error")
											.text(
													"number of questions must be at least 10 and less than 30!");
									$(".alert-error").show();
								} else {

									quiz.title = $(".quiz-property-title-input")
											.val();
									quiz.description = $(
											".quiz-property-description-textarea")
											.val();

									collectEmptyness();
									if (emptyFields === 0) {

										$(".question-container").hide();
										$(".quiz-type-header-property").hide();
										$(".done-error-btn").hide();
										$(".done-error").text("Quiz Created");
										$(".alert-error").show();

										$
												.ajax({
													url : "CreateQuizServlet",
													type : "POST",
													dataType : 'JSON',
													data : {
														"quiz" : JSON
																.stringify(quiz),
														"user" :
		<%="\"" + user.getId() + "\""%>
		<!--"unda"-->
			},
													success : function(data) {
														alert(data);
													}
												});

									} else {
										$(".question-container").hide();
										$(".quiz-type-header-property").hide();
										$(".done-error-btn").show();
										$(".done-error").text(
												"you have " + emptyFields
														+ " fields empty");
										emptyFields = 0;
										$(".alert-error").show();
									}
								}
							});

			function collectEmptyness() {
				if (quiz.title === "") {
					emptyFields++;
				}
				if (quiz.description === "") {
					emptyFields++;
				}

				for (let i = 0; i < quiz.questions.length; i++) {

					if (quiz.questions[i].type === 2) {

						if (quiz.questions[i].beforeBlank === "") {

							emptyFields++;
						}
						if (quiz.questions[i].blank === "") {
							emptyFields++;
						}
						if (quiz.questions[i].afterBlank === "") {
							emptyFields++;
						}
					}
					if (quiz.questions[i].type === 1) {
						if (quiz.questions[i].question === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer1 === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer2 === "") {
							emptyFields++;
						}
					}

					if (quiz.questions[i].type === 3) {
						if (quiz.questions[i].question === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer1 === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer2 === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer3 === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer4 === "") {
							emptyFields++;
						}
					}

					if (quiz.questions[i].type === 4) {
						if (quiz.questions[i].url === "") {
							emptyFields++;
						}
						if (quiz.questions[i].answer === "") {
							emptyFields++;
						}
					}

				}
			}

			function bla2() {
				if (quiz.questions[currentQuestion].type === 3) {
					if (quiz.questions[currentQuestion].question === "") {
						$(".MC-display-question").text(
								"question field is empty");
					} else {
						$(".MC-display-question").text(
								quiz.questions[currentQuestion].question);
					}

					if (quiz.questions[currentQuestion].answer1 === "") {
						$(".MC-display-answer1").text("answer field is empty");
					} else {
						$(".MC-display-answer1").text(
								quiz.questions[currentQuestion].answer1);
					}

					if (quiz.questions[currentQuestion].answer2 === "") {
						$(".MC-display-answer2").text("answer field is empty");
					} else {
						$(".MC-display-answer2").text(
								quiz.questions[currentQuestion].answer2);
					}

					if (quiz.questions[currentQuestion].answer3 === "") {
						$(".MC-display-answer3").text("answer field is empty");
					} else {
						$(".MC-display-answer3").text(
								quiz.questions[currentQuestion].answer3);
					}

					if (quiz.questions[currentQuestion].answer4 === "") {
						$(".MC-display-answer4").text("answer field is empty");
					} else {
						$(".MC-display-answer4").text(
								quiz.questions[currentQuestion].answer4);
					}

					$(".question-count-text").text(
							"question " + (currentQuestion + 1));

					$(".MC-diplay").show();
				}
			}

			function bla3() {
				if (quiz.questions[currentQuestion].type === 4) {
					if (quiz.questions[currentQuestion].answer === "") {
						$(".PR-display-answer").text("question field is empty");
					} else {
						$(".PR-display-answer").text(
								quiz.questions[currentQuestion].answer);
					}
					$(".PR-image").attr("src",
							quiz.questions[currentQuestion].url);

					$(".question-count-text").text(
							"question " + (currentQuestion + 1));

					$(".PR-diplay").show();
				}
			}
			function bla() {
				if (quiz.questions[currentQuestion].type === 1) {
					if (quiz.questions[currentQuestion].question === "") {
						$(".QR-display-question").text(
								"question field is empty");
					} else {
						$(".QR-display-question").text(
								quiz.questions[currentQuestion].question);
					}

					if (quiz.questions[currentQuestion].answer1 === "") {
						$(".QR-display-answer1").text("answer field is empty");
					} else {
						$(".QR-display-answer1").text(
								quiz.questions[currentQuestion].answer1);
					}

					if (quiz.questions[currentQuestion].answer2 === "") {
						$(".QR-display-answer2").text("answer field is empty");
					} else {
						$(".QR-display-answer2").text(
								quiz.questions[currentQuestion].answer2);
					}

					$(".question-count-text").text(
							"question " + (currentQuestion + 1));

					$(".QR-diplay").show();
				}
			}

			function setDescription() {
				if (quiz.description === "") {
					$(".choose-your-path-description").text(
							"description field is empty");
				} else {
					$(".choose-your-path-description").text(quiz.description);
				}
			}

			function setTitle() {
				if (quiz.title === "") {
					$(".choose-your-path-title").text("title field is empty");
				} else {
					$(".choose-your-path-title").text(quiz.title);
				}
			}
		</script>
</body>


</html>
use quizbase;

Create table QUESTION_TYPE(
	TYPE_ID int primary key auto_increment,
    QUESTION_TYPE_DESC nvarchar(50) not null
);

create table QUIZ(
	QUIZ_ID int primary key auto_increment,
	USER_ID int not null,
    QUIZ_NAME nvarchar(50) not null,
    QUIZ_DESCRIPTION nvarchar(500),
    RANDOMIZE boolean default false,
    MULTIPAGE boolean default false
);

create table MESSAGE_TYPE(
	MESSAGE_TYPE_ID int primary key auto_increment,
    MESSAGE_TYPE_DESC nvarchar(50) not null
);

create table ACHIEVMENT_TYPE(
	ACHIEVMENT_TYPE_ID int primary key auto_increment,
    ACHIEVMENT_TYPE_DESC nvarchar(50) not null
);

create table USER_TYPE(
	USER_TYPE_ID int primary key auto_increment,
    USER_TYPE_DESC nvarchar(50) not null
);

create table WEB_USERS(
	USER_ID int primary key auto_increment,
	USER_NAME nvarchar(50) not null unique,
    FIRST_NAME nvarchar(50) not null,
    LAST_NAME nvarchar(50) not null,
    E_MAIL nvarchar(50) not null unique,
    PASSWORD_HASH nvarchar(50) not null,
    USER_TYPE int not null default 1
);

create table FRIENDS(
	FRIENDS_REQUEST_ID int primary key auto_increment,
    SENDER_ID int not null,
    RECIEVER_ID int not null,
    REQUEST_STATUS enum('CONFIRMED','PENDING'),
    SEND_DATE datetime not null,
    CONFIRM_DATE datetime
);

create table USER_ACHIEVMENTS(
	USER_ACHIEV_ID int primary key auto_increment,
    USER_ID int not null,
    ACHIEV_TYPE_ID int not null,
    FINISH_DATE datetime not null
);

create table MAIL_MESSAGE(
	MAIL_MESSAGE_ID int primary key auto_increment,
    SENDER_USER_ID int not null,
    RECIEVER_USER_ID int not null,
    MESSAGE_TYPE_ID int not null,
    MESSAGE_TEXT nvarchar(1000),
    SEND_DATE datetime not null
);

create table QUIZ_QUESTION(
	QUESTION_ID int primary key auto_increment,
	QUIZ_ID int not null,
    QUESTION_TYPE_ID int not null,
    QUESTION_N int not null,
    QUESTION_TEXT nvarchar(100) not null,
    URL nvarchar(100)
);

create table QUESTION_ANSWER(
	ANSWER_ID int primary key auto_increment,
    QUESTION_ID int not null,
    RIGHT_ANSWER bool default false,
    POSSIBLE_ANSWER nvarchar(100) not null
);

create table COMPLETE_QUIZ(
	COMPLETE_QUIZ_ID int primary key auto_increment,
    QUIZ_ID int not null,
    USER_ID int not null,
    SPENT_TIME_SECONDS int not null,
    USER_SCORE int not null,
    FINISH_DATE datetime not null
);

create table CHALLENGE(
	CHALLENGE_ID int primary key auto_increment,
    SENDER_ID int not null,
    RECIEVER_ID int not null,
    QUIZ_ID int not null,
    CHALLENGE_STATUS enum('CONFIRMED','PENDING'),
    SEND_DATE datetime not null,
    CONFIRM_DATE datetime
);

INSERT INTO QUESTION_TYPE VALUES (1, 'Question-Response');
INSERT INTO QUESTION_TYPE VALUES (2, 'Fill in the Blank');

INSERT INTO QUESTION_TYPE VALUES (3, 'Multiple Choice');
INSERT INTO QUESTION_TYPE VALUES (4, 'Picture-Response Questions');
INSERT INTO MESSAGE_TYPE VALUES (1, 'Friend Request');
INSERT INTO MESSAGE_TYPE VALUES (2, 'Challenge');
INSERT INTO MESSAGE_TYPE VALUES (3, 'Note');
INSERT INTO ACHIEVMENT_TYPE VALUES (1, 'Amateur Author');
INSERT INTO ACHIEVMENT_TYPE VALUES (2, 'Prolific Author');
INSERT INTO ACHIEVMENT_TYPE VALUES (3, 'Prodigious Author');
INSERT INTO ACHIEVMENT_TYPE VALUES (4, 'Quiz Machine');
INSERT INTO ACHIEVMENT_TYPE VALUES (5, 'I am the Greatest');
INSERT INTO ACHIEVMENT_TYPE VALUES (6, 'Practice Makes Perfect');
INSERT INTO USER_TYPE VALUES (1, 'Admin');
INSERT INTO USER_TYPE VALUES (2, 'User');
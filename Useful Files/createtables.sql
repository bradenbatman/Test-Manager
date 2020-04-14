CREATE TABLE "question"
(
 "qid"        serial NOT NULL,
 "question"   varchar(255) NOT NULL,
 "answer"     varchar(255) NOT NULL,
 "incorrect1" varchar(255) NOT NULL,
 "incorrect2" varchar(255) NOT NULL,
 "incorrect3" varchar(255) NOT NULL,
 CONSTRAINT "PK_question" PRIMARY KEY ( "qID" )
);

CREATE TABLE "student"
(
 "name"      varchar(50) NOT NULL,
 "studentid" serial NOT NULL,
 CONSTRAINT "PK_student" PRIMARY KEY ( "studentID" )
);

CREATE TABLE "test"
(
 "testid"    serial NOT NULL,
 "question1" integer NOT NULL,
 "question2" integer NOT NULL,
 "question3" integer NOT NULL,
 "question4" integer NOT NULL,
 CONSTRAINT "PK_test" PRIMARY KEY ( "testID" ),
 CONSTRAINT "FK_q1" FOREIGN KEY ( "question1" ) REFERENCES "question" ( "qID" ),
 CONSTRAINT "FK_q2" FOREIGN KEY ( "question2" ) REFERENCES "question" ( "qID" ),
 CONSTRAINT "FK_q3" FOREIGN KEY ( "question3" ) REFERENCES "question" ( "qID" ),
 CONSTRAINT "FK_q4" FOREIGN KEY ( "question4" ) REFERENCES "question" ( "qID" )
);

CREATE TABLE "testlog"
(
 "logid"      serial NOT NULL,
 "numCorrect" integer NOT NULL,
 "studentid"  integer NOT NULL,
 "testid"     integer NOT NULL,
 CONSTRAINT "PK_testlog" PRIMARY KEY ( "logID" ),
 CONSTRAINT "FK_studentID" FOREIGN KEY ( "studentID" ) REFERENCES "student" ( "studentID" ),
 CONSTRAINT "FK_testID" FOREIGN KEY ( "testID" ) REFERENCES "test" ( "testID" )
);

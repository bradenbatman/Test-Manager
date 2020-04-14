CREATE TABLE "question"
(
 "qid"        serial NOT NULL,
 "question"   varchar(255) NOT NULL,
 "answer"     varchar(255) NOT NULL,
 "incorrect1" varchar(255) NOT NULL,
 "incorrect2" varchar(255) NOT NULL,
 "incorrect3" varchar(255) NOT NULL,
 CONSTRAINT "PK_question" PRIMARY KEY ( "qid" )
);

CREATE TABLE "student"
(
 "name"      varchar(50) NOT NULL,
 "studentid" serial NOT NULL,
 CONSTRAINT "PK_student" PRIMARY KEY ( "studentid" )
);

CREATE TABLE "test"
(
 "testid"    serial NOT NULL,
 "question1" integer NOT NULL,
 "question2" integer NOT NULL,
 "question3" integer NOT NULL,
 "question4" integer NOT NULL,
 CONSTRAINT "PK_test" PRIMARY KEY ( "testid" ),
 CONSTRAINT "FK_q1" FOREIGN KEY ( "question1" ) REFERENCES "question" ( "qid" ),
 CONSTRAINT "FK_q2" FOREIGN KEY ( "question2" ) REFERENCES "question" ( "qid" ),
 CONSTRAINT "FK_q3" FOREIGN KEY ( "question3" ) REFERENCES "question" ( "qid" ),
 CONSTRAINT "FK_q4" FOREIGN KEY ( "question4" ) REFERENCES "question" ( "qid" )
);

CREATE TABLE "testlog"
(
 "logid"      serial NOT NULL,
 "numcorrect" integer NOT NULL,
 "studentid"  integer NOT NULL,
 "testid"     integer NOT NULL,
 CONSTRAINT "PK_testlog" PRIMARY KEY ( "logid" ),
 CONSTRAINT "FK_studentID" FOREIGN KEY ( "studentid" ) REFERENCES "student" ( "studentid" ),
 CONSTRAINT "FK_testID" FOREIGN KEY ( "testid" ) REFERENCES "test" ( "testid" )
);

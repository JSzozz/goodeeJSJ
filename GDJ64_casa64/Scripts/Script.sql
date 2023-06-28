
SELECT * FROM QNA;

SELECT * FROM QNA_COMMENT;

SELECT * FROM MEMBER ;

DROP TABLE QNA_COMMENT;

--SELECT * FROM QNA_COMMENT WHERE QNA_REF=60
--START WITH QNA_COMMENT_LEVEL=1 CONNECT BY PRIOR QNA_COMMENT_NO=QNA_COMMENT_REF;

--SELECT * FROM QNA ORDER BY QNA_date DESC;

CREATE TABLE QNA_COMMENT(
    QNA_COMMENT_NO NUMBER PRIMARY KEY,
    QNA_COMMENT_LEVEL NUMBER DEFAULT 1,
    QNA_COMMENT_WRITER VARCHAR2(15), 
    QNA_COMMENT_CONTENT VARCHAR2(2000), 
    QNA_REF NUMBER, 
    QNA_COMMENT_REF NUMBER, 
    QNA_COMMENT_DATE TIMESTAMP DEFAULT SYSTIMESTAMP, 
--    CONSTRAINT PK_QNA_COMMENT_NO PRIMARY KEY(QNA_COMMENT_NO),
--    CONSTRAINT FK_QNA_COMMENT_WRITER FOREIGN KEY(QNA_COMMENT_WRITER) REFERENCES MEMBER(MEMBER_NAME) ON DELETE SET NULL,
    CONSTRAINT FK_QNA_REF FOREIGN KEY(QNA_REF) REFERENCES QNA(QUESTION_NO) ON DELETE CASCADE,
    CONSTRAINT FK_QNA_COMMENT_REF FOREIGN KEY(QNA_COMMENT_REF) REFERENCES QNA_COMMENT(QNA_COMMENT_NO) ON DELETE CASCADE
);

COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_NO IS 'QNA댓글번호';
COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_LEVEL IS 'QNA댓글 레벨';
COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_WRITER IS 'QNA댓글 작성자';
COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_CONTENT IS 'QNA댓글';
COMMENT ON COLUMN QNA_COMMENT.QNA_REF IS '참조원글번호';
COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_REF IS 'QNA댓글 참조번호';
COMMENT ON COLUMN QNA_COMMENT.QNA_COMMENT_DATE IS 'QNA댓글 작성일';

    
CREATE SEQUENCE SEQ_QNA_COMMENT_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

--테스트댓글 추가(qna가 있어야 댓글 가능)
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, DEFAULT, 'admin','글 잘읽었습니다.',3,NULL,DEFAULT);
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, DEFAULT, '유저01','좋은 정보 감사합니다.',61,NULL,DEFAULT);

--대댓글추가 : 댓글번호를 참조해서 insert할 것
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, 2, 'stuv','좋은 정보가 어디에...?',61,30,DEFAULT);
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, 2, 'efgh','하하하...안보이시는구나...',61,30,DEFAULT);
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, 2, 'wxyz','얼씨구..',61,28,DEFAULT);
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, 2, 'wxyz','저는 보입니다.',61,30,DEFAULT);
INSERT INTO QNA_COMMENT VALUES(SEQ_QNA_COMMENT_NO.NEXTVAL, 2, 'hijk','뭐임....',61,28,DEFAULT);


UPDATE QNA SET MEMBER_NO=2 , CATEGORY_NAME='예약/결제' , QUESTION_TITLE='결제관련질문02' , QUESTION_CONTENT='결제관련내용02' WHERE MEMBER_NO=2;

--SELECT * FROM notice LEFT OUTER JOIN notice_images ON notice.NOTICE_NO = notice_images.NOTICE_NO ORDER BY notice.NOTICE_NO DESC;
--DROP TABLE NOTICE;
--DROP SEQUENCE SEQ_NOTICE_NO;
--SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT * FROM NOTICE ORDER BY DATE_CREATED DESC)B) WHERE RNUM BETWEEN 1 AND 5;
----게시판생성
--DROP TABLE QNA;
SELECT * FROM QNA;
CREATE TABLE QNA (   
   QUESTION_NO NUMBER PRIMARY KEY,
   MEMBER_NO NUMBER ,
   CATEGORY_NAME VARCHAR2(20) NOT NULL, 
   QUESTION_DATE DATE DEFAULT SYSDATE,
   ANSWER VARCHAR2(1000),
   QUESTION_CONTENT VARCHAR2(1000) NOT NULL,
   VISIBLE_CK CHAR(1) DEFAULT 1 NOT NULL,
   QUESTION_TITLE VARCHAR2(700),
   CONSTRAINT FK_MEMBER_NO FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER(MEMBER_NO)
);

COMMENT ON COLUMN "QNA"."QUESTION_NO" IS '질문번호';
COMMENT ON COLUMN "QNA"."MEMBER_NO" IS '회원번호'; --FK 시퀀스
COMMENT ON COLUMN "QNA"."CATEGORY_NAME" IS '카테고리명';
COMMENT ON COLUMN "QNA"."QUESTION_DATE" IS '질문작성일';
COMMENT ON COLUMN "QNA"."ANSWER" IS '답변';
COMMENT ON COLUMN "QNA"."QUESTION_CONTENT" IS '질문내용';
COMMENT ON COLUMN "QNA"."VISIBLE_CK" IS '숨김여부';
COMMENT ON COLUMN "QNA"."QUESTION_TITLE" IS '질문제목';

----게시판시퀀스생성
CREATE SEQUENCE SEQ_QNA_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;
----------------------------------------------------------------------------------------------------------------    QUESTION_NO,CATEGORY_NAME,QUESTION_DATE,ANSWER,QUESTION_CONTENT,VISIBLE_CK,QUESTION_TITLE
Insert into QNA values (SEQ_QNA_NO.nextval,1,'전체보기',to_date('18/01/11','RR/MM/DD'),'답변01','질문01','1','제목01');


CREATE TABLE NOTICE (   
   NOTICE_NO NUMBER,
   NOTICE_TITLE VARCHAR2(50),
   NOTICE_CONTENT VARCHAR2(2000), 
   DATE_CREATED DATE DEFAULT SYSDATE,
   DATE_MODIFIED DATE DEFAULT SYSDATE,
   NOTICE_READCOUNT NUMBER DEFAULT 0,
   CONSTRAINT PK_NOTICE_NO PRIMARY KEY(NOTICE_NO)
);

COMMENT ON COLUMN "NOTICE"."NOTICE_NO" IS '게시글번호';
COMMENT ON COLUMN "NOTICE"."NOTICE_TITLE" IS '게시글제목';
COMMENT ON COLUMN "NOTICE"."NOTICE_CONTENT" IS '게시글내용';
COMMENT ON COLUMN "NOTICE"."DATE_CREATED" IS '게시글올린날짜';
COMMENT ON COLUMN "NOTICE"."DATE_MODIFIED" IS '게시글수정날짜';
COMMENT ON COLUMN "NOTICE"."NOTICE_READCOUNT" IS '조회수';

----게시판시퀀스생성
CREATE SEQUENCE SEQ_NOTICE_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;


Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 1','반갑습니다',to_date('18/01/11','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 1','반갑습니다',to_date('18/02/10','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 2','반갑습니다',to_date('18/02/12','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 3','반갑습니다',to_date('18/02/13','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 4','반갑습니다',to_date('18/02/14','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 5','반갑습니다',to_date('18/02/15','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 6','반갑습니다',to_date('18/02/16','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 7','반갑습니다',to_date('18/02/17','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 8','반갑습니다',to_date('18/02/18','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 9','반갑습니다',to_date('18/02/19','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 10','반갑습니다',to_date('18/02/20','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 11','반갑습니다',to_date('18/03/11','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 12','반갑습니다',to_date('18/03/12','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 13','반갑습니다',to_date('18/03/13','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 14','반갑습니다',to_date('18/03/14','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 15','반갑습니다',to_date('18/03/15','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 16','반갑습니다',to_date('18/03/16','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 17','반갑습니다',to_date('18/03/17','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 18','반갑습니다',to_date('18/03/18','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 19','반갑습니다',to_date('18/03/19','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 20','반갑습니다',to_date('18/03/20','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 21','반갑습니다',to_date('18/04/01','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 23','반갑습니다',to_date('18/04/03','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 24','반갑습니다',to_date('18/04/04','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 25','반갑습니다',to_date('18/04/05','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 26','반갑습니다',to_date('18/04/06','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 27','반갑습니다',to_date('18/04/07','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 28','반갑습니다',to_date('18/04/08','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 29','반갑습니다',to_date('18/04/09','RR/MM/DD'),0);
Insert into NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,DATE_CREATED,NOTICE_READCOUNT) values (SEQ_NOTICE_NO.nextval,'안녕하세요, 게시판입니다 - 30','반갑습니다',to_date('18/04/10','RR/MM/DD'),0);

SELECT * FROM NOTICE;

/* Drop Tables */

DROP TABLE ACHIEVE_DEF CASCADE CONSTRAINTS;
DROP TABLE ACTION_DEF CASCADE CONSTRAINTS;
DROP TABLE BATCH_CHG CASCADE CONSTRAINTS;
DROP TABLE CHANGE_DETAIL CASCADE CONSTRAINTS;
DROP TABLE CHANGE_RECORD CASCADE CONSTRAINTS;
DROP TABLE CHECK_ITEMS CASCADE CONSTRAINTS;
DROP TABLE CODE_CHG CASCADE CONSTRAINTS;
DROP TABLE DAILY_ACHIEVE CASCADE CONSTRAINTS;
DROP TABLE DAILY_ISSUE CASCADE CONSTRAINTS;
DROP TABLE DAILY_RECORD CASCADE CONSTRAINTS;
DROP TABLE DATABASE_CHG CASCADE CONSTRAINTS;
DROP TABLE DATA_CHG CASCADE CONSTRAINTS;
DROP TABLE MAIL_CONFIG CASCADE CONSTRAINTS;
DROP TABLE FUNCTION_DEF CASCADE CONSTRAINTS;
DROP TABLE GRADE_DEFINE CASCADE CONSTRAINTS;
DROP TABLE ISSUE_RECORD CASCADE CONSTRAINTS;
DROP TABLE LX_INFO CASCADE CONSTRAINTS;
DROP TABLE MAIN_PLAN_JOB CASCADE CONSTRAINTS;
DROP TABLE MAIN_PROBLEM CASCADE CONSTRAINTS;
DROP TABLE PROJECT_DISTRIBUTE CASCADE CONSTRAINTS;
DROP TABLE MILE_STONE CASCADE CONSTRAINTS;
DROP TABLE PARTICIPATE_LIST CASCADE CONSTRAINTS;
DROP TABLE MODULE_DEF CASCADE CONSTRAINTS;
DROP TABLE OTHER_DAILY CASCADE CONSTRAINTS;
DROP TABLE PARAM_CHG CASCADE CONSTRAINTS;
DROP TABLE PRODUCT_DEF CASCADE CONSTRAINTS;
DROP TABLE PROGRAM_LIST CASCADE CONSTRAINTS;
DROP TABLE PROJECT_CHG_RECORD CASCADE CONSTRAINTS;
DROP TABLE PROJECT_FILES CASCADE CONSTRAINTS;
DROP TABLE PROJECT_RELATION CASCADE CONSTRAINTS;
DROP TABLE REQUIRE_CHANGES CASCADE CONSTRAINTS;
DROP TABLE REVIEW_RECORD CASCADE CONSTRAINTS;
DROP TABLE RISK_RECORD CASCADE CONSTRAINTS;
DROP TABLE PROJECT_LIST CASCADE CONSTRAINTS;
DROP TABLE REPORT_CHG CASCADE CONSTRAINTS;
DROP TABLE ROLE_DEPART_AUTH CASCADE CONSTRAINTS;
DROP TABLE SCALE_DEF CASCADE CONSTRAINTS;
DROP TABLE SCORE_INFO CASCADE CONSTRAINTS;
DROP TABLE SIGN_RECORD CASCADE CONSTRAINTS;
DROP TABLE SUB_SYS_VERSION CASCADE CONSTRAINTS;
DROP TABLE UPDATE_STEP CASCADE CONSTRAINTS;
DROP TABLE VERSION_HIS CASCADE CONSTRAINTS;
DROP TABLE WORK_DAILY CASCADE CONSTRAINTS;
DROP TABLE WORK_DISTRIBUTE CASCADE CONSTRAINTS;
DROP TABLE WORK_LIST CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE ACHIEVE_DEF
(
	ACHIEVE_ID NUMBER(10,0) NOT NULL,
	ACHIEVE VARCHAR2(250),
	CHECK_STYLE VARCHAR2(250),
	PRIMARY KEY (ACHIEVE_ID)
);


CREATE TABLE ACTION_DEF
(
	ACTION_ID NUMBER(10,0) NOT NULL,
	NAME VARCHAR2(250),
	ACTION VARCHAR2(250),
	COMMIT_LIMIT VARCHAR2(250),
	PRIMARY KEY (ACTION_ID)
);


CREATE TABLE BATCH_CHG
(
	ID NUMBER(19,0) NOT NULL,
	VERSION_ID VARCHAR2(20) NOT NULL,
	SUB_SYS VARCHAR2(20),
	BATCH_NAME VARCHAR2(100),
	CHG_TYPE VARCHAR2(10),
	TRIGGER_TYPE VARCHAR2(100),
	PRE_STEP VARCHAR2(200),
	NEXT_STEP VARCHAR2(200),
	PRIMARY KEY (ID)
);


CREATE TABLE CHANGE_DETAIL
(
	DETAIL_ID NUMBER(19,0) NOT NULL,
	RECORD_ID NUMBER(19,0) NOT NULL,
	CHANGE_CONTENT VARCHAR2(100),
	TYPE VARCHAR2(20),
	MEMO VARCHAR2(200),
	PRIMARY KEY (DETAIL_ID)
);


CREATE TABLE CHANGE_RECORD
(
	RECORD_ID NUMBER(10,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	-- PROJECT_ID-BG[00-99]
	CHANGE_ID NUMBER(10,0),
	CHANGE_TITLE VARCHAR2(50),
	CHANGE_TYPE VARCHAR2(20),
	CHANGE_LEVEL VARCHAR2(10),
	REASON VARCHAR2(255),
	CONTENT VARCHAR2(255),
	USER_ID CHAR(8),
	CHANGE_DATE CHAR(8),
	PRIMARY KEY (RECORD_ID)
);


CREATE TABLE CHECK_ITEMS
(
	CHECK_NO CHAR(2) NOT NULL,
	CHECK_NAME VARCHAR2(50),
	MODULUS NUMBER(5,2),
	IN_USE CHAR(1) NOT NULL,
	STAT CHAR(10),
	PRIMARY KEY (CHECK_NO)
);


CREATE TABLE CODE_CHG
(
	ID NUMBER(19,0) NOT NULL,
	FILE_NAME VARCHAR2(200),
	CHG_TYPE VARCHAR2(10),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE DAILY_ACHIEVE
(
	ID NUMBER(10,0) NOT NULL,
	DISTRIBUTE_ID NUMBER(10,0) NOT NULL,
	WORK_ID VARCHAR2(8),
	WORK_DATE CHAR(8),
	USER_ID CHAR(8),
	ACHIEVEMENT VARCHAR2(250),
	ACHIEVE_PERCENT CHAR(3),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (ID)
);


CREATE TABLE DAILY_ISSUE
(
	ID NUMBER(10,0) NOT NULL,
	DISTRIBUTE_ID NUMBER(10,0) NOT NULL,
	WORK_ID VARCHAR2(8),
	WORK_DATE CHAR(8),
	USER_ID CHAR(8),
	ISSUE VARCHAR2(250),
	ISSUE_PERCENT CHAR(3),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (ID)
);


CREATE TABLE DAILY_RECORD
(
	RECORD_ID NUMBER(19,0) NOT NULL,
	ID NUMBER(10,0) NOT NULL,
	PROJECT_ID VARCHAR2(20),
	-- D-dev
	-- S-sit
	-- U-uat
	-- P-prd
	STEP VARCHAR2(10),
	WORK_DATE CHAR(8),
	TASK_COST NUMBER(5,2),
	USER_ID CHAR(8),
	INPUT_DATE CHAR(8),
	WORK_MEMO VARCHAR2(250),
	WORK_ISSUE VARCHAR2(250),
	INPUT_COST NUMBER(5,2),
	-- Y=通过
	-- N=未处理
	-- R=拒绝
	CHECKED CHAR(1),
	CHECK_USER VARCHAR2(8),
	CHECK_DATE CHAR(8),
	REFUSE_REASON VARCHAR2(255),
	PRIMARY KEY (RECORD_ID)
);


CREATE TABLE DATABASE_CHG
(
	ID NUMBER(19,0) NOT NULL,
	VERSION_ID VARCHAR2(20) NOT NULL,
	TABLE_NAME VARCHAR2(50),
	COLUMN_NAME VARCHAR2(50),
	SUB_SYS VARCHAR2(20),
	CHG_TYPE VARCHAR2(10),
	ORIGINL VARCHAR2(1000),
	TARGET VARCHAR2(100),
	PRIMARY KEY (ID)
);


CREATE TABLE DATA_CHG
(
	ID NUMBER(19,0) NOT NULL,
	SHELL_NAME VARCHAR2(100),
	MEMO VARCHAR2(200),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE FUNCTION_DEF
(
	FUNCTION_ID NUMBER(10,0) NOT NULL,
	CLASS_NAME VARCHAR2(100),
	FUNCTION_NAME VARCHAR2(100),
	MEMO VARCHAR2(100),
	PRIMARY KEY (FUNCTION_ID)
);


CREATE TABLE GRADE_DEFINE
(
	CHECK_NO CHAR(2) NOT NULL,
	GRADE CHAR(1) NOT NULL,
	SCORE NUMBER(10,0),
	LOWER_SCORE NUMBER(10,0),
	MEMO VARCHAR2(255),
	IN_USE CHAR(1) NOT NULL,
	PRIMARY KEY (CHECK_NO, GRADE)
);


CREATE TABLE ISSUE_RECORD
(
	ID NUMBER(10,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	-- Y-已完成
	-- N-进行中
	-- U-未分配
	-- D-删除
	-- P-暂停
	STAT CHAR(1),
	USER_ID CHAR(8),
	MEMO VARCHAR2(255),
	CHECK_USER CHAR(8),
	DISTRIBUTE_ID NUMBER(10,0),
	PRIMARY KEY (ID)
);


CREATE TABLE LX_INFO
(
	LX_ID CHAR(8) NOT NULL,
	DEPART CHAR(8),
	NAME CHAR(8),
	PHONE CHAR(20),
	MOBILE CHAR(19),
	EMAIL CHAR(30),
	STUFF_ID CHAR(8),
	PRIMARY KEY (LX_ID)
);


CREATE TABLE MAIL_CONFIG
(
	FUNCTION_ID NUMBER(10,0) NOT NULL,
	ROLE_ID CHAR(8)
);


CREATE TABLE MAIN_PLAN_JOB
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	USER_ID CHAR(8),
	IS_DOEN CHAR(1),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (PROJECT_ID)
);


CREATE TABLE MAIN_PROBLEM
(
	ID NUMBER(10,0) NOT NULL,
	CONTENT VARCHAR2(250),
	PRIMARY KEY (ID)
);


CREATE TABLE MILE_STONE
(
	MILE_STONE_ID NUMBER(19,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	STONE_NAME VARCHAR2(40),
	END_DATE VARCHAR2(8),
	MEMO VARCHAR2(200),
	INPUT_USER CHAR(8),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (MILE_STONE_ID)
);


CREATE TABLE MODULE_DEF
(
	MODULE_ID VARCHAR2(10) NOT NULL,
	NAME VARCHAR2(30),
	MANAGER_ID CHAR(8),
	VERSION VARCHAR2(40),
	PRODUCT_CODE VARCHAR2(30) NOT NULL,
	PRIMARY KEY (MODULE_ID)
);


CREATE TABLE OTHER_DAILY
(
	ID NUMBER(19,0) NOT NULL,
	TYPE VARCHAR2(10),
	WORK_DATE CHAR(8),
	COST NUMBER(5,1),
	INFO VARCHAR2(100),
	MEMO VARCHAR2(250),
	INPUT_USER CHAR(8),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (ID)
);


CREATE TABLE PARAM_CHG
(
	ID NUMBER(19,0) NOT NULL,
	SUB_SYS VARCHAR2(20),
	PARENT_MENU VARCHAR2(50),
	SUB_MENU VARCHAR2(50),
	MEMO VARCHAR2(100),
	CHG_TYPE VARCHAR2(10),
	ORIGINL VARCHAR2(1000),
	TARGET VARCHAR2(100),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE PARTICIPATE_LIST
(
	ID NUMBER(10,0) NOT NULL,
	USER_ID CHAR(8),
	MODULE_ID VARCHAR2(10) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE PRODUCT_DEF
(
	PRODUCT_CODE VARCHAR2(30) NOT NULL,
	NAME VARCHAR2(60),
	MANAGER_ID CHAR(8),
	LATEST_VERSION VARCHAR2(15),
	LAST_PRD_DATE CHAR(8),
	NEXT_PRD_DATE CHAR(8),
	MEMO VARCHAR2(250),
	PRIMARY KEY (PRODUCT_CODE)
);


CREATE TABLE PROGRAM_LIST
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	PROGRAM VARCHAR2(255)
);


CREATE TABLE PROJECT_CHG_RECORD
(
	RECORD_ID NUMBER(19,0) NOT NULL,
	FIRE_DATE CHAR(8),
	FINISH_DATE CHAR(8),
	CHANGE_TYPE VARCHAR2(4),
	FIRE_USER VARCHAR2(40),
	VERSION_AFTER VARCHAR2(15),
	OPER_USER VARCHAR2(20),
	TARGET_VERSION VARCHAR2(15),
	CHANGE_SEQ VARCHAR2(20),
	MEMO VARCHAR2(150),
	BASE_LINE VARCHAR2(10),
	-- mps
	-- cps
	-- ...
	MODULE VARCHAR2(10),
	PRIMARY KEY (RECORD_ID)
);


CREATE TABLE PROJECT_DISTRIBUTE
(
	ID NUMBER(10,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	USER_ID CHAR(8) NOT NULL,
	-- D-dev
	-- S-sit
	-- U-uat
	-- P-prd
	STEP VARCHAR2(10) NOT NULL,
	START_DATE CHAR(8) NOT NULL,
	END_DATE CHAR(8) NOT NULL,
	PERCENT NUMBER(10,0) NOT NULL,
	-- Y-完成
	-- N-进行中
	IS_DONE CHAR(1) NOT NULL,
	MEMO VARCHAR2(255),
	FINAL_END_DATE CHAR(8),
	MILE_STONE_ID NUMBER(19,0) NOT NULL,
	TASK_NAME VARCHAR2(200),
	ACTION_ID NUMBER(10,0),
	MODULE_ID VARCHAR2(10) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE PROJECT_FILES
(
	ID NUMBER(19,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	FILE_NAME VARCHAR2(100),
	FILE_SIZE NUMBER(10,0),
	FILE_TYPE VARCHAR2(10),
	CONTENT BLOB,
	UPDATE_DATE CHAR(8),
	USER_ID CHAR(8),
	MEMO VARCHAR2(100),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE PROJECT_LIST
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	PROJECT_NAME VARCHAR2(40),
	-- Y-已完成
	-- N-进行中
	-- U-未分配
	-- D-删除
	-- P-暂停
	STAT CHAR(1),
	USER_ID CHAR(9),
	MEMO VARCHAR2(40),
	ISS_ID VARCHAR2(200),
	-- 字段复用为是否有附件
	IS_IN_CONTRACT CHAR(1),
	NEED_DEV CHAR(1),
	OWNING CHAR(1),
	-- A|产品研发类项目
	-- B|售前支持类项目
	-- C|合同实施类项目
	-- D|运营维护类项目
	PROJECT_CLASS CHAR(2),
	PRIORITY NUMBER(10,0),
	REQUIRE_MANAGER CHAR(8),
	PROJECT_MANAGER CHAR(8),
	TECH_MANAGER CHAR(8),
	SETUP_DATE CHAR(8),
	PLAN_START_DATE CHAR(8),
	PLAN_END_DATE CHAR(8),
	VERSION_ID VARCHAR2(20) NOT NULL,
	REAL_END_DATE CHAR(8),
	-- D-dev
	-- S-sit
	-- U-uat
	-- P-prd
	STEP VARCHAR2(10),
	SUB_SYS VARCHAR2(50),
	SCALE_ID NUMBER(10,0) NOT NULL,
	CHECKER CHAR(8),
	-- Y 是
	-- N 否
	MAIN_PLAN_DIS CHAR(1),
	PRODUCT_IDS VARCHAR2(300),
	PLAN_COST NUMBER(10,2),
	PRIMARY KEY (PROJECT_ID)
);


CREATE TABLE PROJECT_RELATION
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	PRE_PROJECT VARCHAR2(10)
);


CREATE TABLE REPORT_CHG
(
	ID NUMBER(19,0) NOT NULL,
	REPORT_NAME VARCHAR2(100),
	SUB_SYS VARCHAR2(20),
	CHG_TYPE VARCHAR2(10),
	ORIGINL VARCHAR2(1000),
	TARGET VARCHAR2(100),
	LOCATION VARCHAR2(200),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE REQUIRE_CHANGES
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	VERSION NUMBER(10,0) DEFAULT 0 NOT NULL,
	CONTENT VARCHAR2(255),
	REASON VARCHAR2(255),
	CHANGE_DATE CHAR(8),
	USER_ID CHAR(8),
	PRIMARY KEY (PROJECT_ID, VERSION)
);


CREATE TABLE REVIEW_RECORD
(
	ID NUMBER(19,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	TITLE VARCHAR2(50),
	CONTENT VARCHAR2(250),
	USER_ID CHAR(9),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (ID)
);


CREATE TABLE RISK_RECORD
(
	ID NUMBER(19,0) NOT NULL,
	PROJECT_ID VARCHAR2(20) NOT NULL,
	RISK_NAME VARCHAR2(50),
	-- MARKET 市场
	-- CUST 客户
	-- TECH 技术
	-- MANAGE 管理
	-- PEOPLE 人员
	RISK_TYPE VARCHAR2(10),
	-- L  提示
	-- M 一般
	-- H 严重
	RISK_LV CHAR(1),
	USER_ID CHAR(8),
	INPUT_DATE CHAR(8),
	MEMO VARCHAR2(150),
	-- 趋势上升、趋势平稳、趋势下降，关闭
	RISK_STATUS VARCHAR2(10),
	SOLUTION VARCHAR2(200),
	SOLUTE_DATE CHAR(8),
	RISK_ID NUMBER(10,0),
	PRIMARY KEY (ID)
);


CREATE TABLE ROLE_DEPART_AUTH
(
	ID NUMBER(19,0) NOT NULL,
	ROLE_ID CHAR(8),
	DEPART_ID VARCHAR2(20),
	PRIMARY KEY (ID)
);


CREATE TABLE SCALE_DEF
(
	SCALE_ID NUMBER(10,0) NOT NULL,
	SCALE_DESC VARCHAR2(20),
	SCALE CLOB,
	PRIMARY KEY (SCALE_ID)
);


CREATE TABLE SCORE_INFO
(
	PROJECT_ID VARCHAR2(20) NOT NULL,
	-- Y-已完成
	-- N-进行中
	-- U-未分配
	-- D-删除
	-- P-暂停
	STAT CHAR(1) NOT NULL,
	USER_ID CHAR(8) NOT NULL,
	CHECK_NO CHAR(2) NOT NULL,
	GRADE CHAR(1),
	SCORE NUMBER(10,0),
	CHECK_USER CHAR(8),
	DISTRIBUTE_ID NUMBER(10,0),
	PRIMARY KEY (PROJECT_ID, STAT, USER_ID, CHECK_NO)
);


CREATE TABLE SIGN_RECORD
(
	USER_ID CHAR(8) NOT NULL,
	RECORD_DATE CHAR(8) NOT NULL,
	RECORD_TIME CHAR(6),
	RECORD_TYPE CHAR(1) NOT NULL,
	IS_MODIFIED CHAR(1),
	MODIFY_USER CHAR(8),
	PRIMARY KEY (USER_ID, RECORD_DATE, RECORD_TYPE)
);


CREATE TABLE SUB_SYS_VERSION
(
	ID NUMBER(19,0) NOT NULL,
	VERSION_ID VARCHAR2(20) NOT NULL,
	SYS_NAME VARCHAR2(10),
	PRE_VERSION VARCHAR2(20),
	NEXT_VERSION VARCHAR2(20),
	PRIMARY KEY (ID)
);


CREATE TABLE UPDATE_STEP
(
	ID NUMBER(19,0) NOT NULL,
	STEP NUMBER(10,0),
	MEMO VARCHAR2(250),
	VERSION_ID VARCHAR2(20) NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE VERSION_HIS
(
	VERSION_ID VARCHAR2(20) NOT NULL,
	PLAN_RELEASE_DATE CHAR(8) NOT NULL,
	IS_RELEASED CHAR(1),
	RELEASE_DATE CHAR(8),
	IS_CHECKED CHAR(1),
	INPUT_USER CHAR(8),
	INPUT_DATE CHAR(8),
	MEMO VARCHAR2(100),
	PRIMARY KEY (VERSION_ID)
);


CREATE TABLE WORK_DAILY
(
	ID NUMBER(10,0) NOT NULL,
	DISTRIBUTE_ID NUMBER(10,0) NOT NULL,
	WORK_ID VARCHAR2(8),
	WORK_DATE CHAR(8),
	USER_ID CHAR(8),
	CONTENT VARCHAR2(250),
	INPUT_DATE CHAR(8),
	PRIMARY KEY (ID)
);


CREATE TABLE WORK_DISTRIBUTE
(
	DISTRIBUTE_ID NUMBER(10,0) NOT NULL,
	WORK_ID VARCHAR2(8) NOT NULL,
	USER_ID CHAR(8),
	START_DATE CHAR(8),
	END_DATE CHAR(8),
	CONTENT VARCHAR2(250),
	WORK_STATUS CHAR(1),
	INPUT_USER CHAR(8),
	INPUT_DATE CHAR(8),
	NOTIFY_DAY NUMBER(10,0),
	PRIMARY KEY (DISTRIBUTE_ID)
);


CREATE TABLE WORK_LIST
(
	WORK_ID VARCHAR2(8) NOT NULL,
	WORK_NAME VARCHAR2(20),
	CONTENT VARCHAR2(250),
	WORK_STATUS CHAR(1),
	START_DATE CHAR(8),
	END_DATE CHAR(8),
	INPUT_USER CHAR(8),
	INPUT_DATE CHAR(8),
	IS_MAIN CHAR(1),
	PRIMARY KEY (WORK_ID)
);



/* Create Foreign Keys */

ALTER TABLE MAIL_CONFIG
	ADD FOREIGN KEY (FUNCTION_ID)
	REFERENCES FUNCTION_DEF (FUNCTION_ID)
;


ALTER TABLE PROJECT_DISTRIBUTE
	ADD FOREIGN KEY (MILE_STONE_ID)
	REFERENCES MILE_STONE (MILE_STONE_ID)
;


ALTER TABLE PARTICIPATE_LIST
	ADD FOREIGN KEY (MODULE_ID)
	REFERENCES MODULE_DEF (MODULE_ID)
;


ALTER TABLE PROJECT_DISTRIBUTE
	ADD FOREIGN KEY (MODULE_ID)
	REFERENCES MODULE_DEF (MODULE_ID)
;


ALTER TABLE MODULE_DEF
	ADD FOREIGN KEY (PRODUCT_CODE)
	REFERENCES PRODUCT_DEF (PRODUCT_CODE)
;


ALTER TABLE CHANGE_DETAIL
	ADD FOREIGN KEY (RECORD_ID)
	REFERENCES PROJECT_CHG_RECORD (RECORD_ID)
;


ALTER TABLE DAILY_RECORD
	ADD FOREIGN KEY (ID)
	REFERENCES PROJECT_DISTRIBUTE (ID)
;


ALTER TABLE CHANGE_RECORD
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE ISSUE_RECORD
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE MAIN_PLAN_JOB
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE MILE_STONE
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE PROGRAM_LIST
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE PROJECT_DISTRIBUTE
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE PROJECT_FILES
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE PROJECT_RELATION
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE REQUIRE_CHANGES
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE REVIEW_RECORD
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE RISK_RECORD
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECT_LIST (PROJECT_ID)
;


ALTER TABLE PROJECT_LIST
	ADD FOREIGN KEY (SCALE_ID)
	REFERENCES SCALE_DEF (SCALE_ID)
;


ALTER TABLE BATCH_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE CODE_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE DATABASE_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE DATA_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE PARAM_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE PROJECT_FILES
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE PROJECT_LIST
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE REPORT_CHG
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE SUB_SYS_VERSION
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE UPDATE_STEP
	ADD FOREIGN KEY (VERSION_ID)
	REFERENCES VERSION_HIS (VERSION_ID)
;


ALTER TABLE DAILY_ACHIEVE
	ADD FOREIGN KEY (DISTRIBUTE_ID)
	REFERENCES WORK_DISTRIBUTE (DISTRIBUTE_ID)
;


ALTER TABLE DAILY_ISSUE
	ADD FOREIGN KEY (DISTRIBUTE_ID)
	REFERENCES WORK_DISTRIBUTE (DISTRIBUTE_ID)
;


ALTER TABLE WORK_DAILY
	ADD FOREIGN KEY (DISTRIBUTE_ID)
	REFERENCES WORK_DISTRIBUTE (DISTRIBUTE_ID)
;


ALTER TABLE WORK_DISTRIBUTE
	ADD FOREIGN KEY (WORK_ID)
	REFERENCES WORK_LIST (WORK_ID)
;



/* Comments */

COMMENT ON COLUMN CHANGE_RECORD.CHANGE_ID IS 'PROJECT_ID-BG[00-99]';
COMMENT ON COLUMN DAILY_RECORD.STEP IS 'D-dev
S-sit
U-uat
P-prd';
COMMENT ON COLUMN DAILY_RECORD.CHECKED IS 'Y=通过
N=未处理
R=拒绝';
COMMENT ON COLUMN ISSUE_RECORD.STAT IS 'Y-已完成
N-进行中
U-未分配
D-删除
P-暂停';
COMMENT ON COLUMN PROJECT_CHG_RECORD.MODULE IS 'mps
cps
...';
COMMENT ON COLUMN PROJECT_DISTRIBUTE.STEP IS 'D-dev
S-sit
U-uat
P-prd';
COMMENT ON COLUMN PROJECT_DISTRIBUTE.IS_DONE IS 'Y-完成
N-进行中';
COMMENT ON COLUMN PROJECT_LIST.STAT IS 'Y-已完成
N-进行中
U-未分配
D-删除
P-暂停';
COMMENT ON COLUMN PROJECT_LIST.IS_IN_CONTRACT IS '字段复用为是否有附件';
COMMENT ON COLUMN PROJECT_LIST.PROJECT_CLASS IS 'A|产品研发类项目
B|售前支持类项目
C|合同实施类项目
D|运营维护类项目';
COMMENT ON COLUMN PROJECT_LIST.STEP IS 'D-dev
S-sit
U-uat
P-prd';
COMMENT ON COLUMN PROJECT_LIST.MAIN_PLAN_DIS IS 'Y 是
N 否';
COMMENT ON COLUMN RISK_RECORD.RISK_TYPE IS 'MARKET 市场
CUST 客户
TECH 技术
MANAGE 管理
PEOPLE 人员';
COMMENT ON COLUMN RISK_RECORD.RISK_LV IS 'L  提示
M 一般
H 严重';
COMMENT ON COLUMN RISK_RECORD.RISK_STATUS IS '趋势上升、趋势平稳、趋势下降，关闭';
COMMENT ON COLUMN SCORE_INFO.STAT IS 'Y-已完成
N-进行中
U-未分配
D-删除
P-暂停';




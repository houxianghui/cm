SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS ApplyTypeInfo;
DROP TABLE IF EXISTS BiUnitInfoTb;
DROP TABLE IF EXISTS CallFuncConf;




/* Create Tables */

CREATE TABLE ApplyTypeInfo
(
	ApplyTypeId varchar(6) NOT NULL,
	ApplyIdDesc varchar(50),
	-- 1是0否
	isPki varchar(1),
	-- 1是0否
	-- 
	isV2 varchar(1),
	-- 1是0否
	isV2Sign varchar(1),
	-- 1是0否
	-- 
	isIsamSign varchar(1),
	-- 1是0否
	isIsamTestAllO varchar(1),
	-- sam卡号6.7位
	ProdClassId varchar(2),
	isHTCard varchar(1),
	OperId varchar(9),
	CurrDate varchar(14),
	Remarks varchar(50),
	PRIMARY KEY (ApplyTypeId)
);


CREATE TABLE BiUnitInfoTb
(
	Unitid numeric(9) NOT NULL,
	Hyid numeric(4),
	HyName varchar(40),
	Unittype numeric(2),
	Unitstat numeric(2),
	Unitdes varchar(40),
	Chnshort varchar(16),
	unitperson varchar(20),
	Unittel varchar(20),
	Unitaddr varchar(40),
	-- 0：正式
	-- 1：测试
	-- 
	unittestflag varchar(1),
	LeadStore numeric(9),
	-- 1001
	-- A 一级机构
	-- B 二级机构
	Unitlevel varchar(1) NOT NULL,
	rsvd varchar(80),
	OperId varchar(9),
	CurrDate varchar(14),
	PRIMARY KEY (Unitid)
);


CREATE TABLE CallFuncConf
(
	CallerId varchar(6) NOT NULL,
	OperationType numeric(2),
	ManufacId varchar(4),
	ProdId varchar(2),
	ApplyTypeId varchar(6),
	-- 
	-- 
	func1 varchar(30),
	func2 varchar(30),
	func3 varchar(30),
	func4 varchar(30),
	OperId varchar(9),
	CurrDate varchar(14),
	Remarks varchar(50),
	PRIMARY KEY (CallerId)
);




INSERT INTO ACCOUNT VALUES(1,'Administrator','admin','ad_min11');

INSERT INTO VIOLATION VALUES(1,'Flash light',150,'The charged didnt use his flash lights before turning');
INSERT INTO VIOLATION VALUES(2,'Safety belt',150,'The charged didnt put his safety belt');
INSERT INTO VIOLATION VALUES(3,'Phone',200,'The charged used his phone while he was driving');

INSERT INTO PurchaseDepartment VALUES(1,'Marchal_Pur','MarchalMarchalPurTruite','Marchal');
INSERT INTO PurchaseDepartment VALUES(2,'Delire_Pur','DelireDelirePurGrenouille','Delire');

INSERT INTO Machine VALUES(1,'XE00',1,2,1);
INSERT INTO Machine VALUES(2,'XB00',2,1,1);

INSERT INTO Site_Mach VALUES(1,1);
INSERT INTO Site_Mach VALUES(2,1);
INSERT INTO Site_Mach VALUES(1,2);

INSERT INTO Maintenance(IDMAINTENANCE,DATE_START,ISSUE_DESCRIPTION,STATUS_MAINTENANCE,IDMANAGER,IDMACHINE)
	VALUES(1,to_date('20-12-2021','dd-mm-yyyy'),'This machine makes a loudly noise when it started',1,1,1);

INSERT INTO maint_worker VALUES(1,2);
INSERT INTO maint_worker VALUES(1,3);

commit;
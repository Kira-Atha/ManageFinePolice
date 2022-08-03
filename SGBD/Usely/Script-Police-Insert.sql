INSERT INTO ACCOUNT VALUES(1,'Administrator','admin','ad_min11');
INSERT INTO ACCOUNT VALUES(2,'Policeman','policeman0','policeman11');

INSERT INTO VIOLATION VALUES(1,'Flash light',150,'The charged didnt use his flash lights before turning');
INSERT INTO VIOLATION VALUES(2,'Safety belt',150,'The charged didnt put his safety belt');
INSERT INTO VIOLATION VALUES(3,'Phone',200,'The charged used his phone while he was driving');

INSERT INTO CHARGED VALUES(1,'John','Doe','02 Rue du coquelicot');
INSERT INTO CHARGED VALUES(2,'Johnny','Hoy','08 Rue du cimetière');

INSERT INTO TYPEVEHICLE VALUES(1,'Car');
INSERT INTO TYPEVEHICLE VALUES(2,'Moto');
INSERT INTO TYPEVEHICLE VALUES(3,'Truck');

INSERT INTO REGISTRATION VALUES(1,'0-UGN-201');

INSERT INTO VEHICLE VALUES(1,1,1);
INSERT INTO FINE VALUES(1,to_date('20-12-2021','dd-mm-yyyy'),'Call to order ignored',0,1,1,2);

INSERT INTO VIO_FIN VALUES(3,1);

commit;
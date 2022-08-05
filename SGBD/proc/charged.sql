CREATE OR REPLACE PACKAGE manage_charged IS
	PROCEDURE create_charged(in_firstname IN CHARGED.firstname%TYPE,in_lastname IN CHARGED.lastname%TYPE,in_address IN CHARGED.address%TYPE,new_id OUT CHARGED.IDCHARGED%TYPE);
END manage_charged;
/
CREATE SEQUENCE charged_seq
	INCREMENT BY 1;

CREATE OR REPLACE package body manage_charged IS
	PROCEDURE create_charged(in_firstname IN CHARGED.firstname%TYPE,in_lastname IN CHARGED.lastname%TYPE,in_address IN CHARGED.address%TYPE,new_id OUT CHARGED.IDCHARGED%TYPE) IS
		BEGIN
			INSERT INTO CHARGED (IDCHARGED,FIRSTNAME,LASTNAME,ADDRESS)
			VALUES (charged_seq.NEXTVAL,in_firstname,in_lastname,in_address)
			returning IDCHARGED into new_id;
		COMMIT;
		exception
			WHEN NOT_LOGGED_ON THEN
				dbms_output.put_line('You are not connected');
			WHEN LOGIN_DENIED THEN
				dbms_output.put_line('Connection to db impossible');
			WHEN STORAGE_ERROR THEN
				dbms_output.put_line('Not enough memory');
			WHEN VALUE_ERROR THEN
				dbms_output.put_line('Wrong values (format? number->String?)');
			WHEN NO_DATA_FOUND THEN
				dbms_output.put_line('IN values not found');
			WHEN others THEN
				dbms_output.put_line('Error -> ' ||substr(SQLERRM,1,60));
		END create_charged;
END manage_charged;
/
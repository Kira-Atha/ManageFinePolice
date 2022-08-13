CREATE OR REPLACE PACKAGE manage_registration IS

	PROCEDURE create_registration (SERIALNUMBER IN REGISTRATION.SERIALNUMBER%TYPE, id OUT REGISTRATION.IDREGISTRATION%TYPE);
	
	PROCEDURE delete_registration (id IN REGISTRATION.IDREGISTRATION%TYPE);
	
	PROCEDURE update_registration (id IN REGISTRATION.IDREGISTRATION%TYPE,new_SERIALNUMBER IN REGISTRATION.SERIALNUMBER%TYPE);
	
END manage_registration;
/

CREATE OR REPLACE package body manage_registration IS

	PROCEDURE create_registration (SERIALNUMBER IN REGISTRATION.SERIALNUMBER%TYPE, id OUT REGISTRATION.IDREGISTRATION%TYPE) IS
		
		BEGIN
			INSERT INTO REGISTRATION (IDREGISTRATION,SERIALNUMBER)
			VALUES (REGISTRATION_SEQ.NEXTVAL,SERIALNUMBER)
			RETURNING IDREGISTRATION into id;
			
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
				when Others then
					DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END create_registration;
		
	PROCEDURE delete_registration (id IN REGISTRATION.IDREGISTRATION%TYPE) IS
		BEGIN
			DELETE FROM REGISTRATION
			WHERE IDREGISTRATION = id;
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
				when Others then
					DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END delete_registration;
		
	PROCEDURE update_registration (id IN REGISTRATION.IDREGISTRATION%TYPE,new_SERIALNUMBER IN REGISTRATION.SERIALNUMBER%TYPE) IS
		
		BEGIN
			UPDATE REGISTRATION
			SET SERIALNUMBER = new_SERIALNUMBER
			WHERE IDREGISTRATION = id;
			
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
				when Others then
					DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END update_registration;

END manage_registration;
/

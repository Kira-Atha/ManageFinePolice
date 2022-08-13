CREATE OR REPLACE PACKAGE manage_vehicle_type IS

	PROCEDURE create_vehicle_type (name IN TYPEVEHICLE.NAME%TYPE, id OUT TYPEVEHICLE.IDTYPE%TYPE);
	
	PROCEDURE delete_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE);
	
	PROCEDURE update_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE,new_name IN TYPEVEHICLE.NAME%TYPE);
	
END manage_vehicle_type;
/

CREATE OR REPLACE package body manage_vehicle_type IS

	PROCEDURE create_vehicle_type (name IN TYPEVEHICLE.NAME%TYPE, id OUT TYPEVEHICLE.IDTYPE%TYPE) IS
		
		BEGIN
			INSERT INTO TYPEVEHICLE (IDTYPE,NAME)
			VALUES (TYPEVEHICLE_SEQ.NEXTVAL,name)
			RETURNING IDTYPE into id;
			
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
		END create_vehicle_type;
		
	PROCEDURE delete_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE) IS
		BEGIN
			DELETE FROM TYPEVEHICLE
			WHERE IDTYPE = id;
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
		END delete_vehicle_type;
		
	PROCEDURE update_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE,new_name IN TYPEVEHICLE.NAME%TYPE) IS
		
		BEGIN
			UPDATE TYPEVEHICLE
			SET NAME = new_name
			WHERE IDTYPE = id;
			
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
		END update_vehicle_type;

END manage_vehicle_type;
/

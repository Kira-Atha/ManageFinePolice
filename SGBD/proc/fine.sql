CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "FineDAO" AS
	import java.io.*;
	import java.sql.*;
	import oracle.sql.*;
	import oracle.jdbc.driver.*;
/
CREATE OR REPLACE TYPE tab_num IS table OF NUMBER;
/
CREATE OR REPLACE PACKAGE manage_fine IS
	TYPE tab_num IS table OF NUMBER;
	PROCEDURE create_fine	(in_date IN FINE.DATEFINE%TYPE,in_COMMENTFINE IN FINE.COMMENTFINE%TYPE,
							in_VALIDATED IN FINE.VALIDATED%TYPE,in_idVehicle IN FINE.IDVEHICLE%TYPE,in_idCharged IN FINE.IDCHARGED%TYPE,
							in_idAccount IN FINE.IDACCOUNT%TYPE,new_id OUT Fine.IDFINE%TYPE);
	PROCEDURE create_fine_violation(in_idsViolation IN tab_num,in_idFine IN FINE.IDFINE%TYPE);
	PROCEDURE delete_fine(in_idFine IN Fine.IDFINE%TYPE);
	PROCEDURE delete_fine_violation(in_idFine IN FINE.IDFINE%TYPE);
	PROCEDURE update_fine(in_idFine IN FINE.IDFINE%TYPE);
END manage_fine;
/
CREATE SEQUENCE fine_seq
	INCREMENT BY 1;
/
CREATE OR REPLACE package body manage_fine IS
	PROCEDURE create_fine(in_date IN FINE.DATEFINE%TYPE,in_COMMENTFINE IN FINE.COMMENTFINE%TYPE,
							in_VALIDATED IN FINE.VALIDATED%TYPE,in_idVehicle IN FINE.IDVEHICLE%TYPE,in_idCharged IN FINE.IDCHARGED%TYPE,
							in_idAccount IN FINE.IDACCOUNT%TYPE,new_id OUT Fine.IDFINE%TYPE) IS
		BEGIN
			INSERT INTO FINE (IDFINE,DATEFINE,COMMENTFINE,VALIDATED,IDVEHICLE,IDCHARGED,IDACCOUNT)
			VALUES (fine_seq.NEXTVAL,in_date,in_COMMENTFINE,in_VALIDATED,in_idVehicle,in_idCharged,in_idAccount)
			returning IDFINE into new_id;
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
		END create_fine;
		
	PROCEDURE create_fine_violation(in_idsViolation IN tab_num,in_idFine IN FINE.IDFINE%TYPE) IS
		BEGIN
			IF in_idsViolation.COUNT > 0
			THEN
				FOR i IN 0..in_idsViolation.COUNT
				LOOP
					INSERT INTO VIO_FIN (IDVIOLATION,IDFINE)
					VALUES (in_idsViolation(i),in_idFine);
				END LOOP;
				COMMIT;
			END IF;
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
			WHEN COLLECTION_IS_NULL THEN
				dbms_output.put_line('No collection');
			WHEN others THEN
				dbms_output.put_line('Error -> ' ||substr(SQLERRM,1,60));
		END create_fine_violation;
	
	PROCEDURE delete_fine(in_idFine IN Fine.IDFINE%type) IS
		BEGIN
			DELETE FROM FINE
			WHERE IDFINE = in_idFine;
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
		END delete_fine;
		
	PROCEDURE delete_fine_violation(in_idFine IN FINE.IDFINE%TYPE) IS
		BEGIN 
			DELETE FROM VIO_FIN
			WHERE IdFine = in_idFine;
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
		END delete_fine_violation;
		
		
	PROCEDURE update_fine(in_idFine IN FINE.IDFINE%TYPE) IS
		BEGIN
			UPDATE FINE 
			SET VALIDATED = 1
			WHERE IDFINE = in_idFine;
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
		END update_fine;
END manage_fine;
/
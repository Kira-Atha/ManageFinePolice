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
			when Others then
				DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END create_vehicle_type;
		
	PROCEDURE delete_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE) IS
		BEGIN
			DELETE FROM TYPEVEHICLE
			WHERE IDTYPE = id;
			COMMIT;
		END delete_vehicle_type;
		
	PROCEDURE update_vehicle_type (id IN TYPEVEHICLE.IDTYPE%TYPE,new_name IN TYPEVEHICLE.NAME%TYPE) IS
		
		BEGIN
			UPDATE TYPEVEHICLE
			SET NAME = new_name
			WHERE IDTYPE = id;
			
			COMMIT;
		END update_vehicle_type;

END manage_vehicle_type;
/

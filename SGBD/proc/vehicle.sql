CREATE OR REPLACE PACKAGE manage_vehicle IS
	PROCEDURE create_vehicle(id_type IN VEHICLE.IdType%TYPE,id_registration IN VEHICLE.IdRegistration%TYPE,new_id OUT VEHICLE.IDVEHICLE%TYPE);
END manage_vehicle;
/
CREATE OR REPLACE package body manage_vehicle IS
	PROCEDURE create_vehicle(id_type IN VEHICLE.IdType%TYPE,id_registration IN VEHICLE.IdRegistration%TYPE,new_id OUT VEHICLE.IDVEHICLE%TYPE) IS
		BEGIN
			INSERT INTO VEHICLE (IDVEHICLE,IDTYPE, IDREGISTRATION)
			VALUES (vehicle_seq.NEXTVAL,id_type,id_registration)
			returning IDVEHICLE into new_id;
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
		END create_vehicle;
END manage_vehicle;
/
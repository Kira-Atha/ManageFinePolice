CREATE OR REPLACE PACKAGE manage_violation IS

	PROCEDURE create_violation (name IN VIOLATION.NAME%TYPE ,price IN VIOLATION.PRICE%TYPE,description IN VIOLATION.DESCRIPTION%TYPE , viol_id OUT VIOLATION.IDVIOLATION%TYPE);
	
	PROCEDURE delete_violation (id IN VIOLATION.IDVIOLATION%TYPE);
	
	PROCEDURE update_violation (id IN VIOLATION.IDVIOLATION%TYPE,new_name IN VIOLATION.NAME%TYPE ,new_price IN VIOLATION.PRICE%TYPE,new_description IN VIOLATION.DESCRIPTION%TYPE);
	
END manage_violation;
/

CREATE OR REPLACE package body manage_violation IS

	PROCEDURE create_violation (name IN VIOLATION.NAME%TYPE ,price IN VIOLATION.PRICE%TYPE,description IN VIOLATION.DESCRIPTION%TYPE , viol_id OUT VIOLATION.IDVIOLATION%TYPE) IS
		
		BEGIN
			INSERT INTO VIOLATION (IDVIOLATION,NAME,PRICE, DESCRIPTION)
			VALUES (VIOLATION_SEQ.NEXTVAL,name,price,description)
			RETURNING IDVIOLATION into viol_id;
			
			COMMIT;
			exception
			when Others then
				DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END create_violation;
		
	PROCEDURE delete_violation (id IN VIOLATION.IDVIOLATION%TYPE) IS
		BEGIN
			DELETE FROM violation
			WHERE IDVIOLATION = id;
			COMMIT;
		END delete_violation;
		
	PROCEDURE update_violation (id IN VIOLATION.IDVIOLATION%TYPE,new_name IN VIOLATION.NAME%TYPE ,new_price IN VIOLATION.PRICE%TYPE,new_description IN VIOLATION.DESCRIPTION%TYPE) IS
		
		BEGIN
			
			UPDATE violation
			SET NAME = new_name, PRICE = new_price, DESCRIPTION = new_description
			WHERE IDVIOLATION = id;
			
			COMMIT;
		END update_violation;

END manage_violation;
/

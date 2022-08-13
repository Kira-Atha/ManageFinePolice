CREATE OR REPLACE PACKAGE manage_account IS
	PROCEDURE create_account (personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE,password IN ACCOUNT.PASSWORD%TYPE,type IN ACCOUNT.TYPEACCOUNT%TYPE , acc_id OUT ACCOUNT.IDACCOUNT%TYPE);
	
	PROCEDURE connect_account(personelNumber_check IN ACCOUNT.PERSONELNUMBER%TYPE,password_check IN ACCOUNT.PASSWORD%TYPE, type_account OUT ACCOUNT.TYPEACCOUNT%TYPE , id OUT ACCOUNT.IDACCOUNT%TYPE);
	
	PROCEDURE change_account (id IN ACCOUNT.IDACCOUNT%TYPE,new_personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE,new_password IN ACCOUNT.PASSWORD%TYPE);
	
	PROCEDURE change_password (pn IN ACCOUNT.PERSONELNUMBER%TYPE,new_password IN ACCOUNT.PASSWORD%TYPE);
	
	PROCEDURE change_personelNumber (id IN ACCOUNT.IDACCOUNT%TYPE, new_personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE);
	
	PROCEDURE delete_account(id IN ACCOUNT.IDACCOUNT%TYPE);
	
	PROCEDURE set_chief(id_chief IN ACCOUNT.IDACCOUNT%TYPE, id_subordinate IN ACCOUNT.IDACCOUNT%TYPE);
	
END manage_account;
/
CREATE OR REPLACE package body manage_account IS

	FUNCTION get_hash (password IN ACCOUNT.PASSWORD%TYPE) RETURN ACCOUNT.PASSWORD%TYPE IS
		v_secur VARCHAR2(30) := 'hgkh4zgkz5hlg';
		BEGIN
			RETURN DBMS_OBFUSCATION_TOOLKIT.MD5(input_string => v_secur ||password);
		END get_hash;
		
	PROCEDURE create_account (personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE,password IN ACCOUNT.PASSWORD%TYPE,type IN ACCOUNT.TYPEACCOUNT%TYPE , acc_id OUT ACCOUNT.IDACCOUNT%TYPE) IS
		hash_password ACCOUNT.PASSWORD%TYPE := get_hash(password);
		BEGIN
			INSERT INTO ACCOUNT (IDACCOUNT,PERSONELNUMBER, PASSWORD,TYPEACCOUNT )
			VALUES (ACCOUNT_SEQ.NEXTVAL,personelNumber,hash_password,type )
			returning IDACCOUNT into acc_id;
		
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
		END create_account;
		
	PROCEDURE connect_account(personelNumber_check IN ACCOUNT.PERSONELNUMBER%TYPE,password_check IN ACCOUNT.PASSWORD%TYPE,type_account OUT ACCOUNT.TYPEACCOUNT%TYPE , id OUT ACCOUNT.IDACCOUNT%TYPE)	IS
		hash_password ACCOUNT.PASSWORD%TYPE := get_hash(password_check);
		BEGIN
			SELECT TYPEACCOUNT , IDACCOUNT
			INTO type_account , id
			FROM ACCOUNT
			WHERE personelNumber = personelNumber_check
			AND password = hash_password;
		EXCEPTION
			WHEN NO_DATA_FOUND THEN
				type_account := 'nom utilisateur/mot de passe incorrect';
			WHEN NOT_LOGGED_ON THEN
				dbms_output.put_line('You are not connected');
			WHEN LOGIN_DENIED THEN
				dbms_output.put_line('Connection to db impossible');
			WHEN STORAGE_ERROR THEN
				dbms_output.put_line('Not enough memory');
			WHEN VALUE_ERROR THEN
				dbms_output.put_line('Wrong values (format? number->String?)');
			when Others then
				DBMS_OUTPUT.PUT_LINE('Erreur:'||substr(SQLERRM,1,40) );
		END connect_account;
			
		
		
	PROCEDURE change_account (id IN ACCOUNT.IDACCOUNT%TYPE,new_personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE,new_password IN ACCOUNT.PASSWORD%TYPE) IS

		hash_new_password ACCOUNT.PASSWORD%TYPE := get_hash(new_password);
		old_password ACCOUNT.PASSWORD%TYPE;
		BEGIN
			
			SELECT PASSWORD
			INTO old_password
			FROM ACCOUNT
			WHERE IDACCOUNT = id;
			
			IF old_password = new_password THEN
				hash_new_password := old_password;
			END IF;
			
			UPDATE account
			SET password = hash_new_password, PERSONELNUMBER = new_personelNumber
			WHERE IDACCOUNT = id;
			
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
		END change_account;
		
	PROCEDURE change_password (pn IN ACCOUNT.PERSONELNUMBER%TYPE,new_password IN ACCOUNT.PASSWORD%TYPE) IS
		hash_new_password ACCOUNT.PASSWORD%TYPE := get_hash(new_password);
		BEGIN
			UPDATE account
			SET password = hash_new_password
			WHERE personelNumber = pn;
			
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
		END change_password;	
		
	PROCEDURE change_personelNumber (id IN ACCOUNT.IDACCOUNT%TYPE, new_personelNumber IN ACCOUNT.PERSONELNUMBER%TYPE) IS
		BEGIN
			UPDATE account
			SET personelNumber = new_personelNumber
			WHERE IDACCOUNT = id;
			
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
		END change_personelNumber;	
		
	PROCEDURE delete_account (id IN ACCOUNT.IDACCOUNT%TYPE) IS
		BEGIN
			DELETE FROM account
			WHERE IDACCOUNT = id;
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
		END delete_account;
		
	PROCEDURE set_chief(id_chief IN ACCOUNT.IDACCOUNT%TYPE, id_subordinate IN ACCOUNT.IDACCOUNT%TYPE) IS
		BEGIN
			UPDATE account
			SET IDCHIEF = id_chief		
			WHERE IDACCOUNT = id_subordinate;
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
		END set_chief;
	
		
END manage_account;
/
USE myproject
DELIMITER $

CREATE PROCEDURE get_all_persons() BEGIN
	SELECT id,surname,lastname,email,phone_number,borndate FROM Person;
END$
CREATE PROCEDURE insert_person(IN p_id INT,IN p_surname VARCHAR(50), IN p_lastname VARCHAR(75),IN p_email VARCHAR(150),IN p_phone INT, IN p_borndate DATE) BEGIN
	INSERT INTO Person VALUES(p_id,p_surname,p_lastname,p_email,p_phone,p_borndate);
END$


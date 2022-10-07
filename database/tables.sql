use myproject;
CREATE TABLE Person(
id INT NOT NULL,
surname VARCHAR(50) NOT NULL,
lastname VARCHAR(75) NOT NULL,
email VARCHAR(150) NOT NULL,
phone_number INT NOT NULL,
borndate DATE NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE Project(
id INT NOT NULL AUTO_INCREMENT,
code VARCHAR(100) NOT NULL,
pro_name VARCHAR(100) NOT NULL,
initial_cost FLOAT NOT NULL,
pro_start_date TIMESTAMP NOT NULL,
manager INT NOT NULL,
CONSTRAINT PK_Project PRIMARY KEY(id),
UNIQUE(code),
FOREIGN KEY (manager) REFERENCES Person(id)
);

CREATE TABLE PermanentProject(
pro_id INT NOT NULL,
fixed_anual_cost FLOAT NOT NULL,
inflation_percentage FLOAT NOT NULL,
FOREIGN KEY(pro_id) REFERENCES Project(id)
);

CREATE TABLE TemporaryProject(
pro_id INT NOT NULL,
finish_date TIMESTAMP NOT NULL,
country VARCHAR(50) NOT NULL,
FOREIGN KEY(pro_id) REFERENCES Project(id)
);

CREATE TABLE ProjectTeam(
pro_id INT NOT NULL,
per_id INT NOT NULL,
UNIQUE(pro_id,per_id),
FOREIGN KEY(pro_id) REFERENCES Project(id),
FOREIGN KEY (per_id) REFERENCES Person(id)
);

CREATE TABLE ProjectLog(
id INT PRIMARY KEY AUTO_INCREMENT,
per_fullname VARCHAR(125) NOT NULL,
detail VARCHAR(255) NOT NULL,
pro_id INT not null,
FOREIGN KEY(pro_id) REFERENCES Project(id)
);
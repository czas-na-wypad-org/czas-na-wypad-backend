CREATE DATABASE czasnawypad;
USE czasnawypad;

CREATE TABLE user (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(200) NOT NULL,
    email VARCHAR(320),
    photo VARCHAR(500),
	is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE attraction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(100),
    price_level VARCHAR(50),
    description VARCHAR(5000),
    post_code VARCHAR(6),
    city VARCHAR(100),
    street VARCHAR(200),
    building_number VARCHAR(20),
    local_number VARCHAR(20),
    localization POINT NOT NULL SRID 4326, 
    phone VARCHAR(20),
    email VARCHAR(320),
    website VARCHAR(500),
	photo VARCHAR(500),
	is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    SPATIAL INDEX (localization)
) ENGINE=InnoDB;

CREATE TABLE attraction_ratings (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    attraction_id INT NOT NULL,
    user_id INT NOT NULL,
    rating TINYINT NOT NULL,
    notes VARCHAR(5000),
    date DATE NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE attraction_favourite (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    attraction_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE attraction_reservation (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    attraction_id INT NOT NULL,
    hour_from TIME,
    hour_to TIME,
    date DATE NOT NULL,
    price DECIMAL(9,2) NOT NULL,
    payed BOOLEAN NOT NULL,
    number_of_tickets INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE
);

CREATE TABLE attraction_hour (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    attraction_id INT NOT NULL,
    hour_from TIME,
    hour_to TIME,
    day_of_week VARCHAR(20) NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE
);

DELIMITER //

CREATE TRIGGER after_attraction_insert
AFTER INSERT ON attraction
FOR EACH ROW
BEGIN
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Monday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Tuesday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Wednesday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Thursday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Friday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Saturday');
    INSERT INTO attraction_hour (attraction_id, day_of_week) VALUES (NEW.id, 'Sunday');
END //

DELIMITER ;
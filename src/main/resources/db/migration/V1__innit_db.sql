CREATE TABLE IF NOT EXISTS user (
                      id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      login VARCHAR(50) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      surname VARCHAR(200) NOT NULL,
                      email VARCHAR(320),
                      photo VARCHAR(500),
                      roles VARCHAR(30) NOT NULL,
                      is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS attraction (
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
                            localization GEOMETRY NOT NULL SRID 4326,
                            phone VARCHAR(20),
                            email VARCHAR(320),
                            website VARCHAR(500),
                            photo VARCHAR(500),
                            is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                            SPATIAL INDEX (localization)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS attraction_ratings (
                                    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                    attraction_id INT NOT NULL,
                                    user_id INT NOT NULL,
                                    rating TINYINT NOT NULL,
                                    notes VARCHAR(5000),
                                    date DATE NOT NULL,
                                    FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE,
                                    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS attraction_favourite (
                                      id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                      attraction_id INT NOT NULL,
                                      user_id INT NOT NULL,
                                      FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE,
                                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attraction_reservation (
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

CREATE TABLE IF NOT EXISTS attraction_hour (
                                 id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                 attraction_id INT NOT NULL,
                                 hour_from TIME,
                                 hour_to TIME,
                                 day_of_week VARCHAR(20) NOT NULL,
                                 FOREIGN KEY (attraction_id) REFERENCES attraction(id) ON DELETE CASCADE
);

DELIMITER //

CREATE TRIGGER IF NOT EXISTS after_attraction_insert
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

INSERT INTO attraction (name, type, price_level, description, post_code, city, street, building_number, local_number, localization, phone, email, website, photo, is_deleted)
VALUES
('Stare Miasto', 'kultura i sztuka', 'standard', 
 'Historyczne serce Warszawy, wpisane na listę światowego dziedzictwa UNESCO. Odbudowane po zniszczeniach II wojny światowej, zachwyca malowniczymi kamienicami i klimatycznymi uliczkami.', 
 '00-272', 'Warszawa', 'Rynek Starego Miasta', '1', NULL, 
 ST_GeomFromText('POINT(21.012229 52.229676)', 4326), 
 '+48 22 000 00 00', 'info@starowka.waw.pl', 'https://www.starowka.waw.pl', NULL, FALSE),
('Łazienki Królewskie', 'na powietrzu', 'standard', 
 'Rozległy park z pałacem na wodzie, amfiteatrem i licznymi pomnikami. Popularne miejsce spacerów i koncertów chopinowskich.', 
 '00-460', 'Warszawa', 'Agrykola', '1', NULL, 
 ST_GeomFromText('POINT(21.035034 52.216667)', 4326), 
 '+48 22 506 00 28', 'kontakt@lazienki-krolewskie.pl', 'https://www.lazienki-krolewskie.pl', NULL, FALSE),
('Pałac Kultury i Nauki', 'kultura i sztuka', 'tanio', 
 'Najwyższy budynek w Polsce, oferujący taras widokowy z panoramą miasta, teatry, kina i muzea.', 
 '00-901', 'Warszawa', 'Plac Defilad', '1', NULL, 
 ST_GeomFromText('POINT(21.004444 52.231944)', 4326), 
 '+48 22 656 76 00', 'info@pkin.pl', 'https://www.pkin.pl', NULL, FALSE),
('Muzeum Powstania Warszawskiego', 'edukacja', 'standard', 
 'Interaktywne muzeum poświęcone Powstaniu Warszawskiemu z 1944 roku, prezentujące historię poprzez multimedialne ekspozycje.', 
 '00-844', 'Warszawa', 'Grzybowska', '79', NULL, 
 ST_GeomFromText('POINT(20.980801 52.232998)', 4326), 
 '+48 22 539 79 05', 'muzeum@1944.pl', 'https://www.1944.pl', NULL, FALSE),
('Muzeum Fryderyka Chopina', 'kultura i sztuka', 'tanio', 
 'Muzeum dedykowane życiu i twórczości Fryderyka Chopina, z bogatą kolekcją pamiątek i rękopisów kompozytora.', 
 '00-368', 'Warszawa', 'Okólnik', '1', NULL, 
 ST_GeomFromText('POINT(21.021111 52.235833)', 4326), 
 '+48 22 441 62 51', 'muzeum@chopin.pl', 'https://www.chopin.museum', NULL, FALSE),
('Centrum Nauki Kopernik', 'edukacja', 'standard', 
 'Nowoczesne centrum nauki z interaktywnymi wystawami, planetarium i laboratoriami, promujące naukę poprzez zabawę.', 
 '00-390', 'Warszawa', 'Wybrzeże Kościuszkowskie', '20', NULL, 
 ST_GeomFromText('POINT(21.030696 52.241031)', 4326), 
 '+48 22 596 41 00', 'kontakt@kopernik.org.pl', 'https://www.kopernik.org.pl', NULL, FALSE),
('Pałac w Wilanowie', 'kultura i sztuka', 'drogie', 
 'Barokowy pałac króla Jana III Sobieskiego z pięknymi ogrodami, muzeum i licznymi wydarzeniami kulturalnymi.', 
 '02-958', 'Warszawa', 'Stanisława Kostki Potockiego', '10', NULL, 
 ST_GeomFromText('POINT(21.091321 52.165250)', 4326), 
 '+48 22 842 81 01', 'kontakt@wilanow-palac.pl', 'https://www.wilanow-palac.pl', NULL, FALSE),
('Zamek Królewski w Warszawie', 'kultura i sztuka', 'drogie', 
 'Rekonstrukcja królewskiego zamku z bogatą historią, ekspozycjami sztuki i wydarzeniami kulturalnymi.', 
 '00-277', 'Warszawa', 'Plac Zamkowy', '4', NULL, 
 ST_GeomFromText('POINT(21.014139 52.247031)', 4326), 
 '+48 22 355 51 70', 'kontakt@zamek-krolewski.pl', 'https://www.zamek-krolewski.pl', NULL, FALSE),
('Zoo Warszawa', 'na powietrzu', 'tanio', 
 'Miejski ogród zoologiczny oferujący szeroki wybór egzotycznych i lokalnych gatunków zwierząt.', 
 '03-461', 'Warszawa', 'Ratuszowa', '1', NULL, 
 ST_GeomFromText('POINT(21.035002 52.258656)', 4326), 
 '+48 22 619 40 41', 'kontakt@zoo.waw.pl', 'https://www.zoo.waw.pl', NULL, FALSE),
('Multimedialny Park Fontann', 'rozrywka', 'standard', 
 'Popularne miejsce wieczornych pokazów świetlnych i muzycznych na fontannach w sercu Warszawy.', 
 '00-227', 'Warszawa', 'Skwer I Dywizji Pancernej WP', '1', NULL, 
 ST_GeomFromText('POINT(21.012334 52.254543)', 4326), 
 '+48 22 000 00 00', 'info@parkfontann.pl', 'https://www.parkfontann.pl', NULL, FALSE);

UPDATE attraction_hour
SET hour_from = '00:00:00', hour_to = '23:59:59'
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Stare Miasto');

UPDATE attraction_hour
SET hour_from = '06:00:00', hour_to = '20:00:00'
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Łazienki Królewskie');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '10:00:00'
    ELSE '09:00:00'
END,
hour_to = CASE
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '20:00:00'
    ELSE '18:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Pałac Kultury i Nauki');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    ELSE '10:00:00'
END,
hour_to = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '20:00:00'
    ELSE '18:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Muzeum Powstania Warszawskiego');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    ELSE '11:00:00'
END,
hour_to = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    ELSE '20:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Muzeum Fryderyka Chopina');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '10:00:00'
    ELSE '09:00:00'
END,
hour_to = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '19:00:00'
    ELSE '18:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Centrum Nauki Kopernik');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    WHEN day_of_week IN ('Tuesday', 'Wednesday', 'Thursday') THEN '09:30:00'
    ELSE '09:30:00'
END,
hour_to = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    WHEN day_of_week IN ('Tuesday', 'Wednesday', 'Thursday') THEN '16:00:00'
    ELSE '18:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Pałac w Wilanowie');

UPDATE attraction_hour
SET hour_from = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    ELSE '10:00:00'
END,
hour_to = CASE
    WHEN day_of_week = 'Monday' THEN NULL
    ELSE '17:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Zamek Królewski w Warszawie');

UPDATE attraction_hour
SET hour_from = '09:00:00',
hour_to = CASE
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '19:00:00'
    ELSE '18:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Zoo Warszawa');

UPDATE attraction_hour
SET hour_from = '08:00:00',
hour_to = CASE
    WHEN day_of_week IN ('Saturday', 'Sunday') THEN '23:00:00'
    ELSE '22:00:00'
END
WHERE attraction_id = (SELECT id FROM attraction WHERE name = 'Multimedialny Park Fontann');


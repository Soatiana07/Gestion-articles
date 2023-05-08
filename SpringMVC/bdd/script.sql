CREATE DATABASE contenu;
CREATE ROLE contenu LOGIN PASSWORD 'contenu';
ALTER DATABASE contenu OWNER TO contenu;

-- \q
-- psql -U contenu contenu
-- contenu

CREATE TABLE admin(
    id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    mdp VARCHAR(100)
);

CREATE TABLE auteur(
   id SERIAL PRIMARY KEY,
   email VARCHAR(100),
   mdp VARCHAR(100)
);

CREATE TABLE type(
    id SERIAL PRIMARY KEY ,
    nomtype VARCHAR(100)
);

CREATE TABLE contenu(
    id SERIAL PRIMARY KEY ,
    titre VARCHAR(100),
    idtype INTEGER,
    visuel VARCHAR(100), -- Tokony ovaina base64
    description TEXT,
    date1 DATE,
    date2 DATE,
    lieu VARCHAR(100),
    -- datecreation DATE DEFAULT CURRENT_TIMESTAMP,
    -- datepublication DATE,
    status INTEGER
);
ALTER TABLE contenu ADD FOREIGN KEY (idtype) REFERENCES type(id);

INSERT INTO admin(id, email, mdp) VALUES (DEFAULT, 'admin@gmail.com','admin');

INSERT INTO auteur(id, email, mdp) VALUES (DEFAULT, 'auteur1@gmail.com','auteur1');

INSERT INTO type(nomtype) VALUES ('article');
INSERT INTO type(nomtype) VALUES ('evenement');

ALTER TABLE Contenu ADD datecreation DATE DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE Contenu ADD datepublication DATE;

ALTER TABLE Contenu ADD idauteur INT;
ALTER TABLE Contenu ADD FOREIGN KEY (idauteur) REFERENCES auteur(id);

ALTER TABLE Contenu ADD priorite INT;
ALTER TABLE Contenu ALTER COLUMN priorite SET DEFAULT 0;
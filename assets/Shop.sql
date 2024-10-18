--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.1

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- ---------------------------------------------------------------------------------------------------
-- - Reconstruction de la base de données
-- ---------------------------------------------------------------------------------------------------

DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

-- ---------------------------------------------------------------------------------------------------
-- - Construction des articles en vente
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Articles (
	IdArticle 		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	Description		VARCHAR(100)	NOT NULL,
	Brand			VARCHAR(30)	NOT NULL,
	UnitaryPrice	FLOAT(8)	NOT NULL DEFAULT 0
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Construction des catégories
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Categories (
	IdCategory 		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	CatName			VARCHAR(30)	NOT NULL,
	Description		VARCHAR(100)	NOT NULL
) ENGINE = InnoDB;

ALTER TABLE T_Articles ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

-- ---------------------------------------------------------------------------------------------------
-- - Construction des utilisateurs
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE T_Users (
	IdUser 		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	Login			VARCHAR(20)	NOT NULL,
	Password		VARCHAR(20)	NOT NULL
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------
-- - Remplissage des tables
-- ---------------------------------------------------------------------------------------------------

INSERT INTO T_Categories(CatName, Description) VALUES('Matériel', 'Les appareils');
INSERT INTO T_Categories(CatName, Description) VALUES('Logiciel', 'Sinon tes appareils servent à rien');
INSERT INTO T_Categories(CatName, Description) VALUES('Accessoires', 'Comment utiliser tes appareils sinon ?');

INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Souris', 'Mickey', 30, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Clavier', 'Christian Claviers', 80, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Licence OS', 'tacOS 4v', 165, 2);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Tapis de souris', 'Logiccom 2x2', 3, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Clé usb 64 To', 'M. Bigstock', 285, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('PC Portable', 'Dalle', 360, 1);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('CD', 'Pov2000', 1, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Blu-Ray', 'Pov2010', 45, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Batterie PC Portable', 'ChangingEveryYear', 25, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Casque audio', 'Imfocused', 12, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Webcam 4K', 'Dimsung', 25, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('yPhone', 'Pineapple', 3000, 1);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Puce neuronale', 'Tespasla', 9999, 1);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Chargeur 500W', 'Xioami', 40, 3);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('GT RTX9070 Raytracing V8', 'Mvidia', 9070, 1);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Core i13 19850', 'Untel', 10, 1);

INSERT INTO T_Users(Login, Password) VALUES('toto', 'pizzaraclette');
INSERT INTO T_Users(Login, Password) VALUES('jean', 'motdepasse');
INSERT INTO T_Users(Login, Password) VALUES('fred', 'livraison');

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.2

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SHOW TABLES;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.3

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

DESCRIBE T_Articles;
DESCRIBE T_Categories;
DESCRIBE T_Users;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.4

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO T_Articles(Brand, Description) VALUES('Nimbus 2024', 'HP');
INSERT INTO T_Articles(Brand, Description) VALUES('Windows PVC Edition', 'Microbat');

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.5

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT * FROM T_Articles;
UPDATE T_Articles SET Description = 'CD rayé' WHERE IdArticle = 7;
SELECT * FROM T_Articles;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.6

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

DELETE FROM T_Articles WHERE IdArticle > 4 AND IdArticle < 9;
SELECT * FROM T_Articles;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.7

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT * FROM T_Articles WHERE UnitaryPrice > 100;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.8

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT * FROM T_Articles WHERE UnitaryPrice >= 50 AND UnitaryPrice <= 150;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.9

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT * FROM T_Articles ORDER BY UnitaryPrice;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.10

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT Description FROM T_Articles;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.11

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT Description, Brand, UnitaryPrice FROM T_Articles WHERE UnitaryPrice < 100 AND IdCategory = 3 ORDER BY UnitaryPrice;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.12

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO T_Categories(CatName, Description) VALUES('Matériel réseau', 'pour connecter les appareils entre eux');
INSERT INTO T_Categories(CatName, Description) VALUES('Audiovisuel', 'pour voir et entendre');
INSERT INTO T_Categories(CatName, Description) VALUES('Gaming', 'pour jouer');
INSERT INTO T_Categories(CatName, Description) VALUES('Smartphone', 'pour smartphoner');

INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('Routeur', 'Netshark', 30, 4);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('TV 98 Pouces ZLED', 'Bravo', 2000, 5);
INSERT INTO T_Articles(Description, Brand, UnitaryPrice, IdCategory) VALUES('PSX', 'Sunny', 600, 6);

SELECT * FROM T_Articles;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 1.13

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

SELECT A.IdArticle, A.Description, A.Brand, A.UnitaryPrice, C.CatName FROM T_Articles A RIGHT JOIN T_Categories C ON A.IdCategory = C.IdCategory WHERE A.IdArticle BETWEEN 11 AND 16 AND C.IdCategory IN (1, 2, 7) ORDER BY A.UnitaryPrice ASC;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICE 11

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE USER 'antho'@'localhost' IDENTIFIED BY 'Toto';
GRANT ALL PRIVILEGES ON Shop.* TO 'antho'@'localhost';
FLUSH PRIVILEGES;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- EXERCICES 13/14 (Ajout du système de panier et commande)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE T_Carts (
	IdCart		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	IdUser		INT(4)
) ENGINE = InnoDB;

CREATE TABLE TR_ArticleCarts (
	IdArticleCart	INT(4)	PRIMARY KEY AUTO_INCREMENT,
	IdCart			INT(4),
	IdArticle		INT(4),
	Quantity		INT(4)
) ENGINE = InnoDB;

ALTER TABLE T_Carts ADD FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser);
ALTER TABLE TR_ArticleCarts ADD FOREIGN KEY(IdCart) REFERENCES T_Carts(IdCart);
ALTER TABLE TR_ArticleCarts ADD FOREIGN KEY(IdArticle) REFERENCES T_Articles(IdArticle);

CREATE TABLE T_Orders (
	IdOrder		INT(4)		PRIMARY KEY	AUTO_INCREMENT,
	IdCart		INT(4),
	Status		INT(1) 		DEFAULT 0
) ENGINE = InnoDB;

ALTER TABLE T_Orders ADD FOREIGN KEY(IdCart) REFERENCES T_Carts(IdCart);
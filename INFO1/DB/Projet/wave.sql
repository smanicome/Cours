DROP TABLE IF EXISTS artiste CASCADE;
DROP TABLE IF EXISTS groupe CASCADE;
DROP TABLE IF EXISTS morceau CASCADE;
DROP TABLE IF EXISTS album CASCADE;
DROP TABLE IF EXISTS utilisateur CASCADE;
DROP TABLE IF EXISTS playlist CASCADE;

DROP TABLE IF EXISTS suivreU CASCADE;
DROP TABLE IF EXISTS suivreG CASCADE;
DROP TABLE IF EXISTS contient CASCADE;
DROP TABLE IF EXISTS compose CASCADE;
DROP TABLE IF EXISTS participe CASCADE;
DROP TABLE IF EXISTS appartient CASCADE;

CREATE TABLE artiste(
	idA serial primary key,
	nomA varchar(25) NOT NULL,
	prenomA varchar(25) NOT NULL,
	dateN date NOT NULL,
	dateM date
);

CREATE TABLE groupe(
	idG serial primary key,
	nomG varchar(25) NOT NULL,
	dateC date NOT NULL,
	nationalite varchar(25) NOT NULL,
	genre varchar(10) NOT NULL
);

CREATE TABLE morceau(
	idM serial primary key,
	titre varchar(25) NOT NULL,
	duree float NOT NULL,
	paroles text,
	idG int references groupe(idG)
);

CREATE TABLE album(
	idAl serial primary key,
	titre varchar(25) NOT NULL,
	parution date NOT NULL,
	image varchar(25),
	description text,
	idG int references groupe(idG)
);

CREATE TABLE utilisateur(
	pseudo varchar(25) primary key,
	mail varchar(25) NOT NULL,
	dateI date NOT NULL,
	passwd varchar(25) NOT NULL
);

CREATE TABLE playlist(
	idP serial primary key,
	titre varchar(25) NOT NULL,
	description text,
	acces boolean NOT NULL,
	pseudo varchar(25) references utilisateur(pseudo)
);

CREATE TABLE suivreU(
	pseudo varchar(25) references utilisateur(pseudo),
	pseudoSuivi varchar(25) references utilisateur(pseudo),
	check(pseudo!=pseudoSuivi),
	primary key(pseudo, pseudoSuivi)
);

CREATE TABLE suivreG(
	pseudo varchar(25) references utilisateur(pseudo),
	idG int references groupe(idG),
	primary key(pseudo, idG)
);

CREATE TABLE contient(
	idAl int references album(idAl),
	idM int references morceau(idM),
	idG int references groupe(idG),
	primary key(idAl, idM, idG)
);

CREATE TABLE compose(
	idP int references playlist(idP),
	idM int references morceau(idM),
	primary key(idP, idM)
);

CREATE TABLE participe(
	idA int references artiste(idA),
	idM int references morceau(idM),
	primary key(idA, idM)
);

CREATE TABLE appartient(
	idA int references artiste(idA),
	idG int references groupe(idG),
	dateIntegration date,
	dateDepart date default NULL,
	role varchar(25) NOT NULL,
	check(dateIntegration<dateDepart),
	primary key(idA, idG, dateIntegration)
);

---Filling table artiste :

COPY artiste FROM STDIN csv;
0,Plot,Thomas,1996-3-23,
1,Panneau,Pierre,1976-6-14,
2,Eponge,Lucie,1989-2-3,
3,Feuille,Rooée,1999-8-30,
4,Tarte,Léo,1993-11-6,
5,Ampoule,Kim,1975-9-11,2001-9-11
\.

---Filling table groupe :

COPY groupe FROM STDIN csv;
0,A,2018-12-25,FR,ROCK
1,B,2005-3-13,EN,CLASSIQUE
2,C,1964-7-6,GE,METAL
\.

---Filling table morceau :

COPY morceau FROM STDIN csv;
0,Blob,4.34,NULL,0
1,Plop,3.56,NULL,0
2,FLRK,6.24,NULL,2
\.

---Filling table album :

COPY album FROM STDIN csv;
0,LO,2019-12-20,NULL,NULL,0
\.

---Filling table utilisateur :

COPY utilisateur FROM STDIN csv;
foo,foo@gmail.com,2019-12-20,123456
foo2,foo2@gmail.com,2019-12-20,123456
\.

---Filling table playlist :

COPY playlist FROM STDIN csv;
0,ma_super_playlist,NULL,0,foo
\.

---Filling table suivreU :

COPY suivreU FROM STDIN csv;
foo,foo2
foo2,foo
\.

---Filling table suivreG :

COPY suivreG FROM STDIN csv;
foo,0
foo,2
foo2,1
foo2,2
\.

---Filling table contient :

COPY contient FROM STDIN csv;
0,0,0
0,1,0
\.

---Filling table compose :

COPY compose FROM STDIN csv;
0,0
0,1
0,2
\.

---Filling table participe :

COPY participe FROM STDIN csv;
3,2
1,0
1,1
2,0
4,1
\.

---Filling table appartient :

COPY appartient FROM STDIN csv;
3,2,2019-12-20,,batteur
\.

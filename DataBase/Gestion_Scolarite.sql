create database system_scolarite;
use system_scolarite;


--use master;
--drop database system_scolarite;
-- si la supression de la BD persiste .. veuillez supprimer toutes les connexion entrantes/sortantes , puis réessayer ...

create table PERSONNEL
(
	id_personnel int identity primary key,
	nom_personnel varchar(25),
	prenom_personnel varchar(25),
	date_naissance_personnel date,
	photo_personnel image,
	email_personnel varchar(100),
	telephone_personnel varchar(15),
	sexe varchar(10) not null,
	check(sexe in ('Homme','Femme')),
	adresse varchar(100),
	username varchar(20),
	mot_de_passe varchar(20),
);

create table ACTUALITE
(
	id_actualite int identity primary key,
	sujet varchar(25),
	description_actualite varchar(250),
	ajoute_par_personnel# int foreign key references PERSONNEL,
)

create table BRANCHE
(
	id_branche int identity primary key,
	libelle_branche varchar(50),
	prerequis_note float,
	description_branche varchar(250),
)

create table NIVEAU
(
	id_niveau int identity primary key,
	libelle_niveau varchar(50),
	description_niveau varchar(250),
)

create table GROUPE
(
	id_groupe int identity primary key,
	branche# int foreign key references BRANCHE,
	niveau# int foreign key references NIVEAU,
	libelle_grp varchar(10),
)


create table ETUDIANT
(
	code_massar varchar(20) primary key,
	nom varchar(25),
	prenom varchar(25),
	date_naissance date,
	date_inscription date default getdate(),
	email varchar(100),
	telephone varchar(15),
	a_deja_redouble bit,
	sexe varchar(10) not null,
	check(sexe in ('Homme','Femme')),
	adresse varchar(100),
	groupe# int foreign key references GROUPE,
	username varchar(20),
	mot_de_passe varchar(20),
)

create table PROFESSEUR
(
	Code_Pro_Nationnal varchar(20) primary key,
	Cin varchar(10) unique,
	Nom varchar(25),
	Prenom varchar(25),
	Date_Naissance date,
	Date_Commencement_Contrat date default getdate(),
	Type_Contrat varchar(10),
	Check(Type_Contrat in ('CDD','CDI')),
	Email varchar(100),
	Telephone varchar(15),
	sexe varchar(10) not null,
	check(sexe in ('Homme','Femme')),
	Adresse varchar(100),
	Situation_Familliale varchar(20),
	Check(Situation_Familliale in ('Celibataire','divorcé(e)','Marié(e)')),
	username varchar(20),
	mot_de_passe varchar(20),
)

create table MATIERE
(
	id_matiere int identity primary key,
	LBL_Matiere varchar(25),
	Date_Ajout date default getdate(),
	Coeff int,
)


create table ENSEIGNEMENT
(
	professeur# varchar(20) foreign key references PROFESSEUR,
	groupe# int foreign key references GROUPE,
	matiere# int foreign key references MATIERE,
	primary key(professeur#,groupe#,matiere#),
)

create table NOTE
(
	id_note int identity primary key,
	matiere# int foreign key references MATIERE,
	Valeur_Note float,
	etudiant_ varchar(20),
)

create TRIGGER ChECK_ETD 
	ON NOTE
    INSTEAD OF INSERT
  AS
  BEGIN
    if NOT EXISTS( SELECT etudiant_
			 FROM inserted
			 WHERE etudiant_ not in (select code_massar from ETUDIANT))
		BEGIN
				insert into NOTE
				select matiere#, Valeur_Note,etudiant_
				from inserted
		END
  END
GO

SET IDENTITY_INSERT [dbo].[PERSONNEL] ON 
GO
INSERT [dbo].[PERSONNEL] ([id_personnel], [nom_personnel], [prenom_personnel], [date_naissance_personnel], [photo_personnel], [email_personnel], [telephone_personnel], [sexe], [adresse], [username], [mot_de_passe]) VALUES (1, N'Hicham', N'Oussama', CAST(N'1997-01-01' AS Date), NULL, N'hichal@gmail.com', N'0612345768', N'Homme', N'adresse1', N'hicham', N'hicham123')
GO
SET IDENTITY_INSERT [dbo].[PERSONNEL] OFF

SET IDENTITY_INSERT [dbo].[BRANCHE] ON 
GO
INSERT [dbo].[BRANCHE] ([id_branche], [libelle_branche], [prerequis_note], [description_branche]) VALUES (1, N'Isi', 13, N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[BRANCHE] ([id_branche], [libelle_branche], [prerequis_note], [description_branche]) VALUES (2, N'Com', 11, N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[BRANCHE] ([id_branche], [libelle_branche], [prerequis_note], [description_branche]) VALUES (3, N'Mtd', 10, N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
SET IDENTITY_INSERT [dbo].[BRANCHE] OFF

SET IDENTITY_INSERT [dbo].[NIVEAU] ON 
GO
INSERT [dbo].[NIVEAU] ([id_niveau], [libelle_niveau], [description_niveau]) VALUES (1, N'Niveau 1', N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[NIVEAU] ([id_niveau], [libelle_niveau], [description_niveau]) VALUES (2, N'Niveau 2', N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[NIVEAU] ([id_niveau], [libelle_niveau], [description_niveau]) VALUES (3, N'Niveau 3', N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[NIVEAU] ([id_niveau], [libelle_niveau], [description_niveau]) VALUES (4, N'Niveau 4', N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
INSERT [dbo].[NIVEAU] ([id_niveau], [libelle_niveau], [description_niveau]) VALUES (5, N'Niveau 5', N'Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus porro iste earum, sequi aliquid aut cumque voluptatem perspiciatis qui debitis.')
GO
SET IDENTITY_INSERT [dbo].[NIVEAU] OFF

SET IDENTITY_INSERT [dbo].[GROUPE] ON 
GO
INSERT [dbo].[GROUPE] ([id_groupe], [branche#], [niveau#], [libelle_grp]) VALUES (1, 1, 3, N'isi 3')
GO
INSERT [dbo].[GROUPE] ([id_groupe], [branche#], [niveau#], [libelle_grp]) VALUES (2, 1, 4, N'isi 4')
GO
INSERT [dbo].[GROUPE] ([id_groupe], [branche#], [niveau#], [libelle_grp]) VALUES (3, 2, 1, N'com 1')
GO
INSERT [dbo].[GROUPE] ([id_groupe], [branche#], [niveau#], [libelle_grp]) VALUES (4, 3, 4, N'mtd 4')
GO
SET IDENTITY_INSERT [dbo].[GROUPE] OFF

INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H1197', N'Yaagoubi', N'Noureddine', CAST(N'1998-01-16' AS Date), CAST(N'2019-09-20' AS Date), N'noureddine@gmail.com', N'0642833827', 0, N'Homme', N'adresse1', 1, N'noureddine', N'noureddine123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H1278', N'Saffihh', N'Hichamm', CAST(N'1997-08-25' AS Date), CAST(N'2019-09-20' AS Date), N'hicham@gmail.com', N'0666201747', 0, N'Homme', N'adresse2', 1, N'tony', N'tony123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H1958', N'Ahmed ', N'tizniti', CAST(N'1997-07-29' AS Date), CAST(N'2020-06-01' AS Date), N'Ahmed@gmail.com', N'0662149873', 1, N'Homme', N'adresse4', 1, N'Ahmed', N'moumou123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H1697', N'Mustafa', N'nourawi', CAST(N'1997-02-26' AS Date), CAST(N'2020-06-01' AS Date), N'Mustafa@gmail.com', N'0763851924', 0, N'Homme', N'adresse7', 1, N'Mustafa', N'Mustafa123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H3987', N'Essirioui', N'Amina', CAST(N'1997-02-26' AS Date), CAST(N'2020-06-01' AS Date), N'Amina@gmail.com', N'0662148753', 0, N'Femme', N'adresse3', 1, N'moumou', N'moumou123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H4877', N'Nisrine', N'Hadiwi', CAST(N'1997-08-25' AS Date), CAST(N'2020-06-01' AS Date), N'Nisrine@gmail.com', N'0684793215', 0, N'Femme', N'adresse5', 1, N'Nisrine', N'Nisrine123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H55439', N'abdlawi', N'Adam', CAST(N'1995-05-02' AS Date), CAST(N'2020-06-01' AS Date), N'Adam@gmail.com', N'0598721674', 0, N'Homme', N'adresse9', 1, N'Adam', N'Adam123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H6746', N'Israe', N'Berber', CAST(N'2001-06-05' AS Date), CAST(N'2020-06-01' AS Date), N'israe@gmail.com', N'0236789415', 0, N'Femme', N'adresse 10', 2, N'israe', N'israe123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H9173', N'Aamari', N'Roqaya', CAST(N'1995-02-15' AS Date), CAST(N'2020-06-01' AS Date), N'Roqaya@gmail.com', N'0874923641', 0, N'Femme', N'adresse8', 1, N'Roqaya', N'Roqaya123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H9821', N'Nihal', N'Bkkay', CAST(N'1996-06-09' AS Date), CAST(N'2020-06-01' AS Date), N'Nihal@gmail.com', N'0598721634', 1, N'Femme', N'adresse6', 1, N'Nihal', N'Nihal123')
INSERT [dbo].[ETUDIANT] ([code_massar], [nom], [prenom], [date_naissance], [date_inscription], [email], [telephone], [a_deja_redouble], [sexe], [adresse], [groupe#], [username], [mot_de_passe]) VALUES (N'H9971', N'Lina', N'Nhari', CAST(N'1996-06-09' AS Date), CAST(N'2020-06-01' AS Date), N'Lina@gmail.com', N'0598721634', 1, N'Femme', N'adresse7', 1, N'Lina', N'Lina123')

INSERT [dbo].[PROFESSEUR] ([Code_Pro_Nationnal], [Cin], [Nom], [Prenom], [Date_Naissance], [Date_Commencement_Contrat], [Type_Contrat], [Email], [Telephone], [sexe], [Adresse], [Situation_Familliale], [username], [mot_de_passe]) VALUES (N'P1', N'F123', N'Ziane', N'Mohammed', CAST(N'1995-01-01' AS Date), CAST(N'2020-02-28' AS Date), N'CDI', N'ziane@gmail.com', N'0606060606', N'Homme', N'adresse1', N'Celibataire', N'ziane', N'ziane123')

SET IDENTITY_INSERT [dbo].[MATIERE] ON 
GO
INSERT [dbo].[MATIERE] ([id_matiere], [LBL_Matiere], [Date_Ajout], [Coeff]) VALUES (1, N'Français', CAST(N'2020-05-28' AS Date), 5)
GO
INSERT [dbo].[MATIERE] ([id_matiere], [LBL_Matiere], [Date_Ajout], [Coeff]) VALUES (2, N'Asp.Net', CAST(N'2020-05-28' AS Date), 5)
GO
SET IDENTITY_INSERT [dbo].[MATIERE] OFF

INSERT [dbo].[ENSEIGNEMENT] ([professeur#], [groupe#], [matiere#]) VALUES (N'P1', 1, 1)

INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 15, N'H1197')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 9, N'H1197')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 17.5, N'H1197')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (2, 17.5, N'H1197')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 13, N'H1278')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 7.5, N'H1278')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (1, 18, N'H1278')
INSERT [dbo].[NOTE] ([matiere#], [Valeur_Note], [etudiant_]) VALUES (2, 15, N'H1278')

--utilitaire---------------------
--delete from note
--DBCC CHECKIDENT ('NOTE', RESEED, 0) -- redefinir IDENITY à 0
--select * from note
----------------------------------

--TODO----------------------------------
-- renommer les grp
---------------------------------------

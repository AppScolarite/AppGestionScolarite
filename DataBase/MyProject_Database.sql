create database MyProject_Database;
use MyProject_Database;

create table SEXE
(
Id_Sexe int primary key,
LBL_Sexe varchar(25),
)


create table ETUDIANT
(
Code_Massar varchar(20) primary key,
Nom varchar(25),
Prenom varchar(25),
Date_Naissance date,
Date_Inscription date default getdate(),
Email varchar(100),
Telephone varchar(15),
A_Deja_Redouble bit,
Sexe# int foreign key references SEXE,
Adresse varchar(100),
)

create table personnel
(
	ID_personnel int identity primary key,
	nom_personnel varchar(25),
	prenom_personnel varchar(25),
	Date_Naissance_personnel date,
	Photo_personnel image,
	Email_personnel varchar(100),
	Telephone_personnel varchar(15),
	Sexe# int foreign key references SEXE,
	Adresse varchar(100)
)


create table MATIERE
(
Id_Matiere int identity primary key,
LBL_Matiere varchar(25),
Date_Ajout date default getdate(),
Coeff int,
)


create table Professeur
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
Nbr_Annee_Experience int,
Sexe# int foreign key references SEXE,
Adresse varchar(100),
Situation_Familliale varchar(20),
Check(Situation_Familliale in ('Celibataire','divorcé(e)','Marié(e)')),
MATIERE# int foreign key references MATIERE,
)

create table NOTE
(
Id_Note int identity primary key,
MATIERE# int foreign key references MATIERE,
ETUDIANT# varchar(20) foreign key references ETUDIANT,
Valeur_Note float,
)


--Employe
--Secretaire
--Directeur & Admin
--Absence



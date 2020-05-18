create database system_scolarite;
use system_scolarite;

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
	adresse varchar(100)
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
	inscrit_par_personnel# int foreign key references PERSONNEL,
	groupe# int foreign key references GROUPE,
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
)


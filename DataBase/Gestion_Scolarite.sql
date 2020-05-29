--create database system_scolarite;
--use system_scolarite;

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

insert into PERSONNEL (nom_personnel, prenom_personnel, date_naissance_personnel,email_personnel,telephone_personnel, sexe, adresse , username, mot_de_passe)
values
('Hicham','Oussama', '01/01/1997','hichal@gmail.com','0612345768','Homme','adresse1','hicham','hicham123')

insert into BRANCHE ( libelle_branche,prerequis_note,description_branche)
values 
('branche 1',15,'Descprtion 1')

insert into NIVEAU (libelle_niveau,description_niveau)
values 
('Niveau 1','Description1')

insert into GROUPE(branche#, niveau#,libelle_grp)
values
(1,1,'Groupe 1'),
(1,1,'Groupe 2'),
(1,1,'Groupe 3'),
(1,1,'Groupe 4')

insert into ETUDIANT (code_massar,nom,prenom ,date_naissance, date_inscription , email , telephone , a_deja_redouble , sexe , adresse ,groupe# ,username ,mot_de_passe )
values 
('H1','Yaagoubi','Noureddine','16/01/1998','20/09/2019','noureddine@gmail.com','0642833827',0,'Homme','adresse1',1,'noureddine','noureddine123'),
('H2','Saffih','Hicham','25/08/1997','20/09/2019','hicham@gmail.com','0666201740',0,'Homme','adresse2',1,'tony','tony123')

insert into PROFESSEUR 
values
('P1','F123','Ziane','Mohammed','01/01/1995','28/02/2020','CDI','ziane@gmail.com','0606060606','Homme','adresse1','Celibataire','ziane','ziane123')

insert into MATIERE(LBL_Matiere,Date_Ajout,Coeff)
values
('Français','28/05/2020',5),
('Asp.Net','28/05/2020',5)

insert into NOTE (matiere#, Valeur_Note,etudiant_)
values 
(1,15.5,'h1'),
(1,9,'h1'),
(1,17.5,'h1'),

(1,13,'h2'),
(1,7.5,'h2'),
(1,2,'h2'),
(2,15,'h2')


insert into ENSEIGNEMENT
values
('P1',1,1)


--utilitaire---------------------
--DBCC CHECKIDENT ('NOTE', RESEED, 0) -- redefinir IDENITY à 0
--delete from note
--select * from note
----------------------------------

--TODO----------------------------------
-- renommer les grp
---------------------------------------

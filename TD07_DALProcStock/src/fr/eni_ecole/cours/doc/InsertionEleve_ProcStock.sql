create procedure insererEleve @nom nvarchar(50), @prenom nvarchar(50), @adresse nvarchar(100)
as
insert into eleves
(nom, prenom, adresse)
values
(@nom, @prenom, @adresse);



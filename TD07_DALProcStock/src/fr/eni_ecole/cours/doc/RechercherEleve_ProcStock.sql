create procedure rechercherEleve(@nom nvarchar(50), @prenom nvarchar(50))
AS
SELECT nom, prenom, adresse FROM eleves WHERE nom = @nom AND prenom = @prenom;



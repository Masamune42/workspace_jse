create procedure supprimerEleve @nom nvarchar(50), @prenom nvarchar(50)
as
delete from eleves
where nom=@nom
and prenom=@prenom;
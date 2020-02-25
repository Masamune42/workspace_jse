create procedure modifierEleve @nom nvarchar(50), @prenom nvarchar(50), @adresse nvarchar(100)
as
update eleves set adresse = @adresse
where nom=@nom
and prenom=@prenom;
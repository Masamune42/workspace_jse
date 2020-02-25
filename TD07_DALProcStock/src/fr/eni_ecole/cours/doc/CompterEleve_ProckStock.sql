CREATE function compterEleve() RETURNS int AS
begin
declare @nombre int;
select @nombre=count(*) from eleves;
return @nombre;
end
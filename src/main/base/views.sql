/*function get current duree validite*/
CREATE OR REPLACE FUNCTION getCurrentValidityPeriod()
    RETURNS INTEGER AS
$$
DECLARE
    period INTEGER;
BEGIN
    SELECT dureevalidite into period from ValiditeToken order by DateChangement DESC;
    return period;
end;
$$
    LANGUAGE plpgsql;

/*function add to timestamp*/
CREATE OR REPLACE FUNCTION getDateExpiration(date_gen timestamp)
    RETURNS TIMESTAMP AS
$$
DECLARE
    period   VARCHAR;
    date_exp TIMESTAMP;
BEGIN
    period := (CAST(getCurrentValidityPeriod() AS varchar)) || ' seconds';
    RAISE NOTICE '%',period;
--     date_exp:=(date_gen + interval period);
    select date_gen::timestamp + period::interval into date_exp;
    return date_exp;
end;
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION getDateExpiration(date_gen timestamp with time zone)
    RETURNS TIMESTAMP AS
$$
DECLARE
    period   VARCHAR;
    date_exp TIMESTAMP;
BEGIN
    period := (CAST(getCurrentValidityPeriod() AS varchar)) || ' seconds';
    RAISE NOTICE '%',period;
--     date_exp:=(date_gen + interval period);
    select date_gen::timestamp + period::interval into date_exp;
    return date_exp;
end;
$$
    LANGUAGE plpgsql;

/*trigger validate */
CREATE OR REPLACE FUNCTION setDateExpiration()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.Dategeneration IS NULL THEN
        NEW.Dategeneration := now();
    end if;
    IF NEW.dateexpiration IS NULL THEN
        NEW.dateexpiration := getDateExpiration(NEW.dategeneration);
    END IF;
    RETURN NEW;
end;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER expiration_trigger
    BEFORE INSERT
    ON Tokens
    FOR EACH ROW
EXECUTE PROCEDURE setDateExpiration();

CREATE TRIGGER expiration_admin_trigger
    BEFORE INSERT
    ON admintokens
    FOR EACH ROW
EXECUTE PROCEDURE setDateExpiration();
-- select getCurrentValidityPeriod();
-- select getDateExpiration('2022-11-18 9:30'::TIMESTAMP);

/*functio checkToken*/
CREATE OR REPLACE FUNCTION isTokenValid(token_value VARCHAR)
    RETURNS bool AS
$$
DECLARE
    curr_timestamp TIMESTAMP;
    exp_timestamps TIMESTAMP;
BEGIN
    curr_timestamp := now();
    select Dateexpiration into exp_timestamps from tokens where token = token_value;
    if curr_timestamp < exp_timestamps then
        return true;
    else
        return false;
    end if;
end;
$$
    LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION isAdminTokenValid(token_value VARCHAR)
    RETURNS bool AS
$$
DECLARE
    curr_timestamp TIMESTAMP;
    exp_timestamps TIMESTAMP;
BEGIN
    curr_timestamp := now();
    select Dateexpiration into exp_timestamps from admintokens where token = token_value;
    if curr_timestamp < exp_timestamps then
        return true;
    else
        return false;
    end if;
end;
$$
    LANGUAGE plpgsql;


/*view solde à valider*/
CREATE OR REPLACE VIEW v_solde_attente AS
SELECT *
FROM soldes
where valid = 0;

/*enchere non fini*/
create view ListEnchereNonFinished AS
select *
from encheres
where statut is false;



/*enchere gagnant avec le nom gagnant*/
CREATE OR REPLACE VIEW encheresgagnant AS
SELECT gagnants.idenchere,
       utilisateurs_gagnant.nom           as nomgagnant,
       utilisateurs_gagnant.idutilisateur as idgagnant,
       utilisateurs_vendeur.idutilisateur as idvendeur,
       utilisateurs_vendeur.nom           as nomvendeur,
       encheres.idproduit,
       produits.nomproduit,
       gagnants.montant
FROM gagnants
         JOIN utilisateurs as utilisateurs_gagnant ON gagnants.idutilisateur = utilisateurs_gagnant.idutilisateur
         JOIN encheres ON gagnants.idenchere = encheres.idenchere
         JOIN utilisateurs as utilisateurs_vendeur ON encheres.idutilisateur = utilisateurs_vendeur.idutilisateur
         JOIN produits ON encheres.idproduit = produits.idproduit;


/*view solde à valider*/
CREATE OR REPLACE VIEW v_solde_attente AS
SELECT *
FROM soldes
where valid = 0;

/*view gagnant encheres*/
-- CREATE OR REPLACE VIEW v_gagnant AS
-- SELECT *
-- FROM gagnants g
--          join utilisateurs u on g.idutilisateur = u.idutilisateur;

/*view statistiques par produit*/
-- CREATE OR REPLACE VIEW v_stat_produit AS
-- SELECT *
-- FROM produits p
--          join encheres e on p.idproduit = e.idproduit;
--
-- SELECT *
-- FROM encheres;

/*view produit*/
CREATE OR REPLACE VIEW v_produits AS
SELECT p.*, c.libelle
FROM produits p
         join categories c on c.idcategorie = p.idcategorie;

/*view enchere*/
CREATE OR REPLACE VIEW v_encheres AS
SELECT e.*, p.idcategorie
FROM encheres e
         join produits p on e.idproduit = p.idproduit;

/*view default value categorie*/
CREATE OR REPLACE VIEW v_default_value_categorie AS
SELECT idcategorie, 0 as default_val
FROM categories;


/*view mise*/
CREATE OR REPLACE VIEW v_stat_nbmise AS
SELECT t.*, c.libelle
FROM categories c,
     (SELECT idcategorie, max(nbmises) as nbmises
      from (SELECT idcategorie, count(*) as nbmises
            FROM mises m
                     join v_encheres e on m.idenchere = e.idenchere
            group by idcategorie
            UNION ALL
            SELECT *
            FROM v_default_value_categorie) t
      group by idcategorie order by count(*) desc) t
where t.idcategorie = c.idcategorie
order by nbmises desc;


CREATE OR REPLACE VIEW v_stat_avgmise AS
SELECT t.*, c.libelle
FROM categories c,
     (select idcategorie, max(moyennemise) as moyennemise
      from (SELECT idcategorie, avg(nbmises)::int as moyenneMise
            FROM v_encheres ve,
                 (SELECT m.idenchere, count(*) as nbmises FROM mises m group by m.idenchere) t
            where ve.idenchere = t.idenchere
            group by idcategorie
            UNION ALL
            SELECT *
            FROM v_default_value_categorie) t
      group by idcategorie
      order by max(moyenneMise) desc) t
where c.idcategorie = t.idcategorie
order by moyenneMise desc;



/*view nb encheres par categorie*/
CREATE OR REPLACE VIEW v_nbencheres_categorie AS
SELECT t.*, c.libelle
FROM categories c,
     (SELECT idcategorie,max(nbencheres) from
         (SELECT idcategorie, count(*) as nbencheres
          FROM encheres e
                   join produits p on e.idproduit = p.idproduit
          GROUP BY idcategorie
          UNION ALL
          SELECT * FROM v_default_value_categorie) t GROUP BY idcategorie order by max(nbencheres) desc) t
where c.idcategorie = t.idcategorie;

/*view historique mise*/
CREATE OR REPLACE VIEW v_historique_mise AS
SELECT m.*,e.idutilisateur as idproprietaire,e.idproduit FROM mises m join encheres e on m.idenchere = e.idenchere order by datemise desc;

/*view max mise*/
CREATE OR REPLACE VIEW v_max_mise AS
SELECT vhm.* FROM v_historique_mise vhm,
(SELECT idenchere,max(montant) montant FROM v_historique_mise group by idenchere) t
WHERE t.idenchere=vhm.idenchere and vhm.montant=t.montant;

/* view objet enchere */
CREATE OR REPLACE VIEW enchere_globale AS
select encheres.idenchere, description, prix_min_enchere, duree, dateenchere, statut,
       utilisateurs.idutilisateur,nom,prenoms,datenaissance,email,password,montantsolde, genres.idgenre, genre,
       produits.idproduit,nomproduit, categories.idcategorie, libelle

from encheres join utilisateurs on encheres.idutilisateur = utilisateurs.idutilisateur
              join genres on utilisateurs.idgenre = genres.idgenre
              join produits on encheres.idproduit = produits.idproduit
              join categories on produits.idcategorie=categories.idcategorie;
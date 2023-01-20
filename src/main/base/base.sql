CREATE SEQUENCE admins_idadmin_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE categories_idcategorie_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE commisions_idcommisison_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE encheredurees_idduree_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE encheres_idenchere_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE genres_idgenre_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE mises_idmise_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE produits_idproduit_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE soldes_idsolde_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE utilisateurs_idutilisateur_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE categories
(
    idcategorie smallserial NOT NULL,
    libelle     varchar(50) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (idcategorie)
);

CREATE TABLE commisions
(
    idcommisison   smallserial               NOT NULL,
    pourcentage    numeric(4, 2)             NOT NULL,
    datechangement date DEFAULT CURRENT_DATE NOT NULL,
    CONSTRAINT pk_commisions PRIMARY KEY (datechangement)
);

CREATE TABLE encheredurees
(
    idduree        smallserial    NOT NULL,
    dureemin       numeric(10, 2) NOT NULL,
    dureemax       numeric(10, 2) NOT NULL,
    datechangement timestamp      NOT NULL,
    CONSTRAINT pk_encheredurees PRIMARY KEY (idduree)
);

CREATE TABLE genres
(
    idgenre smallserial NOT NULL,
    genre   varchar(7)  NOT NULL,
    CONSTRAINT pk_genres PRIMARY KEY (idgenre)
);

CREATE TABLE produits
(
    idproduit   integer DEFAULT nextval('produits_idproduit_seq'::regclass) NOT NULL,
    nomproduit  varchar(75)                                                 NOT NULL,
    idcategorie smallint                                                    NOT NULL,
    CONSTRAINT pk_produits PRIMARY KEY (idproduit)
);

CREATE TABLE utilisateurs
(
    idutilisateur integer DEFAULT nextval('utilisateurs_idutilisateur_seq'::regclass) NOT NULL,
    nom           varchar(75)                                                         NOT NULL,
    prenoms       varchar(100)                                                        NOT NULL,
    idgenre       smallint                                                            NOT NULL,
    datenaissance date                                                                NOT NULL,
    email         varchar(100)                                                        NOT NULL,
    "password"    char(34)                                                            NOT NULL,
    montantsolde  integer DEFAULT 0                                                   NOT NULL,
    CONSTRAINT pk_utilisateurs PRIMARY KEY (idutilisateur)
);

CREATE TABLE admins
(
    idadmin       smallserial  NOT NULL,
    nom           varchar(75)  NOT NULL,
    prenoms       varchar(100),
    idgenre       smallint     NOT NULL,
    datenaissance date         NOT NULL,
    identifiant   varchar(100) NOT NULL,
    "password"    char(34)     NOT NULL,
    CONSTRAINT pk_admins PRIMARY KEY (idadmin)
);

CREATE TABLE admintokens
(
    idadmintoken   serial      NOT NULL,
    idadmin        integer     NOT NULL,
    token          varchar(40) NOT NULL,
    dategeneration timestamp   NOT NULL DEFAULT now(),
    dateexpiration timestamp,
    CONSTRAINT pk_admintokens PRIMARY KEY (idadmintoken)
);

CREATE TABLE encheres
(
    idenchere        integer DEFAULT nextval('encheres_idenchere_seq'::regclass) NOT NULL,
    idutilisateur    integer                                                     NOT NULL,
    idproduit        integer                                                     NOT NULL,
    description      text                                                        NOT NULL,
    prix_min_enchere integer                                                     NOT NULL,
    duree            integer                                                     NOT NULL,
    dateEnchere      timestamp                                                   not null default current_timestamp,
    statut           boolean DEFAULT false                                       NOT NULL,
    CONSTRAINT pk_encheres PRIMARY KEY (idenchere)
);

CREATE TABLE gagnants
(
    idenchere     integer NOT NULL,
    idutilisateur bigint  NOT NULL,
    montant       integer NOT NULL
);

CREATE TABLE mises
(
    idmise       serial NOT NULL,
    idutilisateur integer                                                 NOT NULL,
    idenchere     bigint                                                  NOT NULL,
    montant       integer                                                 NOT NULL,
    datemise      timestamp DEFAULT CURRENT_TIMESTAMP                     NOT NULL,
    CONSTRAINT pk_mises PRIMARY KEY (idmise)
);

CREATE TABLE soldes
(
    idsolde       integer   DEFAULT nextval('soldes_idsolde_seq'::regclass) NOT NULL,
    idutilisateur integer                                                   NOT NULL,
    montant       integer                                                   NOT NULL,
    datedepot     timestamp DEFAULT CURRENT_TIMESTAMP                       NOT NULL,
    "valid"       smallint  DEFAULT 0                                       NOT NULL,
    CONSTRAINT pk_soldes PRIMARY KEY (idsolde)
);

CREATE TABLE tokens
(
    idtoken        serial                              NOT NULL,
    iduser         integer                             NOT NULL,
    token          varchar(40)                         NOT NULL,
    dategeneration timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    dateexpiration timestamp,
    CONSTRAINT pk_tokens PRIMARY KEY (idtoken)
);

CREATE TABLE ValiditeToken
(
    IdValidite     SERIAL  NOT NULL,
    DureeValidite  INTEGER NOT NULL,
    DateChangement timestamp DEFAULT now(),
    PRIMARY KEY (IdValidite)
);


ALTER TABLE admins
    ADD CONSTRAINT fk_admins_genres FOREIGN KEY (idgenre) REFERENCES genres (idgenre);

ALTER TABLE admintokens
    ADD CONSTRAINT fk_admintokens_admins FOREIGN KEY (idadmin) REFERENCES admins (idadmin);

ALTER TABLE encheres
    ADD CONSTRAINT fk_encheres_utilisateurs FOREIGN KEY (idutilisateur) REFERENCES utilisateurs (idutilisateur);

ALTER TABLE gagnants
    ADD CONSTRAINT fk_gagnants_encheres FOREIGN KEY (idenchere) REFERENCES encheres (idenchere);

ALTER TABLE gagnants
    ADD CONSTRAINT fk_gagnants_utilisateurs FOREIGN KEY (idutilisateur) REFERENCES utilisateurs (idutilisateur);

ALTER TABLE mises
    ADD CONSTRAINT fk_mises_encheres FOREIGN KEY (idenchere) REFERENCES encheres (idenchere);

ALTER TABLE mises
    ADD CONSTRAINT fk_mises_utilisateurs FOREIGN KEY (idutilisateur) REFERENCES utilisateurs (idutilisateur);

ALTER TABLE produits
    ADD CONSTRAINT fk_produits_categories FOREIGN KEY (idcategorie) REFERENCES categories (idcategorie)  ON UPDATE CASCADE;

ALTER TABLE soldes
    ADD CONSTRAINT fk_soldes_utilisateurs FOREIGN KEY (idutilisateur) REFERENCES utilisateurs (idutilisateur);

ALTER TABLE tokens
    ADD CONSTRAINT fk_tokens_utilisateurs FOREIGN KEY (iduser) REFERENCES utilisateurs (idutilisateur);

ALTER TABLE utilisateurs
    ADD CONSTRAINT fk_utilisateurs_genres FOREIGN KEY (idgenre) REFERENCES genres (idgenre);



CREATE OR REPLACE FUNCTION md5(bytea in VARCHAR)
    RETURNS VARCHAR(34) AS
$$
begin
    return crypt(bytea, gen_salt('md5'))::varchar;
end;
$$
    LANGUAGE plpgsql;
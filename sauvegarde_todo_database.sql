--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2017-11-01 16:30:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 192 (class 1255 OID 32871)
-- Name: journalisertodo(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION journalisertodo() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    BEGIN
INSERT into journal(operation, objet, donnee, moment) VALUES(TG_OP, TG_TABLE_NAME, OLD.titre, NOW());
    return OLD;
END
$$;


ALTER FUNCTION public.journalisertodo() OWNER TO postgres;

--
-- TOC entry 191 (class 1255 OID 32868)
-- Name: journalisertodoinsert(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION journalisertodoinsert() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    BEGIN
INSERT into journal(operation, objet, donnee, moment) VALUES(TG_OP, TG_TABLE_NAME, NEW.titre, NOW());
    return NEW;
END
$$;


ALTER FUNCTION public.journalisertodoinsert() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 32840)
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE journal (
    id_journal bigint NOT NULL,
    operation text,
    donnee text,
    moment timestamp without time zone,
    objet text
);


ALTER TABLE journal OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 32838)
-- Name: journal_id_journal_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE journal_id_journal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE journal_id_journal_seq OWNER TO postgres;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 189
-- Name: journal_id_journal_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE journal_id_journal_seq OWNED BY journal.id_journal;


--
-- TOC entry 186 (class 1259 OID 24624)
-- Name: personnes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE personnes (
    id_personne bigint NOT NULL,
    nom text,
    prenom text,
    nombre_de_todo_fait integer
);


ALTER TABLE personnes OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24622)
-- Name: personnes_id_personne_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE personnes_id_personne_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE personnes_id_personne_seq OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 185
-- Name: personnes_id_personne_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE personnes_id_personne_seq OWNED BY personnes.id_personne;


--
-- TOC entry 188 (class 1259 OID 24677)
-- Name: todo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE todo (
    id_todo bigint NOT NULL,
    titre text,
    description text,
    date text,
    fini text,
    id_personne bigint
);


ALTER TABLE todo OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24675)
-- Name: todo_id_todo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE todo_id_todo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE todo_id_todo_seq OWNER TO postgres;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 187
-- Name: todo_id_todo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE todo_id_todo_seq OWNED BY todo.id_todo;


--
-- TOC entry 2020 (class 2604 OID 32843)
-- Name: journal id_journal; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY journal ALTER COLUMN id_journal SET DEFAULT nextval('journal_id_journal_seq'::regclass);


--
-- TOC entry 2018 (class 2604 OID 24627)
-- Name: personnes id_personne; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personnes ALTER COLUMN id_personne SET DEFAULT nextval('personnes_id_personne_seq'::regclass);


--
-- TOC entry 2019 (class 2604 OID 24680)
-- Name: todo id_todo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY todo ALTER COLUMN id_todo SET DEFAULT nextval('todo_id_todo_seq'::regclass);


--
-- TOC entry 2152 (class 0 OID 32840)
-- Dependencies: 190
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (1, 'test', NULL, NULL, NULL);
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (2, 'test', NULL, '2017-10-18 12:47:09.849141', NULL);
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (5, 'INSERT', 'test', '2017-10-18 13:22:10.755789', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (6, 'UPDATE', 'ezdzed', '2017-10-18 13:25:32.520437', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (7, 'INSERT', 'test', '2017-10-18 13:27:49.071544', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (9, 'DELETE', 'ezdzed', '2017-10-18 13:30:19.713061', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (10, 'DELETE', 'ezdzed', '2017-10-18 13:30:32.845986', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (12, 'INSERT', 'test', '2017-10-18 13:34:35.425046', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (13, 'UPDATE', 'ezdzed', '2017-10-18 13:34:44.696252', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (14, 'DELETE', 'Lire un livre
', '2017-10-18 18:24:23.126898', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (15, 'UPDATE', 'Lire un livre
', '2017-10-18 18:25:26.190338', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (16, 'DELETE', 'mMl', '2017-10-18 18:25:44.870137', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (18, 'DELETE', 'mMl', '2017-10-18 18:27:03.138771', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (19, 'DELETE', 'mMl', '2017-10-18 18:27:35.478786', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (20, 'DELETE', 'mMl', '2017-10-18 18:27:36.378039', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (21, 'DELETE', 'mMl', '2017-10-18 18:27:36.943772', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (22, 'DELETE', 'mMl', '2017-10-18 18:27:37.599171', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (23, 'DELETE', 'mMl', '2017-10-18 18:27:38.264874', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (24, 'DELETE', 'mMl', '2017-10-18 18:27:38.916703', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (25, 'DELETE', 'mMl', '2017-10-18 18:27:39.600836', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (26, 'UPDATE', 'gyuii', '2017-10-18 18:28:30.052056', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (28, 'INSERT', 'brtdtb', '2017-10-18 18:29:06.532948', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (31, 'INSERT', 'brtdtb', '2017-10-18 18:36:21.68568', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (32, 'UPDATE', 'gyuii', '2017-10-18 18:37:40.304961', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (33, 'DELETE', 'gyuii', '2017-10-18 18:37:59.866737', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (34, 'INSERT', 'bsrtyndfg', '2017-10-18 18:40:29.057043', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (35, 'DELETE', 'bsrtyndfg', '2017-10-18 18:40:50.741202', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (36, 'UPDATE', 'bsrtyndfg', '2017-10-18 18:41:15.444267', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (37, 'UPDATE', 'bsrtyndfg', '2017-10-18 18:41:52.872648', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (38, 'DELETE', 'bsrtyndfg', '2017-10-18 18:42:04.9509', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (39, 'INSERT', 'bsrtyndfg', '2017-10-18 18:42:18.143017', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (40, 'UPDATE', 'grttrg', '2017-10-18 18:42:28.778047', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (41, 'DELETE', 'bsrtyndfg', '2017-10-18 18:46:44.625447', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (42, 'UPDATE', 'grttrg', '2017-10-18 18:47:35.196788', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (44, 'DELETE', 'Yolo', '2017-10-18 18:48:43.793896', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (45, 'UPDATE', 'grtrtbrt', '2017-10-18 18:48:58.327658', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (47, 'UPDATE', 'ferfer', '2017-10-18 18:52:21.081994', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (48, 'DELETE', 'Hey', '2017-10-18 18:52:42.057767', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (49, 'UPDATE', 'test', '2017-10-18 18:52:55.833153', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (50, 'INSERT', 'bsrtyndfg', '2017-10-18 18:53:07.55974', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (51, 'DELETE', 'Hey', '2017-10-24 13:39:08.47713', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (52, 'UPDATE', 'gyuii', '2017-10-24 13:39:57.924373', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (53, 'INSERT', 'Lire un livre', '2017-10-24 13:40:34.138394', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (54, 'DELETE', 'test', '2017-10-26 23:22:59.146491', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (55, 'UPDATE', 'brtdtb', '2017-10-26 23:23:17.383322', 'todo');
INSERT INTO journal (id_journal, operation, donnee, moment, objet) VALUES (56, 'INSERT', '57575', '2017-10-26 23:23:34.115305', 'todo');


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 189
-- Name: journal_id_journal_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('journal_id_journal_seq', 56, true);


--
-- TOC entry 2148 (class 0 OID 24624)
-- Dependencies: 186
-- Data for Name: personnes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personnes (id_personne, nom, prenom, nombre_de_todo_fait) VALUES (2, 'Damoneville', 'Nicolas', 3);
INSERT INTO personnes (id_personne, nom, prenom, nombre_de_todo_fait) VALUES (1, 'Szaja', 'Maxime', 5);
INSERT INTO personnes (id_personne, nom, prenom, nombre_de_todo_fait) VALUES (3, 'Chauffourier', 'Kelyan', 1);


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 185
-- Name: personnes_id_personne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('personnes_id_personne_seq', 1, false);


--
-- TOC entry 2150 (class 0 OID 24677)
-- Dependencies: 188
-- Data for Name: todo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (33, 'brtdtb', 'btybt', 'ytnyun', '0', 1);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (34, 'brtdtb', 'btybt', 'ytnyun', '0', 1);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (3, 'Hey', 'ervefr', '171554', '0', 2);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (6, 'Hey', 'ervefr', '171554', '0', 2);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (40, 'bsrtyndfg', 'btybt', 'ytnyun', '0', 1);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (2, 'Faire la vaiselle', 'fourchette', '12/12/17', '0', 2);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (41, 'Lire un livre', 'Maria chapdelaine', '45/45/17', '0', 1);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (32, '757577', '57575', '75757575', '0', 1);
INSERT INTO todo (id_todo, titre, description, date, fini, id_personne) VALUES (42, '57575', '757575', '57575', '0', 1);


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 187
-- Name: todo_id_todo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('todo_id_todo_seq', 42, true);


--
-- TOC entry 2026 (class 2606 OID 32848)
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id_journal);


--
-- TOC entry 2022 (class 2606 OID 24632)
-- Name: personnes personnes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personnes
    ADD CONSTRAINT personnes_pkey PRIMARY KEY (id_personne);


--
-- TOC entry 2024 (class 2606 OID 24685)
-- Name: todo todo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY todo
    ADD CONSTRAINT todo_pkey PRIMARY KEY (id_todo);


--
-- TOC entry 2029 (class 2620 OID 32872)
-- Name: todo suivitodo; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER suivitodo AFTER DELETE OR UPDATE ON todo FOR EACH ROW EXECUTE PROCEDURE journalisertodo();


--
-- TOC entry 2028 (class 2620 OID 32870)
-- Name: todo suivitodoinsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER suivitodoinsert AFTER INSERT ON todo FOR EACH ROW EXECUTE PROCEDURE journalisertodoinsert();


--
-- TOC entry 2027 (class 2606 OID 32833)
-- Name: todo foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY todo
    ADD CONSTRAINT foreign_key FOREIGN KEY (id_personne) REFERENCES personnes(id_personne) ON DELETE CASCADE;


-- Completed on 2017-11-01 16:30:15

--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals_sightings; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE animals_sightings (
    id integer NOT NULL,
    sighting_id integer,
    general_animal_id integer
);


ALTER TABLE animals_sightings OWNER TO "Guest";

--
-- Name: animals_sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE animals_sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_sightings_id_seq OWNER TO "Guest";

--
-- Name: animals_sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE animals_sightings_id_seq OWNED BY animals_sightings.id;


--
-- Name: general_animals; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE general_animals (
    id integer NOT NULL,
    type character varying,
    name character varying,
    health character varying,
    age character varying,
    amount integer
);


ALTER TABLE general_animals OWNER TO "Guest";

--
-- Name: general_animals_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE general_animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE general_animals_id_seq OWNER TO "Guest";

--
-- Name: general_animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE general_animals_id_seq OWNED BY general_animals.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE sightings (
    id integer NOT NULL,
    location character varying,
    ranger_name character varying,
    spotted timestamp without time zone
);


ALTER TABLE sightings OWNER TO "Guest";

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO "Guest";

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY animals_sightings ALTER COLUMN id SET DEFAULT nextval('animals_sightings_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY general_animals ALTER COLUMN id SET DEFAULT nextval('general_animals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals_sightings; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY animals_sightings (id, sighting_id, general_animal_id) FROM stdin;
\.


--
-- Name: animals_sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('animals_sightings_id_seq', 13, true);


--
-- Data for Name: general_animals; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY general_animals (id, type, name, health, age, amount) FROM stdin;
35	animal	chickadee	\N	Newborn	\N
36	animal	bluejay	\N	Young	\N
37	animal	osprey	\N	Adult	\N
38	animal	elk	\N	Young	\N
39	endangered	grey wolf	Ill	Young	2
40	endangered	bee	Ill	Young	3
\.


--
-- Name: general_animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('general_animals_id_seq', 40, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY sightings (id, location, ranger_name, spotted) FROM stdin;
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('sightings_id_seq', 48, true);


--
-- Name: animals_sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY animals_sightings
    ADD CONSTRAINT animals_sightings_pkey PRIMARY KEY (id);


--
-- Name: general_animals_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY general_animals
    ADD CONSTRAINT general_animals_pkey PRIMARY KEY (id);


--
-- Name: sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_supplier_id_fk;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_product_category_id_fk;
ALTER TABLE IF EXISTS ONLY public."order" DROP CONSTRAINT IF EXISTS order_customer_id_fk;
ALTER TABLE IF EXISTS ONLY public."order" DROP CONSTRAINT IF EXISTS order_customer_address_id_fk_2;
ALTER TABLE IF EXISTS ONLY public."order" DROP CONSTRAINT IF EXISTS order_customer_address_id_fk;
ALTER TABLE IF EXISTS ONLY public.line_item DROP CONSTRAINT IF EXISTS line_item_product_id_fk;
ALTER TABLE IF EXISTS ONLY public.line_item DROP CONSTRAINT IF EXISTS line_item_order_id_fk;
ALTER TABLE IF EXISTS ONLY public.customer DROP CONSTRAINT IF EXISTS customer_user_id_fk;
ALTER TABLE IF EXISTS ONLY public.customer_address DROP CONSTRAINT IF EXISTS customer_address_customer_id_fk;
DROP INDEX IF EXISTS public.user_username_uindex;
DROP INDEX IF EXISTS public.user_password_uindex;
DROP INDEX IF EXISTS public.user_id_uindex;
DROP INDEX IF EXISTS public.supplier_id_uindex;
DROP INDEX IF EXISTS public.product_id_uindex;
DROP INDEX IF EXISTS public.product_category_id_uindex;
DROP INDEX IF EXISTS public.order_id_uindex;
DROP INDEX IF EXISTS public.line_item_id_uindex;
DROP INDEX IF EXISTS public.customer_id_uindex;
DROP INDEX IF EXISTS public.customer_address_id_uindex;
ALTER TABLE IF EXISTS ONLY public."user" DROP CONSTRAINT IF EXISTS user_pk;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS supplier_pk;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS product_pk;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS product_category_pk;
ALTER TABLE IF EXISTS ONLY public."order" DROP CONSTRAINT IF EXISTS order_pk;
ALTER TABLE IF EXISTS ONLY public.line_item DROP CONSTRAINT IF EXISTS line_item_pk;
ALTER TABLE IF EXISTS ONLY public.customer DROP CONSTRAINT IF EXISTS customer_pk;
ALTER TABLE IF EXISTS ONLY public.customer_address DROP CONSTRAINT IF EXISTS customer_address_pk;
ALTER TABLE IF EXISTS public."user" ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.supplier ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.product_category ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.product ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public."order" ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.line_item ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.customer_address ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.customer ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE IF EXISTS public.user_id_seq;
DROP TABLE IF EXISTS public."user";
DROP SEQUENCE IF EXISTS public.supplier_id_seq;
DROP TABLE IF EXISTS public.supplier;
DROP SEQUENCE IF EXISTS public.product_id_seq;
DROP SEQUENCE IF EXISTS public.product_category_id_seq;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.product;
DROP SEQUENCE IF EXISTS public.order_id_seq;
DROP TABLE IF EXISTS public."order";
DROP SEQUENCE IF EXISTS public.line_item_id_seq;
DROP TABLE IF EXISTS public.line_item;
DROP SEQUENCE IF EXISTS public.customer_id_seq;
DROP SEQUENCE IF EXISTS public.customer_address_id_seq;
DROP TABLE IF EXISTS public.customer_address;
DROP TABLE IF EXISTS public.customer;
SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    email character varying NOT NULL,
    phone_number character varying NOT NULL,
    user_id integer
);


--
-- Name: customer_address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer_address (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    zip_code character varying NOT NULL,
    city character varying NOT NULL,
    address character varying NOT NULL,
    address_type character varying
);


--
-- Name: customer_address_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.customer_address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: customer_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.customer_address_id_seq OWNED BY public.customer_address.id;


--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: line_item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.line_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    unit_price double precision NOT NULL,
    quantity integer NOT NULL
);


--
-- Name: line_item_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.line_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: line_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.line_item_id_seq OWNED BY public.line_item.id;


--
-- Name: order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."order" (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    total_price double precision NOT NULL,
    status character varying NOT NULL,
    checkout_date timestamp without time zone NOT NULL,
    payment_date timestamp without time zone,
    shipping_id integer,
    billing_id integer
);


--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.order_id_seq OWNED BY public."order".id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    default_price double precision NOT NULL,
    currency character varying NOT NULL,
    supplier_id integer NOT NULL,
    category_id integer NOT NULL
);


--
-- Name: product_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_category (
    id integer NOT NULL,
    name character varying NOT NULL,
    department character varying NOT NULL,
    description character varying
);


--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_category.id;


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: supplier; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.supplier (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying
);


--
-- Name: supplier_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.supplier_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: supplier_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.supplier_id_seq OWNED BY public.supplier.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL
);


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: customer_address id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_address ALTER COLUMN id SET DEFAULT nextval('public.customer_address_id_seq'::regclass);


--
-- Name: line_item id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.line_item ALTER COLUMN id SET DEFAULT nextval('public.line_item_id_seq'::regclass);


--
-- Name: order id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order" ALTER COLUMN id SET DEFAULT nextval('public.order_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: product_category id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);


--
-- Name: supplier id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supplier ALTER COLUMN id SET DEFAULT nextval('public.supplier_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: customer_address; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: line_item; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product VALUES (1, 'Incilius Alvarius Toad', 'It secretes a potent hallucinogenic compound from glands on either side of its head. You can dry and smoke the compound and get a short-lived but intense psychedelic experience thanks to the potent chemicals 5-MeO-DMT and bufotenin.', 27.8900000000000006, 'USD', 1, 1);
INSERT INTO public.product VALUES (2, 'Salvia Divinorum', 'Salvia also known as "sage of the diviners". The herb achieves its intense psychoactive effects by targeting kappa opioid receptors within the brain resulting in reduced levels of dopamine.', 7.19, 'USD', 1, 2);
INSERT INTO public.product VALUES (3, 'Ayahuasca', 'Its ability to induce out of body experiences and plummet users seemingly into other dimensions has given it the title of "the spirit molecule.', 10.79, 'USD', 1, 2);
INSERT INTO public.product VALUES (4, 'Magic mushroom', 'The experience generated by ingesting psilocybin mushrooms often induces relaxation; spiritual experiences, intense emotions, heightened senses, and synesthesia.', 6.89, 'USD', 1, 1);
INSERT INTO public.product VALUES (5, 'Mad honey', 'Consuming mad honey is associated with euphoria and hallucinations. Users need to be careful not to overdo things in order to avoid side effects such as dizziness, nausea and vomiting and impaired consciousness.', 12.69, 'USD', 1, 2);
INSERT INTO public.product VALUES (6, 'Lysergic acid diethylamid', 'Effects typically include altered thoughts- feelings and awareness of one''s surroundings. You can see or hear things that do not exist.', 12.99, 'USD', 2, 2);
INSERT INTO public.product VALUES (7, 'Mescaline', 'Get information about this product using Google.', 9.99, 'USD', 2, 1);

--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_category VALUES (1, 'Natural psychedelic', 'Recreation', 'A natural psychedelic is a substance found in nature that is good for health.');
INSERT INTO public.product_category VALUES (2, 'Classic psychedelic', 'Recreation', 'Classic psychedelic');

--
-- Data for Name: supplier; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.supplier VALUES (1, 'Guadalajara Cartel', 'All kind of things');
INSERT INTO public.supplier VALUES (2, 'Clan del Golfo', 'All kind of things');

--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: customer_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.customer_address_id_seq', 1, false);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.customer_id_seq', 1, false);


--
-- Name: line_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.line_item_id_seq', 1, false);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_id_seq', 1, false);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_category_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 1, false);


--
-- Name: supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.supplier_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- Name: customer_address customer_address_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_address
    ADD CONSTRAINT customer_address_pk PRIMARY KEY (id);


--
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (id);


--
-- Name: line_item line_item_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_pk PRIMARY KEY (id);


--
-- Name: order order_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);


--
-- Name: product_category product_category_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pk PRIMARY KEY (id);


--
-- Name: product product_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- Name: supplier supplier_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: customer_address_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX customer_address_id_uindex ON public.customer_address USING btree (id);


--
-- Name: customer_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX customer_id_uindex ON public.customer USING btree (id);


--
-- Name: line_item_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX line_item_id_uindex ON public.line_item USING btree (id);


--
-- Name: order_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX order_id_uindex ON public."order" USING btree (id);


--
-- Name: product_category_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX product_category_id_uindex ON public.product_category USING btree (id);


--
-- Name: product_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX product_id_uindex ON public.product USING btree (id);


--
-- Name: supplier_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX supplier_id_uindex ON public.supplier USING btree (id);


--
-- Name: user_id_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX user_id_uindex ON public."user" USING btree (id);


--
-- Name: user_password_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX user_password_uindex ON public."user" USING btree (password);


--
-- Name: user_username_uindex; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX user_username_uindex ON public."user" USING btree (username);


--
-- Name: customer_address customer_address_customer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer_address
    ADD CONSTRAINT customer_address_customer_id_fk FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: customer customer_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: line_item line_item_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id);


--
-- Name: line_item line_item_product_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT line_item_product_id_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: order order_customer_address_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_customer_address_id_fk FOREIGN KEY (billing_id) REFERENCES public.customer_address(id);


--
-- Name: order order_customer_address_id_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_customer_address_id_fk_2 FOREIGN KEY (shipping_id) REFERENCES public.customer_address(id);


--
-- Name: order order_customer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_customer_id_fk FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: product product_product_category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_product_category_id_fk FOREIGN KEY (category_id) REFERENCES public.product_category(id);


--
-- Name: product product_supplier_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_supplier_id_fk FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);


--
-- PostgreSQL database dump complete
--


-- Table: doc_cards

-- DROP TABLE doc_cards;

CREATE TABLE doc_cards
(
  id serial NOT NULL,
  id_parent integer NOT NULL,
  "name" character varying(256) NOT NULL,
  description character varying(1024),
  creation_date date NOT NULL,
  modifed_date date,
  author character varying(256),
  id_tree integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doc_cards OWNER TO postgres;

-- Table: doc_content

-- DROP TABLE doc_content;

CREATE TABLE doc_content
(
  id serial NOT NULL,
  id_page integer NOT NULL,
  link character varying(1024) NOT NULL,
  description character varying(1024),
  embedded bit(1)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doc_content OWNER TO postgres;

-- Table: doc_structure_trees

-- DROP TABLE doc_structure_trees;

CREATE TABLE doc_structure_trees
(
  id integer NOT NULL DEFAULT nextval('doc_trees_id_seq'::regclass),
  id_parent integer NOT NULL,
  "name" character varying(256),
  id_doc integer NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doc_structure_trees OWNER TO postgres;

-- Index: idx_id_parent

-- DROP INDEX idx_id_parent;

CREATE INDEX idx_id_parent
  ON doc_structure_trees
  USING btree
  (id_parent NULLS FIRST);

-- Table: doc_trees

-- DROP TABLE doc_trees;

CREATE TABLE doc_trees
(
  id serial NOT NULL,
  id_parent integer NOT NULL,
  "name" character varying(256),
  CONSTRAINT pk_td_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doc_trees OWNER TO postgres;

-- Index: dt_idx_id_parent

-- DROP INDEX dt_idx_id_parent;

CREATE INDEX dt_idx_id_parent
  ON doc_trees
  USING btree
  (id_parent NULLS FIRST);

-- Table: keywords

-- DROP TABLE keywords;

CREATE TABLE keywords
(
  id serial NOT NULL,
  word character varying(128) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE keywords OWNER TO postgres;

-- Table: object_keywords

-- DROP TABLE object_keywords;

CREATE TABLE object_keywords
(
  id_object integer NOT NULL,
  id_keyword integer NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE object_keywords OWNER TO postgres;


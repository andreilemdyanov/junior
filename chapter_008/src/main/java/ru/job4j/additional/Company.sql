CREATE DATABASE comp;

CREATE TABLE company (
  id   INTEGER NOT NULL,
  name CHARACTER VARYING,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
  id         INTEGER NOT NULL,
  name       CHARACTER VARYING,
  company_id INTEGER,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'A');
INSERT INTO company (id, name) VALUES (2, 'B');
INSERT INTO company (id, name) VALUES (4, 'C');
INSERT INTO company (id, name) VALUES (5, 'D');


INSERT INTO person (id, name, company_id) VALUES (11, 'bob', 1);
INSERT INTO person (id, name, company_id) VALUES (22, 'bill', 2);
INSERT INTO person (id, name, company_id) VALUES (33, 'jim', 4);
INSERT INTO person (id, name, company_id) VALUES (44, 'joe', 1);
INSERT INTO person (id, name, company_id) VALUES (55, 'will', 5);
INSERT INTO person (id, name, company_id) VALUES (66, 'sall', 4);
INSERT INTO person (id, name, company_id) VALUES (77, 'moe', 1);

-- Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5,
-- company name for each person
SELECT
  person.name  AS name,
  company.name AS company
FROM person
  JOIN company ON company.id = person.company_id AND company.id <> 5;

--Select the name of the company with the maximum number of persons + number of persons in this company
SELECT
  company.name,
  count(person.company_id) AS C
FROM company
  JOIN person ON company.id = person.company_id
GROUP BY company.name
ORDER BY C DESC
LIMIT 1;

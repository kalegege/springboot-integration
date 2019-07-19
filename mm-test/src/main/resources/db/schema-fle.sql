CREATE TABLE file(
"ID" INT PRIMARY KEY ,
"file_name" VARCHAR(64)
);

CREATE SEQUENCE s_file
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
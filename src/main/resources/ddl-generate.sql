CREATE TABLE AD
(
  ID integer PRIMARY KEY NOT NULL IDENTITY,
  TYPE varchar(10) NOT NULL,
  AMOUNT numeric,
  CURRENCY varchar(50),
  RATE numeric,
  USER_ID integer,
  CITY varchar(50),
  AREA varchar(50),
  COMMENT varchar(50),
  STATUS varchar(30),
  PUBLISHED longvarbinary
);
CREATE TABLE USER
(
  ID integer PRIMARY KEY NOT NULL IDENTITY,
  PHONE_NUMBER varchar(50) NOT NULL
)CREATE TABLE AD
(
  ID integer PRIMARY KEY NOT NULL IDENTITY,
TYPE varchar(10) NOT NULL,
  AMOUNT numeric,
  CURRENCY varchar(50),
  RATE numeric,
  USER_ID integer,
  CITY varchar(50),
  AREA varchar(50),
COMMENT varchar(50),
  STATUS varchar(30),
  PUBLISHED longvarbinary
);
CREATE TABLE USER
(
  ID integer PRIMARY KEY NOT NULL IDENTITY,
  PHONE_NUMBER varchar(50) NOT NULL
)
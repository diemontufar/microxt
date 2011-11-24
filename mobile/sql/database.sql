DROP USER MOBILE CASCADE;
DROP TABLESPACE MOBILE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;

CREATE TABLESPACE MOBILE
LOGGING DATAFILE 'C:\Oracle\database\oradata\oracle\MOBILE.DBF' SIZE 1000M
EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;

CREATE USER MOBILE
PROFILE "DEFAULT"
IDENTIFIED BY MOBILE DEFAULT TABLESPACE MOBILE
TEMPORARY TABLESPACE TEMP
ACCOUNT UNLOCK;
GRANT CONNECT TO MOBILE;
ALTER USER MOBILE QUOTA UNLIMITED ON MOBILE;
GRANT CREATE ANY TABLE TO MOBILE;
GRANT CREATE ANY SEQUENCE TO MOBILE;
GRANT CREATE ANY VIEW TO MOBILE;
GRANT CREATE MATERIALIZED VIEW TO MOBILE;
GRANT CREATE ANY TYPE TO MOBILE;
GRANT CREATE ANY PROCEDURE TO MOBILE;
GRANT ALTER ANY PROCEDURE TO MOBILE;
------------------------------------

ALTER TABLE SEQUENTIAL DROP CONSTRAINT SEQUENTIAL_COMPANY_FK;
ALTER TABLE ENTITY_TABLE DROP CONSTRAINT ENTITY_TABLE_COMPANY_FK;
ALTER TABLE ENTITY_FIELD DROP CONSTRAINT ENTITY_FIELD_COMPANY_FK;
ALTER TABLE ENTITY_RELATIONSHIP DROP CONSTRAINT ENTITY_RELATIONSHIP_COMPANY_FK;
ALTER TABLE DATABASE_TYPE DROP CONSTRAINT DATABASE_TYPE_DATA_TYPE_FK;
ALTER TABLE ENTITY_TABLE DROP CONSTRAINT ENTITY_TABLE_ID_FK;
ALTER TABLE ENTITY_FIELD_ID DROP CONSTRAINT ENTITY_FIELD_ID_TABLE_ID_FK;
ALTER TABLE ENTITY_FIELD DROP CONSTRAINT ENTITY_FIELD_ID_FK;
ALTER TABLE ENTITY_FIELD DROP CONSTRAINT ENTITY_FIELD_DATA_TYPE_FK;
ALTER TABLE ENTITY_FIELD DROP CONSTRAINT ENTITY_FIELD_SEQUENTIAL_ID_FK;
ALTER TABLE ENTITY_RELATIONSHIP DROP CONSTRAINT ENTITY_RELATIONSHIP_FROM_FK;
ALTER TABLE ENTITY_RELATIONSHIP DROP CONSTRAINT ENTITY_RELATIONSHIP_TO_FK;

DROP TABLE LANGUAGE;
CREATE TABLE LANGUAGE (
        LANGUAGE_ID VARCHAR(4) NOT NULL, 
        NAME VARCHAR(40) NOT NULL, 
CONSTRAINT LANGUAGE_PK PRIMARY KEY (LANGUAGE_ID)
);

DROP TABLE COMPANY;
CREATE TABLE COMPANY (
        COMPANY_ID VARCHAR(4) NOT NULL, 
        NAME VARCHAR(40) NOT NULL, 
        DATAFILE_ID NUMBER(10),
        UPGRADE_NUMBER NUMBER(4,2),
        LICENSE_DATE DATE,
        ENABLE VARCHAR(1) DEFAULT 0 NOT NULL,
CONSTRAINT COMPANY_PK PRIMARY KEY (COMPANY_ID)
);

DROP TABLE SEQUENTIAL_ID;
CREATE TABLE SEQUENTIAL_ID (
        SEQUENTIAL_ID VARCHAR(40) NOT NULL, 
CONSTRAINT SEQUENTIAL_ID_PK PRIMARY KEY (SEQUENTIAL_ID)
);

DROP TABLE SEQUENTIAL;
CREATE TABLE SEQUENTIAL (
        COMPANY_ID VARCHAR(4) NOT NULL, 
        SEQUENTIAL_ID VARCHAR(40) NOT NULL, 
        SEQUENTIAL_VALUE NUMBER(10) DEFAULT 0 NOT NULL, 
CONSTRAINT SEQUENTIAL_PK PRIMARY KEY (COMPANY_ID,SEQUENTIAL_ID)
);

DROP TABLE DATA_TYPE;
CREATE TABLE DATA_TYPE (
        DATA_TYPE_ID VARCHAR(30) NOT NULL, 
        DESCRIPTION VARCHAR(100) NOT NULL,
CONSTRAINT DATA_TYPE_PK PRIMARY KEY (DATA_TYPE_ID)
);

DROP TABLE DATABASE_TYPE;
CREATE TABLE DATABASE_TYPE (
        DATA_TYPE_ID VARCHAR(30) NOT NULL, 
        DATABASE_ID VARCHAR(30) NOT NULL, 
        DATA_SIZE NUMBER(5) NOT NULL, 
        DATABASE_TYPE VARCHAR(30) NOT NULL, 
CONSTRAINT DATABASE_TYPE_PK PRIMARY KEY (DATA_TYPE_ID,DATABASE_ID,DATA_SIZE)
);

DROP TABLE ENTITY_TABLE_ID;
CREATE TABLE ENTITY_TABLE_ID (
        TABLE_ID VARCHAR(30) NOT NULL, 
CONSTRAINT ENTITY_TABLE_ID_PK PRIMARY KEY (TABLE_ID) 
);

DROP TABLE ENTITY_TABLE;
CREATE TABLE ENTITY_TABLE (
        COMPANY_ID VARCHAR(4) NOT NULL,
        TABLE_ID VARCHAR(30) NOT NULL, 
        PACKAGE_NAME VARCHAR(30) NOT NULL, 
        HAS_TABLE_ID VARCHAR(1) DEFAULT 0 NOT NULL,     
        MULTI_COMPANY VARCHAR(1) DEFAULT 0 NOT NULL, 
        MULTI_LANGUAGE VARCHAR(1) DEFAULT 0 NOT NULL, 
        HISTORICAL_DATA VARCHAR(1) DEFAULT 0 NOT NULL, 
        OPTIMISTIC_LOCKING VARCHAR(1) DEFAULT 0 NOT NULL, 
        ENUMERATED_TYPES VARCHAR(1) DEFAULT 0 NOT NULL, 
        CACHE_MEMORY VARCHAR(1) DEFAULT 0 NOT NULL,
        DESCRIPTION VARCHAR(100),
CONSTRAINT ENTITY_PK PRIMARY KEY (COMPANY_ID,TABLE_ID)
);

DROP TABLE ENTITY_FIELD_ID;
CREATE TABLE ENTITY_FIELD_ID (
        TABLE_ID VARCHAR(30) NOT NULL, 
        FIELD_ID VARCHAR(30) NOT NULL, 
CONSTRAINT ENTITY_FIELD_ID_PK PRIMARY KEY (TABLE_ID,FIELD_ID)
);

DROP TABLE ENTITY_FIELD;
CREATE TABLE ENTITY_FIELD (
        COMPANY_ID VARCHAR(4) NOT NULL, 
        TABLE_ID VARCHAR(30) NOT NULL, 
        FIELD_ID VARCHAR(30) NOT NULL, 
        FIELD_ORDER NUMBER(3) NOT NULL, 
        DATA_TYPE_ID VARCHAR(30) NOT NULL, 
        DATA_SIZE NUMBER(5) NOT NULL, 
        DATA_SCALE NUMBER(3) DEFAULT 0 NOT NULL, 
        PRIMARY_KEY VARCHAR(1) DEFAULT 0 NOT NULL, 
        UNIQUE_KEY VARCHAR(1) DEFAULT 0 NOT NULL, 
        NULLABLE VARCHAR(1) DEFAULT 0 NOT NULL, 
        DEFAULT_VALUE VARCHAR(30), 
        SEQUENTIAL_ID VARCHAR(30), 
        MINIMUM_VALUE VARCHAR(30), 
        MAXIMUM_VALUE VARCHAR(30), 
        SECURITY_LEVEL NUMBER(3) DEFAULT 0 NOT NULL, 
        READONLY VARCHAR(1) DEFAULT 0 NOT NULL, 
        HIDDEN VARCHAR(1) DEFAULT 0 NOT NULL, 
        DESCRIPTION VARCHAR(100), 
        LABEL VARCHAR(100), 
        TOOLTIP VARCHAR(100), 
        MASK VARCHAR(100), 
        VALIDATION VARCHAR(100), 
        CALCULATION VARCHAR(100), 
CONSTRAINT ENTITY_FIELD_PK PRIMARY KEY (COMPANY_ID,TABLE_ID,FIELD_ID),
CONSTRAINT ENTITY_FIELD_UK UNIQUE (COMPANY_ID,TABLE_ID,FIELD_ID,FIELD_ORDER) 
);

DROP TABLE ENTITY_RELATIONSHIP;
CREATE TABLE ENTITY_RELATIONSHIP (
        COMPANY_ID VARCHAR(4) NOT NULL, 
        RELATIONSHIP_ID VARCHAR(30) NOT NULL, 
        RELATIONSHIP_ORDER NUMBER(3) NOT NULL, 
        TABLE_FROM VARCHAR(30) NOT NULL, 
        FIELD_FROM VARCHAR(30) NOT NULL, 
        TABLE_TO VARCHAR(30) NOT NULL, 
        FIELD_TO VARCHAR(30) NOT NULL, 
CONSTRAINT ENTITY_RELATIONSHIP_PK PRIMARY KEY (COMPANY_ID,RELATIONSHIP_ID,RELATIONSHIP_ORDER)
);

ALTER TABLE SEQUENTIAL ADD CONSTRAINT SEQUENTIAL_COMPANY_FK FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE ENTITY_TABLE ADD CONSTRAINT ENTITY_TABLE_COMPANY_FK FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE ENTITY_FIELD ADD CONSTRAINT ENTITY_FIELD_COMPANY_FK FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE ENTITY_RELATIONSHIP ADD CONSTRAINT ENTITY_RELATIONSHIP_COMPANY_FK FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (COMPANY_ID);
ALTER TABLE DATABASE_TYPE ADD CONSTRAINT DATABASE_TYPE_DATA_TYPE_FK FOREIGN KEY (DATA_TYPE_ID) REFERENCES DATA_TYPE (DATA_TYPE_ID);
ALTER TABLE ENTITY_TABLE ADD CONSTRAINT ENTITY_TABLE_ID_FK FOREIGN KEY (TABLE_ID) REFERENCES ENTITY_TABLE_ID (TABLE_ID);
ALTER TABLE ENTITY_FIELD_ID ADD CONSTRAINT ENTITY_FIELD_ID_TABLE_ID_FK FOREIGN KEY (TABLE_ID) REFERENCES ENTITY_TABLE_ID (TABLE_ID);
ALTER TABLE ENTITY_FIELD ADD CONSTRAINT ENTITY_FIELD_ID_FK FOREIGN KEY (TABLE_ID,FIELD_ID) REFERENCES ENTITY_FIELD_ID (TABLE_ID,FIELD_ID);
ALTER TABLE ENTITY_FIELD ADD CONSTRAINT ENTITY_FIELD_DATA_TYPE_FK FOREIGN KEY (DATA_TYPE_ID) REFERENCES DATA_TYPE (DATA_TYPE_ID);
ALTER TABLE ENTITY_FIELD ADD CONSTRAINT ENTITY_FIELD_SEQUENTIAL_ID_FK FOREIGN KEY (SEQUENTIAL_ID) REFERENCES SEQUENTIAL_ID (SEQUENTIAL_ID);
ALTER TABLE ENTITY_RELATIONSHIP ADD CONSTRAINT ENTITY_RELATIONSHIP_FROM_FK FOREIGN KEY (TABLE_FROM,FIELD_FROM) REFERENCES ENTITY_FIELD_ID (TABLE_ID,FIELD_ID);
ALTER TABLE ENTITY_RELATIONSHIP ADD CONSTRAINT ENTITY_RELATIONSHIP_TO_FK FOREIGN KEY (TABLE_TO,FIELD_TO) REFERENCES ENTITY_FIELD_ID (TABLE_ID,FIELD_ID);

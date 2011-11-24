DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'CIVIL_STATUS_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'GENDER_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'IDENTIFICATION_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PROFESSION_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'ADDRESS_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PHONE_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'DOCUMENT_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'CIVIL_STATUS_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'IDENTIFICATION_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PROFESSION_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'ADDRESS_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PHONE_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'DOCUMENT_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_TYPE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_ADDRESS_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_PHONE_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_DOCUMENT_COMPANY_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'GENDER_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'CIVIL_STATUS_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'IDENTIFICATION_TYPE_LANGUAG_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PROFESSION_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'ADDRESS_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PHONE_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'DOCUMENT_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_TYPE_LANGUAGE_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_IDENTIF_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_GENDER_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_CIVIL_STATUS_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_CITY_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_CITY_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_CITY_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_PROFESSION_TYPE_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_ADDRESS_CITY_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_ADDRESS_CITY_ID_FK';
DELETE FROM ENTITY_RELATIONSHIP WHERE RELATIONSHIP_ID = 'PERSON_ADDRESS_CITY_ID_FK';

DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'GENDER_TYPE' AND FIELD_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'GENDER_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'PERSON_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'LAST_NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'SECOND_LAST_NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'IDENTIFICATION_NUMBER';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'DATE_OF_BIRTH';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'COUNTRY_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PROVINCE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'CITY_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_SEQUENCE';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'FIRST_STREET';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_NUMBER';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'SECOND_STREET';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'BUILDING_NAME';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'BUILDING_FLOOR';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'COUNTRY_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'PROVINCE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'CITY_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_MAP';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_SEQUENCE';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'AREA_CODE';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_NUMBER';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DOCUMENT_SEQUENCE';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_FIELD  WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DATAFILE_ID';

DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'GENDER_TYPE_ID' AND FIELD_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'GENDER_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'GENDER_TYPE' AND FIELD_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'GENDER_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'CIVIL_STATUS_ID' AND FIELD_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'CIVIL_STATUS' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE_ID' AND FIELD_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PROFESSION_TYPE_ID' AND FIELD_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PROFESSION_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'ADDRESS_TYPE_ID' AND FIELD_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'ADDRESS_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PHONE_TYPE_ID' AND FIELD_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PHONE_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'DOCUMENT_TYPE_ID' AND FIELD_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'DOCUMENT_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_TYPE_ID' AND FIELD_ID = 'PERSON_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'LANGUAGE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'PERSON_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_TYPE' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ID' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'LAST_NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'SECOND_LAST_NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'IDENTIFICATION_NUMBER';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'DATE_OF_BIRTH';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'COUNTRY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PROVINCE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'CITY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON' AND FIELD_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_SEQUENCE';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'FIRST_STREET';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_NUMBER';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'SECOND_STREET';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'BUILDING_NAME';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'BUILDING_FLOOR';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'COUNTRY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'PROVINCE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'CITY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_ADDRESS' AND FIELD_ID = 'ADDRESS_MAP';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_SEQUENCE';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'AREA_CODE';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_PHONE' AND FIELD_ID = 'PHONE_NUMBER';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'COMPANY_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'PERSON_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DOCUMENT_SEQUENCE';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_FIELD_ID WHERE TABLE_ID = 'PERSON_DOCUMENT' AND FIELD_ID = 'DATAFILE_ID';

DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'GENDER_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'CIVIL_STATUS';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'IDENTIFICATION_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PROFESSION_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'ADDRESS_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PHONE_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'DOCUMENT_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PERSON_TYPE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PERSON';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PERSON_ADDRESS';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PERSON_PHONE';
DELETE FROM ENTITY_TABLE WHERE TABLE_ID = 'PERSON_DOCUMENT';

DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'GENDER_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'CIVIL_STATUS';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PROFESSION_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'ADDRESS_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PHONE_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'DOCUMENT_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_TYPE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_ADDRESS';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_PHONE';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_DOCUMENT';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'GENDER_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'CIVIL_STATUS_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'IDENTIFICATION_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PROFESSION_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'ADDRESS_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PHONE_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'DOCUMENT_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_TYPE_ID';
DELETE FROM ENTITY_TABLE_ID WHERE TABLE_ID = 'PERSON_ID';

INSERT INTO ENTITY_TABLE_ID VALUES ('GENDER_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('CIVIL_STATUS_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('PROFESSION_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('ADDRESS_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('PHONE_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('DOCUMENT_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_TYPE_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_ID');
INSERT INTO ENTITY_TABLE_ID VALUES ('GENDER_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('CIVIL_STATUS');
INSERT INTO ENTITY_TABLE_ID VALUES ('IDENTIFICATION_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('PROFESSION_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('ADDRESS_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('PHONE_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('DOCUMENT_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_TYPE');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_ADDRESS');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_PHONE');
INSERT INTO ENTITY_TABLE_ID VALUES ('PERSON_DOCUMENT');

INSERT INTO ENTITY_TABLE VALUES ('ALL','GENDER_TYPE','person','1','0','1','0','0','1','0','Values of gender types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','CIVIL_STATUS','person','1','1','1','0','0','0','0','Values of civil statuses');
INSERT INTO ENTITY_TABLE VALUES ('ALL','IDENTIFICATION_TYPE','person','1','1','1','0','0','0','0','Values of identification types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PROFESSION_TYPE','person','1','1','1','0','0','0','0','Values of profession types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','ADDRESS_TYPE','person','1','1','1','0','0','0','0','Values of address types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PHONE_TYPE','person','1','1','1','0','0','0','0','Values of phone types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','DOCUMENT_TYPE','person','1','1','1','0','0','0','0','Values of document types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PERSON_TYPE','person','1','1','1','0','0','0','0','Values of person types');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PERSON','person','1','1','0','1','1','0','0','Values of person');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PERSON_ADDRESS','person','0','1','0','1','1','0','0','Values of person address');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PERSON_PHONE','person','0','1','0','1','1','0','0','Values of person phones');
INSERT INTO ENTITY_TABLE VALUES ('ALL','PERSON_DOCUMENT','person','0','1','0','1','1','0','0','Values of person documents');

INSERT INTO ENTITY_FIELD_ID VALUES ('GENDER_TYPE_ID','GENDER_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('GENDER_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('GENDER_TYPE','GENDER_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('GENDER_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('CIVIL_STATUS_ID','CIVIL_STATUS_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('CIVIL_STATUS','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('CIVIL_STATUS','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('CIVIL_STATUS','CIVIL_STATUS_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('CIVIL_STATUS','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('IDENTIFICATION_TYPE_ID','IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('IDENTIFICATION_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('IDENTIFICATION_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('IDENTIFICATION_TYPE','IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('IDENTIFICATION_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PROFESSION_TYPE_ID','PROFESSION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PROFESSION_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PROFESSION_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PROFESSION_TYPE','PROFESSION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PROFESSION_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('ADDRESS_TYPE_ID','ADDRESS_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('ADDRESS_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('ADDRESS_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('ADDRESS_TYPE','ADDRESS_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('ADDRESS_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PHONE_TYPE_ID','PHONE_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PHONE_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PHONE_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PHONE_TYPE','PHONE_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PHONE_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('DOCUMENT_TYPE_ID','DOCUMENT_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('DOCUMENT_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('DOCUMENT_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('DOCUMENT_TYPE','DOCUMENT_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('DOCUMENT_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_TYPE_ID','PERSON_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_TYPE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_TYPE','LANGUAGE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_TYPE','PERSON_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_TYPE','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ID','PERSON_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','PERSON_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','LAST_NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','SECOND_LAST_NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','IDENTIFICATION_NUMBER');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','DATE_OF_BIRTH');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','GENDER_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','CIVIL_STATUS_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','COUNTRY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','PROVINCE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','CITY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON','PROFESSION_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','PERSON_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','ADDRESS_SEQUENCE');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','ADDRESS_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','FIRST_STREET');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','ADDRESS_NUMBER');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','SECOND_STREET');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','BUILDING_NAME');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','BUILDING_FLOOR');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','COUNTRY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','PROVINCE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','CITY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_ADDRESS','ADDRESS_MAP');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','PERSON_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','PHONE_SEQUENCE');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','PHONE_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','AREA_CODE');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_PHONE','PHONE_NUMBER');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_DOCUMENT','COMPANY_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_DOCUMENT','PERSON_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_DOCUMENT','DOCUMENT_SEQUENCE');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_DOCUMENT','DOCUMENT_TYPE_ID');
INSERT INTO ENTITY_FIELD_ID VALUES ('PERSON_DOCUMENT','DATAFILE_ID');

INSERT INTO ENTITY_FIELD VALUES ('ALL','GENDER_TYPE','GENDER_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Gender type Id','genderTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','GENDER_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of gender','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','CIVIL_STATUS','CIVIL_STATUS_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Civil type status Id','civilStatusId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','CIVIL_STATUS','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of civil status','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','IDENTIFICATION_TYPE','IDENTIFICATION_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Identification type Id','identificationTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','IDENTIFICATION_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of identification type','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PROFESSION_TYPE','PROFESSION_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Profession type Id','professionTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PROFESSION_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of profession type','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','ADDRESS_TYPE','ADDRESS_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Address type Id','addressTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','ADDRESS_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of address type','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PHONE_TYPE','PHONE_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Phone type Id','phoneTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PHONE_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of phone','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','DOCUMENT_TYPE','DOCUMENT_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Document type Id','documentTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','DOCUMENT_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of document type','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_TYPE','PERSON_TYPE_ID',1,'String',4,0,'1','0','0',null,null,null,null,0,'0','0','Person type Id','personTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_TYPE','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of person type','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','PERSON_ID',1,'Long',10,0,'1','0','0',null,null,null,null,0,'0','0','Person Id','personId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','NAME',2,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Name of person','name',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','LAST_NAME',3,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Lastname of person','lastname',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','SECOND_LAST_NAME',4,'String',40,0,'0','0','1',null,null,null,null,0,'0','0','Second lastname of person','secondLastname',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','IDENTIFICATION_TYPE_ID',5,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Identification type Id','identificationTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','IDENTIFICATION_NUMBER',6,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Identification number','identificationNumber',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','DATE_OF_BIRTH',7,'Date',0,0,'0','0','0',null,null,null,null,0,'0','0','Date of birth','dateOfBirth',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','GENDER_TYPE_ID',8,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Gender type Id','genderTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','CIVIL_STATUS_ID',9,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Civil type status Id','civilStatusId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','COUNTRY_ID',10,'String',2,0,'0','0','0',null,null,null,null,0,'0','0','Country Id','countryId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','PROVINCE_ID',11,'String',2,0,'0','0','1',null,null,null,null,0,'0','0','Province Id','provinceId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','CITY_ID',12,'String',3,0,'0','0','1',null,null,null,null,0,'0','0','City Id','cityId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON','PROFESSION_TYPE_ID',13,'String',4,0,'0','0','1',null,null,null,null,0,'0','0','Profession type Id','professionTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','PERSON_ID',1,'Long',10,0,'1','0','0',null,null,null,null,0,'0','0','Person Id','personId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','ADDRESS_SEQUENCE',2,'Integer',3,0,'1','0','0',null,null,null,null,0,'0','0','Sequence of person address','sequence',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','ADDRESS_TYPE_ID',3,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Address type Id','addressTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','FIRST_STREET',4,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','First street','firstStreet',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','ADDRESS_NUMBER',5,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Address number','addressNumber',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','SECOND_STREET',6,'String',40,0,'0','0','1',null,null,null,null,0,'0','0','Second street','secondStreet',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','BUILDING_NAME',7,'String',40,0,'0','0','1',null,null,null,null,0,'0','0','Building name','buildingName',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','BUILDING_FLOOR',8,'String',40,0,'0','0','1',null,null,null,null,0,'0','0','Building number','buildingNumber',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','COUNTRY_ID',9,'String',2,0,'0','0','1',null,null,null,null,0,'0','0','Country Id','countryId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','PROVINCE_ID',10,'String',2,0,'0','0','1',null,null,null,null,0,'0','0','Province Id','provinceId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','CITY_ID',11,'String',3,0,'0','0','1',null,null,null,null,0,'0','0','City Id','cityId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_ADDRESS','ADDRESS_MAP',12,'String',40,0,'0','0','1',null,null,null,null,0,'0','0','Address map','addressMap',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_PHONE','PERSON_ID',1,'Long',10,0,'1','0','0',null,null,null,null,0,'0','0','Person Id','personId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_PHONE','PHONE_SEQUENCE',2,'Integer',3,0,'1','0','0',null,null,null,null,0,'0','0','Sequence of person phone','sequence',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_PHONE','PHONE_TYPE_ID',3,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Phone type Id','phoneTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_PHONE','AREA_CODE',4,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Area code','areaCode',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_PHONE','PHONE_NUMBER',5,'String',40,0,'0','0','0',null,null,null,null,0,'0','0','Phone number','phoneNumber',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_DOCUMENT','PERSON_ID',1,'Long',10,0,'1','0','0',null,null,null,null,0,'0','0','Person Id','personId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_DOCUMENT','DOCUMENT_SEQUENCE',2,'Integer',3,0,'1','0','0',null,null,null,null,0,'0','0','Sequence of person document','sequence',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_DOCUMENT','DOCUMENT_TYPE_ID',3,'String',4,0,'0','0','0',null,null,null,null,0,'0','0','Document type Id','documentTypeId',null,null,null,null);
INSERT INTO ENTITY_FIELD VALUES ('ALL','PERSON_DOCUMENT','DATAFILE_ID',4,'Long',10,0,'0','0','0',null,null,null,null,0,'0','0','Datafile Id','datafileId',null,null,null,null);

INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','CIVIL_STATUS_ID_FK',1,'CIVIL_STATUS','CIVIL_STATUS_ID','CIVIL_STATUS_ID','CIVIL_STATUS_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','GENDER_TYPE_ID_FK',1,'GENDER_TYPE','GENDER_TYPE_ID','GENDER_TYPE_ID','GENDER_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','IDENTIFICATION_TYPE_ID_FK',1,'IDENTIFICATION_TYPE','IDENTIFICATION_TYPE_ID','IDENTIFICATION_TYPE_ID','IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PROFESSION_TYPE_ID_FK',1,'PROFESSION_TYPE','PROFESSION_TYPE_ID','PROFESSION_TYPE_ID','PROFESSION_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','ADDRESS_TYPE_ID_FK',1,'ADDRESS_TYPE','ADDRESS_TYPE_ID','ADDRESS_TYPE_ID','ADDRESS_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PHONE_TYPE_ID_FK',1,'PHONE_TYPE','PHONE_TYPE_ID','PHONE_TYPE_ID','PHONE_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','DOCUMENT_TYPE_ID_FK',1,'DOCUMENT_TYPE','DOCUMENT_TYPE_ID','DOCUMENT_TYPE_ID','DOCUMENT_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_TYPE_ID_FK',1,'PERSON_TYPE','PERSON_TYPE_ID','PERSON_TYPE_ID','PERSON_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_ID_FK',1,'PERSON','PERSON_ID','PERSON_ID','PERSON_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','CIVIL_STATUS_COMPANY_FK',1,'CIVIL_STATUS','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','IDENTIFICATION_TYPE_COMPANY_FK',1,'IDENTIFICATION_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PROFESSION_TYPE_COMPANY_FK',1,'PROFESSION_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','ADDRESS_TYPE_COMPANY_FK',1,'ADDRESS_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PHONE_TYPE_COMPANY_FK',1,'PHONE_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','DOCUMENT_TYPE_COMPANY_FK',1,'DOCUMENT_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_TYPE_COMPANY_FK',1,'PERSON_TYPE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_COMPANY_FK',1,'PERSON','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_ADDRESS_COMPANY_FK',1,'PERSON_ADDRESS','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_PHONE_COMPANY_FK',1,'PERSON_PHONE','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_DOCUMENT_COMPANY_FK',1,'PERSON_DOCUMENT','COMPANY_ID','COMPANY','COMPANY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','GENDER_TYPE_LANGUAGE_FK',1,'GENDER_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','CIVIL_STATUS_LANGUAGE_FK',1,'CIVIL_STATUS','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','IDENTIFICATION_TYPE_LANGUAG_FK',1,'IDENTIFICATION_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PROFESSION_TYPE_LANGUAGE_FK',1,'PROFESSION_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','ADDRESS_TYPE_LANGUAGE_FK',1,'ADDRESS_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PHONE_TYPE_LANGUAGE_FK',1,'PHONE_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','DOCUMENT_TYPE_LANGUAGE_FK',1,'DOCUMENT_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_TYPE_LANGUAGE_FK',1,'PERSON_TYPE','LANGUAGE_ID','LANGUAGE','LANGUAGE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_IDENTIF_TYPE_ID_FK',1,'PERSON','IDENTIFICATION_TYPE_ID','IDENTIFICATION_TYPE_ID','IDENTIFICATION_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_GENDER_TYPE_ID_FK',1,'PERSON','GENDER_TYPE_ID','GENDER_TYPE_ID','GENDER_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_CIVIL_STATUS_ID_FK',1,'PERSON','CIVIL_STATUS_ID','CIVIL_STATUS_ID','CIVIL_STATUS_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_CITY_ID_FK',1,'PERSON','COUNTRY_ID','CITY_ID','COUNTRY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_CITY_ID_FK',2,'PERSON','PROVINCE_ID','CITY_ID','PROVINCE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_CITY_ID_FK',3,'PERSON','CITY_ID','CITY_ID','CITY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_PROFESSION_TYPE_ID_FK',1,'PERSON','PROFESSION_TYPE_ID','PROFESSION_TYPE_ID','PROFESSION_TYPE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_ADDRESS_CITY_ID_FK',1,'PERSON_ADDRESS','COUNTRY_ID','CITY_ID','COUNTRY_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_ADDRESS_CITY_ID_FK',2,'PERSON_ADDRESS','PROVINCE_ID','CITY_ID','PROVINCE_ID');
INSERT INTO ENTITY_RELATIONSHIP VALUES ('ALL','PERSON_ADDRESS_CITY_ID_FK',3,'PERSON_ADDRESS','CITY_ID','CITY_ID','CITY_ID');

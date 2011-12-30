INSERT INTO RESPONSE_ID VALUES ('SUCCESS');
INSERT INTO RESPONSE_ID VALUES ('FAILED');

INSERT INTO RESPONSE VALUES ('ALL', 'ES', 'SUCCESS', 'PROCESO EXITOSO');
INSERT INTO RESPONSE VALUES ('ALL', 'ES', 'FAILED', 'PROCESO FALLIDO');
INSERT INTO RESPONSE VALUES ('ALL', 'EN', 'SUCCESS', 'PROCESS SUCCESSFUL');
INSERT INTO RESPONSE VALUES ('ALL', 'EN', 'FAILED', 'PROCESS FAILED');

INSERT INTO DATAFILE_TYPE VALUES ('DOC','Word Document Format');
INSERT INTO DATAFILE_TYPE VALUES ('XLS','Spreadsheet File Format');
INSERT INTO DATAFILE_TYPE VALUES ('PDF','Portable Document Format');
INSERT INTO DATAFILE_TYPE VALUES ('JPG','Image File Format');

insert into COUNTRY_ID (COUNTRY_ID) values ('EC');

insert into COUNTRY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, NAME, AREA_CODE) values ('MXT', 'ES', 'EC', 'ECUADOR', '0000');

insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'AZ');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'BO');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'CA');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'CH');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'CO');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'CR');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'EL');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'ES');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'GA');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'GU');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'IM');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'LO');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'MA');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'MO');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'NA');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'OR');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'PA');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'PI');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'RI');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'SU');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'TU');
insert into PROVINCE_ID (COUNTRY_ID, PROVINCE_ID) values ('EC', 'ZA');

insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'Azuay');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'BO', 'Bolivar');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'CA', 'Carchi');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'CH', 'Chimborazo');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'CO', 'Cotopaxi');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'CR', 'Canar');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'EL', 'ElOro');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'ES', 'Esmeraldas');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'GA', 'Galapagos');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'GU', 'Guayas');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'IM', 'Imbabura');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'LO', 'Loja');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'MA', 'Manabi');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'MO', 'Morona-Santiago');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'NA', 'Napo');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'OR', 'Orellana');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'PA', 'Pastaza');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'PI', 'Pichincha');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'RI', 'LosRios');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'SU', 'Sucumbios');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'TU', 'Tungurahua');
insert into PROVINCE (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, NAME) values ('MXT', 'ES', 'EC', 'ZA', 'Zamora-Chinchipe');


insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'CH');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'CU');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'EL');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'GI');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'GL');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'GU');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'NA');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'OA');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'PA');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'PO');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'PU');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'SA');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'SG');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'SI');
insert into CITY_ID (COUNTRY_ID, PROVINCE_ID, CITY_ID) values ('EC', 'AZ', 'SO');

insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'CH', 'Chordeleg');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'CU', 'Cuenca');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'EL', 'El Pan');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'GI', 'Giron');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'GL', 'Gualaceo');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'GU', 'Guachapala');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'NA', 'Nabon');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'OA', 'Oña');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'PA', 'Paute');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'PO', 'Camilo Ponce');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'PU', 'Pucara');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'SA', 'San Fernando');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'SG', 'Sigsig');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'SI', 'Santa Isabel');
insert into CITY (COMPANY_ID, LANGUAGE_ID, COUNTRY_ID, PROVINCE_ID, CITY_ID, NAME) values ('MXT', 'ES', 'EC', 'AZ', 'SO', 'Sevilla De Oro');



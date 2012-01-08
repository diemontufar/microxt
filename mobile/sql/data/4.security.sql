insert into SUBSYSTEM_ID (SUBSYSTEM_ID) values ('A');
insert into SUBSYSTEM_ID (SUBSYSTEM_ID) values ('B');
insert into SUBSYSTEM_ID (SUBSYSTEM_ID) values ('C');
insert into SUBSYSTEM_ID (SUBSYSTEM_ID) values ('G');

insert into SUBSYSTEM (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, NAME) values ('MXT', 'ES', 'A', 'SEGURIDADES');
insert into SUBSYSTEM (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, NAME) values ('MXT', 'ES', 'B', 'PERSONAS');
insert into SUBSYSTEM (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, NAME) values ('MXT', 'ES', 'C', 'MICROCREDITO');
insert into SUBSYSTEM (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, NAME) values ('MXT', 'ES', 'G', 'GENERALES');

insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('A', '0');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('A', '1');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('A', '2');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '0');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '1');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '2');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '3');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '4');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('C', '5');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('G', '0');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('G', '1');
insert into MODULE_ID (SUBSYSTEM_ID, MODULE_ID) values ('G', '2');

insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'A', '0', 'AUTENTITICACION');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'A', '1', 'DATOS GENERALES');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'A', '2', 'ROLES Y USUARIOS');

insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '0', 'PARAMETRIZACION');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '1', 'PLANIFICACION');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '2', 'COMERCIALIZACION');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '3', 'SOLICITUD');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '4', 'APROBACION CORE');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'C', '5', 'SEGUIMIENTO Y RECUPERACION');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'G', '0', 'MENU');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'G', '1', 'PARAMETROS');
insert into MODULE (COMPANY_ID, LANGUAGE_ID, SUBSYSTEM_ID, MODULE_ID, NAME) values ('MXT', 'ES', 'G', '2', 'LISTAS PARA LOS COMBOS');

insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '0', '01');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '0', '02');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '1', '01');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '1', '02');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '01');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '02');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '03');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '04');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '05');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('A', '2', '06');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('G', '0', '01');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('G', '1', '01');
insert into PROCESS_ID (SUBSYSTEM_ID, MODULE_ID, PROCESS_ID) values ('G', '2', '01');

insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '0', '01', '2011-10-14 00:00:00', 'LOGGIN', '1', 'A001', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '01', '2011-10-14 00:00:00', 'ESTADO DE USUARIOS', '1', 'A101', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '02', '2011-11-27 00:00:00', 'TIPOS DE USUARIO', '1', 'A102', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '01', '2011-10-14 00:00:00', 'ROLES', '1', 'A201', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '02', '2011-11-29 00:00:00', 'PROCESOS POR ROL', '1', 'A202', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '03', '2011-11-30 00:00:00', 'USUARIOS', '1', 'A203', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '04', '2011-11-30 00:00:00', 'ROLES POR USUARIO', '1', 'A204', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '05', '2011-11-30 00:00:00', 'PASSWORD USUARIOS', '1', 'A205', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '06', '2011-12-07 00:00:00', 'TERMINALES', '1', 'A206', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '01', '2012-01-08 00:00:00', 'ASESOR DE CREDITO', '1', 'C001', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '02', '2012-01-08 00:00:00', 'ZONAS GEOGRAFICAS', '1', 'C102', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '03', '2012-01-08 00:00:00', 'ZONAS POR ASESOR', '1', 'C003', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '04', '2012-01-08 00:00:00', 'PRODUCTOS', '1', 'C004', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '05', '2012-01-08 00:00:00', 'PRODUCTOS POR ASESOR', '1', 'C005', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'G', '0', '01', '2011-10-14 00:00:00', 'MENU PRINCIPAL', '1', 'G001', null, '0');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'G', '1', '01', '2011-10-14 00:00:00', 'PARAMETROS GENERALES', '1', 'G101', null, '1');
insert into PROCESS (COMPANY_ID, LANGUAGE_ID, EXPIRED, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, CREATED, NAME, ENABLE, URL, DATAFILE_ID, MENU) values ('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '01', '2011-10-14 00:00:00', 'LISTA DE VALORES PARA LOS COMBOS', '1', 'G201', null, '0');

insert into COMPONENT_ID (COMPONENT_ID) values ('mobile.bus.parameter.ParameterTest');
insert into COMPONENT_ID (COMPONENT_ID) values ('mobile.bus.security.Loggin');
insert into COMPONENT_ID (COMPONENT_ID) values ('mobile.core.processor.MaintenanceProcessor');
insert into COMPONENT_ID (COMPONENT_ID) values ('mobile.core.processor.SpecialQueryProcessor');
insert into COMPONENT_ID (COMPONENT_ID) values ('mobile.logic.general.QueryMainMenuItems');

insert into COMPONENT (COMPANY_ID, COMPONENT_ID, SUBSYSTEM_ID, CLASS_NAME, METHOD_NAME, DESCRIPTION) values ('MXT', 'mobile.bus.security.Loggin', 'A', 'Loggin', 'general', 'Process loggin');
insert into COMPONENT (COMPANY_ID, COMPONENT_ID, SUBSYSTEM_ID, CLASS_NAME, METHOD_NAME, DESCRIPTION) values ('MXT', 'mobile.core.processor.MaintenanceProcessor', 'G', 'MaintenanceProcessor', 'general', 'General maintenance processor');
insert into COMPONENT (COMPANY_ID, COMPONENT_ID, SUBSYSTEM_ID, CLASS_NAME, METHOD_NAME, DESCRIPTION) values ('MXT', 'mobile.core.processor.SpecialQueryProcessor', 'G', 'SpecialQueryProcessor', 'general', 'Special query');

insert into PROCESS_COMPONENT (COMPANY_ID, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, PROCESS_SEQUENCE, COMPONENT_ID, ENABLE, AUTHORIZE) values ('MXT', 'A', '0', '01', 1, 'mobile.bus.security.Loggin', '1', '0');
insert into PROCESS_COMPONENT (COMPANY_ID, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, PROCESS_SEQUENCE, COMPONENT_ID, ENABLE, AUTHORIZE) values ('MXT', 'A', '0', '02', 1, 'mobile.bus.parameter.ParameterTest', '1', '0');
insert into PROCESS_COMPONENT (COMPANY_ID, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, PROCESS_SEQUENCE, COMPONENT_ID, ENABLE, AUTHORIZE) values ('MXT', 'A', '2', '02', 1, 'mobile.core.processor.SpecialQueryProcessor', '1', '0');
insert into PROCESS_COMPONENT (COMPANY_ID, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, PROCESS_SEQUENCE, COMPONENT_ID, ENABLE, AUTHORIZE) values ('MXT', 'A', '2', '02', 2, 'mobile.core.processor.MaintenanceProcessor', '1', '0');
insert into PROCESS_COMPONENT (COMPANY_ID, SUBSYSTEM_ID, MODULE_ID, PROCESS_ID, PROCESS_SEQUENCE, COMPONENT_ID, ENABLE, AUTHORIZE) values ('MXT', 'G', '0', '01', 1, 'mobile.logic.general.QueryMainMenuItems', '1', '0');


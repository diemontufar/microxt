-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 02-04-2012 a las 00:48:37
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `microxt3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ACCOUNT_STATUS`
--

CREATE TABLE IF NOT EXISTS `ACCOUNT_STATUS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `STATUS_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`STATUS_ID`),
  KEY `ACCOUNT_STATUS_ID_FK` (`STATUS_ID`),
  KEY `ACCOUNT_STATUS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ACCOUNT_STATUS`
--

INSERT INTO `ACCOUNT_STATUS` (`COMPANY_ID`, `LANGUAGE_ID`, `STATUS_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', '001', 'SOLICITADO'),
('MXT', 'ES', '002', 'APROVADO'),
('MXT', 'ES', '003', 'VIGENTE'),
('MXT', 'ES', '004', 'VENCIDO'),
('MXT', 'ES', '005', 'CASTIGADO'),
('MXT', 'ES', '006', 'CANCELADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ACCOUNT_STATUS_ID`
--

CREATE TABLE IF NOT EXISTS `ACCOUNT_STATUS_ID` (
  `STATUS_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ACCOUNT_STATUS_ID`
--

INSERT INTO `ACCOUNT_STATUS_ID` (`STATUS_ID`) VALUES
('001'),
('002'),
('003'),
('004'),
('005'),
('006');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADDRESS_TYPE`
--

CREATE TABLE IF NOT EXISTS `ADDRESS_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `ADDRESS_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`ADDRESS_TYPE_ID`),
  KEY `ADDRESS_TYPE_ID_FK` (`ADDRESS_TYPE_ID`),
  KEY `ADDRESS_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ADDRESS_TYPE`
--

INSERT INTO `ADDRESS_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `ADDRESS_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', 'CE', 'CORREO ELECTRÓNICO'),
('MXT', 'ES', 'HA', 'DOMICILIO'),
('MXT', 'ES', 'ML', 'CORREO'),
('MXT', 'ES', 'TR', 'LUGAR DE TRABAJO'),
('MXT', 'ES', 'WE', 'PAGINA WEB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADDRESS_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `ADDRESS_TYPE_ID` (
  `ADDRESS_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`ADDRESS_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ADDRESS_TYPE_ID`
--

INSERT INTO `ADDRESS_TYPE_ID` (`ADDRESS_TYPE_ID`) VALUES
('CE'),
('HA'),
('ML'),
('TR'),
('WE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CHANNEL`
--

CREATE TABLE IF NOT EXISTS `CHANNEL` (
  `CHANNEL_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`CHANNEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CHANNEL`
--

INSERT INTO `CHANNEL` (`CHANNEL_ID`, `NAME`) VALUES
('MOBI', 'MOBILE'),
('PC', 'COMPUTADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CITY`
--

CREATE TABLE IF NOT EXISTS `CITY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  `CITY_ID` varchar(3) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `CITY_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `CITY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CITY`
--

INSERT INTO `CITY` (`COMPANY_ID`, `LANGUAGE_ID`, `COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `NAME`) VALUES
('MXT', 'ES', 'EC', 'AZ', 'CH', 'CHORDELEG'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CUENCA'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'EL PAN'),
('MXT', 'ES', 'EC', 'AZ', 'GI', 'GIRÓN'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'GUALACEO'),
('MXT', 'ES', 'EC', 'AZ', 'GU', 'GUACHAPALA'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'NABON'),
('MXT', 'ES', 'EC', 'AZ', 'OA', 'OÑA'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'PAUTE'),
('MXT', 'ES', 'EC', 'AZ', 'PO', 'CAMILO PONCE'),
('MXT', 'ES', 'EC', 'AZ', 'SA', 'SAN FERNANDO'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'SIGSIG'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'SANTA ISABEL'),
('MXT', 'ES', 'EC', 'AZ', 'SO', 'SEVILLA DE ORO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CITY_ID`
--

CREATE TABLE IF NOT EXISTS `CITY_ID` (
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  `CITY_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CITY_ID`
--

INSERT INTO `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) VALUES
('EC', 'AZ', 'CH'),
('EC', 'AZ', 'CU'),
('EC', 'AZ', 'EL'),
('EC', 'AZ', 'GI'),
('EC', 'AZ', 'GL'),
('EC', 'AZ', 'GU'),
('EC', 'AZ', 'NA'),
('EC', 'AZ', 'OA'),
('EC', 'AZ', 'PA'),
('EC', 'AZ', 'PO'),
('EC', 'AZ', 'SA'),
('EC', 'AZ', 'SG'),
('EC', 'AZ', 'SI'),
('EC', 'AZ', 'SO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CIVIL_STATUS`
--

CREATE TABLE IF NOT EXISTS `CIVIL_STATUS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `CIVIL_STATUS_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`CIVIL_STATUS_ID`),
  KEY `CIVIL_STATUS_ID_FK` (`CIVIL_STATUS_ID`),
  KEY `CIVIL_STATUS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CIVIL_STATUS`
--

INSERT INTO `CIVIL_STATUS` (`COMPANY_ID`, `LANGUAGE_ID`, `CIVIL_STATUS_ID`, `NAME`) VALUES
('MXT', 'ES', 'CAS', 'CASADO'),
('MXT', 'ES', 'DIV', 'DIVORCIADO'),
('MXT', 'ES', 'SEP', 'SEPARADO'),
('MXT', 'ES', 'SOL', 'SOLTERO'),
('MXT', 'ES', 'UNI', 'UNION LIBRE'),
('MXT', 'ES', 'VIU', 'VIUDO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CIVIL_STATUS_ID`
--

CREATE TABLE IF NOT EXISTS `CIVIL_STATUS_ID` (
  `CIVIL_STATUS_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`CIVIL_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CIVIL_STATUS_ID`
--

INSERT INTO `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`) VALUES
('CAS'),
('DIV'),
('SEP'),
('SOL'),
('UNI'),
('VIU');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPANY`
--

CREATE TABLE IF NOT EXISTS `COMPANY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `DATAFILE_ID` int(11) DEFAULT NULL,
  `UPGRADE_NUMBER` decimal(4,2) DEFAULT NULL,
  `LICENSE_DATE` datetime DEFAULT NULL,
  `ENABLE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPANY`
--

INSERT INTO `COMPANY` (`COMPANY_ID`, `NAME`, `DATAFILE_ID`, `UPGRADE_NUMBER`, `LICENSE_DATE`, `ENABLE`) VALUES
('ALL', 'DEFAULT COMPANY', NULL, 1.10, '2011-01-01 00:00:00', 1),
('MXT', 'MICROXT', NULL, 1.00, '2011-01-01 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT`
--

CREATE TABLE IF NOT EXISTS `COMPONENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `COMPONENT_ID` varchar(150) NOT NULL,
  `TYPE_ID` varchar(3) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `CLASS_NAME` varchar(100) NOT NULL,
  `METHOD_NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`COMPONENT_ID`,`TYPE_ID`),
  KEY `COMPONENT_COM_TYPE_ID_FK` (`TYPE_ID`),
  KEY `COMPONENT_ID_FK` (`COMPONENT_ID`,`TYPE_ID`),
  KEY `COMPONENT_SUBSYSTEM_ID_FK` (`SUBSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT`
--

INSERT INTO `COMPONENT` (`COMPANY_ID`, `COMPONENT_ID`, `TYPE_ID`, `SUBSYSTEM_ID`, `CLASS_NAME`, `METHOD_NAME`, `DESCRIPTION`) VALUES
('MXT', 'mobile.core.processor.MaintenanceProcessor', 'MNT', 'G', 'MaintenanceProcessor', 'general', 'General maintenance processor'),
('MXT', 'mobile.core.processor.QueryProcessor', 'QRY', 'G', 'QueryProcessor', 'general', 'General query processor'),
('MXT', 'mobile.core.security.ChangePassword', 'MNT', 'A', 'ChangePassword', NULL, 'Change password'),
('MXT', 'mobile.core.security.Loggin', 'QRY', 'A', 'Loggin', 'general', 'Process loggin'),
('MXT', 'mobile.core.security.Logout', 'MNT', 'A', 'Logout', NULL, 'Process logout'),
('MXT', 'mobile.core.security.query.QueryProfilesForUser', 'QRY', 'A', 'QueryProfilesForUser', 'general', 'CONSULTA LOS ROLES DE UN USUARIO'),
('MXT', 'mobile.logic.general.MenuGenerator', 'QRY', 'G', 'MenuGenerator', 'general', 'Query the menu items'),
('MXT', 'mobile.logic.microxt.query.QueryPartnerInfo', 'QRY', 'C', 'QueryPartnerInfo', NULL, 'CONSULTA INFORMACION DE CLIENTES INDIVIDUALES'),
('MXT', 'mobile.logic.microxt.query.QuerySolicitude', 'QRY', 'C', 'QuerySolicitude', NULL, 'CONSULTA INFORMACION DE LAS SOLICITUDES'),
('MXT', 'mobile.logic.microxt.RemoteInstrumentation', 'MNT', 'C', 'RemoteInstrumentation', NULL, 'REALIZA LA INSTRUMENTACIÓN CONECTANDOSE CON EL CORE BANCARIO'),
('MXT', 'mobile.logic.person.QueryPersonInfo', 'QRY', 'B', 'QueryPersonInfo', NULL, 'CONSULTA LOS DATOS DE PERSONAS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT_ID`
--

CREATE TABLE IF NOT EXISTS `COMPONENT_ID` (
  `COMPONENT_ID` varchar(150) NOT NULL,
  `TYPE_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`COMPONENT_ID`,`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT_ID`
--

INSERT INTO `COMPONENT_ID` (`COMPONENT_ID`, `TYPE_ID`) VALUES
('mobile.core.processor.MaintenanceProcessor', 'MNT'),
('mobile.core.processor.QueryProcessor', 'QRY'),
('mobile.core.security.ChangePassword', 'MNT'),
('mobile.core.security.Loggin', 'QRY'),
('mobile.core.security.Logout', 'MNT'),
('mobile.core.security.query.QueryProfilesForUser', 'QRY'),
('mobile.logic.general.MenuGenerator', 'QRY'),
('mobile.logic.microxt.query.QueryPartnerInfo', 'QRY'),
('mobile.logic.microxt.query.QuerySolicitude', 'QRY'),
('mobile.logic.microxt.RemoteInstrumentation', 'MNT'),
('mobile.logic.person.QueryPersonInfo', 'QRY');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT_TYPE`
--

CREATE TABLE IF NOT EXISTS `COMPONENT_TYPE` (
  `COMPONENT_TYPE_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPONENT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT_TYPE`
--

INSERT INTO `COMPONENT_TYPE` (`COMPONENT_TYPE_ID`, `DESCRIPTION`) VALUES
('MNT', 'MANTENIMIENTO'),
('QRY', 'CONSULTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COUNTRY`
--

CREATE TABLE IF NOT EXISTS `COUNTRY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `AREA_CODE` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`),
  KEY `COUNTRY_ID_FK` (`COUNTRY_ID`),
  KEY `COUNTRY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COUNTRY`
--

INSERT INTO `COUNTRY` (`COMPANY_ID`, `LANGUAGE_ID`, `COUNTRY_ID`, `NAME`, `AREA_CODE`) VALUES
('MXT', 'ES', 'EC', 'ECUADOR', '0000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COUNTRY_ID`
--

CREATE TABLE IF NOT EXISTS `COUNTRY_ID` (
  `COUNTRY_ID` varchar(2) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COUNTRY_ID`
--

INSERT INTO `COUNTRY_ID` (`COUNTRY_ID`) VALUES
('EC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CURRENCY`
--

CREATE TABLE IF NOT EXISTS `CURRENCY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `CURRENCY_ID` varchar(3) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  `INITIALS` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`CURRENCY_ID`),
  KEY `CURRENCY_ID_FK` (`CURRENCY_ID`),
  KEY `CURRENCY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CURRENCY`
--

INSERT INTO `CURRENCY` (`COMPANY_ID`, `LANGUAGE_ID`, `EXPIRED`, `CURRENCY_ID`, `CREATED`, `DESCRIPTION`, `INITIALS`) VALUES
('MXT', 'ES', '9999-12-31 00:00:00', 'EUR', '2012-01-20 20:46:58', 'EURO', '$'),
('MXT', 'ES', '9999-12-31 00:00:00', 'USD', '2012-01-20 20:46:58', 'DOLAR', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CURRENCY_ID`
--

CREATE TABLE IF NOT EXISTS `CURRENCY_ID` (
  `CURRENCY_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`CURRENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CURRENCY_ID`
--

INSERT INTO `CURRENCY_ID` (`CURRENCY_ID`) VALUES
('EUR'),
('USD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATABASE_TYPE`
--

CREATE TABLE IF NOT EXISTS `DATABASE_TYPE` (
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `DATABASE_ID` varchar(30) NOT NULL,
  `DATA_SIZE` smallint(6) NOT NULL,
  `DATABASE_TYPE` varchar(30) NOT NULL,
  PRIMARY KEY (`DATA_TYPE_ID`,`DATABASE_ID`,`DATA_SIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATABASE_TYPE`
--

INSERT INTO `DATABASE_TYPE` (`DATA_TYPE_ID`, `DATABASE_ID`, `DATA_SIZE`, `DATABASE_TYPE`) VALUES
('BigDecimal', 'MYSQL', 0, 'DECIMAL'),
('BigDecimal', 'ORACLE', 0, 'NUMBER'),
('Blob', 'MYSQL', 0, 'BLOB'),
('Blob', 'ORACLE', 0, 'BLOB'),
('Boolean', 'MYSQL', 0, 'TINYINT'),
('Boolean', 'ORACLE', 0, 'VARCHAR2'),
('Clob', 'MYSQL', 0, 'TEXT'),
('Clob', 'ORACLE', 0, 'CLOB'),
('Date', 'MYSQL', 0, 'DATETIME'),
('Date', 'ORACLE', 0, 'DATE'),
('Integer', 'MYSQL', 0, 'INTEGER'),
('Integer', 'MYSQL', 3, 'TINYINT'),
('Integer', 'MYSQL', 5, 'SMALLINT'),
('Integer', 'MYSQL', 7, 'MEDIUMINT'),
('Integer', 'ORACLE', 0, 'NUMBER'),
('Long', 'MYSQL', 0, 'BIGINT'),
('Long', 'MYSQL', 10, 'INTEGER'),
('Long', 'ORACLE', 0, 'NUMBER'),
('String', 'MYSQL', 0, 'VARCHAR'),
('String', 'ORACLE', 0, 'VARCHAR2'),
('Timestamp', 'MYSQL', 0, 'TIMESTAMP'),
('Timestamp', 'ORACLE', 0, 'TIMESTAMP');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATAFILE`
--

CREATE TABLE IF NOT EXISTS `DATAFILE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `DATAFILE_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DATAFILE_TYPE_ID` varchar(4) NOT NULL,
  `BINARY_PATH` varchar(200) NOT NULL,
  `BINARY_BYTES` int(11) DEFAULT NULL,
  `BINARY_OBJECT` blob,
  `CHARACTER_DATA` text,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`DATAFILE_ID`),
  KEY `DATAFILE_DATAFILE_TYPE_FK` (`DATAFILE_TYPE_ID`),
  KEY `DATAFILE_ID_FK` (`DATAFILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATAFILE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATAFILE_ID`
--

CREATE TABLE IF NOT EXISTS `DATAFILE_ID` (
  `DATAFILE_ID` int(11) NOT NULL,
  PRIMARY KEY (`DATAFILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATAFILE_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATAFILE_TYPE`
--

CREATE TABLE IF NOT EXISTS `DATAFILE_TYPE` (
  `DATAFILE_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`DATAFILE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATAFILE_TYPE`
--

INSERT INTO `DATAFILE_TYPE` (`DATAFILE_TYPE_ID`, `NAME`) VALUES
('DOC', 'Word Document Format'),
('JPG', 'Image File Format'),
('PDF', 'Portable Document Format'),
('XLS', 'Spreadsheet File Format');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATA_TYPE`
--

CREATE TABLE IF NOT EXISTS `DATA_TYPE` (
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`DATA_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATA_TYPE`
--

INSERT INTO `DATA_TYPE` (`DATA_TYPE_ID`, `DESCRIPTION`) VALUES
('BigDecimal', 'Number with decimal fractions'),
('Blob', 'Binary large object'),
('Boolean', 'Logic values true and false'),
('Clob', 'Character large object'),
('Date', 'Sequence of characters that denoting the date'),
('Integer', 'Sequence of digits less than 10'),
('Long', 'Sequence of digits greater than 10 or equal'),
('String', 'Sequence of characters'),
('Timestamp', 'Sequence of characters that denoting the date and/or time');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DISTRICT`
--

CREATE TABLE IF NOT EXISTS `DISTRICT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  `CITY_ID` varchar(3) NOT NULL,
  `DISTRICT_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`),
  KEY `DISTRICT_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`),
  KEY `DISTRICT_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DISTRICT`
--

INSERT INTO `DISTRICT` (`COMPANY_ID`, `LANGUAGE_ID`, `COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`, `NAME`) VALUES
('MXT', 'ES', 'EC', 'AZ', 'CH', 'CHO', 'CHORDELEG'),
('MXT', 'ES', 'EC', 'AZ', 'CH', 'MAR', 'SAN MARTIN DE PUZHIO'),
('MXT', 'ES', 'EC', 'AZ', 'CH', 'PRI', 'PRINCIPAL'),
('MXT', 'ES', 'EC', 'AZ', 'CH', 'UNI', 'LA UNION'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'ANA', 'SANTA ANA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'BAÑ', 'BAÑOS'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'BAT', 'EL BATAN'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'BEL', 'BELLAVISTA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'BLA', 'SAN BLAS'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CAÑ', 'CAÑARIBAMBA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CAP', 'SAN CAPAC'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CHA', 'CHAUCHA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CHE', 'CHECA (JIDCAY)'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CHI', 'CHIQUINTAD'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CUE', 'CUENCA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'CUM', 'CUMBE'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'GIL', 'GIL RAMIREZ DAVALOS'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'HER', 'HERMANO MIGUEL'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'HUA', 'HUAYNACAPAC'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'JOA', 'SAN JOAQUIN'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'LLA', 'LLACAO'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'MAC', 'MACHANGARA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'MOL', 'MOLLETURO'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'MON', 'MONAY'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'NUL', 'NULTI'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'OCT', 'OCTAVIO CORDERO(SANTA ROSA)'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'PAC', 'PACCHA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'QUI', 'QUINGEO'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'RIC', 'RICAURTE'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SAG', 'EL SAGRARIO'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SAY', 'SAYAUSI'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SEB', 'SAN SEBASTIAN'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SID', 'SIDCAY'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SIN', 'SININCAY'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'SUC', 'SUCRE'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'TAR', 'TARQUI'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'TOT', 'TOTORACOCHA'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'TUR', 'TURI'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'VAL', 'VALLE'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'VEC', 'EL VECINO'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'VIC', 'VICTORIA DEL PORTETE (IRQUIS)'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'YAN', 'YANUNCAY'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'AML', '*AMALUZA'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'ELP', 'EL PAN'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'PLM', '*PALMAS'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'VIC', 'SAN VICENTE'),
('MXT', 'ES', 'EC', 'AZ', 'GI', 'ASU', 'ASUNCION'),
('MXT', 'ES', 'EC', 'AZ', 'GI', 'GER', 'SAN GERARDO'),
('MXT', 'ES', 'EC', 'AZ', 'GI', 'GIR', 'GIRON'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'CHO', 'CHORDELEG'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'GUA', 'GUALACEO'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'JAD', 'JADAN'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'JUA', 'SAN JUAN'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'MAR', 'MARIANO MORENO'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'PRI', 'PRINCIPAL'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'REM', 'REMIGIO CRESPO TORAL (GULAG)'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'VEG', 'LUIS CORDERO VEGA'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'ZHI', 'ZHIDMAD'),
('MXT', 'ES', 'EC', 'AZ', 'GU', 'GUA', 'GUACHAPALA'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'COC', 'COCHAPATA'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'NAB', 'NABON'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'NIE', 'LAS NIEVES (CHAYA)'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'OÑA', 'OÑA'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'PRO', 'EL PROGRESO (CAB.EN ZHOTA)'),
('MXT', 'ES', 'EC', 'AZ', 'OA', 'OÑA', '*OÑA'),
('MXT', 'ES', 'EC', 'AZ', 'OA', 'SUS', 'SUSUDEL'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'AMA', '*AMALUZA'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'BUL', 'BULAN (JOSE VICTOR IZQUIERDO)'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'CAB', 'EL CABO'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'CHA', '*GUACHAPALA'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'CHI', 'CHICAN (GUILLERMO ORTEGA)'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'CRI', 'SAN CRISTOBAL(CARLOS ORDO?EZ )'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'DUG', 'DUG DUG'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'NAG', 'GUARAINAG'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'ORO', '*SEVILLA DE ORO'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'PAL', '*PALMAS'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'PAN', '*PAN'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'PAU', 'PAUTE'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'TOM', 'TOMEBAMBA'),
('MXT', 'ES', 'EC', 'AZ', 'PO', 'PON', 'CAMILO PONCE ENRIQUEZ'),
('MXT', 'ES', 'EC', 'AZ', 'PO', 'PUC', 'PUCARA'),
('MXT', 'ES', 'EC', 'AZ', 'PO', 'RAF', 'SAN RAFAEL DE SHARUG'),
('MXT', 'ES', 'EC', 'AZ', 'SA', 'CHU', 'CHUMBLIN'),
('MXT', 'ES', 'EC', 'AZ', 'SA', 'FER', 'SAN FERNANDO'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'BAR', 'SAN BARTOLOME'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'CUC', 'CUCHIL (CUTCHIL)'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'GIM', 'GIMA'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'GUE', 'GUEL'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'JOS', 'SAN JOSE DE RARANGA'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'LUD', 'LUDO'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'SIG', 'SIGSIG'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'CAL', 'ABDON CALDERON  (LA UNION)'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'CAR', 'EL CARMEN DE PIJILI'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'ISA', 'SANTA ISABEL (CHAGUARURCO)'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'ZHA', 'ZHAGLLI (SHAGLLI)'),
('MXT', 'ES', 'EC', 'AZ', 'SO', 'AMA', 'AMALUZA'),
('MXT', 'ES', 'EC', 'AZ', 'SO', 'PAL', 'PALMAS'),
('MXT', 'ES', 'EC', 'AZ', 'SO', 'SEV', 'SEVILLA DE ORO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DISTRICT_ID`
--

CREATE TABLE IF NOT EXISTS `DISTRICT_ID` (
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  `CITY_ID` varchar(3) NOT NULL,
  `DISTRICT_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DISTRICT_ID`
--

INSERT INTO `DISTRICT_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`) VALUES
('EC', 'AZ', 'CH', 'CHO'),
('EC', 'AZ', 'CH', 'MAR'),
('EC', 'AZ', 'CH', 'PRI'),
('EC', 'AZ', 'CH', 'UNI'),
('EC', 'AZ', 'CU', 'ANA'),
('EC', 'AZ', 'CU', 'BAÑ'),
('EC', 'AZ', 'CU', 'BAT'),
('EC', 'AZ', 'CU', 'BEL'),
('EC', 'AZ', 'CU', 'BLA'),
('EC', 'AZ', 'CU', 'CAÑ'),
('EC', 'AZ', 'CU', 'CAP'),
('EC', 'AZ', 'CU', 'CHA'),
('EC', 'AZ', 'CU', 'CHE'),
('EC', 'AZ', 'CU', 'CHI'),
('EC', 'AZ', 'CU', 'CUE'),
('EC', 'AZ', 'CU', 'CUM'),
('EC', 'AZ', 'CU', 'GIL'),
('EC', 'AZ', 'CU', 'HER'),
('EC', 'AZ', 'CU', 'HUA'),
('EC', 'AZ', 'CU', 'JOA'),
('EC', 'AZ', 'CU', 'LLA'),
('EC', 'AZ', 'CU', 'MAC'),
('EC', 'AZ', 'CU', 'MOL'),
('EC', 'AZ', 'CU', 'MON'),
('EC', 'AZ', 'CU', 'NUL'),
('EC', 'AZ', 'CU', 'OCT'),
('EC', 'AZ', 'CU', 'PAC'),
('EC', 'AZ', 'CU', 'QUI'),
('EC', 'AZ', 'CU', 'RIC'),
('EC', 'AZ', 'CU', 'SAG'),
('EC', 'AZ', 'CU', 'SAY'),
('EC', 'AZ', 'CU', 'SEB'),
('EC', 'AZ', 'CU', 'SID'),
('EC', 'AZ', 'CU', 'SIN'),
('EC', 'AZ', 'CU', 'SUC'),
('EC', 'AZ', 'CU', 'TAR'),
('EC', 'AZ', 'CU', 'TOT'),
('EC', 'AZ', 'CU', 'TUR'),
('EC', 'AZ', 'CU', 'VAL'),
('EC', 'AZ', 'CU', 'VEC'),
('EC', 'AZ', 'CU', 'VIC'),
('EC', 'AZ', 'CU', 'YAN'),
('EC', 'AZ', 'EL', 'AML'),
('EC', 'AZ', 'EL', 'ELP'),
('EC', 'AZ', 'EL', 'PLM'),
('EC', 'AZ', 'EL', 'VIC'),
('EC', 'AZ', 'GI', 'ASU'),
('EC', 'AZ', 'GI', 'GER'),
('EC', 'AZ', 'GI', 'GIR'),
('EC', 'AZ', 'GL', 'CHO'),
('EC', 'AZ', 'GL', 'GUA'),
('EC', 'AZ', 'GL', 'JAD'),
('EC', 'AZ', 'GL', 'JUA'),
('EC', 'AZ', 'GL', 'MAR'),
('EC', 'AZ', 'GL', 'PRI'),
('EC', 'AZ', 'GL', 'REM'),
('EC', 'AZ', 'GL', 'VEG'),
('EC', 'AZ', 'GL', 'ZHI'),
('EC', 'AZ', 'GU', 'GUA'),
('EC', 'AZ', 'NA', 'COC'),
('EC', 'AZ', 'NA', 'NAB'),
('EC', 'AZ', 'NA', 'NIE'),
('EC', 'AZ', 'NA', 'OÑA'),
('EC', 'AZ', 'NA', 'PRO'),
('EC', 'AZ', 'OA', 'OÑA'),
('EC', 'AZ', 'OA', 'SUS'),
('EC', 'AZ', 'PA', 'AMA'),
('EC', 'AZ', 'PA', 'BUL'),
('EC', 'AZ', 'PA', 'CAB'),
('EC', 'AZ', 'PA', 'CHA'),
('EC', 'AZ', 'PA', 'CHI'),
('EC', 'AZ', 'PA', 'CRI'),
('EC', 'AZ', 'PA', 'DUG'),
('EC', 'AZ', 'PA', 'NAG'),
('EC', 'AZ', 'PA', 'ORO'),
('EC', 'AZ', 'PA', 'PAL'),
('EC', 'AZ', 'PA', 'PAN'),
('EC', 'AZ', 'PA', 'PAU'),
('EC', 'AZ', 'PA', 'TOM'),
('EC', 'AZ', 'PO', 'PON'),
('EC', 'AZ', 'PO', 'PUC'),
('EC', 'AZ', 'PO', 'RAF'),
('EC', 'AZ', 'SA', 'CHU'),
('EC', 'AZ', 'SA', 'FER'),
('EC', 'AZ', 'SG', 'BAR'),
('EC', 'AZ', 'SG', 'CUC'),
('EC', 'AZ', 'SG', 'GIM'),
('EC', 'AZ', 'SG', 'GUE'),
('EC', 'AZ', 'SG', 'JOS'),
('EC', 'AZ', 'SG', 'LUD'),
('EC', 'AZ', 'SG', 'SIG'),
('EC', 'AZ', 'SI', 'CAL'),
('EC', 'AZ', 'SI', 'CAR'),
('EC', 'AZ', 'SI', 'ISA'),
('EC', 'AZ', 'SI', 'ZHA'),
('EC', 'AZ', 'SO', 'AMA'),
('EC', 'AZ', 'SO', 'PAL'),
('EC', 'AZ', 'SO', 'SEV');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_FIELD`
--

CREATE TABLE IF NOT EXISTS `ENTITY_FIELD` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `TABLE_ID` varchar(30) NOT NULL,
  `FIELD_ID` varchar(30) NOT NULL,
  `FIELD_ORDER` tinyint(4) NOT NULL,
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `DATA_SIZE` smallint(6) NOT NULL,
  `DATA_SCALE` tinyint(4) NOT NULL DEFAULT '0',
  `PRIMARY_KEY` tinyint(1) NOT NULL DEFAULT '0',
  `UNIQUE_KEY` tinyint(1) NOT NULL DEFAULT '0',
  `NULLABLE` tinyint(1) NOT NULL DEFAULT '0',
  `DEFAULT_VALUE` varchar(30) DEFAULT NULL,
  `SEQUENTIAL_ID` varchar(30) DEFAULT NULL,
  `MINIMUM_VALUE` varchar(30) DEFAULT NULL,
  `MAXIMUM_VALUE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`TABLE_ID`,`FIELD_ID`),
  KEY `ENTITY_FIELD_DATA_TYPE_FK` (`DATA_TYPE_ID`),
  KEY `ENTITY_FIELD_ID_FK` (`TABLE_ID`,`FIELD_ID`),
  KEY `ENTITY_FIELD_SEQUENTIAL_ID_FK` (`SEQUENTIAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_FIELD`
--

INSERT INTO `ENTITY_FIELD` (`COMPANY_ID`, `TABLE_ID`, `FIELD_ID`, `FIELD_ORDER`, `DATA_TYPE_ID`, `DATA_SIZE`, `DATA_SCALE`, `PRIMARY_KEY`, `UNIQUE_KEY`, `NULLABLE`, `DEFAULT_VALUE`, `SEQUENTIAL_ID`, `MINIMUM_VALUE`, `MAXIMUM_VALUE`, `DESCRIPTION`) VALUES
('ALL', 'ACCOUNT_STATUS', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'ACCOUNT_STATUS', 'STATUS_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Status'),
('ALL', 'ADDRESS_TYPE', 'ADDRESS_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Address type Id'),
('ALL', 'ADDRESS_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of address type'),
('ALL', 'CHANNEL', 'CHANNEL_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Channel Id'),
('ALL', 'CHANNEL', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name'),
('ALL', 'CITY', 'CITY_ID', 3, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'CITY', 'COUNTRY_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'CITY', 'NAME', 4, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of city'),
('ALL', 'CITY', 'PROVINCE_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'CIVIL_STATUS', 'CIVIL_STATUS_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Civil type status Id'),
('ALL', 'CIVIL_STATUS', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of civil status'),
('ALL', 'COMPANY', 'COMPANY_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Company Id'),
('ALL', 'COMPANY', 'DATAFILE_ID', 3, 'Long', 10, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Document Id'),
('ALL', 'COMPANY', 'ENABLE', 6, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Enable'),
('ALL', 'COMPANY', 'LICENSE_DATE', 5, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'License date of company'),
('ALL', 'COMPANY', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of company'),
('ALL', 'COMPANY', 'UPGRADE_NUMBER', 4, 'BigDecimal', 4, 2, 0, 0, 1, NULL, NULL, NULL, NULL, 'Upgrade number of company'),
('ALL', 'COMPONENT', 'CLASS_NAME', 4, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Class name'),
('ALL', 'COMPONENT', 'COMPONENT_ID', 1, 'String', 150, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Component Id'),
('ALL', 'COMPONENT', 'DESCRIPTION', 6, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'COMPONENT', 'METHOD_NAME', 5, 'String', 100, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Method name'),
('ALL', 'COMPONENT', 'SUBSYSTEM_ID', 3, 'String', 2, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'COMPONENT', 'TYPE_ID', 2, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Component type Id'),
('ALL', 'COMPONENT_TYPE', 'COMPONENT_TYPE_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Component type Id'),
('ALL', 'COMPONENT_TYPE', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Component type description'),
('ALL', 'COUNTRY', 'AREA_CODE', 3, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Area code'),
('ALL', 'COUNTRY', 'COUNTRY_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'COUNTRY', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of country'),
('ALL', 'CURRENCY', 'CURRENCY_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Currency'),
('ALL', 'CURRENCY', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'CURRENCY', 'INITIALS', 3, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Initials'),
('ALL', 'DATABASE_TYPE', 'DATABASE_ID', 2, 'String', 30, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Database Id'),
('ALL', 'DATABASE_TYPE', 'DATABASE_TYPE', 4, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Data type of database'),
('ALL', 'DATABASE_TYPE', 'DATA_SIZE', 3, 'Integer', 5, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Data size of field'),
('ALL', 'DATABASE_TYPE', 'DATA_TYPE_ID', 1, 'String', 30, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Data type Id'),
('ALL', 'DATAFILE', 'BINARY_BYTES', 4, 'Long', 10, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Binary bytes'),
('ALL', 'DATAFILE', 'BINARY_OBJECT', 5, 'Blob', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Binary object'),
('ALL', 'DATAFILE', 'BINARY_PATH', 3, 'String', 200, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Binary path'),
('ALL', 'DATAFILE', 'CHARACTER_DATA', 6, 'Clob', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Character data'),
('ALL', 'DATAFILE', 'DATAFILE_ID', 1, 'Long', 10, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Datafile Id'),
('ALL', 'DATAFILE', 'DATAFILE_TYPE_ID', 2, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Datafile type Id'),
('ALL', 'DATAFILE_TYPE', 'DATAFILE_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Datafile type Id'),
('ALL', 'DATAFILE_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of datafile type'),
('ALL', 'DATA_TYPE', 'DATA_TYPE_ID', 1, 'String', 30, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Data type Id'),
('ALL', 'DATA_TYPE', 'DESCRIPTION', 2, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of data type'),
('ALL', 'DISTRICT', 'CITY_ID', 3, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'DISTRICT', 'COUNTRY_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'DISTRICT', 'DISTRICT_ID', 4, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'DISTRICT', 'NAME', 5, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of district'),
('ALL', 'DISTRICT', 'PROVINCE_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'ENTITY_FIELD', 'DATA_SCALE', 6, 'Integer', 3, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Data scale of field'),
('ALL', 'ENTITY_FIELD', 'DATA_SIZE', 5, 'Integer', 5, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Data size of field'),
('ALL', 'ENTITY_FIELD', 'DATA_TYPE_ID', 4, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Data type of field'),
('ALL', 'ENTITY_FIELD', 'DEFAULT_VALUE', 10, 'String', 30, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Default value of field'),
('ALL', 'ENTITY_FIELD', 'DESCRIPTION', 14, 'String', 100, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Description of field'),
('ALL', 'ENTITY_FIELD', 'FIELD_ID', 2, 'String', 30, 0, 1, 1, 0, NULL, NULL, NULL, NULL, 'Field Id'),
('ALL', 'ENTITY_FIELD', 'FIELD_ORDER', 3, 'Integer', 3, 0, 0, 1, 0, NULL, NULL, NULL, NULL, 'Field order'),
('ALL', 'ENTITY_FIELD', 'MAXIMUM_VALUE', 13, 'String', 30, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Maximum value of field'),
('ALL', 'ENTITY_FIELD', 'MINIMUM_VALUE', 12, 'String', 30, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Minimum value of field'),
('ALL', 'ENTITY_FIELD', 'NULLABLE', 9, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Nullable'),
('ALL', 'ENTITY_FIELD', 'PRIMARY_KEY', 7, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Primary key'),
('ALL', 'ENTITY_FIELD', 'SEQUENTIAL_ID', 11, 'String', 30, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Sequential Id of field'),
('ALL', 'ENTITY_FIELD', 'TABLE_ID', 1, 'String', 30, 0, 1, 1, 0, NULL, NULL, NULL, NULL, 'Table Id'),
('ALL', 'ENTITY_FIELD', 'UNIQUE_KEY', 8, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Unique key'),
('ALL', 'ENTITY_RELATIONSHIP', 'FIELD_FROM', 4, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Field from'),
('ALL', 'ENTITY_RELATIONSHIP', 'FIELD_TO', 6, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Filed to'),
('ALL', 'ENTITY_RELATIONSHIP', 'RELATIONSHIP_ID', 1, 'String', 30, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Relationship order'),
('ALL', 'ENTITY_RELATIONSHIP', 'RELATIONSHIP_ORDER', 2, 'Integer', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Field order'),
('ALL', 'ENTITY_RELATIONSHIP', 'TABLE_FROM', 3, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Table from'),
('ALL', 'ENTITY_RELATIONSHIP', 'TABLE_TO', 5, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Table to'),
('ALL', 'ENTITY_TABLE', 'CACHE_MEMORY', 9, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Cache memory of entity'),
('ALL', 'ENTITY_TABLE', 'DESCRIPTION', 10, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description of entity'),
('ALL', 'ENTITY_TABLE', 'ENUMERATED_TYPES', 8, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Enumerated type of entity'),
('ALL', 'ENTITY_TABLE', 'HAS_TABLE_ID', 2, 'Boolean', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Order of fields'),
('ALL', 'ENTITY_TABLE', 'HISTORICAL_DATA', 6, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Historical data of entity'),
('ALL', 'ENTITY_TABLE', 'MULTI_COMPANY', 4, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Multi company of entity'),
('ALL', 'ENTITY_TABLE', 'MULTI_LANGUAGE', 5, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Multi language of entity'),
('ALL', 'ENTITY_TABLE', 'OPTIMISTIC_LOCKING', 7, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Optimistic locking of entity'),
('ALL', 'ENTITY_TABLE', 'PACKAGE_NAME', 3, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Package name of entity'),
('ALL', 'ENTITY_TABLE', 'TABLE_ID', 1, 'String', 30, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Table Id'),
('ALL', 'FREQUENCY', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'FREQUENCY', 'FREQUENCY_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Frecuency'),
('ALL', 'FUNDS_DESTINATION', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'FUNDS_DESTINATION', 'FUNDS_DESTINATION_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Destination of funds'),
('ALL', 'GENDER_TYPE', 'GENDER_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Gender type Id'),
('ALL', 'GENDER_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of gender'),
('ALL', 'GEOGRAPHIC_ZONE', 'COORDINATE_TYPE', 3, 'String', 20, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Type of coordinate: Point, Route, Polygon'),
('ALL', 'GEOGRAPHIC_ZONE', 'DESCRIPTION', 2, 'String', 100, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Description of the zone'),
('ALL', 'GEOGRAPHIC_ZONE', 'GEOGRAPHIC_ZONE_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, 'GEOZONE', NULL, NULL, 'Geographic zone id'),
('ALL', 'GEOGRAPHIC_ZONE', 'P11', 4, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Point of coordinate 1,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P12', 5, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Point of coordinate 1,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P21', 6, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 2,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P22', 7, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 2,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P31', 8, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 3,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P32', 9, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 3,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P41', 10, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 4,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P42', 11, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Point of coordinate 4,2'),
('ALL', 'HOST', 'ADDRESS', 2, 'String', 60, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Address'),
('ALL', 'HOST', 'CHANNEL_ID', 3, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Channel Id'),
('ALL', 'HOST', 'HOST_ID', 1, 'String', 40, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Host Id'),
('ALL', 'HOST', 'TIME_ZONE', 4, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Time zone'),
('ALL', 'IDENTIFICATION_TYPE', 'IDENTIFICATION_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Identification type Id'),
('ALL', 'IDENTIFICATION_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of identification type'),
('ALL', 'LANGUAGE', 'LANGUAGE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Language Id'),
('ALL', 'LANGUAGE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of language'),
('ALL', 'MICRO_ACCOUNT', 'ACCOUNT_ID', 1, 'String', 25, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Account number and id'),
('ALL', 'MICRO_ACCOUNT', 'AMOUNT', 11, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Amount'),
('ALL', 'MICRO_ACCOUNT', 'ASSESSOR', 4, 'String', 20, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'MICRO_ACCOUNT', 'CLIENT_NAME', 3, 'String', 120, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Client''s name'),
('ALL', 'MICRO_ACCOUNT', 'GROUP_CLIENT_ID', 6, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Group'),
('ALL', 'MICRO_ACCOUNT', 'NUMBER_QUOTAS', 14, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Number of fees'),
('ALL', 'MICRO_ACCOUNT', 'NUMBER_RENEWAL', 9, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Number of renewal'),
('ALL', 'MICRO_ACCOUNT', 'PARTNER_CLIENT_ID', 5, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Client'),
('ALL', 'MICRO_ACCOUNT', 'PAYMENT_FREQUENCY_ID', 15, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Frequency of payment'),
('ALL', 'MICRO_ACCOUNT', 'PREVIOUS_ACCOUNT', 10, 'String', 25, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Renewed account'),
('ALL', 'MICRO_ACCOUNT', 'PRODUCT_ID', 7, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'MICRO_ACCOUNT', 'QUOTA_TYPE_ID', 13, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Quota type id'),
('ALL', 'MICRO_ACCOUNT', 'SOLICITUDE_ID', 2, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Solicitude Id'),
('ALL', 'MICRO_ACCOUNT', 'STATUS_ID', 8, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Account status id'),
('ALL', 'MICRO_ACCOUNT', 'TERM', 12, 'Long', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Term'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'ACCOUNT_ID', 1, 'String', 25, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Account number and id'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'CAPITAL', 8, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Capital amount'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'CHARGE', 10, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Charge amount'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'EXPIRATION_DATE', 5, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Expiration date'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'FROM_DATE', 4, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Initial date for term'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'INTEREST', 9, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Interest amount'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'PAYMENT_DATE', 6, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Payment date'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'PROVISION_DAYS', 3, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Number of provisioned days'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'REDUCED_CAPITAL', 7, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Reduced capital'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 'SUBACCOUNT', 2, 'Integer', 0, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Quota number'),
('ALL', 'MODULE', 'MODULE_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'MODULE', 'NAME', 3, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of module'),
('ALL', 'MODULE', 'SUBSYSTEM_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'OPERATIVE_CONDITION', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'OPERATIVE_CONDITION', 'OPERATIVE_CONDITION_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Operative condition'),
('ALL', 'PARAMETER', 'DATA_TYPE_ID', 3, 'String', 30, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Data type of parameter'),
('ALL', 'PARAMETER', 'DESCRIPTION', 5, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description of parameter'),
('ALL', 'PARAMETER', 'PARAMETER_ID', 1, 'String', 40, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Parameter Id'),
('ALL', 'PARAMETER', 'PARAMETER_VALUE', 4, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Value of parameter'),
('ALL', 'PARAMETER', 'SUBSYSTEM_ID', 2, 'String', 2, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PARTNER', 'ACTIVITY', 3, 'String', 300, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Activity'),
('ALL', 'PARTNER', 'FREQUENCY_ID', 5, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Meeting Frequency id'),
('ALL', 'PARTNER', 'MEETING_DAY', 6, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Meeting day'),
('ALL', 'PARTNER', 'PARTNER_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, 'PARTNER', NULL, NULL, 'Person id'),
('ALL', 'PARTNER', 'PERSON_ID', 2, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Person id'),
('ALL', 'PARTNER', 'USER_ID', 4, 'String', 20, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'PARTNER_GROUP', 'ACTIVITY', 4, 'String', 300, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Activity'),
('ALL', 'PARTNER_GROUP', 'CREATION_DATE', 3, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Creation date'),
('ALL', 'PARTNER_GROUP', 'FREQUENCY_ID', 6, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Meeting Frequency id'),
('ALL', 'PARTNER_GROUP', 'GROUP_DESCRIPTION', 2, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Group description'),
('ALL', 'PARTNER_GROUP', 'MEETING_DAY', 7, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Meeting day'),
('ALL', 'PARTNER_GROUP', 'PARTNER_GROUP_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, 'PARTNERGRP', NULL, NULL, 'Partner group id'),
('ALL', 'PARTNER_GROUP', 'USER_ID', 5, 'String', 20, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'PARTNER_GROUP_MEMBER', 'OBSERVATIONS', 5, 'String', 200, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Observations'),
('ALL', 'PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_ID', 2, 'Integer', 0, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Partner group id'),
('ALL', 'PARTNER_GROUP_MEMBER', 'PERSON_ID', 3, 'Integer', 0, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Person id'),
('ALL', 'PARTNER_GROUP_MEMBER', 'RESPONSABILITY_ID', 4, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Responsability id'),
('ALL', 'PERSON', 'CITY_ID', 11, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'PERSON', 'CIVIL_STATUS_ID', 9, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Civil type status Id'),
('ALL', 'PERSON', 'COUNTRY_ID', 10, 'String', 2, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PERSON', 'DATE_OF_BIRTH', 7, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Date of birth'),
('ALL', 'PERSON', 'DISTRICT_ID', 13, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'PERSON', 'GENDER_TYPE_ID', 8, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Gender type Id'),
('ALL', 'PERSON', 'IDENTIFICATION_NUMBER', 6, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Identification number'),
('ALL', 'PERSON', 'IDENTIFICATION_TYPE_ID', 5, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Identification type Id'),
('ALL', 'PERSON', 'LAST_NAME', 3, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Lastname of person'),
('ALL', 'PERSON', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of person'),
('ALL', 'PERSON', 'PERSON_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, 'PERSON', NULL, NULL, 'Person Id'),
('ALL', 'PERSON', 'PROFESSION_TYPE_ID', 14, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Profession type Id'),
('ALL', 'PERSON', 'PROVINCE_ID', 12, 'String', 2, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'PERSON', 'SECOND_LAST_NAME', 4, 'String', 40, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Second lastname of person'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_DESCRIPTION', 4, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'First street'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_SEQUENCE', 2, 'Integer', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Sequence of person address'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_TYPE_ID', 3, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Address type Id'),
('ALL', 'PERSON_ADDRESS', 'CITY_ID', 6, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'PERSON_ADDRESS', 'COUNTRY_ID', 5, 'String', 2, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PERSON_ADDRESS', 'DISTRICT_ID', 8, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'PERSON_ADDRESS', 'PERSON_ID', 1, 'Long', 10, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'PERSON_ADDRESS', 'PROVINCE_ID', 7, 'String', 2, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'PERSON_PHONE', 'AREA_CODE', 4, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Area code'),
('ALL', 'PERSON_PHONE', 'PERSON_ID', 1, 'Long', 10, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'PERSON_PHONE', 'PHONE_NUMBER', 5, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Phone number'),
('ALL', 'PERSON_PHONE', 'PHONE_SEQUENCE', 2, 'Integer', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Sequence of person phone'),
('ALL', 'PERSON_PHONE', 'PHONE_TYPE_ID', 3, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Phone type Id'),
('ALL', 'PERSON_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of person type'),
('ALL', 'PERSON_TYPE', 'PERSON_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Person type Id'),
('ALL', 'PHONE_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of phone'),
('ALL', 'PHONE_TYPE', 'PHONE_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Phone type Id'),
('ALL', 'PROCESS', 'DATAFILE_ID', 8, 'Long', 10, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Datafile Id'),
('ALL', 'PROCESS', 'ENABLE', 5, 'Boolean', 0, 0, 0, 0, 0, '1', NULL, NULL, NULL, 'Enable'),
('ALL', 'PROCESS', 'MENU', 6, 'Boolean', 0, 0, 0, 0, 0, '1', NULL, NULL, NULL, 'Show in app  menu'),
('ALL', 'PROCESS', 'MODULE_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'PROCESS', 'NAME', 4, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of process'),
('ALL', 'PROCESS', 'PROCESS_ID', 3, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'PROCESS', 'SUBSYSTEM_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PROCESS', 'URL', 7, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'URL'),
('ALL', 'PROCESS_COMPONENT', 'AUTHORIZE', 9, 'Boolean', 0, 0, 0, 0, 1, '0', NULL, NULL, NULL, 'Authorize'),
('ALL', 'PROCESS_COMPONENT', 'COMPONENT_ID', 6, 'String', 150, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Component Id'),
('ALL', 'PROCESS_COMPONENT', 'ENABLE', 8, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Enable'),
('ALL', 'PROCESS_COMPONENT', 'MODULE_ID', 3, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'PROCESS_COMPONENT', 'PROCESS_ID', 4, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'PROCESS_COMPONENT', 'PROCESS_SEQUENCE', 5, 'Integer', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Process sequence'),
('ALL', 'PROCESS_COMPONENT', 'SUBSYSTEM_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PROCESS_COMPONENT', 'TYPE_ID', 7, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Component type Id'),
('ALL', 'PRODUCT_ASESSOR', 'OBSERVATIONS', 3, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Observations'),
('ALL', 'PRODUCT_ASESSOR', 'PRODUCT_ID', 2, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'PRODUCT_ASESSOR', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'PRODUCT_MICROCREDIT', 'CURRENCY_ID', 3, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Currency id'),
('ALL', 'PRODUCT_MICROCREDIT', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description of product'),
('ALL', 'PRODUCT_MICROCREDIT', 'MAX_AMOUNT', 5, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Maximun amount'),
('ALL', 'PRODUCT_MICROCREDIT', 'MAX_PERIOD', 7, 'Long', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Maximun period'),
('ALL', 'PRODUCT_MICROCREDIT', 'MIN_AMOUNT', 4, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Minimun amount'),
('ALL', 'PRODUCT_MICROCREDIT', 'MIN_PERIOD', 6, 'Long', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Minimun period'),
('ALL', 'PRODUCT_MICROCREDIT', 'PRODUCT_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'PRODUCT_MICROCREDIT', 'RATE', 8, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Rate'),
('ALL', 'PROFESSION_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of profession type'),
('ALL', 'PROFESSION_TYPE', 'PROFESSION_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Profession type Id'),
('ALL', 'PROFILE', 'DESCRIPTION', 3, 'String', 150, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Description of profile'),
('ALL', 'PROFILE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of profile'),
('ALL', 'PROFILE', 'PROFILE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'PROVINCE', 'COUNTRY_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PROVINCE', 'NAME', 3, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of province'),
('ALL', 'PROVINCE', 'PROVINCE_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'QUOTA_TYPE', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'QUOTA_TYPE', 'QUOTA_TYPE_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Fee type'),
('ALL', 'RECOMMENDATION', 'CREDIT_HISTORY', 6, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Comments about credit history'),
('ALL', 'RECOMMENDATION', 'DOCUMENTS', 2, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Comments about documents'),
('ALL', 'RECOMMENDATION', 'ECONOMIC_UNIT', 3, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Comments about economic unit'),
('ALL', 'RECOMMENDATION', 'FAMILY_UNIT', 4, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Comments about family unit'),
('ALL', 'RECOMMENDATION', 'PAYMENT_MORALE', 5, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Comments about payment morale'),
('ALL', 'RECOMMENDATION', 'PROPOSAL', 7, 'String', 25, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Proposal'),
('ALL', 'RECOMMENDATION', 'SOLICITUDE_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Solicitude Id'),
('ALL', 'RESPONSABILITY', 'DESCRIPTION', 3, 'String', 60, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'RESPONSABILITY', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name'),
('ALL', 'RESPONSABILITY', 'RESPONSABILITY_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Responsability id'),
('ALL', 'RESPONSE', 'DESCRIPTION', 2, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description of response'),
('ALL', 'RESPONSE', 'RESPONSE_ID', 1, 'String', 8, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Response Id'),
('ALL', 'ROLE', 'DAY_ID', 5, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Day Id'),
('ALL', 'ROLE', 'EDITABLE', 8, 'Boolean', 0, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Editable'),
('ALL', 'ROLE', 'HOUR_FROM', 6, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Hour From'),
('ALL', 'ROLE', 'HOUR_TO', 7, 'String', 4, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Hour To'),
('ALL', 'ROLE', 'MODULE_ID', 3, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'ROLE', 'PROCESS_ID', 4, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'ROLE', 'PROFILE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'ROLE', 'SUBSYSTEM_ID', 2, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'SEQUENTIAL', 'SEQUENTIAL_ID', 1, 'String', 40, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Sequential Id'),
('ALL', 'SEQUENTIAL', 'SEQUENTIAL_VALUE', 2, 'Long', 10, 0, 0, 0, 0, '0', NULL, NULL, NULL, 'Value of sequential'),
('ALL', 'SOLICITUDE', 'ACCOUNT', 2, 'String', 25, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Generated account'),
('ALL', 'SOLICITUDE', 'AMOUNT', 16, 'BigDecimal', 19, 6, 0, 0, 0, NULL, NULL, NULL, NULL, 'Amount'),
('ALL', 'SOLICITUDE', 'APPROVAL_DATE', 7, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Approval date'),
('ALL', 'SOLICITUDE', 'ASSESSOR', 3, 'String', 20, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'SOLICITUDE', 'DESTINATION_DESCRIPTION', 22, 'String', 500, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description of destination'),
('ALL', 'SOLICITUDE', 'DISBURSEMENT_DATE', 8, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Disbursement date'),
('ALL', 'SOLICITUDE', 'EXPIRATION_DATE', 10, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Expiration date'),
('ALL', 'SOLICITUDE', 'FUNDS_DESTINATION_ID', 21, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Destination of funds'),
('ALL', 'SOLICITUDE', 'GROUP_CLIENT_ID', 5, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Group'),
('ALL', 'SOLICITUDE', 'INITIAL_PAY_DATE', 11, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Date for the first pay'),
('ALL', 'SOLICITUDE', 'INSTRUMENTATION_DATE', 9, 'Date', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Instrumentation date'),
('ALL', 'SOLICITUDE', 'NUMBER_QUOTAS', 19, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Number of fees'),
('ALL', 'SOLICITUDE', 'NUMBER_RENEWAL', 15, 'Integer', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Number of renewal'),
('ALL', 'SOLICITUDE', 'OPERATIVE_CONDITION_ID', 14, 'String', 3, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Operative condition'),
('ALL', 'SOLICITUDE', 'PARTNER_CLIENT_ID', 4, 'Integer', 0, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Client'),
('ALL', 'SOLICITUDE', 'PAYMENT_FREQUENCY_ID', 20, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Frequency of payment'),
('ALL', 'SOLICITUDE', 'PRODUCT_ID', 12, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'SOLICITUDE', 'QUOTA_TYPE_ID', 18, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Quota type id'),
('ALL', 'SOLICITUDE', 'SOLICITUDE_DATE', 6, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Solicitud date'),
('ALL', 'SOLICITUDE', 'SOLICITUDE_ID', 1, 'Integer', 0, 0, 1, 0, 0, NULL, 'SOLICITUDE', NULL, NULL, 'Name'),
('ALL', 'SOLICITUDE', 'STATUS_ID', 13, 'String', 3, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Solicitude status id'),
('ALL', 'SOLICITUDE', 'TERM', 17, 'Long', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Term'),
('ALL', 'SOLICITUDE_STATUS', 'DESCRIPTION', 2, 'String', 50, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'SOLICITUDE_STATUS', 'STATUS_ID', 1, 'String', 3, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Status'),
('ALL', 'SUBSYSTEM', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of subsystem'),
('ALL', 'SUBSYSTEM', 'SUBSYSTEM_ID', 1, 'String', 2, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'USER_ACCESS', 'ANSWER', 5, 'String', 100, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Answer'),
('ALL', 'USER_ACCESS', 'LAST_CHANGE', 3, 'Date', 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Last change'),
('ALL', 'USER_ACCESS', 'QUESTION', 4, 'String', 100, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Question'),
('ALL', 'USER_ACCESS', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_ACCESS', 'USER_KEY', 2, 'String', 300, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'User Key'),
('ALL', 'USER_ACCOUNT', 'EMAIL', 6, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Email'),
('ALL', 'USER_ACCOUNT', 'LANGUAGE_ID', 5, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Language Id'),
('ALL', 'USER_ACCOUNT', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of user'),
('ALL', 'USER_ACCOUNT', 'PERSON_ID', 7, 'Long', 10, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'USER_ACCOUNT', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_ACCOUNT', 'USER_STATUS_ID', 4, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'User status Id'),
('ALL', 'USER_ACCOUNT', 'USER_TYPE_ID', 3, 'String', 4, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'User type Id'),
('ALL', 'USER_PROFILE', 'PROFILE_ID', 2, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'USER_PROFILE', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_SESSION', 'HOST_ID', 2, 'String', 40, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Host Id'),
('ALL', 'USER_SESSION', 'SESSION_ID', 3, 'String', 100, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Token'),
('ALL', 'USER_SESSION', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_STATUS', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of user status'),
('ALL', 'USER_STATUS', 'USER_STATUS_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User status Id'),
('ALL', 'USER_TYPE', 'NAME', 2, 'String', 40, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 'Name of user type'),
('ALL', 'USER_TYPE', 'USER_TYPE_ID', 1, 'String', 4, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User type Id'),
('ALL', 'ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID', 2, 'Integer', 0, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'Geographic zone id'),
('ALL', 'ZONE_ASESSOR', 'OBSERVATIONS', 3, 'String', 50, 0, 0, 0, 1, NULL, NULL, NULL, NULL, 'Observations'),
('ALL', 'ZONE_ASESSOR', 'USER_ID', 1, 'String', 20, 0, 1, 0, 0, NULL, NULL, NULL, NULL, 'User Id');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_FIELD_ID`
--

CREATE TABLE IF NOT EXISTS `ENTITY_FIELD_ID` (
  `TABLE_ID` varchar(30) NOT NULL,
  `FIELD_ID` varchar(30) NOT NULL,
  PRIMARY KEY (`TABLE_ID`,`FIELD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_FIELD_ID`
--

INSERT INTO `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`) VALUES
('ACCOUNT_STATUS', 'COMPANY_ID'),
('ACCOUNT_STATUS', 'DESCRIPTION'),
('ACCOUNT_STATUS', 'LANGUAGE_ID'),
('ACCOUNT_STATUS', 'STATUS_ID'),
('ACCOUNT_STATUS_ID', 'STATUS_ID'),
('ADDRESS_TYPE', 'ADDRESS_TYPE_ID'),
('ADDRESS_TYPE', 'COMPANY_ID'),
('ADDRESS_TYPE', 'LANGUAGE_ID'),
('ADDRESS_TYPE', 'NAME'),
('ADDRESS_TYPE_ID', 'ADDRESS_TYPE_ID'),
('CHANNEL', 'CHANNEL_ID'),
('CHANNEL', 'NAME'),
('CITY', 'CITY_ID'),
('CITY', 'COMPANY_ID'),
('CITY', 'COUNTRY_ID'),
('CITY', 'LANGUAGE_ID'),
('CITY', 'NAME'),
('CITY', 'PROVINCE_ID'),
('CITY_ID', 'CITY_ID'),
('CITY_ID', 'COUNTRY_ID'),
('CITY_ID', 'PROVINCE_ID'),
('CIVIL_STATUS', 'CIVIL_STATUS_ID'),
('CIVIL_STATUS', 'COMPANY_ID'),
('CIVIL_STATUS', 'LANGUAGE_ID'),
('CIVIL_STATUS', 'NAME'),
('CIVIL_STATUS_ID', 'CIVIL_STATUS_ID'),
('COMPANY', 'COMPANY_ID'),
('COMPANY', 'DATAFILE_ID'),
('COMPANY', 'ENABLE'),
('COMPANY', 'LICENSE_DATE'),
('COMPANY', 'NAME'),
('COMPANY', 'UPGRADE_NUMBER'),
('COMPONENT', 'CLASS_NAME'),
('COMPONENT', 'COMPANY_ID'),
('COMPONENT', 'COMPONENT_ID'),
('COMPONENT', 'DESCRIPTION'),
('COMPONENT', 'METHOD_NAME'),
('COMPONENT', 'SUBSYSTEM_ID'),
('COMPONENT', 'TYPE_ID'),
('COMPONENT_ID', 'COMPONENT_ID'),
('COMPONENT_ID', 'TYPE_ID'),
('COMPONENT_TYPE', 'COMPONENT_TYPE_ID'),
('COMPONENT_TYPE', 'DESCRIPTION'),
('COUNTRY', 'AREA_CODE'),
('COUNTRY', 'COMPANY_ID'),
('COUNTRY', 'COUNTRY_ID'),
('COUNTRY', 'LANGUAGE_ID'),
('COUNTRY', 'NAME'),
('COUNTRY_ID', 'COUNTRY_ID'),
('CURRENCY', 'COMPANY_ID'),
('CURRENCY', 'CREATED'),
('CURRENCY', 'CURRENCY_ID'),
('CURRENCY', 'DESCRIPTION'),
('CURRENCY', 'EXPIRED'),
('CURRENCY', 'INITIALS'),
('CURRENCY', 'LANGUAGE_ID'),
('CURRENCY_ID', 'CURRENCY_ID'),
('DATABASE_TYPE', 'DATABASE_ID'),
('DATABASE_TYPE', 'DATABASE_TYPE'),
('DATABASE_TYPE', 'DATA_SIZE'),
('DATABASE_TYPE', 'DATA_TYPE_ID'),
('DATAFILE', 'BINARY_BYTES'),
('DATAFILE', 'BINARY_OBJECT'),
('DATAFILE', 'BINARY_PATH'),
('DATAFILE', 'CHARACTER_DATA'),
('DATAFILE', 'COMPANY_ID'),
('DATAFILE', 'CREATED'),
('DATAFILE', 'DATAFILE_ID'),
('DATAFILE', 'DATAFILE_TYPE_ID'),
('DATAFILE', 'EXPIRED'),
('DATAFILE_ID', 'DATAFILE_ID'),
('DATAFILE_TYPE', 'DATAFILE_TYPE_ID'),
('DATAFILE_TYPE', 'NAME'),
('DATA_TYPE', 'DATA_TYPE_ID'),
('DATA_TYPE', 'DESCRIPTION'),
('DISTRICT', 'CITY_ID'),
('DISTRICT', 'COMPANY_ID'),
('DISTRICT', 'COUNTRY_ID'),
('DISTRICT', 'DISTRICT_ID'),
('DISTRICT', 'LANGUAGE_ID'),
('DISTRICT', 'NAME'),
('DISTRICT', 'PROVINCE_ID'),
('DISTRICT_ID', 'CITY_ID'),
('DISTRICT_ID', 'COUNTRY_ID'),
('DISTRICT_ID', 'DISTRICT_ID'),
('DISTRICT_ID', 'PROVINCE_ID'),
('ENTITY_FIELD', 'COMPANY_ID'),
('ENTITY_FIELD', 'DATA_SCALE'),
('ENTITY_FIELD', 'DATA_SIZE'),
('ENTITY_FIELD', 'DATA_TYPE_ID'),
('ENTITY_FIELD', 'DEFAULT_VALUE'),
('ENTITY_FIELD', 'DESCRIPTION'),
('ENTITY_FIELD', 'FIELD_ID'),
('ENTITY_FIELD', 'FIELD_ORDER'),
('ENTITY_FIELD', 'MAXIMUM_VALUE'),
('ENTITY_FIELD', 'MINIMUM_VALUE'),
('ENTITY_FIELD', 'NULLABLE'),
('ENTITY_FIELD', 'PRIMARY_KEY'),
('ENTITY_FIELD', 'SEQUENTIAL_ID'),
('ENTITY_FIELD', 'TABLE_ID'),
('ENTITY_FIELD', 'UNIQUE_KEY'),
('ENTITY_FIELD_ID', 'FIELD_ID'),
('ENTITY_FIELD_ID', 'TABLE_ID'),
('ENTITY_RELATIONSHIP', 'COMPANY_ID'),
('ENTITY_RELATIONSHIP', 'FIELD_FROM'),
('ENTITY_RELATIONSHIP', 'FIELD_TO'),
('ENTITY_RELATIONSHIP', 'RELATIONSHIP_ID'),
('ENTITY_RELATIONSHIP', 'RELATIONSHIP_ORDER'),
('ENTITY_RELATIONSHIP', 'TABLE_FROM'),
('ENTITY_RELATIONSHIP', 'TABLE_TO'),
('ENTITY_TABLE', 'CACHE_MEMORY'),
('ENTITY_TABLE', 'COMPANY_ID'),
('ENTITY_TABLE', 'DESCRIPTION'),
('ENTITY_TABLE', 'ENUMERATED_TYPES'),
('ENTITY_TABLE', 'HAS_TABLE_ID'),
('ENTITY_TABLE', 'HISTORICAL_DATA'),
('ENTITY_TABLE', 'MULTI_COMPANY'),
('ENTITY_TABLE', 'MULTI_LANGUAGE'),
('ENTITY_TABLE', 'OPTIMISTIC_LOCKING'),
('ENTITY_TABLE', 'PACKAGE_NAME'),
('ENTITY_TABLE', 'TABLE_ID'),
('ENTITY_TABLE_ID', 'TABLE_ID'),
('FREQUENCY', 'COMPANY_ID'),
('FREQUENCY', 'DESCRIPTION'),
('FREQUENCY', 'FREQUENCY_ID'),
('FREQUENCY', 'LANGUAGE_ID'),
('FREQUENCY_ID', 'FREQUENCY_ID'),
('FUNDS_DESTINATION', 'COMPANY_ID'),
('FUNDS_DESTINATION', 'DESCRIPTION'),
('FUNDS_DESTINATION', 'FUNDS_DESTINATION_ID'),
('FUNDS_DESTINATION', 'LANGUAGE_ID'),
('FUNDS_DESTINATION_ID', 'FUNDS_DESTINATION_ID'),
('GENDER_TYPE', 'GENDER_TYPE_ID'),
('GENDER_TYPE', 'LANGUAGE_ID'),
('GENDER_TYPE', 'NAME'),
('GENDER_TYPE_ID', 'GENDER_TYPE_ID'),
('GEOGRAPHIC_ZONE', 'COMPANY_ID'),
('GEOGRAPHIC_ZONE', 'COORDINATE_TYPE'),
('GEOGRAPHIC_ZONE', 'DESCRIPTION'),
('GEOGRAPHIC_ZONE', 'GEOGRAPHIC_ZONE_ID'),
('GEOGRAPHIC_ZONE', 'LANGUAGE_ID'),
('GEOGRAPHIC_ZONE', 'P11'),
('GEOGRAPHIC_ZONE', 'P12'),
('GEOGRAPHIC_ZONE', 'P21'),
('GEOGRAPHIC_ZONE', 'P22'),
('GEOGRAPHIC_ZONE', 'P31'),
('GEOGRAPHIC_ZONE', 'P32'),
('GEOGRAPHIC_ZONE', 'P41'),
('GEOGRAPHIC_ZONE', 'P42'),
('GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID'),
('HOST', 'ADDRESS'),
('HOST', 'CHANNEL_ID'),
('HOST', 'COMPANY_ID'),
('HOST', 'CREATED'),
('HOST', 'EXPIRED'),
('HOST', 'HOST_ID'),
('HOST', 'TIME_ZONE'),
('HOST_ID', 'HOST_ID'),
('IDENTIFICATION_TYPE', 'COMPANY_ID'),
('IDENTIFICATION_TYPE', 'IDENTIFICATION_TYPE_ID'),
('IDENTIFICATION_TYPE', 'LANGUAGE_ID'),
('IDENTIFICATION_TYPE', 'NAME'),
('IDENTIFICATION_TYPE_ID', 'IDENTIFICATION_TYPE_ID'),
('LANGUAGE', 'LANGUAGE_ID'),
('LANGUAGE', 'NAME'),
('MICRO_ACCOUNT', 'ACCOUNT_ID'),
('MICRO_ACCOUNT', 'AMOUNT'),
('MICRO_ACCOUNT', 'ASSESSOR'),
('MICRO_ACCOUNT', 'CLIENT_NAME'),
('MICRO_ACCOUNT', 'COMPANY_ID'),
('MICRO_ACCOUNT', 'CREATED'),
('MICRO_ACCOUNT', 'EXPIRED'),
('MICRO_ACCOUNT', 'GROUP_CLIENT_ID'),
('MICRO_ACCOUNT', 'NUMBER_QUOTAS'),
('MICRO_ACCOUNT', 'NUMBER_RENEWAL'),
('MICRO_ACCOUNT', 'PARTNER_CLIENT_ID'),
('MICRO_ACCOUNT', 'PAYMENT_FREQUENCY_ID'),
('MICRO_ACCOUNT', 'PREVIOUS_ACCOUNT'),
('MICRO_ACCOUNT', 'PRODUCT_ID'),
('MICRO_ACCOUNT', 'QUOTA_TYPE_ID'),
('MICRO_ACCOUNT', 'SOLICITUDE_ID'),
('MICRO_ACCOUNT', 'STATUS_ID'),
('MICRO_ACCOUNT', 'TERM'),
('MICRO_ACCOUNT_ID', 'ACCOUNT_ID'),
('MICRO_ACCOUNT_QUOTA', 'ACCOUNT_ID'),
('MICRO_ACCOUNT_QUOTA', 'CAPITAL'),
('MICRO_ACCOUNT_QUOTA', 'CHARGE'),
('MICRO_ACCOUNT_QUOTA', 'COMPANY_ID'),
('MICRO_ACCOUNT_QUOTA', 'CREATED'),
('MICRO_ACCOUNT_QUOTA', 'EXPIRATION_DATE'),
('MICRO_ACCOUNT_QUOTA', 'EXPIRED'),
('MICRO_ACCOUNT_QUOTA', 'FROM_DATE'),
('MICRO_ACCOUNT_QUOTA', 'INTEREST'),
('MICRO_ACCOUNT_QUOTA', 'PAYMENT_DATE'),
('MICRO_ACCOUNT_QUOTA', 'PROVISION_DAYS'),
('MICRO_ACCOUNT_QUOTA', 'REDUCED_CAPITAL'),
('MICRO_ACCOUNT_QUOTA', 'SUBACCOUNT'),
('MICRO_ACCOUNT_QUOTA_ID', 'ACCOUNT_ID'),
('MICRO_ACCOUNT_QUOTA_ID', 'SUBACCOUNT'),
('MODULE', 'COMPANY_ID'),
('MODULE', 'LANGUAGE_ID'),
('MODULE', 'MODULE_ID'),
('MODULE', 'NAME'),
('MODULE', 'SUBSYSTEM_ID'),
('MODULE_ID', 'MODULE_ID'),
('MODULE_ID', 'SUBSYSTEM_ID'),
('OPERATIVE_CONDITION', 'COMPANY_ID'),
('OPERATIVE_CONDITION', 'DESCRIPTION'),
('OPERATIVE_CONDITION', 'LANGUAGE_ID'),
('OPERATIVE_CONDITION', 'OPERATIVE_CONDITION_ID'),
('OPERATIVE_CONDITION_ID', 'OPERATIVE_CONDITION_ID'),
('PARAMETER', 'COMPANY_ID'),
('PARAMETER', 'CREATED'),
('PARAMETER', 'DATA_TYPE_ID'),
('PARAMETER', 'DESCRIPTION'),
('PARAMETER', 'EXPIRED'),
('PARAMETER', 'PARAMETER_ID'),
('PARAMETER', 'PARAMETER_VALUE'),
('PARAMETER', 'SUBSYSTEM_ID'),
('PARAMETER_ID', 'PARAMETER_ID'),
('PARTNER', 'ACTIVITY'),
('PARTNER', 'COMPANY_ID'),
('PARTNER', 'CREATED'),
('PARTNER', 'EXPIRED'),
('PARTNER', 'FREQUENCY_ID'),
('PARTNER', 'LANGUAGE_ID'),
('PARTNER', 'MEETING_DAY'),
('PARTNER', 'PARTNER_ID'),
('PARTNER', 'PERSON_ID'),
('PARTNER', 'USER_ID'),
('PARTNER_GROUP', 'ACTIVITY'),
('PARTNER_GROUP', 'COMPANY_ID'),
('PARTNER_GROUP', 'CREATED'),
('PARTNER_GROUP', 'CREATION_DATE'),
('PARTNER_GROUP', 'EXPIRED'),
('PARTNER_GROUP', 'FREQUENCY_ID'),
('PARTNER_GROUP', 'GROUP_DESCRIPTION'),
('PARTNER_GROUP', 'LANGUAGE_ID'),
('PARTNER_GROUP', 'MEETING_DAY'),
('PARTNER_GROUP', 'PARTNER_GROUP_ID'),
('PARTNER_GROUP', 'USER_ID'),
('PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('PARTNER_GROUP_MEMBER', 'OBSERVATIONS'),
('PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_ID'),
('PARTNER_GROUP_MEMBER', 'PERSON_ID'),
('PARTNER_GROUP_MEMBER', 'RESPONSABILITY_ID'),
('PARTNER_ID', 'PARTNER_ID'),
('PERSON', 'CITY_ID'),
('PERSON', 'CIVIL_STATUS_ID'),
('PERSON', 'COMPANY_ID'),
('PERSON', 'COUNTRY_ID'),
('PERSON', 'CREATED'),
('PERSON', 'DATE_OF_BIRTH'),
('PERSON', 'DISTRICT_ID'),
('PERSON', 'EXPIRED'),
('PERSON', 'GENDER_TYPE_ID'),
('PERSON', 'IDENTIFICATION_NUMBER'),
('PERSON', 'IDENTIFICATION_TYPE_ID'),
('PERSON', 'LAST_NAME'),
('PERSON', 'NAME'),
('PERSON', 'PERSON_ID'),
('PERSON', 'PROFESSION_TYPE_ID'),
('PERSON', 'PROVINCE_ID'),
('PERSON', 'SECOND_LAST_NAME'),
('PERSON_ADDRESS', 'ADDRESS_DESCRIPTION'),
('PERSON_ADDRESS', 'ADDRESS_SEQUENCE'),
('PERSON_ADDRESS', 'ADDRESS_TYPE_ID'),
('PERSON_ADDRESS', 'CITY_ID'),
('PERSON_ADDRESS', 'COMPANY_ID'),
('PERSON_ADDRESS', 'COUNTRY_ID'),
('PERSON_ADDRESS', 'CREATED'),
('PERSON_ADDRESS', 'DISTRICT_ID'),
('PERSON_ADDRESS', 'EXPIRED'),
('PERSON_ADDRESS', 'PERSON_ID'),
('PERSON_ADDRESS', 'PROVINCE_ID'),
('PERSON_ID', 'PERSON_ID'),
('PERSON_PHONE', 'AREA_CODE'),
('PERSON_PHONE', 'COMPANY_ID'),
('PERSON_PHONE', 'CREATED'),
('PERSON_PHONE', 'EXPIRED'),
('PERSON_PHONE', 'PERSON_ID'),
('PERSON_PHONE', 'PHONE_NUMBER'),
('PERSON_PHONE', 'PHONE_SEQUENCE'),
('PERSON_PHONE', 'PHONE_TYPE_ID'),
('PERSON_TYPE', 'COMPANY_ID'),
('PERSON_TYPE', 'LANGUAGE_ID'),
('PERSON_TYPE', 'NAME'),
('PERSON_TYPE', 'PERSON_TYPE_ID'),
('PERSON_TYPE_ID', 'PERSON_TYPE_ID'),
('PHONE_TYPE', 'COMPANY_ID'),
('PHONE_TYPE', 'LANGUAGE_ID'),
('PHONE_TYPE', 'NAME'),
('PHONE_TYPE', 'PHONE_TYPE_ID'),
('PHONE_TYPE_ID', 'PHONE_TYPE_ID'),
('PROCESS', 'COMPANY_ID'),
('PROCESS', 'CREATED'),
('PROCESS', 'DATAFILE_ID'),
('PROCESS', 'ENABLE'),
('PROCESS', 'EXPIRED'),
('PROCESS', 'LANGUAGE_ID'),
('PROCESS', 'MENU'),
('PROCESS', 'MODULE_ID'),
('PROCESS', 'NAME'),
('PROCESS', 'PROCESS_ID'),
('PROCESS', 'SUBSYSTEM_ID'),
('PROCESS', 'URL'),
('PROCESS_COMPONENT', 'AUTHORIZE'),
('PROCESS_COMPONENT', 'COMPANY_ID'),
('PROCESS_COMPONENT', 'COMPONENT_ID'),
('PROCESS_COMPONENT', 'ENABLE'),
('PROCESS_COMPONENT', 'MODULE_ID'),
('PROCESS_COMPONENT', 'PROCESS_ID'),
('PROCESS_COMPONENT', 'PROCESS_SEQUENCE'),
('PROCESS_COMPONENT', 'SUBSYSTEM_ID'),
('PROCESS_COMPONENT', 'TYPE_ID'),
('PROCESS_ID', 'MODULE_ID'),
('PROCESS_ID', 'PROCESS_ID'),
('PROCESS_ID', 'SUBSYSTEM_ID'),
('PRODUCT_ASESSOR', 'COMPANY_ID'),
('PRODUCT_ASESSOR', 'LANGUAGE_ID'),
('PRODUCT_ASESSOR', 'OBSERVATIONS'),
('PRODUCT_ASESSOR', 'PRODUCT_ID'),
('PRODUCT_ASESSOR', 'USER_ID'),
('PRODUCT_ASESSOR_ID', 'PRODUCT_ID'),
('PRODUCT_ASESSOR_ID', 'USER_ID'),
('PRODUCT_MICROCREDIT', 'COMPANY_ID'),
('PRODUCT_MICROCREDIT', 'CREATED'),
('PRODUCT_MICROCREDIT', 'CURRENCY_ID'),
('PRODUCT_MICROCREDIT', 'DESCRIPTION'),
('PRODUCT_MICROCREDIT', 'EXPIRED'),
('PRODUCT_MICROCREDIT', 'LANGUAGE_ID'),
('PRODUCT_MICROCREDIT', 'MAX_AMOUNT'),
('PRODUCT_MICROCREDIT', 'MAX_PERIOD'),
('PRODUCT_MICROCREDIT', 'MIN_AMOUNT'),
('PRODUCT_MICROCREDIT', 'MIN_PERIOD'),
('PRODUCT_MICROCREDIT', 'PRODUCT_ID'),
('PRODUCT_MICROCREDIT', 'RATE'),
('PRODUCT_MICROCREDIT_ID', 'PRODUCT_ID'),
('PROFESSION_TYPE', 'COMPANY_ID'),
('PROFESSION_TYPE', 'LANGUAGE_ID'),
('PROFESSION_TYPE', 'NAME'),
('PROFESSION_TYPE', 'PROFESSION_TYPE_ID'),
('PROFESSION_TYPE_ID', 'PROFESSION_TYPE_ID'),
('PROFILE', 'COMPANY_ID'),
('PROFILE', 'DESCRIPTION'),
('PROFILE', 'LANGUAGE_ID'),
('PROFILE', 'NAME'),
('PROFILE', 'PROFILE_ID'),
('PROFILE_ID', 'PROFILE_ID'),
('PROVINCE', 'COMPANY_ID'),
('PROVINCE', 'COUNTRY_ID'),
('PROVINCE', 'LANGUAGE_ID'),
('PROVINCE', 'NAME'),
('PROVINCE', 'PROVINCE_ID'),
('PROVINCE_ID', 'COUNTRY_ID'),
('PROVINCE_ID', 'PROVINCE_ID'),
('QUOTA_TYPE', 'COMPANY_ID'),
('QUOTA_TYPE', 'DESCRIPTION'),
('QUOTA_TYPE', 'LANGUAGE_ID'),
('QUOTA_TYPE', 'QUOTA_TYPE_ID'),
('QUOTA_TYPE_ID', 'QUOTA_TYPE_ID'),
('RECOMMENDATION', 'COMPANY_ID'),
('RECOMMENDATION', 'CREATED'),
('RECOMMENDATION', 'CREDIT_HISTORY'),
('RECOMMENDATION', 'DOCUMENTS'),
('RECOMMENDATION', 'ECONOMIC_UNIT'),
('RECOMMENDATION', 'EXPIRED'),
('RECOMMENDATION', 'FAMILY_UNIT'),
('RECOMMENDATION', 'LANGUAGE_ID'),
('RECOMMENDATION', 'PAYMENT_MORALE'),
('RECOMMENDATION', 'PROPOSAL'),
('RECOMMENDATION', 'SOLICITUDE_ID'),
('RECOMMENDATION_ID', 'SOLICITUDE_ID'),
('RESPONSABILITY', 'COMPANY_ID'),
('RESPONSABILITY', 'CREATED'),
('RESPONSABILITY', 'DESCRIPTION'),
('RESPONSABILITY', 'EXPIRED'),
('RESPONSABILITY', 'LANGUAGE_ID'),
('RESPONSABILITY', 'NAME'),
('RESPONSABILITY', 'RESPONSABILITY_ID'),
('RESPONSABILITY_ID', 'RESPONSABILITY_ID'),
('RESPONSE', 'COMPANY_ID'),
('RESPONSE', 'DESCRIPTION'),
('RESPONSE', 'LANGUAGE_ID'),
('RESPONSE', 'RESPONSE_ID'),
('RESPONSE_ID', 'RESPONSE_ID'),
('ROLE', 'COMPANY_ID'),
('ROLE', 'CREATED'),
('ROLE', 'DAY_ID'),
('ROLE', 'EDITABLE'),
('ROLE', 'EXPIRED'),
('ROLE', 'HOUR_FROM'),
('ROLE', 'HOUR_TO'),
('ROLE', 'MODULE_ID'),
('ROLE', 'PROCESS_ID'),
('ROLE', 'PROFILE_ID'),
('ROLE', 'SUBSYSTEM_ID'),
('SEQUENTIAL', 'COMPANY_ID'),
('SEQUENTIAL', 'SEQUENTIAL_ID'),
('SEQUENTIAL', 'SEQUENTIAL_VALUE'),
('SEQUENTIAL_ID', 'SEQUENTIAL_ID'),
('SOLICITUDE', 'ACCOUNT'),
('SOLICITUDE', 'AMOUNT'),
('SOLICITUDE', 'APPROVAL_DATE'),
('SOLICITUDE', 'ASSESSOR'),
('SOLICITUDE', 'COMPANY_ID'),
('SOLICITUDE', 'CREATED'),
('SOLICITUDE', 'DESTINATION_DESCRIPTION'),
('SOLICITUDE', 'DISBURSEMENT_DATE'),
('SOLICITUDE', 'EXPIRATION_DATE'),
('SOLICITUDE', 'EXPIRED'),
('SOLICITUDE', 'FUNDS_DESTINATION_ID'),
('SOLICITUDE', 'GROUP_CLIENT_ID'),
('SOLICITUDE', 'INITIAL_PAY_DATE'),
('SOLICITUDE', 'INSTRUMENTATION_DATE'),
('SOLICITUDE', 'LANGUAGE_ID'),
('SOLICITUDE', 'NUMBER_QUOTAS'),
('SOLICITUDE', 'NUMBER_RENEWAL'),
('SOLICITUDE', 'OPERATIVE_CONDITION_ID'),
('SOLICITUDE', 'PARTNER_CLIENT_ID'),
('SOLICITUDE', 'PAYMENT_FREQUENCY_ID'),
('SOLICITUDE', 'PRODUCT_ID'),
('SOLICITUDE', 'QUOTA_TYPE_ID'),
('SOLICITUDE', 'SOLICITUDE_DATE'),
('SOLICITUDE', 'SOLICITUDE_ID'),
('SOLICITUDE', 'STATUS_ID'),
('SOLICITUDE', 'TERM'),
('SOLICITUDE_ID', 'SOLICITUDE_ID'),
('SOLICITUDE_STATUS', 'COMPANY_ID'),
('SOLICITUDE_STATUS', 'DESCRIPTION'),
('SOLICITUDE_STATUS', 'LANGUAGE_ID'),
('SOLICITUDE_STATUS', 'STATUS_ID'),
('SOLICITUDE_STATUS_ID', 'STATUS_ID'),
('SUBSYSTEM', 'COMPANY_ID'),
('SUBSYSTEM', 'LANGUAGE_ID'),
('SUBSYSTEM', 'NAME'),
('SUBSYSTEM', 'SUBSYSTEM_ID'),
('SUBSYSTEM_ID', 'SUBSYSTEM_ID'),
('USER_ACCESS', 'ANSWER'),
('USER_ACCESS', 'COMPANY_ID'),
('USER_ACCESS', 'CREATED'),
('USER_ACCESS', 'EXPIRED'),
('USER_ACCESS', 'LAST_CHANGE'),
('USER_ACCESS', 'QUESTION'),
('USER_ACCESS', 'USER_ID'),
('USER_ACCESS', 'USER_KEY'),
('USER_ACCOUNT', 'COMPANY_ID'),
('USER_ACCOUNT', 'CREATED'),
('USER_ACCOUNT', 'EMAIL'),
('USER_ACCOUNT', 'EXPIRED'),
('USER_ACCOUNT', 'LANGUAGE_ID'),
('USER_ACCOUNT', 'NAME'),
('USER_ACCOUNT', 'PERSON_ID'),
('USER_ACCOUNT', 'USER_ID'),
('USER_ACCOUNT', 'USER_STATUS_ID'),
('USER_ACCOUNT', 'USER_TYPE_ID'),
('USER_ACCOUNT_ID', 'USER_ID'),
('USER_PROFILE', 'COMPANY_ID'),
('USER_PROFILE', 'CREATED'),
('USER_PROFILE', 'EXPIRED'),
('USER_PROFILE', 'PROFILE_ID'),
('USER_PROFILE', 'USER_ID'),
('USER_SESSION', 'COMPANY_ID'),
('USER_SESSION', 'CREATED'),
('USER_SESSION', 'EXPIRED'),
('USER_SESSION', 'HOST_ID'),
('USER_SESSION', 'SESSION_ID'),
('USER_SESSION', 'USER_ID'),
('USER_STATUS', 'COMPANY_ID'),
('USER_STATUS', 'LANGUAGE_ID'),
('USER_STATUS', 'NAME'),
('USER_STATUS', 'USER_STATUS_ID'),
('USER_STATUS_ID', 'USER_STATUS_ID'),
('USER_TYPE', 'COMPANY_ID'),
('USER_TYPE', 'LANGUAGE_ID'),
('USER_TYPE', 'NAME'),
('USER_TYPE', 'USER_TYPE_ID'),
('USER_TYPE_ID', 'USER_TYPE_ID'),
('ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID'),
('ZONE_ASESSOR', 'OBSERVATIONS'),
('ZONE_ASESSOR', 'USER_ID');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_RELATIONSHIP`
--

CREATE TABLE IF NOT EXISTS `ENTITY_RELATIONSHIP` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `RELATIONSHIP_ID` varchar(30) NOT NULL,
  `RELATIONSHIP_ORDER` tinyint(4) NOT NULL,
  `TABLE_FROM` varchar(30) NOT NULL,
  `FIELD_FROM` varchar(30) NOT NULL,
  `TABLE_TO` varchar(30) NOT NULL,
  `FIELD_TO` varchar(30) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`RELATIONSHIP_ID`,`RELATIONSHIP_ORDER`),
  KEY `ENTITY_RELATIONSHIP_FROM_FK` (`TABLE_FROM`,`FIELD_FROM`),
  KEY `ENTITY_RELATIONSHIP_TO_FK` (`TABLE_TO`,`FIELD_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_RELATIONSHIP`
--

INSERT INTO `ENTITY_RELATIONSHIP` (`COMPANY_ID`, `RELATIONSHIP_ID`, `RELATIONSHIP_ORDER`, `TABLE_FROM`, `FIELD_FROM`, `TABLE_TO`, `FIELD_TO`) VALUES
('ALL', 'CITY_ID_PROVINCE_ID_FK', 1, 'CITY_ID', 'COUNTRY_ID', 'PROVINCE_ID', 'COUNTRY_ID'),
('ALL', 'CITY_ID_PROVINCE_ID_FK', 2, 'CITY_ID', 'PROVINCE_ID', 'PROVINCE_ID', 'PROVINCE_ID'),
('ALL', 'COMPONENT_COM_TYPE_ID_FK', 1, 'COMPONENT', 'TYPE_ID', 'COMPONENT_TYPE', 'COMPONENT_TYPE_ID'),
('ALL', 'COMPONENT_SUBSYSTEM_ID_FK', 1, 'COMPONENT', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID'),
('ALL', 'DATABASE_TYPE_DATA_TYPE_FK', 1, 'DATABASE_TYPE', 'DATA_TYPE_ID', 'DATA_TYPE', 'DATA_TYPE_ID'),
('ALL', 'DATAFILE_DATAFILE_TYPE_FK', 1, 'DATAFILE', 'DATAFILE_TYPE_ID', 'DATAFILE_TYPE', 'DATAFILE_TYPE_ID'),
('ALL', 'DISTRICT_ID_CITY_ID_FK', 1, 'DISTRICT_ID', 'COUNTRY_ID', 'CITY_ID', 'COUNTRY_ID'),
('ALL', 'DISTRICT_ID_CITY_ID_FK', 2, 'DISTRICT_ID', 'PROVINCE_ID', 'CITY_ID', 'PROVINCE_ID'),
('ALL', 'DISTRICT_ID_CITY_ID_FK', 3, 'DISTRICT_ID', 'CITY_ID', 'CITY_ID', 'CITY_ID'),
('ALL', 'ENTITY_FIELD_DATA_TYPE_FK', 1, 'ENTITY_FIELD', 'DATA_TYPE_ID', 'DATA_TYPE', 'DATA_TYPE_ID'),
('ALL', 'ENTITY_FIELD_ID_TABLE_ID_FK', 1, 'ENTITY_FIELD_ID', 'TABLE_ID', 'ENTITY_TABLE_ID', 'TABLE_ID'),
('ALL', 'ENTITY_FIELD_SEQUENTIAL_ID_FK', 1, 'ENTITY_FIELD', 'SEQUENTIAL_ID', 'SEQUENTIAL_ID', 'SEQUENTIAL_ID'),
('ALL', 'ENTITY_RELATIONSHIP_FROM_FK', 1, 'ENTITY_RELATIONSHIP', 'TABLE_FROM', 'ENTITY_FIELD_ID', 'TABLE_ID'),
('ALL', 'ENTITY_RELATIONSHIP_FROM_FK', 2, 'ENTITY_RELATIONSHIP', 'FIELD_FROM', 'ENTITY_FIELD_ID', 'FIELD_ID'),
('ALL', 'ENTITY_RELATIONSHIP_TO_FK', 1, 'ENTITY_RELATIONSHIP', 'TABLE_TO', 'ENTITY_FIELD_ID', 'TABLE_ID'),
('ALL', 'ENTITY_RELATIONSHIP_TO_FK', 2, 'ENTITY_RELATIONSHIP', 'FIELD_TO', 'ENTITY_FIELD_ID', 'FIELD_ID'),
('ALL', 'HOST_CHANNEL_ID_FK', 1, 'HOST', 'CHANNEL_ID', 'CHANNEL', 'CHANNEL_ID'),
('ALL', 'MIC_ACC_FREQ_ID_FK', 1, 'MICRO_ACCOUNT', 'PAYMENT_FREQUENCY_ID', 'FREQUENCY_ID', 'FREQUENCY_ID'),
('ALL', 'MIC_ACC_MIC_ACC_ID_FK', 1, 'MICRO_ACCOUNT', 'PREVIOUS_ACCOUNT', 'MICRO_ACCOUNT_ID', 'ACCOUNT_ID'),
('ALL', 'MIC_ACC_PARTNER_GRP_ID_FK', 1, 'MICRO_ACCOUNT', 'GROUP_CLIENT_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('ALL', 'MIC_ACC_PARTNER_ID_FK', 1, 'MICRO_ACCOUNT', 'PARTNER_CLIENT_ID', 'PARTNER_ID', 'PARTNER_ID'),
('ALL', 'MIC_ACC_PRODUCT_ID_FK', 1, 'MICRO_ACCOUNT', 'PRODUCT_ID', 'PRODUCT_MICROCREDIT_ID', 'PRODUCT_ID'),
('ALL', 'MIC_ACC_QUOTA_TYPE_ID_FK', 1, 'MICRO_ACCOUNT', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID'),
('ALL', 'MIC_ACC_QUO_ACC_ID_FK', 1, 'MICRO_ACCOUNT_QUOTA', 'ACCOUNT_ID', 'MICRO_ACCOUNT_ID', 'ACCOUNT_ID'),
('ALL', 'MIC_ACC_SOLICITUDE_ID_FK', 1, 'MICRO_ACCOUNT', 'SOLICITUDE_ID', 'SOLICITUDE_ID', 'SOLICITUDE_ID'),
('ALL', 'MIC_ACC_STATUS_ID_FK', 1, 'MICRO_ACCOUNT', 'STATUS_ID', 'ACCOUNT_STATUS_ID', 'STATUS_ID'),
('ALL', 'MIC_ACC_USER_ID_FK', 1, 'MICRO_ACCOUNT', 'ASSESSOR', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'MODULE_ID_SUBSYSTEM_ID_FK', 1, 'MODULE_ID', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID'),
('ALL', 'PARAMETER_DATA_TYPE_FK', 1, 'PARAMETER', 'DATA_TYPE_ID', 'DATA_TYPE', 'DATA_TYPE_ID'),
('ALL', 'PARTNER_FREQ_ID_FK', 1, 'PARTNER', 'FREQUENCY_ID', 'FREQUENCY_ID', 'FREQUENCY_ID'),
('ALL', 'PARTNER_GROUP_FREQ_ID_FK', 1, 'PARTNER_GROUP', 'FREQUENCY_ID', 'FREQUENCY_ID', 'FREQUENCY_ID'),
('ALL', 'PARTNER_GROUP_PAR_GRP_ID_FK', 1, 'PARTNER_GROUP', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('ALL', 'PARTNER_GROUP_USER_ID_FK', 1, 'PARTNER_GROUP', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'PARTNER_GRP_MEM_GRP_ID_FK', 1, 'PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('ALL', 'PARTNER_GRP_MEM_PERSON_ID_FK', 1, 'PARTNER_GROUP_MEMBER', 'PERSON_ID', 'PERSON_ID', 'PERSON_ID'),
('ALL', 'PARTNER_GRP_MEM_RESP_ID_FK', 1, 'PARTNER_GROUP_MEMBER', 'RESPONSABILITY_ID', 'RESPONSABILITY_ID', 'RESPONSABILITY_ID'),
('ALL', 'PARTNER_PERSON_ID_FK', 1, 'PARTNER', 'PERSON_ID', 'PERSON_ID', 'PERSON_ID'),
('ALL', 'PARTNER_USER_ID_FK', 1, 'PARTNER', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'PERSON_ADDRESS_DISTRICT_ID_FK', 1, 'PERSON_ADDRESS', 'COUNTRY_ID', 'DISTRICT_ID', 'COUNTRY_ID'),
('ALL', 'PERSON_ADDRESS_DISTRICT_ID_FK', 2, 'PERSON_ADDRESS', 'PROVINCE_ID', 'DISTRICT_ID', 'PROVINCE_ID'),
('ALL', 'PERSON_ADDRESS_DISTRICT_ID_FK', 3, 'PERSON_ADDRESS', 'CITY_ID', 'DISTRICT_ID', 'CITY_ID'),
('ALL', 'PERSON_ADDRESS_DISTRICT_ID_FK', 4, 'PERSON_ADDRESS', 'DISTRICT_ID', 'DISTRICT_ID', 'DISTRICT_ID'),
('ALL', 'PERSON_CITY_ID_FK', 1, 'PERSON', 'COUNTRY_ID', 'CITY_ID', 'COUNTRY_ID'),
('ALL', 'PERSON_CITY_ID_FK', 2, 'PERSON', 'PROVINCE_ID', 'CITY_ID', 'PROVINCE_ID'),
('ALL', 'PERSON_CITY_ID_FK', 3, 'PERSON', 'CITY_ID', 'CITY_ID', 'CITY_ID'),
('ALL', 'PERSON_CIVIL_STATUS_ID_FK', 1, 'PERSON', 'CIVIL_STATUS_ID', 'CIVIL_STATUS_ID', 'CIVIL_STATUS_ID'),
('ALL', 'PERSON_GENDER_TYPE_ID_FK', 1, 'PERSON', 'GENDER_TYPE_ID', 'GENDER_TYPE_ID', 'GENDER_TYPE_ID'),
('ALL', 'PERSON_IDENTIF_TYPE_ID_FK', 1, 'PERSON', 'IDENTIFICATION_TYPE_ID', 'IDENTIFICATION_TYPE_ID', 'IDENTIFICATION_TYPE_ID'),
('ALL', 'PERSON_PROFESSION_TYPE_ID_FK', 1, 'PERSON', 'PROFESSION_TYPE_ID', 'PROFESSION_TYPE_ID', 'PROFESSION_TYPE_ID'),
('ALL', 'PROCESS_COMP_COMPONENT_ID_FK', 1, 'PROCESS_COMPONENT', 'COMPONENT_ID', 'COMPONENT_ID', 'COMPONENT_ID'),
('ALL', 'PROCESS_COMP_COMPONENT_ID_FK', 2, 'PROCESS_COMPONENT', 'TYPE_ID', 'COMPONENT_ID', 'TYPE_ID'),
('ALL', 'PROCESS_COMP_PROCESS_ID_FK', 1, 'PROCESS_COMPONENT', 'SUBSYSTEM_ID', 'PROCESS_ID', 'SUBSYSTEM_ID'),
('ALL', 'PROCESS_COMP_PROCESS_ID_FK', 2, 'PROCESS_COMPONENT', 'MODULE_ID', 'PROCESS_ID', 'MODULE_ID'),
('ALL', 'PROCESS_COMP_PROCESS_ID_FK', 3, 'PROCESS_COMPONENT', 'PROCESS_ID', 'PROCESS_ID', 'PROCESS_ID'),
('ALL', 'PROCESS_DATAFILE_ID_FK', 1, 'PROCESS', 'DATAFILE_ID', 'DATAFILE_ID', 'DATAFILE_ID'),
('ALL', 'PROCESS_ID_MODULE_ID_FK', 1, 'PROCESS_ID', 'SUBSYSTEM_ID', 'MODULE_ID', 'SUBSYSTEM_ID'),
('ALL', 'PROCESS_ID_MODULE_ID_FK', 2, 'PROCESS_ID', 'MODULE_ID', 'MODULE_ID', 'MODULE_ID'),
('ALL', 'PRODUCT_MIC_CURRENCY_ID_FK', 1, 'PRODUCT_MICROCREDIT', 'CURRENCY_ID', 'CURRENCY_ID', 'CURRENCY_ID'),
('ALL', 'PROD_ASSESSOR_PRODUCT_ID_FK', 1, 'PRODUCT_ASESSOR', 'PRODUCT_ID', 'PRODUCT_MICROCREDIT_ID', 'PRODUCT_ID'),
('ALL', 'PROD_ASSESSOR_USER_ID_FK', 1, 'PRODUCT_ASESSOR', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'PROVINCE_ID_COUNTRY_ID_FK', 1, 'PROVINCE_ID', 'COUNTRY_ID', 'COUNTRY_ID', 'COUNTRY_ID'),
('ALL', 'RECOMMENDATION_SOLICITUDE_ID', 1, 'RECOMMENDATION', 'SOLICITUDE_ID', 'SOLICITUDE_ID', 'SOLICITUDE_ID'),
('ALL', 'ROLE_PROCESS_ID_FK', 1, 'ROLE', 'SUBSYSTEM_ID', 'PROCESS_ID', 'SUBSYSTEM_ID'),
('ALL', 'ROLE_PROCESS_ID_FK', 2, 'ROLE', 'MODULE_ID', 'PROCESS_ID', 'MODULE_ID'),
('ALL', 'ROLE_PROCESS_ID_FK', 3, 'ROLE', 'PROCESS_ID', 'PROCESS_ID', 'PROCESS_ID'),
('ALL', 'SOLICITUDE_FUNDS_DEST_ID_FK', 1, 'SOLICITUDE', 'FUNDS_DESTINATION_ID', 'FUNDS_DESTINATION_ID', 'FUNDS_DESTINATION_ID'),
('ALL', 'SOLICITUDE_GROUP_CLIENT_ID_FK', 1, 'SOLICITUDE', 'GROUP_CLIENT_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('ALL', 'SOLICITUDE_OPE_COND_ID_FK', 1, 'SOLICITUDE', 'OPERATIVE_CONDITION_ID', 'OPERATIVE_CONDITION_ID', 'OPERATIVE_CONDITION_ID'),
('ALL', 'SOLICITUDE_PARTNER_CLIE_ID_FK', 1, 'SOLICITUDE', 'PARTNER_CLIENT_ID', 'PARTNER_ID', 'PARTNER_ID'),
('ALL', 'SOLICITUDE_PAY_FREQ_ID_FK', 1, 'SOLICITUDE', 'PAYMENT_FREQUENCY_ID', 'FREQUENCY_ID', 'FREQUENCY_ID'),
('ALL', 'SOLICITUDE_PRODUCT_ID_FK', 1, 'SOLICITUDE', 'PRODUCT_ID', 'PRODUCT_MICROCREDIT_ID', 'PRODUCT_ID'),
('ALL', 'SOLICITUDE_QUOTA_TYPE_ID_FK', 1, 'SOLICITUDE', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID'),
('ALL', 'SOLICITUDE_SOL_STATUS_ID_FK', 1, 'SOLICITUDE', 'STATUS_ID', 'SOLICITUDE_STATUS_ID', 'STATUS_ID'),
('ALL', 'SOLICITUDE_USER_ACCOUNT_ID_FK', 1, 'SOLICITUDE', 'ASSESSOR', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_ACCESS_USER_ACCOUNT_ID_FK', 1, 'USER_ACCESS', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_ACCOUNT_PERSON_ID_FK', 1, 'USER_ACCOUNT', 'PERSON_ID', 'PERSON_ID', 'PERSON_ID'),
('ALL', 'USER_ACCOUNT_USER_STATUS_ID_FK', 1, 'USER_ACCOUNT', 'USER_STATUS_ID', 'USER_STATUS_ID', 'USER_STATUS_ID'),
('ALL', 'USER_ACCOUNT_USER_TYPE_ID_FK', 1, 'USER_ACCOUNT', 'USER_TYPE_ID', 'USER_TYPE_ID', 'USER_TYPE_ID'),
('ALL', 'USER_PROF_USER_ACCOUNT_ID_FK', 1, 'USER_PROFILE', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_SESSION_HOST_ID_FK', 1, 'USER_SESSION', 'HOST_ID', 'HOST_ID', 'HOST_ID'),
('ALL', 'USER_SESS_USER_ACCOUNT_ID_FK', 1, 'USER_SESSION', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'ZONE_ASE_GEO_ZONE_ID_FK', 1, 'ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID'),
('ALL', 'ZONE_ASE_USER_ID_FK', 1, 'ZONE_ASESSOR', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('MXT', 'ACCOUNT_STATUS_COMPANY_FK', 1, 'ACCOUNT_STATUS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ACCOUNT_STATUS_ID_FK', 1, 'ACCOUNT_STATUS', 'STATUS_ID', 'ACCOUNT_STATUS_ID', 'STATUS_ID'),
('MXT', 'ACCOUNT_STATUS_LANGUAGE_FK', 1, 'ACCOUNT_STATUS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'ADDRESS_TYPE_COMPANY_FK', 1, 'ADDRESS_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ADDRESS_TYPE_ID_FK', 1, 'ADDRESS_TYPE', 'ADDRESS_TYPE_ID', 'ADDRESS_TYPE_ID', 'ADDRESS_TYPE_ID'),
('MXT', 'ADDRESS_TYPE_LANGUAGE_FK', 1, 'ADDRESS_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'CITY_COMPANY_FK', 1, 'CITY', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'CITY_ID_FK', 1, 'CITY', 'COUNTRY_ID', 'CITY_ID', 'COUNTRY_ID'),
('MXT', 'CITY_ID_FK', 2, 'CITY', 'PROVINCE_ID', 'CITY_ID', 'PROVINCE_ID'),
('MXT', 'CITY_ID_FK', 3, 'CITY', 'CITY_ID', 'CITY_ID', 'CITY_ID'),
('MXT', 'CITY_LANGUAGE_FK', 1, 'CITY', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'CIVIL_STATUS_COMPANY_FK', 1, 'CIVIL_STATUS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'CIVIL_STATUS_ID_FK', 1, 'CIVIL_STATUS', 'CIVIL_STATUS_ID', 'CIVIL_STATUS_ID', 'CIVIL_STATUS_ID'),
('MXT', 'CIVIL_STATUS_LANGUAGE_FK', 1, 'CIVIL_STATUS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'COMPONENT_COMPANY_FK', 1, 'COMPONENT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'COMPONENT_ID_FK', 1, 'COMPONENT', 'COMPONENT_ID', 'COMPONENT_ID', 'COMPONENT_ID'),
('MXT', 'COMPONENT_ID_FK', 2, 'COMPONENT', 'TYPE_ID', 'COMPONENT_ID', 'TYPE_ID'),
('MXT', 'COUNTRY_COMPANY_FK', 1, 'COUNTRY', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'COUNTRY_ID_FK', 1, 'COUNTRY', 'COUNTRY_ID', 'COUNTRY_ID', 'COUNTRY_ID'),
('MXT', 'COUNTRY_LANGUAGE_FK', 1, 'COUNTRY', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'CURRENCY_COMPANY_FK', 1, 'CURRENCY', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'CURRENCY_ID_FK', 1, 'CURRENCY', 'CURRENCY_ID', 'CURRENCY_ID', 'CURRENCY_ID'),
('MXT', 'CURRENCY_LANGUAGE_FK', 1, 'CURRENCY', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'DATAFILE_COMPANY_FK', 1, 'DATAFILE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'DATAFILE_ID_FK', 1, 'DATAFILE', 'DATAFILE_ID', 'DATAFILE_ID', 'DATAFILE_ID'),
('MXT', 'DISTRICT_COMPANY_FK', 1, 'DISTRICT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'DISTRICT_ID_FK', 1, 'DISTRICT', 'COUNTRY_ID', 'DISTRICT_ID', 'COUNTRY_ID'),
('MXT', 'DISTRICT_ID_FK', 2, 'DISTRICT', 'PROVINCE_ID', 'DISTRICT_ID', 'PROVINCE_ID'),
('MXT', 'DISTRICT_ID_FK', 3, 'DISTRICT', 'CITY_ID', 'DISTRICT_ID', 'CITY_ID'),
('MXT', 'DISTRICT_ID_FK', 4, 'DISTRICT', 'DISTRICT_ID', 'DISTRICT_ID', 'DISTRICT_ID'),
('MXT', 'DISTRICT_LANGUAGE_FK', 1, 'DISTRICT', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'ENTITY_FIELD_COMPANY_FK', 1, 'ENTITY_FIELD', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ENTITY_FIELD_ID_FK', 1, 'ENTITY_FIELD', 'TABLE_ID', 'ENTITY_FIELD_ID', 'TABLE_ID'),
('MXT', 'ENTITY_FIELD_ID_FK', 2, 'ENTITY_FIELD', 'FIELD_ID', 'ENTITY_FIELD_ID', 'FIELD_ID'),
('MXT', 'ENTITY_RELATIONSHIP_COMPANY_FK', 1, 'ENTITY_RELATIONSHIP', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ENTITY_TABLE_COMPANY_FK', 1, 'ENTITY_TABLE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ENTITY_TABLE_ID_FK', 1, 'ENTITY_TABLE', 'TABLE_ID', 'ENTITY_TABLE_ID', 'TABLE_ID'),
('MXT', 'FREQUENCY_COMPANY_FK', 1, 'FREQUENCY', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'FREQUENCY_ID_FK', 1, 'FREQUENCY', 'FREQUENCY_ID', 'FREQUENCY_ID', 'FREQUENCY_ID'),
('MXT', 'FREQUENCY_LANGUAGE_FK', 1, 'FREQUENCY', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'FUNDS_DESTINATION_COMPANY_FK', 1, 'FUNDS_DESTINATION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'FUNDS_DESTINATION_ID_FK', 1, 'FUNDS_DESTINATION', 'FUNDS_DESTINATION_ID', 'FUNDS_DESTINATION_ID', 'FUNDS_DESTINATION_ID'),
('MXT', 'FUNDS_DESTINATION_LANGUAGE_FK', 1, 'FUNDS_DESTINATION', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'GENDER_TYPE_ID_FK', 1, 'GENDER_TYPE', 'GENDER_TYPE_ID', 'GENDER_TYPE_ID', 'GENDER_TYPE_ID'),
('MXT', 'GENDER_TYPE_LANGUAGE_FK', 1, 'GENDER_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'GEOGRAPHIC_ZONE_COMPANY_FK', 1, 'GEOGRAPHIC_ZONE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'GEOGRAPHIC_ZONE_ID_FK', 1, 'GEOGRAPHIC_ZONE', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID'),
('MXT', 'GEOGRAPHIC_ZONE_LANGUAGE_FK', 1, 'GEOGRAPHIC_ZONE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'HOST_COMPANY_FK', 1, 'HOST', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'HOST_ID_FK', 1, 'HOST', 'HOST_ID', 'HOST_ID', 'HOST_ID'),
('MXT', 'IDENTIFICATION_TYPE_COMPANY_FK', 1, 'IDENTIFICATION_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'IDENTIFICATION_TYPE_ID_FK', 1, 'IDENTIFICATION_TYPE', 'IDENTIFICATION_TYPE_ID', 'IDENTIFICATION_TYPE_ID', 'IDENTIFICATION_TYPE_ID'),
('MXT', 'IDENT_LANGUAGE_FK', 1, 'IDENTIFICATION_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'MICRO_ACCOUNT_COMPANY_FK', 1, 'MICRO_ACCOUNT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'MICRO_ACCOUNT_ID_FK', 1, 'MICRO_ACCOUNT', 'ACCOUNT_ID', 'MICRO_ACCOUNT_ID', 'ACCOUNT_ID'),
('MXT', 'MICRO_ACCOUNT_QUOTA_COMPANY_FK', 1, 'MICRO_ACCOUNT_QUOTA', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'MICRO_ACCOUNT_QUOTA_ID_FK', 1, 'MICRO_ACCOUNT_QUOTA', 'ACCOUNT_ID', 'MICRO_ACCOUNT_QUOTA_ID', 'ACCOUNT_ID'),
('MXT', 'MICRO_ACCOUNT_QUOTA_ID_FK', 2, 'MICRO_ACCOUNT_QUOTA', 'SUBACCOUNT', 'MICRO_ACCOUNT_QUOTA_ID', 'SUBACCOUNT'),
('MXT', 'MODULE_COMPANY_FK', 1, 'MODULE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'MODULE_ID_FK', 1, 'MODULE', 'SUBSYSTEM_ID', 'MODULE_ID', 'SUBSYSTEM_ID'),
('MXT', 'MODULE_ID_FK', 2, 'MODULE', 'MODULE_ID', 'MODULE_ID', 'MODULE_ID'),
('MXT', 'MODULE_LANGUAGE_FK', 1, 'MODULE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'OPERATIVE_CONDITION_COMPANY_FK', 1, 'OPERATIVE_CONDITION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'OPERATIVE_CONDITION_ID_FK', 1, 'OPERATIVE_CONDITION', 'OPERATIVE_CONDITION_ID', 'OPERATIVE_CONDITION_ID', 'OPERATIVE_CONDITION_ID'),
('MXT', 'OPERA_LANGUAGE_FK', 1, 'OPERATIVE_CONDITION', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PARAMETER_COMPANY_FK', 1, 'PARAMETER', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARAMETER_ID_FK', 1, 'PARAMETER', 'PARAMETER_ID', 'PARAMETER_ID', 'PARAMETER_ID'),
('MXT', 'PARTNER_COMPANY_FK', 1, 'PARTNER', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARTNER_GROUP_COMPANY_FK', 1, 'PARTNER_GROUP', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARTNER_GROUP_ID_FK', 1, 'PARTNER_GROUP', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('MXT', 'PARTNER_GROUP_LANGUAGE_FK', 1, 'PARTNER_GROUP', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PARTNER_ID_FK', 1, 'PARTNER', 'PARTNER_ID', 'PARTNER_ID', 'PARTNER_ID'),
('MXT', 'PARTNER_LANGUAGE_FK', 1, 'PARTNER', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PERSON_ADDRESS_COMPANY_FK', 1, 'PERSON_ADDRESS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_COMPANY_FK', 1, 'PERSON', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_ID_FK', 1, 'PERSON', 'PERSON_ID', 'PERSON_ID', 'PERSON_ID'),
('MXT', 'PERSON_PHONE_COMPANY_FK', 1, 'PERSON_PHONE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_TYPE_COMPANY_FK', 1, 'PERSON_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_TYPE_ID_FK', 1, 'PERSON_TYPE', 'PERSON_TYPE_ID', 'PERSON_TYPE_ID', 'PERSON_TYPE_ID'),
('MXT', 'PERSON_TYPE_LANGUAGE_FK', 1, 'PERSON_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PHONE_TYPE_COMPANY_FK', 1, 'PHONE_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PHONE_TYPE_ID_FK', 1, 'PHONE_TYPE', 'PHONE_TYPE_ID', 'PHONE_TYPE_ID', 'PHONE_TYPE_ID'),
('MXT', 'PHONE_TYPE_LANGUAGE_FK', 1, 'PHONE_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PROCESS_COMPANY_FK', 1, 'PROCESS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PROCESS_COMPONENT_COMPANY_FK', 1, 'PROCESS_COMPONENT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PROCESS_ID_FK', 1, 'PROCESS', 'SUBSYSTEM_ID', 'PROCESS_ID', 'SUBSYSTEM_ID'),
('MXT', 'PROCESS_ID_FK', 2, 'PROCESS', 'MODULE_ID', 'PROCESS_ID', 'MODULE_ID'),
('MXT', 'PROCESS_ID_FK', 3, 'PROCESS', 'PROCESS_ID', 'PROCESS_ID', 'PROCESS_ID'),
('MXT', 'PROCESS_LANGUAGE_FK', 1, 'PROCESS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PRODUCT_ASESSOR_COMPANY_FK', 1, 'PRODUCT_ASESSOR', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PRODUCT_ASESSOR_ID_FK', 1, 'PRODUCT_ASESSOR', 'USER_ID', 'PRODUCT_ASESSOR_ID', 'USER_ID'),
('MXT', 'PRODUCT_ASESSOR_ID_FK', 2, 'PRODUCT_ASESSOR', 'PRODUCT_ID', 'PRODUCT_ASESSOR_ID', 'PRODUCT_ID'),
('MXT', 'PRODUCT_ASESSOR_LANGUAGE_FK', 1, 'PRODUCT_ASESSOR', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PRODUCT_MICROCREDIT_COMPANY_FK', 1, 'PRODUCT_MICROCREDIT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PRODUCT_MICROCREDIT_ID_FK', 1, 'PRODUCT_MICROCREDIT', 'PRODUCT_ID', 'PRODUCT_MICROCREDIT_ID', 'PRODUCT_ID'),
('MXT', 'PRODU_LANGUAGE_FK', 1, 'PRODUCT_MICROCREDIT', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PROFESSION_TYPE_COMPANY_FK', 1, 'PROFESSION_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PROFESSION_TYPE_ID_FK', 1, 'PROFESSION_TYPE', 'PROFESSION_TYPE_ID', 'PROFESSION_TYPE_ID', 'PROFESSION_TYPE_ID'),
('MXT', 'PROFESSION_TYPE_LANGUAGE_FK', 1, 'PROFESSION_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PROFILE_COMPANY_FK', 1, 'PROFILE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PROFILE_ID_FK', 1, 'PROFILE', 'PROFILE_ID', 'PROFILE_ID', 'PROFILE_ID'),
('MXT', 'PROFILE_LANGUAGE_FK', 1, 'PROFILE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PROVINCE_COMPANY_FK', 1, 'PROVINCE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PROVINCE_ID_FK', 1, 'PROVINCE', 'COUNTRY_ID', 'PROVINCE_ID', 'COUNTRY_ID'),
('MXT', 'PROVINCE_ID_FK', 2, 'PROVINCE', 'PROVINCE_ID', 'PROVINCE_ID', 'PROVINCE_ID'),
('MXT', 'PROVINCE_LANGUAGE_FK', 1, 'PROVINCE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'QUOTA_TYPE_COMPANY_FK', 1, 'QUOTA_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'QUOTA_TYPE_ID_FK', 1, 'QUOTA_TYPE', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID', 'QUOTA_TYPE_ID'),
('MXT', 'QUOTA_TYPE_LANGUAGE_FK', 1, 'QUOTA_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'RECOMMENDATION_COMPANY_FK', 1, 'RECOMMENDATION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'RECOMMENDATION_ID_FK', 1, 'RECOMMENDATION', 'SOLICITUDE_ID', 'RECOMMENDATION_ID', 'SOLICITUDE_ID'),
('MXT', 'RECOMMENDATION_LANGUAGE_FK', 1, 'RECOMMENDATION', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'RESPONSABILITY_COMPANY_FK', 1, 'RESPONSABILITY', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'RESPONSABILITY_ID_FK', 1, 'RESPONSABILITY', 'RESPONSABILITY_ID', 'RESPONSABILITY_ID', 'RESPONSABILITY_ID'),
('MXT', 'RESPONSABILITY_LANGUAGE_FK', 1, 'RESPONSABILITY', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'RESPONSE_COMPANY_FK', 1, 'RESPONSE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'RESPONSE_ID_FK', 1, 'RESPONSE', 'RESPONSE_ID', 'RESPONSE_ID', 'RESPONSE_ID'),
('MXT', 'RESPONSE_LANGUAGE_FK', 1, 'RESPONSE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'ROLE_COMPANY_FK', 1, 'ROLE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'SEQUENTIAL_COMPANY_FK', 1, 'SEQUENTIAL', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'SEQUENTIAL_ID_FK', 1, 'SEQUENTIAL', 'SEQUENTIAL_ID', 'SEQUENTIAL_ID', 'SEQUENTIAL_ID'),
('MXT', 'SOLICITUDE_COMPANY_FK', 1, 'SOLICITUDE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'SOLICITUDE_ID_FK', 1, 'SOLICITUDE', 'SOLICITUDE_ID', 'SOLICITUDE_ID', 'SOLICITUDE_ID'),
('MXT', 'SOLICITUDE_LANGUAGE_FK', 1, 'SOLICITUDE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'SOLICITUDE_STATUS_COMPANY_FK', 1, 'SOLICITUDE_STATUS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'SOLICITUDE_STATUS_ID_FK', 1, 'SOLICITUDE_STATUS', 'STATUS_ID', 'SOLICITUDE_STATUS_ID', 'STATUS_ID'),
('MXT', 'SOLICITUDE_STATUS_LANGUAGE_FK', 1, 'SOLICITUDE_STATUS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'SUBSYSTEM_COMPANY_FK', 1, 'SUBSYSTEM', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'SUBSYSTEM_ID_FK', 1, 'SUBSYSTEM', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID', 'SUBSYSTEM_ID'),
('MXT', 'SUBSYSTEM_LANGUAGE_FK', 1, 'SUBSYSTEM', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'USER_ACCESS_COMPANY_FK', 1, 'USER_ACCESS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_ACCOUNT_COMPANY_FK', 1, 'USER_ACCOUNT', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_ACCOUNT_ID_FK', 1, 'USER_ACCOUNT', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('MXT', 'USER_PROFILE_COMPANY_FK', 1, 'USER_PROFILE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_SESSION_COMPANY_FK', 1, 'USER_SESSION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_STATUS_COMPANY_FK', 1, 'USER_STATUS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_STATUS_ID_FK', 1, 'USER_STATUS', 'USER_STATUS_ID', 'USER_STATUS_ID', 'USER_STATUS_ID'),
('MXT', 'USER_STATUS_LANGUAGE_FK', 1, 'USER_STATUS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'USER_TYPE_COMPANY_FK', 1, 'USER_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_TYPE_ID_FK', 1, 'USER_TYPE', 'USER_TYPE_ID', 'USER_TYPE_ID', 'USER_TYPE_ID'),
('MXT', 'USER_TYPE_LANGUAGE_FK', 1, 'USER_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_TABLE`
--

CREATE TABLE IF NOT EXISTS `ENTITY_TABLE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `TABLE_ID` varchar(30) NOT NULL,
  `HAS_TABLE_ID` tinyint(1) NOT NULL,
  `PACKAGE_NAME` varchar(30) NOT NULL,
  `MULTI_COMPANY` tinyint(1) NOT NULL DEFAULT '0',
  `MULTI_LANGUAGE` tinyint(1) NOT NULL DEFAULT '0',
  `HISTORICAL_DATA` tinyint(1) NOT NULL DEFAULT '0',
  `OPTIMISTIC_LOCKING` tinyint(1) NOT NULL DEFAULT '0',
  `ENUMERATED_TYPES` tinyint(1) NOT NULL DEFAULT '0',
  `CACHE_MEMORY` tinyint(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`TABLE_ID`),
  KEY `ENTITY_TABLE_ID_FK` (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_TABLE`
--

INSERT INTO `ENTITY_TABLE` (`COMPANY_ID`, `TABLE_ID`, `HAS_TABLE_ID`, `PACKAGE_NAME`, `MULTI_COMPANY`, `MULTI_LANGUAGE`, `HISTORICAL_DATA`, `OPTIMISTIC_LOCKING`, `ENUMERATED_TYPES`, `CACHE_MEMORY`, `DESCRIPTION`) VALUES
('ALL', 'ACCOUNT_STATUS', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Account status'),
('ALL', 'ADDRESS_TYPE', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of address types'),
('ALL', 'CHANNEL', 0, 'security', 0, 0, 0, 0, 0, 0, 'Values of channels'),
('ALL', 'CITY', 1, 'parameter', 1, 1, 0, 0, 0, 0, 'Values of cities'),
('ALL', 'CIVIL_STATUS', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of civil statuses'),
('ALL', 'COMPANY', 0, 'common', 0, 0, 0, 0, 0, 0, 'Values of companies'),
('ALL', 'COMPONENT', 1, 'security', 1, 0, 0, 0, 0, 1, 'Values of components'),
('ALL', 'COMPONENT_TYPE', 0, 'security', 0, 0, 0, 0, 0, 0, 'Values of component types'),
('ALL', 'COUNTRY', 1, 'parameter', 1, 1, 0, 0, 0, 0, 'Values of countries'),
('ALL', 'CURRENCY', 1, 'microcredit', 1, 1, 1, 0, 0, 0, 'Currency'),
('ALL', 'DATABASE_TYPE', 0, 'common', 0, 0, 0, 0, 0, 0, 'Values of database types'),
('ALL', 'DATAFILE', 1, 'parameter', 1, 0, 1, 1, 0, 0, 'Values of datafiles'),
('ALL', 'DATAFILE_TYPE', 0, 'parameter', 0, 0, 0, 0, 1, 0, 'Values of datafile types'),
('ALL', 'DATA_TYPE', 0, 'common', 0, 0, 0, 0, 1, 0, 'Values of data types'),
('ALL', 'DISTRICT', 1, 'parameter', 1, 1, 0, 0, 0, 0, 'Values of districts'),
('ALL', 'ENTITY_FIELD', 1, 'common', 1, 0, 0, 0, 0, 0, 'Values of entity fields'),
('ALL', 'ENTITY_RELATIONSHIP', 0, 'common', 1, 0, 0, 0, 0, 0, 'Values of entity relationships'),
('ALL', 'ENTITY_TABLE', 1, 'common', 1, 0, 0, 0, 0, 0, 'Values of entity tables'),
('ALL', 'FREQUENCY', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Frequencies'),
('ALL', 'FUNDS_DESTINATION', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Destination of funds'),
('ALL', 'GENDER_TYPE', 1, 'person', 0, 1, 0, 0, 1, 0, 'Values of gender types'),
('ALL', 'GEOGRAPHIC_ZONE', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Geographic zones'),
('ALL', 'HOST', 1, 'security', 1, 0, 1, 0, 0, 0, 'Values of hosts'),
('ALL', 'IDENTIFICATION_TYPE', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of identification types'),
('ALL', 'LANGUAGE', 0, 'common', 0, 0, 0, 0, 1, 0, 'Values of languages'),
('ALL', 'MICRO_ACCOUNT', 1, 'microcredit', 1, 0, 1, 1, 0, 0, 'Microcredit accounts'),
('ALL', 'MICRO_ACCOUNT_QUOTA', 1, 'microcredit', 1, 0, 1, 1, 0, 0, 'Microcredit quotas associated to a account'),
('ALL', 'MODULE', 1, 'security', 1, 1, 0, 0, 0, 0, 'Values of modules'),
('ALL', 'OPERATIVE_CONDITION', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Operative conditions'),
('ALL', 'PARAMETER', 1, 'parameter', 1, 0, 1, 0, 1, 1, 'Values of parameters'),
('ALL', 'PARTNER', 1, 'microcredit', 1, 1, 1, 0, 0, 0, 'Partners'),
('ALL', 'PARTNER_GROUP', 1, 'microcredit', 1, 1, 1, 0, 0, 0, 'Partner groups'),
('ALL', 'PARTNER_GROUP_MEMBER', 0, 'microcredit', 0, 0, 0, 0, 0, 0, 'Group Members'),
('ALL', 'PERSON', 1, 'person', 1, 0, 1, 1, 0, 0, 'Values of person'),
('ALL', 'PERSON_ADDRESS', 0, 'person', 1, 0, 1, 1, 0, 0, 'Values of person address'),
('ALL', 'PERSON_PHONE', 0, 'person', 1, 0, 1, 1, 0, 0, 'Values of person phones'),
('ALL', 'PERSON_TYPE', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of person types'),
('ALL', 'PHONE_TYPE', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of phone types'),
('ALL', 'PROCESS', 1, 'security', 1, 1, 1, 0, 0, 1, 'Values of processes'),
('ALL', 'PROCESS_COMPONENT', 0, 'security', 1, 0, 0, 0, 0, 1, 'Values of process components'),
('ALL', 'PRODUCT_ASESSOR', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Products per Asessor'),
('ALL', 'PRODUCT_MICROCREDIT', 1, 'microcredit', 1, 1, 1, 0, 0, 0, 'Products of microcredit'),
('ALL', 'PROFESSION_TYPE', 1, 'person', 1, 1, 0, 0, 0, 0, 'Values of profession types'),
('ALL', 'PROFILE', 1, 'security', 1, 1, 0, 0, 0, 0, 'Values of profiles'),
('ALL', 'PROVINCE', 1, 'parameter', 1, 1, 0, 0, 0, 0, 'Values of provinces'),
('ALL', 'QUOTA_TYPE', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Quota type'),
('ALL', 'RECOMMENDATION', 1, 'microcredit', 1, 1, 1, 1, 0, 0, 'Recommendation of microcredit'),
('ALL', 'RESPONSABILITY', 1, 'microcredit', 1, 1, 1, 0, 0, 0, 'Responsabilities of each partner'),
('ALL', 'RESPONSE', 1, 'parameter', 1, 1, 0, 0, 1, 1, 'Values of responses'),
('ALL', 'ROLE', 0, 'security', 1, 0, 1, 0, 0, 0, 'Values of roles'),
('ALL', 'SEQUENTIAL', 1, 'common', 1, 0, 0, 1, 1, 0, 'Values of sequences'),
('ALL', 'SOLICITUDE', 1, 'microcredit', 1, 1, 1, 1, 0, 0, 'Solicitude of microcredit'),
('ALL', 'SOLICITUDE_STATUS', 1, 'microcredit', 1, 1, 0, 0, 0, 0, 'Solicitude status'),
('ALL', 'SUBSYSTEM', 1, 'security', 1, 1, 0, 0, 1, 0, 'Values of subsystems'),
('ALL', 'USER_ACCESS', 0, 'security', 1, 0, 1, 0, 0, 0, 'Values of user access'),
('ALL', 'USER_ACCOUNT', 1, 'security', 1, 0, 1, 1, 0, 0, 'Values of user accounts'),
('ALL', 'USER_PROFILE', 0, 'security', 1, 0, 1, 0, 0, 0, 'Values of user profiles'),
('ALL', 'USER_SESSION', 0, 'security', 1, 0, 1, 1, 0, 0, 'Values of user sessions'),
('ALL', 'USER_STATUS', 1, 'security', 1, 1, 0, 0, 1, 0, 'Values of user status'),
('ALL', 'USER_TYPE', 1, 'security', 1, 1, 0, 0, 1, 0, 'Values of user types'),
('ALL', 'ZONE_ASESSOR', 0, 'microcredit', 0, 0, 0, 0, 0, 0, 'Zones per Assessor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_TABLE_ID`
--

CREATE TABLE IF NOT EXISTS `ENTITY_TABLE_ID` (
  `TABLE_ID` varchar(30) NOT NULL,
  PRIMARY KEY (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_TABLE_ID`
--

INSERT INTO `ENTITY_TABLE_ID` (`TABLE_ID`) VALUES
('ACCOUNT_STATUS'),
('ACCOUNT_STATUS_ID'),
('ADDRESS_TYPE'),
('ADDRESS_TYPE_ID'),
('CHANNEL'),
('CITY'),
('CITY_ID'),
('CIVIL_STATUS'),
('CIVIL_STATUS_ID'),
('COMPANY'),
('COMPONENT'),
('COMPONENT_ID'),
('COMPONENT_TYPE'),
('COUNTRY'),
('COUNTRY_ID'),
('CURRENCY'),
('CURRENCY_ID'),
('DATABASE_TYPE'),
('DATAFILE'),
('DATAFILE_ID'),
('DATAFILE_TYPE'),
('DATA_TYPE'),
('DISTRICT'),
('DISTRICT_ID'),
('ENTITY_FIELD'),
('ENTITY_FIELD_ID'),
('ENTITY_RELATIONSHIP'),
('ENTITY_TABLE'),
('ENTITY_TABLE_ID'),
('FREQUENCY'),
('FREQUENCY_ID'),
('FUNDS_DESTINATION'),
('FUNDS_DESTINATION_ID'),
('GENDER_TYPE'),
('GENDER_TYPE_ID'),
('GEOGRAPHIC_ZONE'),
('GEOGRAPHIC_ZONE_ID'),
('HOST'),
('HOST_ID'),
('IDENTIFICATION_TYPE'),
('IDENTIFICATION_TYPE_ID'),
('LANGUAGE'),
('MICRO_ACCOUNT'),
('MICRO_ACCOUNT_ID'),
('MICRO_ACCOUNT_QUOTA'),
('MICRO_ACCOUNT_QUOTA_ID'),
('MODULE'),
('MODULE_ID'),
('OPERATIVE_CONDITION'),
('OPERATIVE_CONDITION_ID'),
('PARAMETER'),
('PARAMETER_ID'),
('PARTNER'),
('PARTNER_GROUP'),
('PARTNER_GROUP_ID'),
('PARTNER_GROUP_MEMBER'),
('PARTNER_ID'),
('PERSON'),
('PERSON_ADDRESS'),
('PERSON_ID'),
('PERSON_PHONE'),
('PERSON_TYPE'),
('PERSON_TYPE_ID'),
('PHONE_TYPE'),
('PHONE_TYPE_ID'),
('PROCESS'),
('PROCESS_COMPONENT'),
('PROCESS_ID'),
('PRODUCT_ASESSOR'),
('PRODUCT_ASESSOR_ID'),
('PRODUCT_MICROCREDIT'),
('PRODUCT_MICROCREDIT_ID'),
('PROFESSION_TYPE'),
('PROFESSION_TYPE_ID'),
('PROFILE'),
('PROFILE_ID'),
('PROVINCE'),
('PROVINCE_ID'),
('QUOTA_TYPE'),
('QUOTA_TYPE_ID'),
('RECOMMENDATION'),
('RECOMMENDATION_ID'),
('RESPONSABILITY'),
('RESPONSABILITY_ID'),
('RESPONSE'),
('RESPONSE_ID'),
('ROLE'),
('SEQUENTIAL'),
('SEQUENTIAL_ID'),
('SOLICITUDE'),
('SOLICITUDE_ID'),
('SOLICITUDE_STATUS'),
('SOLICITUDE_STATUS_ID'),
('SUBSYSTEM'),
('SUBSYSTEM_ID'),
('USER_ACCESS'),
('USER_ACCOUNT'),
('USER_ACCOUNT_ID'),
('USER_PROFILE'),
('USER_SESSION'),
('USER_STATUS'),
('USER_STATUS_ID'),
('USER_TYPE'),
('USER_TYPE_ID'),
('ZONE_ASESSOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FREQUENCY`
--

CREATE TABLE IF NOT EXISTS `FREQUENCY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `FREQUENCY_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`FREQUENCY_ID`),
  KEY `FREQUENCY_ID_FK` (`FREQUENCY_ID`),
  KEY `FREQUENCY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `FREQUENCY`
--

INSERT INTO `FREQUENCY` (`COMPANY_ID`, `LANGUAGE_ID`, `FREQUENCY_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', '0', 'AL VENCIMIENTO'),
('MXT', 'ES', '1', 'DIARIO'),
('MXT', 'ES', '2', 'SEMANAL'),
('MXT', 'ES', '3', 'QUINCENAL'),
('MXT', 'ES', '4', 'MENSUAL'),
('MXT', 'ES', '5', 'BIMENSUAL'),
('MXT', 'ES', '6', 'TRIMESTRAL'),
('MXT', 'ES', '7', 'SEMESTRAL'),
('MXT', 'ES', '8', 'ANUAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FREQUENCY_ID`
--

CREATE TABLE IF NOT EXISTS `FREQUENCY_ID` (
  `FREQUENCY_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`FREQUENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `FREQUENCY_ID`
--

INSERT INTO `FREQUENCY_ID` (`FREQUENCY_ID`) VALUES
('0'),
('1'),
('2'),
('3'),
('4'),
('5'),
('6'),
('7'),
('8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FUNDS_DESTINATION`
--

CREATE TABLE IF NOT EXISTS `FUNDS_DESTINATION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `FUNDS_DESTINATION_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`FUNDS_DESTINATION_ID`),
  KEY `FUNDS_DESTINATION_ID_FK` (`FUNDS_DESTINATION_ID`),
  KEY `FUNDS_DESTINATION_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `FUNDS_DESTINATION`
--

INSERT INTO `FUNDS_DESTINATION` (`COMPANY_ID`, `LANGUAGE_ID`, `FUNDS_DESTINATION_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', 'CON', 'CONSUMO'),
('MXT', 'ES', 'PRO', 'PRODUCCION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FUNDS_DESTINATION_ID`
--

CREATE TABLE IF NOT EXISTS `FUNDS_DESTINATION_ID` (
  `FUNDS_DESTINATION_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`FUNDS_DESTINATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `FUNDS_DESTINATION_ID`
--

INSERT INTO `FUNDS_DESTINATION_ID` (`FUNDS_DESTINATION_ID`) VALUES
('CON'),
('PRO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GENDER_TYPE`
--

CREATE TABLE IF NOT EXISTS `GENDER_TYPE` (
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `GENDER_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LANGUAGE_ID`,`GENDER_TYPE_ID`),
  KEY `GENDER_TYPE_ID_FK` (`GENDER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `GENDER_TYPE`
--

INSERT INTO `GENDER_TYPE` (`LANGUAGE_ID`, `GENDER_TYPE_ID`, `NAME`) VALUES
('ES', 'F', 'FEMENINO'),
('ES', 'M', 'MASCULINO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GENDER_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `GENDER_TYPE_ID` (
  `GENDER_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`GENDER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `GENDER_TYPE_ID`
--

INSERT INTO `GENDER_TYPE_ID` (`GENDER_TYPE_ID`) VALUES
('F'),
('M');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GEOGRAPHIC_ZONE`
--

CREATE TABLE IF NOT EXISTS `GEOGRAPHIC_ZONE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `GEOGRAPHIC_ZONE_ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `COORDINATE_TYPE` varchar(20) NOT NULL,
  `P11` varchar(50) NOT NULL,
  `P12` varchar(50) NOT NULL,
  `P21` varchar(50) DEFAULT NULL,
  `P22` varchar(50) DEFAULT NULL,
  `P31` varchar(50) DEFAULT NULL,
  `P32` varchar(50) DEFAULT NULL,
  `P41` varchar(50) DEFAULT NULL,
  `P42` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`GEOGRAPHIC_ZONE_ID`),
  KEY `GEOGRAPHIC_ZONE_ID_FK` (`GEOGRAPHIC_ZONE_ID`),
  KEY `GEOGRAPHIC_ZONE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `GEOGRAPHIC_ZONE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GEOGRAPHIC_ZONE_ID`
--

CREATE TABLE IF NOT EXISTS `GEOGRAPHIC_ZONE_ID` (
  `GEOGRAPHIC_ZONE_ID` int(11) NOT NULL,
  PRIMARY KEY (`GEOGRAPHIC_ZONE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `GEOGRAPHIC_ZONE_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HOST`
--

CREATE TABLE IF NOT EXISTS `HOST` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `HOST_ID` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `ADDRESS` varchar(60) NOT NULL,
  `CHANNEL_ID` varchar(4) NOT NULL,
  `TIME_ZONE` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`HOST_ID`),
  KEY `HOST_CHANNEL_ID_FK` (`CHANNEL_ID`),
  KEY `HOST_ID_FK` (`HOST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `HOST`
--

INSERT INTO `HOST` (`COMPANY_ID`, `EXPIRED`, `HOST_ID`, `CREATED`, `ADDRESS`, `CHANNEL_ID`, `TIME_ZONE`) VALUES
('MXT', '9999-12-31 00:00:00', 'LOCALHOST', '2012-03-17 00:00:00', '127.0.0.1', 'PC', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HOST_ID`
--

CREATE TABLE IF NOT EXISTS `HOST_ID` (
  `HOST_ID` varchar(40) NOT NULL,
  PRIMARY KEY (`HOST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `HOST_ID`
--

INSERT INTO `HOST_ID` (`HOST_ID`) VALUES
('LOCALHOST');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `IDENTIFICATION_TYPE`
--

CREATE TABLE IF NOT EXISTS `IDENTIFICATION_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `IDENTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`IDENTIFICATION_TYPE_ID`),
  KEY `IDENTIFICATION_TYPE_ID_FK` (`IDENTIFICATION_TYPE_ID`),
  KEY `IDENT_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `IDENTIFICATION_TYPE`
--

INSERT INTO `IDENTIFICATION_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `IDENTIFICATION_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', 'CED', 'CEDULA DE IDENTIDAD'),
('MXT', 'ES', 'PER', 'PERSONA SIN IDENTIFICACION'),
('MXT', 'ES', 'PSE', 'PASAPORTE EXTRANJERO'),
('MXT', 'ES', 'RUC', 'RUC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `IDENTIFICATION_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `IDENTIFICATION_TYPE_ID` (
  `IDENTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`IDENTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `IDENTIFICATION_TYPE_ID`
--

INSERT INTO `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`) VALUES
('CED'),
('PER'),
('PSE'),
('RUC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LANGUAGE`
--

CREATE TABLE IF NOT EXISTS `LANGUAGE` (
  `LANGUAGE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `LANGUAGE`
--

INSERT INTO `LANGUAGE` (`LANGUAGE_ID`, `NAME`) VALUES
('EN', 'ENGLISH'),
('ES', 'ESPAÑOL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MICRO_ACCOUNT`
--

CREATE TABLE IF NOT EXISTS `MICRO_ACCOUNT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `ACCOUNT_ID` varchar(25) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SOLICITUDE_ID` int(11) NOT NULL,
  `CLIENT_NAME` varchar(120) NOT NULL,
  `ASSESSOR` varchar(20) NOT NULL,
  `PARTNER_CLIENT_ID` int(11) DEFAULT NULL,
  `GROUP_CLIENT_ID` int(11) DEFAULT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  `STATUS_ID` varchar(3) NOT NULL,
  `NUMBER_RENEWAL` int(11) NOT NULL,
  `PREVIOUS_ACCOUNT` varchar(25) DEFAULT NULL,
  `AMOUNT` decimal(19,6) NOT NULL,
  `TERM` bigint(20) NOT NULL,
  `QUOTA_TYPE_ID` varchar(3) NOT NULL,
  `NUMBER_QUOTAS` int(11) NOT NULL,
  `PAYMENT_FREQUENCY_ID` varchar(3) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`ACCOUNT_ID`),
  KEY `MICRO_ACCOUNT_ID_FK` (`ACCOUNT_ID`),
  KEY `MIC_ACC_FREQ_ID_FK` (`PAYMENT_FREQUENCY_ID`),
  KEY `MIC_ACC_MIC_ACC_ID_FK` (`PREVIOUS_ACCOUNT`),
  KEY `MIC_ACC_PARTNER_GRP_ID_FK` (`GROUP_CLIENT_ID`),
  KEY `MIC_ACC_PARTNER_ID_FK` (`PARTNER_CLIENT_ID`),
  KEY `MIC_ACC_PRODUCT_ID_FK` (`PRODUCT_ID`),
  KEY `MIC_ACC_QUOTA_TYPE_ID_FK` (`QUOTA_TYPE_ID`),
  KEY `MIC_ACC_SOLICITUDE_ID_FK` (`SOLICITUDE_ID`),
  KEY `MIC_ACC_STATUS_ID_FK` (`STATUS_ID`),
  KEY `MIC_ACC_USER_ID_FK` (`ASSESSOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MICRO_ACCOUNT`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MICRO_ACCOUNT_ID`
--

CREATE TABLE IF NOT EXISTS `MICRO_ACCOUNT_ID` (
  `ACCOUNT_ID` varchar(25) NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MICRO_ACCOUNT_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MICRO_ACCOUNT_QUOTA`
--

CREATE TABLE IF NOT EXISTS `MICRO_ACCOUNT_QUOTA` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `ACCOUNT_ID` varchar(25) NOT NULL,
  `SUBACCOUNT` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `PROVISION_DAYS` int(11) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `EXPIRATION_DATE` datetime NOT NULL,
  `PAYMENT_DATE` datetime DEFAULT NULL,
  `REDUCED_CAPITAL` decimal(19,6) NOT NULL,
  `CAPITAL` decimal(19,6) NOT NULL,
  `INTEREST` decimal(19,6) NOT NULL,
  `CHARGE` decimal(19,6) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`ACCOUNT_ID`,`SUBACCOUNT`),
  KEY `MICRO_ACCOUNT_QUOTA_ID_FK` (`ACCOUNT_ID`,`SUBACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MICRO_ACCOUNT_QUOTA`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MICRO_ACCOUNT_QUOTA_ID`
--

CREATE TABLE IF NOT EXISTS `MICRO_ACCOUNT_QUOTA_ID` (
  `ACCOUNT_ID` varchar(25) NOT NULL,
  `SUBACCOUNT` int(11) NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`,`SUBACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MICRO_ACCOUNT_QUOTA_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MODULE`
--

CREATE TABLE IF NOT EXISTS `MODULE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`SUBSYSTEM_ID`,`MODULE_ID`),
  KEY `MODULE_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`),
  KEY `MODULE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MODULE`
--

INSERT INTO `MODULE` (`COMPANY_ID`, `LANGUAGE_ID`, `SUBSYSTEM_ID`, `MODULE_ID`, `NAME`) VALUES
('MXT', 'ES', 'A', '0', 'AUTENTITICACIÓN'),
('MXT', 'ES', 'A', '1', 'DATOS GENERALES'),
('MXT', 'ES', 'A', '2', 'ROLES Y USUARIOS'),
('MXT', 'ES', 'B', '0', 'PARAMETRIZACIÓN'),
('MXT', 'ES', 'B', '1', 'PERSONAS NATURALES'),
('MXT', 'ES', 'C', '0', 'PARAMETRIZACIÓN'),
('MXT', 'ES', 'C', '1', 'PLANIFICACIÓN'),
('MXT', 'ES', 'C', '2', 'COMERCIALIZACIÓN'),
('MXT', 'ES', 'C', '3', 'SOLICITUD'),
('MXT', 'ES', 'C', '4', 'INSTRUMENTACIÓN CORE BANCARIO'),
('MXT', 'ES', 'C', '5', 'SEGUIMIENTO Y RECUPERACIÓN'),
('MXT', 'ES', 'G', '0', 'MENU'),
('MXT', 'ES', 'G', '1', 'PARAMETROS'),
('MXT', 'ES', 'G', '2', 'LISTAS PARA LOS COMBOS'),
('MXT', 'ES', 'G', '3', 'LOCALIZACIÓN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MODULE_ID`
--

CREATE TABLE IF NOT EXISTS `MODULE_ID` (
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  PRIMARY KEY (`SUBSYSTEM_ID`,`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MODULE_ID`
--

INSERT INTO `MODULE_ID` (`SUBSYSTEM_ID`, `MODULE_ID`) VALUES
('A', '0'),
('A', '1'),
('A', '2'),
('B', '0'),
('B', '1'),
('C', '0'),
('C', '1'),
('C', '2'),
('C', '3'),
('C', '4'),
('C', '5'),
('G', '0'),
('G', '1'),
('G', '2'),
('G', '3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `OPERATIVE_CONDITION`
--

CREATE TABLE IF NOT EXISTS `OPERATIVE_CONDITION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `OPERATIVE_CONDITION_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`OPERATIVE_CONDITION_ID`),
  KEY `OPERATIVE_CONDITION_ID_FK` (`OPERATIVE_CONDITION_ID`),
  KEY `OPERA_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `OPERATIVE_CONDITION`
--

INSERT INTO `OPERATIVE_CONDITION` (`COMPANY_ID`, `LANGUAGE_ID`, `OPERATIVE_CONDITION_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', 'INS', 'INSTRUMENTACIÓN PENDIENTE'),
('MXT', 'ES', 'NOR', 'NORMAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `OPERATIVE_CONDITION_ID`
--

CREATE TABLE IF NOT EXISTS `OPERATIVE_CONDITION_ID` (
  `OPERATIVE_CONDITION_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`OPERATIVE_CONDITION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `OPERATIVE_CONDITION_ID`
--

INSERT INTO `OPERATIVE_CONDITION_ID` (`OPERATIVE_CONDITION_ID`) VALUES
('INS'),
('NOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARAMETER`
--

CREATE TABLE IF NOT EXISTS `PARAMETER` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PARAMETER_ID` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `PARAMETER_VALUE` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PARAMETER_ID`),
  KEY `PARAMETER_DATA_TYPE_FK` (`DATA_TYPE_ID`),
  KEY `PARAMETER_ID_FK` (`PARAMETER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARAMETER`
--

INSERT INTO `PARAMETER` (`COMPANY_ID`, `EXPIRED`, `PARAMETER_ID`, `CREATED`, `SUBSYSTEM_ID`, `DATA_TYPE_ID`, `PARAMETER_VALUE`, `DESCRIPTION`) VALUES
('MXT', '9999-12-31 00:00:00', 'DEFAULT_PASSWORD', '2012-02-24 00:00:00', 'A', 'String', 'b34d7dbecec802e49a6cdb0d0a9334a5', 'CONTRASEÑA POR DEFECTO PARA USUARIOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARAMETER_ID`
--

CREATE TABLE IF NOT EXISTS `PARAMETER_ID` (
  `PARAMETER_ID` varchar(40) NOT NULL,
  PRIMARY KEY (`PARAMETER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARAMETER_ID`
--

INSERT INTO `PARAMETER_ID` (`PARAMETER_ID`) VALUES
('DEFAULT_PASSWORD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER`
--

CREATE TABLE IF NOT EXISTS `PARTNER` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PARTNER_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `ACTIVITY` varchar(300) DEFAULT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `FREQUENCY_ID` varchar(3) DEFAULT NULL,
  `MEETING_DAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`PARTNER_ID`),
  KEY `PARTNER_FREQ_ID_FK` (`FREQUENCY_ID`),
  KEY `PARTNER_ID_FK` (`PARTNER_ID`),
  KEY `PARTNER_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `PARTNER_PERSON_ID_FK` (`PERSON_ID`),
  KEY `PARTNER_USER_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_GROUP`
--

CREATE TABLE IF NOT EXISTS `PARTNER_GROUP` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PARTNER_GROUP_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `GROUP_DESCRIPTION` varchar(100) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `ACTIVITY` varchar(300) DEFAULT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `FREQUENCY_ID` varchar(3) NOT NULL,
  `MEETING_DAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`PARTNER_GROUP_ID`),
  KEY `PARTNER_GROUP_FREQ_ID_FK` (`FREQUENCY_ID`),
  KEY `PARTNER_GROUP_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `PARTNER_GROUP_PAR_GRP_ID_FK` (`PARTNER_GROUP_ID`),
  KEY `PARTNER_GROUP_USER_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_GROUP`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_GROUP_ID`
--

CREATE TABLE IF NOT EXISTS `PARTNER_GROUP_ID` (
  `PARTNER_GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY (`PARTNER_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_GROUP_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_GROUP_MEMBER`
--

CREATE TABLE IF NOT EXISTS `PARTNER_GROUP_MEMBER` (
  `PARTNER_GROUP_ID` int(11) NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `RESPONSABILITY_ID` varchar(3) NOT NULL,
  `OBSERVATIONS` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`PARTNER_GROUP_ID`,`PERSON_ID`),
  KEY `PARTNER_GRP_MEM_PERSON_ID_FK` (`PERSON_ID`),
  KEY `PARTNER_GRP_MEM_RESP_ID_FK` (`RESPONSABILITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_GROUP_MEMBER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_ID`
--

CREATE TABLE IF NOT EXISTS `PARTNER_ID` (
  `PARTNER_ID` int(11) NOT NULL,
  PRIMARY KEY (`PARTNER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON`
--

CREATE TABLE IF NOT EXISTS `PERSON` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `LAST_NAME` varchar(40) NOT NULL,
  `SECOND_LAST_NAME` varchar(40) DEFAULT NULL,
  `IDENTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  `IDENTIFICATION_NUMBER` varchar(40) NOT NULL,
  `DATE_OF_BIRTH` datetime NOT NULL,
  `GENDER_TYPE_ID` varchar(4) NOT NULL,
  `CIVIL_STATUS_ID` varchar(4) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `DISTRICT_ID` varchar(4) DEFAULT NULL,
  `PROFESSION_TYPE_ID` varchar(4) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`),
  KEY `PERSON_CITY_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `PERSON_CIVIL_STATUS_ID_FK` (`CIVIL_STATUS_ID`),
  KEY `PERSON_GENDER_TYPE_ID_FK` (`GENDER_TYPE_ID`),
  KEY `PERSON_IDENTIF_TYPE_ID_FK` (`IDENTIFICATION_TYPE_ID`),
  KEY `PERSON_ID_FK` (`PERSON_ID`),
  KEY `PERSON_PROFESSION_TYPE_ID_FK` (`PROFESSION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON`
--

INSERT INTO `PERSON` (`COMPANY_ID`, `EXPIRED`, `PERSON_ID`, `CREATED`, `NAME`, `LAST_NAME`, `SECOND_LAST_NAME`, `IDENTIFICATION_TYPE_ID`, `IDENTIFICATION_NUMBER`, `DATE_OF_BIRTH`, `GENDER_TYPE_ID`, `CIVIL_STATUS_ID`, `COUNTRY_ID`, `CITY_ID`, `PROVINCE_ID`, `DISTRICT_ID`, `PROFESSION_TYPE_ID`, `VERSION`) VALUES
('MXT', '9999-12-31 00:00:00', 1, '2012-03-13 23:46:56', 'RONALD MARCELO', 'GUALÁN', 'SAAVEDRA', 'CED', '1400658611', '1988-09-28 00:00:00', 'M', 'SOL', 'EC', 'CU', 'AZ', 'RIC', '101', 1),
('MXT', '9999-12-31 00:00:00', 2, '2012-03-13 23:48:24', 'DIEGO FABIAN', 'MONTUFAR', NULL, 'CED', '0105362651', '1988-12-05 00:00:00', 'M', 'SOL', 'EC', 'CU', 'AZ', 'TOT', '103', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_ADDRESS`
--

CREATE TABLE IF NOT EXISTS `PERSON_ADDRESS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `ADDRESS_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `ADDRESS_TYPE_ID` varchar(4) NOT NULL,
  `ADDRESS_DESCRIPTION` varchar(40) NOT NULL,
  `COUNTRY_ID` varchar(2) DEFAULT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `DISTRICT_ID` varchar(4) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`ADDRESS_SEQUENCE`),
  KEY `PERSON_ADDRESS_DISTRICT_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_ADDRESS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_ID`
--

CREATE TABLE IF NOT EXISTS `PERSON_ID` (
  `PERSON_ID` int(11) NOT NULL,
  PRIMARY KEY (`PERSON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_ID`
--

INSERT INTO `PERSON_ID` (`PERSON_ID`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_PHONE`
--

CREATE TABLE IF NOT EXISTS `PERSON_PHONE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `PHONE_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `PHONE_TYPE_ID` varchar(4) NOT NULL,
  `AREA_CODE` varchar(4) NOT NULL,
  `PHONE_NUMBER` varchar(40) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`PHONE_SEQUENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_PHONE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_TYPE`
--

CREATE TABLE IF NOT EXISTS `PERSON_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PERSON_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PERSON_TYPE_ID`),
  KEY `PERSON_TYPE_ID_FK` (`PERSON_TYPE_ID`),
  KEY `PERSON_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_TYPE`
--

INSERT INTO `PERSON_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `PERSON_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', 'JUR', 'JURIDICA'),
('MXT', 'ES', 'NAT', 'NATURAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `PERSON_TYPE_ID` (
  `PERSON_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`PERSON_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_TYPE_ID`
--

INSERT INTO `PERSON_TYPE_ID` (`PERSON_TYPE_ID`) VALUES
('JUR'),
('NAT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PHONE_TYPE`
--

CREATE TABLE IF NOT EXISTS `PHONE_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PHONE_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PHONE_TYPE_ID`),
  KEY `PHONE_TYPE_ID_FK` (`PHONE_TYPE_ID`),
  KEY `PHONE_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PHONE_TYPE`
--

INSERT INTO `PHONE_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `PHONE_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', 'ALE', 'ALEGRO'),
('MXT', 'ES', 'C/W', 'CABLE / WIRL'),
('MXT', 'ES', 'CEL', 'CELULAR'),
('MXT', 'ES', 'FAX', 'FAX'),
('MXT', 'ES', 'MOV', 'MOVISTAR'),
('MXT', 'ES', 'POR', 'CLARO'),
('MXT', 'ES', 'TEL', 'TELEFONO NOR'),
('MXT', 'ES', 'TFX', 'TELEFONO Y FAX');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PHONE_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `PHONE_TYPE_ID` (
  `PHONE_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`PHONE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PHONE_TYPE_ID`
--

INSERT INTO `PHONE_TYPE_ID` (`PHONE_TYPE_ID`) VALUES
('ALE'),
('C/W'),
('CEL'),
('FAX'),
('MOV'),
('POR'),
('TEL'),
('TFX');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROCESS`
--

CREATE TABLE IF NOT EXISTS `PROCESS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `ENABLE` tinyint(1) NOT NULL DEFAULT '1',
  `MENU` tinyint(1) NOT NULL DEFAULT '1',
  `URL` varchar(100) NOT NULL,
  `DATAFILE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `PROCESS_DATAFILE_ID_FK` (`DATAFILE_ID`),
  KEY `PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `PROCESS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS`
--

INSERT INTO `PROCESS` (`COMPANY_ID`, `LANGUAGE_ID`, `EXPIRED`, `SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`, `CREATED`, `NAME`, `ENABLE`, `MENU`, `URL`, `DATAFILE_ID`) VALUES
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '0', '01', '2011-10-14 00:00:00', 'LOGGIN', 1, 0, 'A001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '0', '02', '2012-03-17 02:15:11', 'LOGOUT', 1, 0, 'A002', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '01', '2011-10-14 00:00:00', 'ESTADO DE USUARIOS', 1, 1, 'A101', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '02', '2011-11-27 00:00:00', 'TIPOS DE USUARIO', 1, 1, 'A102', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '03', '2011-11-27 00:00:00', 'SUBSISTEMAS', 1, 1, 'A103', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '04', '2011-11-27 00:00:00', 'MÓDULOS', 1, 1, 'A104', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '05', '2011-11-27 00:00:00', 'PROCESOS', 1, 1, 'A105', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '06', '2012-03-03 12:08:36', 'COMPONENTES', 1, 1, 'A106', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '07', '2012-03-03 12:08:36', 'COMPONENTES POR PROCESO', 1, 1, 'A107', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '01', '2011-10-14 00:00:00', 'ROLES', 1, 1, 'A201', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '02', '2011-11-29 00:00:00', 'PROCESOS POR ROL', 1, 1, 'A202', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '03', '2011-11-30 00:00:00', 'USUARIOS', 1, 1, 'A203', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '04', '2011-11-30 00:00:00', 'ROLES POR USUARIO', 1, 1, 'A204', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '05', '2011-11-30 00:00:00', 'CAMBIO CONTRASEÑA', 1, 1, 'A205', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '06', '2011-11-30 00:00:00', 'RESETEO CONTRASEÑA', 1, 1, 'A205', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '07', '2011-12-07 00:00:00', 'TERMINALES', 1, 1, 'A206', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '01', '2012-01-21 19:34:18', 'TIPOS DE PERSONA', 1, 1, 'B001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '02', '2012-01-21 19:34:18', 'TIPOS DE IDENTIFICACIÓN', 1, 1, 'B002', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '03', '2012-01-21 19:34:18', 'GÉNEROS', 1, 1, 'B003', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '04', '2012-01-21 19:34:18', 'ESTADOS CIVILES', 1, 1, 'B004', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '05', '2012-01-21 19:34:18', 'PROFESIONES', 1, 1, 'B005', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '06', '2011-10-14 00:00:00', 'TIPOS DE DIRECCIÓN', 1, 1, 'B006', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '0', '07', '2011-10-14 00:00:00', 'TIPOS DE TELÉFONO', 1, 1, 'B007', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '1', '01', '2011-10-14 00:00:00', 'PERSONAS NATURALES', 1, 1, 'B101', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '1', '02', '2011-10-14 00:00:00', 'DIRECCIONES', 1, 1, 'B102', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '1', '03', '2011-10-14 00:00:00', 'TELEFONOS', 1, 1, 'B103', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'B', '1', '04', '2011-10-14 00:00:00', 'PERSONAS NATURALES GENERAL', 0, 1, 'B104', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '01', '2012-01-14 16:55:33', 'MONEDAS', 1, 1, 'C001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '02', '2012-01-14 16:55:34', 'ESTATUS SOLICITUD', 1, 1, 'C002', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '03', '2012-01-14 16:55:34', 'TIPOS DE CUOTA', 1, 1, 'C003', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '04', '2012-01-14 16:55:35', 'FRECUENCIAS', 1, 1, 'C004', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '05', '2012-01-14 16:55:35', 'DESTINOS DE FONDOS', 1, 1, 'C005', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '06', '2012-03-13 23:53:53', 'CONDICIONES OPERATIVAS', 1, 1, 'C006', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '07', '2012-03-14 23:30:18', 'ESTATUS DE CUENTA', 1, 1, 'C007', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '01', '2012-01-08 00:00:00', 'ASESOR DE CRÉDITO', 1, 1, 'C001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '02', '2012-01-08 00:00:00', 'ZONAS GEOGRÁFICAS', 1, 1, 'C102', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '03', '2012-01-08 00:00:00', 'ZONAS POR ASESOR', 1, 1, 'C003', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '04', '2012-01-08 00:00:00', 'PRODUCTOS', 1, 1, 'C004', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '05', '2012-01-08 00:00:00', 'PRODUCTOS POR ASESOR', 1, 1, 'C005', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '2', '01', '2012-01-14 16:57:27', 'SOCIOS INDIVIDUALES', 1, 1, 'C201', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '2', '02', '2012-01-14 16:57:28', 'SOCIOS GRUPALES', 1, 1, 'C202', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '3', '01', '2012-01-14 16:58:40', 'SOLICITUD DE MICROCRÉDITO', 1, 1, 'C301', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '3', '02', '2012-01-14 16:58:41', 'RECOMENDACIÓN', 1, 1, 'C302', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '4', '01', '2012-02-29 14:14:17', 'CONSULTA SOLICITUDES', 1, 1, 'C401', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '4', '02', '2012-02-29 14:14:17', 'INSTRUMENTACIÓN CORE', 1, 1, 'C402', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '5', '01', '2012-02-29 14:15:47', 'VERIFICACIÓN DESTINO', 1, 1, 'C501', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '5', '02', '2012-02-29 14:15:47', 'CONSUTA CUOTAS', 1, 1, 'C502', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '5', '03', '2012-02-29 14:15:47', 'PAGO CUOTAS', 1, 1, 'C503', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '5', '04', '2012-02-29 14:15:47', 'ABONO EXTRAORDINARIO', 1, 1, 'C504', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '5', '05', '2012-02-29 14:15:47', 'PRECANCELACIÓN', 1, 1, 'C505', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '0', '01', '2011-10-14 00:00:00', 'MENU PRINCIPAL', 1, 0, 'G001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '1', '01', '2011-10-14 00:00:00', 'PARAMETROS GENERALES', 1, 1, 'G101', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '01', '2011-10-14 00:00:00', 'LOV PARA LOS COMBOS', 1, 0, 'G201', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '02', '2012-03-03 12:09:42', 'LOV PARA SOCIOS INDIVIDUALES', 1, 0, 'G202', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '03', '2012-03-03 12:09:42', 'LOV PARA ROLES POR USUARIO', 1, 0, 'G203', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '04', '2012-03-03 12:09:42', 'LOV PARA PERSONAS', 1, 0, 'G204', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '3', '01', '2011-10-14 00:00:00', 'PAISES', 1, 1, 'G301', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '3', '02', '2011-10-14 00:00:00', 'PROVINCIAS', 1, 1, 'G302', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '3', '03', '2011-10-14 00:00:00', 'CANTONES', 1, 1, 'G303', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '3', '04', '2011-10-14 00:00:00', 'PARROQUIAS', 1, 1, 'G304', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROCESS_COMPONENT`
--

CREATE TABLE IF NOT EXISTS `PROCESS_COMPONENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `PROCESS_SEQUENCE` tinyint(4) NOT NULL,
  `COMPONENT_ID` varchar(150) NOT NULL,
  `TYPE_ID` varchar(3) NOT NULL,
  `ENABLE` tinyint(1) NOT NULL DEFAULT '0',
  `AUTHORIZE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`,`PROCESS_SEQUENCE`),
  KEY `PROCESS_COMP_COMPONENT_ID_FK` (`COMPONENT_ID`,`TYPE_ID`),
  KEY `PROCESS_COMP_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS_COMPONENT`
--

INSERT INTO `PROCESS_COMPONENT` (`COMPANY_ID`, `SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`, `PROCESS_SEQUENCE`, `COMPONENT_ID`, `TYPE_ID`, `ENABLE`, `AUTHORIZE`) VALUES
('MXT', 'A', '0', '01', 1, 'mobile.core.security.Loggin', 'QRY', 1, 0),
('MXT', 'A', '0', '02', 1, 'mobile.core.security.Logout', 'MNT', 1, 0),
('MXT', 'A', '2', '05', 1, 'mobile.core.security.ChangePassword', 'MNT', 1, NULL),
('MXT', 'C', '4', '01', 1, 'mobile.logic.microxt.query.QuerySolicitude', 'QRY', 1, NULL),
('MXT', 'C', '4', '02', 1, 'mobile.logic.microxt.query.QuerySolicitude', 'QRY', 1, NULL),
('MXT', 'C', '4', '02', 2, 'mobile.logic.microxt.RemoteInstrumentation', 'MNT', 1, NULL),
('MXT', 'G', '0', '01', 1, 'mobile.logic.general.MenuGenerator', 'QRY', 1, 0),
('MXT', 'G', '2', '02', 1, 'mobile.logic.microxt.query.QueryPartnerInfo', 'QRY', 1, NULL),
('MXT', 'G', '2', '03', 1, 'mobile.core.security.query.QueryProfilesForUser', 'QRY', 1, NULL),
('MXT', 'G', '2', '04', 1, 'mobile.logic.person.QueryPersonInfo', 'QRY', 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROCESS_ID`
--

CREATE TABLE IF NOT EXISTS `PROCESS_ID` (
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  PRIMARY KEY (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS_ID`
--

INSERT INTO `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) VALUES
('A', '0', '01'),
('A', '0', '02'),
('A', '1', '01'),
('A', '1', '02'),
('A', '1', '03'),
('A', '1', '04'),
('A', '1', '05'),
('A', '1', '06'),
('A', '1', '07'),
('A', '2', '01'),
('A', '2', '02'),
('A', '2', '03'),
('A', '2', '04'),
('A', '2', '05'),
('A', '2', '06'),
('A', '2', '07'),
('B', '0', '01'),
('B', '0', '02'),
('B', '0', '03'),
('B', '0', '04'),
('B', '0', '05'),
('B', '0', '06'),
('B', '0', '07'),
('B', '1', '01'),
('B', '1', '02'),
('B', '1', '03'),
('B', '1', '04'),
('C', '0', '01'),
('C', '0', '02'),
('C', '0', '03'),
('C', '0', '04'),
('C', '0', '05'),
('C', '0', '06'),
('C', '0', '07'),
('C', '1', '01'),
('C', '1', '02'),
('C', '1', '03'),
('C', '1', '04'),
('C', '1', '05'),
('C', '2', '01'),
('C', '2', '02'),
('C', '3', '01'),
('C', '3', '02'),
('C', '4', '01'),
('C', '4', '02'),
('C', '5', '01'),
('C', '5', '02'),
('C', '5', '03'),
('C', '5', '04'),
('C', '5', '05'),
('G', '0', '01'),
('G', '1', '01'),
('G', '2', '01'),
('G', '2', '02'),
('G', '2', '03'),
('G', '2', '04'),
('G', '3', '01'),
('G', '3', '02'),
('G', '3', '03'),
('G', '3', '04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCT_ASESSOR`
--

CREATE TABLE IF NOT EXISTS `PRODUCT_ASESSOR` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  `OBSERVATIONS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`USER_ID`,`PRODUCT_ID`),
  KEY `PRODUCT_ASESSOR_ID_FK` (`USER_ID`,`PRODUCT_ID`),
  KEY `PRODUCT_ASESSOR_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `PROD_ASSESSOR_PRODUCT_ID_FK` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PRODUCT_ASESSOR`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCT_ASESSOR_ID`
--

CREATE TABLE IF NOT EXISTS `PRODUCT_ASESSOR_ID` (
  `USER_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`USER_ID`,`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PRODUCT_ASESSOR_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCT_MICROCREDIT`
--

CREATE TABLE IF NOT EXISTS `PRODUCT_MICROCREDIT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  `CURRENCY_ID` varchar(3) NOT NULL,
  `MIN_AMOUNT` decimal(19,6) NOT NULL,
  `MAX_AMOUNT` decimal(19,6) NOT NULL,
  `MIN_PERIOD` bigint(20) NOT NULL,
  `MAX_PERIOD` bigint(20) NOT NULL,
  `RATE` decimal(19,6) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`PRODUCT_ID`),
  KEY `PRODUCT_MICROCREDIT_ID_FK` (`PRODUCT_ID`),
  KEY `PRODUCT_MIC_CURRENCY_ID_FK` (`CURRENCY_ID`),
  KEY `PRODU_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PRODUCT_MICROCREDIT`
--

INSERT INTO `PRODUCT_MICROCREDIT` (`COMPANY_ID`, `LANGUAGE_ID`, `EXPIRED`, `PRODUCT_ID`, `CREATED`, `DESCRIPTION`, `CURRENCY_ID`, `MIN_AMOUNT`, `MAX_AMOUNT`, `MIN_PERIOD`, `MAX_PERIOD`, `RATE`) VALUES
('MXT', 'ES', '9999-12-31 00:00:00', 'M01', '2012-03-14 00:01:31', 'MICROCREDITO DE PRUEBA', 'USD', 10.000000, 500.000000, 7, 620, 10.000000),
('MXT', 'ES', '9999-12-31 00:00:00', 'M02', '2012-03-14 00:01:31', 'MICROCREDITO SOLIDARIO', 'USD', 10.000000, 50.000000, 7, 60, 9.450000),
('MXT', 'ES', '9999-12-31 00:00:00', 'M03', '2012-03-14 00:01:31', 'MICROCREDITO GANADERO', 'USD', 50.000000, 150.000000, 30, 360, 10.650000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCT_MICROCREDIT_ID`
--

CREATE TABLE IF NOT EXISTS `PRODUCT_MICROCREDIT_ID` (
  `PRODUCT_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PRODUCT_MICROCREDIT_ID`
--

INSERT INTO `PRODUCT_MICROCREDIT_ID` (`PRODUCT_ID`) VALUES
('M01'),
('M02'),
('M03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFESSION_TYPE`
--

CREATE TABLE IF NOT EXISTS `PROFESSION_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PROFESSION_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PROFESSION_TYPE_ID`),
  KEY `PROFESSION_TYPE_ID_FK` (`PROFESSION_TYPE_ID`),
  KEY `PROFESSION_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROFESSION_TYPE`
--

INSERT INTO `PROFESSION_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `PROFESSION_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', '1', 'ABOGADO'),
('MXT', 'ES', '10', 'AMA DE CASA'),
('MXT', 'ES', '100', 'PUBLICISTA'),
('MXT', 'ES', '101', 'QUIMICO'),
('MXT', 'ES', '102', 'RECEPCIONISTA'),
('MXT', 'ES', '103', 'RELACIONISTA PUBLICO'),
('MXT', 'ES', '104', 'RELOJERO'),
('MXT', 'ES', '105', 'REPORTERO'),
('MXT', 'ES', '106', 'REPOSTERO'),
('MXT', 'ES', '107', 'RETIRADO'),
('MXT', 'ES', '108', 'SACERDOTE'),
('MXT', 'ES', '109', 'SECRETARIA'),
('MXT', 'ES', '11', 'AMA DE LLAVES'),
('MXT', 'ES', '110', 'SOLDADOR'),
('MXT', 'ES', '111', 'SUPERVISOR'),
('MXT', 'ES', '112', 'TECNICO'),
('MXT', 'ES', '113', 'TECNOLOGO'),
('MXT', 'ES', '114', 'VENDEDOR BILLETES DE LOTERIA'),
('MXT', 'ES', '115', 'VETERINARIO'),
('MXT', 'ES', '116', 'ZAPATERO'),
('MXT', 'ES', '12', 'ANALISTA DE SISTEMAS'),
('MXT', 'ES', '13', 'ARQUEOLOGO'),
('MXT', 'ES', '14', 'ARQUITECTO'),
('MXT', 'ES', '15', 'ARTESANO'),
('MXT', 'ES', '16', 'ARTISTA COMERCIAL'),
('MXT', 'ES', '17', 'ARTISTA GRAFICO'),
('MXT', 'ES', '18', 'ASISTENTE ADMINISTRATIVO'),
('MXT', 'ES', '19', 'ASISTENTE DE GERENTE'),
('MXT', 'ES', '2', 'ACTOR/ACTRIZ DRAMATICO'),
('MXT', 'ES', '20', 'ASISTENTE DE MAESTRO'),
('MXT', 'ES', '21', 'ASISTENTE DE PROTECCION AMBIENTAL'),
('MXT', 'ES', '22', 'ASISTENTE DE SEGURIDAD'),
('MXT', 'ES', '23', 'AYUDANTE GENERAL'),
('MXT', 'ES', '24', 'BARBERO'),
('MXT', 'ES', '25', 'BENEFICIARIO DE SEGURO SOCIAL'),
('MXT', 'ES', '26', 'BIENES RAICES'),
('MXT', 'ES', '27', 'BOMBERO'),
('MXT', 'ES', '28', 'CAJERO'),
('MXT', 'ES', '29', 'CAPATAZ'),
('MXT', 'ES', '3', 'ADMINISTRADOR'),
('MXT', 'ES', '30', 'CARNICERO'),
('MXT', 'ES', '31', 'CARPINTERO'),
('MXT', 'ES', '32', 'CARTERO'),
('MXT', 'ES', '33', 'CHOFER'),
('MXT', 'ES', '34', 'COBRADOR'),
('MXT', 'ES', '35', 'COMERCIANTE'),
('MXT', 'ES', '36', 'CONSERJE'),
('MXT', 'ES', '37', 'CONTADOR'),
('MXT', 'ES', '38', 'COSTURERA'),
('MXT', 'ES', '39', 'DENTISTA'),
('MXT', 'ES', '4', 'AGENTE DE RENTAS INTERNAS'),
('MXT', 'ES', '40', 'DESEMPLEADO'),
('MXT', 'ES', '41', 'DINAMITERO'),
('MXT', 'ES', '42', 'DIRECTOR ESCOLAR'),
('MXT', 'ES', '43', 'DISEÑADOR'),
('MXT', 'ES', '44', 'DUEÑO TALLER'),
('MXT', 'ES', '45', 'DUEÑO TALLER REJAS'),
('MXT', 'ES', '46', 'DUEÑO TIENDA'),
('MXT', 'ES', '47', 'DUEÑO COMPANIA CONSTRUCCION'),
('MXT', 'ES', '48', 'DUEÑO DE RESTAURANTE'),
('MXT', 'ES', '49', 'DUEÑO RESTAURANTE MOBIL'),
('MXT', 'ES', '5', 'AGENTE DE SEGUROS'),
('MXT', 'ES', '50', 'DUEÑO SUPERMERCADO'),
('MXT', 'ES', '51', 'EBANISTA'),
('MXT', 'ES', '52', 'ECONOMISTA'),
('MXT', 'ES', '53', 'ELECTRICISTA'),
('MXT', 'ES', '54', 'EMPACADOR'),
('MXT', 'ES', '55', 'EMPLEADO DE ALMACEN'),
('MXT', 'ES', '56', 'EMPLEADO DE CONSTRUCCION'),
('MXT', 'ES', '57', 'EMPLEADO DE MANTENIMIENTO'),
('MXT', 'ES', '58', 'EMPLEADO DE MUEBLERIA'),
('MXT', 'ES', '59', 'EMPLEADO DE OBRAS PUBLICAS'),
('MXT', 'ES', '6', 'AGENTE DE VIAJES'),
('MXT', 'ES', '60', 'EMPLEADO DE RESTAURANTE'),
('MXT', 'ES', '61', 'EMPLEADO DE SUPERMERCADO'),
('MXT', 'ES', '62', 'EMPLEADO DOMESTICO'),
('MXT', 'ES', '63', 'ENCARGADA DE COMEDOR ESCOLAR'),
('MXT', 'ES', '64', 'ENFERMERA'),
('MXT', 'ES', '65', 'ESCULTOR'),
('MXT', 'ES', '66', 'ESTUDIANTE'),
('MXT', 'ES', '67', 'EXCAVADOR'),
('MXT', 'ES', '68', 'FARMACEUTICO'),
('MXT', 'ES', '69', 'FERRETERIA'),
('MXT', 'ES', '7', 'AGRICULTOR'),
('MXT', 'ES', '70', 'FUMIGADOR'),
('MXT', 'ES', '71', 'GUARDA BOSQUES'),
('MXT', 'ES', '72', 'GUARDIA DE SEGURIDAD'),
('MXT', 'ES', '73', 'GUARDIA ESCOLAR'),
('MXT', 'ES', '74', 'HIDROLOGO'),
('MXT', 'ES', '75', 'HOJALATERO'),
('MXT', 'ES', '76', 'INCAPACITADO'),
('MXT', 'ES', '77', 'INGENIERO'),
('MXT', 'ES', '78', 'INSTALADOR'),
('MXT', 'ES', '79', 'JARDINERO'),
('MXT', 'ES', '8', 'AGRONOMO'),
('MXT', 'ES', '80', 'LAVAPLATOS'),
('MXT', 'ES', '81', 'MAESTRO DE ESCUELA'),
('MXT', 'ES', '82', 'MATEMATICO'),
('MXT', 'ES', '83', 'MECANICO DE AUTOS'),
('MXT', 'ES', '84', 'MECANICO INDUSTRIAL'),
('MXT', 'ES', '85', 'MEDICO'),
('MXT', 'ES', '86', 'MENOR DE EDAD'),
('MXT', 'ES', '87', 'MENSAJERO'),
('MXT', 'ES', '88', 'MODISTA'),
('MXT', 'ES', '89', 'MOZO'),
('MXT', 'ES', '9', 'ALBAÑIL'),
('MXT', 'ES', '90', 'MUSICO'),
('MXT', 'ES', '91', 'OFICINISTA'),
('MXT', 'ES', '92', 'OPERADOR DE MAQUINARIA'),
('MXT', 'ES', '93', 'OPERADOR EQUIPO PESADO'),
('MXT', 'ES', '94', 'PESCADOR'),
('MXT', 'ES', '95', 'PINTOR'),
('MXT', 'ES', '96', 'PLOMERO'),
('MXT', 'ES', '97', 'PROFESOR UNIVERSITARIO'),
('MXT', 'ES', '98', 'PROGRAMADOR DE COMPUTADORAS'),
('MXT', 'ES', '99', 'PSICOLOGO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFESSION_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `PROFESSION_TYPE_ID` (
  `PROFESSION_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`PROFESSION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROFESSION_TYPE_ID`
--

INSERT INTO `PROFESSION_TYPE_ID` (`PROFESSION_TYPE_ID`) VALUES
('1'),
('10'),
('100'),
('101'),
('102'),
('103'),
('104'),
('105'),
('106'),
('107'),
('108'),
('109'),
('11'),
('110'),
('111'),
('112'),
('113'),
('114'),
('115'),
('116'),
('12'),
('13'),
('14'),
('15'),
('16'),
('17'),
('18'),
('19'),
('2'),
('20'),
('21'),
('22'),
('23'),
('24'),
('25'),
('26'),
('27'),
('28'),
('29'),
('3'),
('30'),
('31'),
('32'),
('33'),
('34'),
('35'),
('36'),
('37'),
('38'),
('39'),
('4'),
('40'),
('41'),
('42'),
('43'),
('44'),
('45'),
('46'),
('47'),
('48'),
('49'),
('5'),
('50'),
('51'),
('52'),
('53'),
('54'),
('55'),
('56'),
('57'),
('58'),
('59'),
('6'),
('60'),
('61'),
('62'),
('63'),
('64'),
('65'),
('66'),
('67'),
('68'),
('69'),
('7'),
('70'),
('71'),
('72'),
('73'),
('74'),
('75'),
('76'),
('77'),
('78'),
('79'),
('8'),
('80'),
('81'),
('82'),
('83'),
('84'),
('85'),
('86'),
('87'),
('88'),
('89'),
('9'),
('90'),
('91'),
('92'),
('93'),
('94'),
('95'),
('96'),
('97'),
('98'),
('99');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFILE`
--

CREATE TABLE IF NOT EXISTS `PROFILE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PROFILE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `DESCRIPTION` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PROFILE_ID`),
  KEY `PROFILE_ID_FK` (`PROFILE_ID`),
  KEY `PROFILE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROFILE`
--

INSERT INTO `PROFILE` (`COMPANY_ID`, `LANGUAGE_ID`, `PROFILE_ID`, `NAME`, `DESCRIPTION`) VALUES
('MXT', 'ES', 'ADM', 'ADMINISTRADOR', 'ADMINISTRADOR DEL SISTEMA'),
('MXT', 'ES', 'ASE', 'ASESOR', 'ASESOR DE MICROCRÉDITO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFILE_ID`
--

CREATE TABLE IF NOT EXISTS `PROFILE_ID` (
  `PROFILE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`PROFILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROFILE_ID`
--

INSERT INTO `PROFILE_ID` (`PROFILE_ID`) VALUES
('ADM'),
('ASE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROVINCE`
--

CREATE TABLE IF NOT EXISTS `PROVINCE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`),
  KEY `PROVINCE_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`),
  KEY `PROVINCE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROVINCE`
--

INSERT INTO `PROVINCE` (`COMPANY_ID`, `LANGUAGE_ID`, `COUNTRY_ID`, `PROVINCE_ID`, `NAME`) VALUES
('MXT', 'ES', 'EC', 'AZ', 'AZUAY'),
('MXT', 'ES', 'EC', 'BO', 'BOLIVAR'),
('MXT', 'ES', 'EC', 'CA', 'CARCHI'),
('MXT', 'ES', 'EC', 'CH', 'CHIMBORAZO'),
('MXT', 'ES', 'EC', 'CO', 'COTOPAXI'),
('MXT', 'ES', 'EC', 'CR', 'CAÑAR'),
('MXT', 'ES', 'EC', 'EL', 'EL ORO'),
('MXT', 'ES', 'EC', 'ES', 'ESMERALDAS'),
('MXT', 'ES', 'EC', 'GA', 'GALAPAGOS'),
('MXT', 'ES', 'EC', 'GU', 'GUAYAS'),
('MXT', 'ES', 'EC', 'IM', 'IMBABURA'),
('MXT', 'ES', 'EC', 'LO', 'LOJA'),
('MXT', 'ES', 'EC', 'MA', 'MANABÍ'),
('MXT', 'ES', 'EC', 'MO', 'MORONA SANTIAGO'),
('MXT', 'ES', 'EC', 'NA', 'NAPO'),
('MXT', 'ES', 'EC', 'OR', 'ORELLANA'),
('MXT', 'ES', 'EC', 'PA', 'PASTAZA'),
('MXT', 'ES', 'EC', 'PI', 'PICHINCHA'),
('MXT', 'ES', 'EC', 'RI', 'LOS RIOS'),
('MXT', 'ES', 'EC', 'SU', 'SUCUMBIOS'),
('MXT', 'ES', 'EC', 'TU', 'TUNGURAHUA'),
('MXT', 'ES', 'EC', 'ZA', 'ZAMORA CHINCHIPE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROVINCE_ID`
--

CREATE TABLE IF NOT EXISTS `PROVINCE_ID` (
  `COUNTRY_ID` varchar(2) NOT NULL,
  `PROVINCE_ID` varchar(2) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`,`PROVINCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROVINCE_ID`
--

INSERT INTO `PROVINCE_ID` (`COUNTRY_ID`, `PROVINCE_ID`) VALUES
('EC', 'AZ'),
('EC', 'BO'),
('EC', 'CA'),
('EC', 'CH'),
('EC', 'CO'),
('EC', 'CR'),
('EC', 'EL'),
('EC', 'ES'),
('EC', 'GA'),
('EC', 'GU'),
('EC', 'IM'),
('EC', 'LO'),
('EC', 'MA'),
('EC', 'MO'),
('EC', 'NA'),
('EC', 'OR'),
('EC', 'PA'),
('EC', 'PI'),
('EC', 'RI'),
('EC', 'SU'),
('EC', 'TU'),
('EC', 'ZA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `QUOTA_TYPE`
--

CREATE TABLE IF NOT EXISTS `QUOTA_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `QUOTA_TYPE_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`QUOTA_TYPE_ID`),
  KEY `QUOTA_TYPE_ID_FK` (`QUOTA_TYPE_ID`),
  KEY `QUOTA_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `QUOTA_TYPE`
--

INSERT INTO `QUOTA_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `QUOTA_TYPE_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', 'AMR', 'AMORTIZACIÓN GRADUAL'),
('MXT', 'ES', 'MNL', 'MANUAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `QUOTA_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `QUOTA_TYPE_ID` (
  `QUOTA_TYPE_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`QUOTA_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `QUOTA_TYPE_ID`
--

INSERT INTO `QUOTA_TYPE_ID` (`QUOTA_TYPE_ID`) VALUES
('AMR'),
('MNL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RECOMMENDATION`
--

CREATE TABLE IF NOT EXISTS `RECOMMENDATION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SOLICITUDE_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DOCUMENTS` varchar(500) NOT NULL,
  `ECONOMIC_UNIT` varchar(500) NOT NULL,
  `FAMILY_UNIT` varchar(500) NOT NULL,
  `PAYMENT_MORALE` varchar(500) NOT NULL,
  `CREDIT_HISTORY` varchar(500) NOT NULL,
  `PROPOSAL` varchar(25) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`SOLICITUDE_ID`),
  KEY `RECOMMENDATION_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `RECOMMENDATION_SOLICITUDE_ID` (`SOLICITUDE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RECOMMENDATION`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RECOMMENDATION_ID`
--

CREATE TABLE IF NOT EXISTS `RECOMMENDATION_ID` (
  `SOLICITUDE_ID` int(11) NOT NULL,
  PRIMARY KEY (`SOLICITUDE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RECOMMENDATION_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESPONSABILITY`
--

CREATE TABLE IF NOT EXISTS `RESPONSABILITY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `RESPONSABILITY_ID` varchar(3) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`RESPONSABILITY_ID`),
  KEY `RESPONSABILITY_ID_FK` (`RESPONSABILITY_ID`),
  KEY `RESPONSABILITY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RESPONSABILITY`
--

INSERT INTO `RESPONSABILITY` (`COMPANY_ID`, `LANGUAGE_ID`, `EXPIRED`, `RESPONSABILITY_ID`, `CREATED`, `NAME`, `DESCRIPTION`) VALUES
('MXT', 'ES', '9999-12-31 00:00:00', '1', '2012-01-13 00:00:00', 'PRESIDENTE(A)', 'PRESIDENTE DEL GRUPO'),
('MXT', 'ES', '9999-12-31 00:00:00', '2', '2012-01-13 00:00:00', 'SECRETARIO(A)', 'SECRETARI(A) DEL GRUPO'),
('MXT', 'ES', '9999-12-31 00:00:00', '3', '2012-01-13 00:00:00', 'INTEGRANTE', 'MIEMBRO DEL GRUPO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESPONSABILITY_ID`
--

CREATE TABLE IF NOT EXISTS `RESPONSABILITY_ID` (
  `RESPONSABILITY_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`RESPONSABILITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RESPONSABILITY_ID`
--

INSERT INTO `RESPONSABILITY_ID` (`RESPONSABILITY_ID`) VALUES
('1'),
('2'),
('3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESPONSE`
--

CREATE TABLE IF NOT EXISTS `RESPONSE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `RESPONSE_ID` varchar(8) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`RESPONSE_ID`),
  KEY `RESPONSE_ID_FK` (`RESPONSE_ID`),
  KEY `RESPONSE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RESPONSE`
--

INSERT INTO `RESPONSE` (`COMPANY_ID`, `LANGUAGE_ID`, `RESPONSE_ID`, `DESCRIPTION`) VALUES
('ALL', 'EN', 'FAILED', 'PROCESS FAILED'),
('ALL', 'EN', 'SUCCESS', 'PROCESS SUCCESSFUL'),
('ALL', 'ES', 'FAILED', 'PROCESO FALLIDO'),
('ALL', 'ES', 'SUCCESS', 'PROCESO EXITOSO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESPONSE_ID`
--

CREATE TABLE IF NOT EXISTS `RESPONSE_ID` (
  `RESPONSE_ID` varchar(8) NOT NULL,
  PRIMARY KEY (`RESPONSE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RESPONSE_ID`
--

INSERT INTO `RESPONSE_ID` (`RESPONSE_ID`) VALUES
('FAILED'),
('SUCCESS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ROLE`
--

CREATE TABLE IF NOT EXISTS `ROLE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PROFILE_ID` varchar(4) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DAY_ID` varchar(3) DEFAULT NULL,
  `HOUR_FROM` varchar(4) DEFAULT NULL,
  `HOUR_TO` varchar(4) DEFAULT NULL,
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PROFILE_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `ROLE_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ROLE`
--

INSERT INTO `ROLE` (`COMPANY_ID`, `EXPIRED`, `PROFILE_ID`, `SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`, `CREATED`, `DAY_ID`, `HOUR_FROM`, `HOUR_TO`, `EDITABLE`) VALUES
('MXT', '9999-12-31 00:00:00', 'ASE', 'A', '0', '01', '2012-03-28 11:54:10', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'A', '0', '02', '2012-03-28 11:54:10', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'A', '2', '05', '2012-03-28 11:54:10', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'B', '1', '01', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'B', '1', '02', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'B', '1', '03', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '2', '01', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '2', '02', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '3', '01', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '3', '02', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '4', '01', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '4', '02', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '5', '02', '2012-03-28 12:03:06', NULL, NULL, NULL, 0),
('MXT', '9999-12-31 00:00:00', 'ASE', 'C', '5', '03', '2012-03-28 12:03:06', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SEQUENTIAL`
--

CREATE TABLE IF NOT EXISTS `SEQUENTIAL` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `SEQUENTIAL_ID` varchar(40) NOT NULL,
  `SEQUENTIAL_VALUE` int(11) NOT NULL DEFAULT '0',
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`SEQUENTIAL_ID`),
  KEY `SEQUENTIAL_ID_FK` (`SEQUENTIAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SEQUENTIAL`
--

INSERT INTO `SEQUENTIAL` (`COMPANY_ID`, `SEQUENTIAL_ID`, `SEQUENTIAL_VALUE`, `VERSION`) VALUES
('MXT', 'GEOZONE', 1, 0),
('MXT', 'PARTNER', 1, 0),
('MXT', 'PARTNERGRP', 1, 0),
('MXT', 'PERSON', 3, 0),
('MXT', 'SOLICITUDE', 1000, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SEQUENTIAL_ID`
--

CREATE TABLE IF NOT EXISTS `SEQUENTIAL_ID` (
  `SEQUENTIAL_ID` varchar(40) NOT NULL,
  PRIMARY KEY (`SEQUENTIAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SEQUENTIAL_ID`
--

INSERT INTO `SEQUENTIAL_ID` (`SEQUENTIAL_ID`) VALUES
('GEOZONE'),
('PARTNER'),
('PARTNERGRP'),
('PERSON'),
('SOLICITUDE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOLICITUDE`
--

CREATE TABLE IF NOT EXISTS `SOLICITUDE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SOLICITUDE_ID` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `ACCOUNT` varchar(25) DEFAULT NULL,
  `ASSESSOR` varchar(20) NOT NULL,
  `PARTNER_CLIENT_ID` int(11) DEFAULT NULL,
  `GROUP_CLIENT_ID` int(11) DEFAULT NULL,
  `SOLICITUDE_DATE` datetime NOT NULL,
  `APPROVAL_DATE` datetime DEFAULT NULL,
  `DISBURSEMENT_DATE` datetime DEFAULT NULL,
  `INSTRUMENTATION_DATE` datetime DEFAULT NULL,
  `EXPIRATION_DATE` datetime DEFAULT NULL,
  `INITIAL_PAY_DATE` datetime DEFAULT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  `STATUS_ID` varchar(3) NOT NULL,
  `OPERATIVE_CONDITION_ID` varchar(3) DEFAULT NULL,
  `NUMBER_RENEWAL` int(11) NOT NULL,
  `AMOUNT` decimal(19,6) NOT NULL,
  `TERM` bigint(20) NOT NULL,
  `QUOTA_TYPE_ID` varchar(3) NOT NULL,
  `NUMBER_QUOTAS` int(11) DEFAULT NULL,
  `PAYMENT_FREQUENCY_ID` varchar(3) NOT NULL,
  `FUNDS_DESTINATION_ID` varchar(3) NOT NULL,
  `DESTINATION_DESCRIPTION` varchar(500) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`SOLICITUDE_ID`),
  KEY `SOLICITUDE_FUNDS_DEST_ID_FK` (`FUNDS_DESTINATION_ID`),
  KEY `SOLICITUDE_GROUP_CLIENT_ID_FK` (`GROUP_CLIENT_ID`),
  KEY `SOLICITUDE_ID_FK` (`SOLICITUDE_ID`),
  KEY `SOLICITUDE_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `SOLICITUDE_OPE_COND_ID_FK` (`OPERATIVE_CONDITION_ID`),
  KEY `SOLICITUDE_PARTNER_CLIE_ID_FK` (`PARTNER_CLIENT_ID`),
  KEY `SOLICITUDE_PAY_FREQ_ID_FK` (`PAYMENT_FREQUENCY_ID`),
  KEY `SOLICITUDE_PRODUCT_ID_FK` (`PRODUCT_ID`),
  KEY `SOLICITUDE_QUOTA_TYPE_ID_FK` (`QUOTA_TYPE_ID`),
  KEY `SOLICITUDE_SOL_STATUS_ID_FK` (`STATUS_ID`),
  KEY `SOLICITUDE_USER_ACCOUNT_ID_FK` (`ASSESSOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SOLICITUDE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOLICITUDE_ID`
--

CREATE TABLE IF NOT EXISTS `SOLICITUDE_ID` (
  `SOLICITUDE_ID` int(11) NOT NULL,
  PRIMARY KEY (`SOLICITUDE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SOLICITUDE_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOLICITUDE_STATUS`
--

CREATE TABLE IF NOT EXISTS `SOLICITUDE_STATUS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `STATUS_ID` varchar(3) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`STATUS_ID`),
  KEY `SOLICITUDE_STATUS_ID_FK` (`STATUS_ID`),
  KEY `SOLICITUDE_STATUS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SOLICITUDE_STATUS`
--

INSERT INTO `SOLICITUDE_STATUS` (`COMPANY_ID`, `LANGUAGE_ID`, `STATUS_ID`, `DESCRIPTION`) VALUES
('MXT', 'ES', '001', 'SOLICITADA'),
('MXT', 'ES', '002', 'APROVADA'),
('MXT', 'ES', '003', 'DENEGADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOLICITUDE_STATUS_ID`
--

CREATE TABLE IF NOT EXISTS `SOLICITUDE_STATUS_ID` (
  `STATUS_ID` varchar(3) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SOLICITUDE_STATUS_ID`
--

INSERT INTO `SOLICITUDE_STATUS_ID` (`STATUS_ID`) VALUES
('001'),
('002'),
('003');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SUBSYSTEM`
--

CREATE TABLE IF NOT EXISTS `SUBSYSTEM` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`SUBSYSTEM_ID`),
  KEY `SUBSYSTEM_ID_FK` (`SUBSYSTEM_ID`),
  KEY `SUBSYSTEM_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SUBSYSTEM`
--

INSERT INTO `SUBSYSTEM` (`COMPANY_ID`, `LANGUAGE_ID`, `SUBSYSTEM_ID`, `NAME`) VALUES
('MXT', 'ES', 'A', 'SEGURIDADES'),
('MXT', 'ES', 'B', 'PERSONAS'),
('MXT', 'ES', 'C', 'MICROCRÉDITO'),
('MXT', 'ES', 'G', 'GENERALES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SUBSYSTEM_ID`
--

CREATE TABLE IF NOT EXISTS `SUBSYSTEM_ID` (
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  PRIMARY KEY (`SUBSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SUBSYSTEM_ID`
--

INSERT INTO `SUBSYSTEM_ID` (`SUBSYSTEM_ID`) VALUES
('A'),
('B'),
('C'),
('G');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_ACCESS`
--

CREATE TABLE IF NOT EXISTS `USER_ACCESS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATED` datetime NOT NULL,
  `USER_KEY` varchar(300) NOT NULL,
  `LAST_CHANGE` datetime NOT NULL,
  `QUESTION` varchar(100) DEFAULT NULL,
  `ANSWER` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`),
  KEY `USER_ACCESS_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_ACCESS`
--

INSERT INTO `USER_ACCESS` (`COMPANY_ID`, `EXPIRED`, `USER_ID`, `CREATED`, `USER_KEY`, `LAST_CHANGE`, `QUESTION`, `ANSWER`) VALUES
('MXT', '9999-12-31 00:00:00', 'ADMIN', '2012-03-14 00:00:00', 'b34d7dbecec802e49a6cdb0d0a9334a5', '2012-03-14 00:00:00', NULL, NULL),
('MXT', '9999-12-31 00:00:00', 'JPEREZ', '2012-03-14 00:00:00', 'b34d7dbecec802e49a6cdb0d0a9334a5', '2012-03-14 00:00:00', NULL, NULL),
('MXT', '9999-12-31 00:00:00', 'MLOPEZ', '2012-03-14 00:00:00', 'b34d7dbecec802e49a6cdb0d0a9334a5', '2012-03-14 00:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_ACCOUNT`
--

CREATE TABLE IF NOT EXISTS `USER_ACCOUNT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `USER_TYPE_ID` varchar(4) NOT NULL,
  `USER_STATUS_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(4) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `PERSON_ID` int(11) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`),
  KEY `USER_ACCOUNT_ID_FK` (`USER_ID`),
  KEY `USER_ACCOUNT_PERSON_ID_FK` (`PERSON_ID`),
  KEY `USER_ACCOUNT_USER_STATUS_ID_FK` (`USER_STATUS_ID`),
  KEY `USER_ACCOUNT_USER_TYPE_ID_FK` (`USER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_ACCOUNT`
--

INSERT INTO `USER_ACCOUNT` (`COMPANY_ID`, `EXPIRED`, `USER_ID`, `CREATED`, `NAME`, `USER_TYPE_ID`, `USER_STATUS_ID`, `LANGUAGE_ID`, `EMAIL`, `PERSON_ID`, `VERSION`) VALUES
('MXT', '9999-12-31 00:00:00', 'ADMIN', '2012-01-20 20:46:12', 'ADMINISTRATOR', 'SYS', 'ACT', 'ES', 'admin@mobile.com', NULL, 1),
('MXT', '9999-12-31 00:00:00', 'JPEREZ', '2012-03-13 23:43:15', 'JUAN PEREZ', 'ASE', 'ACT', 'ES', 'jperez@mobile.com', NULL, 1),
('MXT', '9999-12-31 00:00:00', 'MLOPEZ', '2012-04-01 13:39:54', 'MARÍA LOPEZ', 'ASE', 'ACT', 'ES', 'mlopez@mobile.com', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_ACCOUNT_ID`
--

CREATE TABLE IF NOT EXISTS `USER_ACCOUNT_ID` (
  `USER_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_ACCOUNT_ID`
--

INSERT INTO `USER_ACCOUNT_ID` (`USER_ID`) VALUES
('ADMIN'),
('JPEREZ'),
('MLOPEZ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_PROFILE`
--

CREATE TABLE IF NOT EXISTS `USER_PROFILE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `PROFILE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`,`PROFILE_ID`),
  KEY `USER_PROF_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_PROFILE`
--

INSERT INTO `USER_PROFILE` (`COMPANY_ID`, `EXPIRED`, `USER_ID`, `PROFILE_ID`, `CREATED`) VALUES
('MXT', '9999-12-31 00:00:00', 'ADMIN', 'ADM', '2012-03-18 00:00:00'),
('MXT', '9999-12-31 00:00:00', 'ADMIN', 'ASE', '2012-03-18 00:00:00'),
('MXT', '9999-12-31 00:00:00', 'JPEREZ', 'ASE', '2012-03-18 00:00:00'),
('MXT', '9999-12-31 00:00:00', 'MLOPEZ', 'ASE', '2012-03-18 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_SESSION`
--

CREATE TABLE IF NOT EXISTS `USER_SESSION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `HOST_ID` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SESSION_ID` varchar(100) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`,`HOST_ID`),
  KEY `USER_SESSION_HOST_ID_FK` (`HOST_ID`),
  KEY `USER_SESS_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_SESSION`
--

INSERT INTO `USER_SESSION` (`COMPANY_ID`, `EXPIRED`, `USER_ID`, `HOST_ID`, `CREATED`, `SESSION_ID`, `VERSION`) VALUES
('MXT', '2012-04-01 13:58:28', 'ADMIN', 'LOCALHOST', '2012-04-01 13:26:00', 'FCD708B8E428C1FB7B04EAF180EBF32320120401132559987', 1),
('MXT', '2012-04-01 15:49:16', 'ADMIN', 'LOCALHOST', '2012-04-01 15:34:34', 'FCD708B8E428C1FB7B04EAF180EBF32320120401153434222', 1),
('MXT', '9999-12-31 00:00:00', 'ADMIN', 'LOCALHOST', '2012-04-01 15:49:56', 'FCD708B8E428C1FB7B04EAF180EBF32320120401154956349', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_STATUS`
--

CREATE TABLE IF NOT EXISTS `USER_STATUS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_STATUS_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`USER_STATUS_ID`),
  KEY `USER_STATUS_ID_FK` (`USER_STATUS_ID`),
  KEY `USER_STATUS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_STATUS`
--

INSERT INTO `USER_STATUS` (`COMPANY_ID`, `LANGUAGE_ID`, `USER_STATUS_ID`, `NAME`) VALUES
('MXT', 'ES', 'ACT', 'ACTIVO'),
('MXT', 'ES', 'BLO', 'BLOQUEADO'),
('MXT', 'ES', 'INA', 'INACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_STATUS_ID`
--

CREATE TABLE IF NOT EXISTS `USER_STATUS_ID` (
  `USER_STATUS_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`USER_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_STATUS_ID`
--

INSERT INTO `USER_STATUS_ID` (`USER_STATUS_ID`) VALUES
('ACT'),
('BLO'),
('INA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_TYPE`
--

CREATE TABLE IF NOT EXISTS `USER_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`USER_TYPE_ID`),
  KEY `USER_TYPE_ID_FK` (`USER_TYPE_ID`),
  KEY `USER_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_TYPE`
--

INSERT INTO `USER_TYPE` (`COMPANY_ID`, `LANGUAGE_ID`, `USER_TYPE_ID`, `NAME`) VALUES
('MXT', 'ES', 'ADM', 'ADMINISTRADOR'),
('MXT', 'ES', 'ASE', 'ASESOR CRÉDITO'),
('MXT', 'ES', 'OPE', 'OPERADOR'),
('MXT', 'ES', 'SYS', 'SISTEMAS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `USER_TYPE_ID` (
  `USER_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`USER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_TYPE_ID`
--

INSERT INTO `USER_TYPE_ID` (`USER_TYPE_ID`) VALUES
('ADM'),
('ASE'),
('OPE'),
('SYS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ZONE_ASESSOR`
--

CREATE TABLE IF NOT EXISTS `ZONE_ASESSOR` (
  `USER_ID` varchar(20) NOT NULL,
  `GEOGRAPHIC_ZONE_ID` int(11) NOT NULL,
  `OBSERVATIONS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`GEOGRAPHIC_ZONE_ID`),
  KEY `ZONE_ASE_GEO_ZONE_ID_FK` (`GEOGRAPHIC_ZONE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ZONE_ASESSOR`
--


--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `ACCOUNT_STATUS`
--
ALTER TABLE `ACCOUNT_STATUS`
  ADD CONSTRAINT `ACCOUNT_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ACCOUNT_STATUS_ID_FK` FOREIGN KEY (`STATUS_ID`) REFERENCES `ACCOUNT_STATUS_ID` (`STATUS_ID`),
  ADD CONSTRAINT `ACCOUNT_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `ADDRESS_TYPE`
--
ALTER TABLE `ADDRESS_TYPE`
  ADD CONSTRAINT `ADDRESS_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ADDRESS_TYPE_ID_FK` FOREIGN KEY (`ADDRESS_TYPE_ID`) REFERENCES `ADDRESS_TYPE_ID` (`ADDRESS_TYPE_ID`),
  ADD CONSTRAINT `ADDRESS_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `CITY`
--
ALTER TABLE `CITY`
  ADD CONSTRAINT `CITY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`),
  ADD CONSTRAINT `CITY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `CITY_ID`
--
ALTER TABLE `CITY_ID`
  ADD CONSTRAINT `CITY_ID_PROVINCE_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`) REFERENCES `PROVINCE_ID` (`COUNTRY_ID`, `PROVINCE_ID`);

--
-- Filtros para la tabla `CIVIL_STATUS`
--
ALTER TABLE `CIVIL_STATUS`
  ADD CONSTRAINT `CIVIL_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `CIVIL_STATUS_ID_FK` FOREIGN KEY (`CIVIL_STATUS_ID`) REFERENCES `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`),
  ADD CONSTRAINT `CIVIL_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `COMPONENT`
--
ALTER TABLE `COMPONENT`
  ADD CONSTRAINT `COMPONENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `COMPONENT_COM_TYPE_ID_FK` FOREIGN KEY (`TYPE_ID`) REFERENCES `COMPONENT_TYPE` (`COMPONENT_TYPE_ID`),
  ADD CONSTRAINT `COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`, `TYPE_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`, `TYPE_ID`),
  ADD CONSTRAINT `COMPONENT_SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`);

--
-- Filtros para la tabla `COUNTRY`
--
ALTER TABLE `COUNTRY`
  ADD CONSTRAINT `COUNTRY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `COUNTRY_ID_FK` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY_ID` (`COUNTRY_ID`),
  ADD CONSTRAINT `COUNTRY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `CURRENCY`
--
ALTER TABLE `CURRENCY`
  ADD CONSTRAINT `CURRENCY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `CURRENCY_ID_FK` FOREIGN KEY (`CURRENCY_ID`) REFERENCES `CURRENCY_ID` (`CURRENCY_ID`),
  ADD CONSTRAINT `CURRENCY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `DATABASE_TYPE`
--
ALTER TABLE `DATABASE_TYPE`
  ADD CONSTRAINT `DATABASE_TYPE_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`);

--
-- Filtros para la tabla `DATAFILE`
--
ALTER TABLE `DATAFILE`
  ADD CONSTRAINT `DATAFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DATAFILE_DATAFILE_TYPE_FK` FOREIGN KEY (`DATAFILE_TYPE_ID`) REFERENCES `DATAFILE_TYPE` (`DATAFILE_TYPE_ID`),
  ADD CONSTRAINT `DATAFILE_ID_FK` FOREIGN KEY (`DATAFILE_ID`) REFERENCES `DATAFILE_ID` (`DATAFILE_ID`);

--
-- Filtros para la tabla `DISTRICT`
--
ALTER TABLE `DISTRICT`
  ADD CONSTRAINT `DISTRICT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DISTRICT_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`) REFERENCES `DISTRICT_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`),
  ADD CONSTRAINT `DISTRICT_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `DISTRICT_ID`
--
ALTER TABLE `DISTRICT_ID`
  ADD CONSTRAINT `DISTRICT_ID_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`);

--
-- Filtros para la tabla `ENTITY_FIELD`
--
ALTER TABLE `ENTITY_FIELD`
  ADD CONSTRAINT `ENTITY_FIELD_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_ID_FK` FOREIGN KEY (`TABLE_ID`, `FIELD_ID`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_SEQUENTIAL_ID_FK` FOREIGN KEY (`SEQUENTIAL_ID`) REFERENCES `SEQUENTIAL_ID` (`SEQUENTIAL_ID`);

--
-- Filtros para la tabla `ENTITY_FIELD_ID`
--
ALTER TABLE `ENTITY_FIELD_ID`
  ADD CONSTRAINT `ENTITY_FIELD_ID_TABLE_ID_FK` FOREIGN KEY (`TABLE_ID`) REFERENCES `ENTITY_TABLE_ID` (`TABLE_ID`);

--
-- Filtros para la tabla `ENTITY_RELATIONSHIP`
--
ALTER TABLE `ENTITY_RELATIONSHIP`
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_FROM_FK` FOREIGN KEY (`TABLE_FROM`, `FIELD_FROM`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`),
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_TO_FK` FOREIGN KEY (`TABLE_TO`, `FIELD_TO`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`);

--
-- Filtros para la tabla `ENTITY_TABLE`
--
ALTER TABLE `ENTITY_TABLE`
  ADD CONSTRAINT `ENTITY_TABLE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ENTITY_TABLE_ID_FK` FOREIGN KEY (`TABLE_ID`) REFERENCES `ENTITY_TABLE_ID` (`TABLE_ID`);

--
-- Filtros para la tabla `FREQUENCY`
--
ALTER TABLE `FREQUENCY`
  ADD CONSTRAINT `FREQUENCY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `FREQUENCY_ID_FK` FOREIGN KEY (`FREQUENCY_ID`) REFERENCES `FREQUENCY_ID` (`FREQUENCY_ID`),
  ADD CONSTRAINT `FREQUENCY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `FUNDS_DESTINATION`
--
ALTER TABLE `FUNDS_DESTINATION`
  ADD CONSTRAINT `FUNDS_DESTINATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `FUNDS_DESTINATION_ID_FK` FOREIGN KEY (`FUNDS_DESTINATION_ID`) REFERENCES `FUNDS_DESTINATION_ID` (`FUNDS_DESTINATION_ID`),
  ADD CONSTRAINT `FUNDS_DESTINATION_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `GENDER_TYPE`
--
ALTER TABLE `GENDER_TYPE`
  ADD CONSTRAINT `GENDER_TYPE_ID_FK` FOREIGN KEY (`GENDER_TYPE_ID`) REFERENCES `GENDER_TYPE_ID` (`GENDER_TYPE_ID`),
  ADD CONSTRAINT `GENDER_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `GEOGRAPHIC_ZONE`
--
ALTER TABLE `GEOGRAPHIC_ZONE`
  ADD CONSTRAINT `GEOGRAPHIC_ZONE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `GEOGRAPHIC_ZONE_ID_FK` FOREIGN KEY (`GEOGRAPHIC_ZONE_ID`) REFERENCES `GEOGRAPHIC_ZONE_ID` (`GEOGRAPHIC_ZONE_ID`),
  ADD CONSTRAINT `GEOGRAPHIC_ZONE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `HOST`
--
ALTER TABLE `HOST`
  ADD CONSTRAINT `HOST_CHANNEL_ID_FK` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `CHANNEL` (`CHANNEL_ID`),
  ADD CONSTRAINT `HOST_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `HOST_ID_FK` FOREIGN KEY (`HOST_ID`) REFERENCES `HOST_ID` (`HOST_ID`);

--
-- Filtros para la tabla `IDENTIFICATION_TYPE`
--
ALTER TABLE `IDENTIFICATION_TYPE`
  ADD CONSTRAINT `IDENTIFICATION_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `IDENTIFICATION_TYPE_ID_FK` FOREIGN KEY (`IDENTIFICATION_TYPE_ID`) REFERENCES `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `IDENT_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `MICRO_ACCOUNT`
--
ALTER TABLE `MICRO_ACCOUNT`
  ADD CONSTRAINT `MICRO_ACCOUNT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MICRO_ACCOUNT_ID_FK` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `MICRO_ACCOUNT_ID` (`ACCOUNT_ID`),
  ADD CONSTRAINT `MIC_ACC_FREQ_ID_FK` FOREIGN KEY (`PAYMENT_FREQUENCY_ID`) REFERENCES `FREQUENCY_ID` (`FREQUENCY_ID`),
  ADD CONSTRAINT `MIC_ACC_MIC_ACC_ID_FK` FOREIGN KEY (`PREVIOUS_ACCOUNT`) REFERENCES `MICRO_ACCOUNT_ID` (`ACCOUNT_ID`),
  ADD CONSTRAINT `MIC_ACC_PARTNER_GRP_ID_FK` FOREIGN KEY (`GROUP_CLIENT_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `MIC_ACC_PARTNER_ID_FK` FOREIGN KEY (`PARTNER_CLIENT_ID`) REFERENCES `PARTNER_ID` (`PARTNER_ID`),
  ADD CONSTRAINT `MIC_ACC_PRODUCT_ID_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT_MICROCREDIT_ID` (`PRODUCT_ID`),
  ADD CONSTRAINT `MIC_ACC_QUOTA_TYPE_ID_FK` FOREIGN KEY (`QUOTA_TYPE_ID`) REFERENCES `QUOTA_TYPE_ID` (`QUOTA_TYPE_ID`),
  ADD CONSTRAINT `MIC_ACC_SOLICITUDE_ID_FK` FOREIGN KEY (`SOLICITUDE_ID`) REFERENCES `SOLICITUDE_ID` (`SOLICITUDE_ID`),
  ADD CONSTRAINT `MIC_ACC_STATUS_ID_FK` FOREIGN KEY (`STATUS_ID`) REFERENCES `ACCOUNT_STATUS_ID` (`STATUS_ID`),
  ADD CONSTRAINT `MIC_ACC_USER_ID_FK` FOREIGN KEY (`ASSESSOR`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `MICRO_ACCOUNT_QUOTA`
--
ALTER TABLE `MICRO_ACCOUNT_QUOTA`
  ADD CONSTRAINT `MICRO_ACCOUNT_QUOTA_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MICRO_ACCOUNT_QUOTA_ID_FK` FOREIGN KEY (`ACCOUNT_ID`, `SUBACCOUNT`) REFERENCES `MICRO_ACCOUNT_QUOTA_ID` (`ACCOUNT_ID`, `SUBACCOUNT`),
  ADD CONSTRAINT `MIC_ACC_QUO_ACC_ID_FK` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `MICRO_ACCOUNT_ID` (`ACCOUNT_ID`);

--
-- Filtros para la tabla `MODULE`
--
ALTER TABLE `MODULE`
  ADD CONSTRAINT `MODULE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MODULE_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`) REFERENCES `MODULE_ID` (`SUBSYSTEM_ID`, `MODULE_ID`),
  ADD CONSTRAINT `MODULE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `MODULE_ID`
--
ALTER TABLE `MODULE_ID`
  ADD CONSTRAINT `MODULE_ID_SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`);

--
-- Filtros para la tabla `OPERATIVE_CONDITION`
--
ALTER TABLE `OPERATIVE_CONDITION`
  ADD CONSTRAINT `OPERATIVE_CONDITION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `OPERATIVE_CONDITION_ID_FK` FOREIGN KEY (`OPERATIVE_CONDITION_ID`) REFERENCES `OPERATIVE_CONDITION_ID` (`OPERATIVE_CONDITION_ID`),
  ADD CONSTRAINT `OPERA_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PARAMETER`
--
ALTER TABLE `PARAMETER`
  ADD CONSTRAINT `PARAMETER_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PARAMETER_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`),
  ADD CONSTRAINT `PARAMETER_ID_FK` FOREIGN KEY (`PARAMETER_ID`) REFERENCES `PARAMETER_ID` (`PARAMETER_ID`);

--
-- Filtros para la tabla `PARTNER`
--
ALTER TABLE `PARTNER`
  ADD CONSTRAINT `PARTNER_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PARTNER_FREQ_ID_FK` FOREIGN KEY (`FREQUENCY_ID`) REFERENCES `FREQUENCY_ID` (`FREQUENCY_ID`),
  ADD CONSTRAINT `PARTNER_ID_FK` FOREIGN KEY (`PARTNER_ID`) REFERENCES `PARTNER_ID` (`PARTNER_ID`),
  ADD CONSTRAINT `PARTNER_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PARTNER_PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `PARTNER_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `PARTNER_GROUP`
--
ALTER TABLE `PARTNER_GROUP`
  ADD CONSTRAINT `PARTNER_GROUP_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PARTNER_GROUP_FREQ_ID_FK` FOREIGN KEY (`FREQUENCY_ID`) REFERENCES `FREQUENCY_ID` (`FREQUENCY_ID`),
  ADD CONSTRAINT `PARTNER_GROUP_ID_FK` FOREIGN KEY (`PARTNER_GROUP_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `PARTNER_GROUP_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PARTNER_GROUP_PAR_GRP_ID_FK` FOREIGN KEY (`PARTNER_GROUP_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `PARTNER_GROUP_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `PARTNER_GROUP_MEMBER`
--
ALTER TABLE `PARTNER_GROUP_MEMBER`
  ADD CONSTRAINT `PARTNER_GRP_MEM_GRP_ID_FK` FOREIGN KEY (`PARTNER_GROUP_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `PARTNER_GRP_MEM_PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `PARTNER_GRP_MEM_RESP_ID_FK` FOREIGN KEY (`RESPONSABILITY_ID`) REFERENCES `RESPONSABILITY_ID` (`RESPONSABILITY_ID`);

--
-- Filtros para la tabla `PERSON`
--
ALTER TABLE `PERSON`
  ADD CONSTRAINT `PERSON_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`),
  ADD CONSTRAINT `PERSON_CIVIL_STATUS_ID_FK` FOREIGN KEY (`CIVIL_STATUS_ID`) REFERENCES `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`),
  ADD CONSTRAINT `PERSON_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_GENDER_TYPE_ID_FK` FOREIGN KEY (`GENDER_TYPE_ID`) REFERENCES `GENDER_TYPE_ID` (`GENDER_TYPE_ID`),
  ADD CONSTRAINT `PERSON_IDENTIF_TYPE_ID_FK` FOREIGN KEY (`IDENTIFICATION_TYPE_ID`) REFERENCES `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `PERSON_PROFESSION_TYPE_ID_FK` FOREIGN KEY (`PROFESSION_TYPE_ID`) REFERENCES `PROFESSION_TYPE_ID` (`PROFESSION_TYPE_ID`);

--
-- Filtros para la tabla `PERSON_ADDRESS`
--
ALTER TABLE `PERSON_ADDRESS`
  ADD CONSTRAINT `PERSON_ADDRESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_ADDRESS_DISTRICT_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`) REFERENCES `DISTRICT_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`);

--
-- Filtros para la tabla `PERSON_PHONE`
--
ALTER TABLE `PERSON_PHONE`
  ADD CONSTRAINT `PERSON_PHONE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `PERSON_TYPE`
--
ALTER TABLE `PERSON_TYPE`
  ADD CONSTRAINT `PERSON_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_TYPE_ID_FK` FOREIGN KEY (`PERSON_TYPE_ID`) REFERENCES `PERSON_TYPE_ID` (`PERSON_TYPE_ID`),
  ADD CONSTRAINT `PERSON_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PHONE_TYPE`
--
ALTER TABLE `PHONE_TYPE`
  ADD CONSTRAINT `PHONE_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PHONE_TYPE_ID_FK` FOREIGN KEY (`PHONE_TYPE_ID`) REFERENCES `PHONE_TYPE_ID` (`PHONE_TYPE_ID`),
  ADD CONSTRAINT `PHONE_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROCESS`
--
ALTER TABLE `PROCESS`
  ADD CONSTRAINT `PROCESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROCESS_DATAFILE_ID_FK` FOREIGN KEY (`DATAFILE_ID`) REFERENCES `DATAFILE_ID` (`DATAFILE_ID`),
  ADD CONSTRAINT `PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `PROCESS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROCESS_COMPONENT`
--
ALTER TABLE `PROCESS_COMPONENT`
  ADD CONSTRAINT `PROCESS_COMPONENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROCESS_COMP_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`, `TYPE_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`, `TYPE_ID`),
  ADD CONSTRAINT `PROCESS_COMP_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`);

--
-- Filtros para la tabla `PROCESS_ID`
--
ALTER TABLE `PROCESS_ID`
  ADD CONSTRAINT `PROCESS_ID_MODULE_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`) REFERENCES `MODULE_ID` (`SUBSYSTEM_ID`, `MODULE_ID`);

--
-- Filtros para la tabla `PRODUCT_ASESSOR`
--
ALTER TABLE `PRODUCT_ASESSOR`
  ADD CONSTRAINT `PRODUCT_ASESSOR_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PRODUCT_ASESSOR_ID_FK` FOREIGN KEY (`USER_ID`, `PRODUCT_ID`) REFERENCES `PRODUCT_ASESSOR_ID` (`USER_ID`, `PRODUCT_ID`),
  ADD CONSTRAINT `PRODUCT_ASESSOR_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PROD_ASSESSOR_PRODUCT_ID_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT_MICROCREDIT_ID` (`PRODUCT_ID`),
  ADD CONSTRAINT `PROD_ASSESSOR_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `PRODUCT_MICROCREDIT`
--
ALTER TABLE `PRODUCT_MICROCREDIT`
  ADD CONSTRAINT `PRODUCT_MICROCREDIT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PRODUCT_MICROCREDIT_ID_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT_MICROCREDIT_ID` (`PRODUCT_ID`),
  ADD CONSTRAINT `PRODUCT_MIC_CURRENCY_ID_FK` FOREIGN KEY (`CURRENCY_ID`) REFERENCES `CURRENCY_ID` (`CURRENCY_ID`),
  ADD CONSTRAINT `PRODU_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROFESSION_TYPE`
--
ALTER TABLE `PROFESSION_TYPE`
  ADD CONSTRAINT `PROFESSION_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROFESSION_TYPE_ID_FK` FOREIGN KEY (`PROFESSION_TYPE_ID`) REFERENCES `PROFESSION_TYPE_ID` (`PROFESSION_TYPE_ID`),
  ADD CONSTRAINT `PROFESSION_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROFILE`
--
ALTER TABLE `PROFILE`
  ADD CONSTRAINT `PROFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROFILE_ID_FK` FOREIGN KEY (`PROFILE_ID`) REFERENCES `PROFILE_ID` (`PROFILE_ID`),
  ADD CONSTRAINT `PROFILE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROVINCE`
--
ALTER TABLE `PROVINCE`
  ADD CONSTRAINT `PROVINCE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROVINCE_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`) REFERENCES `PROVINCE_ID` (`COUNTRY_ID`, `PROVINCE_ID`),
  ADD CONSTRAINT `PROVINCE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROVINCE_ID`
--
ALTER TABLE `PROVINCE_ID`
  ADD CONSTRAINT `PROVINCE_ID_COUNTRY_ID_FK` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY_ID` (`COUNTRY_ID`);

--
-- Filtros para la tabla `QUOTA_TYPE`
--
ALTER TABLE `QUOTA_TYPE`
  ADD CONSTRAINT `QUOTA_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `QUOTA_TYPE_ID_FK` FOREIGN KEY (`QUOTA_TYPE_ID`) REFERENCES `QUOTA_TYPE_ID` (`QUOTA_TYPE_ID`),
  ADD CONSTRAINT `QUOTA_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `RECOMMENDATION`
--
ALTER TABLE `RECOMMENDATION`
  ADD CONSTRAINT `RECOMMENDATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `RECOMMENDATION_ID_FK` FOREIGN KEY (`SOLICITUDE_ID`) REFERENCES `RECOMMENDATION_ID` (`SOLICITUDE_ID`),
  ADD CONSTRAINT `RECOMMENDATION_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `RECOMMENDATION_SOLICITUDE_ID` FOREIGN KEY (`SOLICITUDE_ID`) REFERENCES `SOLICITUDE_ID` (`SOLICITUDE_ID`);

--
-- Filtros para la tabla `RESPONSABILITY`
--
ALTER TABLE `RESPONSABILITY`
  ADD CONSTRAINT `RESPONSABILITY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `RESPONSABILITY_ID_FK` FOREIGN KEY (`RESPONSABILITY_ID`) REFERENCES `RESPONSABILITY_ID` (`RESPONSABILITY_ID`),
  ADD CONSTRAINT `RESPONSABILITY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `RESPONSE`
--
ALTER TABLE `RESPONSE`
  ADD CONSTRAINT `RESPONSE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `RESPONSE_ID_FK` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `RESPONSE_ID` (`RESPONSE_ID`),
  ADD CONSTRAINT `RESPONSE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `ROLE`
--
ALTER TABLE `ROLE`
  ADD CONSTRAINT `ROLE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ROLE_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`);

--
-- Filtros para la tabla `SEQUENTIAL`
--
ALTER TABLE `SEQUENTIAL`
  ADD CONSTRAINT `SEQUENTIAL_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SEQUENTIAL_ID_FK` FOREIGN KEY (`SEQUENTIAL_ID`) REFERENCES `SEQUENTIAL_ID` (`SEQUENTIAL_ID`);

--
-- Filtros para la tabla `SOLICITUDE`
--
ALTER TABLE `SOLICITUDE`
  ADD CONSTRAINT `SOLICITUDE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SOLICITUDE_FUNDS_DEST_ID_FK` FOREIGN KEY (`FUNDS_DESTINATION_ID`) REFERENCES `FUNDS_DESTINATION_ID` (`FUNDS_DESTINATION_ID`),
  ADD CONSTRAINT `SOLICITUDE_GROUP_CLIENT_ID_FK` FOREIGN KEY (`GROUP_CLIENT_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `SOLICITUDE_ID_FK` FOREIGN KEY (`SOLICITUDE_ID`) REFERENCES `SOLICITUDE_ID` (`SOLICITUDE_ID`),
  ADD CONSTRAINT `SOLICITUDE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `SOLICITUDE_OPE_COND_ID_FK` FOREIGN KEY (`OPERATIVE_CONDITION_ID`) REFERENCES `OPERATIVE_CONDITION_ID` (`OPERATIVE_CONDITION_ID`),
  ADD CONSTRAINT `SOLICITUDE_PARTNER_CLIE_ID_FK` FOREIGN KEY (`PARTNER_CLIENT_ID`) REFERENCES `PARTNER_ID` (`PARTNER_ID`),
  ADD CONSTRAINT `SOLICITUDE_PAY_FREQ_ID_FK` FOREIGN KEY (`PAYMENT_FREQUENCY_ID`) REFERENCES `FREQUENCY_ID` (`FREQUENCY_ID`),
  ADD CONSTRAINT `SOLICITUDE_PRODUCT_ID_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT_MICROCREDIT_ID` (`PRODUCT_ID`),
  ADD CONSTRAINT `SOLICITUDE_QUOTA_TYPE_ID_FK` FOREIGN KEY (`QUOTA_TYPE_ID`) REFERENCES `QUOTA_TYPE_ID` (`QUOTA_TYPE_ID`),
  ADD CONSTRAINT `SOLICITUDE_SOL_STATUS_ID_FK` FOREIGN KEY (`STATUS_ID`) REFERENCES `SOLICITUDE_STATUS_ID` (`STATUS_ID`),
  ADD CONSTRAINT `SOLICITUDE_USER_ACCOUNT_ID_FK` FOREIGN KEY (`ASSESSOR`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `SOLICITUDE_STATUS`
--
ALTER TABLE `SOLICITUDE_STATUS`
  ADD CONSTRAINT `SOLICITUDE_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SOLICITUDE_STATUS_ID_FK` FOREIGN KEY (`STATUS_ID`) REFERENCES `SOLICITUDE_STATUS_ID` (`STATUS_ID`),
  ADD CONSTRAINT `SOLICITUDE_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `SUBSYSTEM`
--
ALTER TABLE `SUBSYSTEM`
  ADD CONSTRAINT `SUBSYSTEM_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`),
  ADD CONSTRAINT `SUBSYSTEM_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `USER_ACCESS`
--
ALTER TABLE `USER_ACCESS`
  ADD CONSTRAINT `USER_ACCESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_ACCESS_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_ACCOUNT`
--
ALTER TABLE `USER_ACCOUNT`
  ADD CONSTRAINT `USER_ACCOUNT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_USER_STATUS_ID_FK` FOREIGN KEY (`USER_STATUS_ID`) REFERENCES `USER_STATUS_ID` (`USER_STATUS_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_USER_TYPE_ID_FK` FOREIGN KEY (`USER_TYPE_ID`) REFERENCES `USER_TYPE_ID` (`USER_TYPE_ID`);

--
-- Filtros para la tabla `USER_PROFILE`
--
ALTER TABLE `USER_PROFILE`
  ADD CONSTRAINT `USER_PROFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_PROF_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_SESSION`
--
ALTER TABLE `USER_SESSION`
  ADD CONSTRAINT `USER_SESSION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_SESSION_HOST_ID_FK` FOREIGN KEY (`HOST_ID`) REFERENCES `HOST_ID` (`HOST_ID`),
  ADD CONSTRAINT `USER_SESS_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_STATUS`
--
ALTER TABLE `USER_STATUS`
  ADD CONSTRAINT `USER_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_STATUS_ID_FK` FOREIGN KEY (`USER_STATUS_ID`) REFERENCES `USER_STATUS_ID` (`USER_STATUS_ID`),
  ADD CONSTRAINT `USER_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `USER_TYPE`
--
ALTER TABLE `USER_TYPE`
  ADD CONSTRAINT `USER_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_TYPE_ID_FK` FOREIGN KEY (`USER_TYPE_ID`) REFERENCES `USER_TYPE_ID` (`USER_TYPE_ID`),
  ADD CONSTRAINT `USER_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `ZONE_ASESSOR`
--
ALTER TABLE `ZONE_ASESSOR`
  ADD CONSTRAINT `ZONE_ASE_GEO_ZONE_ID_FK` FOREIGN KEY (`GEOGRAPHIC_ZONE_ID`) REFERENCES `GEOGRAPHIC_ZONE_ID` (`GEOGRAPHIC_ZONE_ID`),
  ADD CONSTRAINT `ZONE_ASE_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

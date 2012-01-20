-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 20-01-2012 a las 23:18:10
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `microxt4`
--

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
('MXT', 'ES', 'EC', 'AZ', 'CH', 'Chordeleg'),
('MXT', 'ES', 'EC', 'AZ', 'CU', 'Cuenca'),
('MXT', 'ES', 'EC', 'AZ', 'EL', 'El Pan'),
('MXT', 'ES', 'EC', 'AZ', 'GI', 'Giron'),
('MXT', 'ES', 'EC', 'AZ', 'GL', 'Gualaceo'),
('MXT', 'ES', 'EC', 'AZ', 'GU', 'Guachapala'),
('MXT', 'ES', 'EC', 'AZ', 'NA', 'Nabon'),
('MXT', 'ES', 'EC', 'AZ', 'OA', 'Oña'),
('MXT', 'ES', 'EC', 'AZ', 'PA', 'Paute'),
('MXT', 'ES', 'EC', 'AZ', 'PO', 'Camilo Ponce'),
('MXT', 'ES', 'EC', 'AZ', 'PU', 'Pucara'),
('MXT', 'ES', 'EC', 'AZ', 'SA', 'San Fernando'),
('MXT', 'ES', 'EC', 'AZ', 'SG', 'Sigsig'),
('MXT', 'ES', 'EC', 'AZ', 'SI', 'Santa Isabel'),
('MXT', 'ES', 'EC', 'AZ', 'SO', 'Sevilla De Oro');

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
('EC', 'AZ', 'PU'),
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
('MXT', 'ES', 'CAS', 'CASADO(A)'),
('MXT', 'ES', 'DIV', 'DIVORCIADO(A)'),
('MXT', 'ES', 'SEP', 'SEPARADO(A)'),
('MXT', 'ES', 'SOL', 'SOLTERO(A)'),
('MXT', 'ES', 'UNI', 'UNION LIBRE'),
('MXT', 'ES', 'VIU', 'VIUDO(A)');

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
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPANY`
--

INSERT INTO `COMPANY` (`COMPANY_ID`, `NAME`, `DATAFILE_ID`, `UPGRADE_NUMBER`, `LICENSE_DATE`, `ENABLE`) VALUES
('ALL', 'DEFAULT COMPANY', NULL, 1.10, '2011-01-01 00:00:00', '1'),
('MXT', 'MICROXT', NULL, 1.00, '2011-01-01 00:00:00', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT`
--

CREATE TABLE IF NOT EXISTS `COMPONENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `COMPONENT_ID` varchar(150) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `CLASS_NAME` varchar(100) NOT NULL,
  `METHOD_NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`COMPONENT_ID`),
  KEY `COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `COMPONENT_SUBSYSTEM_ID_FK` (`SUBSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT`
--

INSERT INTO `COMPONENT` (`COMPANY_ID`, `COMPONENT_ID`, `SUBSYSTEM_ID`, `CLASS_NAME`, `METHOD_NAME`, `DESCRIPTION`) VALUES
('MXT', 'mobile.bus.security.Loggin', 'A', 'Loggin', 'general', 'Process loggin'),
('MXT', 'mobile.core.processor.MaintenanceProcessor', 'G', 'MaintenanceProcessor', 'general', 'General maintenance processor'),
('MXT', 'mobile.core.processor.SpecialQueryProcessor', 'G', 'SpecialQueryProcessor', 'general', 'Special query');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT_ID`
--

CREATE TABLE IF NOT EXISTS `COMPONENT_ID` (
  `COMPONENT_ID` varchar(150) NOT NULL,
  PRIMARY KEY (`COMPONENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT_ID`
--

INSERT INTO `COMPONENT_ID` (`COMPONENT_ID`) VALUES
('mobile.bus.parameter.ParameterTest'),
('mobile.bus.security.Loggin'),
('mobile.core.processor.MaintenanceProcessor'),
('mobile.core.processor.SpecialQueryProcessor'),
('mobile.logic.general.MenuGenerator');

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
('Boolean', 'MYSQL', 0, 'VARCHAR'),
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
  `PRIMARY_KEY` varchar(1) NOT NULL DEFAULT '0',
  `UNIQUE_KEY` varchar(1) NOT NULL DEFAULT '0',
  `NULLABLE` varchar(1) NOT NULL DEFAULT '0',
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
('ALL', 'ADDRESS_TYPE', 'ADDRESS_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Address type Id'),
('ALL', 'ADDRESS_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of address type'),
('ALL', 'CITY', 'CITY_ID', 3, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'CITY', 'COUNTRY_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'CITY', 'NAME', 4, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of city'),
('ALL', 'CITY', 'PROVINCE_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'CIVIL_STATUS', 'CIVIL_STATUS_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Civil type status Id'),
('ALL', 'CIVIL_STATUS', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of civil status'),
('ALL', 'COMPANY', 'COMPANY_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Company Id'),
('ALL', 'COMPANY', 'DATAFILE_ID', 3, 'Long', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Document Id'),
('ALL', 'COMPANY', 'ENABLE', 6, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Enable'),
('ALL', 'COMPANY', 'LICENSE_DATE', 5, 'Date', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'License date of company'),
('ALL', 'COMPANY', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of company'),
('ALL', 'COMPANY', 'UPGRADE_NUMBER', 4, 'BigDecimal', 4, 2, '0', '0', '1', NULL, NULL, NULL, NULL, 'Upgrade number of company'),
('ALL', 'COMPONENT', 'CLASS_NAME', 3, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Class name'),
('ALL', 'COMPONENT', 'COMPONENT_ID', 1, 'String', 150, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Component Id'),
('ALL', 'COMPONENT', 'DESCRIPTION', 5, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'COMPONENT', 'METHOD_NAME', 4, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Method name'),
('ALL', 'COMPONENT', 'SUBSYSTEM_ID', 2, 'String', 2, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'COUNTRY', 'AREA_CODE', 3, 'String', 4, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Area code'),
('ALL', 'COUNTRY', 'COUNTRY_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'COUNTRY', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of country'),
('ALL', 'CURRENCY', 'CURRENCY_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Currency'),
('ALL', 'CURRENCY', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'CURRENCY', 'INITIALS', 3, 'String', 3, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Initials'),
('ALL', 'DATABASE_TYPE', 'DATABASE_ID', 2, 'String', 30, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Database Id'),
('ALL', 'DATABASE_TYPE', 'DATABASE_TYPE', 4, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Data type of database'),
('ALL', 'DATABASE_TYPE', 'DATA_SIZE', 3, 'Integer', 5, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Data size of field'),
('ALL', 'DATABASE_TYPE', 'DATA_TYPE_ID', 1, 'String', 30, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Data type Id'),
('ALL', 'DATAFILE', 'BINARY_BYTES', 4, 'Long', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Binary bytes'),
('ALL', 'DATAFILE', 'BINARY_OBJECT', 5, 'Blob', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Binary object'),
('ALL', 'DATAFILE', 'BINARY_PATH', 3, 'String', 200, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Binary path'),
('ALL', 'DATAFILE', 'CHARACTER_DATA', 6, 'Clob', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Character data'),
('ALL', 'DATAFILE', 'DATAFILE_ID', 1, 'Long', 10, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Datafile Id'),
('ALL', 'DATAFILE', 'DATAFILE_TYPE_ID', 2, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Datafile type Id'),
('ALL', 'DATAFILE_TYPE', 'DATAFILE_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Datafile type Id'),
('ALL', 'DATAFILE_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of datafile type'),
('ALL', 'DATA_TYPE', 'DATA_TYPE_ID', 1, 'String', 30, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Data type Id'),
('ALL', 'DATA_TYPE', 'DESCRIPTION', 2, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of data type'),
('ALL', 'DISTRICT', 'CITY_ID', 3, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'DISTRICT', 'COUNTRY_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'DISTRICT', 'DISTRICT_ID', 4, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'DISTRICT', 'NAME', 5, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of district'),
('ALL', 'DISTRICT', 'PROVINCE_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'ENTITY_FIELD', 'DATA_SCALE', 6, 'Integer', 3, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Data scale of field'),
('ALL', 'ENTITY_FIELD', 'DATA_SIZE', 5, 'Integer', 5, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Data size of field'),
('ALL', 'ENTITY_FIELD', 'DATA_TYPE_ID', 4, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Data type of field'),
('ALL', 'ENTITY_FIELD', 'DEFAULT_VALUE', 10, 'String', 30, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Default value of field'),
('ALL', 'ENTITY_FIELD', 'DESCRIPTION', 14, 'String', 100, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Description of field'),
('ALL', 'ENTITY_FIELD', 'FIELD_ID', 2, 'String', 30, 0, '1', '1', '0', NULL, NULL, NULL, NULL, 'Field Id'),
('ALL', 'ENTITY_FIELD', 'FIELD_ORDER', 3, 'Integer', 3, 0, '0', '1', '0', NULL, NULL, NULL, NULL, 'Field order'),
('ALL', 'ENTITY_FIELD', 'MAXIMUM_VALUE', 13, 'String', 30, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Maximum value of field'),
('ALL', 'ENTITY_FIELD', 'MINIMUM_VALUE', 12, 'String', 30, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Minimum value of field'),
('ALL', 'ENTITY_FIELD', 'NULLABLE', 9, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Nullable'),
('ALL', 'ENTITY_FIELD', 'PRIMARY_KEY', 7, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Primary key'),
('ALL', 'ENTITY_FIELD', 'SEQUENTIAL_ID', 11, 'String', 30, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Sequential Id of field'),
('ALL', 'ENTITY_FIELD', 'TABLE_ID', 1, 'String', 30, 0, '1', '1', '0', NULL, NULL, NULL, NULL, 'Table Id'),
('ALL', 'ENTITY_FIELD', 'UNIQUE_KEY', 8, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Unique key'),
('ALL', 'ENTITY_RELATIONSHIP', 'FIELD_FROM', 4, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Field from'),
('ALL', 'ENTITY_RELATIONSHIP', 'FIELD_TO', 6, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Filed to'),
('ALL', 'ENTITY_RELATIONSHIP', 'RELATIONSHIP_ID', 1, 'String', 30, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Relationship order'),
('ALL', 'ENTITY_RELATIONSHIP', 'RELATIONSHIP_ORDER', 2, 'Integer', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Field order'),
('ALL', 'ENTITY_RELATIONSHIP', 'TABLE_FROM', 3, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Table from'),
('ALL', 'ENTITY_RELATIONSHIP', 'TABLE_TO', 5, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Table to'),
('ALL', 'ENTITY_TABLE', 'CACHE_MEMORY', 9, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Cache memory of entity'),
('ALL', 'ENTITY_TABLE', 'DESCRIPTION', 10, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description of entity'),
('ALL', 'ENTITY_TABLE', 'ENUMERATED_TYPES', 8, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Enumerated type of entity'),
('ALL', 'ENTITY_TABLE', 'HAS_TABLE_ID', 2, 'Boolean', 1, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Order of fields'),
('ALL', 'ENTITY_TABLE', 'HISTORICAL_DATA', 6, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Historical data of entity'),
('ALL', 'ENTITY_TABLE', 'MULTI_COMPANY', 4, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Multi company of entity'),
('ALL', 'ENTITY_TABLE', 'MULTI_LANGUAGE', 5, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Multi language of entity'),
('ALL', 'ENTITY_TABLE', 'OPTIMISTIC_LOCKING', 7, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Optimistic locking of entity'),
('ALL', 'ENTITY_TABLE', 'PACKAGE_NAME', 3, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Package name of entity'),
('ALL', 'ENTITY_TABLE', 'TABLE_ID', 1, 'String', 30, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Table Id'),
('ALL', 'FREQUENCY', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'FREQUENCY', 'FREQUENCY_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Frecuency'),
('ALL', 'FUNDS_DESTINATION', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'FUNDS_DESTINATION', 'FUNDS_DESTINATION_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Destination of funds'),
('ALL', 'GENDER_TYPE', 'GENDER_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Gender type Id'),
('ALL', 'GENDER_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of gender'),
('ALL', 'GEOGRAPHIC_ZONE', 'COORDINATE_TYPE', 3, 'String', 20, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Type of coordinate: Point, Route, Polygon'),
('ALL', 'GEOGRAPHIC_ZONE', 'DESCRIPTION', 2, 'String', 100, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Description of the zone'),
('ALL', 'GEOGRAPHIC_ZONE', 'GEOGRAPHIC_ZONE_ID', 1, 'String', 6, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Geographic zone id'),
('ALL', 'GEOGRAPHIC_ZONE', 'P11', 4, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Point of coordinate 1,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P12', 5, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Point of coordinate 1,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P21', 6, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 2,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P22', 7, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 2,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P31', 8, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 3,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P32', 9, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 3,2'),
('ALL', 'GEOGRAPHIC_ZONE', 'P41', 10, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 4,1'),
('ALL', 'GEOGRAPHIC_ZONE', 'P42', 11, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Point of coordinate 4,2'),
('ALL', 'HOST', 'ADDRESS', 2, 'String', 60, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Address'),
('ALL', 'HOST', 'HOST_ID', 1, 'String', 40, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Host Id'),
('ALL', 'HOST', 'TIME_ZONE', 6, 'String', 4, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Time zone'),
('ALL', 'IDENTIFICATION_TYPE', 'IDENTIFICATION_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Identification type Id'),
('ALL', 'IDENTIFICATION_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of identification type'),
('ALL', 'LANGUAGE', 'LANGUAGE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Language Id'),
('ALL', 'LANGUAGE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of language'),
('ALL', 'MODULE', 'MODULE_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'MODULE', 'NAME', 3, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of module'),
('ALL', 'MODULE', 'SUBSYSTEM_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PARAMETER', 'DATA_TYPE_ID', 3, 'String', 30, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Data type of parameter'),
('ALL', 'PARAMETER', 'DESCRIPTION', 5, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description of parameter'),
('ALL', 'PARAMETER', 'PARAMETER_ID', 1, 'String', 40, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Parameter Id'),
('ALL', 'PARAMETER', 'PARAMETER_VALUE', 4, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Value of parameter'),
('ALL', 'PARAMETER', 'SUBSYSTEM_ID', 2, 'String', 2, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PARTNER', 'ACTIVITY', 3, 'String', 300, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Activity'),
('ALL', 'PARTNER', 'FREQUENCY_ID', 5, 'String', 3, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Meeting Frequency id'),
('ALL', 'PARTNER', 'MEETING_DAY', 6, 'Integer', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Meeting day'),
('ALL', 'PARTNER', 'PARTNER_ID', 1, 'String', 10, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Person id'),
('ALL', 'PARTNER', 'PERSON_ID', 2, 'Long', 10, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Person id'),
('ALL', 'PARTNER', 'USER_ID', 4, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'PARTNER_GROUP', 'ACTIVITY', 4, 'String', 300, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Activity'),
('ALL', 'PARTNER_GROUP', 'CREATION_DATE', 3, 'Date', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Creation date'),
('ALL', 'PARTNER_GROUP', 'FREQUENCY_ID', 6, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Meeting Frequency id'),
('ALL', 'PARTNER_GROUP', 'GROUP_DESCRIPTION', 2, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Group description'),
('ALL', 'PARTNER_GROUP', 'MEETING_DAY', 7, 'Integer', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Meeting day'),
('ALL', 'PARTNER_GROUP', 'PARTNER_GROUP_ID', 1, 'String', 15, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Partner group id'),
('ALL', 'PARTNER_GROUP', 'USER_ID', 5, 'String', 20, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'PARTNER_GROUP_MEMBER', 'OBSERVATIONS', 5, 'String', 200, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Activity'),
('ALL', 'PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_ID', 2, 'String', 15, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Partner group id'),
('ALL', 'PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_MEMBER_ID', 1, 'String', 15, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Group member id'),
('ALL', 'PARTNER_GROUP_MEMBER', 'PERSON_ID', 3, 'Long', 10, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Person id'),
('ALL', 'PARTNER_GROUP_MEMBER', 'RESPONSABILITY_ID', 4, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Responsability id'),
('ALL', 'PERSON', 'CITY_ID', 11, 'String', 3, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'PERSON', 'CIVIL_STATUS_ID', 9, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Civil type status Id'),
('ALL', 'PERSON', 'COUNTRY_ID', 10, 'String', 2, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PERSON', 'DATE_OF_BIRTH', 7, 'Date', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Date of birth'),
('ALL', 'PERSON', 'DISTRICT_ID', 13, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'PERSON', 'GENDER_TYPE_ID', 8, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Gender type Id'),
('ALL', 'PERSON', 'IDENTIFICATION_NUMBER', 6, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Identification number'),
('ALL', 'PERSON', 'IDENTIFICATION_TYPE_ID', 5, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Identification type Id'),
('ALL', 'PERSON', 'LAST_NAME', 3, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Lastname of person'),
('ALL', 'PERSON', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of person'),
('ALL', 'PERSON', 'PERSON_ID', 1, 'Long', 10, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'PERSON', 'PROFESSION_TYPE_ID', 14, 'String', 4, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Profession type Id'),
('ALL', 'PERSON', 'PROVINCE_ID', 12, 'String', 2, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'PERSON', 'SECOND_LAST_NAME', 4, 'String', 40, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Second lastname of person'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_DESCRIPTION', 4, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'First street'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_SEQUENCE', 2, 'Integer', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Sequence of person address'),
('ALL', 'PERSON_ADDRESS', 'ADDRESS_TYPE_ID', 3, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Address type Id'),
('ALL', 'PERSON_ADDRESS', 'CITY_ID', 6, 'String', 3, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'City Id'),
('ALL', 'PERSON_ADDRESS', 'COUNTRY_ID', 5, 'String', 2, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PERSON_ADDRESS', 'DISTRICT_ID', 8, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'District Id'),
('ALL', 'PERSON_ADDRESS', 'PERSON_ID', 1, 'Long', 10, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'PERSON_ADDRESS', 'PROVINCE_ID', 7, 'String', 2, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'PERSON_PHONE', 'AREA_CODE', 4, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Area code'),
('ALL', 'PERSON_PHONE', 'PERSON_ID', 1, 'Long', 10, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'PERSON_PHONE', 'PHONE_NUMBER', 5, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Phone number'),
('ALL', 'PERSON_PHONE', 'PHONE_SEQUENCE', 2, 'Integer', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Sequence of person phone'),
('ALL', 'PERSON_PHONE', 'PHONE_TYPE_ID', 3, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Phone type Id'),
('ALL', 'PERSON_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of person type'),
('ALL', 'PERSON_TYPE', 'PERSON_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Person type Id'),
('ALL', 'PHONE_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of phone'),
('ALL', 'PHONE_TYPE', 'PHONE_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Phone type Id'),
('ALL', 'PROCESS', 'DATAFILE_ID', 8, 'Long', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Datafile Id'),
('ALL', 'PROCESS', 'ENABLE', 5, 'Boolean', 1, 0, '0', '0', '0', '1', NULL, NULL, NULL, 'Enable'),
('ALL', 'PROCESS', 'MENU', 6, 'Boolean', 1, 0, '0', '0', '0', '1', NULL, NULL, NULL, 'Show in app  menu'),
('ALL', 'PROCESS', 'MODULE_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'PROCESS', 'NAME', 4, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of process'),
('ALL', 'PROCESS', 'PROCESS_ID', 3, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'PROCESS', 'SUBSYSTEM_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PROCESS', 'URL', 7, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'URL'),
('ALL', 'PROCESS_COMPONENT', 'AUTHORIZE', 8, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Enable'),
('ALL', 'PROCESS_COMPONENT', 'COMPONENT_ID', 6, 'String', 150, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Component Id'),
('ALL', 'PROCESS_COMPONENT', 'ENABLE', 7, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Enable'),
('ALL', 'PROCESS_COMPONENT', 'MODULE_ID', 3, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'PROCESS_COMPONENT', 'PROCESS_ID', 4, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'PROCESS_COMPONENT', 'PROCESS_SEQUENCE', 5, 'Integer', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Process sequence'),
('ALL', 'PROCESS_COMPONENT', 'SUBSYSTEM_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'PRODUCT_ASESSOR', 'OBSERVATIONS', 3, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Observations'),
('ALL', 'PRODUCT_ASESSOR', 'PRODUCT_ID', 2, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'PRODUCT_ASESSOR', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'PRODUCT_MICROCREDIT', 'CURRENCY_ID', 3, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Currency id'),
('ALL', 'PRODUCT_MICROCREDIT', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description of product'),
('ALL', 'PRODUCT_MICROCREDIT', 'MAX_AMOUNT', 5, 'BigDecimal', 19, 6, '0', '0', '0', NULL, NULL, NULL, NULL, 'Maximun amount'),
('ALL', 'PRODUCT_MICROCREDIT', 'MAX_PERIOD', 7, 'Long', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Maximun period'),
('ALL', 'PRODUCT_MICROCREDIT', 'MIN_AMOUNT', 4, 'BigDecimal', 19, 6, '0', '0', '0', NULL, NULL, NULL, NULL, 'Minimun amount'),
('ALL', 'PRODUCT_MICROCREDIT', 'MIN_PERIOD', 6, 'Long', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Minimun period'),
('ALL', 'PRODUCT_MICROCREDIT', 'PRODUCT_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'PRODUCT_MICROCREDIT', 'RATE', 8, 'BigDecimal', 19, 6, '0', '0', '0', NULL, NULL, NULL, NULL, 'Rate'),
('ALL', 'PROFESSION_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of profession type'),
('ALL', 'PROFESSION_TYPE', 'PROFESSION_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Profession type Id'),
('ALL', 'PROFILE', 'DESCRIPTION', 3, 'String', 150, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Description of profile'),
('ALL', 'PROFILE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of profile'),
('ALL', 'PROFILE', 'PROFILE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'PROVINCE', 'COUNTRY_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Country Id'),
('ALL', 'PROVINCE', 'NAME', 3, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of province'),
('ALL', 'PROVINCE', 'PROVINCE_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Province Id'),
('ALL', 'QUOTA_TYPE', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'QUOTA_TYPE', 'QUOTA_TYPE_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Fee type'),
('ALL', 'RECOMMENDATION', 'CREDIT_HISTORY', 6, 'String', 500, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Comments about credit history'),
('ALL', 'RECOMMENDATION', 'DOCUMENTS', 2, 'String', 500, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Comments about documents'),
('ALL', 'RECOMMENDATION', 'ECONOMIC_UNIT', 3, 'String', 500, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Comments about economic unit'),
('ALL', 'RECOMMENDATION', 'FAMILY_UNIT', 4, 'String', 500, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Comments about family unit'),
('ALL', 'RECOMMENDATION', 'PAYMENT_MORALE', 5, 'String', 500, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Comments about payment morale'),
('ALL', 'RECOMMENDATION', 'PROPOSAL', 7, 'String', 25, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Proposal'),
('ALL', 'RECOMMENDATION', 'SOLICITUDE_ID', 1, 'String', 25, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Solicitude Id'),
('ALL', 'RESPONSABILITY', 'DESCRIPTION', 3, 'String', 60, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'RESPONSABILITY', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name'),
('ALL', 'RESPONSABILITY', 'RESPONSABILITY_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Responsability id'),
('ALL', 'RESPONSE', 'DESCRIPTION', 2, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description of response'),
('ALL', 'RESPONSE', 'RESPONSE_ID', 1, 'String', 8, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Response Id'),
('ALL', 'ROLE', 'DAY_ID', 5, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Day Id'),
('ALL', 'ROLE', 'EDITABLE', 8, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Editable'),
('ALL', 'ROLE', 'HOUR_FROM', 6, 'String', 4, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Hour From'),
('ALL', 'ROLE', 'HOUR_TO', 7, 'String', 4, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Hour To'),
('ALL', 'ROLE', 'MODULE_ID', 3, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'ROLE', 'PROCESS_ID', 4, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'ROLE', 'PROFILE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'ROLE', 'SUBSYSTEM_ID', 2, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'SEQUENTIAL', 'SEQUENTIAL_ID', 1, 'String', 40, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Sequential Id'),
('ALL', 'SEQUENTIAL', 'SEQUENTIAL_VALUE', 2, 'Long', 10, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Value of sequential'),
('ALL', 'SOLICITUDE', 'ACCOUNT', 2, 'String', 25, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Generated account'),
('ALL', 'SOLICITUDE', 'AMOUNT', 13, 'BigDecimal', 19, 6, '0', '0', '0', NULL, NULL, NULL, NULL, 'Amount'),
('ALL', 'SOLICITUDE', 'APPROVAL_DATE', 7, 'Date', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Approval date'),
('ALL', 'SOLICITUDE', 'ASSESSOR', 3, 'String', 20, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Assessor'),
('ALL', 'SOLICITUDE', 'DESTINATION_DESCRIPTION', 19, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description of destination'),
('ALL', 'SOLICITUDE', 'DISBURSEMENT_DATE', 8, 'Date', 0, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Disbursement date'),
('ALL', 'SOLICITUDE', 'EXPIRATION_DATE', 9, 'Date', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Expiration date'),
('ALL', 'SOLICITUDE', 'FUNDS_DESTINATION_ID', 18, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Destination of funds'),
('ALL', 'SOLICITUDE', 'GROUP_CLIENT_ID', 5, 'String', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Group'),
('ALL', 'SOLICITUDE', 'NUMBER_QUOTAS', 16, 'Integer', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Number of fees'),
('ALL', 'SOLICITUDE', 'NUMBER_RENEWAL', 12, 'Integer', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Number of renewal'),
('ALL', 'SOLICITUDE', 'PARTNER_CLIENT_ID', 4, 'String', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Client'),
('ALL', 'SOLICITUDE', 'PAYMENT_FREQUENCY_ID', 17, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Frequency of payment'),
('ALL', 'SOLICITUDE', 'PRODUCT_ID', 10, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Product id'),
('ALL', 'SOLICITUDE', 'QUOTA_TYPE_ID', 15, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Quota type id'),
('ALL', 'SOLICITUDE', 'SOLICITUDE_DATE', 6, 'Date', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Solicitud date'),
('ALL', 'SOLICITUDE', 'SOLICITUDE_ID', 1, 'String', 25, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Name'),
('ALL', 'SOLICITUDE', 'STATUS_ID', 11, 'String', 3, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Solicitude status id'),
('ALL', 'SOLICITUDE', 'TERM', 14, 'Long', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Term'),
('ALL', 'SOLICITUDE_STATUS', 'DESCRIPTION', 2, 'String', 50, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Description'),
('ALL', 'SOLICITUDE_STATUS', 'STATUS_ID', 1, 'String', 3, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Status'),
('ALL', 'SUBSYSTEM', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of subsystem'),
('ALL', 'SUBSYSTEM', 'SUBSYSTEM_ID', 1, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'USER_ACCESS', 'ANSWER', 5, 'String', 100, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Answer'),
('ALL', 'USER_ACCESS', 'LAST_CHANGE', 3, 'Date', 0, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Last change'),
('ALL', 'USER_ACCESS', 'QUESTION', 4, 'String', 100, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Question'),
('ALL', 'USER_ACCESS', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_ACCESS', 'USER_KEY', 2, 'String', 20, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'User Key'),
('ALL', 'USER_ACCOUNT', 'EMAIL', 6, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Email'),
('ALL', 'USER_ACCOUNT', 'LANGUAGE_ID', 5, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Language Id'),
('ALL', 'USER_ACCOUNT', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of user'),
('ALL', 'USER_ACCOUNT', 'PERSON_ID', 7, 'Long', 10, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Person Id'),
('ALL', 'USER_ACCOUNT', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_ACCOUNT', 'USER_STATUS_ID', 4, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'User status Id'),
('ALL', 'USER_ACCOUNT', 'USER_TYPE_ID', 3, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'User type Id'),
('ALL', 'USER_AUTHORIZATION', 'AUTHORIZER_ID', 2, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Authorizer Id'),
('ALL', 'USER_AUTHORIZATION', 'MODULE_ID', 4, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Module Id'),
('ALL', 'USER_AUTHORIZATION', 'PROCESS_ID', 5, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Process Id'),
('ALL', 'USER_AUTHORIZATION', 'SUBSYSTEM_ID', 3, 'String', 2, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Subsystem Id'),
('ALL', 'USER_AUTHORIZATION', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_NOTIFICATION', 'MESSAGE', 4, 'String', 4000, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Message'),
('ALL', 'USER_NOTIFICATION', 'READ_', 5, 'Boolean', 1, 0, '0', '0', '0', '0', NULL, NULL, NULL, 'Read'),
('ALL', 'USER_NOTIFICATION', 'SUBJECT', 3, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Subject'),
('ALL', 'USER_NOTIFICATION', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_NOTIFICATION', 'USER_NOTIFICATION_TYPE_ID', 2, 'String', 4, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'User notification type Id'),
('ALL', 'USER_NOTIFICATION_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of user notification type'),
('ALL', 'USER_NOTIFICATION_TYPE', 'USER_NOTIFICATION_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User notification type Id'),
('ALL', 'USER_PROFILE', 'PROFILE_ID', 2, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Profile Id'),
('ALL', 'USER_PROFILE', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_SESSION', 'HOST_ID', 2, 'String', 40, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Host Id'),
('ALL', 'USER_SESSION', 'TOKEN', 3, 'String', 100, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Token'),
('ALL', 'USER_SESSION', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id'),
('ALL', 'USER_STATUS', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of user status'),
('ALL', 'USER_STATUS', 'USER_STATUS_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User status Id'),
('ALL', 'USER_TYPE', 'NAME', 2, 'String', 40, 0, '0', '0', '0', NULL, NULL, NULL, NULL, 'Name of user type'),
('ALL', 'USER_TYPE', 'USER_TYPE_ID', 1, 'String', 4, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User type Id'),
('ALL', 'ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID', 2, 'String', 6, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'Geographic zone id'),
('ALL', 'ZONE_ASESSOR', 'OBSERVATIONS', 3, 'String', 50, 0, '0', '0', '1', NULL, NULL, NULL, NULL, 'Observations'),
('ALL', 'ZONE_ASESSOR', 'USER_ID', 1, 'String', 20, 0, '1', '0', '0', NULL, NULL, NULL, NULL, 'User Id');

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
('ADDRESS_TYPE', 'ADDRESS_TYPE_ID'),
('ADDRESS_TYPE', 'COMPANY_ID'),
('ADDRESS_TYPE', 'LANGUAGE_ID'),
('ADDRESS_TYPE', 'NAME'),
('ADDRESS_TYPE_ID', 'ADDRESS_TYPE_ID'),
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
('COMPONENT_ID', 'COMPONENT_ID'),
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
('MODULE', 'COMPANY_ID'),
('MODULE', 'LANGUAGE_ID'),
('MODULE', 'MODULE_ID'),
('MODULE', 'NAME'),
('MODULE', 'SUBSYSTEM_ID'),
('MODULE_ID', 'MODULE_ID'),
('MODULE_ID', 'SUBSYSTEM_ID'),
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
('PARTNER_GROUP_MEMBER', 'COMPANY_ID'),
('PARTNER_GROUP_MEMBER', 'CREATED'),
('PARTNER_GROUP_MEMBER', 'EXPIRED'),
('PARTNER_GROUP_MEMBER', 'OBSERVATIONS'),
('PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_ID'),
('PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_MEMBER_ID'),
('PARTNER_GROUP_MEMBER', 'PERSON_ID'),
('PARTNER_GROUP_MEMBER', 'RESPONSABILITY_ID'),
('PARTNER_GROUP_MEMBER_ID', 'PARTNER_GROUP_MEMBER_ID'),
('PARTNER_ID', 'PARTNER_ID'),
('PARTNER_ID', 'USER_ID'),
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
('PERSON_ID', 'DISTRICT_ID'),
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
('SOLICITUDE', 'LANGUAGE_ID'),
('SOLICITUDE', 'NUMBER_QUOTAS'),
('SOLICITUDE', 'NUMBER_RENEWAL'),
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
('USER_AUTHORIZATION', 'AUTHORIZER_ID'),
('USER_AUTHORIZATION', 'COMPANY_ID'),
('USER_AUTHORIZATION', 'CREATED'),
('USER_AUTHORIZATION', 'EXPIRED'),
('USER_AUTHORIZATION', 'MODULE_ID'),
('USER_AUTHORIZATION', 'PROCESS_ID'),
('USER_AUTHORIZATION', 'SUBSYSTEM_ID'),
('USER_AUTHORIZATION', 'USER_ID'),
('USER_NOTIFICATION', 'COMPANY_ID'),
('USER_NOTIFICATION', 'CREATED'),
('USER_NOTIFICATION', 'EXPIRED'),
('USER_NOTIFICATION', 'MESSAGE'),
('USER_NOTIFICATION', 'READ_'),
('USER_NOTIFICATION', 'SUBJECT'),
('USER_NOTIFICATION', 'USER_ID'),
('USER_NOTIFICATION', 'USER_NOTIFICATION_TYPE_ID'),
('USER_NOTIFICATION_TYPE', 'LANGUAGE_ID'),
('USER_NOTIFICATION_TYPE', 'NAME'),
('USER_NOTIFICATION_TYPE', 'USER_NOTIFICATION_TYPE_ID'),
('USER_NOTIFICATION_TYPE_ID', 'USER_NOTIFICATION_TYPE_ID'),
('USER_PROFILE', 'COMPANY_ID'),
('USER_PROFILE', 'CREATED'),
('USER_PROFILE', 'EXPIRED'),
('USER_PROFILE', 'PROFILE_ID'),
('USER_PROFILE', 'USER_ID'),
('USER_SESSION', 'COMPANY_ID'),
('USER_SESSION', 'CREATED'),
('USER_SESSION', 'EXPIRED'),
('USER_SESSION', 'HOST_ID'),
('USER_SESSION', 'TOKEN'),
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
('ZONE_ASESSOR', 'COMPANY_ID'),
('ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID'),
('ZONE_ASESSOR', 'LANGUAGE_ID'),
('ZONE_ASESSOR', 'OBSERVATIONS'),
('ZONE_ASESSOR', 'USER_ID'),
('ZONE_ASESSOR_ID', 'GEOGRAPHIC_ZONE_ID'),
('ZONE_ASESSOR_ID', 'USER_ID');

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
('ALL', 'USER_AUTH_PROCESS_ID_FK', 1, 'USER_AUTHORIZATION', 'SUBSYSTEM_ID', 'PROCESS_ID', 'SUBSYSTEM_ID'),
('ALL', 'USER_AUTH_PROCESS_ID_FK', 2, 'USER_AUTHORIZATION', 'MODULE_ID', 'PROCESS_ID', 'MODULE_ID'),
('ALL', 'USER_AUTH_PROCESS_ID_FK', 3, 'USER_AUTHORIZATION', 'PROCESS_ID', 'PROCESS_ID', 'PROCESS_ID'),
('ALL', 'USER_AUTH_USER_ACCOUNT_ID_FK', 1, 'USER_AUTHORIZATION', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_AUTH_USER_AUTH_ID_FK', 1, 'USER_AUTHORIZATION', 'AUTHORIZER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_NOTIF_USER_ACCOUNT_ID_FK', 1, 'USER_NOTIFICATION', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_NOTIF_USER_NOT_TYPE_ID_FK', 1, 'USER_NOTIFICATION', 'USER_NOTIFICATION_TYPE_ID', 'USER_NOTIFICATION_TYPE_ID', 'USER_NOTIFICATION_TYPE_ID'),
('ALL', 'USER_PROF_USER_ACCOUNT_ID_FK', 1, 'USER_PROFILE', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'USER_SESSION_HOST_ID_FK', 1, 'USER_SESSION', 'HOST_ID', 'HOST_ID', 'HOST_ID'),
('ALL', 'USER_SESS_USER_ACCOUNT_ID_FK', 1, 'USER_SESSION', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
('ALL', 'ZONE_ASE_GEO_ZONE_ID_FK', 1, 'ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID', 'GEOGRAPHIC_ZONE_ID'),
('ALL', 'ZONE_ASE_USER_ID_FK', 1, 'ZONE_ASESSOR', 'USER_ID', 'USER_ACCOUNT_ID', 'USER_ID'),
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
('MXT', 'MODULE_COMPANY_FK', 1, 'MODULE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'MODULE_ID_FK', 1, 'MODULE', 'SUBSYSTEM_ID', 'MODULE_ID', 'SUBSYSTEM_ID'),
('MXT', 'MODULE_ID_FK', 2, 'MODULE', 'MODULE_ID', 'MODULE_ID', 'MODULE_ID'),
('MXT', 'MODULE_LANGUAGE_FK', 1, 'MODULE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PARAMETER_COMPANY_FK', 1, 'PARAMETER', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARAMETER_ID_FK', 1, 'PARAMETER', 'PARAMETER_ID', 'PARAMETER_ID', 'PARAMETER_ID'),
('MXT', 'PARTNER_COMPANY_FK', 1, 'PARTNER', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARTNER_GROUP_COMPANY_FK', 1, 'PARTNER_GROUP', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PARTNER_GROUP_ID_FK', 1, 'PARTNER_GROUP', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID', 'PARTNER_GROUP_ID'),
('MXT', 'PARTNER_GROUP_LANGUAGE_FK', 1, 'PARTNER_GROUP', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PARTNER_GROUP_MEMBER_ID_FK', 1, 'PARTNER_GROUP_MEMBER', 'PARTNER_GROUP_MEMBER_ID', 'PARTNER_GROUP_MEMBER_ID', 'PARTNER_GROUP_MEMBER_ID'),
('MXT', 'PARTNER_ID_FK', 1, 'PARTNER', 'PARTNER_ID', 'PARTNER_ID', 'PARTNER_ID'),
('MXT', 'PARTNER_ID_FK', 2, 'PARTNER', 'USER_ID', 'PARTNER_ID', 'USER_ID'),
('MXT', 'PARTNER_LANGUAGE_FK', 1, 'PARTNER', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'PARTN_COMPANY_FK', 1, 'PARTNER_GROUP_MEMBER', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_ADDRESS_COMPANY_FK', 1, 'PERSON_ADDRESS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_COMPANY_FK', 1, 'PERSON', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'PERSON_ID_FK', 1, 'PERSON', 'PERSON_ID', 'PERSON_ID', 'PERSON_ID'),
('MXT', 'PERSON_ID_FK', 2, 'PERSON', 'DISTRICT_ID', 'PERSON_ID', 'DISTRICT_ID'),
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
('MXT', 'USER_AUTHORIZATION_COMPANY_FK', 1, 'USER_AUTHORIZATION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_NOTIFICATION_COMPANY_FK', 1, 'USER_NOTIFICATION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_NOTIFICATION_TYPE_ID_FK', 1, 'USER_NOTIFICATION_TYPE', 'USER_NOTIFICATION_TYPE_ID', 'USER_NOTIFICATION_TYPE_ID', 'USER_NOTIFICATION_TYPE_ID'),
('MXT', 'USER_PROFILE_COMPANY_FK', 1, 'USER_PROFILE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_SESSION_COMPANY_FK', 1, 'USER_SESSION', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_STATUS_COMPANY_FK', 1, 'USER_STATUS', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_STATUS_ID_FK', 1, 'USER_STATUS', 'USER_STATUS_ID', 'USER_STATUS_ID', 'USER_STATUS_ID'),
('MXT', 'USER_STATUS_LANGUAGE_FK', 1, 'USER_STATUS', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'USER_TYPE_COMPANY_FK', 1, 'USER_TYPE', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'USER_TYPE_ID_FK', 1, 'USER_TYPE', 'USER_TYPE_ID', 'USER_TYPE_ID', 'USER_TYPE_ID'),
('MXT', 'USER_TYPE_LANGUAGE_FK', 1, 'USER_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'USER__LANGUAGE_FK', 1, 'USER_NOTIFICATION_TYPE', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID'),
('MXT', 'ZONE_ASESSOR_COMPANY_FK', 1, 'ZONE_ASESSOR', 'COMPANY_ID', 'COMPANY', 'COMPANY_ID'),
('MXT', 'ZONE_ASESSOR_ID_FK', 1, 'ZONE_ASESSOR', 'USER_ID', 'ZONE_ASESSOR_ID', 'USER_ID'),
('MXT', 'ZONE_ASESSOR_ID_FK', 2, 'ZONE_ASESSOR', 'GEOGRAPHIC_ZONE_ID', 'ZONE_ASESSOR_ID', 'GEOGRAPHIC_ZONE_ID'),
('MXT', 'ZONE_ASESSOR_LANGUAGE_FK', 1, 'ZONE_ASESSOR', 'LANGUAGE_ID', 'LANGUAGE', 'LANGUAGE_ID');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_TABLE`
--

CREATE TABLE IF NOT EXISTS `ENTITY_TABLE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `TABLE_ID` varchar(30) NOT NULL,
  `HAS_TABLE_ID` varchar(1) NOT NULL,
  `PACKAGE_NAME` varchar(30) NOT NULL,
  `MULTI_COMPANY` varchar(1) NOT NULL DEFAULT '0',
  `MULTI_LANGUAGE` varchar(1) NOT NULL DEFAULT '0',
  `HISTORICAL_DATA` varchar(1) NOT NULL DEFAULT '0',
  `OPTIMISTIC_LOCKING` varchar(1) NOT NULL DEFAULT '0',
  `ENUMERATED_TYPES` varchar(1) NOT NULL DEFAULT '0',
  `CACHE_MEMORY` varchar(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`TABLE_ID`),
  KEY `ENTITY_TABLE_ID_FK` (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_TABLE`
--

INSERT INTO `ENTITY_TABLE` (`COMPANY_ID`, `TABLE_ID`, `HAS_TABLE_ID`, `PACKAGE_NAME`, `MULTI_COMPANY`, `MULTI_LANGUAGE`, `HISTORICAL_DATA`, `OPTIMISTIC_LOCKING`, `ENUMERATED_TYPES`, `CACHE_MEMORY`, `DESCRIPTION`) VALUES
('ALL', 'ADDRESS_TYPE', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of address types'),
('ALL', 'CITY', '1', 'parameter', '1', '1', '0', '0', '0', '0', 'Values of cities'),
('ALL', 'CIVIL_STATUS', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of civil statuses'),
('ALL', 'COMPANY', '0', 'common', '0', '0', '0', '0', '0', '0', 'Values of companies'),
('ALL', 'COMPONENT', '1', 'security', '1', '0', '0', '0', '0', '1', 'Values of components'),
('ALL', 'COUNTRY', '1', 'parameter', '1', '1', '0', '0', '0', '0', 'Values of countries'),
('ALL', 'CURRENCY', '1', 'microcredit', '1', '1', '1', '0', '0', '0', 'Currency'),
('ALL', 'DATABASE_TYPE', '0', 'common', '0', '0', '0', '0', '0', '0', 'Values of database types'),
('ALL', 'DATAFILE', '1', 'parameter', '1', '0', '1', '1', '0', '0', 'Values of datafiles'),
('ALL', 'DATAFILE_TYPE', '0', 'parameter', '0', '0', '0', '0', '1', '0', 'Values of datafile types'),
('ALL', 'DATA_TYPE', '0', 'common', '0', '0', '0', '0', '1', '0', 'Values of data types'),
('ALL', 'DISTRICT', '1', 'parameter', '1', '1', '0', '0', '0', '0', 'Values of districts'),
('ALL', 'ENTITY_FIELD', '1', 'common', '1', '0', '0', '0', '0', '0', 'Values of entity fields'),
('ALL', 'ENTITY_RELATIONSHIP', '0', 'common', '1', '0', '0', '0', '0', '0', 'Values of entity relationships'),
('ALL', 'ENTITY_TABLE', '1', 'common', '1', '0', '0', '0', '0', '0', 'Values of entity tables'),
('ALL', 'FREQUENCY', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Frecuency of payments'),
('ALL', 'FUNDS_DESTINATION', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Destination of funds'),
('ALL', 'GENDER_TYPE', '1', 'person', '0', '1', '0', '0', '1', '0', 'Values of gender types'),
('ALL', 'GEOGRAPHIC_ZONE', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Geographic zones'),
('ALL', 'HOST', '1', 'security', '1', '0', '1', '0', '0', '0', 'Values of hosts'),
('ALL', 'IDENTIFICATION_TYPE', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of identification types'),
('ALL', 'LANGUAGE', '0', 'common', '0', '0', '0', '0', '1', '0', 'Values of languages'),
('ALL', 'MODULE', '1', 'security', '1', '1', '0', '0', '0', '0', 'Values of modules'),
('ALL', 'PARAMETER', '1', 'parameter', '1', '0', '1', '0', '1', '1', 'Values of parameters'),
('ALL', 'PARTNER', '1', 'microcredit', '1', '1', '1', '0', '0', '0', 'Partners'),
('ALL', 'PARTNER_GROUP', '1', 'microcredit', '1', '1', '1', '0', '0', '0', 'Partner groups'),
('ALL', 'PARTNER_GROUP_MEMBER', '1', 'microcredit', '1', '0', '1', '1', '0', '0', 'Group Members'),
('ALL', 'PERSON', '1', 'person', '1', '0', '1', '1', '0', '0', 'Values of person'),
('ALL', 'PERSON_ADDRESS', '0', 'person', '1', '0', '1', '1', '0', '0', 'Values of person address'),
('ALL', 'PERSON_PHONE', '0', 'person', '1', '0', '1', '1', '0', '0', 'Values of person phones'),
('ALL', 'PERSON_TYPE', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of person types'),
('ALL', 'PHONE_TYPE', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of phone types'),
('ALL', 'PROCESS', '1', 'security', '1', '1', '1', '0', '0', '1', 'Values of processes'),
('ALL', 'PROCESS_COMPONENT', '0', 'security', '1', '0', '0', '0', '0', '1', 'Values of process components'),
('ALL', 'PRODUCT_ASESSOR', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Products per Asessor'),
('ALL', 'PRODUCT_MICROCREDIT', '1', 'microcredit', '1', '1', '1', '0', '0', '0', 'Products of microcredit'),
('ALL', 'PROFESSION_TYPE', '1', 'person', '1', '1', '0', '0', '0', '0', 'Values of profession types'),
('ALL', 'PROFILE', '1', 'security', '1', '1', '0', '0', '0', '0', 'Values of profiles'),
('ALL', 'PROVINCE', '1', 'parameter', '1', '1', '0', '0', '0', '0', 'Values of provinces'),
('ALL', 'QUOTA_TYPE', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Quota type'),
('ALL', 'RECOMMENDATION', '1', 'microcredit', '1', '1', '1', '1', '0', '0', 'Recommendation of microcredit'),
('ALL', 'RESPONSABILITY', '1', 'microcredit', '1', '1', '1', '0', '0', '0', 'Responsabilities of each partner'),
('ALL', 'RESPONSE', '1', 'parameter', '1', '1', '0', '0', '1', '1', 'Values of responses'),
('ALL', 'ROLE', '0', 'security', '1', '0', '1', '0', '0', '0', 'Values of roles'),
('ALL', 'SEQUENTIAL', '1', 'common', '1', '0', '0', '1', '1', '0', 'Values of sequences'),
('ALL', 'SOLICITUDE', '1', 'microcredit', '1', '1', '1', '1', '0', '0', 'Solicitude of microcredit'),
('ALL', 'SOLICITUDE_STATUS', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Solicitude status'),
('ALL', 'SUBSYSTEM', '1', 'security', '1', '1', '0', '0', '1', '0', 'Values of subsystems'),
('ALL', 'USER_ACCESS', '0', 'security', '1', '0', '1', '0', '0', '0', 'Values of user access'),
('ALL', 'USER_ACCOUNT', '1', 'security', '1', '0', '1', '1', '0', '0', 'Values of user accounts'),
('ALL', 'USER_AUTHORIZATION', '0', 'security', '1', '0', '1', '0', '0', '0', 'Values of user authorizations'),
('ALL', 'USER_NOTIFICATION', '0', 'security', '1', '0', '1', '0', '0', '0', 'Values of user notification'),
('ALL', 'USER_NOTIFICATION_TYPE', '1', 'security', '0', '1', '0', '0', '1', '0', 'Values of user notification types'),
('ALL', 'USER_PROFILE', '0', 'security', '1', '0', '1', '0', '0', '0', 'Values of user profiles'),
('ALL', 'USER_SESSION', '0', 'security', '1', '0', '1', '1', '0', '0', 'Values of user sessions'),
('ALL', 'USER_STATUS', '1', 'security', '1', '1', '0', '0', '1', '0', 'Values of user status'),
('ALL', 'USER_TYPE', '1', 'security', '1', '1', '0', '0', '1', '0', 'Values of user types'),
('ALL', 'ZONE_ASESSOR', '1', 'microcredit', '1', '1', '0', '0', '0', '0', 'Zones per Asessor');

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
('ADDRESS_TYPE'),
('ADDRESS_TYPE_ID'),
('CITY'),
('CITY_ID'),
('CIVIL_STATUS'),
('CIVIL_STATUS_ID'),
('COMPANY'),
('COMPONENT'),
('COMPONENT_ID'),
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
('MODULE'),
('MODULE_ID'),
('PARAMETER'),
('PARAMETER_ID'),
('PARTNER'),
('PARTNER_GROUP'),
('PARTNER_GROUP_ID'),
('PARTNER_GROUP_MEMBER'),
('PARTNER_GROUP_MEMBER_ID'),
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
('USER_AUTHORIZATION'),
('USER_NOTIFICATION'),
('USER_NOTIFICATION_TYPE'),
('USER_NOTIFICATION_TYPE_ID'),
('USER_PROFILE'),
('USER_SESSION'),
('USER_STATUS'),
('USER_STATUS_ID'),
('USER_TYPE'),
('USER_TYPE_ID'),
('ZONE_ASESSOR'),
('ZONE_ASESSOR_ID');

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
  `GEOGRAPHIC_ZONE_ID` varchar(6) NOT NULL,
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
  `GEOGRAPHIC_ZONE_ID` varchar(6) NOT NULL,
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
  `TIME_ZONE` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`HOST_ID`),
  KEY `HOST_ID_FK` (`HOST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `HOST`
--


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
('MXT', 'ES', 'A', '0', 'AUTENTITICACION'),
('MXT', 'ES', 'A', '1', 'DATOS GENERALES'),
('MXT', 'ES', 'A', '2', 'ROLES Y USUARIOS'),
('MXT', 'ES', 'C', '0', 'PARAMETRIZACION'),
('MXT', 'ES', 'C', '1', 'PLANIFICACION'),
('MXT', 'ES', 'C', '2', 'COMERCIALIZACION'),
('MXT', 'ES', 'C', '3', 'SOLICITUD'),
('MXT', 'ES', 'C', '4', 'APROBACION CORE'),
('MXT', 'ES', 'C', '5', 'SEGUIMIENTO Y RECUPERACION'),
('MXT', 'ES', 'G', '0', 'MENU'),
('MXT', 'ES', 'G', '1', 'PARAMETROS'),
('MXT', 'ES', 'G', '2', 'LISTAS PARA LOS COMBOS');

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
('C', '0'),
('C', '1'),
('C', '2'),
('C', '3'),
('C', '4'),
('C', '5'),
('G', '0'),
('G', '1'),
('G', '2');

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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER`
--

CREATE TABLE IF NOT EXISTS `PARTNER` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PARTNER_ID` varchar(10) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `ACTIVITY` varchar(300) DEFAULT NULL,
  `FREQUENCY_ID` varchar(3) DEFAULT NULL,
  `MEETING_DAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`PARTNER_ID`,`USER_ID`),
  KEY `PARTNER_FREQ_ID_FK` (`FREQUENCY_ID`),
  KEY `PARTNER_ID_FK` (`PARTNER_ID`,`USER_ID`),
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
  `PARTNER_GROUP_ID` varchar(15) NOT NULL,
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
  `PARTNER_GROUP_ID` varchar(15) NOT NULL,
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
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PARTNER_GROUP_MEMBER_ID` varchar(15) NOT NULL,
  `CREATED` datetime NOT NULL,
  `PARTNER_GROUP_ID` varchar(15) NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `RESPONSABILITY_ID` varchar(3) NOT NULL,
  `OBSERVATIONS` varchar(200) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PARTNER_GROUP_MEMBER_ID`),
  KEY `PARTNER_GROUP_MEMBER_ID_FK` (`PARTNER_GROUP_MEMBER_ID`),
  KEY `PARTNER_GRP_MEM_GRP_ID_FK` (`PARTNER_GROUP_ID`),
  KEY `PARTNER_GRP_MEM_PERSON_ID_FK` (`PERSON_ID`),
  KEY `PARTNER_GRP_MEM_RESP_ID_FK` (`RESPONSABILITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_GROUP_MEMBER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_GROUP_MEMBER_ID`
--

CREATE TABLE IF NOT EXISTS `PARTNER_GROUP_MEMBER_ID` (
  `PARTNER_GROUP_MEMBER_ID` varchar(15) NOT NULL,
  PRIMARY KEY (`PARTNER_GROUP_MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PARTNER_GROUP_MEMBER_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTNER_ID`
--

CREATE TABLE IF NOT EXISTS `PARTNER_ID` (
  `PARTNER_ID` varchar(10) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`PARTNER_ID`,`USER_ID`)
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
  `DISTRICT_ID` varchar(4) NOT NULL,
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
  `PROFESSION_TYPE_ID` varchar(4) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`DISTRICT_ID`),
  KEY `PERSON_CITY_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `PERSON_CIVIL_STATUS_ID_FK` (`CIVIL_STATUS_ID`),
  KEY `PERSON_GENDER_TYPE_ID_FK` (`GENDER_TYPE_ID`),
  KEY `PERSON_IDENTIF_TYPE_ID_FK` (`IDENTIFICATION_TYPE_ID`),
  KEY `PERSON_ID_FK` (`PERSON_ID`,`DISTRICT_ID`),
  KEY `PERSON_PROFESSION_TYPE_ID_FK` (`PROFESSION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_ADDRESS`
--

CREATE TABLE IF NOT EXISTS `PERSON_ADDRESS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `ADDRESS_SEQUENCE` tinyint(4) NOT NULL,
  `DISTRICT_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `ADDRESS_TYPE_ID` varchar(4) NOT NULL,
  `ADDRESS_DESCRIPTION` varchar(40) NOT NULL,
  `COUNTRY_ID` varchar(2) DEFAULT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`ADDRESS_SEQUENCE`,`DISTRICT_ID`),
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
  `DISTRICT_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`PERSON_ID`,`DISTRICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_ID`
--


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
  `ENABLE` varchar(1) NOT NULL DEFAULT '1',
  `MENU` varchar(1) NOT NULL DEFAULT '1',
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
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '0', '01', '2011-10-14 00:00:00', 'LOGGIN', '1', '0', 'A001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '01', '2011-10-14 00:00:00', 'ESTADO DE USUARIOS', '1', '1', 'A101', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '02', '2011-11-27 00:00:00', 'TIPOS DE USUARIO', '1', '1', 'A102', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '03', '2011-11-27 00:00:00', 'SUBSISTEMAS', '1', '1', 'A103', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '04', '2011-11-27 00:00:00', 'MODULOS', '1', '1', 'A104', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '1', '05', '2011-11-27 00:00:00', 'PROCESOS', '1', '1', 'A105', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '01', '2011-10-14 00:00:00', 'ROLES', '1', '1', 'A201', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '02', '2011-11-29 00:00:00', 'PROCESOS POR ROL', '1', '1', 'A202', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '03', '2011-11-30 00:00:00', 'USUARIOS', '1', '1', 'A203', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '04', '2011-11-30 00:00:00', 'ROLES POR USUARIO', '1', '1', 'A204', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '05', '2011-11-30 00:00:00', 'PASSWORD USUARIOS', '1', '1', 'A205', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'A', '2', '06', '2011-12-07 00:00:00', 'TERMINALES', '1', '1', 'A206', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '01', '2012-01-14 16:55:33', 'MONEDAS', '1', '1', 'C001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '02', '2012-01-14 16:55:34', 'ESTATUS SOLICITUD', '1', '1', 'C002', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '03', '2012-01-14 16:55:34', 'TIPOS DE CUOTA', '1', '1', 'C003', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '04', '2012-01-14 16:55:35', 'FRECUENCIAS', '1', '1', 'C004', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '0', '05', '2012-01-14 16:55:35', 'DESTINOS DE FONDOS', '1', '1', 'C005', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '01', '2012-01-08 00:00:00', 'ASESOR DE CREDITO', '1', '1', 'C001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '02', '2012-01-08 00:00:00', 'ZONAS GEOGRAFICAS', '1', '1', 'C102', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '03', '2012-01-08 00:00:00', 'ZONAS POR ASESOR', '1', '1', 'C003', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '04', '2012-01-08 00:00:00', 'PRODUCTOS', '1', '1', 'C004', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '1', '05', '2012-01-08 00:00:00', 'PRODUCTOS POR ASESOR', '1', '1', 'C005', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '2', '01', '2012-01-14 16:57:27', 'SOCIOS INDIVIDUALES', '1', '1', 'C201', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '2', '02', '2012-01-14 16:57:28', 'SOCIOS GRUPALES', '1', '1', 'C202', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '3', '01', '2012-01-14 16:58:40', 'SOLICITUD DE MICROCREDITO', '1', '1', 'C301', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'C', '3', '02', '2012-01-14 16:58:41', 'RECOMENDACION', '1', '1', 'C302', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '0', '01', '2011-10-14 00:00:00', 'MENU PRINCIPAL', '1', '1', 'G001', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '1', '01', '2011-10-14 00:00:00', 'PARAMETROS GENERALES', '1', '1', 'G101', NULL),
('MXT', 'ES', '9999-12-31 00:00:00', 'G', '2', '01', '2011-10-14 00:00:00', 'LISTA DE VALORES PARA LOS COMBOS', '1', '0', 'G201', NULL);

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
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  `AUTHORIZE` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`,`PROCESS_SEQUENCE`),
  KEY `PROCESS_COMP_COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `PROCESS_COMP_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS_COMPONENT`
--

INSERT INTO `PROCESS_COMPONENT` (`COMPANY_ID`, `SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`, `PROCESS_SEQUENCE`, `COMPONENT_ID`, `ENABLE`, `AUTHORIZE`) VALUES
('MXT', 'A', '0', '01', 1, 'mobile.bus.security.Loggin', '1', '0'),
('MXT', 'A', '0', '02', 1, 'mobile.bus.parameter.ParameterTest', '1', '0'),
('MXT', 'A', '2', '02', 1, 'mobile.core.processor.SpecialQueryProcessor', '1', '0'),
('MXT', 'A', '2', '02', 2, 'mobile.core.processor.MaintenanceProcessor', '1', '0'),
('MXT', 'G', '0', '01', 1, 'mobile.logic.general.MenuGenerator', '1', '0');

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
('A', '2', '01'),
('A', '2', '02'),
('A', '2', '03'),
('A', '2', '04'),
('A', '2', '05'),
('A', '2', '06'),
('C', '0', '01'),
('C', '0', '02'),
('C', '0', '03'),
('C', '0', '04'),
('C', '0', '05'),
('C', '1', '01'),
('C', '1', '02'),
('C', '1', '03'),
('C', '1', '04'),
('C', '1', '05'),
('C', '2', '01'),
('C', '2', '02'),
('C', '3', '01'),
('C', '3', '02'),
('G', '0', '01'),
('G', '1', '01'),
('G', '2', '01');

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
('MXT', 'ES', '0', 'NO CODIFICADO'),
('MXT', 'ES', '1', 'NO USAR'),
('MXT', 'ES', '10', 'ADMINISTRADOR DE SEGURIDAD'),
('MXT', 'ES', '100', 'DISENADOR DE INTERIORES'),
('MXT', 'ES', '101', 'DUENO TALLER'),
('MXT', 'ES', '102', 'DUENO TALLER REJAS'),
('MXT', 'ES', '103', 'DUENO TIENDA EFECTOS AGRICULTU'),
('MXT', 'ES', '104', 'DUENO TIENDA VENTA ANIMALES'),
('MXT', 'ES', '105', 'DUEÑO CO. LIMPIEZA ALFOMBRAS'),
('MXT', 'ES', '106', 'DUEÑO COLMADO O TIENDA'),
('MXT', 'ES', '107', 'DUEÑO COMPANIA CONSTRUCCION'),
('MXT', 'ES', '108', 'DUEÑO COMPANIA SELLADO TECHOS'),
('MXT', 'ES', '109', 'DUEÑO CUADRA CABALLOS'),
('MXT', 'ES', '11', 'ADMINISTRADOR SEGUROS DE SALUD'),
('MXT', 'ES', '110', 'DUEÑO DE LICORERIA'),
('MXT', 'ES', '111', 'DUEÑO DE RESTAURANTE'),
('MXT', 'ES', '112', 'DUEÑO FUNERARIA'),
('MXT', 'ES', '113', 'DUEÑO RESTAURANTE MOBIL'),
('MXT', 'ES', '114', 'DUEÑO SUPERMERCADO'),
('MXT', 'ES', '115', 'EBANISTA'),
('MXT', 'ES', '116', 'ECONOMISTA'),
('MXT', 'ES', '117', 'ELECTRICISTA'),
('MXT', 'ES', '118', 'EMPACADOR'),
('MXT', 'ES', '119', 'EMPLEADO CASINO'),
('MXT', 'ES', '12', 'AGENTE DE RENTAS INTERNAS'),
('MXT', 'ES', '120', 'EMPLEADO CENTRO CUIDADO NIÑOS'),
('MXT', 'ES', '121', 'EMPLEADO DE ALMACEN'),
('MXT', 'ES', '122', 'EMPLEADO DE CONSTRUCCION'),
('MXT', 'ES', '123', 'EMPLEADO DE FARMACIA'),
('MXT', 'ES', '124', 'EMPLEADO DE MANTENIMIENTO'),
('MXT', 'ES', '125', 'EMPLEADO DE MUEBLERIA'),
('MXT', 'ES', '126', 'EMPLEADO DE MUSEO'),
('MXT', 'ES', '127', 'EMPLEADO DE OBRAS PUBLICAS'),
('MXT', 'ES', '128', 'EMPLEADO DE PUERTOS'),
('MXT', 'ES', '129', 'EMPLEADO DE RECREACION Y DEP.'),
('MXT', 'ES', '13', 'AGENTE DE SEGUROS'),
('MXT', 'ES', '130', 'EMPLEADO DE RESTAURANTE'),
('MXT', 'ES', '131', 'EMPLEADO DE SUPERMERCADO'),
('MXT', 'ES', '132', 'EMPLEADO DEPT SALUD'),
('MXT', 'ES', '133', 'EMPLEADO DEPT. FAMILIA'),
('MXT', 'ES', '134', 'EMPLEADO DOMESTICO'),
('MXT', 'ES', '135', 'EMPLEADO ESTACION DE GASOLINA'),
('MXT', 'ES', '136', 'EMPLEADO LAVANDERIA'),
('MXT', 'ES', '137', 'EMPLEADO PANADERIA'),
('MXT', 'ES', '138', 'EMPLEADO PERIODICO'),
('MXT', 'ES', '139', 'EMPLEADO POSTAL'),
('MXT', 'ES', '14', 'AGENTE DE VIAJES'),
('MXT', 'ES', '140', 'EMPLEADO TIENDA PIEZAS DE AUTO'),
('MXT', 'ES', '141', 'ENCARGADA DE COMEDOR ESCOLAR'),
('MXT', 'ES', '142', 'ENFERMERA'),
('MXT', 'ES', '143', 'ENTRENADOR DE ANIMALES'),
('MXT', 'ES', '144', 'ENTRENADOR PERSONAL'),
('MXT', 'ES', '145', 'ESCRITOR'),
('MXT', 'ES', '146', 'ESCULTOR'),
('MXT', 'ES', '147', 'ESPECIALISTA DE PROT. AMBIENTE'),
('MXT', 'ES', '148', 'ESPECIALISTA EN TELECOMUNICACI'),
('MXT', 'ES', '149', 'ESTACION DE GASOLINA'),
('MXT', 'ES', '15', 'AGENTE FEDERAL'),
('MXT', 'ES', '150', 'ESTIVADOR'),
('MXT', 'ES', '151', 'ESTUDIANTE'),
('MXT', 'ES', '152', 'EXCAVADOR'),
('MXT', 'ES', '153', 'FARMACEUTICO'),
('MXT', 'ES', '154', 'FARMACIA'),
('MXT', 'ES', '155', 'FERRETERIA'),
('MXT', 'ES', '156', 'FISCAL'),
('MXT', 'ES', '157', 'FISICO'),
('MXT', 'ES', '158', 'FOTOGRAFO'),
('MXT', 'ES', '159', 'FUERZAS ARMADAS'),
('MXT', 'ES', '16', 'AGRICULTOR'),
('MXT', 'ES', '160', 'FUMIGADOR'),
('MXT', 'ES', '161', 'GEOLOGO'),
('MXT', 'ES', '162', 'GERENTE'),
('MXT', 'ES', '163', 'GERENTE ADMINISTRACION PUBLICA'),
('MXT', 'ES', '164', 'GERENTE DE IMPRENTA'),
('MXT', 'ES', '165', 'GERENTE DE INVENTARIO'),
('MXT', 'ES', '166', 'GERENTE DE MANUFACTURA'),
('MXT', 'ES', '167', 'GERENTE DE RECURSOS HUMANOS'),
('MXT', 'ES', '168', 'GERENTE FINANCIERO'),
('MXT', 'ES', '169', 'GERENTE SUCURSAL COOPERATIVA'),
('MXT', 'ES', '17', 'AGRIMENSOR'),
('MXT', 'ES', '170', 'GERENTE SUPERMERCADO'),
('MXT', 'ES', '171', 'GERENTE VENTAS Y MERCADEO'),
('MXT', 'ES', '172', 'GUARDA BOSQUES'),
('MXT', 'ES', '173', 'GUARDIA DE SEGURIDAD'),
('MXT', 'ES', '174', 'GUARDIA ESCOLAR'),
('MXT', 'ES', '175', 'GUIA TURISTICO'),
('MXT', 'ES', '176', 'HIDROLOGO'),
('MXT', 'ES', '177', 'HOJALATERO'),
('MXT', 'ES', '178', 'IGLESIA CATOLICA'),
('MXT', 'ES', '179', 'IGLESIA LUTERANO'),
('MXT', 'ES', '18', 'AGRONOMO'),
('MXT', 'ES', '180', 'IGLESIA PROTESTANTE'),
('MXT', 'ES', '181', 'INCAPACITADO'),
('MXT', 'ES', '182', 'INGENIERO'),
('MXT', 'ES', '183', 'INGENIERO AEROESPACIAL'),
('MXT', 'ES', '184', 'INGENIERO AERONAUTICO'),
('MXT', 'ES', '185', 'INGENIERO AGRICULTOR'),
('MXT', 'ES', '186', 'INGENIERO AMBIENTAL'),
('MXT', 'ES', '187', 'INGENIERO BIOMEDICO'),
('MXT', 'ES', '188', 'INGENIERO CIVIL'),
('MXT', 'ES', '189', 'INGENIERO COMERCIAL'),
('MXT', 'ES', '19', 'AJUSTADOR EXTERNO'),
('MXT', 'ES', '190', 'INGENIERO DE PETROLEO'),
('MXT', 'ES', '191', 'INGENIERO DEL AMBIENTE'),
('MXT', 'ES', '192', 'INGENIERO ELECTRICO'),
('MXT', 'ES', '193', 'INGENIERO ELECTRONICO'),
('MXT', 'ES', '194', 'INGENIERO EN COMPUTADORAS'),
('MXT', 'ES', '195', 'INGENIERO INDUSTRIAL'),
('MXT', 'ES', '196', 'INGENIERO MARINO'),
('MXT', 'ES', '197', 'INGENIERO MECANICO'),
('MXT', 'ES', '198', 'INGENIERO NUCLEAR'),
('MXT', 'ES', '199', 'INGENIERO QUIMICO'),
('MXT', 'ES', '2', 'ABOGADO'),
('MXT', 'ES', '20', 'ALBAÑIL'),
('MXT', 'ES', '200', 'INSPECTOR DE ALIMENTOS'),
('MXT', 'ES', '201', 'INSPECTOR DE DROGAS Y ALCOHOL'),
('MXT', 'ES', '202', 'INSPECTOR DE INMIGRACION'),
('MXT', 'ES', '203', 'INSPECTOR DE MANUFACTURA'),
('MXT', 'ES', '204', 'INSPECTOR DE SALUD PUBLICA'),
('MXT', 'ES', '205', 'INSPECTOR DE SERVICIO PUBLICO'),
('MXT', 'ES', '206', 'INSTALADOR'),
('MXT', 'ES', '207', 'INSTITUCION FINANCIERA'),
('MXT', 'ES', '208', 'INSTRUCTOR DEPORTES'),
('MXT', 'ES', '209', 'INTERPRETE'),
('MXT', 'ES', '21', 'AMA DE CASA'),
('MXT', 'ES', '210', 'INVESTIGADOR CRIMINAL'),
('MXT', 'ES', '211', 'INVESTIGADOR DE SEG. AEREA'),
('MXT', 'ES', '212', 'INVESTIGADOR GENERAL'),
('MXT', 'ES', '213', 'INVESTIGADOR PRIVADO'),
('MXT', 'ES', '214', 'INVESTIGADOR SEG. AEREA'),
('MXT', 'ES', '215', 'JARDINERO'),
('MXT', 'ES', '216', 'JEFE DE CORREO'),
('MXT', 'ES', '217', 'JOYERO'),
('MXT', 'ES', '218', 'JUEZ'),
('MXT', 'ES', '219', 'LAVAPLATOS'),
('MXT', 'ES', '22', 'AMA DE LLAVES'),
('MXT', 'ES', '220', 'LIDER RECREATIVO'),
('MXT', 'ES', '221', 'LOCUTOR'),
('MXT', 'ES', '222', 'MAESTRO DE ESCUELA'),
('MXT', 'ES', '223', 'MANICURISTA'),
('MXT', 'ES', '224', 'MAQUILLISTA'),
('MXT', 'ES', '225', 'MARINO'),
('MXT', 'ES', '226', 'MATEMATICO'),
('MXT', 'ES', '227', 'MECANICO DE AUTOS'),
('MXT', 'ES', '228', 'MECANICO DE AVIONES'),
('MXT', 'ES', '229', 'MECANICO DE EQUIPO INDUSTRIAL'),
('MXT', 'ES', '23', 'ANALISTA DE DERECHOS CIVILES'),
('MXT', 'ES', '230', 'MECANICO DE TRENES'),
('MXT', 'ES', '231', 'MECANICO INDUSTRIAL'),
('MXT', 'ES', '232', 'MEDICO'),
('MXT', 'ES', '233', 'MENOR DE EDAD'),
('MXT', 'ES', '234', 'MENSAJERO'),
('MXT', 'ES', '235', 'METEOROLOGO'),
('MXT', 'ES', '236', 'METEOROLOGO'),
('MXT', 'ES', '237', 'MICROBIOLOGO'),
('MXT', 'ES', '238', 'MINISTRO DE IGLESIA'),
('MXT', 'ES', '239', 'MODELO'),
('MXT', 'ES', '24', 'ANALISTA DE PRESUPUESTO'),
('MXT', 'ES', '240', 'MODISTA'),
('MXT', 'ES', '241', 'MOZO'),
('MXT', 'ES', '242', 'MUSICO'),
('MXT', 'ES', '243', 'OFICIAL DE COMPRAS'),
('MXT', 'ES', '244', 'OFICIAL DE CORRECCION'),
('MXT', 'ES', '245', 'OFICIAL DE CUMPLIMIENTO'),
('MXT', 'ES', '246', 'OFICIAL DE CUSTODIA'),
('MXT', 'ES', '247', 'OFICIAL DE OPERACIONES'),
('MXT', 'ES', '248', 'OFICIAL DE PRESTAMOS'),
('MXT', 'ES', '249', 'OFICIAL DE RENTAS INTERNAS'),
('MXT', 'ES', '25', 'ANALISTA DE SISTEMAS'),
('MXT', 'ES', '250', 'OFICIAL DE TRAFICO'),
('MXT', 'ES', '251', 'OFICIAL RECURSOS NATURALES'),
('MXT', 'ES', '252', 'OFICIAL SERVICIO JUVENIL'),
('MXT', 'ES', '253', 'OFICINISTA'),
('MXT', 'ES', '254', 'OPERADOR CAMIONES BLINDADOS'),
('MXT', 'ES', '255', 'OPERADOR DE ASCENSORES'),
('MXT', 'ES', '256', 'OPERADOR DE COMPUTADORAS'),
('MXT', 'ES', '257', 'OPERADOR DE EQUIPO DE T.V.'),
('MXT', 'ES', '258', 'OPERADOR DE FABRICAS'),
('MXT', 'ES', '259', 'OPERADOR DE MANUFACTURA'),
('MXT', 'ES', '26', 'ANALISTA DE TRANSP. INDUSTRIAL'),
('MXT', 'ES', '260', 'OPERADOR DE MAQUINARIA'),
('MXT', 'ES', '261', 'OPERADOR DE RADIO'),
('MXT', 'ES', '262', 'OPERADOR DE TELEFONOS'),
('MXT', 'ES', '263', 'OPERADOR DE TELEGRAFO'),
('MXT', 'ES', '264', 'OPERADOR EN LA A.A.A.'),
('MXT', 'ES', '265', 'OPERADOR EN LA A.E.E.'),
('MXT', 'ES', '266', 'OPERADOR EQUIPO PESADO'),
('MXT', 'ES', '267', 'OPTICO'),
('MXT', 'ES', '268', 'OPTOMETRA'),
('MXT', 'ES', '269', 'PARALEGAL'),
('MXT', 'ES', '27', 'ANALISTA FINANCIERO'),
('MXT', 'ES', '270', 'PATOLOGO'),
('MXT', 'ES', '271', 'PATOLOGO DEL HABLA'),
('MXT', 'ES', '272', 'PELOTERO PROFESIONAL'),
('MXT', 'ES', '273', 'PENSIONADO'),
('MXT', 'ES', '274', 'PESCADOR'),
('MXT', 'ES', '275', 'PILOTO DE AVIACION'),
('MXT', 'ES', '276', 'PINTOR'),
('MXT', 'ES', '277', 'PLOMERO'),
('MXT', 'ES', '278', 'POETA'),
('MXT', 'ES', '279', 'POLICIA'),
('MXT', 'ES', '28', 'ANALISTA QUIMICO'),
('MXT', 'ES', '280', 'PORTEADOR PUBLICO'),
('MXT', 'ES', '281', 'PRESIDENTE EJECUTIVO COOP'),
('MXT', 'ES', '282', 'PROCESADOR DE DATA'),
('MXT', 'ES', '283', 'PROCESADOR DE PRESTAMOS'),
('MXT', 'ES', '284', 'PRODUCTOR DE TELEVISION'),
('MXT', 'ES', '285', 'PROFESOR UNIVERSITARIO'),
('MXT', 'ES', '286', 'PROGRAMADOR DE COMPUTADORAS'),
('MXT', 'ES', '287', 'PSICOLOGO'),
('MXT', 'ES', '288', 'PUBLICISTA'),
('MXT', 'ES', '289', 'PULIDOR BANERAS'),
('MXT', 'ES', '29', 'ANESTESIOLOGO'),
('MXT', 'ES', '290', 'PULIDOR DE PISOS'),
('MXT', 'ES', '291', 'QUIMICO'),
('MXT', 'ES', '292', 'RECEPCIONISTA'),
('MXT', 'ES', '293', 'RELACIONISTA PUBLICO'),
('MXT', 'ES', '294', 'RELOJERO'),
('MXT', 'ES', '295', 'REPORTERO'),
('MXT', 'ES', '296', 'REPOSTERO'),
('MXT', 'ES', '297', 'REPRESENTANTE DE SERVICIOS'),
('MXT', 'ES', '298', 'REPRESENTANTE DE VENTAS'),
('MXT', 'ES', '299', 'RETIRADO'),
('MXT', 'ES', '3', 'ACTOR/ACTRIZ DRAMATICO'),
('MXT', 'ES', '30', 'ANTROPOLOGO'),
('MXT', 'ES', '300', 'SACERDOTE'),
('MXT', 'ES', '301', 'SALVAVIDAS'),
('MXT', 'ES', '302', 'SECRETARIA'),
('MXT', 'ES', '303', 'SECRETARIA DE CORTE'),
('MXT', 'ES', '304', 'SECRETARIA LEGAL'),
('MXT', 'ES', '305', 'SECRETARIA MEDICA'),
('MXT', 'ES', '306', 'SELLADOR DE TECHOS'),
('MXT', 'ES', '307', 'SOLDADOR'),
('MXT', 'ES', '308', 'SOLDADOR'),
('MXT', 'ES', '309', 'SONOGRAFISTA'),
('MXT', 'ES', '31', 'ARQUEOLOGO'),
('MXT', 'ES', '310', 'SUPERMERCADO'),
('MXT', 'ES', '311', 'SUPERVISOR'),
('MXT', 'ES', '312', 'SUPERVISOR DE FARMACIA'),
('MXT', 'ES', '313', 'SUPERVISOR DE PERSONAL'),
('MXT', 'ES', '314', 'SUPERVISOR DEPTO. COOPERATIVA'),
('MXT', 'ES', '315', 'TASADOR'),
('MXT', 'ES', '316', 'TECNICO DE COMPUTADORAS'),
('MXT', 'ES', '317', 'TECNICO DE COMUNICACIONES'),
('MXT', 'ES', '318', 'TECNICO DE CONTABILIDAD'),
('MXT', 'ES', '319', 'TECNICO DE EDUCACION'),
('MXT', 'ES', '32', 'ARQUITECTO'),
('MXT', 'ES', '320', 'TECNICO DE EMERGENCIAS MEDICAS'),
('MXT', 'ES', '321', 'TECNICO DE IMPUESTOS'),
('MXT', 'ES', '322', 'TECNICO DE INGENIERIA'),
('MXT', 'ES', '323', 'TECNICO DE INGENIERIA INDUSTRI'),
('MXT', 'ES', '324', 'TECNICO DE RAYOS X'),
('MXT', 'ES', '325', 'TECNICO DE RECORD MEDICO'),
('MXT', 'ES', '326', 'TECNICO DE REFRIGERACION'),
('MXT', 'ES', '327', 'TECNICO DE SALUD AMBIENTAL'),
('MXT', 'ES', '328', 'TECNICO DE SONIDO'),
('MXT', 'ES', '329', 'TECNICO DESARROLLO INDUSTRIAL'),
('MXT', 'ES', '33', 'ARQUITECTO NAVAL'),
('MXT', 'ES', '330', 'TECNICO ELECTRONICO'),
('MXT', 'ES', '331', 'TECNICO EN GOMAS'),
('MXT', 'ES', '332', 'TECNICO RECURSOS HUMANOS'),
('MXT', 'ES', '333', 'TECNICO SALA OPERACIONES'),
('MXT', 'ES', '334', 'TECNICO VETERINARIO'),
('MXT', 'ES', '335', 'TECNOLOGO DENTAL'),
('MXT', 'ES', '336', 'TECNOLOGO MEDICO'),
('MXT', 'ES', '337', 'TENEDOR DE LIBROS'),
('MXT', 'ES', '338', 'TERAPISTA EDUCACIONAL'),
('MXT', 'ES', '339', 'TERAPISTA FISICO'),
('MXT', 'ES', '34', 'ARTESANO'),
('MXT', 'ES', '340', 'TERAPISTA OCUPACIONAL'),
('MXT', 'ES', '341', 'TERAPISTA RESPIRATORIO'),
('MXT', 'ES', '342', 'TIENDA DE LICORES'),
('MXT', 'ES', '343', 'TRABAJADOR SOCIAL'),
('MXT', 'ES', '344', 'VENDEDOR AGUA EMBOTELLADA'),
('MXT', 'ES', '345', 'VENDEDOR BILLETES DE LOTERIA'),
('MXT', 'ES', '346', 'VENDEDOR DE AUTOS'),
('MXT', 'ES', '347', 'VENDEDOR DE MUEBLES'),
('MXT', 'ES', '348', 'VENDEDOR DE PROD. AGRICULTURA'),
('MXT', 'ES', '349', 'VENDEDOR DE PRODUCTOS DE IMPRE'),
('MXT', 'ES', '35', 'ARTISTA COMERCIAL'),
('MXT', 'ES', '350', 'VENDEDOR DE PRODUCTOS QUIMICOS'),
('MXT', 'ES', '351', 'VENDEDOR EQUIPO COMERCIAL'),
('MXT', 'ES', '352', 'VENDEDOR EQUIPO MEDICO'),
('MXT', 'ES', '353', 'VENDEDOR PRODUCTOS INTERNET'),
('MXT', 'ES', '354', 'VENDEDOR TIENDA DE ROPA'),
('MXT', 'ES', '355', 'VETERINARIO'),
('MXT', 'ES', '356', 'VICEPRESIDENTE DE FINANZAS'),
('MXT', 'ES', '357', 'VICEPRESIDENTE DE OPERACIONES'),
('MXT', 'ES', '358', 'ZAPATERO'),
('MXT', 'ES', '359', 'ZOOLOGO'),
('MXT', 'ES', '36', 'ARTISTA GRAFICO'),
('MXT', 'ES', '37', 'ARTISTA GRAFICO'),
('MXT', 'ES', '38', 'ASIST. PROTECCION DE AMBIENTE'),
('MXT', 'ES', '39', 'ASISTENTE ADMINISTRATIVO'),
('MXT', 'ES', '4', 'ADMINISTRADOR'),
('MXT', 'ES', '40', 'ASISTENTE DE GERENTE'),
('MXT', 'ES', '41', 'ASISTENTE DE MAESTRO'),
('MXT', 'ES', '42', 'ASISTENTE DE PRESUPUESTO'),
('MXT', 'ES', '43', 'ASISTENTE DE PROTECCION AMBIEN'),
('MXT', 'ES', '44', 'ASISTENTE DE SEGURIDAD'),
('MXT', 'ES', '45', 'ASISTENTE DE TRAFICO AEREO'),
('MXT', 'ES', '46', 'ASISTENTE DE VUELO'),
('MXT', 'ES', '47', 'ASISTENTE DENTAL'),
('MXT', 'ES', '48', 'ASISTENTE INVESTIGATIVO'),
('MXT', 'ES', '49', 'ASISTENTE TERAPEUTICA'),
('MXT', 'ES', '5', 'ADMINISTRADOR DE CEMENTERIOS'),
('MXT', 'ES', '50', 'ASTRONOMO'),
('MXT', 'ES', '51', 'ATLETA'),
('MXT', 'ES', '52', 'AUDITOR'),
('MXT', 'ES', '53', 'AUDITOR DE IMPUESTOS'),
('MXT', 'ES', '54', 'AUDITOR DE INSTITUCIONES FINAN'),
('MXT', 'ES', '55', 'AUDITOR DE RECLAMOS'),
('MXT', 'ES', '56', 'AYUDANTE GENERAL'),
('MXT', 'ES', '57', 'BAILARIN'),
('MXT', 'ES', '58', 'BARBERO'),
('MXT', 'ES', '59', 'BENEFICIARIO DE SEGURO SOCIAL'),
('MXT', 'ES', '6', 'ADMINISTRADOR DE EDIFICIOS'),
('MXT', 'ES', '60', 'BIBLIOTECARIO'),
('MXT', 'ES', '61', 'BIENES RAICES'),
('MXT', 'ES', '62', 'BIOLOGO'),
('MXT', 'ES', '63', 'BIOLOGO MARINO'),
('MXT', 'ES', '64', 'BOMBERO'),
('MXT', 'ES', '65', 'CAJERO'),
('MXT', 'ES', '66', 'CANTINERO'),
('MXT', 'ES', '67', 'CAPATAZ'),
('MXT', 'ES', '68', 'CAPITAN DE BARCOS'),
('MXT', 'ES', '69', 'CARNICERO'),
('MXT', 'ES', '7', 'ADMINISTRADOR DE INST. CORRECC'),
('MXT', 'ES', '70', 'CARPINTERO'),
('MXT', 'ES', '71', 'CARTERO'),
('MXT', 'ES', '72', 'CARTOGRAFO'),
('MXT', 'ES', '73', 'CENTRO DE ENVEJECIENTES'),
('MXT', 'ES', '74', 'CHOFER'),
('MXT', 'ES', '75', 'CHOFER DE CAMIONES'),
('MXT', 'ES', '76', 'CHOFER DE TRENES'),
('MXT', 'ES', '77', 'CIRUJANO'),
('MXT', 'ES', '78', 'COBRADOR'),
('MXT', 'ES', '79', 'COCINERO'),
('MXT', 'ES', '8', 'ADMINISTRADOR DE PERSONAL'),
('MXT', 'ES', '80', 'COMERCIANTE'),
('MXT', 'ES', '81', 'COMITE CLASE GRADUADA'),
('MXT', 'ES', '82', 'CONSERJE'),
('MXT', 'ES', '83', 'CONTADOR'),
('MXT', 'ES', '84', 'CONTROL DE CALIDAD'),
('MXT', 'ES', '85', 'CONTROLADOR DE TRAFICO AEREO'),
('MXT', 'ES', '86', 'COORDINADOR DE ACTIVIDADES'),
('MXT', 'ES', '87', 'COORDINADOR SERV. A FAMILIA'),
('MXT', 'ES', '88', 'COSMETOLOGA'),
('MXT', 'ES', '89', 'COSTURERA'),
('MXT', 'ES', '9', 'ADMINISTRADOR DE RECORD MEDICO'),
('MXT', 'ES', '90', 'DEALER DE AUTOS'),
('MXT', 'ES', '91', 'DEALER DE BOTES'),
('MXT', 'ES', '92', 'DECORADOR'),
('MXT', 'ES', '93', 'DELINEANTE'),
('MXT', 'ES', '94', 'DENTISTA'),
('MXT', 'ES', '95', 'DESEMPLEADO'),
('MXT', 'ES', '96', 'DIETISTA'),
('MXT', 'ES', '97', 'DINAMITERO'),
('MXT', 'ES', '98', 'DIRECTOR ESCOLAR'),
('MXT', 'ES', '99', 'DISENADOR');

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
('0'),
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
('117'),
('118'),
('119'),
('12'),
('120'),
('121'),
('122'),
('123'),
('124'),
('125'),
('126'),
('127'),
('128'),
('129'),
('13'),
('130'),
('131'),
('132'),
('133'),
('134'),
('135'),
('136'),
('137'),
('138'),
('139'),
('14'),
('140'),
('141'),
('142'),
('143'),
('144'),
('145'),
('146'),
('147'),
('148'),
('149'),
('15'),
('150'),
('151'),
('152'),
('153'),
('154'),
('155'),
('156'),
('157'),
('158'),
('159'),
('16'),
('160'),
('161'),
('162'),
('163'),
('164'),
('165'),
('166'),
('167'),
('168'),
('169'),
('17'),
('170'),
('171'),
('172'),
('173'),
('174'),
('175'),
('176'),
('177'),
('178'),
('179'),
('18'),
('180'),
('181'),
('182'),
('183'),
('184'),
('185'),
('186'),
('187'),
('188'),
('189'),
('19'),
('190'),
('191'),
('192'),
('193'),
('194'),
('195'),
('196'),
('197'),
('198'),
('199'),
('2'),
('20'),
('200'),
('201'),
('202'),
('203'),
('204'),
('205'),
('206'),
('207'),
('208'),
('209'),
('21'),
('210'),
('211'),
('212'),
('213'),
('214'),
('215'),
('216'),
('217'),
('218'),
('219'),
('22'),
('220'),
('221'),
('222'),
('223'),
('224'),
('225'),
('226'),
('227'),
('228'),
('229'),
('23'),
('230'),
('231'),
('232'),
('233'),
('234'),
('235'),
('236'),
('237'),
('238'),
('239'),
('24'),
('240'),
('241'),
('242'),
('243'),
('244'),
('245'),
('246'),
('247'),
('248'),
('249'),
('25'),
('250'),
('251'),
('252'),
('253'),
('254'),
('255'),
('256'),
('257'),
('258'),
('259'),
('26'),
('260'),
('261'),
('262'),
('263'),
('264'),
('265'),
('266'),
('267'),
('268'),
('269'),
('27'),
('270'),
('271'),
('272'),
('273'),
('274'),
('275'),
('276'),
('277'),
('278'),
('279'),
('28'),
('280'),
('281'),
('282'),
('283'),
('284'),
('285'),
('286'),
('287'),
('288'),
('289'),
('29'),
('290'),
('291'),
('292'),
('293'),
('294'),
('295'),
('296'),
('297'),
('298'),
('299'),
('3'),
('30'),
('300'),
('301'),
('302'),
('303'),
('304'),
('305'),
('306'),
('307'),
('308'),
('309'),
('31'),
('310'),
('311'),
('312'),
('313'),
('314'),
('315'),
('316'),
('317'),
('318'),
('319'),
('32'),
('320'),
('321'),
('322'),
('323'),
('324'),
('325'),
('326'),
('327'),
('328'),
('329'),
('33'),
('330'),
('331'),
('332'),
('333'),
('334'),
('335'),
('336'),
('337'),
('338'),
('339'),
('34'),
('340'),
('341'),
('342'),
('343'),
('344'),
('345'),
('346'),
('347'),
('348'),
('349'),
('35'),
('350'),
('351'),
('352'),
('353'),
('354'),
('355'),
('356'),
('357'),
('358'),
('359'),
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
('MXT', 'ES', 'EC', 'AZ', 'Azuay'),
('MXT', 'ES', 'EC', 'BO', 'Bolivar'),
('MXT', 'ES', 'EC', 'CA', 'Carchi'),
('MXT', 'ES', 'EC', 'CH', 'Chimborazo'),
('MXT', 'ES', 'EC', 'CO', 'Cotopaxi'),
('MXT', 'ES', 'EC', 'CR', 'Canar'),
('MXT', 'ES', 'EC', 'EL', 'ElOro'),
('MXT', 'ES', 'EC', 'ES', 'Esmeraldas'),
('MXT', 'ES', 'EC', 'GA', 'Galapagos'),
('MXT', 'ES', 'EC', 'GU', 'Guayas'),
('MXT', 'ES', 'EC', 'IM', 'Imbabura'),
('MXT', 'ES', 'EC', 'LO', 'Loja'),
('MXT', 'ES', 'EC', 'MA', 'Manabi'),
('MXT', 'ES', 'EC', 'MO', 'Morona-Santiago'),
('MXT', 'ES', 'EC', 'NA', 'Napo'),
('MXT', 'ES', 'EC', 'OR', 'Orellana'),
('MXT', 'ES', 'EC', 'PA', 'Pastaza'),
('MXT', 'ES', 'EC', 'PI', 'Pichincha'),
('MXT', 'ES', 'EC', 'RI', 'LosRios'),
('MXT', 'ES', 'EC', 'SU', 'Sucumbios'),
('MXT', 'ES', 'EC', 'TU', 'Tungurahua'),
('MXT', 'ES', 'EC', 'ZA', 'Zamora-Chinchipe');

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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RECOMMENDATION`
--

CREATE TABLE IF NOT EXISTS `RECOMMENDATION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SOLICITUDE_ID` varchar(25) NOT NULL,
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
  `SOLICITUDE_ID` varchar(25) NOT NULL,
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
  `DAY_ID` varchar(3) NOT NULL,
  `CREATED` datetime NOT NULL,
  `HOUR_FROM` varchar(4) DEFAULT NULL,
  `HOUR_TO` varchar(4) DEFAULT NULL,
  `EDITABLE` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PROFILE_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`,`DAY_ID`),
  KEY `ROLE_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ROLE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOLICITUDE`
--

CREATE TABLE IF NOT EXISTS `SOLICITUDE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SOLICITUDE_ID` varchar(25) NOT NULL,
  `CREATED` datetime NOT NULL,
  `ACCOUNT` varchar(25) DEFAULT NULL,
  `ASSESSOR` varchar(20) NOT NULL,
  `PARTNER_CLIENT_ID` varchar(10) DEFAULT NULL,
  `GROUP_CLIENT_ID` varchar(10) DEFAULT NULL,
  `SOLICITUDE_DATE` datetime NOT NULL,
  `APPROVAL_DATE` datetime DEFAULT NULL,
  `DISBURSEMENT_DATE` datetime DEFAULT NULL,
  `EXPIRATION_DATE` datetime NOT NULL,
  `PRODUCT_ID` varchar(3) NOT NULL,
  `STATUS_ID` varchar(3) NOT NULL,
  `NUMBER_RENEWAL` int(11) NOT NULL,
  `AMOUNT` decimal(19,6) NOT NULL,
  `TERM` bigint(20) NOT NULL,
  `QUOTA_TYPE_ID` varchar(3) NOT NULL,
  `NUMBER_QUOTAS` int(11) NOT NULL,
  `PAYMENT_FREQUENCY_ID` varchar(3) NOT NULL,
  `FUNDS_DESTINATION_ID` varchar(3) NOT NULL,
  `DESTINATION_DESCRIPTION` varchar(3) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`SOLICITUDE_ID`),
  KEY `SOLICITUDE_FUNDS_DEST_ID_FK` (`FUNDS_DESTINATION_ID`),
  KEY `SOLICITUDE_GROUP_CLIENT_ID_FK` (`GROUP_CLIENT_ID`),
  KEY `SOLICITUDE_ID_FK` (`SOLICITUDE_ID`),
  KEY `SOLICITUDE_LANGUAGE_FK` (`LANGUAGE_ID`),
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
  `SOLICITUDE_ID` varchar(25) NOT NULL,
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
('MXT', 'ES', 'C', 'MICROCREDITO'),
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
  `USER_KEY` varchar(20) NOT NULL,
  `LAST_CHANGE` datetime NOT NULL,
  `QUESTION` varchar(100) DEFAULT NULL,
  `ANSWER` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`),
  KEY `USER_ACCESS_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_ACCESS`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_AUTHORIZATION`
--

CREATE TABLE IF NOT EXISTS `USER_AUTHORIZATION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `AUTHORIZER_ID` varchar(20) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `CREATED` datetime NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`,`AUTHORIZER_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `USER_AUTH_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `USER_AUTH_USER_ACCOUNT_ID_FK` (`USER_ID`),
  KEY `USER_AUTH_USER_AUTH_ID_FK` (`AUTHORIZER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_AUTHORIZATION`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_NOTIFICATION`
--

CREATE TABLE IF NOT EXISTS `USER_NOTIFICATION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATED` datetime NOT NULL,
  `USER_NOTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  `SUBJECT` varchar(100) NOT NULL,
  `MESSAGE` varchar(4000) NOT NULL,
  `READ_` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`),
  KEY `USER_NOTIF_USER_ACCOUNT_ID_FK` (`USER_ID`),
  KEY `USER_NOTIF_USER_NOT_TYPE_ID_FK` (`USER_NOTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_NOTIFICATION`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_NOTIFICATION_TYPE`
--

CREATE TABLE IF NOT EXISTS `USER_NOTIFICATION_TYPE` (
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_NOTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LANGUAGE_ID`,`USER_NOTIFICATION_TYPE_ID`),
  KEY `USER_NOTIFICATION_TYPE_ID_FK` (`USER_NOTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_NOTIFICATION_TYPE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_NOTIFICATION_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `USER_NOTIFICATION_TYPE_ID` (
  `USER_NOTIFICATION_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`USER_NOTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_NOTIFICATION_TYPE_ID`
--


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
  `TOKEN` varchar(100) NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`,`HOST_ID`),
  KEY `USER_SESSION_HOST_ID_FK` (`HOST_ID`),
  KEY `USER_SESS_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_SESSION`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ZONE_ASESSOR`
--

CREATE TABLE IF NOT EXISTS `ZONE_ASESSOR` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `GEOGRAPHIC_ZONE_ID` varchar(6) NOT NULL,
  `OBSERVATIONS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`USER_ID`,`GEOGRAPHIC_ZONE_ID`),
  KEY `ZONE_ASESSOR_ID_FK` (`USER_ID`,`GEOGRAPHIC_ZONE_ID`),
  KEY `ZONE_ASESSOR_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `ZONE_ASE_GEO_ZONE_ID_FK` (`GEOGRAPHIC_ZONE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ZONE_ASESSOR`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ZONE_ASESSOR_ID`
--

CREATE TABLE IF NOT EXISTS `ZONE_ASESSOR_ID` (
  `USER_ID` varchar(20) NOT NULL,
  `GEOGRAPHIC_ZONE_ID` varchar(6) NOT NULL,
  PRIMARY KEY (`USER_ID`,`GEOGRAPHIC_ZONE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ZONE_ASESSOR_ID`
--


--
-- Filtros para las tablas descargadas (dump)
--

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
  ADD CONSTRAINT `COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`),
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
  ADD CONSTRAINT `PARTNER_ID_FK` FOREIGN KEY (`PARTNER_ID`, `USER_ID`) REFERENCES `PARTNER_ID` (`PARTNER_ID`, `USER_ID`),
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
  ADD CONSTRAINT `PARTNER_GROUP_MEMBER_ID_FK` FOREIGN KEY (`PARTNER_GROUP_MEMBER_ID`) REFERENCES `PARTNER_GROUP_MEMBER_ID` (`PARTNER_GROUP_MEMBER_ID`),
  ADD CONSTRAINT `PARTNER_GRP_MEM_GRP_ID_FK` FOREIGN KEY (`PARTNER_GROUP_ID`) REFERENCES `PARTNER_GROUP_ID` (`PARTNER_GROUP_ID`),
  ADD CONSTRAINT `PARTNER_GRP_MEM_PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `PARTNER_GRP_MEM_RESP_ID_FK` FOREIGN KEY (`RESPONSABILITY_ID`) REFERENCES `RESPONSABILITY_ID` (`RESPONSABILITY_ID`),
  ADD CONSTRAINT `PARTN_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `PERSON`
--
ALTER TABLE `PERSON`
  ADD CONSTRAINT `PERSON_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`),
  ADD CONSTRAINT `PERSON_CIVIL_STATUS_ID_FK` FOREIGN KEY (`CIVIL_STATUS_ID`) REFERENCES `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`),
  ADD CONSTRAINT `PERSON_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_GENDER_TYPE_ID_FK` FOREIGN KEY (`GENDER_TYPE_ID`) REFERENCES `GENDER_TYPE_ID` (`GENDER_TYPE_ID`),
  ADD CONSTRAINT `PERSON_IDENTIF_TYPE_ID_FK` FOREIGN KEY (`IDENTIFICATION_TYPE_ID`) REFERENCES `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`, `DISTRICT_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`, `DISTRICT_ID`),
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
  ADD CONSTRAINT `PROCESS_COMP_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`),
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
-- Filtros para la tabla `USER_AUTHORIZATION`
--
ALTER TABLE `USER_AUTHORIZATION`
  ADD CONSTRAINT `USER_AUTHORIZATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_AUTH_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `USER_AUTH_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_AUTH_USER_AUTH_ID_FK` FOREIGN KEY (`AUTHORIZER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_NOTIFICATION`
--
ALTER TABLE `USER_NOTIFICATION`
  ADD CONSTRAINT `USER_NOTIFICATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_NOTIF_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_NOTIF_USER_NOT_TYPE_ID_FK` FOREIGN KEY (`USER_NOTIFICATION_TYPE_ID`) REFERENCES `USER_NOTIFICATION_TYPE_ID` (`USER_NOTIFICATION_TYPE_ID`);

--
-- Filtros para la tabla `USER_NOTIFICATION_TYPE`
--
ALTER TABLE `USER_NOTIFICATION_TYPE`
  ADD CONSTRAINT `USER_NOTIFICATION_TYPE_ID_FK` FOREIGN KEY (`USER_NOTIFICATION_TYPE_ID`) REFERENCES `USER_NOTIFICATION_TYPE_ID` (`USER_NOTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `USER__LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

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
  ADD CONSTRAINT `ZONE_ASESSOR_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ZONE_ASESSOR_ID_FK` FOREIGN KEY (`USER_ID`, `GEOGRAPHIC_ZONE_ID`) REFERENCES `ZONE_ASESSOR_ID` (`USER_ID`, `GEOGRAPHIC_ZONE_ID`),
  ADD CONSTRAINT `ZONE_ASESSOR_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `ZONE_ASE_GEO_ZONE_ID_FK` FOREIGN KEY (`GEOGRAPHIC_ZONE_ID`) REFERENCES `GEOGRAPHIC_ZONE_ID` (`GEOGRAPHIC_ZONE_ID`),
  ADD CONSTRAINT `ZONE_ASE_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

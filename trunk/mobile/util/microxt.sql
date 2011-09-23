-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 06-09-2011 a las 06:41:09
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `thesis3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADDRESS_TYPE`
--

CREATE TABLE IF NOT EXISTS `ADDRESS_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `ADDRESS_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
-- Estructura de tabla para la tabla `BRANCH`
--

CREATE TABLE IF NOT EXISTS `BRANCH` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `BRANCH_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`BRANCH_ID`),
  KEY `BRANCH_BRANCH_ID_FK` (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `BRANCH`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `BRANCH_ID`
--

CREATE TABLE IF NOT EXISTS `BRANCH_ID` (
  `BRANCH_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `BRANCH_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CHANNEL`
--

CREATE TABLE IF NOT EXISTS `CHANNEL` (
  `CHANNEL_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CHANNEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CHANNEL`
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `CITY_CITY_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`),
  KEY `CITY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CITY`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CIVIL_STATUS`
--

CREATE TABLE IF NOT EXISTS `CIVIL_STATUS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `CIVIL_STATUS_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`CIVIL_STATUS_ID`),
  KEY `CIVIL_STATUS_ID_FK` (`CIVIL_STATUS_ID`),
  KEY `CIVIL_STATUS_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CIVIL_STATUS`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPANY`
--

CREATE TABLE IF NOT EXISTS `COMPANY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `DATAFILE_ID` int(11) DEFAULT NULL,
  `UPGRADE_NUMBER` decimal(4,2) DEFAULT NULL,
  `LICENSE_DATE` datetime DEFAULT NULL,
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPANY`
--

INSERT INTO `COMPANY` (`COMPANY_ID`, `CREATED`, `NAME`, `DATAFILE_ID`, `UPGRADE_NUMBER`, `LICENSE_DATE`, `ENABLE`, `VERSION`) VALUES
('MXT', '2011-09-05 00:00:00', 'MICROXT', NULL, 1.00, NULL, '1', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT`
--

CREATE TABLE IF NOT EXISTS `COMPONENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `COMPONENT_ID` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `CLASS_NAME` varchar(100) NOT NULL,
  `METHOD_NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`COMPONENT_ID`),
  KEY `COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `COMPONENT_SUBSYSTEM_ID_FK` (`SUBSYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENT_ID`
--

CREATE TABLE IF NOT EXISTS `COMPONENT_ID` (
  `COMPONENT_ID` varchar(40) NOT NULL,
  PRIMARY KEY (`COMPONENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COMPONENT_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CONTROLLER`
--

CREATE TABLE IF NOT EXISTS `CONTROLLER` (
  `CONTROLLER_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `COMPONENT_ID` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CONTROLLER_ID`),
  KEY `CONTROLLER_COMPONENT_ID_FK` (`COMPONENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CONTROLLER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COUNTRY`
--

CREATE TABLE IF NOT EXISTS `COUNTRY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `COUNTRY_ID` varchar(2) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `AREA_CODE` varchar(4) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`),
  KEY `COUNTRY_COUNTRY_ID_FK` (`COUNTRY_ID`),
  KEY `COUNTRY_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `COUNTRY`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATABASE_TYPE`
--

CREATE TABLE IF NOT EXISTS `DATABASE_TYPE` (
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `DATABASE_ID` varchar(30) NOT NULL,
  `DATA_SIZE` smallint(6) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DATABASE_TYPE` varchar(30) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DATA_TYPE_ID`,`DATABASE_ID`,`DATA_SIZE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATABASE_TYPE`
--


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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`DATAFILE_ID`),
  KEY `DATAFILE_DATAFILE_ID_FK` (`DATAFILE_ID`),
  KEY `DATAFILE_DATAFILE_TYPE_FK` (`DATAFILE_TYPE_ID`)
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DATAFILE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATAFILE_TYPE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DATA_TYPE`
--

CREATE TABLE IF NOT EXISTS `DATA_TYPE` (
  `DATA_TYPE_ID` varchar(30) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DATA_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DATA_TYPE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DEPARTMENT`
--

CREATE TABLE IF NOT EXISTS `DEPARTMENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `BRANCH_ID` varchar(4) NOT NULL,
  `OFFICE_ID` varchar(4) NOT NULL,
  `DEPARTMENT_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`BRANCH_ID`,`OFFICE_ID`,`DEPARTMENT_ID`),
  KEY `DEPARTMENT_DEPARTMENT_ID_FK` (`BRANCH_ID`,`OFFICE_ID`,`DEPARTMENT_ID`),
  KEY `DEPARTMENT_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DEPARTMENT`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DEPARTMENT_ID`
--

CREATE TABLE IF NOT EXISTS `DEPARTMENT_ID` (
  `BRANCH_ID` varchar(4) NOT NULL,
  `OFFICE_ID` varchar(4) NOT NULL,
  `DEPARTMENT_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`BRANCH_ID`,`OFFICE_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DEPARTMENT_ID`
--


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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`),
  KEY `DISTRICT_DISTRICT_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`,`DISTRICT_ID`),
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
-- Estructura de tabla para la tabla `DOCUMENT_TYPE`
--

CREATE TABLE IF NOT EXISTS `DOCUMENT_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `DOCUMENT_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`DOCUMENT_TYPE_ID`),
  KEY `DOCUMENT_TYPE_ID_FK` (`DOCUMENT_TYPE_ID`),
  KEY `DOCUMENT_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DOCUMENT_TYPE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DOCUMENT_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `DOCUMENT_TYPE_ID` (
  `DOCUMENT_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`DOCUMENT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DOCUMENT_TYPE_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_FIELD`
--

CREATE TABLE IF NOT EXISTS `ENTITY_FIELD` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `TABLE_ID` varchar(30) NOT NULL,
  `FIELD_ID` varchar(30) NOT NULL,
  `CREATED` datetime NOT NULL,
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
  `SECURITY_LEVEL` tinyint(4) NOT NULL DEFAULT '0',
  `READONLY` varchar(1) NOT NULL DEFAULT '0',
  `HIDDEN` varchar(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  `TOOLTIP` varchar(100) DEFAULT NULL,
  `MASK` varchar(100) DEFAULT NULL,
  `VALIDATION` varchar(100) DEFAULT NULL,
  `CALCULATION` varchar(100) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`TABLE_ID`,`FIELD_ID`),
  KEY `ENTITY_FIELD_DATA_TYPE_FK` (`DATA_TYPE_ID`),
  KEY `ENTITY_FIELD_ID_FK` (`TABLE_ID`,`FIELD_ID`),
  KEY `ENTITY_FIELD_SEQUENTIAL_ID_FK` (`SEQUENTIAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_FIELD`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_RELATIONSHIP`
--

CREATE TABLE IF NOT EXISTS `ENTITY_RELATIONSHIP` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `RELATIONSHIP_ID` varchar(30) NOT NULL,
  `RELATIONSHIP_ORDER` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `TABLE_FROM` varchar(30) NOT NULL,
  `FIELD_FROM` varchar(30) NOT NULL,
  `TABLE_TO` varchar(30) NOT NULL,
  `FIELD_TO` varchar(30) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`RELATIONSHIP_ID`,`RELATIONSHIP_ORDER`),
  KEY `ENTITY_RELATIONSHIP_FROM_FK` (`TABLE_FROM`,`FIELD_FROM`),
  KEY `ENTITY_RELATIONSHIP_TO_FK` (`TABLE_TO`,`FIELD_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_RELATIONSHIP`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTITY_TABLE`
--

CREATE TABLE IF NOT EXISTS `ENTITY_TABLE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `TABLE_ID` varchar(30) NOT NULL,
  `CREATED` datetime NOT NULL,
  `HAS_TABLE_ID` varchar(1) NOT NULL,
  `PACKAGE_NAME` varchar(30) NOT NULL,
  `MULTI_COMPANY` varchar(1) NOT NULL DEFAULT '0',
  `MULTI_LANGUAGE` varchar(1) NOT NULL DEFAULT '0',
  `HISTORICAL_DATA` varchar(1) NOT NULL DEFAULT '0',
  `OPTIMISTIC_LOCKING` varchar(1) NOT NULL DEFAULT '0',
  `ENUMERATED_TYPES` varchar(1) NOT NULL DEFAULT '0',
  `CACHE_MEMORY` varchar(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(100) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`TABLE_ID`),
  KEY `ENTITY_TABLE_ID_FK` (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `ENTITY_TABLE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GENDER_TYPE`
--

CREATE TABLE IF NOT EXISTS `GENDER_TYPE` (
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `GENDER_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LANGUAGE_ID`,`GENDER_TYPE_ID`),
  KEY `GENDER_TYPE_ID_FK` (`GENDER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `GENDER_TYPE`
--


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
  `CHANNEL_ID` varchar(4) DEFAULT NULL,
  `BRANCH_ID` varchar(4) DEFAULT NULL,
  `OFFICE_ID` varchar(4) DEFAULT NULL,
  `TIME_ZONE` varchar(4) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`HOST_ID`),
  KEY `HOST_CHANNEL_ID_FK` (`CHANNEL_ID`),
  KEY `HOST_ID_FK` (`HOST_ID`),
  KEY `HOST_OFFICE_ID_FK` (`BRANCH_ID`,`OFFICE_ID`)
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`IDENTIFICATION_TYPE_ID`),
  KEY `IDENTIFICATION_TYPE_ID_FK` (`IDENTIFICATION_TYPE_ID`),
  KEY `IDENTIFICATION_TYPE_LANGUAG_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `IDENTIFICATION_TYPE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LANGUAGE`
--

CREATE TABLE IF NOT EXISTS `LANGUAGE` (
  `LANGUAGE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `LANGUAGE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_COPY`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_COPY` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `FIELD_NAME` varchar(40) NOT NULL,
  `MESSAGE_TYPE_COPY` varchar(4) NOT NULL,
  `FIELD_NAME_COPY` varchar(40) NOT NULL,
  `COPY_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `FIELD_VALUE` varchar(40) NOT NULL,
  `FIELD_VALUE_COPY` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`MESSAGE_TYPE_ID`,`FIELD_NAME`,`MESSAGE_TYPE_COPY`,`FIELD_NAME_COPY`,`COPY_SEQUENCE`),
  KEY `MESSAGE_COPY_MESS_TYPE_ID_FK` (`MESSAGE_TYPE_ID`),
  KEY `MESSAGE_COPY_TYPE_ID_FK` (`MESSAGE_TYPE_COPY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_COPY`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_FIELD`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_FIELD` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `FIELD_NAME` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `FIELD_ORDER` tinyint(4) NOT NULL,
  `FIELD_INIT` tinyint(4) DEFAULT NULL,
  `FIELD_LENGTH` tinyint(4) DEFAULT NULL,
  `FIELD_FILLED` varchar(1) DEFAULT NULL,
  `FIELD_ALIGN` varchar(1) DEFAULT NULL,
  `FIELD_VALUE` varchar(40) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`MESSAGE_TYPE_ID`,`FIELD_NAME`),
  KEY `MESSAGE_FIELD_MESS_TYPE_ID_FK` (`MESSAGE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_FIELD`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_LOG`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_LOG` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `MESSAGE_ID` varchar(100) NOT NULL,
  `MESSAGE_INOUT` varchar(1) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SERVICE_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `HOST_ID` varchar(40) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `PROCESSING_TIME` decimal(6,4) DEFAULT NULL,
  `MESSAGE_TEXT` varchar(4000) DEFAULT NULL,
  `RESPONSE_ID` varchar(8) DEFAULT NULL,
  `REVERSE_ID` varchar(100) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`MESSAGE_ID`,`MESSAGE_INOUT`),
  KEY `MESSAGE_LOG_HOST_ID_FK` (`HOST_ID`),
  KEY `MESSAGE_LOG_MESSAGE_TYPE_ID_FK` (`MESSAGE_TYPE_ID`),
  KEY `MESSAGE_LOG_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `MESSAGE_LOG_SERVICE_ID_FK` (`SERVICE_ID`),
  KEY `MESSAGE_LOG_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_LOG`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_MAP`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_MAP` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `FIELD_NAME` varchar(40) NOT NULL,
  `MESSAGE_TYPE_MAP` varchar(4) NOT NULL,
  `FIELD_NAME_MAP` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `OPERATION` varchar(1) NOT NULL,
  `CONSTANT` varchar(40) DEFAULT NULL,
  `COMPONENT_ID` varchar(40) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`MESSAGE_TYPE_ID`,`FIELD_NAME`,`MESSAGE_TYPE_MAP`,`FIELD_NAME_MAP`),
  KEY `MESSAGE_MAP_COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `MESSAGE_MAP_MESSAGE_TYPE_ID_FK` (`MESSAGE_TYPE_ID`),
  KEY `MESSAGE_MAP_TYPE_ID_FK` (`MESSAGE_TYPE_MAP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_MAP`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_ROUTER`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_ROUTER` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SERVICE_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `ROUTER_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SERVICE_ROUTER` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ROUTER` varchar(4) NOT NULL,
  `TIMEOUT` tinyint(4) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`SERVICE_ID`,`MESSAGE_TYPE_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`,`ROUTER_SEQUENCE`),
  KEY `MESSAGE_ROUTER_MESS_TYPE_ID_FK` (`MESSAGE_TYPE_ID`),
  KEY `MESSAGE_ROUTER_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `MESSAGE_ROUTER_SERVICE_ID_FK` (`SERVICE_ROUTER`),
  KEY `MESSAGE_ROUTER_SERV_ID_FK` (`SERVICE_ID`),
  KEY `MESSAGE_ROUTER_TYPE_ID_FK` (`MESSAGE_TYPE_ROUTER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_ROUTER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_TYPE`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `COMPONENT_ID` varchar(40) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`MESSAGE_TYPE_ID`),
  KEY `MESSAGE_TYPE_COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `MESSAGE_TYPE_ID_FK` (`MESSAGE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_TYPE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESSAGE_TYPE_ID`
--

CREATE TABLE IF NOT EXISTS `MESSAGE_TYPE_ID` (
  `MESSAGE_TYPE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`MESSAGE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MESSAGE_TYPE_ID`
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`SUBSYSTEM_ID`,`MODULE_ID`),
  KEY `MODULE_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`),
  KEY `MODULE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `MODULE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `OFFICE`
--

CREATE TABLE IF NOT EXISTS `OFFICE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `BRANCH_ID` varchar(4) NOT NULL,
  `OFFICE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `COUNTRY_ID` varchar(2) DEFAULT NULL,
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`BRANCH_ID`,`OFFICE_ID`),
  KEY `OFFICE_OFFICE_ID_FK` (`BRANCH_ID`,`OFFICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `OFFICE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `OFFICE_ID`
--

CREATE TABLE IF NOT EXISTS `OFFICE_ID` (
  `BRANCH_ID` varchar(4) NOT NULL,
  `OFFICE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`BRANCH_ID`,`OFFICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `OFFICE_ID`
--


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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PARAMETER_ID`),
  KEY `PARAMETER_DATA_TYPE_FK` (`DATA_TYPE_ID`),
  KEY `PARAMETER_PARAMETER_ID_FK` (`PARAMETER_ID`)
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
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `PROFESSION_TYPE_ID` varchar(4) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `FIRST_STREET` varchar(40) NOT NULL,
  `ADDRESS_NUMBER` varchar(40) NOT NULL,
  `SECOND_STREET` varchar(40) DEFAULT NULL,
  `BUILDING_NAME` varchar(40) DEFAULT NULL,
  `BUILDING_FLOOR` varchar(40) DEFAULT NULL,
  `COUNTRY_ID` varchar(2) DEFAULT NULL,
  `PROVINCE_ID` varchar(2) DEFAULT NULL,
  `CITY_ID` varchar(3) DEFAULT NULL,
  `ADDRESS_MAP` varchar(40) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`ADDRESS_SEQUENCE`),
  KEY `PERSON_ADDRESS_CITY_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`,`CITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_ADDRESS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERSON_DOCUMENT`
--

CREATE TABLE IF NOT EXISTS `PERSON_DOCUMENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `PERSON_ID` int(11) NOT NULL,
  `DOCUMENT_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DOCUMENT_TYPE_ID` varchar(4) NOT NULL,
  `DATAFILE_ID` int(11) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`PERSON_ID`,`DOCUMENT_SEQUENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_DOCUMENT`
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
(1);

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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PERSON_TYPE_ID`),
  KEY `PERSON_TYPE_ID_FK` (`PERSON_TYPE_ID`),
  KEY `PERSON_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PERSON_TYPE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PHONE_TYPE`
--

CREATE TABLE IF NOT EXISTS `PHONE_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PHONE_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PHONE_TYPE_ID`),
  KEY `PHONE_TYPE_ID_FK` (`PHONE_TYPE_ID`),
  KEY `PHONE_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PHONE_TYPE`
--


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
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  `URL` varchar(100) NOT NULL,
  `DATAFILE_ID` int(11) DEFAULT NULL,
  `WORKFLOW_ID` int(11) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`EXPIRED`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `PROCESS_DATAFILE_ID_FK` (`DATAFILE_ID`),
  KEY `PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `PROCESS_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `PROCESS_WORKFLOW_PROCESS_ID_FK` (`WORKFLOW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROCESS_COMPONENT`
--

CREATE TABLE IF NOT EXISTS `PROCESS_COMPONENT` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `CHANNEL_ID` varchar(4) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `PROCESS_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `COMPONENT_ID` varchar(40) NOT NULL,
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  `AUTHORIZE` varchar(1) NOT NULL DEFAULT '0',
  `DECISION_ID` varchar(4) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`CHANNEL_ID`,`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`,`PROCESS_SEQUENCE`),
  KEY `PROCESS_COMPONENT_CHANNE_ID_FK` (`CHANNEL_ID`),
  KEY `PROCESS_COMP_COMPONENT_ID_FK` (`COMPONENT_ID`),
  KEY `PROCESS_COMP_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `PROCESS_COMP_WORKF_DECIS_ID_FK` (`DECISION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROCESS_COMPONENT`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFESSION_TYPE`
--

CREATE TABLE IF NOT EXISTS `PROFESSION_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PROFESSION_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`PROFESSION_TYPE_ID`),
  KEY `PROFESSION_TYPE_ID_FK` (`PROFESSION_TYPE_ID`),
  KEY `PROFESSION_TYPE_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROFESSION_TYPE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROFILE`
--

CREATE TABLE IF NOT EXISTS `PROFILE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `PROFILE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`COUNTRY_ID`,`PROVINCE_ID`),
  KEY `PROVINCE_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `PROVINCE_PROVINCE_ID_FK` (`COUNTRY_ID`,`PROVINCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `PROVINCE`
--


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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESPONSE`
--

CREATE TABLE IF NOT EXISTS `RESPONSE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `RESPONSE_ID` varchar(8) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`RESPONSE_ID`),
  KEY `RESPONSE_LANGUAGE_FK` (`LANGUAGE_ID`),
  KEY `RESPONSE_RESPONSE_ID_FK` (`RESPONSE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `RESPONSE`
--


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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `CREATED` datetime NOT NULL,
  `SEQUENTIAL_VALUE` int(11) NOT NULL DEFAULT '0',
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`SEQUENTIAL_ID`)
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
-- Estructura de tabla para la tabla `SERVER`
--

CREATE TABLE IF NOT EXISTS `SERVER` (
  `SERVER_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`SERVER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERVER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SERVICE`
--

CREATE TABLE IF NOT EXISTS `SERVICE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SERVICE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `SERVER_ID` varchar(4) NOT NULL,
  `CHANNEL_ID` varchar(4) NOT NULL,
  `CONTROLLER_ID` varchar(4) NOT NULL,
  `ENABLE` varchar(1) NOT NULL DEFAULT '0',
  `ONLINE` varchar(1) NOT NULL DEFAULT '0',
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`SERVICE_ID`),
  KEY `SERVICE_CHANNEL_ID_FK` (`CHANNEL_ID`),
  KEY `SERVICE_CONTROLLER_ID_FK` (`CONTROLLER_ID`),
  KEY `SERVICE_ID_FK` (`SERVICE_ID`),
  KEY `SERVICE_SERVICE_ID_FK` (`SERVER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERVICE`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SERVICE_ID`
--

CREATE TABLE IF NOT EXISTS `SERVICE_ID` (
  `SERVICE_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERVICE_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SERVICE_PARAMETER`
--

CREATE TABLE IF NOT EXISTS `SERVICE_PARAMETER` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `SERVICE_ID` varchar(4) NOT NULL,
  `PARAMETER_ID` varchar(40) NOT NULL,
  `CREATED` datetime NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`SERVICE_ID`,`PARAMETER_ID`),
  KEY `SERVICE_PARAM_PARAMETER_ID_FK` (`PARAMETER_ID`),
  KEY `SERVICE_PARAM_SERVICE_ID_FK` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERVICE_PARAMETER`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SUBSYSTEM`
--

CREATE TABLE IF NOT EXISTS `SUBSYSTEM` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`LANGUAGE_ID`,`SUBSYSTEM_ID`),
  KEY `SUBSYSTEM_ID_FK` (`SUBSYSTEM_ID`),
  KEY `SUBSYSTEM_LANGUAGE_FK` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SUBSYSTEM`
--


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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`),
  KEY `USER_ACCESS_USER_ACCOUNT_ID_FK` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `USER_ACCESS`
--

INSERT INTO `USER_ACCESS` (`COMPANY_ID`, `EXPIRED`, `USER_ID`, `CREATED`, `USER_KEY`, `LAST_CHANGE`, `QUESTION`, `ANSWER`, `VERSION`) VALUES
('MXT', '9999-12-31 00:00:00', 'ADMIN', '2011-09-05 00:00:00', 'ADMIN', '2011-09-05 00:00:00', '???', '???', 0);

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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
('MXT', '9999-12-31 00:00:00', 'ADMIN', '2011-09-05 00:00:00', 'ADMINISTRATOR', 'OPE', 'ACT', 'ES', 'admin@microxt.com', 1, 0);

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
('ADMIN');

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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`USER_ID`,`PROFILE_ID`),
  KEY `USER_PROFILE_PROFILE_ID_FK` (`PROFILE_ID`),
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
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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

INSERT INTO `USER_STATUS_ID` (`USER_STATUS_ID`) VALUES
('ACT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_TYPE`
--

CREATE TABLE IF NOT EXISTS `USER_TYPE` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `USER_TYPE_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
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

INSERT INTO `USER_TYPE_ID` (`USER_TYPE_ID`) VALUES
('OPE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_DECISION`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_DECISION` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `DECISION_ID` varchar(4) NOT NULL,
  `DECISION_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `DECISION_RULE` varchar(4000) NOT NULL,
  `WORKFLOW_STEP_ID` varchar(4) NOT NULL,
  `CHANNEL_ID` varchar(4) DEFAULT NULL,
  `SUBSYSTEM_ID` varchar(2) DEFAULT NULL,
  `MODULE_ID` varchar(2) DEFAULT NULL,
  `PROCESS_ID` varchar(2) DEFAULT NULL,
  `PROCESS_SEQUENCE` tinyint(4) DEFAULT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`DECISION_ID`,`DECISION_SEQUENCE`),
  KEY `WORKFLOW_DECISION_ID_FK` (`DECISION_ID`),
  KEY `WORKFLOW_DECIS_CHANNEL_ID_FK` (`CHANNEL_ID`),
  KEY `WORKFLOW_DECIS_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `WORKFLOW_DECIS_STEP_ID_FK` (`WORKFLOW_STEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_DECISION`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_DECISION_ID`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_DECISION_ID` (
  `DECISION_ID` varchar(4) NOT NULL,
  `DECISION_SEQUENCE` tinyint(4) NOT NULL,
  PRIMARY KEY (`DECISION_ID`,`DECISION_SEQUENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_DECISION_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_MONITOR`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_MONITOR` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `EXPIRED` datetime NOT NULL,
  `JOB_NUMBER` int(11) NOT NULL,
  `CREATED` datetime NOT NULL,
  `JOB_DATE` datetime NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `WORKFLOW_ID` int(11) NOT NULL,
  `WORKFLOW_SEQUENCE` tinyint(4) NOT NULL,
  `WORKFLOW_STATUS_ID` varchar(4) NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `AUTHORIZER_ID` varchar(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`EXPIRED`,`JOB_NUMBER`),
  KEY `WORKFLOW_MONITOR_AUTH_ID_FK` (`AUTHORIZER_ID`),
  KEY `WORKFLOW_MONITOR_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`),
  KEY `WORKFLOW_MONITOR_STATUS_ID_FK` (`WORKFLOW_STATUS_ID`),
  KEY `WORKFLOW_MONITOR_USER_ID_FK` (`USER_ID`),
  KEY `WORKFLOW_MONITOR_WF_PROC_ID_FK` (`WORKFLOW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_MONITOR`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_PROCESS`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_PROCESS` (
  `COMPANY_ID` varchar(4) NOT NULL,
  `WORKFLOW_ID` int(11) NOT NULL,
  `WORKFLOW_SEQUENCE` tinyint(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `SUBSYSTEM_ID` varchar(2) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PROCESS_ID` varchar(2) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COMPANY_ID`,`WORKFLOW_ID`,`WORKFLOW_SEQUENCE`),
  KEY `WORKFLOW_PROCESS_ID_FK` (`WORKFLOW_ID`),
  KEY `WORKFLOW_PROCESS_PROCESS_ID_FK` (`SUBSYSTEM_ID`,`MODULE_ID`,`PROCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_PROCESS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_PROCESS_ID`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_PROCESS_ID` (
  `WORKFLOW_ID` int(11) NOT NULL,
  `WORKFLOW_SEQUENCE` tinyint(4) NOT NULL,
  PRIMARY KEY (`WORKFLOW_ID`,`WORKFLOW_SEQUENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_PROCESS_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_STATUS`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_STATUS` (
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `WORKFLOW_STATUS_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LANGUAGE_ID`,`WORKFLOW_STATUS_ID`),
  KEY `WORKFLOW_STATUS_ID_FK` (`WORKFLOW_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_STATUS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_STATUS_ID`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_STATUS_ID` (
  `WORKFLOW_STATUS_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`WORKFLOW_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_STATUS_ID`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_STEP`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_STEP` (
  `LANGUAGE_ID` varchar(2) NOT NULL,
  `WORKFLOW_STEP_ID` varchar(4) NOT NULL,
  `CREATED` datetime NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `VERSION` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LANGUAGE_ID`,`WORKFLOW_STEP_ID`),
  KEY `WORKFLOW_STEP_ID_FK` (`WORKFLOW_STEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_STEP`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `WORKFLOW_STEP_ID`
--

CREATE TABLE IF NOT EXISTS `WORKFLOW_STEP_ID` (
  `WORKFLOW_STEP_ID` varchar(4) NOT NULL,
  PRIMARY KEY (`WORKFLOW_STEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `WORKFLOW_STEP_ID`
--


--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `ADDRESS_TYPE`
--
ALTER TABLE `ADDRESS_TYPE`
  ADD CONSTRAINT `ADDRESS_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `ADDRESS_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ADDRESS_TYPE_ID_FK` FOREIGN KEY (`ADDRESS_TYPE_ID`) REFERENCES `ADDRESS_TYPE_ID` (`ADDRESS_TYPE_ID`);

--
-- Filtros para la tabla `BRANCH`
--
ALTER TABLE `BRANCH`
  ADD CONSTRAINT `BRANCH_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `BRANCH_BRANCH_ID_FK` FOREIGN KEY (`BRANCH_ID`) REFERENCES `BRANCH_ID` (`BRANCH_ID`);

--
-- Filtros para la tabla `CITY`
--
ALTER TABLE `CITY`
  ADD CONSTRAINT `CITY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `CITY_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`),
  ADD CONSTRAINT `CITY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `CITY_ID`
--
ALTER TABLE `CITY_ID`
  ADD CONSTRAINT `CITY_ID_PROVINCE_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`) REFERENCES `PROVINCE_ID` (`COUNTRY_ID`, `PROVINCE_ID`);

--
-- Filtros para la tabla `CIVIL_STATUS`
--
ALTER TABLE `CIVIL_STATUS`
  ADD CONSTRAINT `CIVIL_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `CIVIL_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `CIVIL_STATUS_ID_FK` FOREIGN KEY (`CIVIL_STATUS_ID`) REFERENCES `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`);

--
-- Filtros para la tabla `COMPONENT`
--
ALTER TABLE `COMPONENT`
  ADD CONSTRAINT `COMPONENT_SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`),
  ADD CONSTRAINT `COMPONENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`);

--
-- Filtros para la tabla `CONTROLLER`
--
ALTER TABLE `CONTROLLER`
  ADD CONSTRAINT `CONTROLLER_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`);

--
-- Filtros para la tabla `COUNTRY`
--
ALTER TABLE `COUNTRY`
  ADD CONSTRAINT `COUNTRY_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `COUNTRY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `COUNTRY_COUNTRY_ID_FK` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY_ID` (`COUNTRY_ID`);

--
-- Filtros para la tabla `DATABASE_TYPE`
--
ALTER TABLE `DATABASE_TYPE`
  ADD CONSTRAINT `DATABASE_TYPE_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`);

--
-- Filtros para la tabla `DATAFILE`
--
ALTER TABLE `DATAFILE`
  ADD CONSTRAINT `DATAFILE_DATAFILE_TYPE_FK` FOREIGN KEY (`DATAFILE_TYPE_ID`) REFERENCES `DATAFILE_TYPE` (`DATAFILE_TYPE_ID`),
  ADD CONSTRAINT `DATAFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DATAFILE_DATAFILE_ID_FK` FOREIGN KEY (`DATAFILE_ID`) REFERENCES `DATAFILE_ID` (`DATAFILE_ID`);

--
-- Filtros para la tabla `DEPARTMENT`
--
ALTER TABLE `DEPARTMENT`
  ADD CONSTRAINT `DEPARTMENT_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `DEPARTMENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DEPARTMENT_DEPARTMENT_ID_FK` FOREIGN KEY (`BRANCH_ID`, `OFFICE_ID`, `DEPARTMENT_ID`) REFERENCES `DEPARTMENT_ID` (`BRANCH_ID`, `OFFICE_ID`, `DEPARTMENT_ID`);

--
-- Filtros para la tabla `DEPARTMENT_ID`
--
ALTER TABLE `DEPARTMENT_ID`
  ADD CONSTRAINT `DEPARTMENT_ID_OFFICE_ID_FK` FOREIGN KEY (`BRANCH_ID`, `OFFICE_ID`) REFERENCES `OFFICE_ID` (`BRANCH_ID`, `OFFICE_ID`);

--
-- Filtros para la tabla `DISTRICT`
--
ALTER TABLE `DISTRICT`
  ADD CONSTRAINT `DISTRICT_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `DISTRICT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DISTRICT_DISTRICT_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`) REFERENCES `DISTRICT_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`, `DISTRICT_ID`);

--
-- Filtros para la tabla `DISTRICT_ID`
--
ALTER TABLE `DISTRICT_ID`
  ADD CONSTRAINT `DISTRICT_ID_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`);

--
-- Filtros para la tabla `DOCUMENT_TYPE`
--
ALTER TABLE `DOCUMENT_TYPE`
  ADD CONSTRAINT `DOCUMENT_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `DOCUMENT_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `DOCUMENT_TYPE_ID_FK` FOREIGN KEY (`DOCUMENT_TYPE_ID`) REFERENCES `DOCUMENT_TYPE_ID` (`DOCUMENT_TYPE_ID`);

--
-- Filtros para la tabla `ENTITY_FIELD`
--
ALTER TABLE `ENTITY_FIELD`
  ADD CONSTRAINT `ENTITY_FIELD_SEQUENTIAL_ID_FK` FOREIGN KEY (`SEQUENTIAL_ID`) REFERENCES `SEQUENTIAL_ID` (`SEQUENTIAL_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`),
  ADD CONSTRAINT `ENTITY_FIELD_ID_FK` FOREIGN KEY (`TABLE_ID`, `FIELD_ID`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`);

--
-- Filtros para la tabla `ENTITY_FIELD_ID`
--
ALTER TABLE `ENTITY_FIELD_ID`
  ADD CONSTRAINT `ENTITY_FIELD_ID_TABLE_ID_FK` FOREIGN KEY (`TABLE_ID`) REFERENCES `ENTITY_TABLE_ID` (`TABLE_ID`);

--
-- Filtros para la tabla `ENTITY_RELATIONSHIP`
--
ALTER TABLE `ENTITY_RELATIONSHIP`
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_TO_FK` FOREIGN KEY (`TABLE_TO`, `FIELD_TO`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`),
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `ENTITY_RELATIONSHIP_FROM_FK` FOREIGN KEY (`TABLE_FROM`, `FIELD_FROM`) REFERENCES `ENTITY_FIELD_ID` (`TABLE_ID`, `FIELD_ID`);

--
-- Filtros para la tabla `ENTITY_TABLE`
--
ALTER TABLE `ENTITY_TABLE`
  ADD CONSTRAINT `ENTITY_TABLE_ID_FK` FOREIGN KEY (`TABLE_ID`) REFERENCES `ENTITY_TABLE_ID` (`TABLE_ID`),
  ADD CONSTRAINT `ENTITY_TABLE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `GENDER_TYPE`
--
ALTER TABLE `GENDER_TYPE`
  ADD CONSTRAINT `GENDER_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `GENDER_TYPE_ID_FK` FOREIGN KEY (`GENDER_TYPE_ID`) REFERENCES `GENDER_TYPE_ID` (`GENDER_TYPE_ID`);

--
-- Filtros para la tabla `HOST`
--
ALTER TABLE `HOST`
  ADD CONSTRAINT `HOST_OFFICE_ID_FK` FOREIGN KEY (`BRANCH_ID`, `OFFICE_ID`) REFERENCES `OFFICE_ID` (`BRANCH_ID`, `OFFICE_ID`),
  ADD CONSTRAINT `HOST_CHANNEL_ID_FK` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `CHANNEL` (`CHANNEL_ID`),
  ADD CONSTRAINT `HOST_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `HOST_ID_FK` FOREIGN KEY (`HOST_ID`) REFERENCES `HOST_ID` (`HOST_ID`);

--
-- Filtros para la tabla `IDENTIFICATION_TYPE`
--
ALTER TABLE `IDENTIFICATION_TYPE`
  ADD CONSTRAINT `IDENTIFICATION_TYPE_LANGUAG_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `IDENTIFICATION_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `IDENTIFICATION_TYPE_ID_FK` FOREIGN KEY (`IDENTIFICATION_TYPE_ID`) REFERENCES `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`);

--
-- Filtros para la tabla `MESSAGE_COPY`
--
ALTER TABLE `MESSAGE_COPY`
  ADD CONSTRAINT `MESSAGE_COPY_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_COPY`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_COPY_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MESSAGE_COPY_MESS_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`);

--
-- Filtros para la tabla `MESSAGE_FIELD`
--
ALTER TABLE `MESSAGE_FIELD`
  ADD CONSTRAINT `MESSAGE_FIELD_MESS_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_FIELD_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `MESSAGE_LOG`
--
ALTER TABLE `MESSAGE_LOG`
  ADD CONSTRAINT `MESSAGE_LOG_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `MESSAGE_LOG_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MESSAGE_LOG_HOST_ID_FK` FOREIGN KEY (`HOST_ID`) REFERENCES `HOST_ID` (`HOST_ID`),
  ADD CONSTRAINT `MESSAGE_LOG_MESSAGE_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_LOG_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `MESSAGE_LOG_SERVICE_ID_FK` FOREIGN KEY (`SERVICE_ID`) REFERENCES `SERVICE_ID` (`SERVICE_ID`);

--
-- Filtros para la tabla `MESSAGE_MAP`
--
ALTER TABLE `MESSAGE_MAP`
  ADD CONSTRAINT `MESSAGE_MAP_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_MAP`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_MAP_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MESSAGE_MAP_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`),
  ADD CONSTRAINT `MESSAGE_MAP_MESSAGE_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`);

--
-- Filtros para la tabla `MESSAGE_ROUTER`
--
ALTER TABLE `MESSAGE_ROUTER`
  ADD CONSTRAINT `MESSAGE_ROUTER_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ROUTER`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_ROUTER_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MESSAGE_ROUTER_MESS_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_ROUTER_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `MESSAGE_ROUTER_SERVICE_ID_FK` FOREIGN KEY (`SERVICE_ROUTER`) REFERENCES `SERVICE_ID` (`SERVICE_ID`),
  ADD CONSTRAINT `MESSAGE_ROUTER_SERV_ID_FK` FOREIGN KEY (`SERVICE_ID`) REFERENCES `SERVICE_ID` (`SERVICE_ID`);

--
-- Filtros para la tabla `MESSAGE_TYPE`
--
ALTER TABLE `MESSAGE_TYPE`
  ADD CONSTRAINT `MESSAGE_TYPE_ID_FK` FOREIGN KEY (`MESSAGE_TYPE_ID`) REFERENCES `MESSAGE_TYPE_ID` (`MESSAGE_TYPE_ID`),
  ADD CONSTRAINT `MESSAGE_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MESSAGE_TYPE_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`);

--
-- Filtros para la tabla `MODULE`
--
ALTER TABLE `MODULE`
  ADD CONSTRAINT `MODULE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `MODULE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `MODULE_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`) REFERENCES `MODULE_ID` (`SUBSYSTEM_ID`, `MODULE_ID`);

--
-- Filtros para la tabla `MODULE_ID`
--
ALTER TABLE `MODULE_ID`
  ADD CONSTRAINT `MODULE_ID_SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`);

--
-- Filtros para la tabla `OFFICE`
--
ALTER TABLE `OFFICE`
  ADD CONSTRAINT `OFFICE_OFFICE_ID_FK` FOREIGN KEY (`BRANCH_ID`, `OFFICE_ID`) REFERENCES `OFFICE_ID` (`BRANCH_ID`, `OFFICE_ID`),
  ADD CONSTRAINT `OFFICE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `OFFICE_ID`
--
ALTER TABLE `OFFICE_ID`
  ADD CONSTRAINT `OFFICE_ID_BRANCH_ID_FK` FOREIGN KEY (`BRANCH_ID`) REFERENCES `BRANCH_ID` (`BRANCH_ID`);

--
-- Filtros para la tabla `PARAMETER`
--
ALTER TABLE `PARAMETER`
  ADD CONSTRAINT `PARAMETER_PARAMETER_ID_FK` FOREIGN KEY (`PARAMETER_ID`) REFERENCES `PARAMETER_ID` (`PARAMETER_ID`),
  ADD CONSTRAINT `PARAMETER_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PARAMETER_DATA_TYPE_FK` FOREIGN KEY (`DATA_TYPE_ID`) REFERENCES `DATA_TYPE` (`DATA_TYPE_ID`);

--
-- Filtros para la tabla `PERSON`
--
ALTER TABLE `PERSON`
  ADD CONSTRAINT `PERSON_PROFESSION_TYPE_ID_FK` FOREIGN KEY (`PROFESSION_TYPE_ID`) REFERENCES `PROFESSION_TYPE_ID` (`PROFESSION_TYPE_ID`),
  ADD CONSTRAINT `PERSON_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`),
  ADD CONSTRAINT `PERSON_CIVIL_STATUS_ID_FK` FOREIGN KEY (`CIVIL_STATUS_ID`) REFERENCES `CIVIL_STATUS_ID` (`CIVIL_STATUS_ID`),
  ADD CONSTRAINT `PERSON_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_GENDER_TYPE_ID_FK` FOREIGN KEY (`GENDER_TYPE_ID`) REFERENCES `GENDER_TYPE_ID` (`GENDER_TYPE_ID`),
  ADD CONSTRAINT `PERSON_IDENTIF_TYPE_ID_FK` FOREIGN KEY (`IDENTIFICATION_TYPE_ID`) REFERENCES `IDENTIFICATION_TYPE_ID` (`IDENTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`);

--
-- Filtros para la tabla `PERSON_ADDRESS`
--
ALTER TABLE `PERSON_ADDRESS`
  ADD CONSTRAINT `PERSON_ADDRESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_ADDRESS_CITY_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`) REFERENCES `CITY_ID` (`COUNTRY_ID`, `PROVINCE_ID`, `CITY_ID`);

--
-- Filtros para la tabla `PERSON_DOCUMENT`
--
ALTER TABLE `PERSON_DOCUMENT`
  ADD CONSTRAINT `PERSON_DOCUMENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `PERSON_PHONE`
--
ALTER TABLE `PERSON_PHONE`
  ADD CONSTRAINT `PERSON_PHONE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `PERSON_TYPE`
--
ALTER TABLE `PERSON_TYPE`
  ADD CONSTRAINT `PERSON_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PERSON_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PERSON_TYPE_ID_FK` FOREIGN KEY (`PERSON_TYPE_ID`) REFERENCES `PERSON_TYPE_ID` (`PERSON_TYPE_ID`);

--
-- Filtros para la tabla `PHONE_TYPE`
--
ALTER TABLE `PHONE_TYPE`
  ADD CONSTRAINT `PHONE_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PHONE_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PHONE_TYPE_ID_FK` FOREIGN KEY (`PHONE_TYPE_ID`) REFERENCES `PHONE_TYPE_ID` (`PHONE_TYPE_ID`);

--
-- Filtros para la tabla `PROCESS`
--
ALTER TABLE `PROCESS`
  ADD CONSTRAINT `PROCESS_WORKFLOW_PROCESS_ID_FK` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `WORKFLOW_PROCESS_ID` (`WORKFLOW_ID`),
  ADD CONSTRAINT `PROCESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROCESS_DATAFILE_ID_FK` FOREIGN KEY (`DATAFILE_ID`) REFERENCES `DATAFILE_ID` (`DATAFILE_ID`),
  ADD CONSTRAINT `PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `PROCESS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROCESS_COMPONENT`
--
ALTER TABLE `PROCESS_COMPONENT`
  ADD CONSTRAINT `PROCESS_COMP_WORKF_DECIS_ID_FK` FOREIGN KEY (`DECISION_ID`) REFERENCES `WORKFLOW_DECISION_ID` (`DECISION_ID`),
  ADD CONSTRAINT `PROCESS_COMPONENT_CHANNE_ID_FK` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `CHANNEL` (`CHANNEL_ID`),
  ADD CONSTRAINT `PROCESS_COMPONENT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROCESS_COMP_COMPONENT_ID_FK` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `COMPONENT_ID` (`COMPONENT_ID`),
  ADD CONSTRAINT `PROCESS_COMP_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`);

--
-- Filtros para la tabla `PROCESS_ID`
--
ALTER TABLE `PROCESS_ID`
  ADD CONSTRAINT `PROCESS_ID_MODULE_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`) REFERENCES `MODULE_ID` (`SUBSYSTEM_ID`, `MODULE_ID`);

--
-- Filtros para la tabla `PROFESSION_TYPE`
--
ALTER TABLE `PROFESSION_TYPE`
  ADD CONSTRAINT `PROFESSION_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PROFESSION_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROFESSION_TYPE_ID_FK` FOREIGN KEY (`PROFESSION_TYPE_ID`) REFERENCES `PROFESSION_TYPE_ID` (`PROFESSION_TYPE_ID`);

--
-- Filtros para la tabla `PROFILE`
--
ALTER TABLE `PROFILE`
  ADD CONSTRAINT `PROFILE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `PROFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROFILE_ID_FK` FOREIGN KEY (`PROFILE_ID`) REFERENCES `PROFILE_ID` (`PROFILE_ID`);

--
-- Filtros para la tabla `PROVINCE`
--
ALTER TABLE `PROVINCE`
  ADD CONSTRAINT `PROVINCE_PROVINCE_ID_FK` FOREIGN KEY (`COUNTRY_ID`, `PROVINCE_ID`) REFERENCES `PROVINCE_ID` (`COUNTRY_ID`, `PROVINCE_ID`),
  ADD CONSTRAINT `PROVINCE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `PROVINCE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `PROVINCE_ID`
--
ALTER TABLE `PROVINCE_ID`
  ADD CONSTRAINT `PROVINCE_ID_COUNTRY_ID_FK` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY_ID` (`COUNTRY_ID`);

--
-- Filtros para la tabla `RESPONSE`
--
ALTER TABLE `RESPONSE`
  ADD CONSTRAINT `RESPONSE_RESPONSE_ID_FK` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `RESPONSE_ID` (`RESPONSE_ID`),
  ADD CONSTRAINT `RESPONSE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `RESPONSE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`);

--
-- Filtros para la tabla `ROLE`
--
ALTER TABLE `ROLE`
  ADD CONSTRAINT `ROLE_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `ROLE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `SEQUENTIAL`
--
ALTER TABLE `SEQUENTIAL`
  ADD CONSTRAINT `SEQUENTIAL_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `SERVICE`
--
ALTER TABLE `SERVICE`
  ADD CONSTRAINT `SERVICE_SERVICE_ID_FK` FOREIGN KEY (`SERVER_ID`) REFERENCES `SERVER` (`SERVER_ID`),
  ADD CONSTRAINT `SERVICE_CHANNEL_ID_FK` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `CHANNEL` (`CHANNEL_ID`),
  ADD CONSTRAINT `SERVICE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SERVICE_CONTROLLER_ID_FK` FOREIGN KEY (`CONTROLLER_ID`) REFERENCES `CONTROLLER` (`CONTROLLER_ID`),
  ADD CONSTRAINT `SERVICE_ID_FK` FOREIGN KEY (`SERVICE_ID`) REFERENCES `SERVICE_ID` (`SERVICE_ID`);

--
-- Filtros para la tabla `SERVICE_PARAMETER`
--
ALTER TABLE `SERVICE_PARAMETER`
  ADD CONSTRAINT `SERVICE_PARAM_SERVICE_ID_FK` FOREIGN KEY (`SERVICE_ID`) REFERENCES `SERVICE_ID` (`SERVICE_ID`),
  ADD CONSTRAINT `SERVICE_PARAMETER_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SERVICE_PARAM_PARAMETER_ID_FK` FOREIGN KEY (`PARAMETER_ID`) REFERENCES `PARAMETER_ID` (`PARAMETER_ID`);

--
-- Filtros para la tabla `SUBSYSTEM`
--
ALTER TABLE `SUBSYSTEM`
  ADD CONSTRAINT `SUBSYSTEM_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `SUBSYSTEM_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `SUBSYSTEM_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`) REFERENCES `SUBSYSTEM_ID` (`SUBSYSTEM_ID`);

--
-- Filtros para la tabla `USER_ACCESS`
--
ALTER TABLE `USER_ACCESS`
  ADD CONSTRAINT `USER_ACCESS_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_ACCESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`);

--
-- Filtros para la tabla `USER_ACCOUNT`
--
ALTER TABLE `USER_ACCOUNT`
  ADD CONSTRAINT `USER_ACCOUNT_USER_TYPE_ID_FK` FOREIGN KEY (`USER_TYPE_ID`) REFERENCES `USER_TYPE_ID` (`USER_TYPE_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_PERSON_ID_FK` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON_ID` (`PERSON_ID`),
  ADD CONSTRAINT `USER_ACCOUNT_USER_STATUS_ID_FK` FOREIGN KEY (`USER_STATUS_ID`) REFERENCES `USER_STATUS_ID` (`USER_STATUS_ID`);

--
-- Filtros para la tabla `USER_AUTHORIZATION`
--
ALTER TABLE `USER_AUTHORIZATION`
  ADD CONSTRAINT `USER_AUTH_USER_AUTH_ID_FK` FOREIGN KEY (`AUTHORIZER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_AUTHORIZATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_AUTH_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `USER_AUTH_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_NOTIFICATION`
--
ALTER TABLE `USER_NOTIFICATION`
  ADD CONSTRAINT `USER_NOTIF_USER_NOT_TYPE_ID_FK` FOREIGN KEY (`USER_NOTIFICATION_TYPE_ID`) REFERENCES `USER_NOTIFICATION_TYPE_ID` (`USER_NOTIFICATION_TYPE_ID`),
  ADD CONSTRAINT `USER_NOTIFICATION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_NOTIF_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `USER_NOTIFICATION_TYPE`
--
ALTER TABLE `USER_NOTIFICATION_TYPE`
  ADD CONSTRAINT `USER_NOTIFICATION_TYPE_LANG_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `USER_NOTIFICATION_TYPE_ID_FK` FOREIGN KEY (`USER_NOTIFICATION_TYPE_ID`) REFERENCES `USER_NOTIFICATION_TYPE_ID` (`USER_NOTIFICATION_TYPE_ID`);

--
-- Filtros para la tabla `USER_PROFILE`
--
ALTER TABLE `USER_PROFILE`
  ADD CONSTRAINT `USER_PROF_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_PROFILE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_PROFILE_PROFILE_ID_FK` FOREIGN KEY (`PROFILE_ID`) REFERENCES `PROFILE_ID` (`PROFILE_ID`);

--
-- Filtros para la tabla `USER_SESSION`
--
ALTER TABLE `USER_SESSION`
  ADD CONSTRAINT `USER_SESS_USER_ACCOUNT_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `USER_SESSION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_SESSION_HOST_ID_FK` FOREIGN KEY (`HOST_ID`) REFERENCES `HOST_ID` (`HOST_ID`);

--
-- Filtros para la tabla `USER_STATUS`
--
ALTER TABLE `USER_STATUS`
  ADD CONSTRAINT `USER_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `USER_STATUS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_STATUS_ID_FK` FOREIGN KEY (`USER_STATUS_ID`) REFERENCES `USER_STATUS_ID` (`USER_STATUS_ID`);

--
-- Filtros para la tabla `USER_TYPE`
--
ALTER TABLE `USER_TYPE`
  ADD CONSTRAINT `USER_TYPE_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `USER_TYPE_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `USER_TYPE_ID_FK` FOREIGN KEY (`USER_TYPE_ID`) REFERENCES `USER_TYPE_ID` (`USER_TYPE_ID`);

--
-- Filtros para la tabla `WORKFLOW_DECISION`
--
ALTER TABLE `WORKFLOW_DECISION`
  ADD CONSTRAINT `WORKFLOW_DECIS_STEP_ID_FK` FOREIGN KEY (`WORKFLOW_STEP_ID`) REFERENCES `WORKFLOW_STEP_ID` (`WORKFLOW_STEP_ID`),
  ADD CONSTRAINT `WORKFLOW_DECISION_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `WORKFLOW_DECISION_ID_FK` FOREIGN KEY (`DECISION_ID`) REFERENCES `WORKFLOW_DECISION_ID` (`DECISION_ID`),
  ADD CONSTRAINT `WORKFLOW_DECIS_CHANNEL_ID_FK` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `CHANNEL` (`CHANNEL_ID`),
  ADD CONSTRAINT `WORKFLOW_DECIS_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`);

--
-- Filtros para la tabla `WORKFLOW_MONITOR`
--
ALTER TABLE `WORKFLOW_MONITOR`
  ADD CONSTRAINT `WORKFLOW_MONITOR_WF_PROC_ID_FK` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `WORKFLOW_PROCESS_ID` (`WORKFLOW_ID`),
  ADD CONSTRAINT `WORKFLOW_MONITOR_AUTH_ID_FK` FOREIGN KEY (`AUTHORIZER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`),
  ADD CONSTRAINT `WORKFLOW_MONITOR_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `WORKFLOW_MONITOR_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `WORKFLOW_MONITOR_STATUS_ID_FK` FOREIGN KEY (`WORKFLOW_STATUS_ID`) REFERENCES `WORKFLOW_STATUS_ID` (`WORKFLOW_STATUS_ID`),
  ADD CONSTRAINT `WORKFLOW_MONITOR_USER_ID_FK` FOREIGN KEY (`USER_ID`) REFERENCES `USER_ACCOUNT_ID` (`USER_ID`);

--
-- Filtros para la tabla `WORKFLOW_PROCESS`
--
ALTER TABLE `WORKFLOW_PROCESS`
  ADD CONSTRAINT `WORKFLOW_PROCESS_PROCESS_ID_FK` FOREIGN KEY (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`) REFERENCES `PROCESS_ID` (`SUBSYSTEM_ID`, `MODULE_ID`, `PROCESS_ID`),
  ADD CONSTRAINT `WORKFLOW_PROCESS_COMPANY_FK` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`COMPANY_ID`),
  ADD CONSTRAINT `WORKFLOW_PROCESS_ID_FK` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `WORKFLOW_PROCESS_ID` (`WORKFLOW_ID`);

--
-- Filtros para la tabla `WORKFLOW_STATUS`
--
ALTER TABLE `WORKFLOW_STATUS`
  ADD CONSTRAINT `WORKFLOW_STATUS_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `WORKFLOW_STATUS_ID_FK` FOREIGN KEY (`WORKFLOW_STATUS_ID`) REFERENCES `WORKFLOW_STATUS_ID` (`WORKFLOW_STATUS_ID`);

--
-- Filtros para la tabla `WORKFLOW_STEP`
--
ALTER TABLE `WORKFLOW_STEP`
  ADD CONSTRAINT `WORKFLOW_STEP_LANGUAGE_FK` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `LANGUAGE` (`LANGUAGE_ID`),
  ADD CONSTRAINT `WORKFLOW_STEP_ID_FK` FOREIGN KEY (`WORKFLOW_STEP_ID`) REFERENCES `WORKFLOW_STEP_ID` (`WORKFLOW_STEP_ID`);

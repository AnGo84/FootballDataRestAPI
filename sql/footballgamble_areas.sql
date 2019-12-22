CREATE DATABASE  IF NOT EXISTS `footballgamble` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `footballgamble`;
-- MySQL dump 10.13  Distrib 5.5.62, for debian-linux-gnu (x86_64)
--
-- Host: footballgamble.cu4zm1grealp.eu-west-3.rds.amazonaws.com    Database: footballgamble
-- ------------------------------------------------------
-- Server version	5.6.40-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `country_code` varchar(45) NOT NULL,
  `ensign_url` varchar(255) DEFAULT NULL,
  `parent_area_Id` int(11) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (2002,'Albania','ALB',NULL,2077,NULL),(2006,'Andorra','AND',NULL,2077,NULL),(2012,'Armenia','ARM',NULL,2077,NULL),(2016,'Austria','AUT','https://upload.wikimedia.org/wikipedia/commons/4/41/Flag_of_Austria.svg',2077,NULL),(2017,'Azerbaijan','AZE',NULL,2077,NULL),(2022,'Belarus','BLR',NULL,2077,NULL),(2023,'Belgium','BEL','https://upload.wikimedia.org/wikipedia/commons/6/65/Flag_of_Belgium.svg',2077,NULL),(2030,'Bosnia and Herzegovina','BIH','https://upload.wikimedia.org/wikipedia/commons/b/bf/Flag_of_Bosnia_and_Herzegovina.svg',2077,NULL),(2035,'Bulgaria','BGR','https://upload.wikimedia.org/wikipedia/commons/9/9a/Flag_of_Bulgaria.svg',2077,NULL),(2058,'Croatia','HRV','https://upload.wikimedia.org/wikipedia/commons/1/1b/Flag_of_Croatia.svg',2077,NULL),(2061,'Cyprus','CYP',NULL,2077,NULL),(2062,'Czech Republic','CZE','https://upload.wikimedia.org/wikipedia/commons/c/cb/Flag_of_the_Czech_Republic.svg',2077,NULL),(2065,'Denmark','DNK','https://upload.wikimedia.org/wikipedia/commons/9/9c/Flag_of_Denmark.svg',2077,NULL),(2072,'England','ENG','https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg',2077,NULL),(2075,'Estonia','EST','https://upload.wikimedia.org/wikipedia/commons/8/8f/Flag_of_Estonia.svg',2077,NULL),(2077,'Europe','EUR',NULL,2267,NULL),(2078,'Faroe Islands','FRO',NULL,2077,NULL),(2080,'Finland','FIN','https://upload.wikimedia.org/wikipedia/commons/b/bc/Flag_of_Finland.svg',2077,NULL),(2081,'France','FRA','https://upload.wikimedia.org/wikipedia/en/c/c3/Flag_of_France.svg',2077,NULL),(2083,'FYR Macedonia','MKD',NULL,2077,NULL),(2087,'Georgia','GEO',NULL,2077,NULL),(2088,'Germany','DEU','https://upload.wikimedia.org/wikipedia/commons/b/ba/Flag_of_Germany.svg',2077,NULL),(2090,'Gibraltar','GIB',NULL,2077,NULL),(2093,'Greece','GRC','https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_Greece.svg',2077,NULL),(2106,'Hungary','HUN','https://upload.wikimedia.org/wikipedia/commons/c/c1/Flag_of_Hungary.svg',2077,NULL),(2107,'Iceland','ISL','https://upload.wikimedia.org/wikipedia/commons/c/ce/Flag_of_Iceland.svg',2077,NULL),(2113,'Israel','ISR','https://upload.wikimedia.org/wikipedia/commons/d/d4/Flag_of_Israel.svg',2077,NULL),(2114,'Italy','ITA','https://upload.wikimedia.org/wikipedia/en/0/03/Flag_of_Italy.svg',2077,NULL),(2119,'Kazakhstan','KAZ',NULL,2077,NULL),(2124,'Kosovo','KSV',NULL,2077,NULL),(2129,'Latvia','LVA','https://upload.wikimedia.org/wikipedia/commons/8/84/Flag_of_Latvia.svg',2077,NULL),(2135,'Lithuania','LTU','https://upload.wikimedia.org/wikipedia/commons/1/11/Flag_of_Lithuania.svg',2077,NULL),(2136,'Luxembourg','LUX',NULL,2077,NULL),(2143,'Malta','MLT','https://upload.wikimedia.org/wikipedia/commons/7/73/Flag_of_Malta.svg',2077,NULL),(2151,'Moldova','MHL',NULL,2077,NULL),(2152,'Monaco','MCO',NULL,2077,NULL),(2154,'Montenegro','MNE',NULL,2077,NULL),(2163,'Netherlands','NLD','https://upload.wikimedia.org/wikipedia/commons/2/20/Flag_of_the_Netherlands.svg',2077,NULL),(2171,'Northern Ireland','NIR','https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg',2077,NULL),(2173,'Norway','NOR','https://upload.wikimedia.org/wikipedia/commons/d/d9/Flag_of_Norway.svg',2077,NULL),(2186,'Poland','POL','https://upload.wikimedia.org/wikipedia/en/1/12/Flag_of_Poland.svg',2077,NULL),(2187,'Portugal','PRT','https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_Portugal.svg',2077,NULL),(2192,'Republic of Ireland','IRL','https://upload.wikimedia.org/wikipedia/commons/4/45/Flag_of_Ireland.svg',2077,NULL),(2194,'Romania','ROU','https://upload.wikimedia.org/wikipedia/commons/7/73/Flag_of_Romania.svg',2077,NULL),(2195,'Russia','RUS','https://upload.wikimedia.org/wikipedia/en/f/f3/Flag_of_Russia.svg',2077,NULL),(2200,'San Marino','SMR',NULL,2077,NULL),(2204,'Scotland','SCO','https://upload.wikimedia.org/wikipedia/commons/1/10/Flag_of_Scotland.svg',2077,NULL),(2206,'Serbia','SER',NULL,2077,NULL),(2214,'Slovakia','SVK',NULL,2077,NULL),(2215,'Slovenia','SVN',NULL,2077,NULL),(2224,'Spain','ESP','https://upload.wikimedia.org/wikipedia/en/9/9a/Flag_of_Spain.svg',2077,NULL),(2233,'Sweden','SWE','https://upload.wikimedia.org/wikipedia/en/4/4c/Flag_of_Sweden.svg',2077,NULL),(2234,'Switzerland','CHE','https://upload.wikimedia.org/wikipedia/commons/0/08/Flag_of_Switzerland_%28Pantone%29.svg',2077,NULL),(2247,'Turkey','TUR','https://upload.wikimedia.org/wikipedia/commons/b/b4/Flag_of_Turkey.svg',2077,NULL),(2253,'Ukraine','UKR','https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg',2077,NULL),(2264,'Wales','WAL','https://upload.wikimedia.org/wikipedia/commons/a/a9/Flag_of_Wales_%281959%E2%80%93present%29.svg',2077,NULL);
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-18 11:11:46

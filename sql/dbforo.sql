-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.17-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for dbforo
CREATE DATABASE IF NOT EXISTS `dbforo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbforo`;

-- Dumping structure for table dbforo.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `anclado` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.categorias: ~0 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Dumping structure for table dbforo.hilos
CREATE TABLE IF NOT EXISTS `hilos` (
  `cod_hilo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) DEFAULT 0,
  `cod_subcategoria` int(11) DEFAULT 0,
  `titulo` varchar(255) NOT NULL,
  `fecha_publi` date NOT NULL,
  `n_visitas` int(11) NOT NULL DEFAULT 0,
  `n_respuestas` int(11) NOT NULL DEFAULT 0,
  `cod_usuario` int(11) NOT NULL DEFAULT 0,
  `anclado` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_hilo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.hilos: ~0 rows (approximately)
/*!40000 ALTER TABLE `hilos` DISABLE KEYS */;
/*!40000 ALTER TABLE `hilos` ENABLE KEYS */;

-- Dumping structure for table dbforo.newsletter
CREATE TABLE IF NOT EXISTS `newsletter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.newsletter: ~0 rows (approximately)
/*!40000 ALTER TABLE `newsletter` DISABLE KEYS */;
/*!40000 ALTER TABLE `newsletter` ENABLE KEYS */;

-- Dumping structure for table dbforo.respuestas
CREATE TABLE IF NOT EXISTS `respuestas` (
  `cod_respuesta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario` int(11) NOT NULL DEFAULT 0,
  `cod_hilo` int(11) NOT NULL DEFAULT 0,
  `fecha_public` date NOT NULL,
  `contenido` varchar(255) NOT NULL DEFAULT '',
  `votos` int(11) NOT NULL DEFAULT 0,
  `solucion` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.respuestas: ~0 rows (approximately)
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;

-- Dumping structure for table dbforo.subcategorias
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL DEFAULT 0,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `anclado` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_subcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.subcategorias: ~0 rows (approximately)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `subcategorias` ENABLE KEYS */;

-- Dumping structure for table dbforo.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `email` varchar(255) NOT NULL DEFAULT 'admin@admin.es',
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_nacimiento` date NOT NULL,
  `pais` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permiso` int(11) DEFAULT 0,
  PRIMARY KEY (`email`),
  UNIQUE KEY `cod_usuario` (`cod_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.usuarios: ~0 rows (approximately)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

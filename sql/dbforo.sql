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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.categorias: ~2 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`cod_categoria`, `titulo`, `descripcion`, `anclado`) VALUES
	(1, 'Cocina', 'prueba', 0),
	(2, 'Motor', 'prueba', 0),
	(3, 'Tecnologia', 'prueba', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.hilos: ~13 rows (approximately)
/*!40000 ALTER TABLE `hilos` DISABLE KEYS */;
INSERT INTO `hilos` (`cod_hilo`, `cod_categoria`, `cod_subcategoria`, `titulo`, `fecha_publi`, `n_visitas`, `n_respuestas`, `cod_usuario`, `anclado`) VALUES
	(0, 0, 0, 'No existen hilos', '0000-00-00', 0, 0, 1, 0),
	(1, 1, 1, 'gg1', '0000-00-00', 0, 0, 1, 1),
	(2, 1, 2, 'gg', '0000-00-00', 0, 0, 1, 0),
	(3, 1, 2, 'gg', '0000-00-00', 0, 0, 1, 0),
	(4, 1, 1, 'gg', '0000-00-00', 0, 0, 1, 0),
	(5, 1, 2, 'gg', '0000-00-00', 0, 0, 1, 0),
	(7, 1, 1, 'ad1', '2022-06-03', 0, 0, 2, NULL),
	(8, 1, 1, 'ad1', '2022-06-03', 0, 0, 2, NULL),
	(9, 1, 1, 'ad2', '2022-06-03', 0, 0, 2, NULL),
	(10, 1, 1, 'ad3', '2022-06-03', 0, 0, 2, NULL),
	(11, 1, 1, 'adad1q', '2022-06-03', 0, 0, 2, NULL),
	(12, 1, 1, 'los xungito', '2022-06-03', 0, 0, 2, NULL),
	(13, 1, 1, 'prueba', '2022-06-09', 0, 0, 2, NULL),
	(14, 1, 1, 'prueba titulo', '2022-06-09', 0, 0, 2, NULL),
	(15, 1, 1, 'prueba titulo2', '2022-06-09', 0, 0, 2, NULL);
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
  `contenido` longtext NOT NULL DEFAULT '',
  `votos` int(11) NOT NULL DEFAULT 0,
  `solucion` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_respuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.respuestas: ~7 rows (approximately)
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` (`cod_respuesta`, `cod_usuario`, `cod_hilo`, `fecha_public`, `contenido`, `votos`, `solucion`) VALUES
	(1, 2, 6, '2022-06-03', '<p>ad12</p>', 0, NULL),
	(2, 2, 8, '2022-06-03', '<p>ad12</p>', 0, NULL),
	(3, 2, 9, '2022-06-03', '<p>ad4</p>', 0, NULL),
	(4, 2, 10, '2022-06-03', '<p>ad3</p>', 0, NULL),
	(5, 2, 11, '2022-06-03', '<p>adad</p>', 0, NULL),
	(6, 2, 12, '2022-06-03', '<ul><li>aprueb<strong>adba d ada</strong></li></ul>', 0, NULL),
	(8, 2, 15, '2022-06-09', '<p><span style=\'color: rgb(0, 0, 0);\'>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque consectetur eros a diam vestibulum, lacinia pharetra neque lobortis. Curabitur nisi velit, ultrices vel felis quis, semper euismod dui. Praesent vitae faucibus libero, ac auctor odio. Integer ac ante quis nisl dictum ullamcorper in dapibus nulla. </span><em style=\'color: rgb(0, 0, 0);\'>Aenean arcu est, vestibulum</em><span style=\'color: rgb(0, 0, 0);\'> ac lacinia sed, porttitor quis tellus. Integer in massa ex. Nunc bibendum finibus dapibus. In eget turpis iaculis, molestie libero ac, vehicula magna. Nunc porta sem id neque semper, quis scelerisque purus tempus. Nam fermentum sapien tellus, eu varius felis dapibus porttitor. Quisque sed ante ut arcu fringilla dapibus. Fusce eu nibh tellus. Ut vel dolor convallis ipsum luctus vestibulum. Aliquam ut massa ut lorem feugiat laoreet sit amet vel nisl. Aenean sem nisl, auctor nec velit et, fringilla aliquam tortor.</span></p>', 0, NULL),
	(9, 2, 15, '2022-06-09', '<p><span style=\'color: rgb(0, 0, 0);\'>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque consectetur eros a diam vestibulum, lacinia pharetra neque lobortis. Curabitur nisi velit, ultrices vel felis quis, semper euismod dui. Praesent vitae faucibus libero, ac auctor odio. Integer ac ante quis nisl dictum ullamcorper in dapibus nulla. </span><em style=\'color: rgb(0, 0, 0);\'>Aenean arcu est, vestibulum</em><span style=\'color: rgb(0, 0, 0);\'> ac lacinia sed, porttitor quis tellus. Integer in massa ex. Nunc bibendum finibus dapibus. In eget turpis iaculis, molestie libero ac, vehicula magna. Nunc porta sem id neque semper, quis scelerisque purus tempus. Nam fermentum sapien tellus, eu varius felis dapibus porttitor. Quisque sed ante ut arcu fringilla dapibus. Fusce eu nibh tellus. Ut vel dolor convallis ipsum luctus vestibulum. Aliquam ut massa ut lorem feugiat laoreet sit amet vel nisl. Aenean sem nisl, auctor nec velit et, fringilla aliquam tortor.</span></p>', 0, 0),
	(10, 2, 15, '2022-06-09', '1 voto', 1, 0),
	(11, 2, 15, '2022-06-09', '2 voto', 0, 0),
	(12, 2, 15, '2022-06-09', '-1 voto', 3, 0),
	(13, 2, 15, '2022-06-09', '0 voto', 0, 0),
	(14, 1, 15, '2022-06-09', '<p class=\'ql-align-center\'><strong>Prueba de respuesta creada desde cliente</strong></p>', 1, 1);
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;

-- Dumping structure for table dbforo.subcategorias
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL DEFAULT 0,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `anclado` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_subcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.subcategorias: ~2 rows (approximately)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` (`cod_subcategoria`, `cod_categoria`, `titulo`, `descripcion`, `anclado`) VALUES
	(1, 1, 'subcate1', 'test', 0),
	(2, 1, 'subcate2', 'test', 1),
	(3, 2, 'subcate21', 'test', 1);
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
  `imagen` varchar(255) NOT NULL,
  `permiso` int(11) DEFAULT 0,
  PRIMARY KEY (`email`),
  UNIQUE KEY `cod_usuario` (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.usuarios: ~1 rows (approximately)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`email`, `cod_usuario`, `fecha_nacimiento`, `pais`, `nombre`, `apellidos`, `password`, `imagen`, `permiso`) VALUES
	('admin@atlanthis.com', 1, '1996-05-08', 'ESP', 'Administrador', '', 'YAgIFasDMz4=', 'default.png', 3),
	('alexyanamusicpro@gmail.com', 2, '1996-05-08', 'spain', 'Alfonso', 'Melasuda', 'lYMz0T9NhznTp5ioLIr+cg==', 'default.png', NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.categorias: ~3 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`cod_categoria`, `titulo`, `descripcion`, `anclado`) VALUES
	(1, 'Cocina', 'prueba', 0),
	(2, 'Motor', 'prueba', 0);
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
  `anclado` int(11) NOT NULL DEFAULT 0,
  `cerrado` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cod_hilo`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.hilos: ~5 rows (approximately)
/*!40000 ALTER TABLE `hilos` DISABLE KEYS */;
INSERT INTO `hilos` (`cod_hilo`, `cod_categoria`, `cod_subcategoria`, `titulo`, `fecha_publi`, `n_visitas`, `n_respuestas`, `cod_usuario`, `anclado`, `cerrado`) VALUES
	(0, 0, 0, 'No existen hilos', '0000-00-00', 0, 0, 1, 0, 1),
	(1, 1, 1, 'Mousses de chocolate', '2022-06-16', 0, 0, 2, 0, 0),
	(2, 1, 1, 'Flan de turrón', '2022-06-16', 0, 0, 2, 0, 1),
	(3, 1, 1, 'Cómo hacer fresas escabechadas', '2022-06-16', 0, 0, 2, 1, 0),
	(4, 1, 2, 'Prueba de tema de otro usuario', '2022-06-16', 0, 0, 1, 0, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.respuestas: ~10 rows (approximately)
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` (`cod_respuesta`, `cod_usuario`, `cod_hilo`, `fecha_public`, `contenido`, `votos`, `solucion`) VALUES
	(1, 2, 1, '2022-06-16', '<p><span style=\'color: rgb(51, 51, 51);\'>Fácil no, lo siguiente. Eso es lo que son las mousses. Postres que no tardan nada en estar listos y que tienen gran acogida entre los comensales. Esa textura etérea que le otorga la clara montada a punto de nieve las hace irresistibles y nos deja con una sensación de placer tremenda, pero&nbsp;</span><strong style=\'color: rgb(51, 51, 51);\'>sin la pesadez de otros postres</strong><span style=\'color: rgb(51, 51, 51);\'>. Aunque los reyes son los de chocolate, también se puede jugar con frutas para adaptarlos al gusto de todos.</span></p>', 0, NULL),
	(2, 2, 2, '2022-06-16', '<p><span style=\'color: rgb(51, 51, 51);\'>Si se nos acumula el turrón de Jijona o blando en casa y no somos muy de devorarlo tal cual, una buena forma de aprovecharlo es cocinando con él. Es un ingrediente fabuloso de&nbsp;</span><a href=\'https://www.directoalpaladar.com/postres/biscuit-turron-salsa-frutos-rojos-receta-muy-facil-para-postre-estas-navidades\' rel=\'noopener noreferrer\' target=\'_blank\' style=\'color: rgb(177, 0, 57);\'>dulces deliciosos</a><span style=\'color: rgb(51, 51, 51);\'>, y este&nbsp;</span><strong style=\'color: rgb(51, 51, 51);\'>flan de turrón sin horno y sin huevo</strong><span style=\'color: rgb(51, 51, 51);\'>&nbsp;además nos puede solucionar el postre en un menú de fiesta.</span></p>', 0, NULL),
	(3, 2, 3, '2022-06-16', '<p><span style=\'color: rgb(51, 51, 51);\'>Fácil no, lo siguiente. Eso es lo que son las mousses. Postres que no tardan nada en estar listos y que tienen gran acogida entre los comensales. Esa textura etérea que le otorga la clara montada a punto de nieve las hace irresistibles y nos deja con una sensación de placer tremenda, pero&nbsp;</span><strong style=\'color: rgb(51, 51, 51);\'>sin la pesadez de otros postres</strong><span style=\'color: rgb(51, 51, 51);\'>. Aunque los reyes son los de chocolate, también se puede jugar con frutas para adaptarlos al gusto de todos.</span></p>', 0, NULL),
	(4, 2, 3, '2022-06-16', '<p>Prueba de Respuesta con 1 voto</p>', 1, NULL),
	(5, 2, 3, '2022-06-16', '<p>Prueba de respuesta con 2 votos</p>', 2, NULL),
	(6, 2, 3, '2022-06-16', '<p>Prueba de respuesta marcada como solucion</p>', 0, 1),
	(7, 1, 4, '2022-06-16', '<p><span style=\'color: rgb(0, 0, 0);\'>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc nunc quam, varius eu ante sit amet, dignissim fermentum mi. Vestibulum a vestibulum tortor, in molestie odio. Integer vel augue interdum, fermentum eros sit amet, lobortis diam. Aliquam viverra enim quis ante vestibulum, nec pulvinar sem molestie. Ut convallis tellus eu lorem sollicitudin vehicula. Nunc ut tempor ante. Nam tempor, augue ut faucibus lobortis, elit augue tincidunt augue, eget dapibus lorem urna eu magna. Vivamus cursus, nisi in vulputate interdum, elit sem sollicitudin lorem, eget feugiat urna elit et felis. Nullam vel massa vel massa rhoncus consequat sit amet in sem.</span></p>', 0, NULL),
	(8, 1, 4, '2022-06-16', '<p>Prueba de respuesta por otro usuario</p>', 0, NULL),
	(9, 1, 4, '2022-06-16', '<p>Prueba de respuesta 2</p>', 0, NULL),
	(10, 1, 4, '2022-06-16', '<p>prueba de solucion</p>', 0, 1);
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;

-- Dumping structure for table dbforo.subcategorias
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL DEFAULT 0,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `anclado` int(11) DEFAULT 0,
  PRIMARY KEY (`cod_subcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.subcategorias: ~4 rows (approximately)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` (`cod_subcategoria`, `cod_categoria`, `titulo`, `descripcion`, `anclado`) VALUES
	(1, 1, 'Postres', 'Subcategoria de postres', 0),
	(2, 1, 'Entrantes', 'Subcategoria de platos entrantes', 1),
	(3, 2, 'Formula 1', 'Subcategoria dedicada a la F1', NULL),
	(4, 3, 'Procesadores', 'Subcategoria dedicada a procesadores', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table dbforo.usuarios: ~2 rows (approximately)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`email`, `cod_usuario`, `fecha_nacimiento`, `pais`, `nombre`, `apellidos`, `password`, `imagen`, `permiso`) VALUES
	('admin@atlanthis.com', 1, '2026-06-01', 'España', 'Atlanthis', '', 'YAgIFasDMz4=', 'default.png', 3),
	('alex-zam-olv@hotmail.com', 2, '2012-06-06', 'España', 'Alejandro', 'Mendoza Zambrana', 'lYMz0T9NhznTp5ioLIr+cg==', 'default.png', 0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-03-2022 a las 20:22:10
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carta`
--

CREATE TABLE `carta` (
  `idProducto` int(11) NOT NULL,
  `nombreProducto` varchar(60) NOT NULL,
  `precioProducto` int(11) NOT NULL,
  `detalleProducto` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carta`
--

INSERT INTO `carta` (`idProducto`, `nombreProducto`, `precioProducto`, `detalleProducto`) VALUES
(1, 'Chocolate', 1, 'Marca VALOR'),
(2, 'Churros', 2, 'Cada pedido son 10 unidades'),
(3, 'Brownie', 4, 'Nuestro producto estrella'),
(4, 'Muffin', 3, 'Es una bomba de chocolate'),
(5, 'Agua', 1, 'Volumen 33cl'),
(6, 'Palmera de chocolate', 2, 'Rellena de kinder'),
(7, 'Combo 23', 4, 'Chocolate caliente con 10 churros'),
(8, 'Combo VIP 23', 6, 'Dos botellas de agua y tres Brownies. Para 2 personas'),
(9, 'Combo Remix', 8, 'Dos chocolate caliente y 20 churros. Para 2 persona');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` int(11) NOT NULL,
  `titulo` varchar(60) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `categoria` varchar(60) NOT NULL,
  `ISBN` varchar(60) NOT NULL,
  `edicion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `titulo`, `autor`, `categoria`, `ISBN`, `edicion`) VALUES
(1, 'Conducta humana', 'Mario Alonso Puig', 'Psicología', '9789217031066', 'Tierra'),
(2, 'Nacimos para vivir', 'Berthod Johannes', 'Psicología', '9789029114064', 'Tierra'),
(3, 'Origen', 'Berthod Johannes', 'Psicología', '9789029114065', 'Tierra'),
(4, 'Python desde cero', 'Bill Setag', 'Programación', '9779018087062', 'SB'),
(5, 'Cocina para tu esposa', 'Jose Louis Villa Expósito', 'Gastronomía', '9789207102041', 'Testa'),
(6, 'Cocina para tus esposas', 'Jose Louis Villa Expósito', 'Gastronomía', '9789207102041', 'Testa'),
(7, 'Inteligencia Emocional', 'Daniel Goleman', 'Psicología', '9779018087062', 'Tierra'),
(8, 'One Piece', 'Eiichiro Oda', 'Anime', '9769031077126', 'Sota Verde'),
(9, 'Visión', 'Luis Robbe Toichoa Sautoho', 'Neurociencias', '9779018087062', 'Tierra'),
(10, 'Los siete pecados capitales', 'Simon Sinek', 'Ciencia', '9779005104063', 'Sota Verde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `idPedido` int(11) NOT NULL,
  `productos_id` int(11) NOT NULL,
  `precioPedido` int(11) NOT NULL,
  `estadoPedido` varchar(60) NOT NULL,
  `fechaPedido` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`idPedido`, `productos_id`, `precioPedido`, `estadoPedido`, `fechaPedido`) VALUES
(1, 5, 1, 'pendiente', '2022-03-05'),
(2, 8, 8, 'pendiente', '2022-03-05'),
(3, 1, 1, 'recogido', '2022-03-03'),
(4, 7, 7, 'recogido', '2022-03-02');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carta`
--
ALTER TABLE `carta`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `productos_id` (`productos_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carta`
--
ALTER TABLE `carta`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `idPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`productos_id`) REFERENCES `carta` (`idProducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecalpii
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `id_area` int NOT NULL AUTO_INCREMENT,
  `nombre_area` varchar(30) NOT NULL,
  PRIMARY KEY (`id_area`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Marketing'),(2,'Recursos Humanos'),(3,'Ventas'),(4,'Finanzas'),(5,'Tecnología');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id_autor` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(30) NOT NULL,
  `nombre_completo` varchar(30) NOT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'Smith','John Smith'),(2,'García','Laura García'),(3,'Dupont','François Dupont'),(4,'González','Lucía González'),(5,'Johnson','Andrew Johnson');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(30) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nombre_completo` varchar(30) DEFAULT NULL,
  `telefono` varchar(9) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Navarro','Av. proceres','45875645','daniel@gmail.com','Daniel','956458746',''),(12,'González','Av. Abancay 123','12345678','maria.gonzalez@gmail.com','María González','555-12342',''),(13,'García','Calle Los Álamos 456','23456789','pedro.garcia@hotmail.com','Pedro García','555-56784',''),(14,'Fernández','Calle Las Flores 789','34567890','lucia.fernandez@yahoo.com','Lucía Fernández','555-90127',''),(15,'Rodríguez','Av. La Marina 234','45678901','javier.rodriguez@gmail.com','Javier Rodríguez','555-34566',''),(16,'Martínez','Calle Las Palmas 567','56789012','ana.martinez@outlook.com','Ana Martínez','555-78907',''),(17,'Sánchez','Calle Las Begonias 890','67890123','pablo.sanchez@gmail.com','Pablo Sánchez','555-23455',''),(18,'Pérez','Av. La Merced 123','78901234','carmen.perez@yahoo.com','Carmen Pérez','555-67891',''),(19,'López','Calle Los Girasoles 456','89012345','david.lopez@hotmail.com','David López','555-01237',''),(20,'Ruiz','Calle Los Pinos 789','90123456','monica.ruiz@gmail.com','Mónica Ruiz','555-45676',''),(21,'López','Av. Los Cóndores 234','01234567','jose.lopez@hotmail.com','José López','555-89015',''),(22,'Gomez','Av.proceres','73020044','tommy@gmail.com','tommy','994034731','$2a$12$4Her2QH1lYNlKerVxxLySOlE1ZaFMjl71VDng9Xz6qr76nV7IoRTC'),(23,'Laura','Av.proceres','73020044','pedro@gmail.com','pedro','994034731','$argon2id$v=19$m=16,t=2,p=1$N0RMbHhyYlI2SllyNVVvYQ$HDGBwGGL9XuAENaaaaLVHw'),(24,'Perez','Av.proceres','73020044','juan@gmail.com','juan','994034731','$argon2id$v=19$m=1024,t=3,p=1$RFZDVU1VajN4MHpocWVpQw$qx+7fqgSPv5FxH2rXbhGoQ'),(25,'Navarro','Av. proceres','45875678','alejandro@gmail.com','alejandro','956458745','$2a$10$XXjAnpT.ttbLyVv3SJ4jUe6vCAeShn.WIEaG0GJukXOCqzLEwmIZu'),(26,'Gomez','Av.proceres','73040055','felipe@gmail.com','Felipe','994034731','$2a$12$qty17jsFANS4QKt9zkOVm.Dd.CP7duUW8SHFwdK/Z8ISN8oJ08dyq'),(27,'V.R','Peru','45785695','leticia@gmail.com','Leticia','495647854','$2a$10$ZGfnGQbOFkrqhqOzNPUfJu/yxWVmnUnhRfpVMte3D/O.B9SkBex22'),(28,'Gomez','Peru','45785695','juancho@gmail.com','Juancho','495647854','$2a$10$kK4.dhIQdoClX8/hha0WRe.FPqPSEwt2BSe2qI9TekzDJzYFPf78a'),(29,'gomez','Postes','74125896','niño@gmail.com','ñiño','741258963','$2a$10$Awbnrp.WNuraELpuf4RyvO.vI/AzPKZ1.zThjKbJ7q1yCKdKVWIw6'),(30,'Navarro','Postes','78945612','eduardo@gmail.com','Eduardo','745896321','$2a$10$27IIFG87Af9DUiBszTwpwejxIMlPfkT2RYo4vqRDxJKaGfbhZxFBO'),(31,'Navarro','Peru','74125896','pedro@gmail.com','Pedro','741258963','$2a$10$NXjfP.2D9YNRzwwTb67PN.egEK99mcsO01mBA2w4Ob6SinEx72pXK'),(32,'Gomez','Peru','45785697','andres@gmail.com','Andres','495647855','$2a$10$Dzy9sGeRoHxtU1TBgLD7hOsLw.4pb2tWXcRjxLgdIRXOX9TXZ8Nle'),(33,'Perez','Av.proceres','73020044','pedro@gmail.com','Pedro','923566574','$2a$10$fNgGsHpI6LwL5fhXejN1Fusp3DyFSrOKZyAF6K1ALSIFajZEL.Qgu'),(34,'Luque','Perú','74125896','m@gmail.com','Marjorie','563256854','$2a$10$z4LQZ6wATNcQS1FxllGgM.ictufKxxVa44hRyXm.4RRkmKtypHxQy'),(35,'Perez','Perú','73020044','andrea@gmail.com','Andrea','856125478','$2a$10$UgSX2e98AGQqppeV/Xq6ROffrVvcrOK5MZvTI6emg3aUlqQaifq9O'),(36,'Perez','Av.proceres de la Independecia 2138','78945612','fernando@gmail.com','Fernando','994034731','$2a$10$X5UZyU1ucVoXcY8Gsig5yugyFkB228lyN5gu8IEbdWkwVZwlBbdqu'),(37,'Tantalean','Av.proceres de la Independecia 2138','78945612','maria@gmail.com','Maria','994034731','$2a$10$sg68emx44ST23phyKBbp6Oe8e/ejK6jheLWS5UDmMeUlKed88IYnm'),(38,'Lopez','Av.proceres de la Independecia 2138','73020044','margarito@gmail.com','Margarito','994034731','$2a$10$0DQnpZ5U6puagRFsPsQJmORXqVGAzg6Sh4am/yww3A4/mVvdnAlam'),(39,'Gomez','Peru','45785685','lorenzo@gmail.com','Lorenzo','495647852','$2a$10$higZkYzZXaGchVbZKGM85Oz/8qWtQWaJF.CHB.ZYgI3xf04CKnQ6q'),(42,'Prueba','Av.proceres de la Independecia 2138','88855585','2113010091@untels.edu.pe','Prueba','994034731','$2a$10$3XkKQakXV1HdWG2NaEFbZOvqf5W9XGv/RgDBKOjzWXqY9rX.BAbxK');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editorial` (
  `id_editorial` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id_editorial`),
  KEY `FK_EDITORIAL_PAIS` (`id_pais`),
  CONSTRAINT `FK_EDITORIAL_PAIS` FOREIGN KEY (`id_pais`) REFERENCES `pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editorial`
--

LOCK TABLES `editorial` WRITE;
/*!40000 ALTER TABLE `editorial` DISABLE KEYS */;
INSERT INTO `editorial` VALUES (1,'Editorial Americana',1),(2,'Editorial Oxford',1),(3,'Editorial Santillana',2),(4,'Editorial Planeta',2),(5,'Editorial Hachette',3);
/*!40000 ALTER TABLE `editorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id_libro` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(20) NOT NULL,
  `año_publicacion` varchar(255) NOT NULL,
  `edicion` varchar(20) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `titulo` varchar(30) NOT NULL,
  `id_area` int NOT NULL,
  `id_editorial` int NOT NULL,
  `id_tipo` int NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `precio` int NOT NULL,
  `autor` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_libro`),
  KEY `FKpt6ycnvmp7myr5kmyy2xmpp6n` (`id_area`),
  KEY `FK7901riu71fmob1ocuw98n988m` (`id_editorial`),
  KEY `FK6nf9weabd4c44yx9ajq16l295` (`id_tipo`),
  CONSTRAINT `FK6nf9weabd4c44yx9ajq16l295` FOREIGN KEY (`id_tipo`) REFERENCES `tipo` (`id_tipo`),
  CONSTRAINT `FK7901riu71fmob1ocuw98n988m` FOREIGN KEY (`id_editorial`) REFERENCES `editorial` (`id_editorial`),
  CONSTRAINT `FKpt6ycnvmp7myr5kmyy2xmpp6n` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (21,'9788437613615','1967','Primera Edicion','disponible','Cien años de soledad',1,1,1,'https://m.media-amazon.com/images/I/91TvVQS7loL.jpg',20,'Gabriel García Márquez','Escrito por Gabriel García Márquez, esta novela cuenta la historia de la familia Buendía a lo largo de varias generaciones en el ficticio pueblo de Macondo, fusionando realidad y fantasía en un relato mágico y lleno de realismo mágico.'),(22,'9789568476310','1982','Primera Edicion','disponible','La casa de los espíritus',1,2,1,'https://books.google.com.pe/books/publisher/content?id=WY1IAgAAQBAJ&pg=PP1&img=1&zoom=3&hl=en&bul=1&sig=ACfU3U0NtChhoSIpatS1yjuit2khqvxgRg&w=1280',40,'Isabel Allende','Isabel Allende narra la historia de la familia Trueba a través de varias generaciones, explorando temas como la política, el amor, la magia y el destino en un contexto latinoamericano.'),(23,'9788497944178','1320','Primera Edicion','disponible','La divina comedia',2,3,2,'https://m.media-amazon.com/images/I/71WJbXGxPdL.jpg',20,'Dante Alighieri','Escrita por Dante Alighieri, es un poema épico que relata el viaje del protagonista a través del Infierno, el Purgatorio y el Paraíso, ofreciendo una visión alegórica y simbólica del viaje espiritual.'),(24,'9788420674510','1949','Primera Edicion','disponible','1984',3,4,1,'https://m.media-amazon.com/images/I/71kxa1-0mfL.jpg',23,'George Orwell','George Orwell crea una distopía en la que el Estado totalitario controla todos los aspectos de la vida de los ciudadanos, presentando una crítica feroz al poder, la manipulación y la pérdida de la libertad individual.'),(25,'9788408069005','1943','Primera Edicion','disponible','El Principito',4,5,1,'https://m.media-amazon.com/images/I/81IUHwbPxTL.jpg',40,'Antoine de Saint-Exupéry','Escrito por Antoine de Saint-Exupéry, es una obra maestra de la literatura infantil que narra las aventuras de un niño proveniente de un asteroide y su encuentro con diferentes personajes.'),(26,'9788497941702','1851','Primera Edicion','disponible','Moby Dick',2,1,2,'https://m.media-amazon.com/images/I/81+3Wl6KVNL.jpg',50,'Herman Melville','Herman Melville relata la obsesión del capitán Ahab por capturar a una gran ballena blanca llamada Moby Dick, convirtiéndose en una épica historia sobre la naturaleza humana, la obsesión y la lucha contra fuerzas incontrolables.'),(27,'9788499891878','1925','Primera Edicion','disponible','El Gran Gatsby',3,2,1,'https://m.media-amazon.com/images/I/81aWosOAepL.jpg',30,'F. Scott Fitzgerald','Escrito por F. Scott Fitzgerald, sigue la vida de Jay Gatsby, un hombre adinerado obsesionado con reconquistar a su antiguo amor, Daisy Buchanan. La novela explora temas como el sueño americano, la corrupción y la decadencia de la sociedad de los años 20.'),(29,'9788478884459','1997','Primera Edicion','disponible','Harry Potter',5,4,1,'https://m.media-amazon.com/images/I/81pM4orRvFL.jpg',25,'J.K. Rowling',' La famosa serie de J.K. Rowling sigue las aventuras del joven mago Harry Potter mientras descubre el mundo de la magia, lucha contra el malvado Lord Voldemort y explora temas de amistad, valentía y destino.'),(30,'9788497941344','1813','Primera Edicion','disponible','Orgullo y Prejuicio',3,5,1,'https://m.media-amazon.com/images/I/81smOptGtLL.jpg',16,'Jane Austen','Jane Austen nos presenta una comedia romántica que sigue las vidas de las hermanas Bennet y sus encuentros con distintos pretendientes, retratando la sociedad y las convenciones sociales de la época.'),(45,'9786070700133','2008','Primera Edicion','disponible','Los juegos del hambre',3,4,1,'https://m.media-amazon.com/images/I/41k0dOgLx+L.jpg',20,'Suzanne Collins','Suzanne Collins nos lleva a un futuro distópico donde los jóvenes son obligados a participar en un juego mortal de supervivencia. La trilogía sigue a Katniss Everdeen mientras desafía al sistema y lidera una rebelión contra el gobierno opresor.'),(46,'9788483231144','2007','Primera Edicion','disponible','El nombre del viento',5,2,1,'https://m.media-amazon.com/images/I/6195Z6SDAkL.jpg',18,'Patrick Rothfuss','Patrick Rothfuss cuenta la historia de Kvothe, un prodigioso músico y mago, a través de sus propias palabras, entrelazando misterio, aventura y magia en un mundo fantástico lleno de detalles y personajes cautivadores.'),(47,'9788420402437','2001','Primera Edicion','disponible','La sombra del viento',4,1,1,'https://m.media-amazon.com/images/I/513aLEseZiL.jpg',29,'Carlos Ruiz Zafón','Carlos Ruiz Zafón nos transporta a la Barcelona de posguerra, donde un joven llamado Daniel Sempere descubre un libro maldito y se adentra en una trama llena de secretos, intriga y amor por los libros.'),(49,'9788497594306','2003','Primera Edicion','disponible','El código Da Vinci',1,4,2,'https://m.media-amazon.com/images/I/51riEVXta5L.jpg',23,'Dan Brown','Dan Brown presenta una historia de suspense y misterio que sigue al profesor Robert Langdon en su búsqueda del Santo Grial, desentrañando una serie de códigos y enigmas ligados a la Iglesia católica y a la vida de Leonardo da Vinci.'),(50,'9788445077650','1937','Primera Edicion','disponible','El Hobbit',5,2,2,'https://m.media-amazon.com/images/I/41FvFVUZLjL._SY291_BO1,204,203,200_QL40_FMwebp_.jpg',30,'J.R.R. Tolkien','J.R.R. Tolkien nos sumerge en un viaje épico con Bilbo Bolsón, un hobbit que se une a un grupo de enanos y al mago Gandalf en su misión para reclamar el reino perdido de Erebor, enfrentando peligros y encontrando criaturas mágicas en el camino.'),(52,'9788420473817','1985','Primera Edicion','disponible','El amor en tiempos de cólera',2,3,5,'https://m.media-amazon.com/images/I/51GH-QYAzDL._SX316_BO1,204,203,200_.jpg',24,'Gabriel García Márquez','Gabriel García Márquez relata la historia de amor de Florentino Ariza y Fermina Daza, quienes se reencuentran en la vejez después de años separados, explorando temas como el amor, la pasión y el paso del tiempo.'),(53,'978-84-08-04717-5','2002','Primera Edicion','disponible','El Psicoanalista',1,4,2,'https://m.media-amazon.com/images/I/81UHpI13c8L._AC_UY327_FMwebp_QL65_.jpg',25,'John Katzenbach','ohn Katzenbach narra la historia de un psicoanalista llamado Frederick Starks, quien se enfrenta a un misterioso paciente que amenaza con destruir su vida, llevándolo a una carrera contrarreloj para descubrir su identidad.'),(54,'978-84-7953-276-4','1988','Primera Edicion','disponible','El alquimista',3,5,5,'https://m.media-amazon.com/images/I/81KYCUSk8KL._AC_UY327_FMwebp_QL65_.jpg',26,'Paulo Coelho','Paulo Coelho cuenta la historia de Santiago, un joven pastor que emprende un viaje en busca de su tesoro personal, descubriendo lecciones espirituales y aprendiendo a seguir sus sueños en el proceso.'),(66,'9788420665058','1898','Primera Edicion','disponible','La Guerra de los Mundos',3,5,3,'https://m.media-amazon.com/images/I/615ZMYhZvjL._AC_UY327_FMwebp_QL65_.jpg',32,'H.G. Wells','H.G. Wells narra una invasión extraterrestre en la Tierra, explorando las consecuencias y la lucha de la humanidad para sobrevivir ante una fuerza superior e implacable.'),(67,'9780307473332','2011','Primera Edicion','disponible','La Fisica del Futuro',5,2,3,'https://m.media-amazon.com/images/I/81UEbtZRmzL._AC_UY327_FMwebp_QL65_.jpg',36,'Michio Kaku','Michio Kaku ofrece una visión fascinante del futuro científico y tecnológico de la humanidad, explorando posibles avances en campos como la energía, la medicina, la inteligencia artificial y los viajes espaciales.'),(68,'9788467030876','2006','Primera Edicion','disponible','La Elegancia del Erizo',1,3,2,'https://m.media-amazon.com/images/I/71YIt3-zApL._AC_UY327_FMwebp_QL65_.jpg',15,'Muriel Barbery','Muriel Barbery narra la historia de dos personajes, la portera Renée y la joven Paloma, quienes ocultan su inteligencia y sensibilidad detrás de una fachada de mediocridad, explorando temas como la belleza, la soledad y la filosofía de la vida.'),(69,'9788490017482','1890','Primera Edicion','disponible','El Retrato de Dorian Gray',1,4,1,'https://m.media-amazon.com/images/I/71Cd3in7reL._AC_UL480_FMwebp_QL65_.jpg',28,'Oscar Wilde','Oscar Wilde presenta la historia de Dorian Gray, un joven cuya belleza y juventud se mantienen intactas mientras su retrato envejece y muestra los estragos de sus acciones inmorales, explorando temas de belleza, corrupción y doble moral.'),(70,'9788497593557','1989','Primera Edicion','disponible','Los Pilares de la Tierra',4,1,1,'https://m.media-amazon.com/images/I/918YWcFfpoL._AC_UY327_FMwebp_QL65_.jpg',29,'Ken Follett','Ken Follett nos transporta a la Inglaterra medieval, siguiendo la construcción de una catedral gótica en el pueblo ficticio de Kingsbridge, donde se entrelazan las vidas de diferentes personajes en medio de intrigas políticas y religiosas.'),(71,'9788491052663','1844','Primera Edicion','disponible','El Conde de Montecristo',1,5,1,'https://m.media-amazon.com/images/I/51LjEaq3TIL._AC_UL480_FMwebp_QL65_.jpg',30,'Alexandre Dumas','Alexandre Dumas narra la historia de Edmond Dantès, quien es traicionado y encarcelado injustamente, pero regresa bajo la identidad del Conde de Montecristo para vengarse de aquellos que le arruinaron la vida.'),(73,'9788498920817','2002','Primera Edicion','disponible','El mundo antiguo',4,4,4,'https://www.alianzaeditorial.es/imagenes/libros/grande/9788413625287-el-mundo-antiguo.jpg',35,'Michael Scott','Con un enfoque ameno y accesible, Scott nos transporta a través del tiempo y nos muestra la grandeza y los logros de culturas como la egipcia, la mesopotámica, la griega y la romana.'),(75,'9780140275360','-800','Primera Edicion','disponible','La Iliada',4,1,2,'https://m.media-amazon.com/images/I/41TRzQp+V2L._SY346_.jpg',34,'Homero','Atribuida a Homero, esta epopeya clásica narra los eventos de la guerra de Troya, centrándose en el conflicto entre los griegos y los troyanos, y presentando personajes legendarios como Aquiles y Héctor.'),(76,'9786073114815','1995','Primera Edicion','disponible','El mundo y sus demonios',5,4,3,'https://m.media-amazon.com/images/I/411c9bm8PVL.jpg',25,'Carl Sagan','Carl Sagan examina el papel de la ciencia en la sociedad y explora la importancia del pensamiento crítico y la evidencia empírica en la lucha contra la superstición, la ignorancia y la pseudociencia.'),(77,'9789688531155','1956','Primera Edicion','disponible','El arte de amar',2,5,5,'https://m.media-amazon.com/images/I/81loRYpWp0L._AC_UY327_FMwebp_QL65_.jpg',26,'Erich Fromm','Erich Fromm aborda el amor y las relaciones humanas desde una perspectiva psicológica y filosófica, explorando distintas formas de amor y proporcionando reflexiones sobre cómo construir relaciones saludables y satisfactorias.'),(78,'9789875666472','1962','Primera Edicion','disponible','La naranja mecánica',4,4,1,'https://m.media-amazon.com/images/I/616LPlvHW1L._AC_UY327_FMwebp_QL65_.jpg',27,'Anthony Burgess','Anthony Burgess nos presenta una distopía violenta en la que el protagonista, Alex, lidera una pandilla y se somete a un experimento para rehabilitarlo, explorando temas de libre albedrío, violencia y manipulación del comportamiento.'),(79,'9788437603053','1949','Primera Edicion','disponible','El Aleph',5,3,1,'https://m.media-amazon.com/images/I/71DrhHHKgQL._AC_UY327_FMwebp_QL65_.jpg',28,'Jorge Luis Borges','Jorge Luis Borges recopila una serie de cuentos que exploran el tiempo, la memoria y la infinitud, y en el cuento que da título al libro, el protagonista experimenta una revelación cósmica al encontrar el Aleph, un punto que contiene todo el universo.');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros_autor`
--

DROP TABLE IF EXISTS `libros_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros_autor` (
  `id_libro_autor` int NOT NULL AUTO_INCREMENT,
  `id_autor` int NOT NULL,
  `id_libro` int NOT NULL,
  PRIMARY KEY (`id_libro_autor`),
  KEY `FK8lvtkldtsuv08ytglic0wr9yk` (`id_autor`),
  KEY `FK8c8x4rfnsq9dmrulyuxd2vo66` (`id_libro`),
  CONSTRAINT `FK8c8x4rfnsq9dmrulyuxd2vo66` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`),
  CONSTRAINT `FK8lvtkldtsuv08ytglic0wr9yk` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros_autor`
--

LOCK TABLES `libros_autor` WRITE;
/*!40000 ALTER TABLE `libros_autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `libros_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pais` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Estados Unidos'),(2,'España'),(3,'Francia');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'Ficción'),(2,'No Ficción'),(3,'Ciencia Ficción'),(4,'Historia'),(5,'Romance');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `fecha_venta` datetime(6) NOT NULL,
  `impuesto` decimal(6,2) NOT NULL,
  `total` decimal(6,2) NOT NULL,
  `id_cliente` int NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `FK_VENTA_CLIENTE` (`id_cliente`),
  CONSTRAINT `FK_VENTA_CLIENTE` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (2,'2023-04-23 22:39:58.179812',9.00,50.00,1),(19,'2023-05-10 22:20:56.967265',9.00,50.00,22),(20,'2023-05-12 21:44:35.790881',9.00,50.00,26),(21,'2023-05-12 21:52:28.247018',18.00,100.00,26),(22,'2023-05-12 22:28:27.016873',27.00,150.00,26),(23,'2023-05-12 22:29:21.932115',9.00,50.00,30),(24,'2023-06-24 18:39:10.859133',14.40,94.40,30),(25,'2023-06-24 19:52:21.364167',7.20,40.00,30),(26,'2023-06-25 14:57:06.749281',14.40,100.00,26),(27,'2023-06-25 15:07:32.790594',14.40,80.00,26),(28,'2023-06-25 16:01:17.265642',14.40,94.40,1),(29,'2023-06-29 11:13:59.095023',14.40,94.40,1),(30,'2023-06-29 11:19:25.497315',14.40,20.00,26),(31,'2023-06-29 18:46:47.145750',14.40,20.00,26),(32,'2023-06-29 18:52:28.206510',14.40,20.00,26),(33,'2023-07-04 10:13:48.944466',43.20,240.00,26),(35,'2023-07-09 13:22:58.780193',14.40,40.00,42);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta_detalle`
--

DROP TABLE IF EXISTS `venta_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta_detalle` (
  `id_venta_detalle` int NOT NULL AUTO_INCREMENT,
  `cantidad` smallint NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `id_libro` int NOT NULL,
  `id_venta` int NOT NULL,
  PRIMARY KEY (`id_venta_detalle`),
  KEY `FKspyg2p9pef2vdtotq2hj9fb0n` (`id_libro`),
  KEY `FKrhdv8ci51e1bwdcgrp9ls8ko4` (`id_venta`),
  CONSTRAINT `FKrhdv8ci51e1bwdcgrp9ls8ko4` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `FKspyg2p9pef2vdtotq2hj9fb0n` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_detalle`
--

LOCK TABLES `venta_detalle` WRITE;
/*!40000 ALTER TABLE `venta_detalle` DISABLE KEYS */;
INSERT INTO `venta_detalle` VALUES (2,1,50.00,21,2),(3,1,50.00,22,19),(4,1,50.00,26,20),(5,2,50.00,26,21),(6,3,50.00,26,22),(7,1,50.00,26,23),(8,1,50.00,26,24),(9,1,30.00,27,24),(10,1,40.00,22,25),(11,1,40.00,22,26),(12,3,20.00,21,26),(13,4,20.00,21,27),(14,1,50.00,26,28),(15,1,30.00,27,28),(16,1,50.00,26,29),(17,1,30.00,27,29),(18,1,20.00,23,30),(19,1,20.00,23,31),(20,1,20.00,23,32),(21,6,40.00,22,33),(23,1,40.00,22,35);
/*!40000 ALTER TABLE `venta_detalle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-11  8:49:46

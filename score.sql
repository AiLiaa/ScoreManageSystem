/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.20 : Database - scoresystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scoresystem` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `scoresystem`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(16) DEFAULT 'null',
  `course_credit` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2130739202 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`course_name`,`course_credit`) values (-1883209726,'数据库原理与应用',1.5),(-1388281855,'高等数学',3),(-1027571710,'计算机网络',2),(-759136255,'马克思主义原理',2),(-230653950,'算法设计与分析',2),(-176136190,'数据结构',2),(-50307071,'操作系统',2.5),(41975809,'思想道德修养',2),(41975810,'概率论',2),(549486594,'线性代数',2),(612401154,'大学英语1',2),(738230274,'计算机组成原理',2),(801144833,'python',1.5),(893411329,'C语言',2),(1375764481,'体育1',2),(1417707522,'大学物理',4),(1740668930,'CSAPP',3),(2130739201,'软件工程概论',2);

/*Table structure for table `secret_key` */

DROP TABLE IF EXISTS `secret_key`;

CREATE TABLE `secret_key` (
  `id` int NOT NULL AUTO_INCREMENT,
  `secret_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'null',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `secret_key` */

insert  into `secret_key`(`id`,`secret_key`) values (1,'sVnobf1V6g1eNvs64N5rE0sd51W');

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(16) DEFAULT 'null',
  `student_name` varchar(16) DEFAULT 'null',
  `course_id` int DEFAULT '0',
  `course_name` varchar(16) DEFAULT 'null',
  `course_credit` float DEFAULT '0',
  `daily_score` float DEFAULT '0',
  `exam_score` float DEFAULT '0',
  `final_score` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2025881603 DEFAULT CHARSET=utf8;

/*Data for the table `student_course` */

insert  into `student_course`(`id`,`student_id`,`student_name`,`course_id`,`course_name`,`course_credit`,`daily_score`,`exam_score`,`final_score`) values (-2050981887,'201901005','小美',738230274,'计算机组成原理',2,85,90,88.5),(406880258,'201901001','小明',-230653950,'算法设计与分析',2,90,95,92.5),(859869186,'201901005','小美',-230653950,'算法设计与分析',2,90,90,90),(2025881602,'201901001','小明',-176136190,'数据结构',2,90,85,88);

/*Table structure for table `student_teacher` */

DROP TABLE IF EXISTS `student_teacher`;

CREATE TABLE `student_teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(16) DEFAULT 'null',
  `teacher_id` varchar(16) DEFAULT 'null',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `student_teacher` */

insert  into `student_teacher`(`id`,`student_id`,`teacher_id`) values (11,'201901001','201902001'),(12,'201901001','201902002'),(13,'201901005','201902003'),(14,'201901005','201902004');

/*Table structure for table `teacher_course` */

DROP TABLE IF EXISTS `teacher_course`;

CREATE TABLE `teacher_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(16) DEFAULT 'null',
  `course_id` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `teacher_course` */

insert  into `teacher_course`(`id`,`teacher_id`,`course_id`) values (7,'201902001',-176136190),(8,'201902002',-230653950),(9,'201902003',738230274),(10,'201902004',-230653950);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'null',
  `user_id` varchar(16) DEFAULT 'null',
  `user_class` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'null',
  `credit` float DEFAULT '0',
  `type` int DEFAULT '1',
  `password` varchar(16) DEFAULT '123456',
  `major` varchar(16) DEFAULT 'null',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2134933506 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`user_id`,`user_class`,`credit`,`type`,`password`,`major`) values (-1895792639,'小五','201901006','软工2班',0,1,'123456','软件工程'),(-1585414142,'小白','201901004','计算机2班',0,1,'123456','计算机科学'),(-1296007166,'小明','201901001','计算机1班',4,1,'123456','计算机科学'),(-457146366,'王五','201902003','null',0,2,'123456','软件工程'),(-163545086,'小兰','201901003','计算机1班',0,1,'123456','计算机科学'),(633372673,'小美','201901005','软工1班',4,1,'123456','软件工程'),(1161846785,'管理员1','admin','null',0,3,'admin','null'),(1581285377,'小西','201901007','人工智能1班',0,1,'123456','人工智能'),(1656782850,'李四','201902002','null',0,2,'123456','计算机科学'),(1832943618,'张三','201902001','null',0,2,'123456','计算机科学'),(1996521474,'小红','201901002','计算机1班',0,1,'123456','计算机科学'),(2134933505,'赵六','201902004','null',0,2,'123456','人工智能');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

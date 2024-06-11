/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - foodie
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`foodie` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `foodie`;

/*Table structure for table `adds` */

DROP TABLE IF EXISTS `adds`;

CREATE TABLE `adds` (
  `add_id` int(11) NOT NULL AUTO_INCREMENT,
  `addpath` varchar(1000) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `noofview` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`add_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `adds` */

insert  into `adds`(`add_id`,`addpath`,`title`,`noofview`) values 
(1,'static/7488e77e-fab3-4534-ad0d-b01f724b7228war_-_61412 (360p).mp4','sakjdla','0'),
(7,'static/68eb3aa9-a7d4-44e0-9e13-0f9c3a6d1aa7video_20230303_202346.mp4','fdsfs','0'),
(6,'static/aa4ed4ed-75db-46d1-b6b0-e769ab26f497pexels-tima-miroshnichenko-6201055-3840x2160-25fps.mp4','fdrfdr','0'),
(8,'static/d4b17ccf-9123-46de-ba7d-e78fd4f5d97d39ecbd76-e549-4f87-9e39-381df5c21fe0 - Copy.mp4','fdfds','0'),
(9,'static/c4a7db59-78cd-4908-b4ac-14cc7fe9bd18f3ea064a-593d-43a9-81da-a7fc110521bc - Copy.mp4','dsfs','0');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,2,3,'hi','2023-04-11'),
(2,2,3,'yf','2023-04-25'),
(3,2,3,'good','2023-05-20'),
(4,2,3,'good','2023-05-20'),
(5,2,3,'good','2023-05-20'),
(6,2,3,'good','2023-05-20'),
(7,2,3,'good','2023-05-20'),
(8,2,3,'good','2023-05-20'),
(9,5,4,'hii','2023-05-24'),
(10,3,2,'hii','2023-05-25'),
(11,3,2,'hii','2023-05-25'),
(12,3,2,'hii','2023-05-25'),
(13,3,5,'hii','2023-05-25'),
(14,3,4,'hloi','2023-05-25');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'san','san','user'),
(3,'kallu','kallu','user'),
(4,'reshma','reshma','user'),
(5,'ash','ash','user');

/*Table structure for table `recipy` */

DROP TABLE IF EXISTS `recipy`;

CREATE TABLE `recipy` (
  `recipy_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `incredints` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `video` varchar(2000) DEFAULT NULL,
  `additional_dtls` varchar(100) DEFAULT NULL,
  `diet_mode` varchar(10) DEFAULT NULL,
  `calorie` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`recipy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `recipy` */

insert  into `recipy`(`recipy_id`,`user_id`,`name`,`incredints`,`image`,`video`,`additional_dtls`,`diet_mode`,`calorie`) values 
(4,1,'Fried Rice','dnsjadyhujsa,dsabdbsads,dasbdsabb,dasbd','static/f47b4cd9-4a0c-4214-8a8a-15217bed1e59food2.jpg','static/06b722fc-723c-4e86-a3b8-f55d716aed12WhatsApp Video 2022-11-14 at 11.09.34 AM.mp4','Peper mix','1','300'),
(3,1,'Cheezy Sandwich ','sakndsa,dsabdsaj,dasmjn','static/ce3f1e72-f944-47d9-a05b-2b5afc184e3efood1.jpg','static/12bde703-bab4-42d4-acdf-40ed173f96aaWhatsApp Video 2022-11-14 at 11.09.34 AM.mp4','Cheezy','0','200'),
(5,2,'Veg Salad','sakndsa,dsabdsaj,dasmjn','static/uploads/e4241ad1-21d5-4689-943e-1574dc2be80cabc.jpg','static/uploads/5dda3597-5d87-45db-ad97-9974918dd6bdabc.mp4','Healthy diet','1','500'),
(8,2,'burger','weg','static/uploads/7fb7e065-f5e9-4a6f-98cb-e55d5939dbefabc.jpg','static/uploads/3f688327-58e2-408e-ac0a-efba387f28ababc.mp4','adfhkk','1','300');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`pic`) values 
(1,2,'san','kar','alpy','6238526459','sankusanku001@gmail.com','static/profilepic/ecc6f8c7-2d2a-4572-8537-6e496c44ebdfabc.jpg'),
(2,3,'kallu','TK','ekm','6238526455','kallu@gmail.com','static/profilepic/b841820a-7261-4226-ab59-8163b2010b63abc.jpg'),
(3,4,'reshma','s','kottayam','6238526459','kidyt001@gmail.com','static/profilepic/b841820a-7261-4226-ab59-8163b2010b63abc.jpg'),
(4,5,'ashna','ash','kottayam ','9876676767','ash@gmail.com','static/profilepic/ecc6f8c7-2d2a-4572-8537-6e496c44ebdfabc.jpg');

/*Table structure for table `view_count` */

DROP TABLE IF EXISTS `view_count`;

CREATE TABLE `view_count` (
  `view_count_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `recipy_id` int(11) DEFAULT NULL,
  `count` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`view_count_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `view_count` */

insert  into `view_count`(`view_count_id`,`user_id`,`recipy_id`,`count`) values 
(1,1,7,'1'),
(2,1,3,'1'),
(3,1,4,'1'),
(4,1,5,'1'),
(5,2,7,'1'),
(6,4,3,'1'),
(7,4,5,'1'),
(8,2,8,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

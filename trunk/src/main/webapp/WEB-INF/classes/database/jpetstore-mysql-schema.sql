-- drop table section;

CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `moderated` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

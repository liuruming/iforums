use iforums2;
--
-- Table structure for table 'banlist'
--
DROP TABLE IF EXISTS banlist;
CREATE TABLE banlist (
  id INT NOT NULL auto_increment,
  userid INT,
  ip varchar(15),
  email varchar(255),
  PRIMARY KEY  (id),
  INDEX idx_user (userid),
  INDEX (ip),
  INDEX (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'categories'
--
DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id INT NOT NULL auto_increment,
  name varchar(100) NOT NULL default '',
  weight INT NOT NULL default '0',
  moderated TINYINT(1),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'config'
--
DROP TABLE IF EXISTS config;
CREATE TABLE config (
  name varchar(255) NOT NULL default '',
  value varchar(255) NOT NULL default '',
  id int not null auto_increment,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'forums'
--
DROP TABLE IF EXISTS forums;
CREATE TABLE forums (
  id INT NOT NULL auto_increment,
  categoryid INT NOT NULL default '1',
  name varchar(150) NOT NULL default '',
  description varchar(255) default NULL,
  weight INT  NOT NULL default '0',
  topics INT  NOT NULL default '0',
  lasttopicid INT NOT NULL default '0',
  moderated TINYINT(1) DEFAULT '0',
  PRIMARY KEY  (id),
  KEY (categoryid),
  INDEX idx_cats (categoryid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'watch'
--
DROP TABLE IF EXISTS watch;
CREATE TABLE watch (
  forumId INT NOT NULL,
  userid INT NOT NULL,
  INDEX idx_fw_forum (forumid),
  INDEX idx_fw_user (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'groups'
--
DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
  id INT NOT NULL auto_increment,
  name varchar(40) NOT NULL default '',
  description varchar(255) default NULL,
  parentid INT default '0',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
	id INT NOT NULL,
	userId INT NOT NULL,
	INDEX idx_group (id),
	INDEX idx_user (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'roles'
--
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  groupid INT default '0',
  name varchar(255) NOT NULL,
  INDEX idx_group (groupid),
  INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'role_values'
--
DROP TABLE IF EXISTS role_values;
CREATE TABLE role_values (
  id INT NOT NULL,
  value VARCHAR(255),
  INDEX idx_role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'posts'
--
DROP TABLE IF EXISTS posts;
CREATE TABLE posts (
  id INT NOT NULL auto_increment,
  topicid INT NOT NULL default '0',
  forumid INT NOT NULL default '0',
  userid INT NOT NULL default '0',
  posttime datetime default NULL,
  posterip varchar(15) default NULL,
  enablebbcode tinyint(1) NOT NULL default '1',
  enablehtml tinyint(1) NOT NULL default '1',
  enablesmilies tinyint(1) NOT NULL default '1',
  enablesig tinyint(1) NOT NULL default '1',
  postedittime datetime default NULL,
  posteditcount INT NOT NULL default '0',
  status tinyint(1) default '1',
  attach TINYINT(1) DEFAULT '0',
  needmoderate TINYINT(1) DEFAULT '0',
  PRIMARY KEY  (id),
  KEY (userid),
  KEY (topicid),
  KEY (forumid),
  KEY(posttime),
  INDEX (needmoderate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'posts_text'
--
DROP TABLE IF EXISTS posts_text;
CREATE TABLE posts_text (
	id INT NOT NULL PRIMARY KEY,
	text TEXT,
	title VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'privmsgs'
--
DROP TABLE IF EXISTS privmsgs;
CREATE TABLE privmsgs (
  id INT NOT NULL auto_increment,
  type tinyint(4) NOT NULL default '0',
  subject varchar(255) NOT NULL default '',
  fromuserid INT NOT NULL default '0',
  touserid INT NOT NULL default '0',
  date datetime default null,
  ip varchar(15) NOT NULL default '',
  enablebbcode tinyint(1) NOT NULL default '1',
  enablehtml tinyint(1) NOT NULL default '0',
  enablesmilies tinyint(1) NOT NULL default '1',
  attachsig tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

DROP TABLE IF EXISTS text;
CREATE TABLE text (
	id INT NOT NULL,
	text TEXT,
	PRIMARY KEY ( id )
);

--
-- Table structure for table 'ranks'
--
DROP TABLE IF EXISTS ranks;
CREATE TABLE ranks (
  id INT NOT NULL auto_increment,
  title varchar(50) NOT NULL default '',
  min INT NOT NULL default '0',
  special tinyint(1) default NULL,
  image varchar(255) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'sessions'
--
DROP TABLE IF EXISTS sessions;
CREATE TABLE sessions (
  id varchar(150) NOT NULL default '',
  userid INT NOT NULL default '0',
  start datetime default null,
  time bigint default '0',
  ip varchar(15) NOT NULL default '',
  page int(11) NOT NULL default '0',
  logged tinyint(1) default NULL,
  INDEX idx_sessions_users (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'smilies'
--
DROP TABLE IF EXISTS smilies;
CREATE TABLE smilies (
  id INT NOT NULL auto_increment,
  code varchar(50) NOT NULL default '',
  url varchar(100) default NULL,
  diskname varchar(255),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'themes'
--
DROP TABLE IF EXISTS themes;
CREATE TABLE themes (
  id INT NOT NULL auto_increment,
  name varchar(30) NOT NULL default '',
  stylename varchar(30) NOT NULL default '',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'topics'
--
DROP TABLE IF EXISTS topics;
CREATE TABLE topics (
  id INT NOT NULL auto_increment,
  forumid INT NOT NULL default '0',
  title varchar(100) NOT NULL default '',
  userid INT NOT NULL default '0',
  time datetime default null,
  views INT default '1',
  replies INT default '0',
  status tinyint(3) default '0',
  voteid INT NOT NULL default '0',
  type tinyint(3) default '0',
  firstpostid INT default '0',
  lastpostid INT NOT NULL default '0',
  movedid INT DEFAULT 0,
  moderated TINYINT(1) DEFAULT '0',
  PRIMARY KEY  (id),
  KEY (forumid),
  KEY(userid),
  KEY(firstpostid),
  KEY(lastpostid),
  KEY(movedid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'topics_watch'
--
DROP TABLE IF EXISTS topics_watch;
CREATE TABLE topics_watch (
  topicid INT NOT NULL,
  userid INT NOT NULL,
  readed tinyint(1) DEFAULT '1',
  INDEX idx_topic (topicid),
  INDEX idx_user (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'users'
--
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT NOT NULL auto_increment,
  active tinyint(1) default NULL,
  username varchar(50) NOT NULL default '',
  password varchar(32) NOT NULL default '',
  sessiontime bigint default 0,
  sessionpage INT NOT NULL default '0',
  lastvisit datetime default null,
  registrationDate datetime default null,
  level tinyint(4) default NULL,
  posts INT NOT NULL default '0',
  timezone varchar(5) NOT NULL default '',
  style tinyint(4) default NULL,
  lang varchar(255) NOT NULL default '',
  dateformat varchar(20) NOT NULL default '%d/%M/%Y %H:%i',
  newprivmsg INT NOT NULL default '0',
  unreadprivmsg INT NOT NULL default '0',
  lastprivmsg datetime NULL,
  emailtime datetime default NULL,
  viewemail tinyint(1) default '0',
  attachsig tinyint(1) default '1',
  allowhtml tinyint(1) default '0',
  allowbbcode tinyint(1) default '1',
  allowsmilies tinyint(1) default '1',
  allowavatar tinyint(1) default '1',
  allowpm tinyint(1) default '1',
  allowviewonline tinyint(1) default '1',
  notify tinyint(1) default '1',
  notifyalways tinyint(1) default '0',
  notifytext tinyint(1) default '0',
  notifypm tinyint(1) default '1',
  popuppm tinyint(1) default '1',
  rankid INT default 0,
  avatar varchar(100) default NULL,
  avatartype tinyint(4) NOT NULL default '0',
  email varchar(255) NOT NULL default '',
  icq varchar(15) default NULL,
  website varchar(255) default NULL,
  userfrom varchar(100) default NULL,
  sig text,
  sigbbcodeuid varchar(10),
  aim varchar(255) default NULL,
  yim varchar(255) default NULL,
  msnm varchar(255) default NULL,
  occ varchar(100) default NULL,
  interests varchar(255) default NULL,
  biography text DEFAULT NULL,
  actkey varchar(32) default NULL,
  gender char(1) default NULL,
  themesId INT default NULL,
  deleted tinyint(1) default NULL,
  viewonline tinyint(1) default '1',
  securityhash varchar(32),
  karma DOUBLE,
  authhash VARCHAR(32),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'vote_desc'
--
DROP TABLE IF EXISTS vote;
CREATE TABLE vote (
  id INT NOT NULL auto_increment,
  topicid INT NOT NULL default '0',
  text varchar(255) NOT NULL default '',
  startTime datetime NOT NULL,
  length int(11) NOT NULL default '0',
  PRIMARY KEY  (id),
  INDEX(topicid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'vote_results'
--
DROP TABLE IF EXISTS vote_results;
CREATE TABLE vote_results (
  voteid INT NOT NULL default '0',
  optionid tinyint(4) NOT NULL default '0',
  optiontext varchar(255) NOT NULL default '',
  result int(11) NOT NULL default '0',
  INDEX(voteid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'vote_voters'
--
DROP TABLE IF EXISTS vote_voters;
CREATE TABLE vote_voters (
  voteid INT NOT NULL default '0',
  userId INT NOT NULL default '0',
  userip varchar(15) NOT NULL default '',
  INDEX(voteid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'words'
--
DROP TABLE IF EXISTS words;
CREATE TABLE words (
  id INT NOT NULL auto_increment,
  word varchar(100) NOT NULL default '',
  replacement varchar(100) NOT NULL default '',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'karma'
--
DROP TABLE IF EXISTS karma;
CREATE TABLE karma (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	postid INT NOT NULL,
	topicid INT NOT NULL,
	postuserid INT NOT NULL,
	fromid INT NOT NULL,
	points INT NOT NULL,
	ratedate datetime NULL,
	KEY(postid),
	KEY(topicid),
	KEY(postid),
	KEY(fromid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'bookmark'
--
DROP TABLE IF EXISTS bookmarks;
CREATE TABLE bookmarks (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userid INT NOT NULL,
	relationid INT NOT NULL,
	relationtype INT NOT NULL,
	publicvisible INT DEFAULT '1',
	title varchar(255),
	description varchar(255),
	INDEX book_idx_relation (relationid),
	KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
-- 
-- Table structure for table 'quota_limit'
--
DROP TABLE IF EXISTS quota_limit;
CREATE TABLE quota_limit (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	quotalimit INT,
	quotatype TINYINT(1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'extension_groups'
--
DROP TABLE IF EXISTS extension_groups;
CREATE TABLE extension_groups (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	allow TINYINT(1) DEFAULT '1', 
	uploadicon VARCHAR(100),
	downloadmode TINYINT(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- 
-- Table structure for table 'extensions'
--
DROP TABLE IF EXISTS extensions;
CREATE TABLE extensions (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	groupid INT NOT NULL,
	description VARCHAR(100),
	uploadicon VARCHAR(100),
	extension VARCHAR(10),
	allow TINYINT(1) DEFAULT '1',
	KEY(groupid),
	INDEX(extension)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'attach'
--
DROP TABLE IF EXISTS attach;
CREATE TABLE attach (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postid` int(11) DEFAULT NULL,
  `privmsgsid` int(11) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_att_post` (`postid`),
  KEY `idx_att_priv` (`privmsgsid`),
  KEY `idx_att_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- 
-- Table structure for table 'attach_desc'
--
DROP TABLE IF EXISTS attach_desc;
CREATE TABLE attach_desc (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	attachid INT NOT NULL,
	physicalfilename VARCHAR(255) NOT NULL,
	realfilename VARCHAR(255) NOT NULL,
	downloadcount INT,
	description VARCHAR(255),
	mimetype VARCHAR(50),
	filesize INT,
	uploadtime DATETIME,
	thumb TINYINT(1) DEFAULT '0',
	extensionid INT,
	INDEX idx_att_d_att(attachid),
	INDEX idx_att_d_ext(extensionid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'attach_quota'
--
DROP TABLE IF EXISTS attach_quota;
CREATE TABLE attach_quota (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	groupid INT NOT NULL,
	quotalimitid INT NOT NULL,
	KEY(groupid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'banner'
--
DROP TABLE IF EXISTS banner;
CREATE TABLE banner (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(90),
	placement INT NOT NULL DEFAULT '0',
	description VARCHAR(250),
	clicks INT NOT NULL DEFAULT '0',
	views INT NOT NULL DEFAULT '0',
	url VARCHAR(250),
	weight TINYINT(1) NOT NULL DEFAULT '50',
	active TINYINT(1) NOT NULL DEFAULT '0',
	comment VARCHAR(250),
	type INT NOT NULL DEFAULT '0',
	width INT NOT NULL DEFAULT '0',
	height INT NOT NULL DEFAULT '0',
	KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'mail_integration'
--
DROP TABLE IF EXISTS mail_integration;
CREATE TABLE mail_integration (
	forumid INT NOT NULL,
	email VARCHAR(100) NOT NULL,
	pop_username VARCHAR(100) NOT NULL,
	pop_password VARCHAR(100) NOT NULL,
	pop_host VARCHAR(100) NOT NULL,
	pop_port INT DEFAULT 110,
	pop_ssl TINYINT DEFAULT '0',
	KEY(forumid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


--
-- Table structure for table 'api'
--
DROP TABLE IF EXISTS api;
CREATE TABLE api (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	apikey VARCHAR(32) NOT NULL,
	validity DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

--
-- Table structure for table 'moderation_log'
-- 
DROP TABLE IF EXISTS moderation_log;
CREATE TABLE moderation_log (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userid INT NOT NULL,
	description TEXT NOT NULL,
	originalmessage TEXT,
	date DATETIME NOT NULL,
	type TINYINT DEFAULT 0,
	postid INT DEFAULT 0,
	topicid INT DEFAULT 0,
	postUserid INT DEFAULT 0,
	KEY(id),
	KEY(postid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

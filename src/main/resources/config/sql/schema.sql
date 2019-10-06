
-- 需要 MySQL 5.6.5以上的版本
CREATE DATABASE channelapi_web;
USE channelapi_web;


CREATE TABLE report_data_day
(
  id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  biz_date DATE NOT NULL,
  adverter_code VARCHAR(100),
  app_code VARCHAR(100),
  click_cnt INT(10),
  callback_cnt INT(10),
  active_cnt INT(10)
)ENGINE=INNODB  DEFAULT CHARSET=utf8;

CREATE TABLE user
(
  id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role_id INT(4) COMMENT '1:管理员，2:渠道商',
  role_name VARCHAR(100)
)ENGINE=INNODB  DEFAULT CHARSET=utf8;


create table role_menu
(
    id        int(10) primary key not null auto_increment,
    role_id   int(4)       ,
    menu_id   int(4)       ,
    menu_name varchar(255) not null,
    menu_url  varchar(255) ,
    menu_icon varchar(255)
) ENGINE=INNODB  DEFAULT CHARSET=utf8;


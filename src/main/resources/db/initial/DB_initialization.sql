create database falcondb;

create user 'falcon_usr'@'localhost' identified by 'Db12PwD';

grant all on falcondb.* to 'falcon_usr'@'localhost';
create database falcondb;

create user 'falcon_usr'@'%' identified by 'Db12PwD';

grant all on falcondb.* to 'falcon_usr'@'%';
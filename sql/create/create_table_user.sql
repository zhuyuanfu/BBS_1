drop table if exists user;
create table user (
	userid			int(10)not null auto_increment,
    name			varchar(32),
    password		varchar(32),
    sex				char(2),
    age				int(10),
    address			varchar(40),
    email			varchar(50),
	phone			varchar(32),
    introduction	varchar(50),
    is_admin		boolean default false,
    
    primary key(userid)
) default character set=utf8;
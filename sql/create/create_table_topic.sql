drop table if exists topic;
create table topic (
	id		int(10)not null auto_increment,
    title	varchar(100),
    author	varchar(30),
    content	text,
    pubtime	datetime default current_timestamp,
    primary key(id)
) default charset=utf8;
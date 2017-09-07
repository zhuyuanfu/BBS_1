drop table if exists back;
create table back (
	id		int(10)not null auto_increment,
    topicId	int(10) ,
    title	varchar(100),
    author	varchar(30),
    content	text,
    pubtime	datetime default current_timestamp,
    
    primary key(id),
    foreign key(topicId) references topic(id)
) default charset=utf8;
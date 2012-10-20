create table tblPoll(
	id int not null auto_increment,
	short varchar(15) not null,
	question varchar(100) not null,
	constraint pk_Poll primary key(id),
	constraint uk_Poll_short unique(short)
)
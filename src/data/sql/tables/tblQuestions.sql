create table tblQuestions(
	id int not null auto_increment,
	id_poll int not null,
	name varchar(25) not null,
	description varchar(256) null,
	votes int default 0,
	constraint pk_questions primary key(id),
	constraint uk_questions_name unique(name),
	constraint fk_questions_poll foreign key(id_poll) references tblQuestions(id)
		on delete cascade
)
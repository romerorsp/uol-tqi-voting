start transaction;
	insert into tblPoll(short, question) values("MASCOTE", "Escolha o melhor nome para o mascote");
	insert into tblQuestions(id_poll, name, description)
		select id, 
		       "Amijubi", 
		       "União das palavras amizade e jubilo, que está ligado ao tupi guarani, em que jubi significa amarelo, cor predominante no mascote."
		from tblPoll
		where short = "MASCOTE";
		
	insert into tblQuestions(id_poll, name, description)
		select id, 
		       "Fuleco", 
		       "Uma mistura de futebol e ecologia. O nome busca incentivar o cuidado das pessoas com o meio ambiente."
		from tblPoll
		where short = "MASCOTE";
	
	insert into tblQuestions(id_poll, name, description)
		select id, 
		       "Zuzeco", 
		       "Mistura da cor azul com ecologia -, que busca também incentivar cuidados relacionados à ecologia."
		from tblPoll
		where short = "MASCOTE";
commit;
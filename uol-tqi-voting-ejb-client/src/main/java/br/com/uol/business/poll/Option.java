package br.com.uol.business.poll;

import java.io.Serializable;

public interface Option extends Serializable{
	public Poll getPoll();
	
	public int getTotalVotes();
	
	public int getId();
	
	public String getName();
	
	public String getDescription();
}

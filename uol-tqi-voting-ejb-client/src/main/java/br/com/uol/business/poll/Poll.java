package br.com.uol.business.poll;

import java.io.Serializable;
import java.util.List;

public interface Poll extends Serializable{
	public List<Option> getOptions();
	
	public String getQuestion();

	public int getTotalVotes();
}

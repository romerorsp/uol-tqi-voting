package br.com.uol.business.poll;

import java.util.ArrayList;
import java.util.List;

public class PollImpl implements Poll {

	private static final long serialVersionUID = -4529311515267414587L;
	private List<Option> options;

	@Override
	@SuppressWarnings("serial")
	public List<Option> getOptions() {
		// TODO Auto-generated method stub
		return options = new ArrayList<Option>(){{
			add(new OptionImpl(PollImpl.this));
			add(new OptionImpl(PollImpl.this));
			add(new OptionImpl(PollImpl.this));
		}};
	}

	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return "Escolha o melhor nome para o mascote";
	}

	@Override
	public int getTotalVotes() {
		int result = 0;
		for(Option opt: this.options){
			result += opt.getTotalVotes();
		}
		return result;
	}

}

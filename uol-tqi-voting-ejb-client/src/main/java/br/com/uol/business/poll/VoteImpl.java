package br.com.uol.business.poll;

public class VoteImpl implements Vote {
	private static final long serialVersionUID = -8560620179993166516L;
	private final Option option;

	public VoteImpl(Option option){
		this.option = option;
	}
	
	@Override
	public Option getOption() {
		return option;
	}
}
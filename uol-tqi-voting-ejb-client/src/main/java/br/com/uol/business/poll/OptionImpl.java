package br.com.uol.business.poll;

public class OptionImpl implements Option {

	private static final long serialVersionUID = -7483300644860624056L;
	private final PollImpl poll;

	public OptionImpl(PollImpl poll) {
		// TODO Auto-generated constructor stub
		this.poll = poll;
	}

	@Override
	public Poll getPoll() {
		return poll;
	}

	@Override
	public int getTotalVotes() {
		// TODO Auto-generated method stub
		return 33;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.hashCode();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Mistura da cor azul com ecologia -, que busca também incentivar cuidados relacionados à ecologia.";
	}

}

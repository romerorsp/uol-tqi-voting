package br.com.uol.business;

import br.com.uol.business.poll.Poll;
import br.com.uol.business.poll.Vote;

public interface PollBO {
	public static final String MAPPED_NAME = "poll";
	public static String JNDI_PATH = String.format("ejb:uol-tqi-voting-ear/voting/%s!%s", MAPPED_NAME, PollBO.class.getName());

	public void registerVote(Vote vote);
	
	public Poll getPollByShortName(String sn);
	
	public void savePoll(Poll poll);
}
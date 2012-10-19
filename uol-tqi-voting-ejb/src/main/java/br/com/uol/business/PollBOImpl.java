package br.com.uol.business;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.com.uol.business.poll.Poll;
import br.com.uol.business.poll.PollImpl;
import br.com.uol.business.poll.Vote;
import br.com.uol.util.PersistenceUtil;

/**
 * Session Bean implementation class PollBOImpl
 */
@Remote(PollBO.class)
@Stateless(name=PollBO.MAPPED_NAME, mappedName = PollBO.MAPPED_NAME)
@TransactionManagement(TransactionManagementType.BEAN)
public class PollBOImpl implements PollBO {

    /**
     * Default constructor. 
     */
    public PollBOImpl() {
//        EntityManager m = PersistenceUtil.getEntityManager();
    }

	@Override
	public void registerVote(Vote vote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Poll getPollByShortName(final String sn) {
		// TODO Auto-generated method stub
		return new PollImpl();
	}

	@Override
	public void savePoll(Poll poll) {
		// TODO Auto-generated method stub
		
	}

}

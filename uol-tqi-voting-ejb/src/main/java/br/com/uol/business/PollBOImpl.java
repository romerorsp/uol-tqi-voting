package br.com.uol.business;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.uol.business.poll.Poll;
import br.com.uol.business.poll.Vote;
import br.com.uol.util.PersistenceUtil;

/**
 * Session Bean implementation class PollBOImpl
 */
@Remote(PollBO.class)
@Stateless(name=PollBO.MAPPED_NAME, mappedName = PollBO.MAPPED_NAME)
@TransactionManagement(TransactionManagementType.BEAN)
public class PollBOImpl implements PollBO {

	@Override
	public void registerVote(Vote vote) {
		
		EntityManager m = PersistenceUtil.getEntityManager();
		m .getTransaction().begin();
		Query query = m.createQuery("update Option q set q.totalVotes = q.totalVotes+1 where q.id = :id");
		query.setParameter("id", vote.getOption().getId());
		if(!(query.executeUpdate() == 1)){
			m.getTransaction().rollback();
			throw new RuntimeException("Não foi possível atualizar as informações sobre o voto.");
		}
		m.getTransaction().commit();
		m.clear();
	}

	@Override
	public Poll getPollByShortName(final String sn) {
		EntityManager m = PersistenceUtil.getEntityManager();
		Query query = m.createQuery("from Poll p where p.shortName = ?");
		query.setParameter(1, sn);
		Object o;
		if((o=query.getSingleResult()) != null){
			m.clear();
			return Poll.class.cast(o);
		}
		return null;
	}

	@Override
	public void savePoll(Poll poll) {
		EntityManager m = PersistenceUtil.getEntityManager();
		m .getTransaction().begin();
		m.persist(poll);
		m.getTransaction().commit();
		m.clear();
	}
}
package br.com.uol.testing;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

public class PersistenceIntegrationTest {
	
	@SuppressWarnings("unused")
	private EntityManager em;

	@Before
	public void startup(){
//		em = PersistenceUtil.getEntityManager();
	}
	
	@Test
	public void connectionTest(){
//		Assert.assertNotNull("It looks there's something wrong with your configuratins", em.createQuery("from Poll").getSingleResult());
	}
}

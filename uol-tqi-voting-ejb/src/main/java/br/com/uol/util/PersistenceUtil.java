package br.com.uol.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class PersistenceUtil {
	
	
	private static final EntityManagerFactory factory;

	static{
		factory = Persistence.createEntityManagerFactory("UOL");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}

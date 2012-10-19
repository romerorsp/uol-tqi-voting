package br.com.uol.business;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

public class ServiceLocator {
	private static final Properties properties = new Properties();
	private static final Logger LOGGER = Logger.getLogger(ServiceLocator.class.getName());
	
	static{
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jboss-ejb-client.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, String.format("it was not possible to get properties for service location, due: %s", e.getLocalizedMessage()));
		}
	}
	
	public static <T> T locate(final String PATH, Class<T> clazz){
		try {
			properties.put("jboss.naming.client.ejb.context", true);
			InitialContext context = new InitialContext(properties);
			return clazz.cast(context.lookup(PATH));
		} catch (NamingException e) {
			e.printStackTrace();
			Assert.fail(String.format("it was not possible to create the initial context, due: %s", e.getLocalizedMessage()));
		}
		return null;
	}
}

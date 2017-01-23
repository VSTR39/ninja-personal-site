package dao;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.registered_users;

public class PasswordRestoreDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	public boolean isRegisteredUser(String email) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from registered_users where email = ?1").setParameter(1, email);
		if (query.getResultList().size() == 1) {
			result = true;
		}
		return result;
	}

	public String getRegisteredUserCredentials(String email) {
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from registered_users where email = ?1").setParameter(1, email);
		registered_users user = (registered_users)query.getSingleResult();
		String result = "Username:\n" + user.getUsername()+ "\nPassword:\n" + user.getPassword();
		return result;
	}
}

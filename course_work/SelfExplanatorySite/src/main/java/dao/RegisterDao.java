package dao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.registered_users;

public class RegisterDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	public boolean isRegistrationSuccessful(String username, String password, String email) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		registered_users new_user = new registered_users();
		if (areUsernameAndEmailFree(username, email)) {

			new_user.setUsername(username);
			new_user.setPassword(password);
			new_user.setEmail(email);
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(new_user);
			transaction.commit();
			result = true;
		}

		return result;
	}

	private boolean areUsernameAndEmailFree(String username, String email) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from registered_users where username = ?1 and email = ?2")
				.setParameter(1, username).setParameter(2, email);
		if (query.getResultList().isEmpty()) {
			result = true;
		}
		return result;
	}
}

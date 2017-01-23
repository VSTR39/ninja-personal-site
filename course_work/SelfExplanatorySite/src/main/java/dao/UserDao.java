package dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Transaction;

import com.google.inject.Provider;

import entities.registered_users;

public class UserDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	public boolean isUsernameAndPasswordValid(String username, String password) {
		EntityManager entityManager = entityManagerProvider.get();
		boolean result = false;
		List<?> users = entityManager.createQuery("from registered_users where username = ?1 and password = ?2")
				.setParameter(1, username).setParameter(2, password).getResultList();
		if (users.size() == 1) {
			result = true;
		}
		return result;
	}

	public List<registered_users> getAllUsers() {
		EntityManager entityManager = entityManagerProvider.get();
		List<registered_users> users = entityManager.createQuery("from registered_users").getResultList();
		return users;
	}

	public registered_users getUser(long id) {
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from registered_users where id = ?1").setParameter(1, id);
		registered_users result = (registered_users) query.getSingleResult();
		return result;
	}

	public boolean isDeleteUserSuccessful(String username) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from registered_users where username = ?1").setParameter(1, username);
		if (!query.getResultList().isEmpty()) {
			registered_users user = (registered_users) query.getSingleResult();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(user);
			transaction.commit();
			result = true;
		}
		return result;
	}
	
	/*public List<comments> getAllComments(){
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from comments");
		List<comments> result = query.getResultList();
		return result;
	}*/
}

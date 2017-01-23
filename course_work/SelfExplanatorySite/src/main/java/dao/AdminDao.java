package dao;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.inject.Provider;

import entities.admins;
import entities.articles;
import entities.registered_users;
import entities.sections;

public class AdminDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	public boolean isAdmin(String username) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query queryRegisteredUser = entityManager.createQuery("from registered_users where username = ?1")
				.setParameter(1, username);
		if (queryRegisteredUser.getResultList().size() == 1) {
			registered_users user = (registered_users) queryRegisteredUser.getSingleResult();
			Query queryAdmin = entityManager.createQuery("from admins where registered_users_id = ?1").setParameter(1,
					user.getId());
			if (queryAdmin.getResultList().size() == 1) {
				result = true;
			}

		}
		return result;
	}

	public boolean isRegisteredUser(String username) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query queryRegisteredUser = entityManager.createQuery("from registered_users where username = ?1")
				.setParameter(1, username);
		if (queryRegisteredUser.getResultList().size() == 1) {
			registered_users user = (registered_users) queryRegisteredUser.getSingleResult();
			result = true;
		}
		return result;

	}

	public boolean isSuccessfullyGrantedAdminship(String username) {
		boolean result = false;
		boolean isAdmin = isAdmin(username);
		boolean isRegisteredUser = isRegisteredUser(username);
		EntityManager entityManager = entityManagerProvider.get();
		if (!isAdmin && isRegisteredUser) {
			Query queryRegisteredUser = entityManager.createQuery("from registered_users where username = ?1")
					.setParameter(1, username);
			registered_users user = (registered_users) queryRegisteredUser.getSingleResult();
			admins admin = new admins();
			admin.setRegistered_users_id(user.getId());
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(admin);
			transaction.commit();
			result = true;
		}
		return result;
	}

	public boolean isSectionValid(String section) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from sections where name = ?1").setParameter(1, section);
		if (!query.getResultList().isEmpty()) {
			result = true;
		}
		return result;
	}

	public boolean isTitleValid(String title) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from articles where title = ?1").setParameter(1, title);
		if (query.getResultList().isEmpty()) {
			result = true;
		}
		return result;
	}

	public boolean titleExists(String title) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from articles where title = ?1").setParameter(1, title);
		if (!query.getResultList().isEmpty()) {
			result = true;
		}
		return result;
	}

	public boolean isWritePostSuccessful(String author, String section, String title, String content) {
		boolean result = false;
		EntityManager entityManager = entityManagerProvider.get();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		articles article = new articles();
		if (isSectionValid(section) && isTitleValid(title)) {
			Query queryAuthor = entityManager.createQuery("from registered_users where username = ?1").setParameter(1,
					author);
			registered_users article_author = (registered_users) queryAuthor.getSingleResult();
			Query queryAuthorId = entityManager.createQuery("from admins where registered_users_id = ?1")
					.setParameter(1, article_author.getId());
			admins admin = (admins) queryAuthorId.getSingleResult();
			Query querySection = entityManager.createQuery("from sections where name = ?1").setParameter(1, section);
			sections sec = (sections) querySection.getSingleResult();
			article.setAuthor_id(admin.getId());
			article.setSection_id(sec.getId());
			article.setTimestamp(timestamp);
			article.setTitle(title);
			article.setContent(content);
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(article);
			transaction.commit();
			result = true;
		}
		return result;
	}

	public boolean isPostDeletedSuccessfully(String title) {
		EntityManager entityManager = entityManagerProvider.get();
		boolean isTitleValid = titleExists(title);
		boolean result = false;
		if (isTitleValid) {
			Query query = entityManager.createQuery("from articles where title = ?1").setParameter(1, title);
			articles article = (articles) query.getSingleResult();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(article);
			transaction.commit();
			result = true;
		}
		return result;
	}

}

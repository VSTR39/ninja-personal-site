package dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import entities.articles;
import entities.registered_users;

public class ArticleDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	public List getAllArticles() {
		List result;
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from articles");
		return query.getResultList();
	}

	public articles getArticle(Long id) {
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from articles where id = ?1").setParameter(1, id);
		articles result = (articles)query.getSingleResult();
		return result;
	}
	
//	public List getAllCommentsOnArticle(long id){
//		EntityManager entityManager = entityManagerProvider.get();
//		Query query = entityManager.createQuery("from comments where article_id = ?1").setParameter(1, id);
//		return query.getResultList();
//	}
	public List getAllPostsInSection(long id){
		EntityManager entityManager = entityManagerProvider.get();
		Query query = entityManager.createQuery("from articles where section_id = ?1").setParameter(1, id);
		List result = query.getResultList();
		return result;
	}
	
	/*public void comment(String author,String content,long id){
		EntityManager entityManager = entityManagerProvider.get();
		EntityTransaction transaction = entityManager.getTransaction();
		comments comment = new comments();
		Query query = entityManager.createQuery("from registered_users where username = ?1").setParameter(1, author);
		registered_users user = (registered_users) query.getSingleResult();
		Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		comment.setArticle_id(id);
		comment.setAuthor_id(user.getId());
		comment.setAuthor_name(author);
		comment.setContent(content);
		comment.setTimestamp(timestamp);
		transaction.begin();
		entityManager.persist(comment);
		transaction.commit();
	}*/
}

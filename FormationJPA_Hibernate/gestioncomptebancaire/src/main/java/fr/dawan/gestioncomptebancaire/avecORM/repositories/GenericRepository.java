package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class GenericRepository<T, ID> implements IGenericRepository<T, ID> {

	private final String persistenceUnit = "gestionCB_mysql";	
	
	
	@Override
	public void save(T entity) {
		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public T findById(Class<T> entityClass, ID id) {
		EntityManager em = createEntityManager();
		T entity = null;
		try {
			entity = em.find(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return entity;
	}

	@Override
	public void deleteById(Class<T> entityClass, ID id) {
		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			T entity = em.find(entityClass, id);
			if(entity != null) {
				em.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}

		
	}

	@Override
	public void update(T entity) {
		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}

	}

	@Override
	public List<T> getAll(Class<T> entityClass) {
		List<T> entities = null;
		EntityManager em = createEntityManager();
		
		try {
			String entity_jpq_query =  "SELECT e FROM " + entityClass.getName() + " e";
			TypedQuery<T> query = em.createQuery(entity_jpq_query, entityClass);
			entities = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return entities;
	}
	
	/**
	 * Crée et retourne un objet EntityManager pour interagir avec la base données.
	 * @return  EntityManager nouvellement créé
	 */
	protected EntityManager createEntityManager() {
		//Création de l'entityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		//Création de l'entityManager
		EntityManager em = emf.createEntityManager();
		return em;
	}

}

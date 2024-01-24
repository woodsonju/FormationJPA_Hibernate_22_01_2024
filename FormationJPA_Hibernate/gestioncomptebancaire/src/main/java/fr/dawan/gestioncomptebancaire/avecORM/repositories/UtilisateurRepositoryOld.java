package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UtilisateurRepositoryOld implements IUtilisateurRepositoryOld {

	private static final Logger logger = LoggerFactory.getLogger(UtilisateurRepositoryOld.class);
	private final String persistenceUnit = "gestionCB_mysql";

	@Override
	public void addUser(Utilisateur user) {		
		EntityManager em = createEntityManager();
		//Creation d'une transaction 
		EntityTransaction transaction = em.getTransaction();

		try {
			//Demarrer la transaction 
			transaction.begin();

			//Enregistrer l'utilisateur dans le contexte de persistance
			em.persist(user);

			//Valider la transaction
			transaction.commit();  //Enregistre l'utilisateur dans la base de données
			logger.info("Utilisateur ajouté : OK");
		} catch (Exception e) {
			//Annuler la transaction en cas d'zxception
			transaction.rollback();
			logger.error("Erreur lors de l'ajout de l'utilisateur");
			e.printStackTrace();
		} finally {
			em.close();
		}



	}



	@Override
	public Utilisateur findUserById(Long id) {
		EntityManager em = createEntityManager();
		Utilisateur user = null;

		try {
			user = em.find(Utilisateur.class, id);
			logger.info("Recuperation de l'utilisateur : " + user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("L'utilisateur n'existe pas");
		}finally {
			em.close();
		}

		return user;
	}

	@Override
	public void deleteUserById(Long id) {
		EntityManager em = createEntityManager();
		//Creation d'une transaction 
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Utilisateur user = em.find(Utilisateur.class, id);
			if(user != null) {
				em.remove(user);
			}

			transaction.commit();
			logger.info("L'utilisateur a été supprimé : OK");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Erreur lors de la suppression de l'utilisateur");
			e.printStackTrace();
		}finally {
			em.close();
		}

	}

	@Override
	public void updateUser(Utilisateur user) throws Exception {
		EntityManager em = createEntityManager();
		//Creation d'une transaction 
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.merge(user);
			transaction.commit();
			logger.info("L'utilisateur a été mise à jour  ");
		} catch (Exception e) {
			transaction.rollback();
			logger.error("Erreur lors de la mise de l'utilisateur");
			logger.error("Echec Interdiction de mettre à jour simultanement la même entité (Vérrouillage optimiste)" + e);
			
			e.printStackTrace();
			throw new Exception("Echec Interdiction de mettre à jour simultanement la même entité (Vérrouillage optimiste)" + e);
		}finally {
			em.close();
		}

	}

	@Override
	public List<Utilisateur> getAllUsers() {
		List<Utilisateur> users = null;
		EntityManager em = createEntityManager();
		
		try {
			//Creation de la requete JPQL : SELECT u FROM Utilisateur u
			String user_jpql_query = "SELECT u FROM " + Utilisateur.class.getName() + " u"; 
			TypedQuery<Utilisateur> query =  em.createQuery(user_jpql_query, Utilisateur.class);
			
			//Execution de la requête pour recuperer le resultat 
			users = query.getResultList();
			
			logger.info("Récuperation de la liste des utilisateurs avec succès " + users);
			 
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de la liste des utilisateurs");
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return users;
	}

	@Override
	public List<Utilisateur> findAll(int begin, int nbResult) {
		
		List<Utilisateur> users = null;
		EntityManager em = createEntityManager();
		
		try {
			//Creation de la requete JPQL : SELECT u FROM Utilisateur u
			String user_jpql_query = "SELECT u FROM " + Utilisateur.class.getName() + " u"; 
			TypedQuery<Utilisateur> query =  em.createQuery(user_jpql_query, Utilisateur.class);
			
			//Execution de la requête pour recuperer le resultat 
			users = query.setFirstResult(begin)//Definit l'index du premier resultat à recuperer
					.setMaxResults(nbResult)    //Definit le nombre maximal de resultats à recuperer
					.getResultList();
			
			logger.info("Récuperation de la liste des utilisateurs (paginées) avec succès " + users);
			 
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de la liste des utilisateurs paginées");
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return users;
	}


	/**
	 * Crée et retourne un objet EntityManager pour interagir avec la base données.
	 * @return  EntityManager nouvellement créé
	 */
	private EntityManager createEntityManager() {
		//Création de l'entityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		//Création de l'entityManager
		EntityManager em = emf.createEntityManager();
		return em;
	}

}

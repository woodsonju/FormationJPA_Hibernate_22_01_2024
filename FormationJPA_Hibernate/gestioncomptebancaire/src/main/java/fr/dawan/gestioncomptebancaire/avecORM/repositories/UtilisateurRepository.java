package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UtilisateurRepository extends GenericRepository<Utilisateur, Long> implements IUtilisateurRepository{

	private static final Logger logger = LoggerFactory.getLogger(UtilisateurRepositoryOld.class);

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

	@Override
	public Utilisateur findByEmail(String email) {
		
		Utilisateur user = null;
		EntityManager em = createEntityManager();
		
		try {
			String user_jpql_query = "SELECT u FROM Utilisateur u WHERE u.email = :email";
			TypedQuery<Utilisateur> query = em.createQuery(user_jpql_query, Utilisateur.class);
			query.setParameter("email", email);
			user = query.getSingleResult();
			logger.info("Récuperation de l'utilisateur vec succès " + user);
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de l'utilisateur");
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return user;
	}

	@Override
	public void saveUserWithComptes(Utilisateur user) {
		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			
			em.persist(user);
			
			//Associez les comptes à l'utilisateur 
			for (Compte compte : user.getComptes()) {
				compte.setClient(user);
				em.persist(compte);
			}
			
			transaction.commit();
			logger.info("Utilisateur et comptes associés ajoutés avec succès ");
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de l'utilisateur");
			transaction.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public Utilisateur findUserWithComtes(Long userId) {
		Utilisateur utilisateur = null;
		EntityManager em = createEntityManager();
		try {
			String jpql_user = "SELECT u FROM Utilisateur u JOIN FETCH u.comptes WHERE u.id = :userId";
			TypedQuery<Utilisateur> query =  em.createQuery(jpql_user, Utilisateur.class);
			query.setParameter("userId", userId);
			utilisateur =  query.getSingleResult();
			logger.info("Recuperation de l'utilisateur et comptes associés " + utilisateur);
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de l'utilisateur et ses comptes");
			e.printStackTrace();

		}finally {
			em.close();
		}
		return utilisateur;
	}

	


}	

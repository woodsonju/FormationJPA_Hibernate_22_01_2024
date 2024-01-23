package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UtilisateurRepository implements IUtilisateurRepository {

	private static final Logger logger = LoggerFactory.getLogger(UtilisateurRepository.class);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Utilisateur user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Utilisateur> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findAll(int begin, int nbResult) {
		// TODO Auto-generated method stub
		return null;
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

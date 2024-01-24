package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import jakarta.persistence.EntityManager;
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

	


}	

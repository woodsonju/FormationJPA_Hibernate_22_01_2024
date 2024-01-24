package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Compte;
import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

//TODO Ajouter une interface specifique pour la pagination 
//CompteRepository implemente cette interface
public class CompteRepository extends GenericRepository<Compte, String> implements ICompteRepository{

	private static final Logger logger = LoggerFactory.getLogger(CompteRepository.class);

	
	@Override
	public List<Compte> findAll(int begin, int nbResult) {
		List<Compte> comptes = null;
		EntityManager em = createEntityManager();
		try {
			String user_jpql_query = "SELECT c FROM " + Compte.class.getName() + " c"; 
			TypedQuery<Compte> query =  em.createQuery(user_jpql_query, Compte.class);
			
			//Execution de la requête pour recuperer le resultat 
			comptes = query.setFirstResult(begin)//Definit l'index du premier resultat à recuperer
					.setMaxResults(nbResult)    //Definit le nombre maximal de resultats à recuperer
					.getResultList();
			
			logger.info("Récuperation de la liste des comptes (paginées) avec succès " + comptes);
			 
		} catch (Exception e) {
			logger.error("Erreur lors de la recuperation de la liste des comptes paginées");
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return comptes;
	}

}

package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Utilisateur;

public interface IUtilisateurRepository extends IGenericRepository<Utilisateur, Long>{


	List<Utilisateur> findAll(int begin, int nbResult);
	
	Utilisateur findByEmail(String email);
	
	
	/**
	 * Enregistre un utilisateur avec une liste de comptes 
	 * 
	 * @param user
	 */
	void saveUserWithComptes(Utilisateur user);
	
	/**
	 * Cette méthode utilise une requête JPQL pour récupérer un utilisateur avec ses comptes 
	 * en utilisant JOIN FETCH. L'annotation JOIN FETCH indique à JPA de récupérer les entités 
	 * associées (ici, les comptes) en même temps que l'entité principale (ici, l'utilisateur), 
	 * évitant ainsi les problèmes de LazyInitializationException 
	 * @param userId Identifiant de l'utilisateur à récupérer avec ses comptes.
	 * @return L'utilisateur avec ses comptes ou null s'il n'est pas trouvé.
	 */
	Utilisateur findUserWithComtes(Long userId);

}

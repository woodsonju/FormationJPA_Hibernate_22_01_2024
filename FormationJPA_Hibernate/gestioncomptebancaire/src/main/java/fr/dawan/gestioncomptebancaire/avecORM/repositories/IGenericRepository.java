package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

/**
 * 
 * @param <T>  : represent une entité
 * @param <ID> : represent l'identifiant de l'entité
 */
public interface IGenericRepository<T, ID> {
	/**
	 * Ajoute une entité 
	 * @param entity : L'entité à ajouter
	 */
	void save(T entity);
	
	/**
	 * Recherche et retourne une entité par son identifiant
	 * @param id l'identifiant de l'entité à rechercher
	 * @return L'entité correspodante ou nu si aucune entité n'est trouvé
	 */
	T findById(Class<T> clazz, ID id);
	
	
	/**
	 * Supprime une entité par son identifiant
	 * @param id L'identifiant de l'entité à supprimer
	 */
	void deleteById(Class<T> entityClass, ID id);
	
	
	/**
	 * Met à jour une entité
	 * @param entity : L'entité à mettre à jour
	 */
	void update(T entity);
	
	/**
	 * Récupère toutes les entités
	 * @return une liste de toutes les entités
	 */
	List<T> getAll(Class<T> clazz);
}

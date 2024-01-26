package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RoleRepository extends GenericRepository<Role, Long> implements IRoleRepository{

	private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);
	
	@Override
	public List<Role> findRoleByContaing(String keyword) {
		List<Role> roles = null;
		EntityManager em = createEntityManager();
		try {
			String role_query = "SELECT r FROM Role r WHERE r.nom LIKE :keyword";
			TypedQuery<Role> query = em.createQuery(role_query, Role.class);
			query.setParameter("keyword", "%" + keyword + "%");
			roles = query.getResultList();
			logger.info("Roles : " + roles);
		} catch (Exception e) {
			logger.error("Erreur lors de la recherche des roles par mot clé");
			e.printStackTrace();
			return Collections.emptyList();
		}finally {
			em.close();
		}
		return roles;
	}

}

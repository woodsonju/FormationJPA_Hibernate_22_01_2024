package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.util.List;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Role;

public interface IRoleRepository extends IGenericRepository<Role, Long>{
	
	List<Role> findRoleByContaing(String keyword);

}
